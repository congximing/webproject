<%--
  Created by IntelliJ IDEA.
  User: 17956
  Date: 2019/8/31
  Time: 14:42
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
    <%--搜索式下拉框--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>

    <title>课程表</title>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">课程信息列表</h3>
    <div style="float:left">
        <form class="form-inline" action="${pageContext.request.contextPath}/AddClassroomServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName1" >课号</label>
                <select type="text" name="number" value="${one.number}" class="form-control" style="width: 180px;"
                       id="exampleInputName1">
                    <option>${one.number}</option>
                </select>
            </div>
            <div class="form-group">
                <label for="exampleInputName2">课名</label>
                <select type="text" name="coursename" value="${one.coursename}" class="form-control" style="width: 180px;"
                       id="exampleInputName2">
                    <option>${one.coursename}</option>
                </select>
            </div><div class="form-group">
            <label for="exampleInputName4">上课时间</label>
            <select type="text" name="coursename" value="${one.time}" class="form-control" style="width: 180px;margin-right: 0px"
                    id="exampleInputName4">
                <option>${one.time}</option>
            </select>
        </div>
            <div class="form-group">
                <label for="exampleInputName3" style="float: left;line-height: 42px;margin-right: 3px">教室</label>
                <div style="float: left;margin-top: 7px">
                <select type="text" name="place" value="${one.place}" class="form-control selectpicker show-tick" data-live-search="true" style="width: 180px;;"
                       id="exampleInputName3">
                    <c:if test="${ one.place != null}">
                        <option>${one.place}</option>
                    </c:if>

                    <c:if test="${one.place == null}">
                        <c:forEach items="${room}" var="r">
                            <c:if test="${r.state == 0}">
                                <option>${r.place}</option>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </select>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">添加教室</button>
        </form>

    </div>
    <div style="float: right;margin: 5px;">
        <%--<a class="btn btn-primary" href="/Administrators/CourseCRUD/addCourse.jsp">添加教室</a>--%>

    </div>

    <form id="form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th>课号</th>
                <th>课名</th>
                <th>上课时间</th>
                <th>地点</th>
                <th>周数</th>
                <th>课程类型</th>
                <th>学分</th>
            </tr>

            <%--<c:forEach items="${one}" var="user" varStatus="s">--%>
                <tr>
                    <td>${one.number}</td>
                    <td>${one.coursename}</td>
                    <td>${one.time}</td>
                    <td>${one.place}</td>
                    <td>${one.weeks}</td>
                    <td>${one.type}</td>
                    <td>${one.credit}</td>
                    <%--<td><a class="btn btn-default btn-sm"--%>
                           <%--href="${pageContext.request.contextPath}/AlterCourseServlet?number=${one.number}">修改</a>&nbsp;--%>
                <%--</tr>--%>
            <%--</c:forEach>--%>
        </table>
    </form>

    <%--<div>--%>
        <%--<nav aria-label="Page navigation">--%>
            <%--<ul class="pagination">--%>
                <%--<c:if test="${pb.currentPage ==1}">--%>
                <%--<li class="disabled">--%>
                    <%--</c:if>--%>

                    <%--<c:if test="${pb.currentPage !=1}">--%>
                <%--<li>--%>
                    <%--</c:if>--%>
                    <%--<a href="${pageContext.request.contextPath}/FindSCourseByPageServlet?currentPage=${pb.currentPage-1}&rows=5&number=${condition.number[0]}&coursename=${condition.coursename[0]}&credit&=${condition.credit[0]}"--%>
                       <%--aria-label="Previous">--%>
                        <%--<span aria-hidden="true">&laquo;</span>--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<c:forEach begin="1" end="${pb.totalPage}" var="i">--%>
                    <%--<c:if test="${pb.currentPage == i}">--%>
                        <%--<li class="active"><a--%>
                                <%--href="${pageContext.request.contextPath}/FindSCourseByPageServlet?currentPage=${i}&rows=5&number=${condition.number[0]}&coursename=${condition.coursename[0]}&credit&=${condition.credit[0]}">${i}</a>--%>
                        <%--</li>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${pb.currentPage != i}">--%>
                        <%--<li>--%>
                            <%--<a href="${pageContext.request.contextPath}/FindSCourseByPageServlet?currentPage=${i}&rows=5&number=${condition.number[0]}&coursename=${condition.coursename[0]}&credit&=${condition.credit[0]}">${i}</a>--%>
                        <%--</li>--%>
                    <%--</c:if>--%>
                <%--</c:forEach>--%>
                <%--<li>--%>
                    <%--<a href="${pageContext.request.contextPath}/FindSCourseByPageServlet?currentPage=${pb.currentPage+1}&rows=5&number=${condition.number[0]}&coursename=${condition.coursename[0]}&credit&=${condition.credit[0]}"--%>
                       <%--aria-label="Next">--%>
                        <%--<span aria-hidden="true">&raquo;</span>--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<span style="font-size: 25px;margin-left:5px;">共${pb.totalPage}页，共${pb.totalCount}条记录</span>--%>
            <%--</ul>--%>
        <%--</nav>--%>
    <%--</div>--%>
</div>
</body>
</html>
