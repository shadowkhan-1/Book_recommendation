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
            // var Book_ISBN = book[1_5000];
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
<div class="container-fiuled">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="getbook.action">首页</a>
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
    <%
        PageBean pagebean = (PageBean)request.getAttribute("pagebean");
        Integer pages = pagebean.getPages();
        Integer totalpages = pagebean.getTotalpages();
    %>
    <div class="favorite_left">
        <div class="row">
            <%
                List<BX_Books> list=pagebean.getList();
                for(BX_Books books:list){
                    String author_href = "https://www.baidu.com/s?wd="+books.getBook_Author().replace(" ","+")+"&ie=UTF-8";
                    String title_href = "https://www.baidu.com/s?wd="+books.getBook_Title().replace(" ","+")+"&ie=UTF-8";
                    String big_image_href = books.getImage_URL_L();
            %>
            <%--//    大屏幕放3张略缩图，pc端放4张，平板和手机放6张 --%>
            <div class="col-md-3 col-xs-3">
                <%--        <div class="thumbnail">--%>
                <div class="thumbnail book_info_left">
                    <a href=<%=big_image_href%> target="_blank"><img src=<%=books.getImage_URL_M()%> alt="无图"  class="img-responsive" style="width:140px;height:160px"></a>
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
    </div>

    <div class="favorite">
        <div class="alert alert-success alert-dismissable">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <h4>
                猜你喜欢
            </h4>
        </div>
        <%
            List<BX_Books> recommend = pagebean.getRecommend();
            for(BX_Books recm:recommend){
                String author_href = "https://www.baidu.com/s?wd="+recm.getBook_Author().replace(" ","+")+"&ie=UTF-8";
                String title_href = "https://www.baidu.com/s?wd="+recm.getBook_Title().replace(" ","+")+"&ie=UTF-8";
                String big_image_href = recm.getImage_URL_L();
        %>
        <div class="col-md-5">
            <%--        <div class="thumbnail">--%>
            <div class="thumbnail book_info_left">
                <a href=<%=big_image_href%> target="_blank"><img src=<%=recm.getImage_URL_M()%> alt="无图"  class="img-responsive" style="width:140px;height:160px"></a>
            </div>
            <div class="thumbnail book_info_right">
                <h4 class="book_info"><a href=<%=title_href%> target="_blank"><%=recm.getBook_Title()%></a></h4>
                <p style="color:#28ff46;">
                    <span class="book_info">作者：<a href=<%=author_href%> target="_blank"><%=recm.getBook_Author()%><br></a></span>
                    <span class="book_info">出版社：<%=recm.getPublisher()%></span><br>
                    出版日期:<%=recm.getYear_Of_Publication()%>
                </p>
                <a name="favorite" href="javascript:;" data-username="<%=userbean.getUsername()%>" onclick="sendFavorite('<%=recm.getISBN()%>')"><span class="glyphicon glyphicon-heart"></span></a>  <!--用''来防止去除数据型字符型前面的0-->
            </div>
            <%--        </div>--%>
        </div>
        <%}%>
    </div>
    <%
    String username = userbean.getUsername();
    %>
    <form action="getfavorite.action?username=<%=username%>" method="post">         <!--属性驱动要获取action里面1链接的值要用post提交注意注意注意-->
        <nav style="text-align: center">
            <ul class="pagination">
                <li><a href="#">第<%=pagebean.getPages()%>页 共<%=(totalpages==0)?1_5000:totalpages%>页 </a></li>
                <li><a href="getfavorite?username=<%=username%>&pages=1">首页</a></li>
                <li><a href="getfavorite?username=<%=username%>&pages=<%=(pages<=1_5000)?pages=1_5000:(pages-1_5000) %>"> 上一页</a></li>
                <li><a href="getfavorite?username=<%=username%>&pages=<%=(pages>=totalpages)?totalpages:(pages+1_5000)%>"> 下一页</a></li>
                <li><a href="getfavorite?username=<%=username%>&pages=<%=(totalpages==0)?1_5000:totalpages%>">最后一页</a></li>
                <li>转到第:<input type="text" id="page"name="page" size="8">页<input type="submit" class="btn btn-default" value="跳转" name="submit"></li>
<%--                <input type="hidden"  name="username" value=<%=username%>>   一种方法--%>
            </ul>
        </nav>
    </form>
</div>
</body>
</html>

