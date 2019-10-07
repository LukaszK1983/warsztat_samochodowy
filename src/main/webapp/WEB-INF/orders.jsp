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
    <title>Zlecenia</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<h3 class="h3me">Zlecenia</h3>
<div class="table">
    <table cellpadding="10">
        <tr class="trme">
            <td>Id</td>
            <td>Data przyjęcia</td>
            <td>Opis problemu</td>
            <td>Status</td>
            <td></td>
        </tr>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.id}</td>
                <td>${order.receiptDate}</td>
                <td>${order.problemDesc}</td>
                <td>${order.status}</td>
                <td><button class="btn btn-primary" ><a href="detailsOrder?id=${order.id}" class="ame">Szczegóły</a></button>
                    <button class="btn btn-primary" ><a href="editOrder?id=${order.id}" class="ame">Edycja</a></button>
                    <button class="btn btn-danger" ><a href="deleteOrder?id=${order.id}" class="ame">Usuń</a></button></td>
            </tr>
        </c:forEach>
        <tr>
            <td class="noneBorder2"></td>
            <td class="noneBorder2"></td>
            <td class="noneBorder2"></td>
            <td class="noneBorder"></td>
            <td><button class="btn btn-primary" ><a href="addOrder" class="ame">Dodaj nowe zlecenie</a></button></td>
        </tr>
    </table>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
