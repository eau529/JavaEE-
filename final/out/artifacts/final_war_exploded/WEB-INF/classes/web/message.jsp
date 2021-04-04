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
            var replytext =  $('#replytext01').val()
            function displayMessage() {
                $.ajax({
                    type: "post",
                    url: "MessageController",
                    data: {
                        action: "getMessage",

                    },
                    dataType: "json",
                    async: false,
                    success: function (data) {
                        var html = "";
                        var message = eval(data);
                        for (var i = 0; i < message.length; i++) {
                            html += "<div class=\"row MY_divbordertop\" >"+
                                " <div class=\"MY_divborder\" >"+"<h4>"+
                            "<span class=\"glyphicon glyphicon-calendar\" aria-hidden=\"true\">"+message[i].userName+"&nbsp;"+message[i].date+"</span>"+"</h4>"
                            +"<p>"+
                            message[i].message
                            "</p>";
                            if (${user.id == 1001}){
                                  html +=  '<a >'+'<span id=reply01 name='+message[i].id+'>'+"回复"+'</span>'+'</a>'+
                                "</br>"
                            }
                            if (message[i].reply!=null) {
                                html += " <p>" +
                                    "博主回复：" + "</br>"
                                    + message[i].reply +
                                    "</p>"
                            }
                            html +=
                            "</div>"+
                            "</div>"
                        }
                        $("#MessageTable01").html(html);
                    }
                });
            }
                $("#returnMessage").click(function () {//修改
                    var MessageText = $("#MessageText").val();
                    var username=$("#username").val();
                    var action="updateMessage";
                    $.ajax({
                        type: "post",
                        url: "MessageController",
                        data: {
                            action:action,
                            MessageText:MessageText,
                            username:username
                        },
                        dataType: "json",
                        success: function (result) {
                            alert("test");
                            var html = "";
                            var life=result;

                        }
                    });
                displayMessage();
            });
            $("#MessageTable01").on('click',"#reply01",function(){// 回复
                var replytext = $("#replytext01").val();
                var val=$(this).attr("name");
                alert(val);
                var id=val;
                var action="updateReply";
                $.ajax({
                    type: "post",
                    url: "MessageController",
                    data: {
                        action:action,
                        replytext:replytext,
                        id:id
                    },
                    dataType: "json",
                    success: function (result) {
                        alert("test");
                        var html = "";
                        var life=result;
                        displayMessage();
                    }
                });
            });
            displayMessage();
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
            <h1>&nbsp;讨论区&nbsp;<small>&nbsp;&nbsp;&nbsp;来都来了，说点什么呗~~</small>
            </h1>
            <hr class="My_hr1"/>
            <c:if test="${user.id == null}">
            <div class="row MY_divbordertop">
                <button onclick="javascrtpt:window.location.href='login.jsp'" class="btn btn-success">留言前登录一下</button>
            </div>
            </c:if>
            <!-- 是要被隐藏的 -->
            <c:if test="${user.id != null}">
                <div class="row MY_divbordertop">
                    <!--<form  method="post" > -->
                    <form class="navbar-form navbar-left">
                        <div class="form-group">
                        <textarea name="MessageText" class="MY_textarea" id="MessageText" cols="100"
                                  rows="3"></textarea>
                        </div>
                    </form>
                    <div class="form-group" >
                        <button type="submit" class="btn btn-success" id="returnMessage" name="returnMessage" onclick="">留言</button>
                    </div>
                    <!--</form>-->
                </div>
            </c:if>
            <h2>￥最新发帖：</h2>
            <!-- 遍历的每一行 -->
            <input id="username" value="${user.name }" type="hidden" name="username"/>
            <div class="MY_divpadding" id="MessageTable01">
                <br/>
            </div>
            <form class="navbar-form navbar-left">
                <h4>回复内容：</h4>
                <div class="form-group">
                    <textarea name="replytext01" class="MY_textarea" id="replytext01" cols="100" rows="1" ></textarea>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>