<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>File Upload</title>
<link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>
</head>
<body>
	<h1>${message}</h1>	
	<div class="container">
	<c:if test="${not empty error}">
		    <div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
                                aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${error}</strong>
		    </div>
	</c:if>
	
	<form:form action="processExcel" method="post" cssClass="form-inline" enctype="multipart/form-data">
		<label for="title" class="col-sm-2 control-label">Excel File 2003:</label>
	<div class="form-group">
		<input name="excelfile" type="file"  class="form-control">
		<input type="submit" value="Process Excel2003" class="btn btn-primary">
		</div>
	</form:form>
	<hr>
	<form:form action="fileUpload" method="post" cssClass="form-inline" enctype="multipart/form-data">
	
		<label for="title" class="col-sm-2 control-label">Excel File 2007:</label>
		<div class="form-group">
		<input name="excelfile2007" type="file" class="form-control">		
		<input type="submit" value="Process Excel2007" class="btn btn-success">
		</div>
	</form:form>
	<hr>
	<form:form action="processAsync" method="post" cssClass="form-inline" enctype="multipart/form-data">
		<label for="title" class="col-sm-2 control-label">Process  Async:</label>
		<div class="form-group">
		<input name="file" type="file"  class="form-control">		
		<input type="submit" value="processAsync" class="btn btn-info ">
		</div>
	</form:form>
	<hr>
	<h3>Users List</h3>
	<c:if test="${!empty lstUser}">
		<table class="tg table table-bordered">
			<tr>
				<th width="80">User ID</th>
				<th width="120">UserName</th>
				<th width="120">Input Date</th>
			</tr>
			<c:forEach items="${lstUser}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.username}</td>
					<td>${user.inputDate}</td>				
				</tr>
			</c:forEach>
		</table>
	</c:if>
	</div>
</body>
</html>