<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/5/29
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    request.setAttribute("basePath",basePath);
%>

<html>
<head>
    <title>AJAX学习</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/my_css.css"/>

   <!-- <meta http-equiv="refresh" content="1; url=ArticleController?action=getNewArticle"> -->
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<script type="text/javascript" >
    $(function () {
        $("#blog").trigger("click");
    })
</script>


</body>
</html>
