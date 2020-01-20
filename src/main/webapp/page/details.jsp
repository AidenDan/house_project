<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
  <script src="../js/jquery-1.8.3.js"></script>
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<BODY>

<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV>
<%--<DIV class=search>
<FORM method=get><INPUT class=text type=text name=keywords>
 <LABEL class="ui-green searchs"><a href="list.htm" target="_blank">搜索房屋</a></LABEL>
</FORM></DIV>--%></DIV>
<DIV id=navbar class=wrap>
<DL class="search clearfix">

  <FORM id=sform method=post action="${pageContext.request.contextPath}/h/listHouse">
    <%--    <div style="width: 1000px; border: 1px solid red ; margin: auto; text-align: center" >--%>
    <span style="color: red">房屋信息:</span>
    <input type="hidden" name="pageNum" id="pageNum">
    <br>
    标题:<input id="ctitle" type="text" name="ctitle" value="${requestScope.condition.ctitle}">&nbsp;&nbsp;&nbsp;&nbsp;
    价格:<input type="text" id="cprice1" name="cprice1" value="${requestScope.condition.cprice1}"> ---> <input type="text" id="cprice" name="cprice" value="${requestScope.condition.cprice}">
    <br>
    <span style="color: red">房屋位置:</span> <br>
    区域:<SELECT class=text name=cdistrictId id="cdistrictId">
    <option value="-1">请选择</option>
  </SELECT>&nbsp;&nbsp;&nbsp;&nbsp;
    街道:<SELECT class=text
               name=cstreetId id="cstreetId">
    <option value="-1">请选择</option>
  </SELECT>&nbsp;&nbsp;&nbsp;&nbsp;
    房型:<SELECT class=text name=ctypeId id="ctypeId">
    <option value="-1">请选择</option>
  </SELECT>&nbsp;&nbsp;&nbsp;&nbsp;
    面积:<input type="text" id="cfloorage1" name="cfloorage1" value="${requestScope.condition.cfloorage1}"> ---> <input type="text" id="cfloorage" name="cfloorage" value="${requestScope.condition.cfloorage}">

    <%--   </div>--%>
    &nbsp;&nbsp;&nbsp;&nbsp;<LABEL class=ui-blue><input type="submit" value="搜索房屋"></LABEL>
  </FORM>

</DL></DIV>
<DIV id=position class=wrap>当前位置：汉房租房网 - 浏览房源</DIV>
<DIV id=view class="main wrap">
<DIV class=intro>
<DIV class=lefter>
<H1>${house.title}</H1>
<DIV class=subinfo><fmt:formatDate value="${house.pubdate}" pattern="yyyy-MM-dd"></fmt:formatDate> </DIV>
<DIV class=houseinfo>
<P>户　　型：<SPAN>${house.typeName}</SPAN></P>
<P>面　　积：<SPAN>${house.floorage}m<SUP>2</SUP></SPAN></P>
<P>价　　格：<SPAN>${house.price}元</SPAN></P>
<P>位　　置：<SPAN>${house.districtName}，${house.streetName}</SPAN></P>
<P>联系方式：<SPAN>${house.telephone}</SPAN></P></DIV></DIV>
<DIV class=side>
<P><A class=bold href="http://localhost:8080/House-2/#">汉房地产经纪公司</A></P>
<P>资质证书：有</P>
<P>内部编号:1000</P>
<P>联 系 人：</P>
<P>联系电话：<SPAN></SPAN></P>
<P>手机号码：<SPAN>暂无</SPAN></P></DIV>
<DIV class=clear></DIV>
<DIV class=introduction>
<H2><SPAN><STRONG>房源详细信息</STRONG></SPAN></H2>
<DIV class=content>
<P>${house.description}</P></DIV></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>汉房租房 © 2019 汉房集团 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV>
</BODY>
<script>
  //分页查询
  function goPage(page) {
    $("#pageNum").val(page);
    $("#sform").submit();
  }
</script>
<script>
  $(function () {
    $("#cprice1").val(${requestScope.condition.cprice1});
    $("#cprice").val(${requestScope.condition.cprice});
    $("#cdistrictId").val(${requestScope.condition.cdistrictId});
    $("#cstreetId").val(${requestScope.condition.cstreetId});
    $("#ctypeId").val(${requestScope.condition.ctypeId});
    $("#cfloorage1").val(${requestScope.condition.cfloorage1});
    $("#cfloorage").val(${requestScope.condition.cfloorage});
  })
</script>
<script>
  $(function () {
    $.post (
            "/t/getType",
            null,
            function (data) {
              $.each(data, function (i, e) {
                $("#ctypeId").append("<option value='"+e.id+"'>"+e.name+"</option>")
              })
            }, "json"
    );
    $.post(
            "/d/getAllDistrict",
            null,
            function (data) {
              $.each(data, function (i, e) {
                $("#cdistrictId").append("<option value='"+e.id+"'>"+e.name+"</option>");
              })
            }, "json"
    );
    /*区域与街道的级联操作*/
    $("#cdistrictId").change(function () {
      $("#cstreetId > option:gt(0)").remove();
      $.post(
              "/s/getStreet",
              {"districtId":$(this).val()},
              function (data) {
                $.each(data, function (i, e) {
                  $("#cstreetId").append("<option value='"+e.id+"'>"+e.name+"</option>");
                });
              }, "json"
      )
    })
  })
</script>
</HTML>
