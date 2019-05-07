<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/6 0006
  Time: 上午 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="utf-8">
<title>注册新用户</title>
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function checkpwd() {
            if(newPassword.value != Password.value) {
                alert("两次输入密码不一致！")
                newPassword.value = "";
                Password.value = "";
            }
        }
    </script>
</head>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-4 column">
        </div>
        <div class="col-md-4 column">
        </div>
        <div class="col-md-4 column">
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-4 column">
        </div>
        <div class="col-md-4 column">
        </div>
        <div class="col-md-4 column">
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-4 column">
        </div>
        <div class="col-md-4 column">
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label for="Email" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" id="Email" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="newPassword" class="col-sm-2 control-label">newPassword</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="newPassword" />
                   </div>
                </div>
                <div class="form-group">
                        <label for="Password" class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="Password" onblur="checkpwd()"/>
                        </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Registered</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-4 column">
        </div>
    </div>
</div>
</html>
