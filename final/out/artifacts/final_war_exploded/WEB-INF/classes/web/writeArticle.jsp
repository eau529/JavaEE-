<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    request.setAttribute("basePath",basePath);
    request.setAttribute("edit", request.getParameter("edit"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"/>
    <title>论坛主页</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/my_css.css"/>
    <script type="text/javascript">

    </script>
    <style>
        .down{position:fixed;  right: 500px ;top: 500px}
    </style>

</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<jsp:include page="right.jsp"></jsp:include>
<!-- 容器 -->
<c:if test="${edit ne 1}">
<div class="container">
    <div class="row">
        <div class="col-lg-8">
            <h1>&nbsp;资源列表&nbsp;<small>&nbsp;&nbsp;&nbsp;不遮遮掩掩，让我们认真谈性</small>
            </h1>
            <hr class="My_hr1"/>
            <div class="row MY_divbordertop">
                <a  href="<%=basePath%>articledetail.jsp?edit=1" class="btn btn-primary btn-lg">写一篇长长的文章</a>
            </div>
            <!-- 遍历的每一行 -->
            <c:forEach var="article" items="${articleList}">
                <div class="row MY_divbordertop">
                    <div class="col-lg-5" id="div01">
                        <a href="ArticleController/action=getarticlebyid&articleid=${article.id}" class="thumbnail">
                            <img height="120px" width="180px" src="${article.image }" alt="无法加载图片">
                        </a>
                    </div>
                    <!-- 一个段落 -->
                    <div class="col-lg-7">
                        <h2>${article.title }</h2>
                        <p class="MY_fontSize3">
                                ${article.summary }...<a
                                href="ArticleController?action=getarticlebyid&articleid=${article.id}">[详情]</a>
                        </p>
                        <hr class="My_hr2"/>
                        <div>
                            <span class="glyphicon glyphicon-time" aria-hidden="true"/>&nbsp;${article.date }
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"/>&nbsp;${article.num } 条评论
                            <a href="ArticleController?action=delArticle&articleid=${article.id }">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"/>&nbsp;删除
                            </a>
                            <a href="ArticleController?action=editarticle&articleid=${article.id }">
                                <span class="glyphicon glyphicon-file" aria-hidden="true"/>&nbsp;修改
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</c:if>
<c:if test="${edit eq 1}">
<div class="container">
    <div class="row">
        <div class="col-lg-8">
            <h1>&nbsp;最新帖子&nbsp;<small>&nbsp;&nbsp;&nbsp;大胆谈性，认真谈性。</small>
            </h1>
            <hr class="My_hr1"/>
            <!-- 遍历的每一行 -->
            <c:forEach var="article" items="${NewArticleList}">
                <div class="row MY_divbordertop">
                    <div class="col-lg-5" >
                        <div  class="thumbnail">
                        <a href="ArticleController?action=getarticlebyid&articleid=${article.id}">
                            <img height="120px" width="180px" src="${article.image }" alt="无法加载图片">
                        </a>
                        </div>
                    </div>
                    <!-- 一个段落 -->
                    <div class="col-lg-7">
                        <h2>${article.title }</h2>
                        <p class="MY_fontSize3">
                                ${article.summary }...<a
                                href="ArticleController?action=getarticlebyid&articleid=${article.id}">[详情]</a>
                        </p>
                        <hr class="My_hr2"/>
                        <div>
                            <span class="glyphicon glyphicon-time" aria-hidden="true"/>&nbsp;${article.date }
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"/>&nbsp;${article.num } 条评论
                            <c:if test="${user.id == 1001}">
                            <a href="ArticleController?action=delArticle&articleid=${article.id }">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"/>&nbsp;删除
                            </a>
                            <a href="ArticleController?action=editarticle&articleid=${article.id }">
                                <span class="glyphicon glyphicon-file" aria-hidden="true" id="change"/>&nbsp;修改</span>
                            </a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</c:if>
</body>
</html>
