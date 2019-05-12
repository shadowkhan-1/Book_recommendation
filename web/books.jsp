<%--
  Created by IntelliJ IDEA.
  User: linwei
  Date: 5/11/19
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="table.BX_Books,java.util.List" %>
<%@ page import="Factory.ServiceFactory" %>
<%@ page import="javabean.PageBean" %>
<%@ page import="javabean.UserBean" %>
<html>
<head>
    <title>书籍</title>
    <style type="text/css">
           /*防止内容被挡*/
        body {  padding-top: 70px; padding-bottom: 70px;}
        /*强制不换行 */
        .book_info{
            display: inline-block;
            white-space: nowrap;
            width: 100%;             /*文本宽度*/
            overflow: hidden;        /*超出部分隐藏*/
            text-overflow:ellipsis;
        }
           .loginCenter {
               line-height: 27px;
               position: relative;
               width: 50px;
               height: 50px;
               padding: 13px 25px 5px 25px;
           }
        .div_left{
            width: 80%;
            border: 0px;
        }
        .div_right{
            width: auto;
        }
           .thumbnail{
               cursor: pointer;
               transition: all 0.6s;
           }
           .thumbnail:hover{
               transform: scale(1.4);
           }
    </style>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%--&lt;%&ndash;    <script>&ndash;%&gt;--%>
<%--&lt;%&ndash;    function getPage() {&ndash;%&gt;--%>
<%--        // var page = document.getElementById("pages").value;//js用var创建变量--%>
<%--        // document.forms.selectbook.action="getbook?"+page.value;//直接获取表单的action;--%>
<%--        // document.getElementById('selectbook').action="getbook?pages="+page;另一种写法--%>
<%--        // document.getElementById('selectbook').submit();另一种写法--%>
<%--        // alert(document.getElementById("selectbook").action);--%>
<%--        //document.forms.selectbook.submit();--%>
<%--    }--%>
<%--&lt;%&ndash; </script>  &ndash;%&gt;--%>
</head>
<body>
<%
    UserBean userbean = (UserBean)session.getAttribute("userbean");
%>
<div class="container">
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
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">Action</a>
                                </li>
                                <li>
                                    <a href="#">Another action</a>
                                </li>
                                <li>
                                    <a href="#">Something else here</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">Separated link</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">One more separated link</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" />
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
                                    <a href="#">收藏夹</a>
                                </li>
                                <li>
                                    <a href="#">密码修改</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">Separated link</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>

            </nav>
        </div>
    </div>
    <%
        PageBean pagebean = (PageBean)request.getAttribute("pagebean");
        Integer pages = pagebean.getPages();
        Integer totalpages = pagebean.getTotalpages();
    %>
    <div class="div_left">
    <div class="row">
    <%
    List<BX_Books> list=pagebean.getList();
    for(BX_Books books:list){
        String author_href = "https://www.baidu.com/s?wd="+books.getBook_Author().replace(" ","+")+"&ie=UTF-8";
        String title_href = "https://www.baidu.com/s?wd="+books.getBook_Title().replace(" ","+")+"&ie=UTF-8";
        String big_image_href = books.getImage_URL_L();
    %>
<%--//    大屏幕放3张略缩图，pc端放4张，平板和手机放6张 --%>
    <div class="col-lg-3 col-md-4 col-sm-6 col-xs-6">
        <div class="thumbnail">
            <a href=<%=big_image_href%> target="_blank"><img src=<%=books.getImage_URL_M()%> alt="无图"  class="img-responsive" style="width: 100px;height:100px"></a>
            <div class="caption">
                <h4 class="book_info"><a href=<%=title_href%> target="_blank"><%=books.getBook_Title()%></a></h4>
                <p style="color:#28ff46;">
                    <span class="book_info">作者：<a href=<%=author_href%> target="_blank"><%=books.getBook_Author()%><br></a></span>
                    <span class="book_info">出版社：<%=books.getPublisher()%></span><br>
                    出版日期:<%=books.getYear_Of_Publication()%>
                </p>
            </div>
        </div>
    </div>
    <%} %>
    </div>
    </div>
    <div class="div_right"></div>
<form action="getbook.action">
    <nav style="text-align: center">
    <ul class="pagination">
        <li><a href="#">第<%=pagebean.getPages()%>页 共<%=totalpages%>页 </a></li>
        <li><a href="getbook?pages=1">首页</a></li>
        <li><a href="getbook?pages=<%=(pages<=1)?pages=1:(pages-1) %>"> 上一页</a></li>
        <li><a href="getbook?pages=<%=(pages>=totalpages)?totalpages:(pages+1)%>"> 下一页</a></li>
        <li><a href="getbook?pages=<%=totalpages%>">最后一页</a></li>
        <li>转到第:<input type="text" id="pages"name="pages" size="8">页<input type="submit" value="跳转" name="submit"></li>
    </ul>
    </nav>
</form>
</div>
</body>
</html>
