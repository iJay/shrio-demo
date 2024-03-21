<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h2>系统主页v1.0(受限页面)</h2>
<label>
    <a href="${pageContext.request.contextPath}/user/logout">退出登录</a>
</label>
<div>
    <button onclick="handleClick()">批量导入</button>
</div>
<ul>
    <li>用户管理</li>
    <li>商品管理</li>
    <li>订单管理</li>
    <li>物流管理</li>
</ul>
</body>
<script>
    function handleClick() {
        fetch('/user/import', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(res => {
            return res.json()
        }).then(data => {
            console.log(data)
        }).catch(err => {
            console.log(err)
        })
    }
</script>
</html>