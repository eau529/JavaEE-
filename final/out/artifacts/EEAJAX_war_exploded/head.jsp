<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/5/29
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/my_css.css"/>
    <script type="text/javascript">
        $(function () {
            $("#select").click(function () {
                window.location.href="index.jsp";
            });
            $("#exit").click(function () {
                window.location.href="New.jsp";
            });

            $("#Selectbutton").click(function () {
                var title = $("#input01").val();
                $.ajax({
                    type: "post",
                    url: "ArticleController",
                    data: {
                        "action": "getarticlebytitle",
                        "title": title,
                        "time": new Date()
                    },
                    dataType: "json",
                    success: function (result) {
                        var list = eval(result);
                        var str = "";
                        for (var i = 0; i < list.length; i++) {

                            str += "<div class='row MY_divbordertop'>" +
                                "<div class='col-lg-5'>" +
                                "<a href='ArticleController?action=getarticlebyid&articleid=" + list[i].id + "' class='thumbnail'>" +
                                "<img height='120px' width='180px' src='" + list[i].image + "' alt='无法加载图片'>" +
                                " </a>" +
                                "</div>" +
                                "<div class='col-lg-7'>" +
                                "<h2>" + list[i].title + "</h2>" +
                                "<p class='MY_fontSize3'>" +
                                list[i].summary + "...<a href='ArticleController?action=getarticlebyid&articleid=" + list[i].id + " '>[详情]</a>" +
                                "</p>" +
                                "<hr class='My_hr2' />" +
                                "<div>" +
                                "<span class='glyphicon glyphicon-time' aria-hidden='true'>&nbsp;" + list[i].date +
                                "&nbsp;" +
                                "<span class='glyphicon glyphicon-pencil' aria-hidden='true'>&nbsp;" + list[i].num + "条评论" +
                                "</div>" +
                                "</div>" +
                                "</div>";
                        }
                        if (str == "") {
                            alert("没有相应的搜索内容，请重新输入搜索内容！");
                            window.location.reload();
                        }
                        $("#newarticle").empty();
                        $("#newarticle").append(str);
                    }
                });
            });
        });
    </script>
</head>
<body>
<nav class="navbar navbar-default MY_fontSize1 navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand active" href="ArticleController?action=getNewArticle&edit=1"><span id="blog">老张头的博客</span></a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="ArticleController?action=getLifeEssay"><span class="glyphicon glyphicon-tree-deciduous"
                                     aria-hidden="true"></span>&nbsp;生活随笔 </a></li>
                <li><a href="message.jsp"><span class="glyphicon glyphicon-bullhorn"
                                     aria-hidden="true">&nbsp;留言板</span></a></li>
                <li><a href="aboutMe.jsp"> <span class="glyphicon glyphicon-hand-right"
                                                 aria-hidden="true">&nbsp;关于我</span></a></li>
                <c:if test="${user.id == 1001}">
                    <li><a href="ArticleController?action=getAllArticle"><span class="glyphicon glyphicon-pencil"
                                                                               aria-hidden="true">&nbsp;写文章</span></a></li>
                    <li><a href="FriendController?action=getFriend"> <span
                            class="glyphicon glyphicon-star-empty" aria-hidden="true">&nbsp;联系人</span></a></li>
                </c:if>
            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input id="input01" type="text" class="form-control" placeholder="搜索文章" name="title">
                    <button id="select" type="button" class="btn btn-info">搜索</button>
                </div>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${empty user.name}">
                    <li><a href="login.jsp"> &nbsp;登录</a></li>
                    <li><a href="register.jsp">&nbsp;注册</a></li>
                    <button type="button" class="btn btn-success" id="exit" name="exit" >退出</button>
                </c:if>
                <c:if test="${!empty user.name}">
                    <li><a href=""> &nbsp;欢迎${user.name}登陆</a></li>
                    <li><a href="register.jsp">&nbsp;注册</a></li>
                    <button type="button" class="btn btn-success" id="exit" name="exit" onclick="se()">退出</button>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<div class="row MY_divbordertop" id="newarticle"></div>

</body>
</html>
