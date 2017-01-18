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
</head>
<body>
<c:if test="${empty mDto}">
	<script>
	alert("로그인 먼저 해주세요.");
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
	$("#searchList").load("myAlarmList.alarm", {
		curPage:1,
		id:"${mDto.id}",
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
			
			if ($(this).prop("id") == "cb_goodid") {
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
			
			if ($(this).prop("id") == "cb_goodid") {
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
	var goodid="";
	var entpid="";		
	var alarm_yn="";
	
	$("#btn_search").click(function(){
		var btn_search_yn = true;
		if ( !($("input[name=goodid]").val()==null) ) {
			goodid=$("input[name=goodid]").val();	
		}
		if ( !($("input[name=entpid]").val()==null) ) {
			entpid=$("input[name=entpid]").val();	
		}
		alarm_yn=$("input[name=alarm_yn]:checked").val();
		
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
			$("#searchList").load("myAlarmList.alarm", {
				curPage:1,
				id:"${mDto.id}",
				goodid:goodid,
				entpid:entpid,		
				alarm_yn:alarm_yn	},
				function(){
					//alert("searchList - myAlarmList");
				});
				
		}
		
	});

	// 수정화면 오픈
	$("#searchList").on("click", ".tr_alarm", function() {
		//alert("가격알람 번호" + $(this).attr("id"));
		window.open("myAlarmView.alarm?num="+$(this).attr("id"), "MyPrice", "width=1050,height=270");
		
	});
	
	// 리스트 페이징
	$("#searchList").on("click", ".paging", function() {	// paging에 이벤트 위임
		//alert("paging : " + $(this).attr("id"));
		$("#searchList").load("myAlarmList.alarm", {
			curPage:$(this).attr("id"),
			id:"${mDto.id}",
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
			<div id="sm_image"><p>가격알람</p></div>
			<div id="sm_list">
				<ul><li class="sm_list_m" id=""><a href = "/MyPrice/myPage/myAlarm.jsp">내 가격알람</a></li></ul>
			</div>
		</section>
		<section id="info">
			<div id="info_image"><p>내 가격알람</p></div>
			<div id="info_list">
				<div id="d_search">
					<table>
						<tr><td class="t_s_out"><td class="t_s_items" colspan="4"><p>* [생필품가격정보]에서 가격알람 설정을 추가하실 수 있습니다.</p></td></tr>
						<tr><td class="t_s_out"><td class="t_s_items" colspan="4"><p>* 각각의 항목을 클릭하시면 내 가격알람 정보 수정 및 삭제를 할 수 있습니다.</p></td></tr>
						<tr><td class="t_s_out"></td><td class="t_s_l">조건선택</td>	<td><input type="checkbox" id="cb_All"></td>
							<td class="t_s_items"><p>* 선택된 조건이 없으면 전체조회</p></td><td class="t_s_btns"><button id="btn_search" class="btn_style">검    색</button></td></tr>
						<tr><td class="t_s_out"></td><td class="t_s_l">상품</td>	<td><input type="checkbox" id="cb_goodid" class="cbs"></td>
							<td class="t_s_items">
								<input type="hidden" id="goodid" name="goodid">
								<input type="text" id="goodname" name="goodname" class="t_s_inputs" size=20px readonly="readonly">
								<input type="button" id="search_goodid" class="btn_style" value="찾기"></td><td></td></tr>
						<tr><td class="t_s_out"></td><td class="t_s_l">점포</td>	<td><input type="checkbox" id="cb_entpid" class="cbs"></td>
							<td class="t_s_items">
								<input type="hidden" id="entpid" name="entpid">
								<input type="text" id="entpname" name="entpname" class="t_s_inputs" size=20px readonly="readonly">
								<input type="button" id="search_entpid" class="btn_style" value="찾기"></td><td></td></tr>
						<tr><td class="t_s_out"></td><td class="t_s_l">알람상태</td>	<td><input type="checkbox" id="cb_alarm_yn" class="cbs"></td>	
							<td>
								<input type="radio" name="alarm_yn" class="t_s_inputs" value="Y" checked>알람 중
								<input type="radio" name="alarm_yn" class="t_s_inputs" value="N">알람중지
							</td><td></td></tr>
					</table>
					
				</div>
				<div id="searchList"></div>
				
			</div>
		</section>
	</section>
	
<%@ include file="/template/footer_mp.jsp"  %>
</body>
</html>