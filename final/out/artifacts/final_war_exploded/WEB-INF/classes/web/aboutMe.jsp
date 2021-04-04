<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/5/29
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>个人信息</title>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/my_css.css"/>
    <script type="text/javascript">
        //获取用户详细信息url
        $(function () {
            $("#div02").hide();

            function getUserInfo() {
                $.ajax({
                    type: "post",
                    url: "userInfo",
                    data: {
                        userId:${user.id},
                        action: "getUserInfo"
                    },
                    dataType: "json",
                    success: function (result) {
                        var html = "";
                        var user = result;
                        html += "<label class=\"MY_fontSize4\"><span class=\"glyphicon glyphicon-info-sign\" aria-hidden=\"true\"/>&nbsp;论坛资料：&nbsp;</label>\n" +
                            "<span class=\"MY_fontSize3\">" + user.info + "</span>\n" +
                            "<br/>\n" +
                            "<br/>\n" +
                            "<label class=\"MY_fontSize4\"><span class=\"glyphicon glyphicon-info-sign\" aria-hidden=\"true\"/>&nbsp;论坛简介：&nbsp;</label>\n" +
                            "<span class=\"MY_fontSize3\">" + user.hobby + "</span>\n" +
                            "<br/>\n" +
                            "<br/>\n" +
                            "<label class=\"MY_fontSize4\"><span class=\"glyphicon glyphicon-info-sign\" aria-hidden=\"true\"/>&nbsp;管理员QQ：&nbsp;</label>\n" +
                            "<span class=\"MY_fontSize3\">" + user.qq + "</span>\n" +
                            "<br/>\n" +
                            "<br/>\n" +
                            "<label class=\"MY_fontSize4\"><span class=\"glyphicon glyphicon-info-sign\" aria-hidden=\"true\"/>&nbsp;管理员邮箱：&nbsp;</label>\n" +
                            "<span class=\"MY_fontSize3\">" + user.email + "</span>\n" +
                            "<br/>\n" +
                            "<br/>\n" +
                            "<label class=\"MY_fontSize4\"><span class=\"glyphicon glyphicon-info-sign\" aria-hidden=\"true\"/>&nbsp;免责声明：&nbsp;</label>\n" +
                            "<br/><br/>\n" +
                            "<p class=\"MY_fontSize3 My_ppadding\">\n" +
                            " " + user.description + "\n" +
                            "</p>";
                        $("#div01").html(html);
                    }
                });
            }
            if (${user.id == 1001}) {
                getUserInfo();
            }

            $("#button02").click(function () {
                $("#div01").hide();
                $("#button02").hide();
                $("#div02").show();
            });
        });
    </script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>

<div id="userInfo">
    <div class="container">
        <div class="row">
            <div class="col-lg-8">
                <h1>&nbsp;关于论坛&nbsp;<small>&nbsp;&nbsp;&nbsp;有事请联系我们哦~</small>
                </h1>
                <hr class="My_hr1"/>
                <div class="row MY_divbordertop">
                    <div class="MY_divpadding" id="div01">
                        <br/>
                    </div>
                    <c:if test="${user.id == 1001}">
                        <button id="button02" class="btn btn-success">修改</button>
                    </c:if>
                    <c:if test="${user.id == 1001}">
                        <div class="MY_divpadding" id="div02">
                            <form method="post" action="userInfo?action=updateUserInfo">
                                <div class="form-group">
                                    <label class="MY_fontSize4 "><span class="glyphicon glyphicon-info-sign"
                                                                       aria-hidden="true">&nbsp;论坛资料&nbsp;</span></label>
                                    <input type="text" class="form-control MY_input" name="info" value="${me.info }">
                                    <input type="hidden" name="userID" value="${user.id }">
                                </div>

                                <div class="form-group">
                                    <label class="MY_fontSize4"><span class="glyphicon glyphicon-info-sign"
                                                                      aria-hidden="true">&nbsp;管理员QQ&nbsp;</span></label>
                                    <input type="text" class="form-control MY_input" name="qq" value="${me.qq }">
                                </div>
                                <div class="form-group">
                                    <label class="MY_fontSize4"><span class="glyphicon glyphicon-info-sign"
                                                                      aria-hidden="true">&nbsp;管理员邮箱&nbsp;</span></label>
                                    <input type="text" class="form-control MY_input" name="email" value="${me.email }">
                                </div>
                                <div class="form-group">
                                    <label class="MY_fontSize4"><span class="glyphicon glyphicon-info-sign"
                                                                      aria-hidden="true">&nbsp;论坛简介&nbsp;</span></label>
                                    <textarea class="MY_textarea" id="exampleInputFile" cols="100" rows="3"
                                              name="description">${me.description }</textarea>
                                </div>
                                <button type="submit" class="btn btn-success">保存</button>
                            </form>
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="col-lg-4">
                <jsp:include page="right.jsp"></jsp:include>
            </div>
        </div>
    </div>
</div>
</body>
</html>
