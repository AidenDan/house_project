<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0032)http://localhost:8080/HouseRent/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>汉房租房 - 用户登录</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
  <script src="../js/jquery-1.8.3.js"></script>
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DIV class=box>
<H4>用户登录</H4>
<FORM id=user method=post name=user action="${pageContext.request.contextPath}/u/check">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD colSpan=2></TD></TR>
  <TR>
    <TD class=field>手 机 号：</TD>
    <TD><!-- <input type="text" class="text" name="name" /> --><INPUT 
      id=phone class=text type=text name=phone>&nbsp;&nbsp;<INPUT  value="点击获取验证码" type="button" id="smsBtn"> </TD></TR>
  <TR>
    <TD class=field>输入验证码：</TD>
    <TD><!-- <input type="password" class="text" name="password" /> --><INPUT 
      id=code class=text type=text name=code> </TD></TR><!--
						<tr>
							<td class="field">验 证 码：</td>
							<td><input type="text" class="text verycode" name="veryCode" /></td>
						</tr>
						--></TBODY></TABLE>
<DIV class=buttons> <INPUT  value="登陆" type="button" onclick="login()"> <INPUT onclick="goRegs()" value=注册 type=button><br>
  <span style="color: red">${requestScope.msg02}</span>
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>汉房租房 © 2019汉房   京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY>
<script>
  function goRegs() {
    window.location.href = "${pageContext.request.contextPath}/page/regs.jsp";

  }
</script>
<script>
  /*获取短信验证码*/
  $("#smsBtn").click(function () {
      $.post(
          "/sms/send",
          {"phone":$("#phone").val()},
          function (data) {
              if (data.result>0){
                  alert("短信发送成功！");
              }
          }, "json"
      )
  });
</script>
<script>
  /*登录跳转*/
  function login() {
      location.href="${pageContext.request.contextPath}/sms/login?code="+$("#code").val();
  }
</script>
</HTML>
