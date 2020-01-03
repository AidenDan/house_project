<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
  <script src="../js/jquery-1.8.3.js"></script>
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
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
  </FORM></DL>

</DIV>
<DIV class="main wrap">
<TABLE class=house-list>
  <TBODY>
  <c:forEach var="h" items="${requestScope.pageInfo.list}">
    <TR>                                                                                                                             <%--在nginx里面设置了代理路径/代表了G:/upLoadFile/--%>
      <TD class=house-thumb><SPAN><A href="#" target="_blank"><img src="http://localhost:80/${h.path}" width="100" height="75" alt=""></A></SPAN></TD>
      <TD>
        <DL>
          <DT><A href="#" target="_blank">${h.title}</A>&nbsp;&nbsp;&nbsp;&nbsp;
            <c:if test="${h.ispass  eq '1'}">
              <span style="color: red">${"已经审核"}</span>
            </c:if>
            <c:if test="${h.ispass  eq '0'}">
              <span style="color: red">${"未审核"}</span>
            </c:if>
          </DT>
          <DD>房屋编号：${h.id}，${h.districtName}，${h.streetName}，${h.floorage}平米<BR>价格：￥${h.price}，联系方式：${h.contact} </DD></DL></TD>
      <TD class=house-type><LABEL class=ui-green><INPUT onclick='location.href="${pageContext.request.contextPath}/h/upHouse/${h.id}"' value="收    藏" type=button name=search></LABEL></TD>
      <TD class=house-price><LABEL class=ui-green><INPUT value="详    情" type=button name=search onclick="location.href='${pageContext.request.contextPath}/h/downHouse?houseId=${h.id}'"></LABEL></TD></TR>
  </c:forEach>
  </TBODY></TABLE>
<DIV class=pager>
<UL>
  <LI class=current><A href="javascript:goPage(1)">首页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.prePage})">上一页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.pageNum+1})">下一页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.lastPage})">末页</A></LI></UL><SPAN
class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>汉房租房 © 2019 汉房集团 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY>
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
