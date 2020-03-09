<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
后台测试页面
<br>
>>>>>:${review}:<<<<<<br>
>>>>>:${review.id}:<<<<<<br>
>>>>>:${review.content}:<<<<<<br>
>>>>>:${review.user_id}:<<<<<<br>
>>>>>:${review.product_id}:<<<<<<br>
>>>>>:${review.createDate}:<<<<<<br>
集合<br>
${reviews}
<br>产品评论数 ${count}
<br>产品请求测试<br>
${products}
</body>
</html>
