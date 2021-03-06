<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/6 0006
  Time: 上午 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
    <form action="loading">
    <div class="container">
    <div class="form row">
        <div class="form-horizontal col-md-offset-3" id="login_form">
            <h3 class="form-title">LOGIN</h3>
            <div class="col-md-9">
                <div class="form-group">
                    <i class="fa fa-user fa-lg"></i>
                    <input class="form-control required" type="text" placeholder="Username" id="username" name="username" autofocus="autofocus" maxlength="20"/>
                </div> <div class="form-group">
                <i class="fa fa-lock fa-lg"></i>
                <input class="form-control required" type="password" placeholder="Password" id="password" name="password" maxlength="8"/>
            </div>
                <div class="form-group">
                    <label class="checkbox">
                        <input type="checkbox" name="remember" value="1"/>记住我
                        <a class="pull-right" href="#">忘记密码?</a></label>
                </div>
                <div class="form-group col-md-offset-9">
                    <button type="submit" class="btn btn-success pull-left" name="submit">登录</button>
<%--                    <button type="button" class="btn btn-success pull-right" name="registered">注册</button>--%>
                    <a class="btn btn-success pull-right" href="newuser.jsp">注册</a>
                </div>
            </div>
        </div>
    </div>
</div>
</form>
</html>