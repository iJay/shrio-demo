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
        <h1>注册页面</h1>
        <form action="${pageContext.request.contextPath}/user/register" method="post">
            <!--指定实际发送的请求方式-->
            <input type="hidden" name="_method" value="PUT">
            <label>
                用户名:
                <input type="text" name="username">
            </label> <br/>
            <label>
                密码 :
                <input type="text" name="password">
            </label> <br>
            <input type="submit" value="注册">
        </form>
    </body>
</html>