<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Price | 모든 물건들의 가격을 알고 싶어 하는 당신을 위한 사이트</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/reset.css">
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/temp.css">
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/temp_info.css">
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/member/searchList.css">
<style type="text/css">
#btn_alarmInsert { display: none; /* 테스트 버튼 숨기기*/} 
</style>

</head>
<body>
<c:if test="${empty mDto or mDto.id ne 'admin'}">
	<script>
	alert("관리자 화면 입니다.");
	location.href = "/MyPrice/index_MyPrice.jsp";
	</script>
</c:if>

<script type="text/javascript">
	
$(function(){
	$(".cbs").prop("checked", false);
	$("#search_goodid").prop("disabled", true);
	$("#search_entpid").prop("disabled", true);
	$(".t_s_inputs").prop("disabled", "disabled");
	// 페이지 처음 열었을 때 전체 조회
	$("#searchList").load("alarmAdminList.alarm", {
		curPage:1,
		id:"",
		goodid:"",
		entpid:"",		
		alarm_yn:""
		},
		function(){
			
		});
	// 전체버튼
	$("#cb_All").click(function() {
		$(".cbs").prop("checked", ($(this).prop("checked")));
		$(".t_s_inputs").prop("disabled", (!($(this).prop("checked"))));
	});
	
	$(".cbs").click(function() {
		//alert($(this).prop("checked"));
		if ($(this).prop("checked") == false) {
			$("#cb_All").prop("checked", false);
			
			if($(this).prop("id") == "cb_id") {
				$("input[name=id]").prop("disabled", true);
			}else if ($(this).prop("id") == "cb_goodid") {
				$("input[name=goodname]").prop("disabled", true);
				$("#search_goodid").prop("disabled", true);
			}else if ($(this).prop("id") == "cb_entpid") {
				$("input[name=entpname]").prop("disabled", true);
				$("#search_entpid").prop("disabled", true);
			}else if ($(this).prop("id") == "cb_alarm_yn") {
				$("input[name=alarm_yn]").prop("disabled", true);
			}
		}else {
			
			var ch_all_check = true;
			$(".cbs").each(function(index, this_cb) {
				if ( $(this_cb).prop("checked") == false) {
					ch_all_check = false;
				}
			});
			$("#cb_All").prop("checked", ch_all_check);
			
			if($(this).prop("id") == "cb_id") {
				$("input[name=id]").prop("disabled", false);
			}else if ($(this).prop("id") == "cb_goodid") {
				$("input[name=goodname]").prop("disabled", false);
				$("#search_goodid").prop("disabled", false);
			}else if ($(this).prop("id") == "cb_entpid") {
				$("input[name=entpname]").prop("disabled", false);
				$("#search_entpid").prop("disabled", false);
			}else if ($(this).prop("id") == "cb_alarm_yn") {
				$("input[name=alarm_yn]").prop("disabled", false);
			}
		}
	});

	// 상품 search
	$("#search_goodid").click(function(){
		window.open("/MyPrice/member/searchItem.jsp?search_item=prod&input_id=goodid&input_name=goodname", "상품찾기", "width=850,height=600");
	});
	// 점포 search
	$("#search_entpid").click(function(){
		window.open("/MyPrice/member/searchItem.jsp?search_item=store&input_id=entpid&input_name=entpname", "점포찾기", "width=850,height=600");
	});
	
	// 검색버튼
	var id="";
	var goodid="";
	var entpid="";		
	var alarm_yn="";
	
	$("#btn_search").click(function(){
		var btn_search_yn = true;
		if ( !($("input[name=id]").val()==null) ) {
			id=$("input[name=id]").val();	
		}
		if ( !($("input[name=goodid]").val()==null) ) {
			goodid=$("input[name=goodid]").val();	
		}
		if ( !($("input[name=entpid]").val()==null) ) {
			entpid=$("input[name=entpid]").val();	
		}
		alarm_yn=$("input[name=alarm_yn]:checked").val();
		
		if ($("#cb_id").prop("checked") == true && $("input[name=id]").val() == "" ) {
			alert('아이디 검색어를 입력해 주세요.');
			$("input[name=id]").focus();
			btn_search_yn = false;
		}else if ( !$("#cb_id").prop("checked") ) {
			id="";
		}
		if ($("#cb_goodid").prop("checked") == true && $("input[name=goodid]").val() == "") {
			alert('삼품을 선택해 주세요.');
			$("#search_goodid").focus();
			btn_search_yn = false;
		}else if ( !$("#cb_goodid").prop("checked") ) {
			goodid="";
		}
		if ($("#cb_entpid").prop("checked") == true && $("input[name=entpid]").val() == "") {
			alert('점포를 선택해 주세요.');
			$("#search_entpid").focus();
			btn_search_yn = false;
		}else if ( !$("#cb_entpid").prop("checked") ) {
			entpid="";
		}
		if ($("#cb_alarm_yn").prop("checked") == false) {
			alarm_yn = "";
		}
	
		if ( btn_search_yn = true ) {
			$("#searchList").load("alarmAdminList.alarm", {
				curPage:1,
				id:id,
				goodid:goodid,
				entpid:entpid,		
				alarm_yn:alarm_yn	},
				function(){
					//alert("searchList - alarmAdminList");
				});
				
		}
		
	});
	// 엑셀파일 저장
	$("#btn_saveFile").click(function(){
	    $.ajax({
			type:"GET",
			url:"alarmAdminSave.alarm",
			data:{
				curPage:1,
				id:id,
				goodid:goodid,
				entpid:entpid,		
				alarm_yn:alarm_yn
			},
			success: function (result_message) {
				alert(result_message);	
			
			},
			error: function(error) {
				alert(error);
			}
		});
	    
	});	
	
	// 테스트용 : 가격알람 입력 (현재 disabled)
	$("#btn_alarmInsert").click(function(){
		//window.open("/MyPrice/myPage/myAlarmWrite.jsp?goodid=5&entpid=416&inspectday=20161125&goodname=가그린 레귤러&entpname=농협광주유통센터", "MyPrice", "width=1050,height=270");
		//window.open("/MyPrice/myPage/myAlarmWrite.jsp?goodid=5&entpid=416&inspectday=20161125", "MyPrice", "width=1050,height=270");
		window.open("/MyPrice/myPage/myAlarmPrices.alarm?goodid=246&entpid=685&inspectday=20161125", "MyPrice", "width=1050,height=270");
	});
	
	// 가격알람 메일 발송
	$("#btn_sendEmail").click(function(){
		var sendEmail_yn = confirm("[매주 금요일 21시 이후 ~ 24시 이전에 수행해야 하는 작업]\r\r가격알람 단체메일을 전송하시겠습니까??");
		if (sendEmail_yn) {
			alert("가격알람 메일 단체발송 합니다.\r\r발송이 완료되면 발송건수를 확인 하실 수 있습니다.");	
			$.post("myAlarmEmail.alarm", {}, function(results){
				results=results.trim();
				alert(results + "건의 가격알람 메일을 발송 하였습니다.");
			});
		}
		
	});
	
	// 알람상태 업데이트 작업
	$("#btn_update_alarm_yn").click(function() {
		var update_alarm_yn = confirm("가격알람 상태 일괄 업데이트 작업을 수행하시겠습니까??\r\r[알람상태='Y'&오늘날짜 기준으로 종료일자 지난 데이터\r => 알람상태='N'으로 일괄 업데이트]");
		if (update_alarm_yn) {
			var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth()+1;
			if (dd<10) {
				dd = '0' + dd;
			}
			if (mm < 10) {
				mm = '0' + mm;
			}
			var todayStr = today.getFullYear() + '-'+mm + '-'+dd 
			$.post("myAlarmUpdateYn.alarm", {}, function(results){
				results=results.trim();
				alert(results + "건의 가격알람상태를 업데이트 하였습니다.");
			});
		}
	});
	
	// 리스트 페이징
	$("#searchList").on("click", ".paging", function() {	// paging에 이벤트 위임
		//alert("paging : " + $(this).attr("id"));
		$("#searchList").load("alarmAdminList.alarm", {
			curPage:$(this).attr("id"),
			id:id,
			goodid:goodid,
			entpid:entpid,		
			alarm_yn:alarm_yn	},
			function(){
				
		});	
	});
	
});
</script>

