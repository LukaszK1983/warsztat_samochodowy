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
<h3 class="h3me">Edycja danych pojazdu</h3>
<div class="table">
    <c:forEach var="vehicle" items="${vehicles}">
        <form action="vehicles" method="post" class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-2 control-label">Marka:</label>
                <input class="form-control" type="text" name="brand" value="${vehicle.brand}" />
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Model:</label>
                <input class="form-control" type="text" name="model" value="${vehicle.model}" />
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Rok produkcji:</label>
                <input class="form-control" type="text" name="made" value="${vehicle.made}" />
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Nr rejestracyjny:</label>
                <input class="form-control" type="text" name="registrationNr" value="${vehicle.registrationNr}" />
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Następne badanie:</label>
                <input class="form-control" type="text" name="nextExam" value="${vehicle.nextExam}" />
            </div>
            <div>
                <input type="hidden" name="id" value="${vehicle.id}" />
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
