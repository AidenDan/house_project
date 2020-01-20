<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: LIKUS
  Date: 2019-12-24
  Time: 16:42:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <link href="Css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css" />
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body>
<!--区域管理模块，展示数据-->
<table id ="dg"></table>
<!--定义工具栏-->
<div id="ToolBar">
    <div style="height: 40px;">
        <%--<a href="javascript:goadd()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a>--%>
        <%--<a
                href="javascript:goupdate()" class="easyui-linkbutton"
                iconCls="icon-edit" plain="true">修改</a>--%>
        <%--<a
                href="javascript:godelete()" class="easyui-linkbutton"
                iconCls="icon-remove" plain="true">批量删除</a>--%>
        <%-- 用户名:<input type="text" id="inputName" name="cname">电话:<input id="inputTel" type="text" name="ctelephone">--%>

        房屋类型:<SELECT class=text name=ctypeId id="ctypeId">
        <option value="-1">请选择</option>
    </SELECT>
        区域:<SELECT class=text name=cdistrictId id="cdistrictId">
        <option value="-1">请选择</option>
    </SELECT>
        街道:<SELECT class=text
                   name=cstreetId id="cstreetId">
        <option value="-1">请选择</option>
    </SELECT>
        审核状态:<SELECT class=text
                     name=cispass id="cispass">
        <option value="-1">请选择</option>
        <option value="0">未审核</option>
        <option value="1"> 已审核</option>
    </SELECT>
        价格:<input type="text" id="cprice1" name="cprice1"> -- <input type="text" id="cprice" name="cprice"> 标题:<input id="ctitle" type="text" name="ctitle">
        <a id="btn" href="javascript:searchUser();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
    </div>
</div>
<%--添加弹框--%>
<!--添加窗口-->
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="saveDialogForm" method="post">
        <table>
            <tr>
                <td style="font-size: 10px">用户:</td>
                <td><input type="text" name="name" id="" /></td>
            </tr>
            <tr>
                <td style="font-size: 10px">密码:</td>
                <td><input type="text" name="password" id="" /></td>
            </tr>
            <tr>
                <td style="font-size: 10px">手机号:</td>
                <td><input type="text" name="telephone" id="" /></td>
            </tr>
            <tr>
                <td style="font-size: 10px">管理员(1/0):</td>
                <td><input type="text" name="isadmin" id="" /></td>
            </tr>
        </table>
    </form>
</div>
<div id="AddDialogButtons">
    <a href="javascript:saveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a> <a href="javascript:closeDialog()"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>


<!--修改窗口-->
<div id="UpDialog" class="easyui-dialog" buttons="#UpDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="upDialogForm" method="post">
        <%--隐藏域，用于传id--%>
        <input type="hidden" name="id">
        <table>
            <tr>
                <td style="font-size: 10px">用户:</td>
                <td><input type="text" name="name" id="" /></td>
            </tr>
            <tr>
                <td style="font-size: 10px">密码:</td>
                <td><input type="text" name="password" id="" /></td>
            </tr>
            <tr>
                <td style="font-size: 10px">手机号:</td>
                <td><input type="text" name="telephone" id="" /></td>
            </tr>
            <tr>
                <td style="font-size: 10px">管理员(1/0):</td>
                <td><input type="text" name="isadmin" id="" /></td>
            </tr>
        </table>
    </form>
</div>
<div id="UpDialogButtons">
    <a href="javascript:saveUpDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存修改</a> <a href="javascript:closeUpDialog()"
                                     class="easyui-linkbutton" iconCls="icon-cancel">取消修改</a>