<%@ include file="/template/header_mp.jsp"  %>

	<section id = "center">
		<section id="submenu">
			<div id="sm_image"><p>마이페이지</p></div>
			<div id="sm_list">
				<%@ include file="/template/sm_list_myInfo.jsp"  %>
				
			</div>
		</section>
		<section id="info">
			<div id="info_image"><p>회원가격알람:admin</p></div>
			<div id="info_list">
				<div id="d_search">
					<table>
						<tr><td class="t_s_out"></td><td class="t_s_l">조건선택</td>	<td><input type="checkbox" id="cb_All"></td>
							<td class="t_s_items"><p>* 선택된 조건이 없으면 전체조회</p></td><td class="t_s_btns"><button id="btn_search" class="btn_style">검    색</button></td></tr>
						<tr><td class="t_s_out"></td><td class="t_s_l">아이디</td>	<td><input type="checkbox" id="cb_id" class="cbs"></td>	
							<td><input type="text" name="id" class="t_s_inputs" size=20px maxlength="20"></td>	<td class="t_s_btns"><button id="btn_saveFile" class="btn_style">검색결과 엑셀저장</button></td></tr>
						<tr><td class="t_s_out"></td><td class="t_s_l">상품</td>	<td><input type="checkbox" id="cb_goodid" class="cbs"></td>
							<td class="t_s_items">
								<input type="hidden" id="goodid" name="goodid">
								<input type="text" id="goodname" name="goodname" class="t_s_inputs" size=20px readonly="readonly">
								<input type="button" id="search_goodid" class="btn_style" value="찾기"></td>
							<td class="t_s_btns"><button id="btn_alarmInsert" class="btn_style">(test)가격알람 입력</button></td></tr>
						<tr><td class="t_s_out"></td><td class="t_s_l">점포</td>	<td><input type="checkbox" id="cb_entpid" class="cbs"></td>
							<td class="t_s_items">
								<input type="hidden" id="entpid" name="entpid">
								<input type="text" id="entpname" name="entpname" class="t_s_inputs" size=20px readonly="readonly">
								<input type="button" id="search_entpid" class="btn_style" value="찾기"></td>
							<td class="t_s_btns"><button id="btn_sendEmail" class="btn_style">가격알람 메일발송</button></td></tr>
						<tr><td class="t_s_out"></td><td class="t_s_l">알람상태</td>	<td><input type="checkbox" id="cb_alarm_yn" class="cbs"></td>	
							<td>
								<input type="radio" name="alarm_yn" class="t_s_inputs" value="Y" checked>알람 중
								<input type="radio" name="alarm_yn" class="t_s_inputs" value="N">알람중지</td>
							<td class="t_s_btns"><button id="btn_update_alarm_yn" class="btn_style">알람상태 업데이트</button></td></tr>
					</table>
					
				</div>
				<div id="searchList"></div>
				
			</div>
		</section>
	</section>
	
<%@ include file="/template/footer_mp.jsp"  %>
</body>
</html>