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
<c:if test="${empty mDto or mDto.id ne 'admin'}">
	<script>
	alert("관리자 화면 입니다.");
	//history.go(-1);
	location.href = "/MyPrice/index_MyPrice.jsp";
	</script>
</c:if>

<script type="text/javascript">
	
$(function(){
		
	$(".cbs").prop("checked", false);
	$("#search_store").prop("disabled", true);
	$(".t_s_inputs").prop("disabled", "disabled");
	$("#searchList").load("myInfoAdminList.member", {
		curPage:1,
		id:"",
		out_yn:"",
		gender:"",
		phone:"",
		store:"",
		inputdttm_s:"",
		inputdttm_e:"",
		out_date_s:"",
		out_date_e:""	},
		function(){
			
		});
	
	// 전체버튼
	$("#cb_All").click(function() {
		//alert(($(this).prop("checked")) );
		$(".cbs").prop("checked", ($(this).prop("checked")));
		$(".t_s_inputs").prop("disabled", (!($(this).prop("checked"))));
	});
	$(".cbs").click(function() {
		//alert($(this).prop("checked"));
		if ($(this).prop("checked") == false) {
			$("#cb_All").prop("checked", false);
			if($(this).prop("id") == "cb_id") {
				$("input[name=id]").prop("disabled", true);
			}else if ($(this).prop("id") == "cb_out_yn") {
				$("input[name=out_yn]").prop("disabled", true);
			}else if ($(this).prop("id") == "cb_gender") {
				$("input[name=gender]").prop("disabled", true);
			}else if ($(this).prop("id") == "cb_phone") {
				$("input[name=phone]").prop("disabled", true);
			}else if ($(this).prop("id") == "cb_store") {
				$("input[name=entpname]").prop("disabled", true);
				$("#search_store").prop("disabled", true);
			}else if ($(this).prop("id") == "cb_inputdttm") {
				$("input[name=inputdttm_S]").prop("disabled", true);
				$("input[name=inputdttm_e]").prop("disabled", true);
			}else if ($(this).prop("id") == "cb_out_date") {
				$("input[name=out_date_s]").prop("disabled", true);
				$("input[name=out_date_e]").prop("disabled", true);
			}
		}else {
			
			var ch_all_check = true;
			$(".cbs").each(function(index, this_cb) {
				if ( $(this_cb).prop("checked") == false) {
					ch_all_check = false;
				}
				//alert($(this_cb).prop("id") +"/"+ $(this_cb).prop("checked") +"/"+ ch_all_check);
			});
			$("#cb_All").prop("checked", ch_all_check);
			
			if($(this).prop("id") == "cb_id") {
				$("input[name=id]").prop("disabled", false);
			}else if ($(this).prop("id") == "cb_out_yn") {
				$("input[name=out_yn]").prop("disabled", false);
			}else if ($(this).prop("id") == "cb_gender") {
				$("input[name=gender]").prop("disabled", false);
			}else if ($(this).prop("id") == "cb_phone") {
				$("input[name=phone]").prop("disabled", false);
			}else if ($(this).prop("id") == "cb_store") {
				$("input[name=entpname]").prop("disabled", false);
				$("#search_store").prop("disabled", false);
			}else if ($(this).prop("id") == "cb_inputdttm") {
				$("input[name=inputdttm_s]").prop("disabled", false);
				$("input[name=inputdttm_e]").prop("disabled", false);
			}else if ($(this).prop("id") == "cb_out_date") {
				$("input[name=out_date_s]").prop("disabled", false);
				$("input[name=out_date_e]").prop("disabled", false);
			}
		}
	});
	
	// 관심점포 search
	$("#search_store").click(function(){
		window.open("/MyPrice/member/searchItem.jsp?search_item=store&input_id=store&input_name=entpname", "점포찾기", "width=850,height=600");
	});
	
	// 검색버튼
	var id="";
	var out_yn="";
	var gender="";
	var phone="";
	var store="";
	var inputdttm_s="";
	var inputdttm_e="";
	var out_date_s="";
	var out_date_e="";
	
	$("#btn_search").click(function(){
		var btn_search_yn = true;
		id=$("input[name=id]").val();
		out_yn=$("input[name=out_yn]:checked").val();
		gender=$("input[name=gender]:checked").val();
		phone=$("input[name=phone]").val();
		store=$("input[name=store]").val();
		inputdttm_s=$("input[name=inputdttm_s]").val();
		inputdttm_e=$("input[name=inputdttm_e]").val();
		out_date_s=$("input[name=out_date_s]").val();
		out_date_e=$("input[name=out_date_e]").val();
		
		if ($("#cb_id").prop("checked") == true && $("input[name=id]").val() == "" ) {
			alert('아이디 검색어를 입력해 주세요.');
			$("input[name=id]").focus();
			btn_search_yn = false;
		}else if ( !$("#cb_id").prop("checked") ) {
			id="";
		}
		if ($("#cb_out_yn").prop("checked") == false) {
			out_yn = "";
		}
		if ($("#cb_gender").prop("checked") == false) {
			gender = "";
		}
		if ($("#cb_phone").prop("checked") == true && $("input[name=phone]").val() == "" ) {
			alert('핸드폰번호 검색어를 입력해 주세요.');
			$("input[name=phone]").focus();
			btn_search_yn = false;
		}else if ( !$("#cb_phone").prop("checked") ) {
			phone="";
		}
		if ($("#cb_store").prop("checked") == true && $("input[name=store]").val() == "") {
			alert('주요점포를 선택해 주세요.');
			$("#search_store").focus();
			btn_search_yn = false;
		}else if ( !$("#cb_store").prop("checked") ) {
			store="";
		}
		if ($("#cb_inputdttm").prop("checked") == true && ($("input[name=inputdttm_s]").val() == "" || $("input[name=inputdttm_e]").val() == "" )) {
			alert('가입일자를 선택해 주세요.');
			$("input[name=inputdttm_s]").focus();
			btn_search_yn = false;
		}else if ( !$("#cb_inputdttm").prop("checked") ) {
			inputdttm_s="";
			inputdttm_e="";
		}
		if ($("#cb_out_date").prop("checked") == true && ($("input[name=out_date_s]").val() == "" || $("input[name=out_date_e]").val() == "") ) {
			alert('탈퇴일자를 선택해 주세요.');
			$("input[name=out_date_s]").focus();
			btn_search_yn = false;
		}else if ( !$("#cb_out_date").prop("checked") ) {
			out_date_s="";
			out_date_e="";
		}
		//alert( id +"/"+	out_yn +"/"+gender +"/"+phone +"/"+	store +"/"+	inputdttm_s +"/"+	inputdttm_e +"/"+	out_date_s +"/"+out_date_e);
		//location.href = "/MyPrice/myPage/myInfoAdminList.member?curPage=1&id="+id+"&out_yn="+out_yn+"&gender="+gender+"&phone="+phone+"&store="+store+"&inputdttm_s="+inputdttm_s+"&inputdttm_e="+inputdttm_e+"&out_date_s="+out_date_s+"&out_date_e="+out_date_e;
		if ( btn_search_yn = true ) {
			$("#searchList").load("myInfoAdminList.member", {
				curPage:1,
				id:id,
				out_yn:out_yn,
				gender:gender,
				phone:phone,
				store:store,
				inputdttm_s:inputdttm_s,
				inputdttm_e:inputdttm_e,
				out_date_s:out_date_s,
				out_date_e:out_date_e	},
				function(){
					//alert("memberList");
				});	
		}
			
	});
	
	// 엑셀파일 저장
	$("#btn_saveFile").click(function(){
		/* 화면 내용자체를 저장
		window.open('data:application/vnd.ms-excel,' + $('#t_list').html());
	    e.preventDefault();
	    */
	    $.ajax({
			type:"GET",
			url:"myInfoAdminSave.member",
			data:{
				curPage:1,
				id:id,
				out_yn:out_yn,
				gender:gender,
				phone:phone,
				store:store,
				inputdttm_s:inputdttm_s,
				inputdttm_e:inputdttm_e,
				out_date_s:out_date_s,
				out_date_e:out_date_e
			},
			success: function (result_message) {
				alert(result_message);	
			
			},
			error: function(error) {
				alert(error);
			}
		});
	    
	});	
	
	// 사용자정보 상세정보 보여주기
	var tr_click = false;
	var tr_before = "";
	$("#searchList").on("click", ".tr_member", function(){	// tr_member에 이벤트 위임
		if( tr_click == false || tr_before != $(this).attr("id") ){
			$(".td_contents").hide();
			$("#td_show"+$(this).attr("id")).show();
			tr_click = true;
						
		}else {
			$("#td_show"+$(this).attr("id")).hide();
			tr_click = false;
		}
		tr_before = $(this).attr("id");		
	});
	// 사용자 리스트 페이징
	$("#searchList").on("click", ".paging", function() {	// paging에 이벤트 위임
		//alert("paging : " + $(this).attr("id"));
		$("#searchList").load("myInfoAdminList.member", {
			curPage:$(this).attr("id"),
			id:id,
			out_yn:out_yn,
			gender:gender,
			phone:phone,
			store:store,
			inputdttm_s:inputdttm_s,
			inputdttm_e:inputdttm_e,
			out_date_s:out_date_s,
			out_date_e:out_date_e	},
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
			<div id="info_image"><p>회원관리:admin</p></div>
			<div id="info_list">
				<div id="d_search">
					<table>
						<tr><td class="t_s_out"></td><td class="t_s_l">조건선택</td>	<td><input type="checkbox" id="cb_All"></td>
							<td class="t_s_items"><p>* 선택된 조건이 없으면 전체조회</p></td><td class="t_s_btns"><button id="btn_search" class="btn_style">검    색</button></td></tr>
						<tr><td class="t_s_out"></td><td class="t_s_l">아이디</td>	<td><input type="checkbox" id="cb_id" class="cbs"></td>	
							<td><input type="text" name="id" class="t_s_inputs" size=20px maxlength="20"></td>	<td></td></tr>
						<tr><td class="t_s_out"></td><td class="t_s_l">탈퇴여부</td>	<td><input type="checkbox" id="cb_out_yn" class="cbs"></td>	
							<td>
								<input type="radio" name="out_yn" class="t_s_inputs" value="N" checked>회원
								<input type="radio" name="out_yn" class="t_s_inputs" value="Y">탈퇴
							</td><td></td></tr>	
						<tr><td class="t_s_out"></td><td class="t_s_l">성별</td>		<td><input type="checkbox" id="cb_gender" class="cbs"></td>	
							<td>
								<input type="radio" id="gender" name="gender" class="t_s_inputs" value="F" checked>여성
								<input type="radio" id="gender" name="gender" class="t_s_inputs" value="M">남성
							</td><td></td></tr>
						<tr><td class="t_s_out"></td><td class="t_s_l">핸드폰번호</td>	<td><input type="checkbox" id="cb_phone" class="cbs"></td>
							<td class="t_s_items"><input type="text" name="phone" class="t_s_inputs" size=20px maxlength="11"><p>*일부번호 검색 가능, - 제외 후 입력</p></td><td></td></tr>
						<tr><td class="t_s_out"></td><td class="t_s_l">주요점포</td>	<td><input type="checkbox" id="cb_store" class="cbs"></td>
							<td class="t_s_items">
								<input type="hidden" id="store" name="store">
								<input type="text" id="entpname" name="entpname" class="t_s_inputs" size=20px readonly="readonly">
								<input type="button" id="search_store" class="btn_style" value="찾기"></td><td></td></tr>
						<tr><td class="t_s_out"></td><td class="t_s_l">가입일자</td>	<td><input type="checkbox" id="cb_inputdttm" class="cbs"></td>
							<td><input type="date" name="inputdttm_s" class="t_s_inputs"> ~ <input type="date" name="inputdttm_e" class="t_s_inputs"></td><td></td></tr>
						<tr><td class="t_s_out"></td><td class="t_s_l">탈퇴일자</td>	<td><input type="checkbox" id="cb_out_date" class="cbs"></td>
							<td><input type="date" name="out_date_s" class="t_s_inputs"> ~ <input type="date" name="out_date_e" class="t_s_inputs"></td>
							<td class="t_s_btns"><button id="btn_saveFile" class="btn_style">엑셀저장</button></td></tr>
					</table>
					
				</div>
				<div id="searchList"></div>
				
			</div>
		</section>
	</section>
	
<%@ include file="/template/footer_mp.jsp"  %>
</body>
</html>