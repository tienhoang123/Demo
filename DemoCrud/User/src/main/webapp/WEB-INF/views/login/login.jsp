<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url var="login" value="/j_spring_security_check"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<link href="<c:url value='/resources/css/boostrapcss/bootstrap.min.css'/>" rel="stylesheet" id="bootstrap-css">
<script src="<c:url value='/resources/js/bootstrapjs/bootstrap.min.js'/>"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<link href="<c:url value='/resources/css/home.css' />" rel="stylesheet">
</head>
<body>
<div class="container">
	<c:if test="${error == 'true'}">
	<label style="color: red" id="userNameError" for="usr" >Login Fail !</label>
	</c:if>
	<c:if test="${error == '0'}">
	<label style="color: red" id="userNameError" for="usr" >Account Lock !</label>
	</c:if>
  	  <form action="${login}" method="POST">
    <div class="form-group">
      <label for="usr">UserName:</label>
      <input type="text" class="form-control" id="userName" name="userName" >
      
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>     
      <input type="password" class="form-control" id="password" name="password">
    </div>   
<!--         <div class="form-group">
      <label for="pwd">Remember Me?:</label>     
      <input type="checkbox" class="form-control" id="remember-me" name="remember-me">
    </div> -->
     <button id="Login"  type="submit" class="btn btn-primary">Login</button>  
     </form>  
</div>
</body>
</html>