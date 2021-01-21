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
    <title>课程表</title>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function deleteUser(number, username) {
            if (confirm("确定删除？")) {
                location.href = "${pageContext.request.contextPath}/DeleteStudentCourseInAdministrators?number="+number+"&username="+username;

            }
        }

        window.onload = function () {
            document.getElementById("delSelected").onclick = function () {
                if (confirm("确定删除")) {
                    document.getElementById("form").submit();
                }
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">学生已选课程列表</h3>
    <div style="float:left">
        <form class="form-inline" action="${pageContext.request.contextPath}/FindStudentCourseByPageServlet"
              method="post">
            <div class="form-group">
                <label for="exampleInputName1">学号</label>
                <input type="text" name="username" value="${condition.username[0]}" class="form-control"
                       id="exampleInputName1">
            </div>
            <div class="form-group">
                <label for="exampleInputName2">名字</label>
                <input type="text" name="name" value="${condition.name[0]}" class="form-control"
                       id="exampleInputName2">
            </div>
            <div class="form-group">
                <label for="exampleInputName3">专业</label>
                <input type="text" name="major" value="${condition.major[0]}" class="form-control"
                       id="exampleInputName3">
            </div>
            <div class="form-group">
                <label for="exampleInputName4">课名</label><%--<label>中的for 和 <input>的id 对应 --%>
                <input type="text" name="coursename" value="${condition.coursename[0]}" class="form-control"
                       id="exampleInputName4">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>

    </div>
    <div style="float: right;margin:25px;">
        <a class="btn " href="javascript:void(0);" id="delSelected"></a>
    </div>

    <form id="form" action="${pageContext.request.contextPath}/DeleteStudentCourseInAdministrators" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <%--<th><input type="checkbox" id="firstCb"></th>--%>
                <th>学号</th>
                <th>名字</th>
                <th>课名</th>
                <th>上课时间</th>
                <th>地点</th>
                <th>课程类型</th>
                <th>学分</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${pb.list}" var="user" varStatus="s">
                <tr>
                    <input type="hidden" name="username" value="${user.username}">
                    <%--<th><input type="checkbox" name="number" value="${user.number}"></th>--%>
                    <td>${user.username}</td>
                    <td>${user.name}</td>
                    <td>${user.coursename}</td>
                    <td>${user.time}</td>
                    <td>${user.place}</td>
                    <td>${user.type}</td>
                    <td>${user.credit}</td>
                    <td>
                        <a class="btn btn-default btn-sm" href="javascript:deleteUser('${user.number}','${user.username}');">删除</a>
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
                    <a href="${pageContext.request.contextPath}/FindStudentCourseByPageServlet?currentPage=${pb.currentPage-1}&rows=5&username=${condition.username[0]}&name=${condition.name[0]}&major=${condition.major[0]}&coursename&=${condition.coursename[0]}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                    <c:if test="${pb.currentPage == i}">
                        <li class="active"><a
                                href="${pageContext.request.contextPath}/FindStudentCourseByPageServlet?currentPage=${i}&rows=5&username=${condition.username[0]}&name=${condition.name[0]}&major=${condition.major[0]}&coursename&=${condition.coursename[0]}">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${pb.currentPage != i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/FindStudentCourseByPageServlet?currentPage=${i}&rows=5&username=${condition.username[0]}&name=${condition.name[0]}&major=${condition.major[0]}&coursename&=${condition.coursename[0]}">${i}</a>
                        </li>
                    </c:if>
                </c:forEach>
                <li>
                    <a href="${pageContext.request.contextPath}/FindStudentCourseByPageServlet?currentPage=${pb.currentPage+1}&rows=5&username=${condition.username[0]}&name=${condition.name[0]}&major=${condition.major[0]}&coursename&=${condition.coursename[0]}"
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
