<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/17 0017
  Time: 下午 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="table.BX_Books,java.util.List" %>
<%@ page import="javabean.PageBean" %>
<%@ page import="javabean.UserBean" %>
<html>
<head>
    <title>我的收藏</title>
    <link rel="stylesheet" href="css/books.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function sendFavorite(ISBN){
            var test = $("[name='favorite']");
            var UserName = test.attr("data-username");
            // var Book_ISBN = test.attr("data-ISBN");                 //id是唯一的,name不是唯一的
            var Book_ISBN = ISBN;
            // alert(data);
            // var book = data.split(",");
            // var UserName = book[0];
            // var Book_ISBN = book[1];
            // alert(UserName+Book_ISBN);
            $.ajax({
                type:"post",                //切记要用post发送，没有execute()，在struts.xml中指定方法
                url:"favorite.action",     //url要加""，注意了，注意了
                data:{"username":UserName,"ISBN":Book_ISBN},          //action通过request.Parameter("username") 取值
                dataType:"text",                                //类型：xml，html，script，json，jsonp，text
                contenType:"application/json;charset=utf-8",
                success:function (result) {
                    if(result=="success"){
                        $(".glyphicon-heart").click(function () {
                            $(this).css("color","#ed4259");
                        });//点击变颜色$(class).click(fuction(){};)
                        alert("收藏成功！");
                    }
                    else if(result == "already"){
                        alert("已经收藏！");
                        $(".glyphicon-heart").click(function () {
                            $(this).css("color","#ed4259");
                        })
                    }
                    else {
                        alert("收藏失败！")
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
                                    <a href="getfavorite.action?pages=1&username=<%=userbean.getUsername()%>">我的收藏</a>
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
        <div class="row">
            <%
                List<BX_Books> list=pagebean.getList();
                for(BX_Books books:list){
                    String author_href = "https://www.baidu.com/s?wd="+books.getBook_Author().replace(" ","+")+"&ie=UTF-8";
                    String title_href = "https://www.baidu.com/s?wd="+books.getBook_Title().replace(" ","+")+"&ie=UTF-8";
                    String big_image_href = books.getImage_URL_L();
            %>
            <%--//    大屏幕放3张略缩图，pc端放4张，平板和手机放6张 --%>
            <div class="col-md-4 col-xs-3">
                <%--        <div class="thumbnail">--%>
                <div class="thumbnail book_info_left">
                    <a href=<%=big_image_href%> target="_blank"><img src=<%=books.getImage_URL_M()%> alt="无图"  class="img-responsive" style="width:140px;height:160px"><span>无</span></a>
                </div>
                <div class="thumbnail book_info_right">
                    <h4 class="book_info"><a href=<%=title_href%> target="_blank"><%=books.getBook_Title()%></a></h4>
                    <p style="color:#28ff46;">
                        <span class="book_info">作者：<a href=<%=author_href%> target="_blank"><%=books.getBook_Author()%><br></a></span>
                        <span class="book_info">出版社：<%=books.getPublisher()%></span><br>
                        出版日期:<%=books.getYear_Of_Publication()%>
                    </p>
                    <a name="favorite" href="javascript:;" data-username="<%=userbean.getUsername()%>" onclick="sendFavorite('<%=books.getISBN()%>')"><span class="glyphicon glyphicon-heart"></span></a>  <!--用''来防止去除数据型字符型前面的0-->
                </div>
                <%--        </div>--%>
            </div>
            <%} %>
        </div>

    <form action="getfavorite?username=<%=userbean.getUsername()%>">
        <nav style="text-align: center">
            <ul class="pagination">
                <li><a href="#">第<%=pagebean.getPages()%>页 共<%=(totalpages==0)?1:totalpages%>页 </a></li>
                <li><a href="getfavorite?username=<%=userbean.getUsername()%>&pages=1">首页</a></li>
                <li><a href="getfavorite?username=<%=userbean.getUsername()%>&pages=<%=(pages<=1)?pages=1:(pages-1) %>"> 上一页</a></li>
                <li><a href="getfavorite?username=<%=userbean.getUsername()%>&pages=<%=(pages>=totalpages)?totalpages:(pages+1)%>"> 下一页</a></li>
                <li><a href="getfavorite?username=<%=userbean.getUsername()%>&pages=<%=(totalpages==0)?1:totalpages%>">最后一页</a></li>
                <li>转到第:<input type="text" id="pages"name="pages" size="8">页<input type="submit" value="跳转" name="submit"></li>
            </ul>
        </nav>
    </form>
</div>
</body>
</html>

