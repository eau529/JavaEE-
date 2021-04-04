<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta charset="utf-8"/>
    <title></title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/my_css.css"/>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<jsp:include page="right.jsp"></jsp:include>
<!-- 容器 -->
<div class="container">
    <div class="row">
        <div class="col-lg-8">
            <h1>&nbsp;注册的小伙伴&nbsp;<small>&nbsp;&nbsp;&nbsp;快来认识一下新朋友</small>
            </h1>
            <hr class="My_hr1"/>

            <!-- 遍历的每一行 -->
            <div class="row MY_divbordertop">
                <table class="table table-hover  MY_table MY_fontSize4">
                    <thead>
                    <tr>
                        <th class="MY_table">昵称
                        </td>
                        <th class="MY_table">电子邮箱</th>
                        <th class="MY_table">他的帖子</th>
                        <th class="MY_table">操作</th>
                    </tr>

                    </thead>
                    <tbody>
                    <!-- 点击标记好友，前端刷新，数据库数据更新，stranger字段修改
                        数据库字段：stranger： 7：被标记成好友。8：非标记好友。5：被屏蔽好友
                        4、未屏蔽的好友 2：被删除的好友
                        点击删除时，当前好友不可展示-->

                    <c:forEach var="friend" items="${friendList }">
                        <c:if test="${friend.stranger !=2}">
                        <tr>
                            <td>

                                <c:if test="${friend.stranger == 4 || friend.stranger ==7 }">
                                    <span class="label label-success">友</span>&nbsp;
                                </c:if>
                                    ${friend.name }
                            </td>
                            <td>${friend.emailAddress}</td>
                            <td>${friend.blogAddress}</td>
                            <td>
                                <c:if test="${friend.stranger == 4 || friend.stranger ==7 }"><!-- 4、未屏蔽的好友 7：被标记成好友  -->
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                    <a href="FriendController?action=markfriend&userid=${friend.id}&flag=${friend.stranger}">取消标记</a>
                                </c:if>
                                <c:if test="${friend.stranger == 5|| friend.stranger ==8 }"><!-- 5：被屏蔽好友 8：非标记好友 -->
                                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                    <a href="FriendController?action=markfriend&userid=${friend.id}&flag=${friend.stranger}">标记好友</a>
                                </c:if>
                                <c:if test="${friend.stranger == 4|| friend.stranger ==5}"><!-- 4、未屏蔽的好友 5：被屏蔽好友-->
                                    <span class="glyphicon glyphicon-volume-down" aria-hidden="true"></span>
                                    <a href="FriendController?action=shieldfriend&userid=${friend.id}&flag=${friend.stranger}">恢复</a>
                                </c:if>
                                <c:if test="${friend.stranger == 7|| friend.stranger ==8 }"><!-- 7：被标记成好友 8：非标记好友 -->
                                    <span class="glyphicon glyphicon-volume-off" aria-hidden="true"></span>
                                    <a href="FriendController?action=shieldfriend&userid=${friend.id}&flag=${friend.stranger}">屏蔽</a>
                                </c:if>
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                <a href="FriendController?action=delfriend&userid=${friend.id}">删除</a>
                            </td>
                        </tr>
                        </c:if>
                    </c:forEach>

                    </tbody>

                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>