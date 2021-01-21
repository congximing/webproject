<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 17956
  Date: 2019/9/5
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <script src="../../js/jquery-2.1.0.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <title>学期课程表</title>
    <style type="text/css">
        td{
            float: left;
            margin-left: 0px;
            border: 3px solid gold;
            height: 90px;
            width: 123px;
        }
        tr{
            /*border: 1px solid red;*/
            text-align: center;
            line-height: 90px;
        }
    </style>
</head>
<body>
<h3 style="text-align: center">
    <c:if test="${t == 1}">大一上学期</c:if>
    <c:if test="${t == 2}">大一下学期</c:if>
    <c:if test="${t == 3}">大二上学期</c:if>
    <c:if test="${t == 4}">大二下学期</c:if>
    <c:if test="${t == 5}">大三上学期</c:if>
    <c:if test="${t == 6}">大三下学期</c:if>
    <c:if test="${t == 7}">大四上学期</c:if>
    <c:if test="${t == 8}">大四下学期</c:if>
    课程列表</h3>
<div class="container" style="height: 580px;width: 1150px;margin-left: 30px;margin-top: 35px">
    <div style="height: 700px;margin-left: 85px">
        <table style="width: 988px;border: 2px solid gold">

                <tr><td></td>
                    <td>周一</td>
                    <td>周二</td>
                    <td>周三</td>
                    <td>周四</td>
                    <td>周五</td>
                    <td>周六</td>
                    <td>周日</td>
                </tr>
                <tr>
                    <td>上午一二节</td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                            <c:if test="${term.time == '周一上午一二节'}">
                                ${term.coursename}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                            <c:if test="${term.time == '周二上午一二节'}">
                                ${term.coursename}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                            <c:if test="${term.time == '周三上午一二节'}">
                                ${term.coursename}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                            <c:if test="${term.time == '周四上午一二节'}">
                                ${term.coursename}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                            <c:if test="${term.time == '周五上午一二节'}">
                                ${term.coursename}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td> </td>
                    <td> </td>
                </tr>
                <tr><td>上午三四节</td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                            <c:if test="${term.time == '周一上午三四节'}">
                                ${term.coursename}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                            <c:if test="${term.time == '周二上午三四节'}">
                                ${term.coursename}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                            <c:if test="${term.time == '周三上午三四节'}">
                                ${term.coursename}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                            <c:if test="${term.time == '周四上午三四节'}">
                                ${term.coursename}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                            <c:if test="${term.time == '周五上午三四节'}">
                                ${term.coursename}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td> </td>
                    <td> </td>
                </tr>
                <tr><td>下午一二节</td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                            <c:if test="${term.time == '周一下午一二节'}">
                                ${term.coursename}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                            <c:if test="${term.time == '周二下午一二节'}">
                                ${term.coursename}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                            <c:if test="${term.time == '周三下午一二节'}">
                                ${term.coursename}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                            <c:if test="${term.time == '周四下午一二节'}">
                                ${term.coursename}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                            <c:if test="${term.time == '周五下午一二节'}">
                                ${term.coursename}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td> </td>
                    <td> </td>
                </tr>
                <tr>
                    <%--<c:forEach items="${termCourse}" var="term">--%>
                    <td>下午三四节</td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                        <c:if test="${term.time == '周一下午三四节'}">
                            ${term.coursename}
                        </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                            <c:if test="${term.time == '周二下午三四节'}">
                                ${term.coursename}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                            <c:if test="${term.time == '周三下午三四节'}">
                                ${term.coursename}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                            <c:if test="${term.time == '周四下午三四节'}">
                                ${term.coursename}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${termCourse}" var="term">
                            <c:if test="${term.time == '周五下午三四节'}">
                                ${term.coursename}
                            </c:if>
                        </c:forEach></td>
                    <td> </td>
                    <td> </td>
                    <%--</c:forEach>--%>
                </tr>

        </table>
    </div>
</div>
</body>
</html>
