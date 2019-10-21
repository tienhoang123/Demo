<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url var="formUrl" value="/api/user"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Drop And Drag</title>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<link href="<c:url value='/resources/css/boostrapcss/bootstrap.min.css'/>" rel="stylesheet" id="bootstrap-css">
<script src="<c:url value='/resources/js/bootstrapjs/bootstrap.min.js'/>"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="<c:url value='/resources/css/home.css' />" rel="stylesheet">
<style type="text/css">
ol{
	list-style-type: none;
	width: 170px;
	height: 480px;
}
li{
	height: 30px;
	width: 110px;
	border: 1px solid black;
	margin: 2px;
	font-size: 20px;
	text-align: center;
	
}

#user li{
	background-color: pink;	
}
fieldset {
	float: left;
	margin: 30px;
	width: 200px;
	height: 500px;
	border: 5px solid black;
}

</style>
</head>
<body>
	<c:forEach items="${models}" var="item">
	<fieldset id="${item.id}" class=box>
		<legend><b>${item.name}</b></legend>
		<ol id="user">
			<c:forEach items="${item.userModels}" var="itemuser" >
			<li id = "${itemuser.id}">${itemuser.userName}</li>
			</c:forEach>
		</ol>
	</fieldset>
	</c:forEach>

</body>
</html>

<script type="text/javascript">
	$('.box li').draggable({
		helper: 'clone'
	});

	$(".box").droppable({
		drop: function(event,ui){
			var dataArray = {};
			var id = $(ui.draggable).attr('id');
			var textHtml = $(ui.draggable).html();
			var box = $(this).attr('id');
			console.log(id);
			console.log(textHtml);
			console.log(textHtml);
			var roleUsers = [];
			roleUsers.push(box);
			dataArray["id"] = id;
			dataArray["roleUsers"] = roleUsers;
			 $.ajax({
		           url:  '${formUrl}/update-role/'+id,
		           type: 'POST',
		           dataType: 'json',
		           contentType:'application/json',
		           data: JSON.stringify(dataArray),
		           success: function(res) {
		        	   $(ui.draggable).remove();
		        	   $('#' + box).find('ol#user').append('<li id = "' + id + '">' + textHtml + '</li>');
		           },
		           error: function(res) {
		        	   $("#messageError").show();
		        	   setTimeout(function () {
		                   $("#messageError").hide();
		               },3000);
		           }
		       });

		}
	});
  
</script>