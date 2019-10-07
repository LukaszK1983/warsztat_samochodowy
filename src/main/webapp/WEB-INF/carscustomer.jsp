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
    <title>Pojazdy</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<h3 class="h3me">Pojazdy klienta</h3>
<div class="table">
    <table cellpadding="10">
        <tr class="trme">
            <td>Id</td>
            <td>Marka</td>
            <td>Model</td>
            <td>Rok produkcji</td>
            <td>Nr rejestracyjny</td>
            <td>Następne badanie</td>
            <td></td>
        </tr>
        <c:forEach var="vehicle" items="${vehicles}">
            <tr>
                <td>${vehicle.id}</td>
                <td>${vehicle.brand}</td>
                <td>${vehicle.model}</td>
                <td>${vehicle.made}</td>
                <td>${vehicle.registrationNr}</td>
                <td>${vehicle.nextExam}</td>
                <td><button class="btn btn-primary" ><a href="vehicleFixes?id=${vehicle.id}" class="ame">Naprawy</a></button>
                    <button class="btn btn-primary" ><a href="editVehicle?id=${vehicle.id}" class="ame">Edycja</a></button>
                    <button class="btn btn-danger" ><a href="deleteVehicle?id=${vehicle.id}" class="ame">Usuń</a></button></td>
            </tr>
        </c:forEach>
        <tr>
            <td class="noneBorder2"></td>
            <td class="noneBorder2"></td>
            <td class="noneBorder2"></td>
            <td class="noneBorder2"></td>
            <td class="noneBorder2"></td>
            <td class="noneBorder"></td>
            <td><button class="btn btn-primary" ><a href="addVehicleCustomer?customerId=${param.customerId}" class="ame">Dodaj nowy pojazd klienta</a></button></td>
        </tr>
    </table>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
