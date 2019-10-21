<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<link href="<c:url value='/resources/css/boostrapcss/bootstrap.min.css'/>" rel="stylesheet" id="bootstrap-css">
<script src="<c:url value='/resources/js/bootstrapjs/bootstrap.min.js'/>"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<link href="<c:url value='/resources/css/home.css' />" rel="stylesheet">
</head>
<body>
<h1 style="text-align: center;color: red;">Home User</h1>

<security:authorize access="isAuthenticated()">
 <security:authentication property="principal" var="myUserDetail"/>
 ${myUserDetail.fullName }
 <button id="btnBack2" onclick="window.location.href='<c:url value="/logout" />'"  type="button" class="btn btn-danger">Logout</button>
</security:authorize>	

<%-- <h1>${userName}</h1> --%>

<security:authorize access="!isAuthenticated()">
<button id="btnBack" onclick="window.location.href='<c:url value="/login" />'"  type="button" class="btn btn-danger">Login</button> 
</security:authorize>
   
</body>
</html>
