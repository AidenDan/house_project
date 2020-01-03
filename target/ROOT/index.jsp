<%--
  Created by IntelliJ IDEA.
  User: LIKUS
  Date: 2019-12-20
  Time: 23:16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="button" value="我是后台用户" style="font-size: large" onclick="location.href='${pageContext.request.contextPath}/admin/admin.html'">
<input type="button" value="我是房东用户" style="font-size: large" onclick="location.href='${pageContext.request.contextPath}/page/login.jsp'">
</body>
</html>
