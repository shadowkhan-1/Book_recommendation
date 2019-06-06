<%--
  Created by IntelliJ IDEA.
  User: linwei
  Date: 5/11/19
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="table.BX_Books,java.util.List" %>
<%@ page import="javabean.PageBean" %>
<%@ page import="javabean.UserBean" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>书籍</title>
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
<div class="container-fiuled">    <!--去除两边的空格-->
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
                    <form class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="请输入关键字" />
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
    <div class="div_left">
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#tab-weekvisit" role="tab" data-toggle="tab">本月热门</a></li>
            <li role="presentation"><a href="#tab-goodnum" role="tab" data-toggle="tab">收藏排行</a></li>
            <li role="presentation"><a href="#tab-allvote" role="tab" data-toggle="tab">网友推荐</a></li>
        </ul>
        <div class="tab-content mt10">
            <div role="tabpanel" class="tab-pane active" id="tab-weekvisit">
                <ul class="list-group list-top">
                    <%
                        List<BX_Books> top_book_list = pagebean.getList();
                        for (int i=0;i<top_book_list.size();i++){
                            if (i>14) break;
                    %>
                    <li class="list-group-item"><i class="topNum"><%=i+1%></i><a href="" title=<%=top_book_list.get(i).getBook_Title()%>><%=top_book_list.get(i).getBook_Title()%></a><small class="text-muted">/<%=top_book_list.get(i).getBook_Author()%></small><span class="pull-right text-muted"><%=top_book_list.get(i).getBook_Count()%></span></li>
                    <%}%>
                </ul>
            </div>
            <div role="tabpanel" class="tab-pane" id="tab-goodnum">
                <ul class="list-group list-top">

                    <li class="list-group-item"><i class="topNum">1</i><a href="http://rw.svip3.cn/book/1.html" title="战天龙帝">战天龙帝</a><small class="text-muted">/ 天岩</small><span class="pull-right text-muted">483</span></li>

                    <li class="list-group-item"><i class="topNum">2</i><a href="http://rw.svip3.cn/book/4.html" title="忠犬已到请签收">忠犬已到请签收</a><small class="text-muted">/ 淡粥</small><span class="pull-right text-muted">304</span></li>

                    <li class="list-group-item"><i class="topNum">3</i><a href="http://rw.svip3.cn/book/42.html" title="逆剑狂神">逆剑狂神</a><small class="text-muted">/ 一剑清新</small><span class="pull-right text-muted">354</span></li>

                    <li class="list-group-item"><i class="topNum">4</i><a href="http://rw.svip3.cn/book/18.html" title="寒门状元">寒门状元</a><small class="text-muted">/ 天子</small><span class="pull-right text-muted">74</span></li>

                    <li class="list-group-item"><i class="topNum">5</i><a href="http://rw.svip3.cn/book/2.html" title="都市之修仙战尊">都市之修仙战尊</a><small class="text-muted">/ 盛世狼烟</small><span class="pull-right text-muted">52</span></li>

                    <li class="list-group-item"><i class="topNum">6</i><a href="http://rw.svip3.cn/book/32.html" title="能穿越的修行者">能穿越的修行者</a><small class="text-muted">/ 神秘男人</small><span class="pull-right text-muted">119</span></li>

                    <li class="list-group-item"><i class="topNum">7</i><a href="http://rw.svip3.cn/book/33.html" title="网游之隐士无双">网游之隐士无双</a><small class="text-muted">/ 季郁</small><span class="pull-right text-muted">84</span></li>

                    <li class="list-group-item"><i class="topNum">8</i><a href="http://rw.svip3.cn/book/13.html" title="神级门派系统">神级门派系统</a><small class="text-muted">/ 弘琰</small><span class="pull-right text-muted">31</span></li>

                    <li class="list-group-item"><i class="topNum">9</i><a href="http://rw.svip3.cn/book/29.html" title="大唐风华路">大唐风华路</a><small class="text-muted">/ 山下出水</small><span class="pull-right text-muted">37</span></li>

                    <li class="list-group-item"><i class="topNum">10</i><a href="http://rw.svip3.cn/book/30.html" title="郭大炮的文娱生涯">郭大炮的文娱生涯</a><small class="text-muted">/ 大江入海</small><span class="pull-right text-muted">66</span></li>

                    <li class="list-group-item"><i class="topNum">11</i><a href="http://rw.svip3.cn/book/38.html" title="网游之亡灵神官">网游之亡灵神官</a><small class="text-muted">/ 九年起点教育</small><span class="pull-right text-muted">42</span></li>

                    <li class="list-group-item"><i class="topNum">12</i><a href="http://rw.svip3.cn/book/3.html" title="圣墟">圣墟</a><small class="text-muted">/ 辰东</small><span class="pull-right text-muted">70</span></li>

                    <li class="list-group-item"><i class="topNum">13</i><a href="http://rw.svip3.cn/book/5.html" title="都市无上仙尊">都市无上仙尊</a><small class="text-muted">/ 纸花船</small><span class="pull-right text-muted">37</span></li>

                    <li class="list-group-item"><i class="topNum">14</i><a href="http://rw.svip3.cn/book/19.html" title="大数据修仙">大数据修仙</a><small class="text-muted">/ 陈风笑</small><span class="pull-right text-muted">31</span></li>

                    <li class="list-group-item"><i class="topNum">15</i><a href="http://rw.svip3.cn/book/20.html" title="打造异界">打造异界</a><small class="text-muted">/ 华任仇</small><span class="pull-right text-muted">19</span></li>

                </ul>
            </div>

            <div role="tabpanel" class="tab-pane" id="tab-allvote">
                <ul class="list-group list-top">
             <%
                List<BX_Books> book_list = pagebean.getBook_list();
                for(int i=0;i<book_list.size();i++){
                    String title_href = "https://www.baidu.com/s?wd="+book_list.get(i).getBook_Title().replace(" ","+")+"&ie=UTF-8";
             %>
                    <li class="list-group-item"><i class="topNum"><%=i+1%></i><a href=<%=title_href%> target="_blank" title=<%=book_list.get(i).getBook_Title()%>><%=book_list.get(i).getBook_Title()%></a><small class="text-muted">/<%=book_list.get(i).getBook_Author()%></small><span class="pull-right text-muted"><%=book_list.get(i).getBook_Count()%></span></li>
                <%}%>
                </ul>
            </div>
        </div>
    </div>
    <div class="div_center">
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
            <a href=<%=big_image_href%> target="_blank"><img src=<%=books.getImage_URL_M()%> alt="无图"  class="img-responsive" style="width:140px;height:160px"><span><%=books.getGrade()%></span></a>

            </div>
                <div class="thumbnail book_info_right">
                <h4 class="book_info"><a href=<%=title_href%> target="_blank"><%=books.getBook_Title()%></a></h4>
                <p style="color:#28ff46;">
                    <span class="book_info">作者：<a href=<%=author_href%> target="_blank"><%=books.getBook_Author()%><br></a></span>
                    <span class="book_info">出版社：<%=books.getPublisher()%></span><br>
                    出版日期:<%=books.getYear_Of_Publication()%>
                </p>
                   <a name="favorite" href="javascript:;" data-username="<%=userbean.getUsername()%>" onclick="sendFavorite(<%=books.getISBN()%>)"><span class="glyphicon glyphicon-heart"></span></a>
            </div>
<%--        </div>--%>
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
        <li>转到第:<input type="text" id="pages"name="pages" size="8">页<input type="submit" class="btn btn-default" value="跳转" name="submit"></li>
    </ul>
    </nav>
</form>
</div>
</body>
</html>
