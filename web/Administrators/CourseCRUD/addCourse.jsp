<%@page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加课程</title>

    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <script src="../../js/jquery-2.1.0.min.js" type="text/javascript"></script>
    <script src="../../js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js" type="text/javascript" charset="UTF-8"></script>

</head>
<body>
<div class="container" style="width: 400px;margin-top: 60px">
    <h3 style="text-align: center;">添加课表信息</h3>
    <form action="${pageContext.request.contextPath}/addCourse" method="post">
        <div class="form-group">
            <label for="coursetype">课程类别：</label>
            <select  class="form-control" id="coursetype" name="coursetype">
                <option id="0" >请选择</option>
            </select>
        </div><div class="form-group">
        <label for="coursename">课程名称：</label>
        <select  class="form-control" id="coursename" name="coursename">
            <option id="0" >请选择</option>
        </select>
    </div>
        <div class="form-group">
            <label for="week">星期：</label>
            <br>
            <%--<input type="text" class="form-control" id="time" name="time" placeholder="请输入上课时间"/>--%>
            <select  class="form-control" id="week" name="week">
                <option value="周一">周一</option>
                <option value="周二">周二</option>
                <option value="周三" selected>周三</option>
                <option value="周四">周四</option>
                <option value="周五">周五</option>
                <option value="周六">周六</option>
            </select>
            <div class="form-group">
                <label for="day">上课时间</label><br>
                <select  class="form-control" id="day" name="day">
                    <option value="上午一二">上午一二</option>
                    <option value="上午三四">上午三四</option>
                    <option value="下午一二" selected>下午一二</option>
                    <option value="下午三四">下午三四</option>
                    <option value="下午五六">下午五六</option>
                    <option value="下午七八">下午七八</option>
                </select>
            </div>

        <div class="form-group">
            <label for="credit">学分：</label>
            <%--<input type="text" class="form-control" id="credit" name="credit" placeholder="请输入学分"/>--%>
            <select  class="form-control" id="credit" name="credit">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3" selected>3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
            </select>

        </div>
            <div class="form-group">
                <label for="type">课程类型</label>
                <select  class="form-control" id="type" name="type">
                    <option value="必修" selected>必修</option>
                    <option value="选修">选修</option>

                </select>
            </div>
        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" onclick="my()" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
<script type="text/javascript">

    $(function () {
        $.ajax({
            url:"${pageContext.request.contextPath}/findSubjectType",//查找学科类型
            data:"method=findType",
            type:"post",//发送类型
            dataType:"json",//返回类型
            success:function (list) {//返回一个列表
                for (var i = 0; i < list.length; i++) {
                    var $op=$("<option></option>");
                    $op.text(list[i].name);
                    $op.attr("id",list[i].type);
                    $op.attr("value",list[i].type);
                    $("#coursetype").append($op);
                }
            }
        }) ;
    })
    $("#coursetype").change(function () {
        $("#coursename").empty();
        var id = $("#coursetype option:selected").attr("id");
        if (id==0){
            $("#coursename").append("<option id='0'>请选择</option>");
            return;
        }
        $.ajax({
            url:"${pageContext.request.contextPath}/findSubjectType",
            type:"post",
            data:{"method":"findSubject","param":id},
            dataType:"json",
            success:function (list) {
                for (var i = 0; i < list.length; i++) {
                    var $op=$("<option></option>");//   记住
                    $op.text(list[i].name);//记住
                    $op.attr("id",list[i].type);
                    $op.attr("value",list[i].type);
                    $("#coursename").append($op);
                }
            }
        });
    });
    function my() {
        var $i=$("#coursename option:selected").attr("id");
        $.ajax({
            url:"${pageContext.request.contextPath}/findSubjectType",
            type:"post",
            data:{"method":"addCourse","param":$i},
            dataType:"json",
            success:function (list) {
                if (list.state==200){
                    alert("添加成功");
                }else {
                    alert("添加失败");
                }
            }
        });
    }

</script>
</html>