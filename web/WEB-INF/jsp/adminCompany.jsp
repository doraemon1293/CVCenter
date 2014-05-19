<%-- 
    Document   : adminCompany
    Created on : 18-Dec-2012, 01:37:41
    Author     : Giang Tran Hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Admin</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
        <link href='http://fonts.googleapis.com/css?family=Belgrano' rel='stylesheet' type='text/css'>
            <!-- jQuery file -->
            <script src="js/jquery.min.js"></script>
            <script src="js/jquery.tabify.js" type="text/javascript" charset="utf-8"></script>
            <script language="javascript" src="js/sort.js"></script>
            <script type="text/javascript">
                var $ = jQuery.noConflict();
                $(function() {
                    $('#tabsmenu').tabify();
                    $(".toggle_container").hide(); 
                    $(".trigger").click(function(){
                        $(this).toggleClass("active").next().slideToggle("slow");
                        return false;
                    });
                });
            </script>

    </head>
    <body>
        <div id="panelwrap">
            <div class="header">
                <div class="title">Companies</div>
                <div class="header_right">Welcome administrator, <a href="<c:url value="/j_spring_security_logout" />"  class="logout">Logout</a> </div>
                <div class="menu">
                    <ul>
                        <li><a href="adminStudent.htm">Students</a></li>
                        <li><a href="adminCompany.htm" class="selected">Companies</a></li>
                    </ul>
                </div>
            </div>
            <div class="center_content">
                <div id="right_wrap">

                    <table id="rounded-corner">
                        <thead>
                            <tr>
                                <th onclick="sortTable('rounded-corner', 0);" style="cursor:pointer">Company Name</th>
                                <th>Username</th>
                                <th>operation</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="company" items="${companyList}">
                                <tr class="odd">
                                    <td>${company.getCompanyName()}</td>
                                    <td>${company.getUsers().getUsername()}</td>
                                    <td>
                                        <a href="adminChangeStatusCompany.htm?id=${company.getCompanyId()}"><img src="images/edit.png" alt="edit" title="" border="0" width="20" height="20"/></a>
                                        <a href="adminDeleteCompany.htm?id=${company.getCompanyId()}"><img src="images/trash.gif" alt="delete" title="" border="0" width="20" height="20"/></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
                </div>


                <div class="clear"></div>
            </div>

        </div>
    </body>
</html>