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
<h3 class="h3me">Dodawanie nowego zlecenia</h3>
    <form action="addOrder" method="post" class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-2 control-label">Data przyjęcia do naprawy:</label>
            <input class="form-control" type="text" name="receiptDate" />
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Planowana data rozpoczęcia naprawy:</label>
            <input class="form-control" type="text" name="plannedRepairDate" />
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Przypisany pracownik:</label>
            <input class="form-control" type="text" name="employeeId" />
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Opis problemu:</label>
            <input class="form-control" type="text" name="problemDesc" />
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Pojazd:</label>
            <input class="form-control" type="text" name="vehicleId" />
        </div>
        <div class="col-sm-10">
            <button class="btn btn-primary" >Utwórz</button>
        </div>
    </form>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
