<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Company Profile</title>
		<link rel="stylesheet" href="css/screen.css" media="screen" />
		
    </head>
    <body>

<div id="container">		
        <form:form id="form2" modelAttribute="company" method="post">
			<h3><span>Edit profile</span></h3>
	<fieldset>
                <form:hidden path="companyId"/>
  		<p class="first">
                       <label>Company Name</label>
                       <form:input path="companyName"/>
 		</p>
 		<p>
                       <label>Address</label>
		       <form:input path="address"/>
		</p>
		<p>
			<label>Email</label>
                        <form:input path="email"/>
 		</p>
                <p>     
			<label>Tel No.</label>
                        <form:input path="tel"/>
		</p>
                <p>     <lanel>Contact Point</label>
                        <form:input path="contactName"/>
		</p>
   				<p><input type="submit" value="Save">
				   <input type="reset" value="Reset">
				</p>
	</fieldset>
        </form:form>
        <br>
        <a href="companyjob.htm">Cancel </a>
	</div>
    </body>
</html>
