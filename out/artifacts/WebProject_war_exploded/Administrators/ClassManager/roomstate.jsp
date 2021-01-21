<%--
  Created by IntelliJ IDEA.
  User: 17956
  Date: 2019/9/9
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <script src="../../js/jquery-2.1.0.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <title>教室状态</title>
    <style>
        .have {
            height: 100%;
            width: 100%;
            background: yellow;
        }

        .no {
            height: 100%;
            width: 100%;
            background: red;
        }

        .cen {
            text-align: center;
            line-height: 45px;
        }

    </style>
</head>
<body>
<div class="container" style="margin: auto;width: 900px;height: 680px;"><%--width: 900px;height: 680px--%>
    <h3 style="text-align: center">教室信息列表</h3>
    <div style="float:left;">
        <form class="form-inline" action="${pageContext.request.contextPath}/FindClassroomServlet" method="post">

            <div class="form-group">
                <label for="exampleInputName3">教室号</label>
                <input type="text" name="place" value="${condition.username[0]}" class="form-control"
                       id="exampleInputName3">
            </div>
            <div class="form-group">
                <label for="week">星期</label>
                <%--<input type="text" name="username" value="${condition.username[0]}" class="form-control"--%>
                       <%--id="exampleInputName4">--%>
                <select  class="form-control" id="week" name="week">
                    <option value="周一">周一</option>
                    <option value="周二">周二</option>
                    <option value="周三" selected>周三</option>
                    <option value="周四">周四</option>
                    <option value="周五">周五</option>
                    <option value="周六">周六</option>
                </select>
            </div>
            <div class="form-group">
                <label for="time">上课时间</label>
                <select  class="form-control" id="time" name="time">
                    <option value="上午一二节">上午一二节</option>
                    <option value="上午三四节">上午三四节</option>
                    <option value="下午一二节"selected>下午一二节</option>
                    <option value="下午三四节">下午三四节</option>
                </select>
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <div style="float: right;margin: 5px;">
        <%--<a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>--%>
    </div>
    <div style="margin-top: 100px;border: 1px solid blue;height: 430px;width: 850px">
        <c:forEach items="${room}" var="room">
            <c:if test="${room.state==0}">
            <div style="height: 110px;width: 100px;border: 1px solid red;float: left;margin: 20px;padding: 0px;text-align: center;line-height: 100px;">${room.place}
                <span style="/*width: 100%;height: 100%;*/border: 1px solid red;line-height: 0px">空位</span>
            </div>
            </c:if>
            <c:if test="${room.state==1}">
                <div style="height: 110px;width: 100px;border: 1px solid black;float: left;margin: 20px;padding: 0px;text-align: center;line-height: 100px;background-color: red;">${room.place}
                    <span style="/*width: 100%;height: 100%;*/border: 1px solid black;line-height: 0px">占用</span>
                </div>
            </c:if>
        </c:forEach>
    </div>
    <%--<div style="margin-top: 30px;">--%>
        <%--<nav aria-label="Page navigation">--%>
            <%--<ul class="pagination">--%>
                <%--<c:if test="${pb.currentPage ==1}">--%>
                <%--<li class="disabled">--%>
                    <%--</c:if>--%>

                    <%--<c:if test="${pb.currentPage !=1}">--%>
                <%--<li>--%>
                    <%--</c:if>--%>
                    <%--<a href="${pageContext.request.contextPath}/FindStudentByPageServlet?currentPage=${pb.currentPage-1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email&=${condition.email[0]}"--%>
                       <%--aria-label="Previous">--%>
                        <%--<span aria-hidden="true">&laquo;</span>--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<c:forEach begin="1" end="${pb.totalPage}" var="i">--%>
                    <%--<c:if test="${pb.currentPage == i}">--%>
                        <%--<li class="active"><a--%>
                                <%--href="${pageContext.request.contextPath}/FindStudentByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email&=${condition.email[0]}">${i}</a>--%>
                        <%--</li>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${pb.currentPage != i}">--%>
                        <%--<li>--%>
                            <%--<a href="${pageContext.request.contextPath}/FindStudentByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email&=${condition.email[0]}">${i}</a>--%>
                        <%--</li>--%>
                    <%--</c:if>--%>
                <%--</c:forEach>--%>
                <%--<li>--%>
                    <%--<a href="${pageContext.request.contextPath}/FindStudentByPageServlet?currentPage=${pb.currentPage+1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email&=${condition.email[0]}"--%>
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
