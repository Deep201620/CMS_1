<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


</head>
<body>
 <div class="container">
  <h2>Create New User</h2>
  <div class="card">
   <div class="card-body">
    <form action="/saveUser" method="post">

     <div class="form-group row">
      <label for="firstName" class="col-sm-2 col-form-label">First
       Name</label>
      <div class="col-sm-7">
       <input type="text" class="form-control" name="firstName"
        placeholder="Enter first name">
      </div>
     </div>

     <div class="form-group row">
      <label for="lastName" class="col-sm-2 col-form-label">Last
       Name</label>
      <div class="col-sm-7">
       <input type="text" class="form-control" name="lastName"
        placeholder="Enter last name">
      </div>
     </div>

     <div class=" form-group row">
      <label for="userName" class="col-sm-2 col-form-label">User
       Name</label>
      <div class="col-sm-7">
       <input type="text" class="form-control" name="userName"
        placeholder="Enter user name">
      </div>
     </div>

     <div class="form-group row">
      <label for="userEmail" class="col-sm-2 col-form-label">Email</label>
      <div class="col-sm-7">
       <input type="email" class="form-control" name="userEmail"
        placeholder="Enter email">
      </div>
     </div>

     <div class="form-group row">
      <label for="userPassword" class="col-sm-2 col-form-label">Password</label>
      <div class="col-sm-7">
       <input type="password" class="form-control" name="userPassword"
        placeholder="Enter Password">
      </div>
     </div>

	<div class="form-group row">
	<label for="userRole" class="col-sm-2 col-form-label">Role</label>
      <div class="col-sm-7">
      <select name="userRole">
      <c:forEach var="role" items="${roles}">
      <option value="${role.roleName}">${role.roleName}</option>
	</c:forEach>
      </select>
      </div>
	</div>

     <button type="submit" class="btn btn-primary">Submit</button>
    </form>
   </div>
  </div>
 </div>
</body>
</html>
