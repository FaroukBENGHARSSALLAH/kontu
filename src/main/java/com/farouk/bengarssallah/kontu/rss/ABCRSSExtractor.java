package com.farouk.bengarssallah.kontu.rss;

import java.util.LinkedList;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service ("aBCRSSExtractor")
public class ABCRSSExtractor  {
	
	private static final String abc_money_rss = "http://www.abc.net.au/news/feed/51892/rss.xml";
	

	private LinkedList<ABCNews> abc_news_money_list = null;
	

	
	public LinkedList<ABCNews> getABCMoneyNews()  {
									  if(abc_news_money_list == null){
										                    abc_news_money_list = new LinkedList<ABCNews>();
												            ABCParser parser = new ABCParser(abc_money_rss);
												            abc_news_money_list = parser.getNews();
							                           }
							          return abc_news_money_list;
	                       }
	
	
	  @Scheduled(cron = "0 0 0 1 * ?")
	  private void updateNewsList(){
		                        {
			             ABCParser parser = new ABCParser(abc_money_rss);
			             abc_news_money_list = parser.getNews();
					            }
	         }

        }