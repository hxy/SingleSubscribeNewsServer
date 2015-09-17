package main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;
import objects.NewsBrief;
import tools.RssParser;

public class MainOperation {

	private final int TITTLE_TATTLE = 100;
	private RssParser rssParser;
	private String[] TITTLE_TATTLE_URL = {"http://www.nbweekly.com/rss/smw/","http://9.douban.com/rss/life"};
	private static String tittle_tattle_jsonString;
	
	public MainOperation(){
		rssParser = new RssParser();
		//tittle_tattle = new ArrayList<NewsBrief>();
	}
	
	public String getNewsList(int newsType){
		switch(newsType){
		case TITTLE_TATTLE: return tittle_tattle_jsonString;
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
		try {
			getNewsList(TITTLE_TATTLE_URL,cacheList);
			tittle_tattle_jsonString = newsListToJson(cacheList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		//System.out.println("tittle_tattle.size:"+tittle_tattle.size());
	}

	private void getNewsList(String[] urls,ArrayList<NewsBrief> newsList) throws Exception{
		for(int n=0;n<urls.length;n++){
			    String rssString = getRssString(TITTLE_TATTLE_URL[n]);
			//System.out.println("rssString------------:"+rssString);
				rssParser.getNewsList(rssString, newsList);
		}
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


	
	private String newsListToJson(ArrayList<NewsBrief> newsList) throws JSONException{
		JSONArray jsonArray = new JSONArray();
		for(NewsBrief news : newsList){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("title", news.getTitle());
			jsonObject.put("url", news.getUrl());
			jsonObject.put("source", news.getSource());
			jsonObject.put("thumbnail", news.getThumbnail());
			jsonObject.put("description", news.getDescription());
			jsonArray.add(jsonObject);
		}
		return jsonArray.toString();
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
