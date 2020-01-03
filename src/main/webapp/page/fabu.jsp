<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>汉房租房 -发布房屋信息</TITLE>
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
  <DT>新房屋信息发布</DT>
  <DD class=past>填写房屋信息</DD></DL>
<DIV class=box>
<FORM id=add_action method=post name=add_action
action="${pageContext.request.contextPath}/h/getHouse" enctype="multipart/form-data" onsubmit="return check()">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field>标　　题：</TD>
    <TD><INPUT id=add_action_title class=text type=text name=title><span id="sptitle" style="color: red"></span></TD></TR>
  <TR>
    <TD class=field>户　　型：</TD>
    <TD><SELECT class=text name=typeId id="typeId">
      <option value="-1">请选择</option>
    </SELECT><span id="sptypeid" style="color: red"></span></TD></TR>
  <TR>
    <TD class=field>面　　积：</TD>
    <TD><INPUT id=floorage class=text type=text
name=floorage><span id="spfloor" style="color: red"></span></TD></TR>
  <TR>
    <TD class=field>价　　格：</TD>
    <TD><INPUT id=price class=text type=text name=price> <span id="spprice" style="color: red"></span></TD></TR>
  <TR>
    <TD class=field>房产证日期：</TD>
    <TD><INPUT class=text type=date name=pubdate id="pubdate"><span id="spdate" style="color: red"></span></TD></TR>
  <TR>
    <TD class=field>位　　置：</TD>
    <TD>区：<SELECT class=text name=districtId id="districtId">
      <option value="-1">请选择</option>
    </SELECT><span id="spdistrictId" style="color: red"></span>
      街：<SELECT class=text
        name=streetId id="streetId">
        <option value="-1">请选择</option>
      </SELECT><span id="spstreetId" style="color: red"></span> </TD></TR><!--
						<tr>
							<td class="field">坐  标：</td>
							<td><input type="text" class="text" name="point" />
							</td>
						</tr>
						--><!--  <tr>
							<td class="field">Y 坐  标：</td>
							<td><input type="text" class="text" name="point.y" /></td>
						</tr>-->
  <TR>
    <TD class=field>联系方式：</TD>
    <TD><INPUT id=add_action_contact class=text type=text name=contact><span id="spcontact" style="color: red"></span> </TD></TR>
  <TR>
    <TD class=field>详细信息：</TD>
    <TD><TEXTAREA name=description id="description"></TEXTAREA><span id="spdescription" style="color: red"></span></TD></TR>
  <TR>
    <TD class=field>添加图片：</TD>
    <TD><INPUT class=text type=file name=pfile id="pfile"><span id="spfile" style="color: red"></span></TD></TR>
  <TR>
  </TBODY></TABLE>
<DIV class=buttons><INPUT  value=立即发布 type=submit><input value="返回管理页" type="button" onclick="location.href='${pageContext.request.contextPath}/h/showHouse'">
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>汉房租房 © 2019  汉房  京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY>
<script>
  $(function () {
    $.post (
            "/t/getType",
            null,
            function (data) {
              $.each(data, function (i, e) {
                $("#typeId").append("<option value='"+e.id+"'>"+e.name+"</option>")
              })
            }, "json"
    );
    $.post(
            "/d/getAllDistrict",
            null,
            function (data) {
              $.each(data, function (i, e) {
                $("#districtId").append("<option value='"+e.id+"'>"+e.name+"</option>");
              })
            }, "json"
    );
/*区域与街道的级联操作*/
    $("#districtId").change(function () {
      $("#streetId > option:gt(0)").remove();
      $.post(
              "/s/getStreet",
              {"districtId":$(this).val()},
              function (data) {
                $.each(data, function (i, e) {
                  $("#streetId").append("<option value='"+e.id+"'>"+e.name+"</option>");
                });
              }, "json"
      )
    })
  });
</script>
<script>
  <c:if test="${requestScope.msg03 eq 'success'}">
  alert("发布成功！");
  </c:if>
  <c:if test="${requestScope.msg03 eq 'fail'}">
  alert("发布失败！");
  </c:if>
</script>
<script>
  function check() {
    if ($("#add_action_title").val()===""){
      $("#sptitle").html("标题不能为空");
      return false;
    }

    /*var arr1 = $("#typeId").options;*/
    if ($("#typeId").val()==="-1"){
      $("#sptypeid").html("类型不能为空");
      return false;
    }
    if ($("#floorage").val()===""){
      $("#spfloor").html("面积不能为空");
      return false;
    }
    if ($("#price").val()===""){
      $("#spprice").html("价格不能为空");
      return false;
    }
    if ($("#pubdate").val()===""){
      $("#spdate").html("日期不能为空");
      return false;
    }

    /*var arr2 = $("#districtId").options;*/
    if ($("#districtId").val()==="-1"){
      $("#spdistrictId").html("区域不能为空");
      return false;
    }
    if ($("#add_action_contact").val()===""){
      $("#spcontact").html("联系方式不能为空");
      return false;
    }
    if ($("#description").val()===""){
      $("#spdescription").html("描述不能为空");
      return false;
    }
    if ($("#pfile").val()===''){
      $("#spfile").html("图片不能为空");
      return false;
    }
      return true;
  }
</script>
</HTML>
