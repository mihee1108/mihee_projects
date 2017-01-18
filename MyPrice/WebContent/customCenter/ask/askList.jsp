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
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/customCenter/notice.css">
</head>
<body>
<c:if test="${empty mDto}">
	<script>
	alert("회원 전용 메뉴 입니다.");
	history.go(-1);
	</script>
</c:if>

<script type="text/javascript">
	
$(function(){
	$("#btn_search").click(function(){
		location.href = "/MyPrice/customCenter/ask/askList.ask?curPage=1&writer=${mDto.id}&search_txt="+$("#search_txt").val();
	});
	var t_c_cls = document.getElementsByClassName("t_c_cls");
	for (var i=0; i<t_c_cls.length; i++) {
		if (t_c_cls[i].innerText == 'P') {
			t_c_cls[i].innerHTML = '<input type="hidden" value="${n.c_cls}">필독';
		}else if (t_c_cls[i].innerText == 'N') {
			t_c_cls[i].innerHTML = '<input type="hidden" value="${n.c_cls}">일반';
		}else if (t_c_cls[i].innerText == 'A') {
			t_c_cls[i].innerHTML = '<input type="hidden" value="${n.c_cls}">이용문의';
		}else if (t_c_cls[i].innerText == 'B') {
			t_c_cls[i].innerHTML = '<input type="hidden" value="${n.c_cls}">회원관리';
		}else if (t_c_cls[i].innerText == 'Z') {
			t_c_cls[i].innerHTML = '<input type="hidden" value="${n.c_cls}">기타';
		}
	}
	
	var td_title_click = false;
	var td_title_before = "";
	$(".td_title").click(function(){
		if( td_title_click == false || td_title_before != $(this).attr("id") ){
			$(".td_contents").hide();
			$(".td_red").css("font-weight", "bold"); // 왜 안됨
			$(".td_title").css("font-weight", "normal");
			$(this).css("font-weight", "bold");
			$("#td_show"+$(this).attr("id")).show();
			$("#td_r_show"+$(this).attr("id")).show();
			td_title_click = true;
		}else {
			$("#td_show"+$(this).attr("id")).hide();
			$("#td_r_show"+$(this).attr("id")).hide();
			td_title_click = false;
		}
		td_title_before = $(this).attr("id");		
	});
	
});
</script>

<%@ include file="/template/header_mp.jsp"  %>

	<section id = "center">
		<section id="submenu">
			<div id="sm_image"><p>고객센터</p></div>
			<div id="sm_list">
				<%@ include file="/template/sm_list_notice.jsp"  %>
				
			</div>
		</section>
		<section id="info">
			<div id="info_image"><p>1:1문의</p></div>
			<div id="info_list">
				<div id="d_search">
					<%-- 검색조건 우선 빼기
					<select name="c_cls">
							<option value="A" selected>이용문의</option>
							<option value="B">회원관리</option>
							<option value="Z">기타</option>
					</select>
					--%>
					<input id="search_txt" type = "text" size = 50px value="${search_txt}">
					<button id="btn_search" class="btn_style">검색</button>
				</div>
				<table id="t_list">
					<tr><th class="td_out"></th><th class="num">번호</th><th class="num">답변</th><th class="num">분류</th><th class="title">제목</th><th class="reg_date">작성날짜</th></tr>

					<c:forEach items="${list}" var="a" varStatus="num">
						<tr>
							<td></td>
							<td>${a.num}</td>
							<td>${a.reply_yn}</td>
							<td class="t_c_cls">${a.c_cls}</td>
							<td class="td_title" id="${a.num}">${a.title}<input type="hidden" value="${a.num}"></td>
							<td>${a.reg_date}</td>
						</tr>
						<tr>
							<td colspan="6" id="td_show${a.num}" class="td_contents">${a.contents}<br>
						</tr>
						<tr>
							<td colspan="6" id="td_r_show${a.num}" class="td_contents"><li>[ 답변내용 ]</li><br><p>${a.reply_contents}</p><br>
							<c:if test="${a.reply_yn eq 'Y'}">
								<li>[ 답변일자 : ${a.reply_date} ]</li>
							</c:if>
							</td>
						</tr>
					</c:forEach>	
					
				</table>
				<div id="paging">
			
					<c:if test="${pm.curBlock > 1}">
						<a href="askList.ask?curPage=${pm.startPage-1}&writer=${mDto.id}&search_txt=${search_txt}"><img alt="btn_pre2" src="/MyPrice/image/myprice_trend/btn_prev2.gif"></a>
					</c:if>
					<c:forEach begin="${pm.startPage}" end="${pm.lastPage}" step="1" var="i"> <!-- for(int i=0; i<11; i++){} -->
						<a href="askList.ask?curPage=${i}&writer=${mDto.id}&search_txt=${search_txt}">${i}</a>
					</c:forEach>	
					<c:if test="${pm.curBlock < pm.totalBlock}">
						<a href="askList.ask?curPage=${pm.lastPage+1}&writer=${mDto.id}&search_txt=${search_txt}"><img alt="btn_next2" src="/MyPrice/image/myprice_trend/btn_next2.gif"></a>
					</c:if>
				
				</div>
			</div>
		</section>
	</section>
	
<%@ include file="/template/footer_mp.jsp"  %>
</body>
</html>