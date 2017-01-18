<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Price | 모든 물건들의 가격을 알고 싶어 하는 당신을 위한 사이트</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/reset.css">
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/member/alarm.css">
<style type="text/css">
/* 별도 css 파일 없음 */
div {
	width: 810px;
}
div > * {
	float: left;
}
#d_search, #d_select{
	height: 40px;
}
#d_search > *, #d_select > * {
	margin-top:10px;
	margin-left: 10px;
}
p {
	text-align:left;
	padding-left: 10px;
	color: #0066ff;
	font-size: 14px;
}
.btn_style{
	width:80px;
	height:20px;
	display:block;
	text-align:center;
	cursor:pointer;
	color:#0066ff;
	font-size:14px;
	background-color:white;
	border: 1px #0066ff solid;
}


#d_table{
	height: 480px;
	border: 1px solid #cccccc;
	margin-top:20px;
	overflow: scroll;
}
table {
	width: 100%;
}
th{
	font-weight: bold;
}
th, td {
	height: 30px;
	border: 1px solid #cccccc;
	text-align: left;
	padding-left: 5px;
}
.td_itemName {
	width: 20%;
}
.td_itemName:HOVER {
	cursor:pointer;
	font-weight:bold;
}
.td_entpAddrBasic, .td_entpAddrDetail {
	width: 40%;
}
.td_codename {
	width: 26%;
}

#d_select > * {

}
#select_item {
	width:370px;
	height:30px;
	border: 1px solid #cccccc;
	text-align: center;
	font-weight: bold;
}
</style>
</head>
<body>
 <script type="text/javascript">
 	
$(function(){
	//alert("${param.input_id} / ${param.input_name}");
	var item_id = "";
	$("#btn_search").click(function(){
		$("#d_table").load("searchItem.member", {search_item:"${param.search_item}", search_txt:$("#search_txt").val()}, function() {});	
	});
	
	$("#d_table").on("click", ".td_itemName", function() {
		//alert($(this).attr("id") + " / " + $(this).text());
		item_id = $(this).attr('id');
		$("#select_item").text($(this).text());
	});
	
	$("#btn_select").click(function(){		
		$("#${param.input_id}",opener.document).val(item_id);
		$("#${param.input_name}",opener.document).val($("#select_item").text());

		window.close();
	});
		
});

</script> 

<div id="d_search"><input type="text" id="search_txt" size=50px><button id="btn_search" class="btn_style">검    색</button><p>* 검색단어가 포함된 항목들이 검색됩니다.</p></div>
<div id="d_table">

</div>
<div id="d_select">
	<div id="select_item"></div><button id="btn_select" class="btn_style">선택</button>
</div>

</body>
</html>