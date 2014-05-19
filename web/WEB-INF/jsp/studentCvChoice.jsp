<%-- 
    Document   : studentCvChoice
    Created on : 18-Dec-2012, 12:51:18
    Author     : Giang Tran Hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CV Choice</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
        <link href='http://fonts.googleapis.com/css?family=Belgrano' rel='stylesheet' type='text/css'>
        <script language="javascript" src="js/sort.js"></script>
    </head>
    <body>
        <div class="center_content">
            Please choose one of the CVs above:
            <div id="right_wrap">
                <table id="rounded-corner">
                    <thead>
                        <tr>
                            <th onclick="sortTable('rounded-corner', 0);" style="cursor:pointer">CV name</th>
                        </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="cv" items="${CVList}">
                        <tr class="odd">
                            <td>
                                <a href="studentCvAndJob.htm?cvId=${cv.getCvId()}&jobId=${jobId}"> 
                                    ${cv.getFilename()}
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
            </div>
            <div class="clear"></div>
        </div>
    </body>
</html>
