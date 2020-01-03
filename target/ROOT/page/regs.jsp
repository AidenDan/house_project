<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0049)http://localhost:8080/HouseRent/page/register.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>汉房租房 - 用户注册</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
  <script src="../js/jquery-1.8.3.js"></script>
  <META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新用户注册</DT>
  <DD class=past>填写个人信息</DD></DL>
<DIV class=box>
<FORM action="${pageContext.request.contextPath}/u/insert" id="LoginForm" onsubmit="return check()" method="post">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field>用 户 名：</TD>
    <TD><INPUT class=text type=text name=name id="name"><span id="sp"></span></TD></TR>
  <TR>
    <TD class=field>密　　码：</TD>
    <TD><INPUT class=text type=password name=password id="password"></TD></TR>
  <TR>
    <TD class=field>确认密码：</TD>
    <TD><INPUT class=text type=password name=repassword id="repassword"><span id="rep"></span></TD></TR>
  <TR>
    <TD class=field>电　　话：</TD>
    <TD><INPUT class=text type=text name=telephone> </TD></TR>
  <TR>
    <TD class=field>年　　龄：</TD>
    <TD><INPUT class=text type=text name=age> </TD></TR></TBODY></TABLE>
<DIV class=buttons>
<INPUT  value=立即注册 type=submit><br>
  <span id="in" style="color: red">${requestScope.msg01}</span>
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>汉房租房 © 2019   汉房租房  京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV>
</BODY>
<script>
    $("#name").blur(function () {
      var val_name = $("#name").val();
      $.post(
              "/u/isexist",
              {"name":val_name},
              function (data) {
              /*  alert(typeof(data));*/
                if (data==="true"){
                  $("#sp").html("用户名已存在！").css("color", "red");
                }else {
                  $("#sp").html("用户名可用！").css("color", "green");
                }
              }, "text"
      )
    });
</script>
<script>
    $("#repassword").blur(function () {
      var val_repassword = $("#repassword").val();
      var val_password = $("#password").val();
      if (val_repassword !==val_password){
        $("#rep").html("两次输入的密码不相同！").css("color", "red");
      }else{
        $("#rep").html("密码可用！").css("color", "green");
      }
    });
</script>
<script>
 function check() {
   //var params = $("#LoginForm").serialize();
   var val_repassword = $("#repassword").val();
   var val_password = $("#password").val();
   /*判断两次密码是否相同*/
   if (val_repassword !==val_password){
     $("#in").html("注册失败，两次输入的密码不同！").css("color", "red");
     return false;
   }
   return true;
}
</script>
</HTML>
