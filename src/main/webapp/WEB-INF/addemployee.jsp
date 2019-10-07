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
    <title>Pracownicy</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<h3 class="h3me">Dodawanie nowego pracownika</h3>
<table cellpadding="10">
    <form action="addEmployee" method="post" class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-2 control-label">Imię:</label>
            <input class="form-control" type="text" name="firstname" />
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Nazwisko:</label>
            <input class="form-control" type="text" name="surname" />
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">E-mail:</label>
            <input class="form-control" type="email" name="email" />
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Telefon:</label>
            <input class="form-control" type="text" name="phone" />
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Notatka:</label>
            <input class="form-control" type="text" name="note" />
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Roboczogodzina:</label>
            <input class="form-control" type="text" name="manhour" />
        </div>
        <div class="col-sm-10">
            <button class="btn btn-primary" >Utwórz</button>
        </div>
    </form>
</table>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
