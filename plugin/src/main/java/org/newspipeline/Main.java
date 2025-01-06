package org.newspipeline;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        NewsBreakParser newsBreakParser = new NewsBreakParser();
        newsBreakParser.getArticles(new T()).subscribe(System.out::println);
        Thread.sleep(20000);
    }

}
class T implements Location{

    @Override
    public String getToponymName() {
        return "New York City";
    }

    @Override
    public String getAdminCode1() {
        return "NY";
    }

    @Override
    public String getAdminName1() {
        return null;
    }
}
