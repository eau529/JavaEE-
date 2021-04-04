<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"/>
    <title></title>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/my_css.css"/>
    <script type="text/javascript">
        $(function () {
            function display() {
                var articleid = $("#articleid").val();
                $.ajax({
                    type: "post",
                    url: "CommentController",
                    data: {
                        articleid:articleid,
                        action: "getComment"
                    },
                    dataType: "json",
                    async: false,
                    success: function (result) {
                        var html = "";
                        var article = eval(result);
                        for(var i=0;i<article.length;i++) {
                            html +=
                                "<tr>" +"<th>"+"用户"+article[i].userName  +"评论了："+"</th>"
                                + "<th>" + "<span ></span>" +
                                article[i].body + "</th>" +"</tr>"+
                                "<tr>" +"<th>"+"日期："+article[i].date  +""+"</th>"+"</tr>"
                        }
                        $("#commentTable").html(html);
                    }
                });
            }
            $("#commenting").click(function () {//修改
                var articleid = $("#articleid").val();
                var comment1=$("#comment1").val();
                var username=$("#username").val();
                var action="NewComment";
                $.ajax({
                    type: "post",
                    url: "CommentController",
                    data: {
                        action:action,
                        articleid:articleid,
                        comment1:comment1,
                        username:username
                    },
                    dataType: "json",
                    success: function (result) {
                        var html = "";
                        var life=result;
                        display();
                    }
                });

            });
            display();
        });

    </script>

</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<jsp:include page="right.jsp"></jsp:include>
<!-- 容器 -->
<div class="container">
    <div class="row">
        <div class="col-lg-8">
            <h1>&nbsp;阅读&nbsp;<small>&nbsp;&nbsp;&nbsp;阅读使人开明</small>
            </h1>
            <hr class="My_hr1"/>
            <!-- 遍历的每一行 -->
            <c:forEach var="article" items="${articleList}">
            <div class="row MY_divbordertop">
                <div class="MY_divpadding2">
                    <h2>${article.title  }</h2>
                    <small>
							<span class="glyphicon glyphicon-user" aria-hidden="true">&nbsp;${article.author }&nbsp;
							<span class="glyphicon glyphicon-time" aria-hidden="true">&nbsp;${article.date }
                    </small>
                    <br/>
                    <br/>
                    <p class="MY_fontSize1">
                        ${article.body}
                    </p>
                </div>
                </c:forEach>

            </div>
            <br/><br/>

            <h4><span class="glyphicon glyphicon-list-alt" aria-hidden="true">&nbsp;最新评论</span></h4>
            <div class="MY_divpadding" id="commentTable">
                <br/>
            </div>
            <c:forEach var="article" items="${articleList}">
            <form>
                <div class="form-group">
                    <textarea id="comment1" cols="100" rows="2" name="comment1"></textarea>
                    <input id="articleid" value="${article.id }" type="hidden" name="articleid"/>
                    <input id="username" value="${user.name }" type="hidden" name="username"/>
                </div>
                <button id="commenting" type="button" class="btn btn-info">评论</button>
            </form>
            </c:forEach>
                <!-- 遍历的每一行 -->
                <%--<c:forEach var="comment" items="${commentList }">--%>
                    <%--<div class="row MY_divbordertop">--%>
                        <%--<div class="col-lg-1">--%>
                            <%--<img src="img/apple.png" alt="苹果" height="60px" width="60px" class="img-circle">--%>
                        <%--</div>--%>
                        <%--<div class="col-lg-11">--%>
                            <%--<span class="MY_span">${comment.userName }</span><span class="MY_span"><span--%>
                                <%--class="glyphicon glyphicon-time" aria-hidden="true"></span>${comment.date }</span>--%>
                            <%--<br/>--%>
                            <%--<span> ${comment.body }</span>--%>
                            <%--<br/>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</c:forEach>--%>

        </div>
    </div>
</div>
</body>
</html>
