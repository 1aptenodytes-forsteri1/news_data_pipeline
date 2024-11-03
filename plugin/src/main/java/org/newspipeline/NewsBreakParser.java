package org.newspipeline;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import java.io.IOException;
import java.util.Objects;

public class NewsBreakParser implements WebsitePlugin {

    @Override
    public Flux<Article> getArticles(Location location) {
        LinkBuilder linkBuilder;
        linkBuilder = new LinkBuilder(location);
        return Flux.create(element -> {
                    Document document;
                    try {
                        document = Jsoup.connect(linkBuilder.getNextPage()).get();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (!document.select("p.font-black.mb-2.cursor-pointer.lg\\:text-28.md\\:text-2xl.text-xl.flex.items-center").text().equalsIgnoreCase(location.getToponymName())){
                        element.complete();
                    }
                    Elements divs = document.select("div.flex-1.mr-3");
                while (true){
                    if (!divs.isEmpty()){
                        divs.forEach(element::next);
                    }else{
                        break;
                    }
                    try {
                        divs = Jsoup.connect(linkBuilder.getNextPage()).get().select("div.flex-1.mr-3");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                element.complete();
        })
                .parallel()
                .runOn(Schedulers.boundedElastic())
                .map(article -> {
                    Element art = (Element) article;
                    Elements links = art.select("a");
                    Element link;
                    String url;
                    if (links.size() == 1) {
                        link = links.get(0);
                    } else {
                        link = links.get(1);
                    }
                    try {
                        url = checkRedirection(link.attr("href"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Element h3 = link.selectFirst("h3");
                    return (Article) new News(url,
                            h3.text(),
                            location.getToponymName());
                })
                .sequential()
                .doOnComplete(()->System.out.println("t"));
    }

    public String checkRedirection(String url) throws IOException {
        String initialUrl = url;
        Document document = Jsoup.connect(initialUrl).get();
        Element element = document.select("div.pt-8.px-4").first();
        if(element != null){
            String divText = element.text();
            initialUrl = divText.substring(15);
        }
        return initialUrl;
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

    public String toString(){
        return title;
    }
    public News(String url, String title, String location) {
        this.url = url;
        this.title = title;
        this.location = location;

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

    public void setLocation(String location) {
        this.location = location;
    }
}