</div>
</body>
<%--发送异步请求，每次刷新这个网页都会自动加载得到新数据--%>
<script language="JavaScript">
    $(function(){
        //使用datagrid绑定数据展示
        $('#dg').datagrid({
            url:'/house/getAllHouse2',
            fitColumns: true,
            pagination: true,
            pageList: [5, 10, 15, 20],
            toolbar:"#ToolBar",
            //singleSelect:true,  //只能设置单选
            pageSize:5,
            columns: [[
                {field:'ck',checkbox:true},  //复选框列
                { field: 'id', title: '房屋编号', width: 50, align: "center" },
                { field: 'userId', title: '用户编号', width: 50, align: "center" },
                { field: 'typeName', title: '房屋类型', width: 50, align: "center" },
                { field: 'title', title: '标题', width: 50, align: "center" },
                { field: 'description', title: '描述', width: 50, align: "center" },
                { field: 'price', title: '价格', width: 50, align: "center" },
                { field: 'pubdate', title: '发布时间', width: 80, align: "center",
                    formatter: function(value,row,index){
                        //返回的内容就是呈现在单元格的内容
                        //value 表示当前字段的值， row当前行的值 index表示索引
                        return new Date(value).toLocaleDateString();
                    }
                },
                { field: 'floorage', title: '面积', width: 50, align: "center" },
                { field: 'contact', title: '联系方式', width: 50, align: "center" },
                { field: 'districtName', title: '区域', width: 50, align: "center" },
                { field: 'streetName', title: '街道', width: 50, align: "center" },
                { field: 'ispass', title: '审核状态', width: 50, align: "center",
                    formatter: function(value,row,index){
                        //返回的内容就是呈现在单元格的内容
                        //value 表示当前字段的值， row当前行的值 index表示索引
                        return value===0?"未审核":"已审核";
                    }
                },
                { field: 'isdel', title: '是否下架', width: 50, align: "center",
                    formatter: function(value,row,index){
                        //返回的内容就是呈现在单元格的内容
                        //value 表示当前字段的值， row当前行的值 index表示索引
                        return value===0?"未下架":"已下架";
                    }
                },
                { field: 'path', title: '房屋图片', width: 70, align: "center" },
                { field: 'opt', title: '审核', width: 50, align: "center",
                    formatter: function(value,row,index){
                        //同步跳转 "<a href='delUsers?id="+row.id+"'>删除</a>  value 表示当前字段的值， row当前行的值 index表示索引"
                        return "<a href='javascript:upSingle1("+row.id+")'>确认审核</a>   <a href='javascript:delSingle("+row.id+")'>详情</a>";
                    }
                }
            ]]
        });
    });

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
<%--<script>
    /*添加的弹框，打开添加的弹框*/
    function goadd() {
        $("#AddDialog").dialog("open").dialog("setTitle", "添加");
    }
    /*点击弹框上面的保存按钮触发函数*/
    function saveDialog() {
        /*用ajax发送异步请求
        * 序列化表单参数
        * */
        var name = $("#saveDialogForm").serialize();
        if (name===""||null){
            alert("用户名不能为空");
            return;
        }
        $.post(
            "/addUsers",
            name,
            function (data) {
                if (data.returnKey>0){
                    //添加成功就关闭窗口
                    $('#dg').datagrid('reload');  //刷新
                    $("#AddDialog").dialog("close");
                }else{
                    alert("添加失败！");
                }
            },
            "json"
        );
    }
    /*点击弹框上面的取消按钮触发函数*/
    function closeDialog() {
        $("#AddDialog").dialog("close");
    }
</script>

&lt;%&ndash;以下为修改功能&ndash;%&gt;
<script>
    //去打开修改的窗口
    function goupdate(){
        //1.获取datagrid的选中行
        var selObjs=$("#dg").datagrid("getSelections");
        //2.验证是否选中一行
        if(selObjs.length==1){
            $("#UpDialog").dialog("open").dialog('setTitle',"编辑用户");

            //发送异步请求获取对应id数据进行回显
            $.post(
                "/getUsersById",
                {"id":selObjs[0].id},
                function (data) {
                    //还原表单数据  查询数据库，通过id获取单行记录的对象，进行回显？
                    // $("#upDialogForm").form('load',json对象:{"表单对象名称":值});把选中行数据加载到表单进行回显，即更新data代表的数据
                    //注意form函数里面传的为json对象
                    $("#upDialogForm").form('load', data);
                },
                "json"
            )
        }else{
            $.messager.alert('友情提示','你可能没有选中行，获者选中多行，请选择一行修改，真他妈笨，选一行都不会','info');
        }
    }
