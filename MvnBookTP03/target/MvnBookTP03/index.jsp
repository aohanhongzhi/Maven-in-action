<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index JSP</title>
    </head>
    <body>
        <form action="welcomeServlet" method="post">
           请输入问候人名:<input type='text' name="name"/><br/>
           <input type='submit' value='问候'/>
        </form>
    </body>
</html>
