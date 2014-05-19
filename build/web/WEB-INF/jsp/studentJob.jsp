<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Student</title>
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
                <div class="title">Job List</div>
                <div class="header_right">Welcome ${student.getFirstName()}, <a href="studenteditprofile.htm"class="Profile">Profile</a> <a href="<c:url value="/j_spring_security_logout"/>" class="logout">Logout</a> </div>
                <div class="menu">
                    <ul>
                        <li><a href="studentCv.htm" >CV List</a></li>
                        <li><a href="studentBrowsejob.htm" class="selected">Jobs</a></li>
                        <li><a href="studentUpload.htm?id=0">Upload New CV</a></li>        
                    </ul>
                </div>
            </div>
            <div class="center_content">
                <div id="right_wrap">

                    <table id="rounded-corner">
                        <thead>
                            <tr>
                                <th onClick="sortTable('rounded-corner', 0);" style="cursor:pointer">Job Title</th>
                                <th onClick="sortTable('rounded-corner', 1);" style="cursor:pointer">Company</th>
                                <th onClick="sortTable('rounded-corner', 2);" style="cursor:pointer">Category</th>
                                <th onClick="sortTable('rounded-corner', 3);" style="cursor:pointer">Deadline</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="job" items="${jobList}">
                                <tr class="odd">
                                    <td>${job.getTitle()}</td>
                                    <td>${job.getCompany().getCompanyName()}</td>
                                    <td>${job.getJobCat()}</td>
                                    <td>${job.getDeadline()}</td>
                                    <td>
                                        <a href="downloadJob.htm?jobId=${job.getJobId()}">Description</a>
                                    </td>
                                    <td>
                                        <a href="studentApplyJob.htm?jobId=${job.getJobId()}">Apply</a>
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
