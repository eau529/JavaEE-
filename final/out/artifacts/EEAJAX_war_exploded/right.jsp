<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/5/28
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>left</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script type="text/javascript" src="./js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/my_css.css"/>
    <style>
        .right{position:fixed; right:100px; top:100px;}
    </style>

    <script type="text/javascript">
        //获取用户详细信息url
        var url = 'ArticleController';
        var url2 = 'FriendController';
        $(function () {
            function getHotComment() {
                $.ajax({
                    type: "post",
                    url: url,
                    data: {
                        action: "getHotComment"
                    },
                    dataType: "json",
                    async: false,
                    success: function (result) {
                        var html = "";
                        var article = eval(result);
                        for(var i=0;i<article.length;i++) {
                            html +=
                                "<tr>" +
                                "<th>" + "<span class=\"glyphicon glyphicon-menu-right\" aria-hidden=\"true\"></span>" +
                                '<a href="/blog/ArticleController?action=getarticlebyid&articleid=' + article[i].id + '">' +
                                article[i].title + '</a>' + "</th>" +
                                "</tr>"
                        }
                        $("#table01").html(html);
                    }
                });
            }
            function getFriend() {
                $.ajax({
                    type: "post",
                    url: url2,
                    data: {
                        action: "selectFriend"
                    },
                    dataType: "json",
                    async: false,
                    success: function (result) {
                        var html = "";
                        var friend = eval(result);
                        for(var i=0;i<friend.length;i++) {
                            html +=
                                "<tr>"+
                                "<th>"+"<span class=\"glyphicon glyphicon-eye-open\" aria-hidden=\"true\"></span>"
                                +"<a>" + friend[i].name+"的博客"+"</a>"+"</th>"+
                                "</tr> "
                        }
                        $("#goodfriend").html(html);
                    }
                });
            }
            getHotComment();
            getFriend();
        });
    </script>

</head>
<body>
<div class="right">
    <table class="table MY_fontSize2"  >
        <tbody>
        <tr>
            <td><span class="glyphicon glyphicon-fire" aria-hidden="true"/>&nbsp;热门文章</td>
        </tr>

        <table class="table my_fontsize3"  id="table01">
            <tbody>

            </tbody>
        </table>

        </tbody>
    </table>
    <br/>
    <table class="table MY_fontSize2">
        <tbody>
        <tr>
            <td><span class="glyphicon glyphicon-send" aria-hidden="true"/>&nbsp;友情连接</td>
        </tr>
        <table class="table MY_fontSize3" id="goodfriend">
            <tbody>

            </tbody>
        </table>
        </tbody>
    </table>
</div>

</body>
</html>
