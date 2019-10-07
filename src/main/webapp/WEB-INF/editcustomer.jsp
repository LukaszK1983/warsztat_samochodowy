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
    <title>Klienci</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<h3 class="h3me">Edycja danych klienta</h3>
<div class="table">
    <c:forEach var="customer" items="${customers}">
        <form action="customers" method="post" class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-2 control-label">Imię:</label>
                <input class="form-control" type="text" name="firstName" value="${customer.firstName}" />
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Nazwisko:</label>
                <input class="form-control" type="text" name="surname" value="${customer.surname}" />
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Data urodzenia:</label>
                <input class="form-control" type="text" name="birthYear" value="${customer.birthYear}" />
            </div>
            <div>
                <input type="hidden" name="id" value="${customer.id}" />
            </div>
            <div class="col-sm-10">
                <button class="btn btn-primary" >Wykonaj</button>
            </div>
        </form>
    </c:forEach>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
