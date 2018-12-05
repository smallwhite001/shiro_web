<%--
  Created by IntelliJ IDEA.
  User: Small White
  Date: 2018/7/21
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
  <head>
    <title>Shiro</title>
  </head>
  <body>
   <h1>欢迎来到Shiro</h1>
  <br>
   欢迎<shiro:principal></shiro:principal><br>

   <shiro:hasRole name="admin">
   <a href="/admin.jsp">Admin page</a>
    <br>
   </shiro:hasRole>

   <shiro:hasRole name="user">
   <a href="/user.jsp">User page</a>
     <br>
   </shiro:hasRole>

   <a href="shiro/testShiroAnnotation">Annotation注解</a>
   <a href="/shiro/logout">登出</a><br>
  </body>
</html>
