package com.farouk.bengarssallah.kontu.rss;

import org.springframework.scheduling.annotation.Scheduled;
import java.util.LinkedList;
import org.springframework.stereotype.Service;

@Service("aBCRSSExtractor")
public class ABCRSSExtractor
{
    private static final String abc_money_rss = "http://www.abc.net.au/news/feed/51892/rss.xml";
    private LinkedList<ABCNews> abc_news_money_list;
    
    public ABCRSSExtractor() {
        this.abc_news_money_list = null;
    }
    
    public LinkedList<ABCNews> getABCMoneyNews() {
        if (this.abc_news_money_list == null) {
            this.abc_news_money_list = new LinkedList<ABCNews>();
            final ABCParser parser = new ABCParser("http://www.abc.net.au/news/feed/51892/rss.xml");
            this.abc_news_money_list = parser.getNews();
        }
        return this.abc_news_money_list;
    }
    
    @Scheduled(cron = "0 0 0 1 * ?")
    private void updateNewsList() {
        final ABCParser parser = new ABCParser("http://www.abc.net.au/news/feed/51892/rss.xml");
        this.abc_news_money_list = parser.getNews();
    }
}
