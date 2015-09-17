package main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.xpath.XPathExpressionException;

import objects.NewsBrief;
import sun.util.logging.resources.logging;
import tools.RssParser;

public class MainOperation {

	private final int TITTLE_TATTLE = 100;
	private RssParser rssParser;
	private String[] TITTLE_TATTLE_URL = {"http://www.nbweekly.com/rss/smw/","http://9.douban.com/rss/life"};
	private ArrayList<NewsBrief> tittle_tattle;
	
	public MainOperation(){
		rssParser = new RssParser();
		tittle_tattle = new ArrayList<NewsBrief>();
	}
	
	public ArrayList<NewsBrief> getNewsList(int newsType){
		switch(newsType){
		case TITTLE_TATTLE: return tittle_tattle;
		}

		return null;
	}

	public void startGetNewsList(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		        public void run() {
		        	System.out.println("timer");
					getTittle_TattleNewsList();
					//other get news method
		        }
		}, 0 , 1800000);
	}

	private void getTittle_TattleNewsList(){
		//System.out.println("getTittle_TattleNewsList");
		ArrayList<NewsBrief> cacheList = new ArrayList<NewsBrief>();
		if(getNewsList(TITTLE_TATTLE_URL,cacheList)){
			//tittle_tattle = 
		}
		System.out.println("tittle_tattle.size:"+tittle_tattle.size());
	}

	private boolean getNewsList(String[] urls,ArrayList<NewsBrief> newsList){
		for(int n=0;n<urls.length;n++){
			try {
			    String rssString = getRssString(TITTLE_TATTLE_URL[n]);
			//System.out.println("rssString------------:"+rssString);
				rssParser.getNewsList(rssString, newsList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	private String getRssString(String urlPath) throws Exception{
		StringBuffer resultString = new StringBuffer();
			URL url = new URL(urlPath);
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(5000);
			connection.connect();
			InputStream is = connection.getInputStream();
			InputStreamReader isReader = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isReader);
			String line;
			while((line = reader.readLine())!=null){
				resultString.append(line);
			}
		return resultString.toString();
	}


//	private class GetNewsThread extends Thread{
//
//		@Override
//		public void run() {
//			getTittle_TattleNewsList();
//			//other get news method
//		}
//
//	}
}
