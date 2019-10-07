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
<h3 class="h3me">Klienci</h3>
<div class="table">
    <table cellpadding="10">
        <tr class="trme">
            <td>Id</td>
            <td>Imię</td>
            <td>Nazwisko</td>
            <td>Data urodzenia</td>
            <td></td>
        </tr>
        <c:forEach var="customer" items="${customers}">
            <tr>
                <td>${customer.id}</td>
                <td>${customer.firstName}</td>
                <td>${customer.surname}</td>
                <td>${customer.birthYear}</td>
                <td><button class="btn btn-primary" ><a href="carsCustomer?id=${customer.id}" class="ame">Pojazdy</a></button>
                    <button class="btn btn-primary" ><a href="ordersCustomer?id=${customer.id}" class="ame">Zlecenia</a></button>
                    <button class="btn btn-primary" ><a href="editCustomer?id=${customer.id}" class="ame">Edycja</a></button>
                    <button class="btn btn-danger" ><a href="deleteCustomer?id=${customer.id}" class="ame">Usuń</a></button></td>
            </tr>
        </c:forEach>
        <tr>
            <td class="noneBorder2"></td>
            <td class="noneBorder2"></td>
            <td class="noneBorder2"></td>
            <td class="noneBorder"></td>
            <td><button class="btn btn-primary" ><a href="addCustomer" class="ame">Dodaj nowego klienta</a></button></td>
        </tr>
    </table>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
