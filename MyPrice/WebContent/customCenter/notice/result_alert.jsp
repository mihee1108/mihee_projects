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
	if("${result_action}" == "NoticeCountsAction" || "${result_action}" == "NoticeModFormAction" || "${result_action}" == "NoticeWriteAction") {
			location.href = "noticeList.notice?curPage=1&b_cls="+"${b_cls}";	
		}else if("${result_action}" == "NoticeModAction" || "${result_action}" == "NoticeDelAction" || "${result_action}" == "NoticeModAction") {
			location.href = "noticeList.notice?curPage="+"${curPage}&b_cls="+"${b_cls}";
	}
   	
</script>
</body>
</html>