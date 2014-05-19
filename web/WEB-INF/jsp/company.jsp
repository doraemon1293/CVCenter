<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Compnay</title>
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
                <div class="title">Your jobs</div>
                <div class="header_right">Welcome ${company.getCompanyName()}, <a href="companyeditprofile.htm" class="Profile">Profile</a> <a href="<c:url value="/j_spring_security_logout"/>"  class="logout">Logout</a> </div>
                <div class="menu">
                    <ul>
                        <li><a href="companyjob.htm" class="selected">Job List</a></li>
                        <li><a href="companyUpload.htm?id=0">add a job</a></li>
                    </ul>
                </div>
            </div>
            <div class="center_content">
                <div id="right_wrap">

                    <table id="rounded-corner">
                        <thead>
                            <tr>
                                <th onclick="sortTable('rounded-corner', 0);" style="cursor:pointer">Job title</th>
                                <th onclick="sortTable('rounded-corner', 1);" style="cursor:pointer">Deadline</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="job" items="${joblist}">
                                <tr class="odd">
                                    <td>${job.getTitle()}</td>
                                    <td>${job.getDeadline()}</td>
                                    <td>
                                        <a href="companyUpload.htm?id=${job.getJobId()}">Edit details</a>
                                    </td>
                                    <td>
                                        <table>
                                            <c:forEach var="cv" items="${job.getCvs()}">
                                                <tr>
                                                    <a href="downloadCV.htm?cvId=${cv.getCvId()}">${cv.getFilename()}</a>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </td>
                                    <td>
                                        <a href="companyDeleteJob.htm?jobId=${job.getJobId()}"><img src="images/trash.gif" alt="delete" title="" border="0" width="20" height="20"/></a>
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
