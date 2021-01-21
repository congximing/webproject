    <%--
  Created by IntelliJ IDEA.
  User: 17956
  Date: 2019/8/31
  Time: 14:42
  To change this template use File | Settings | File Templates.


  课程操作
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
    <title>课程表</title>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function findCourse(number) {
            location.href="${pageContext.request.contextPath}/courseAndPlace?number="+number;
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">课程信息列表</h3>
    <div style="float:left">
        <form class="form-inline" action="${pageContext.request.contextPath}/FindSCourseByPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName1">课号</label>
                <input type="text" name="number" value="${condition.number[0]}" class="form-control"
                       id="exampleInputName1">
            </div>
            <div class="form-group">
                <label for="exampleInputName2">课名</label>
                <input type="text" name="coursename" value="${condition.coursename[0]}" class="form-control"
                       id="exampleInputName2">
            </div>
            <div class="form-group">
                <label for="exampleInputName3">学分</label>
                <input type="text" name="credit" value="${condition.credit[0]}" class="form-control"
                       id="exampleInputName3">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>

    </div>
  <div style="float: right;margin: 15px;">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/Administrators/CourseCRUD/addCourse.jsp">添加课程</a>

    </div>

    <form id="form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th>课号</th>
                <th>课名</th>
                <th>上课时间</th>
                <%--<th>地点</th>--%>
                <th>周数</th>
                <th>课程类型</th>
                <th>学分</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${pb.list}" var="user" varStatus="s">
                <tr>
                    <td><a href="javascript:findCourse('${user.number}');" >${user.number}</a></td>
                    <td>${user.coursename}</td>
                    <td>${user.time}</td>
                    <%--<td>${user.place}</td>--%>
                    <td>${user.weeks}</td>
                    <td>${user.type}</td>
                    <td>${user.credit}</td>
                    <td><a class="btn btn-default btn-sm"
                           href="javascript:findCourse('${user.number}');">安排教室</a>&nbsp;
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>

    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${pb.currentPage ==1}">
                <li class="disabled">
                    </c:if>

                    <c:if test="${pb.currentPage !=1}">
                <li>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/FindSCourseByPageServlet?currentPage=${pb.currentPage-1}&rows=5&number=${condition.number[0]}&coursename=${condition.coursename[0]}&credit&=${condition.credit[0]}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                    <c:if test="${pb.currentPage == i}">
                        <li class="active"><a
                                href="${pageContext.request.contextPath}/FindSCourseByPageServlet?currentPage=${i}&rows=5&number=${condition.number[0]}&coursename=${condition.coursename[0]}&credit&=${condition.credit[0]}">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${pb.currentPage != i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/FindSCourseByPageServlet?currentPage=${i}&rows=5&number=${condition.number[0]}&coursename=${condition.coursename[0]}&credit&=${condition.credit[0]}">${i}</a>
                        </li>
                    </c:if>
                </c:forEach>
                <li>
                    <a href="${pageContext.request.contextPath}/FindSCourseByPageServlet?currentPage=${pb.currentPage+1}&rows=5&number=${condition.number[0]}&coursename=${condition.coursename[0]}&credit&=${condition.credit[0]}"
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
