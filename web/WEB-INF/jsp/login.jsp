<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Online Employment Platform</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<link href='http://fonts.googleapis.com/css?family=Belgrano' rel='stylesheet' type='text/css'>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
 
	<form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
		


	<div class="loginheader">
    <div class="logintitle"><a href="#">Online Employment Platform</a></div>
    </div>

<div id="loginpanelwrap">
    <div class="loginform">
	<form name='f' action="<c:url value='j_spring_security_check' />" method="post">
        <div class="loginform_row">
        <label>Username:</label>
        <input type="text" class="loginform_input" name="j_username" />
        </div>
        <div class="loginform_row">
        <label>Password:</label>
        <input type="password" class="loginform_input" name="j_password" />
        </div>
        <div class="loginform_row">
        <input type="submit" class="loginform_submit" value="Login" />
        </div> 
        <div class="clear"></div>
	</form>
    </div>

</div>
      	
</body>

</html>