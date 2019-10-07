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
<h3 class="h3me">Zlecenia aktywne</h3>
<div>
    <table class="table" cellpadding="10">
        <tr class="trme">
            <td>Id</td>
            <td>Data przyjęcia do naprawy: </td>
            <td>Planowana data rozpoczęcia naprawy: </td>
            <td>Data rozpoczęcia naprawy: </td>
            <td>Pracownik: </td>
            <td>Opis problemu: </td>
            <td>Opis naprawy: </td>
            <td>Status: </td>
            <td>Pojazd: </td>
            <td>Koszt naprawy: </td>
            <td>Koszt części: </td>
            <td>Ilość roboczogodzin: </td>
        </tr>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.id}</td>
                <td>${order.receiptDate}</td>
                <td>${order.plannedRepairDate}</td>
                <td>${order.startRepairDate}</td>
                <td>${order.employeeId}</td>
                <td>${order.problemDesc}</td>
                <td>${order.repairDesc}</td>
                <td>${order.status}</td>
                <td>${order.vehicleId}</td>
                <td>${order.repairCost}</td>
                <td>${order.partsCost}</td>
                <td>${order.numberManHours}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
