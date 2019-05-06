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
  <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
  <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="xgxt_login.css"/>
</head>
<div class="container">
  <from action="" method="">
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
          <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
          <div class="col-sm-10">
            <input type="email" class="form-control" id="inputEmail3" />
          </div>
        </div>
        <div class="form-group">
          <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
          <div class="col-sm-10">
            <input type="password" class="form-control" id="inputPassword3" />
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <div class="checkbox">
              <label><input type="checkbox" />Remember me</label>
              <label><a href="newuser.jsp">get a new account</a></label>
            </div>
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Sign in</button>
          </div>
        </div>
      </form>
    </div>
    <div class="col-md-4 column">
    </div>
  </div>
  </from>
</div>
</html>