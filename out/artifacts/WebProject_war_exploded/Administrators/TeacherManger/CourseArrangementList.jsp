<%--
  Created by IntelliJ IDEA.
  User: 17956
  Date: 2019/8/31
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%--给教师添加课程不能大于2--%>
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
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js" type="text/javascript" charset="UTF-8"></script>
    <title>教师课表安排</title>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
            window.onload = function () {
                document.getElementById("add").onclick = function (ev) {
                    document.getElementById("form").submit();
                }
            }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">教师课表</h3>
    <div style="float:left">
        <form class="form-inline" action="${pageContext.request.contextPath}/AddTeacherCourseServlet" method="post"><%--添加该课程 不能大于三 时间和地点不能同时重复--%>
            <div class="form-group">
                <label for="type">类别：</label>
                <select name="type" class="form-control" id="type" >
                    <option id="0">请选择</option>
                </select>
            </div>
            <div class="form-group">
                <input type="hidden" name="username" value="${username}">
                <label for="number">课程：</label>
                <select name="number" class="form-control" id="number">
                    <%--<c:forEach items="${course}" var="user">
                        &lt;%&ndash;<input type="hidden" name="number" value="${user.number}">&ndash;%&gt;
                        <option value="${user.number}">${user.coursename}</option>&lt;%&ndash;${user.number} ${user.coursename} ${user.time} ${user.place}&ndash;%&gt;
                    </c:forEach>--%>
                    <option id="0">请选择</option>
                </select>
            </div>

            <button type="submit" id="submit" onclick="mySubmit()" class="btn btn-default">添加</button>
        </form>

    </div>

    <form id="form" action="${pageContext.request.contextPath}/AddTeacherCourseServlet" method="post"><%--修改未完成--%>
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th>课程代号</th>
                <th>名字</th>
                <th>课名</th>
                <th>上课时间</th>
                <th>地点</th>
                <th>课程类型</th>
                <th>学分</th>
               <%-- <th>操作</th>--%>
            </tr>

            <c:forEach items="${pb.list}" var="user" varStatus="s">
                <input type="hidden" name="username" value="${user.username}">
                <tr>
                    <td>${user.number}</td>
                    <td>${user.name}</td>
                    <td>${user.coursename}</td>
                    <td>${user.time}</td>
                    <td>${user.place}</td>
                    <td>${user.type}</td>
                    <td>${user.credit}</td>
                    <%--<td>
                        <a class="btn btn-default btn-sm" href="javascript:deleteUser('${user.number}','${user.username}');">删除</a></td>--%>
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
                    <a href="${pageContext.request.contextPath}/FindTeacherCourseByPageServlet?currentPage=${pb.currentPage-1}&rows=5&name=${condition.name[0]}&major=${condition.major[0]}&coursename&=${condition.coursename[0]}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                    <c:if test="${pb.currentPage == i}">
                        <li class="active"><a
                                href="${pageContext.request.contextPath}/FindTeacherCourseByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&major=${condition.major[0]}&coursename&=${condition.coursename[0]}">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${pb.currentPage != i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/FindTeacherCourseByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&major=${condition.major[0]}&coursename&=${condition.coursename[0]}">${i}</a>
                        </li>
                    </c:if>
                </c:forEach>
                <li>
                    <a href="${pageContext.request.contextPath}/FindTeacherCourseByPageServlet?currentPage=${pb.currentPage+1}&rows=5&name=${condition.name[0]}&major=${condition.major[0]}&coursename&=${condition.coursename[0]}"
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
<script>
    $(function () {
       $.ajax({
           url:"${pageContext.request.contextPath}/findSubjectType",
           data:{"method":"findType"},
           type:"post",
           dataType:"json",
           success:function (list) {
               for (var i = 0; i < list.length; i++) {
                   var $op=$("<option></option>");
                   $op.text(list[i].name);
                   $op.attr("id",list[i].type);
                   $("#type").append($op);//加了child？？？？？？
               }
           }
       });

       
    });
    $("#type").change(function () {
        $("#number").empty();
        var id = $("#type option:selected").attr("id");
        if (id == 0) {
            $("#number").append("<option></option>");
            return;
        }
        $.ajax({
            url:"${pageContext.request.contextPath}/findSubjectType",
            data:{"method":"findCur","param":id},//查找对应的课程
            type:"post",
            dataType:"json",
            success:function (list) {
                for (var i = 0; i < list.length; i++) {
                    var $op=$("<option></option>");
                    $op.text(list[i].coursename);
                    $op.attr("value",list[i].number);
                    $op.attr("id",list[i].number);
                    $("#number").append($op);
                }
            }
        });
    });
    
    function mySubmit() {
        var $i=$("#number option:selected").attr("id");
            $.ajax({
                //几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "/findSubjectType" ,//url
                data: {
                    "method":"add",
                    "number": $i
                },
                success: function (list) {
                    //alert("hello world");
                    //console.log(list);//打印服务端返回的数据(调试用)
                    if (list.state == 200) {
                        alert("添加成功");
                    }else {
                        alert("你太帅了，一添加就添加失败了！");
                    }
                }
            });
    }
</script>
</html>
