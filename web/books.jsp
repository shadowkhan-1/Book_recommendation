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
<html>
<head>
    <title>书籍</title>
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

<table width="500" border="1" align="center" cellpadding="5" cellspacing="0">
<tr>
    <td colspan="5" align="center" valign="middle"><strong><font color="#FF00CC" size="5">书籍信息</font></strong></td>
</tr>
<tr align="center" valign="middle">
    <td><strong>书名</strong></td>
    <td><strong>作者</strong></td>
    <td><strong>出版社</strong></td>
    <td><strong>出版日期</strong></td>
    <td><strong>图片</strong></td>
</tr>
    <%
        PageBean pagebean = (PageBean)request.getAttribute("pagebean");
        Integer pages = pagebean.getPages();
        Integer totalpages = pagebean.getTotalpages();
    %>
<%
    List<BX_Books> list=pagebean.getList();
    for(BX_Books p:list){
%>
<tr align="center" valign="middle">
    <td><%=p.getBook_Title() %></td>
    <td><%=p.getBook_Author() %></td>
    <td><%=p.getPublisher() %></td>
    <td><%=p.getYear_Of_Publication()%></td>
    <td><img src=<%=p.getImage_URL_S() %>></td>
</tr>
<%} %>
</table>
<form action="getbook.action">
<table border="0" align="center" >
    <tr>
        <td>第<%=pagebean.getPages()%>页 共<%=totalpages%>页 <a href="getbook?pages=1">首页</a></td>
        <td><a href="getbook?pages=<%=(pages<=1)?pages=1:(pages-1) %>"> 上一页</a></td>
        <td><a href="getbook?pages=<%=(pages>=totalpages)?totalpages:(pages+1)%>"> 下一页</a></td>
        <td><a href="getbook?pages=<%=totalpages%>">最后一页</a></td>
        <td>转到第:<input type="text" id="pages"name="pages" size="8">页<input type="submit" value="跳转" name="submit"></td>
    </tr>
</table>
</form>
</body>
</html>
