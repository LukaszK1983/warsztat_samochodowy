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
    <title>Zlecenia</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<h3 class="h3me">Edycja zlecenia</h3>
<c:forEach var="order" items="${orders}">
    <form action="orders" method="post" class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-2 control-label">Data przyjęcia do naprawy:</label>
            <input class="form-control" type="datetime-local" name="receiptDate" value="${order.receiptDate}" />
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Planowana data rozpoczęcia naprawy:</label>
            <input class="form-control" type="datetime-local" name="plannedRepairDate" value="${order.plannedRepairDate}" />
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Data rozpoczęcia naprawy:</label>
            <input class="form-control" type="datetime-local" name="startRepairDate" value="${order.startRepairDate}" />
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Przypisany pracownik:</label>
            <input class="form-control" type="text" name="employeeId" value="${order.employeeId}" />
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Opis problemu:</label>
            <input class="form-control" type="text" name="problemDesc" value="${order.problemDesc}" />
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Opis naprawy:</label>
            <input class="form-control" type="text" name="repairDesc" value="${order.repairDesc}" />
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Status:</label>
            <select name="status">
                <option value="Przyjęty">Przyjęty</option>
                <option value="Zatwierdzone koszty naprawy">Zatwierdzone koszty naprawy</option>
                <option value="W naprawie">W naprawie</option>
                <option value="Gotowy do odbioru">Gotowy do odbioru</option>
                <option value="Rezygnacja">Rezygnacja</option>
            </select>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Pojazd:</label>
            <input class="form-control" type="text" name="vehicleId" value="${order.vehicleId}" />
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Koszt naprawy:</label>
            <input class="form-control" type="text" name="repairCost" value="${order.repairCost}" />
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Koszt części:</label>
            <input class="form-control" type="text" name="partsCost" value="${order.partsCost}" />
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Ilość roboczogodzin:</label>
            <input class="form-control" type="text" name="numberManHours" value="${order.numberManHours}" />
        </div>
        <div>
            <input type="hidden" name="id" value="${order.id}" />
        </div>
        <div class="col-sm-10">
            <button class="btn btn-primary" >Wykonaj</button>
        </div>
    </form>
</c:forEach>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
