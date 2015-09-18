<%@page import="main.MainOperation"%>
<%@page import="objects.NewsBrief"%>
<%@taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="GBK"%>
<%@page import="java.util.*"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="atg.taglib.json.util.JSONObject"%>
<jsp:useBean id="mainOperation" class="main.MainOperation" scope="page" />
<jsp:setProperty name="mainOperation" property="*" />

<%
	request.setCharacterEncoding("utf8");
	response.setCharacterEncoding("utf8");
	
	StringBuffer requestJson = new StringBuffer();
	String line = null;
	int newsType = 0;
	try {
		BufferedReader reader = request.getReader();
		while ((line = reader.readLine()) != null) {
			requestJson.append(line);
		}
		System.out.println("string:"+requestJson.toString());
		JSONObject jsonObject = new JSONObject(requestJson.toString());
		newsType = Integer.parseInt(jsonObject.getString("newsType"));
	}catch(Exception e){
		e.printStackTrace();
	}
	System.out.println("newsType:" + newsType);
	String newsList = mainOperation.getNewsList(newsType);
	System.out.println("newsList:" + newsList);
%>
<%=newsList%>
<!-- 
<json:array name="newsList" var="news" items="${newsList}">
	<json:object>
		<json:property name="title" value="${news.getTitle()}" />
		<json:property name="url" value="${news.getUrl()}" />
		<json:property name="source" value="${news.getSource()}" />
		<json:property name="thumbnail" value="${news.getThumbnail()}" />
		<json:property name="description" value="${news.getDescription()}" />
	</json:object>
-->
</json:array>