<%--
  Created by IntelliJ IDEA.
  User: 17956
  Date: 2019/9/3
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--分页的图标--%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <script src="../../js/jquery-2.1.0.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <title>查看学生选课</title>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>

</head>
<body>
<div class="container" style="margin-top: 50px">
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>学号</th>
            <th>名字</th>
            <th>课名</th>
            <th>上课时间</th>
            <th>地点</th>
            <th>课程类型</th>
            <th>学分</th>
        </tr>

        <c:forEach items="${pb.list}" var="user" varStatus="s">
            <tr>
                <td>${user.username}</td>
                <td>${user.name}</td>
                <td>${user.coursename}</td>
                <td>${user.time}</td>
                <td>${user.place}</td>
                <td>${user.type}</td>
                <td>${user.credit}</td>

        </c:forEach>
    </table>

<div>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${pb.currentPage == 1}">
            <li class="disabled">
                </c:if>

                <c:if test="${pb.currentPage != 1}">
            <li>
                </c:if>
                <a href="${pageContext.request.contextPath}/FindStudentNumberServlet?currentPage=${pb.currentPage-1}&rows=5&number=${number}&name=${condition.name[0]}&major=${condition.major[0]}&coursename&=${condition.coursename[0]}"
                   aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach begin="1" end="${pb.totalPage}" var="i">
                <c:if test="${pb.currentPage == i}">
                    <li class="active"><a
                            href="${pageContext.request.contextPath}/FindStudentNumberServlet?currentPage=${i}&rows=5&number=${number}&name=${condition.name[0]}&major=${condition.major[0]}&coursename&=${condition.coursename[0]}">${i}</a>
                    </li>
                </c:if>
                <c:if test="${pb.currentPage != i}">
                    <li>
                        <a href="${pageContext.request.contextPath}/FindStudentNumberServlet?currentPage=${i}&rows=5&number=${number}&name=${condition.name[0]}&major=${condition.major[0]}&coursename&=${condition.coursename[0]}">${i}</a>
                    </li>
                </c:if>
            </c:forEach>
            <li>
                <a href="${pageContext.request.contextPath}/FindStudentNumberServlet?currentPage=${pb.currentPage+1}&rows=5&number=${number}&name=${condition.name[0]}&major=${condition.major[0]}&coursename&=${condition.coursename[0]}"
                   aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            <span style="font-size: 25px;margin-left:5px;">共${pb.totalPage}页，共${pb.totalCount}条记录</span>
        </ul>
    </nav>
</div>
</div>
</body>
</html>
