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
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="${pageContext.request.contextPath}/updateUserServlet" method="post">
        <input type="hidden" name = "id" value="${user.id}">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" value="${user.name}" readonly="readonly"
                   placeholder="请输入姓名"/>
        </div>

        <div class="form-group">
            <label>性别：</label>
            <c:if test="${user.gender == '男'}">
                <input type="radio" name="gender" value="男" checked/>男
                <input type="radio" name="sex" value="女"/>女
            </c:if>
            <c:if test="${user.gender == '女'}">
                <input type="radio" name="gender" value="男"/>男
                <input type="radio" name="gender" value="女" checked/>女
            </c:if>

        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" value="${user.age}" id="age" name="age" placeholder="请输入年龄"/>
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" class="form-control">
                <option value="广东">广东</option>
                <option value="广西">广西</option>
                <option value="湖南">湖南</option>
                <option value="湖北">湖北</option>
                <option value="北京" selected>北京</option>
                <option value="天津">天津</option>
                <option value="山东">山东</option>
                <option value="陕西">陕西</option>
                <option value="山西">山西</option>
                <option value="新疆">新疆</option>
                <option value="内蒙古">内蒙古</option>
                <option value="西藏">西藏</option>
                <option value="吉林">吉林</option>
                <option value="辽宁">辽宁</option>
                <option value="黑龙江">黑龙江</option>
                <option value="宁夏">宁夏</option>
                <option value="四川">四川</option>
                <option value="青海">青海</option>
                <option value="云南">云南</option>
                <option value="重庆">重庆</option>
                <option value="浙江">浙江</option>
                <option value="江苏">江苏</option>
                <option value="江西">江西</option>
                <option value="福建">福建</option>
                <option value="台湾">台湾</option>
                <option value="上海">上海</option>
                <option value="澳门">澳门</option>
                <option value="香港">香港</option>
            </select>
        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" class="form-control" value="${user.qq}" name="qq" placeholder="请输入QQ号码"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" value="${user.email}" name="email" placeholder="请输入邮箱地址"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>