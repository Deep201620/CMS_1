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
  <h1>Add Subject</h1>
  <div class="card">
   <div class="card-body">
    <form method="post" action="/addSubject">
     <div class="form-group row">
      <label for="subjectName" class="col-sm-2 col-form-label">SubjectName</label>
      <div class="col-sm-7">
       <input type="text" class="form-control" name="subjectName"
        placeholder="Enter Subject Name">	
      </div>
     </div>

     <button type="submit" class="btn btn-primary">Add Subject</button>
    </form>
   </div>
  </div>
 </div>
</body>
</html>