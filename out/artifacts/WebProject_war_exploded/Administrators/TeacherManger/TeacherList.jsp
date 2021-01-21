<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>教师信息表</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="../../js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="../../js/bootstrap.min.js"></script>

    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function deleteUser(username) {
            if (confirm("确定授课？")) {
                location.href = "${pageContext.request.contextPath}/delUserServlet?username=" + username;
            }
        }

        function findUser(username){
            location.href = "${pageContext.request.contextPath}/FindTeacher?username="+username;
        }
        //多选删除
        window.onload = function () {
            document.getElementById("delSelected").onclick = function () {
                if (confirm("确定删除吗？")) {
                    document.getElementById("form").submit();//如何避免空指针异常？
                }
            }

            document.getElementById("firstCb").onclick = function () {
                //获取所有的uid
                var cbs = document.getElementsByName("uid");
                for (var i = 0; i < cbs.length; i++) {
                    cbs[i].checked = this.checked;
                }
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">教师信息表</h3>
    <div style="float:left">
        <form class="form-inline" action="${pageContext.request.contextPath}/FindTeacherByPageServlet" method="post">
            <div class="form-group">
                <label for="name">姓名</label>
                <input type="text" name="name" value="${condition.name[0]}" class="form-control" id="name">
            </div>
            <div class="form-group">
                <label for="username">学号</label>
                <input type="text" name="username" value="${condition.username[0]}" class="form-control"
                       id="username">
            </div>
            <div class="form-group">
                <label for="major">专业</label>
                <input type="text" name="major" value="${condition.email[0]}" class="form-control"
                       id="major" >
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <br/>
   <%-- --%><div style="float: right;margin: 15px;">
        <a class="btn " href="javascript:void(0);"></a>
    <%-- <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>--%>
    </div>

    <form id="form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <%--<th><input type="checkbox" id="firstCb"></th>--%>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>学号</th>
                <th>班级</th>
                <th>专业</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${pb.list}" var="user" varStatus="s">
                <tr>
                    <%--<th><input type="checkbox" name="uid" value="${user.username}"></th>--%>
                    <td>${s.count}</td>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td><a href="javascript:findUser(${user.username});">${user.username}</a></td><%--Administrators/TeacherManger/ArrangementOfLectures.jsp--%>
                    <td>${user.grade}</td>
                    <td>${user.major}</td>
                     <td><a class="btn btn-default btn-sm" href="javascript:findUser(${user.username});">授课</a></td>

                    <%--<td><a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/findUserServlet?id=${user.username}">修改</a>&nbsp;&lt;%&ndash;修改操作&ndash;%&gt;
                        <a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.username});">删除</a></td>&lt;%&ndash;删除操作&ndash;%&gt;--%>
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
                    <a href="${pageContext.request.contextPath}/FindTeacherByPageServlet?currentPage=${pb.currentPage-1}&rows=5&name=${condition.name[0]}&username=${condition.username[0]}&major&=${condition.major[0]}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                    <c:if test="${pb.currentPage == i}">
                        <li class="active"><a
                                href="${pageContext.request.contextPath}/FindTeacherByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&username=${condition.username[0]}&major&=${condition.major[0]}">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${pb.currentPage != i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/FindTeacherByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&username=${condition.username[0]}&major&=${condition.major[0]}">${i}</a>
                        </li>
                    </c:if>
                </c:forEach>
                <li>
                    <a href="${pageContext.request.contextPath}/FindTeacherByPageServlet?currentPage=${pb.currentPage+1}&rows=5&name=${condition.name[0]}&username=${condition.username[0]}&major&=${condition.major[0]}"
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
