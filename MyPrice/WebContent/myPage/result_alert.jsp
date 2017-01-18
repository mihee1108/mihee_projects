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
	alert("${result_message}");
	if("${result_action}" == "MyInfoOutAction") {
		// MyInfoOutAction.java
		location.href = "/MyPrice/index_MyPrice.jsp";	
	}else if("${result_action}" == "MyInfoUpdateAction") {
		location.href = "/MyPrice/myPage/myInfo.jsp";
	}else if ("${result_action}" == "MyAlarmViewAction") {
		window.close();		
	}
   	
</script>
</body>
</html>