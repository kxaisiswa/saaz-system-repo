<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Staff Login | SAAZ Student Management System</title>
    <link rel="icon" href="assets/img/SAAZ_logo.png">
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <link href="style.css" rel="stylesheet">
</head>
<body class="text-center bg-light">
	<main class="container">
        <div class="form-signin">
            <input type="button" value="Student" class="inactive" onclick="window.location.href='index.jsp'"><input type="button" value="Staff">
            <form action="LoginServlet" method="post">
	            <div class="row g-3 mt-1 mb-2">
	            	<div class="col-5 text-end">
	            		<img src="assets/img/SAAZ_logo.png" width="80" height="80" />
	            	</div>
	            	<div class="col-7">
	            		<h1 class="h5 text-start">SAAZ Student hehe<br>Management<br>System</h1>
					</div>
	            </div>
                <h1 class="h3 mb-3 fw-bolder p-3">Sign in</h1>
                <c:if test="${status != null}">
					<div class="alert alert-danger" role="alert"><c:out value="${status}"></c:out></div>
				</c:if>
                <input type="hidden" name="usertype" value="Admin">
                <div class="form-floating">
                    <input type="text" name="userid" class="form-control" placeholder="ID number" required>
                    <label>ID number</label>
                </div>
                <div class="form-floating">
                    <input type="password" name="userpassword" class="form-control" placeholder="Password" required>
                    <label>Password</label>
                </div>
                <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">Sign in</button>
                <p class="mt-5 mb-3 text-muted">&copy; 2017 - 2021</p>
            </form>
        </div>
    </main>
</body>
</html>
