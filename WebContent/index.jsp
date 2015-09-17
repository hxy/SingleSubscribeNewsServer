<%@page import="main.MainOperation"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Init News list</title>
</head>
<body>
        <jsp:useBean id="mainOperation" class="main.MainOperation" scope="page"/>
        <jsp:setProperty name="mainOperation" property="*"/>
        <%
            mainOperation.startGetNewsList();
        %>
</body>
</html>