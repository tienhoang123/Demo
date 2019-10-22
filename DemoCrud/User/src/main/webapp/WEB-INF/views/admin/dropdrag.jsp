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
<%-- <script src="<c:url value='/resources/js/bootstrapjs/bootstrap.min.js'/>"></script> --%>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="<c:url value='/resources/css/home.css' />" rel="stylesheet">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<style type="text/css">
ol{
	list-style-type: none;
	/* width: 170px; */
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
  		<div class="alert alert-success" id="messageSuccess" style="display: none">
        <strong>Success!</strong> Save Success!
    </div>
    <div class="alert alert-danger" id="messageError" style="display: none">
    <strong>Fail!</strong> Fail !
  </div>
	<c:forEach items="${models}" var="item">
	<fieldset id="${item.id}" class=box>
		<legend><b>${item.name}</b></legend>
		<ol id="user">
			<c:forEach items="${item.userModels}" var="itemuser" >
			<div id = "${itemuser.id}" style="display: flex;">
			<li id = "${itemuser.id}">${itemuser.userName}</li>		
			<div class="dropdown">
			  <button type="button" class="btn btn-primary dropdown-toggle" style="line-height: 1.0;" data-toggle="dropdown">
			   
			  </button>
			  <div class="dropdown-menu">
			    <a class="dropdown-item btnEdit" id="btnEdit" data-id="${itemuser.id}" href="#">Edit</a>
			    <a class="dropdown-item" href="#">Delete</a>
			    <a class="dropdown-item" href="#">Add</a>
			  </div>
			</div>	
			</div>	
			</c:forEach>
		</ol>
	</fieldset>
	</c:forEach>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">User</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form>
           <div class="form-group">
		      <label for="usr">Name:</label>
		      <input type="text" class="form-control" id="userName" name="userName" value="${model.userName}" >
		      <label style="color: red" id="userNameError" for="usr" ></label>
		    </div>
		    <div class="form-group">
		      <label for="pwd">Email:</label>
		      <input type="text" class="form-control" id="email" name="email" value = "${model.email}">
		       <label style="color: red" id="emailError" for="usr" ></label>
		    </div>
		     <div class="form-group">
		      <label for="pwd">Phone:</label>
		      <input type="text" class="form-control" id="phone" name="phone" value = "${model.phone}">
		       <label style="color: red" id="phoneError" for="usr" ></label>
		    </div>
            <div class="form-group">
		     <label for="pwd">Roles:</label>
		    <br>
			<c:forEach var="entry" items="${roles}">
				<input type="checkbox" name="role" value="${entry.key}"> ${entry.value}<br>
			</c:forEach>
		       <br>
		        <label style="color: red" id="roleUsersError" for="usr" ></label>
		    </div>
		     <input type="hidden" id="id" >
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" id="btnSave" class="btn btn-primary">Save</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>

<script type="text/javascript">

	$('.box div').draggable({
		helper: 'clone'
	});

	$(".box").droppable({
		drop: function(event,ui){
			var dataArray = {};
			var id = $(ui.draggable).attr('id');
			var textHtml = $(ui.draggable).find("li").text();
			var box = $(this).attr('id');
			console.log(id);
			console.log(textHtml);
			console.log(box);
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
		        	   var item = '';
		        	   item += '<div id = "' + id + ' " class="ui-draggable ui-draggable-handle" style="display: flex;" >';
		        	   item += '<li id = "' + id + '">' + textHtml + '</li>';
		        	   item += 	'<div class="dropdown">';
		        	   item += 	'<button type="button" class="btn btn-primary dropdown-toggle" style="line-height: 1.0;" data-toggle="dropdown">';					   
		        	   item += 	'</button>';
		        	   item += 	'<div class="dropdown-menu">';
		        	   item += 	'<a class="dropdown-item btnEdit" id="btnEdit" data-id="' + id + '" href="#">Edit</a>';
		        	   item += 	'<a class="dropdown-item" href="#">Delete</a>';
		        	   item += 	'<a class="dropdown-item" href="#">Add</a>';
		        	   item += 	'</div>';
		        	   item += 	'</div>	';
		        	   $('#' + box).find('ol#user').append(item);
		           		/* $('#' + box).find('ol#user').find('#' + id).draggable({
			           helper: 'clone'
				           }); */
		        	   
		           },
		       });

		}
	});

	$(document).on('click', '.btnEdit', function(e) {
		$('#exampleModal').modal('toggle');
		var id = $(this).attr('data-id');	   	
		 $.ajax({
	           url: '${formUrl}/get-data/'+id,
	           type: 'POST',
	           dataType: 'json',
	           contentType:'application/json',
	           success: function(res) {
				var checkboxs = document.querySelectorAll("input[type=checkbox]");		
				for (let index = 0; index < checkboxs.length; index++) {
					for (let i = 0; i < res.roleUsers.length; i++) {					   
						   if(checkboxs[index].value == res.roleUsers[i]){
						    	 checkboxs[index].checked = true;
							}else{
								 checkboxs[index].checked = false;
								}
						}				    
					}
			    $("#userName").val(res.userName);	
			    $("#email").val(res.email);	
			    $("#phone").val(res.phone);	
			    $("#id").val(id);

	            }            
	       });
	});

	$("#btnSave").click(function(e){
		 e.preventDefault();
		 var id = $("#id").val();
		 var dataArray = {};
		   $("#userNameError").text('');
		   $("#emailError").text('');
		   $("#phoneError").text('');	   
		   $("#roleUsersError").text('');
		   var roleUsers = $('input[type=checkbox]:checked').map(function () {
	           return $(this).val();
	       }).get();
	       	   
		   dataArray["userName"] = $("#userName").val();
		   dataArray["email"] = $("#email").val();
		   dataArray["phone"] = $("#phone").val();    
		   dataArray["roleUsers"] = roleUsers;
		   console.log(dataArray);
		  updateUser(dataArray,$("#id").val());
		
	});

	 function updateUser(data, id) {
	       $.ajax({
	           url: '${formUrl}/update/'+id,
	           type: 'POST',
	           dataType: 'json',
	           contentType:'application/json',
	           data: JSON.stringify(data),
	           success: function(res) {
	        	   if(res.length != undefined){
	        		   validateInput(res);
	            	   }else{
	              		 $("#messageSuccess").show();
	              	  		 setTimeout(function () {
	                         $("#messageSuccess").hide();
	                   },3000);
	               }             },
	           error: function(res) {
	        	   $("#messageError").show();
	        	   setTimeout(function () {
	                   $("#messageError").hide();
	               },3000);
	           }
	       });


	   }
	  
	 function validateInput(data){
		   $('#' + data[0].field).focus();
			for(var i = 0; i < data.length; i++){
				$('#' + data[i].field + 'Error').text(data[i].code);
				
			}	
			
	  }
</script>