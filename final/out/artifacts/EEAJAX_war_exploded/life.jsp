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
        var url = 'ArticleController';
        $(function () {
        $("#record").click(function () {//修改
            var lifetext=$("#lifetext").val();
            var action="AddLifeEssay";
            $.ajax({
                type: "post",
                url: url,
                data: {
                    action:action,
                    lifetext:lifetext
                },
                dataType: "json",
                success: function (result) {
                    var html = "";
                    var life=result;
                    html +=
                    "<div class=\"row MY_divbordertop\" >"+
                     " <div class=\"MY_divborder\" >"  +
                        "<h4>"+
                    "<span class=\"glyphicon glyphicon-calendar\" aria-hidden=\"true\">"+"&nbsp;"+life.date+ "</span>"+ "</h4>"+
                    "<p>"
                    +life.body+
                    "</p>"+
                    "<span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span>"+
                        '<a href="ArticleController?action=delEassy&articleid='+life.id+'">'+"删除"+'</a>'+
                        "</div>"+
                        "</div>";
                    $("#table").html(html);

                }
            });
        });
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
            <h1>&nbsp;生活随笔&nbsp;<small>&nbsp;&nbsp;&nbsp;用心记录生活中的点滴。</small>
            </h1>
            <hr class="My_hr1"/>
            <!-- 是要被隐藏的 -->
            <c:if test="${user.id !=null }">
            <div class="row MY_divbordertop">
                <h2>个人随笔</h2>
               <!--<form  method="post" > -->
                <form class="navbar-form navbar-left">
                    <div class="form-group">
                        <textarea name="lifetext" class="MY_textarea" id="lifetext" cols="100"
                                  rows="3"></textarea>
                    </div>
                </form>
                    <div class="form-group" >
                        <button type="submit" class="btn btn-success" id="record" name="record" onclick="">记录一下</button>
                    </div>

                <!--</form>-->
            </div>
            </c:if>
            <div class="row MY_divbordertop">
                <div class="MY_divpadding" id="table">
                    <br/>
                </div>
            </div>
            <!-- 遍历的每一行 -->
            <c:forEach var="life" items="${lifeBeans}">
            <div class="row MY_divbordertop" >
                <div class="MY_divborder" >
                    <h4>
                        <span class="glyphicon glyphicon-calendar" aria-hidden="true">&nbsp;${life.date }</span></h4>
                   <p>
                        ${life.body }
                   </p>

                    <c:if test="${user.id == 1001}">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                        <a href="ArticleController?action=delEassy&articleid=${life.id }">删除</a>
                    </c:if>
                    </a>
                </div>
            </div>
            </c:forEach>
    </div>
</div>
</div>
</body>
</html>