<%@page import="main.MainOperation"%>
<%@page import="objects.NewsBrief"%>
<%@taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="GBK"%>
<%@page import="java.util.*"%>

<jsp:useBean id="mainOperation" class="main.MainOperation" scope="page" />
<jsp:setProperty name="mainOperation" property="*" />

<%
	request.setCharacterEncoding("utf8");
	response.setCharacterEncoding("utf8");
	int newsType = Integer.parseInt(request.getParameter("newsType"));
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