</script>
<script>
    /*点击弹框上面的保存按钮触发函数*/
    function saveUpDialog(){
        /*用ajax发送异步请求
        * 序列化表单参数
        * */
        var name = $("#upDialogForm").serialize();
        $.post(
            "/upUsers",
            name,
            function (data) {
                if (data.returnKey>0){
                    //添加成功就刷新并关闭窗口
                    $('#dg').datagrid('reload');  //刷新
                    $("#UpDialog").dialog("close");
                }else{
                    alert("修改失败！");
                }
            },
            "json"
        );
    }
    /*点击弹框上面的取消按钮触发函数*/
    function closeUpDialog() {
        $("#UpDialog").dialog("close");
    }
</script>--%>
<script>
    /*点击每行内置的审核通过链接进行修改，把ispass设为1*/
    function upSingle1(id) {
        /* $("#UpDialog").dialog("open").dialog('setTitle',"编辑用户");*/
        //1.获取datagrid的选中行
        /*var selObjs=$("#dg").datagrid("getSelections");*/
        $.post(
            "/getUsersById1",
            {"id":id},
            function (data) {
                if (data.returnKey>0){
                    //刷新页面
                    $('#dg').datagrid('reload');  //刷新
                }else {
                    $.messager.alert("友情提示", "确认审核失败！", "info");
                }
            },
            "json"
        )
    }
</script>
<%--<script>
    /*点击每行内置的删除链接进行删除*/
    function delSingle(id) {
        $.post(
            "/delUsersById",
            {"id":id},
            function (data) {
                if (data.returnKey>0){
                    //添加成功就刷新并关闭窗口
                    $('#dg').datagrid('reload');  //刷新
                }else{
                    $.messager.alert("友情提示", "删除失败！", "info");
                }
            },
            "json"
        )
    }
</script>
<script>
    /*批量删除*/
    function godelete() {
        //1.获取datagrid的选中行
        var selObjs=$("#dg").datagrid("getSelections");
        //2.验证是否选中一行
        if(selObjs.length>0){
            /*前台不能直接传一个数组或集和到后台*/
            $.messager.confirm("友情提示", "确认删除吗", function (r) {
                if (r){
                    //r=true表示点击ok 否则点击取消
                    //发送异步请求调用接口实现批量删除   ids=1,2,3,4
                    //获取选中项的值id,拼接成:  值1,值2,值3
                    var ids= '';
                    for (var i = 0; i <selObjs.length ; i++) {
                        ids+=selObjs[i].id + ',';
                    }
                    //发送异步请求获取对应id数据进行回显
                    $.post(
                        "/delUsersByBatch",
                        {"ids":ids},
                        function (data) {
                            if (data.returnKey>0){
                                //添加成功就刷新并关闭窗口
                                $('#dg').datagrid('reload');  //刷新
                            }else{
                                $.messager.alert("友情提示", "删除失败！", "info");
                            }
                        },
                        "json"
                    )
                }
            });
        }else{
            $.messager.alert('友情提示','你可能没有选中行，获者选中多行，请选择一行修改，真他妈笨，选一行都不会','info');
        }
    }
</script>--%>
<script>
    /*搜索功能*/
    function searchUser() {
        /* var inputNameval = $("#inputName").val();
         var inputTelval = $("#inputTel").val();*/
        var ctypeIdVal = $("#ctypeId").val();
        var cdistrictIdVal = $("#cdistrictId").val();
        var cstreetIdVal = $("#cstreetId").val();
        var cispassVal = $("#cispass").val();
        var cprice1Val = $("#cprice1").val();
        var cpriceVal = $("#cprice").val();
        var ctitleVal = $("#ctitle").val();

        //设置条件查询，动态查询
        $("#dg").datagrid("load",{ //设置查询条件
            /* cname: inputNameval,
             ctelephone: inputTelval*/
            ctypeId:ctypeIdVal,
            cdistrictId:cdistrictIdVal,
            cstreetId:cstreetIdVal,
            cispass:cispassVal,
            cprice1:cprice1Val,
            cprice:cpriceVal,
            ctitle:ctitleVal
        });
    }
</script>
</html>




















