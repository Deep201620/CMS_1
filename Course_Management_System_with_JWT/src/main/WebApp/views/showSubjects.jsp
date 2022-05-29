<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

</head>
<body>
	<div class="container">
		<h1>Subjects</h1>
		<hr></hr>
		<div class="form-group row">
			<div class="col-sm-7">
				<ol>
					<c:forEach var="subject" items="${subjects}">
						<li>${subject.subjectName}
					
					<a href="/delSubject/${subject.subjectId}"
						class="btn btn-sm btn-outline-danger">Delete Subject</a>
						</li>
						</c:forEach>
				</ol>
			</div>
		</div>
	</div>
</body>
</html>