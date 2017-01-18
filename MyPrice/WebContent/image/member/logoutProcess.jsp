<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script type="text/javascript">
	<%session.invalidate();%>	// 로그아웃. 세션끊기
	location.href = "/MyPrice/index_MyPrice.jsp";
</script>
</body>
</html>