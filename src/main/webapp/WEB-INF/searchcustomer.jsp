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
    <%@ include file="/WEB-INF/jspf/head-imports.jspf"%>
    <title>Klienci</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<h3 class="h3me">Wyszukiwanie klienta</h3>
    <form action="searchCustomer" method="post" class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-2 control-label">Wpisz nazwisko klienta:</label>
            <input class="form-control" type="text" name="surname" />
        </div>
        <div class="col-sm-10">
            <button class="btn btn-primary" >Szukaj</button>
        </div>
    </form>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
