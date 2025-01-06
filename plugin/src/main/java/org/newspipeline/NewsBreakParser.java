package org.newspipeline;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class NewsBreakParser implements WebsitePlugin{

    @Override
    public Flux<Article> getArticles(Location location) {
        LinkBuilder linkBuilder;
        linkBuilder = new LinkBuilder(location);
        return Flux.create(element -> {
            try {
                Document document;
                while (true) {

                    document = Jsoup.connect(linkBuilder.getNextPage()).get();

                    if (!document.selectFirst("div.font-black.md\\:mb-2.cursor-pointer.lg\\:text-22.md\\:text-2xl.text-xl.flex.items-start").text()
                            .equalsIgnoreCase(location.getToponymName())) {
                        break;
                    }

                    Elements divs = document.select("div.border-b.border-gray-200");

                    if (divs.isEmpty()) {
                        break;
                    }

                    divs.forEach(element::next);
                }
                element.complete();
            } catch (IOException e) {
                element.error(e);
            }
        })
                .parallel()
                .runOn(Schedulers.boundedElastic())
                .map(article -> {
                    Element art = (Element) article;
                    Element link = art.select("div.flex-1.mr-3").first();
                    Element image = art.select("source.jsx-38949707").first();
                    Element date = art.selectFirst("div.flex.flex-row.items-center.space-x-2.text-gray-light.text-sm");
                    Element h = link.selectFirst("h3");
                    return (Article) new News("https://www.newsbreak.com" + link.selectFirst("a").attr("href"),
                            h.text(),
                            location.getToponymName(),
                            image == null ? null : image.attr("srcset"),
                            parseDate(date.text())
                            );
                })
                .sequential();
    }

    private String parseDate(String date){
        int index = -1;
        for (int i = 0; i < date.length(); i++) {
            if (Character.isDigit(date.charAt(i))) {
                index = i;
                break;
            }
        }

        String dateString = (index != -1) ? date.substring(index) : "";
        String timeNumber = dateString.substring(0, dateString.length() - 1);
        char lastCharacter = dateString.charAt(dateString.length() - 1);
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime utcTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        switch (lastCharacter){
            case 'd':
                utcTime = now.atZone(ZoneId.systemDefault())
                        .withZoneSameInstant(ZoneId.of("UTC")).minus(Integer.parseInt(timeNumber), ChronoUnit.DAYS);
                return utcTime.format(formatter);
            case 'h':
                utcTime = now.atZone(ZoneId.systemDefault())
                        .withZoneSameInstant(ZoneId.of("UTC")).minus(Integer.parseInt(timeNumber), ChronoUnit.HOURS);
                return utcTime.format(formatter);
            case 'm':
                utcTime = now.atZone(ZoneId.systemDefault())
                        .withZoneSameInstant(ZoneId.of("UTC")).minus(Integer.parseInt(timeNumber), ChronoUnit.MINUTES);
                return utcTime.format(formatter);
        }
        return null;
    }

}



class LinkBuilder {
    private final Location location;
    private StringBuilder link = new StringBuilder("https://www.newsbreak.com/");
    private Integer page = 1;

    LinkBuilder(Location location){
        this.location = location;
        link.append(location.getToponymName().toLowerCase().replace(" ", "-"));
        link.append("-" + location.getAdminCode1().toLowerCase());
        link.append("?page=");
    }

    public String getNextPage(){
        String url = link.toString() + page;
        page++;
        return url;
    }
}


class News implements Article {

    private String url;
    private String title;
    private String location;
    private String imageUrl;
    private String time;

    public String toString(){
        return url + "   " + title + "   " + location + "   " + imageUrl + "   " + time + "\n";
    }
    public News(String url, String title, String location, String imageUrl, String time) {
        this.url = url;
        this.title = title;
        this.location = location;
        this.imageUrl = imageUrl;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(title, news.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time){this.time = time;}

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl){this.imageUrl = imageUrl;}

    public void setLocation(String location) {
        this.location = location;
    }
}



