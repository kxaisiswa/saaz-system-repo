<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	
	if(session.getAttribute("session_id") == null) {
		response.sendRedirect("Admin_login.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Staff | SAAZ Student Management System</title>
    <link rel="icon" href="assets/img/SAAZ_logo.png">
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <link href="sidebars.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.css">
    <style>
        table {
            background-color: #fff !important;
        }
        
        th {
            background-color: #ffc909 !important;
        }
    </style>
    <script>
        $(document).ready(function() {
            $('#table_id').DataTable();
        });
    </script>
</head>
<body class="bg-light">
    <div class="p-3 text-dark sidebar-style">
	<center><img src="assets/img/SAAZ_logo.png" alt="Logo Sekolah" style="width: 60%;"></center>
        <h1 class="py-2 d-flex align-items-center fw-bold text-dark fs-4">Staff</h1>
        <hr>
        <ul class="nav nav-pills flex-column mb-auto">
            <li class="nav-item">
                <a href="AdminRedirectServlet?action=student" class="nav-link text-dark">Student</a>
            </li>
            <li>
                <a href="AdminRedirectServlet?action=program" class="nav-link text-dark">Program</a>
            </li>
            <li>
                <a href="AdminRedirectServlet?action=class" class="nav-link text-dark">Class</a>
            </li>
            <li>
                <a href="AdminRedirectServlet?action=subject" class="nav-link text-dark">Subject</a>
            </li>
            <li>
                <a href="AdminRedirectServlet?action=classsubject" class="nav-link text-dark">Assign Subject</a>
            </li>
            <li>
                <a href="AdminRedirectServlet?action=admin" class="nav-link active">Staff</a>
            </li>
            <li>
                <a href="AdminRedirectServlet?action=account" class="nav-link text-dark">Account</a>
            </li>
            <li>
                <a href="AdminRedirectServlet?action=statistic" class="nav-link text-dark">Statistic</a>
            </li>
            <li>
				<a href="" data-bs-toggle="modal" data-bs-target="#exampleModal" class="nav-link text-dark">Logout</a>
            </li>
        </ul>
    </div>
    <div class="content">
        <button type="button" class="btn m-2" style="background-color: #e6e6e6">
            <img src="assets/img/arrow-left-short.svg" width="32" height="32" />
        </button>
        <div class="col-md-7 col-lg-8 container" style="max-width: 900px;">
            <h4 class="mb-3">Maklumat Staf</h4>
            <form action="AddAdminServlet" method="post" class="needs-validation" novalidate>
                <div class="row g-3">
                	<c:if test="${status != null}">
                		<c:choose>
							<c:when test="${status == 'New admin information added'}">
								<div class="alert alert-success text-center" role="alert"><c:out value="${status}"></c:out></div>
							</c:when>
							<c:otherwise>
								<div class="alert alert-danger text-center" role="alert"><c:out value="${status}"></c:out></div>
							</c:otherwise>
						</c:choose>
					</c:if>
                    <div class="col-6">
                        <label class="form-label">ID Staf</label>
                        <input type="text" class="form-control" name="adminid" placeholder="" value="" required>
                        <div class="invalid-feedback">
                            Sila isi ID staff dengan betul.
                        </div>
                    </div>
                    <div class="col-6">
                        <label class="form-label">Kata Laluan</label>
                        <input type="text" class="form-control" name="adminpassword" placeholder="" value="" required>
                        <div class="invalid-feedback">
                            Sila isi kata laluan dengan betul.
                        </div>
                    </div>
                    <div class="col-12">
                        <label class="form-label">Nama</label>
                        <input type="text" class="form-control" name="adminname" placeholder="" value="" required>
                        <div class="invalid-feedback">
                            Sila isi nama staf dengan betul.
                        </div>
                    </div>
                    <input type="hidden" name="superviseid" value="<c:out value="${session_id}"/>" required>
                    <hr class="my-5">
                    <button class="w-100 btn btn-primary btn-lg mb-5" type="submit">Submit</button>
                </div>
            </form>
        </div>
    </div>
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel">
    	<div class="modal-dialog modal-sm">
    		<div class="modal-content">
    			<div class="modal-header">
    				<h5 class="modal-title" id="exampleModalLabel">Logout</h5>
    				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    			</div>
    			<div class="modal-body d-flex justify-content-center">
    				<button type="button" class="btn btn-secondary mx-1" data-bs-dismiss="modal">Cancel</button>
    				<button onclick="window.location.href='LogoutServlet'" type="button" class="btn btn-primary mx-1">Confirm</button>
    			</div>
    		</div>
  		</div>
	</div>
    <script src="assets/js/bootstrap.js"></script>
    <script src="form-validation.js"></script>
</body>
</html>
