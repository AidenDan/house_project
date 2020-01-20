<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0050)http://localhost:8080/HouseRent/manage!list.action -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD>
<TITLE>汉房租房 - 用户管理</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
  <script src="../admin/js/jquery-1.8.3.js"></script>
<META name=GENERATOR ></HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV>
<DIV class=search><LABEL class="ui-green searchs"><a href="${pageContext.request.contextPath}/page/fabu.jsp" title="">发布房屋信息</a></LABEL>
<LABEL class=ui-green><INPUT onclick='document.location="${pageContext.request.contextPath}/u/out"' value="退       出" type=button name=search></LABEL>&nbsp;&nbsp;<span style="color: red">欢迎您，${sessionScope.users.name}</span>
</DIV></DIV>
<DIV class="main wrap">
<DIV id=houseArea>
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
          <DD>房屋编号:${h.id}，${h.districtName}，${h.streetName}，${h.floorage}平米<BR>联系方式：${h.contact} </DD></DL></TD>
      <TD class=house-type><LABEL class=ui-green><INPUT onclick='location.href="${pageContext.request.contextPath}/h/upHouse/${h.id}"' value="修    改" type=button name=search></LABEL></TD>
      <TD class=house-price><LABEL class=ui-green><INPUT value="下    架" type=button name=search onclick="location.href='${pageContext.request.contextPath}/h/downHouse?houseId=${h.id}'"></LABEL></TD></TR>
  </c:forEach>

  <%--<TR class=odd>
    <TD class=house-thumb><SPAN><A href="details.htm" target="_blank"><img src="../images/thumb_house.gif" width="100" height="75" alt=""></A></SPAN></TD>
    <TD>
      <DL>
        <DT><A href="details.htm" target="_blank">jjjj</A></DT>
        <DD>海淀区中关村大街,123平米<BR>联系方式：ff </DD></DL></TD>
    <TD class=house-type><LABEL class=ui-green><INPUT onclick=update(47) value="修    改" type=button name=search></LABEL></TD>
    <TD class=house-price><LABEL class=ui-green><INPUT value="删    除" type=button name=search></LABEL></TD></TR>--%>
  <%--<TR>
    <TD class=house-thumb><SPAN><A href="details.htm" target="_blank"><img src="../images/thumb_house.gif" width="100" height="75" alt=""></A></SPAN></TD>
    <TD>
      <DL>
        <DT><A href="details.htm" target="_blank">大房子</A></DT>
        <DD>海淀区中关村大街,100平米<BR>联系方式：123456789 </DD></DL></TD>
    <TD class=house-type><LABEL class=ui-green><INPUT onclick=update(6) value="修    改" type=button name=search></LABEL></TD>
    <TD class=house-price><LABEL class=ui-green><INPUT value="删    除" type=button name=search></LABEL></TD></TR>--%>
  </TBODY></TABLE></DIV>
<DIV class=pager>
<UL>
  <c:forEach begin="1" end="${requestScope.pageInfo.pages}" step="1" var="p">
    <LI class=current><A id=widget_338868862
                         href="${pageContext.request.contextPath}/h/showHouse?pageNum=${p}"
                         parseContent="true" showError="true" targets="houseArea"
                         ajaxAfterValidation="false" validate="false"
                         dojoType="struts:BindAnchor">${p}</A>
    </LI>
  </c:forEach>
<%--  <LI class=current><A id=widget_1160989910
  href="http://localhost:8080/HouseRent/manage!ajaxList.action?number=2" 
  parseContent="true" showError="true" targets="houseArea" 
  ajaxAfterValidation="false" validate="false" 
dojoType="struts:BindAnchor">2</A>
   </LI>--%>
</UL><SPAN class=total>${requestScope.pageInfo.pageNum}/${requestScope.pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>汉房租房 © 2019 汉房 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY>
<script>
  <c:if test="${requestScope.msg04 eq 'success'}">
  alert("修改成功！");
  </c:if>
  <c:if test="${requestScope.msg04 eq 'fail'}">
  alert("修改失败！");
  </c:if>
</script>
</HTML>
