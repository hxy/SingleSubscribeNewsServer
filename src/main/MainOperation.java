package main;

import java.util.ArrayList;

import objects.NewsBrief;
import tools.RssParser;

public class MainOperation {

	private final int TITTLE_TATTLE = 100;
	private RssParser rssParser;
	private String url = "http://www.nbweekly.com/rss/smw/";
	private ArrayList<NewsBrief> tittle_tattle;
	
	public MainOperation(){
		rssParser = new RssParser();
		tittle_tattle = new ArrayList<NewsBrief>();
	}
	
	public ArrayList<NewsBrief> getNewsList(int newsType){
		switch(newsType){
		case TITTLE_TATTLE: return tittle_tattle;
		}
	}
	
	private void getNewsList(){
		
	}
}
