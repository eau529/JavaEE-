<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setAttribute("edit", request.getParameter("edit"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"/>
    <title></title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/my_css.css"/>
    <script type="text/javascript">
    </script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<jsp:include page="right.jsp"></jsp:include>
<!-- 容器 -->
<div class="container">
    <div class="row">
        <div class="col-lg-8">
            <c:if test="${edit eq 1}">
                <h1>&nbsp;写文章&nbsp;</h1>
            </c:if>
            <c:if test="${edit eq 0}">
                <h1>&nbsp;修改文章&nbsp;</h1>
            </c:if>
            <hr class="My_hr1"/>
            <c:if test="${edit eq 1}">
                <div class="row MY_divbordertop">
                    <form action="ArticleController?action=AddNewArticle" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label class="MY_fontSize4 ">&nbsp;标题&nbsp;</label>
                            <input type="text" class="form-control MY_input" name="title">
                            <input type="hidden" class="form-control MY_input" name="id">
                        </div>
                        <div class="form-group">
                            <label class="MY_fontSize4 ">&nbsp;封面配图&nbsp;</label>
                            <input type="file" class="MY_input" name="image">
                        </div>
                        <label class="MY_fontSize4">&nbsp;主体内容&nbsp;</label>
                        <div class="form-group">
                            <textarea class="MY_textarea" id="exampleInputFile1" cols="100" rows="25"
                                      name="body"></textarea>
                        </div>
                        <button type="submit" class="btn btn-success">提交</button>
                    </form>
                    <br/><br/><br/><br/>
                </div>
            </c:if>
            <c:if test="${edit eq 0}">
                <div class="row MY_divbordertop">
                    <form action="EditArticle" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label class="MY_fontSize4 ">&nbsp;标题&nbsp;</label>
                            <input type="text" class="form-control MY_input" value="${article.title}" name="title">
                            <input type="hidden" class="form-control MY_input" value="${article.id}" name="id">
                            <input type="hidden" class="form-control MY_input" value="${article.image}" name="image">
                        </div>

                        <div class="form-group">
                            <label class="MY_fontSize4 ">&nbsp;封面配图&nbsp;</label>
                            <input type="file" class="MY_input" name="image">
                            <br/>
                            <img height="120px" width="180px" src="${article.image}" alt="无法加载图片">
                        </div>
                        <label class="MY_fontSize4">&nbsp;主体内容&nbsp;</label>
                        <div class="form-group">
                            <textarea class="MY_textarea" id="exampleInputFile2" cols="100" rows="25"
                                      name="body">${article.body}</textarea>
                        </div>
                        <button type="submit" class="btn btn-success">保存</button>
                    </form>
                    <br/><br/><br/><br/>
                </div>
            </c:if>
        </div>
    </div>

</div>
</body>
</html>
