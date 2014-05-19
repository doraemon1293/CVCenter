<%-- 
    Document   : editJob
    Created on : 18-Dec-2012, 07:42:14
    Author     : Ruoi Iu
--%>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Job</title>
        <link rel="stylesheet" href="css/screen.css" media="screen" />
    </head>
    <body>

        <div id="container">	
            <form:form id="form2" modelAttribute="fileHolder" method="post" enctype="multipart/form-data">
                <h3><span>Edit job</span></h3>
                <fieldset>
                    <form:hidden path="id"/>
                    <p>
                        <label>Title</label>
                        <form:input path="title"/>
                    </p>
                    <p>     
                        <label>Deadline(dd-mm-yyyy)</label>
                        <form:input path="date"/>
                    </p>
                    <p> 	<label>Job Category	</label>
                        <form:input path="jobCat"/>
                    </p>
                    <p>		<label>Description File</label>
                        <form:input path="file" type="file"/>
                    </p>
                    <p>     <input type="submit" value="Save"><input type="reset" value="Reset">
                    </p>			
                </fieldset>
            </form:form>
            <br>
            <a href="companyjob.htm">Cancel </a>
        </div>
    </body>
</html>
