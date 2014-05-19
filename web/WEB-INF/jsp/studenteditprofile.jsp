<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">             
		<title>Edit User</title>
		<link rel="stylesheet" href="css/screen.css" media="screen" />
		</head>
	<body>
		
		
<div id="container">		
            <form:form id="form2" modelAttribute="student" method="post">
			<h3><span>Edit profile</span></h3>
			<fieldset>
				<form:hidden path="studentId"/>
				<p class="first">
					<label>Firstename</label>
				 	<form:input path="firstName" size="30"/>
				</p>
				<p>
					<label>Lastname</label>
					<form:input path="lastName" size="30"/>
				</p>
				<p>
					<label>Email</label>
					<form:input path="email" size="30"/>
				</p>
				<p>
					<label>Tel No.</label>
					<form:input path="tel"/>
				</p>
				<p class="submit"><input type="submit" value="Save"><input type="reset" value="Reset"></p>
		</fieldset>
                </form:form>
		<br>
  		<a href="studentCv.htm">Back to your CV list. </a>
</div>
	</body>
</html>
