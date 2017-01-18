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
	if("${result_action}" == "AskWriteAction") {
		location.href = "askWrite.jsp";	
	}else if("${result_action}" == "AskListAction") {
		location.href = "askList.ask?writer="+"${writer}";
	}else if("${result_action}" == "AskReplyFormAction" || "${result_action}" == "AskModFormAction") {
		location.href = "askAdminList.ask?curPage=1";
	}else if("${result_action}" == "AskModAction" || "${result_action}" == "AskReplyAction") {
		location.href = "askAdminList.ask?curPage=${curPage}";
	}
   	
</script>
</body>
</html>