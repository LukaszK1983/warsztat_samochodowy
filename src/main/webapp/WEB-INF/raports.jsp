<%--
  Created by IntelliJ IDEA.
  User: myszamisiek
  Date: 31/08/2019
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jspf/head-imports.jspf" %>
    <title>Raporty</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<h3 class="h3me">Raporty</h3>
<div class="divme">
    <p><a href="activeOrdersEmployees">Aktualne zlecenia pracowników</a></p>
    <p><a href="workHours">Liczba przepracowanych roboczogodzin</a></p>
    <p><a href="profits">Zyski z działalności</a></p>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
