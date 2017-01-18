<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript">
    var logout = confirm("로그아웃 하시겠습니까??");
    //alert("logout : " + logout);
	if (logout) {
		location.href = "/MyPrice/member/logoutProcess.jsp";
	}else {
		history.go(-1);
	}
</script>
</body>
</html>