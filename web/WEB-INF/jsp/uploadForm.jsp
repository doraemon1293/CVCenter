<%-- 
    Document   : uploadForm
    Created on : 16-Dec-2012, 22:18:37
    Author     : Giang Tran Hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CV upload form </title>     
		<link rel="stylesheet" href="css/screen.css" media="screen" />
    </head>
    <body>
	<div id="container">
        <form:form id="form2" modelAttribute="fileHolder" method="post" enctype="multipart/form-data">
            <fieldset>
                <legend>Upload Fields</legend>
 
                <p><form:hidden path="id" /><p>
                <p>
                    <form:label for="jobCat" path="jobCat">Job Category</form:label><br/>
                    <form:input path="jobCat"/>
                </p>
 
                <p>
                    <form:label for="file" path="file">CV file</form:label><br/>
                    <form:input path="file" type="file"/>
                </p>
 
                <p>
                    <input type="submit" />  <a href="studentCv.htm"> Cancel </a>
                </p>
 
            </fieldset>
        </form:form>     
		</div>  
    </body>
</html>
