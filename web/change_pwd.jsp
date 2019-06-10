<%@ page import="javabean.UserBean" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/9 0009
  Time: 下午 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script>
        function checkpwd() {
            if(newpassword.value != newpassword2.value) {
                alert("两次输入密码不一致！")
                newpassword.value = "";
                newpassword2.value = "";
            }
        }
        function sendOldPwd(UserName){
            var oldpassword = OldPassword.value;
            var username = UserName;
            $.ajax({
                type:"post",                //切记要用post发送，没有execute()，在struts.xml中指定方法
                url:"password.action",     //url要加""，注意了，注意了
                data:{"todo":"check_oldpassword","username":username,"oldpassword":oldpassword},          //action通过request.Parameter("username") 取值
                dataType:"text",                                //类型：xml，html，script，json，jsonp，text
                contenType:"application/json;charset=utf-8",
                success:function (result) {
                    if(result=="success"){
                    }
                    else {
                        alert("原有密码错误!")
                        OldPassword.value="";
                    }
                },
            });
        }
    </script>
</head>
<body>
<%
    UserBean userbean = (UserBean)session.getAttribute("userbean");
%>
<div class="container-fiuled">
<div class="row clearfix">
    <div class="col-md-12 column">
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">首页</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active">
                        <a href="#">Link</a>
                    </li>
                    <li>
                        <a href="#">Link</a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">排行榜<strong class="caret"></strong></a>
                        <ul class="dropdown-menu">
                            <li><a href="/top/allvisit/" title="点击总榜">点击总榜</a></li>
                            <li><a href="/top/allvote/" title="推荐总榜">推荐总榜</a></li>
                            <li><a href="/top/monthvisit/" title="点击月榜">点击月榜</a></li>
                            <li><a href="/top/monthvote/" title="推荐月榜">推荐月榜</a></li>
                            <li><a href="/top/weekvisit/" title="周排行榜">周排行榜</a></li>
                            <li><a href="/top/weekvote/" title="周推荐榜">周推荐榜</a></li>
                            <li><a href="/top/goodnum/" title="总收藏榜">总收藏榜</a></li>
                            <li><a href="/top/toptime/" title="本站推荐">本站推荐</a></li>
                            <li><a href="/top/size/" title="字数排行">字数排行</a></li>
                            <li><a href="/top/lastupdate/" title="最近更新">最近更新</a></li>
                            <li><a href="/top/postdate/" title="最新入库">最新入库</a></li>
                        </ul>
                    </li>
                </ul>
                <form action="search.action" class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input name="search_name" type="text" class="form-control" placeholder="请输入关键字" />
                    </div> <button type="submit" class="btn btn-default">搜索</button>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <div class="loginCenter"><img src="img/kula.png"></div>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%=userbean.getUsername()%><strong class="caret"></strong></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#">个人资料</a>
                            </li>
                            <li>
                                <a href="getfavorite.action?pages=1&username=<%=userbean.getUsername()%>">我的收藏</a>
                            </li>
                            <li>
                                <a href="change_pwd.jsp">密码修改</a>
                            </li>
                            <li class="divider">
                            </li>
                            <li>
                                <a href="index.jsp">退出</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>

        </nav>
    </div>
</div>
<form action="password?todo=change_password" method="post">
<%--    <div class="container">--%>
        <div class="form row">
            <div class="form-horizontal col-md-offset-3" id="login_form">
                <h3 class="form-title">密码修改</h3>
                <div class="col-md-9">
                    <div class="form-group">
                        <i class="fa fa-user fa-lg"></i>
                        <input class="form-control required" type="text" placeholder="Username" id="username" name="username" autofocus="autofocus" maxlength="20"/>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control required" type="password" placeholder="OldPassword" id="OldPassword" onblur="sendOldPwd('<%=userbean.getUsername()%>')" name="OldPassword" maxlength="8"/>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control required" type="password" placeholder="NewPassword" id="newpassword" name="newpassword" maxlength="8"/>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control required" type="password" placeholder="NewPassword" id="newpassword2" onblur="checkpwd()" name="newpassword2" maxlength="8"/>
                    </div>
                    <div class="form-group col-md-offset-9">
                        <button type="submit" class="btn btn-success pull-right" name="submit">更改密码</button>
                    </div>
                </div>
            </div>
        </div>
<%--    </div>--%>
</form>
</div>
</body>
</html>

