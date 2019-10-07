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
    <title>Pracownicy</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<h3 class="h3me">Pracownicy</h3>
<div class="table">
    <table cellpadding="10">
        <tr class="trme">
            <td>Id</td>
            <td>Imię</td>
            <td>Nazwisko</td>
            <td></td>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${employee.id}</td>
                <td>${employee.firstName}</td>
                <td>${employee.surname}</td>
                <td><button class="btn btn-primary" ><a href="editEmployee?id=${employee.id}" class="ame">Edycja</a></button>
                    <button class="btn btn-danger" ><a href="deleteEmployee?id=${employee.id}" class="ame">Usuń</a></button>
                    <button class="btn btn-primary" ><a href="ordersEmployee?id=${employee.id}" class="ame">Zlecenia</a></button></td>
            </tr>
        </c:forEach>
        <tr>
            <td class="noneBorder2"></td>
            <td class="noneBorder2"></td>
            <td class="noneBorder"></td>
            <td><button class="btn btn-primary" ><a href="addEmployee" class="ame">Dodaj nowego pracownika</a></button></td>
        </tr>
    </table>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
