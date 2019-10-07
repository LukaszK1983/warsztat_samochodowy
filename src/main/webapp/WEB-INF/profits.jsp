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
    <title>Raporty</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<h3 class="h3me">Zyski z działalności</h3>

<h6 class="h6me">Podaj zakres czasu w celu wygenerowania raportu</h6>
<div>
    <form action="profits" method="post" class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-2 control-label">Data od:</label>
            <input class="form-control" type="date" name="dateFrom" />
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Data do:</label>
            <input class="form-control" type="date" name="dateTo" />
        </div>
        <div class="col-sm-10">
            <button class="btn btn-primary" >Wykonaj</button>
        </div>
    </form>
</div>
<div class="table">
    <table cellpadding="10">
        <tr class="trme">
            <td>Naprawa</td>
            <td>Części</td>
            <td>Roboczogodziny</td>
        </tr>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.repairCost}</td>
                <td>${order.partsCost}</td>
                <td>${order.manHourCost}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
