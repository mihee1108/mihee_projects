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
<script type="text/javascript">
	
$(function(){
	//$(this).load("noticeList.notice", {b_cls:"N", curPage:curPage}, function(){ alert("333");	} );
	$("#btn_admin").click(function(){
		location.href = "/MyPrice/customCenter/notice/noticeWrite.jsp?b_cls=${param.b_cls}";		
	});
	$("#btn_search").click(function(){
		location.href = "/MyPrice/customCenter/notice/noticeList.notice?curPage=1&b_cls=${param.b_cls}&search_txt="+$("#search_txt").val();
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
			td_title_click = true;
			
			// 조회수 업데이트
			var n =$(this).attr("id")
			$.post("noticeCounts.notice",{
				b_cls:"${param.b_cls}",
				num:$(this).attr("id")
			}, function(upCount) {
				upCount=upCount.trim();
				$("#td_counts"+n).text(upCount);
				
			} );
			
		}else {
			$("#td_show"+$(this).attr("id")).hide();
			td_title_click = false;
		}
		td_title_before = $(this).attr("id");		
	});
	//index 화면에서 넘어왔을 경우 : ${num} is not null
	if ( !("${num}" == null)) {
		$("#td_show${num}").show();
		td_title_click = true;
		td_title_before = "${num}";	
	}

	
	
	
	$(".btn_admin_del").click(function(){
		var del = confirm("해당 공지를 삭제 하시겠습니까??");
		if (del) {
			location.href = "noticeDel.notice?b_cls=${param.b_cls}&num=" + $(this).attr("id") + "&curPage=" + ${curPage};
		}
	});
	$(".btn_admin_mod").click(function(){
		location.href = "noticeModForm.notice?b_cls=${param.b_cls}&num=" + $(this).attr("id") + "&curPage=" + ${curPage};
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
			<div id="info_image">
				<c:if test="${param.b_cls eq 'N'}">
					<p>공지사항</p>
				</c:if>
				<c:if test="${param.b_cls eq 'F'}">
					<p>FAQ</p>
				</c:if>
				</div>
			<div id="info_list">
				<div id="d_search">
				<%-- 검색조건 우선 빼기
					<c:if test="${param.b_cls eq 'F'}">
					<select name="c_cls">
							<option value="A" selected>이용문의</option>
							<option value="B">회원관리</option>
							<option value="Z">기타</option>
					</select>
					</c:if>
				 --%>
					<input id="search_txt" type = "text" size = 50px value="${search_txt}">
					<button id="btn_search" class="btn_style">검색</button>
					<c:if test="${mDto.id eq 'admin'}">
						<button id="btn_admin" class="btn_style">작성:admin</button>
					</c:if>
				</div>
				<table id="t_list">
					<tr><th class="td_out"></th><th class="num">번호</th><th class="num">분류</th><th class="title">제목</th><th class="num">조회</th><th class="reg_date">작성날짜</th></tr>
					<c:forEach items="${list}" var="n" varStatus="num">
					<c:if test="${n.c_cls eq 'P'}">
					<tr>
						<td></td>
						<td class="t_red">${n.num}</td>
						<td class="t_c_cls t_red">${n.c_cls}</td>
						<td class="td_title t_red" id="${n.num}">${n.title}<input type="hidden" value="${n.num}"></td>
						<td id="td_counts${n.num}" class="t_red">${n.counts}</td>
						<td class="t_red">${n.reg_date}</td>
					</tr>
					</c:if>
					<c:if test="${n.c_cls ne 'P'}">
					<tr>
						<td></td>
						<td>${n.num}</td>
						<td class="t_c_cls">${n.c_cls}</td>
						<td class="td_title" id="${n.num}">${n.title}</td>
						<td id="td_counts${n.num}">${n.counts}</td>
						<td>${n.reg_date}</td>
					</tr>
					</c:if>
					<tr>
						<td colspan="6" id="td_show${n.num}" class="td_contents">${n.contents}<br>
						<c:if test="${mDto.id eq 'admin'}">
							<button id="${n.num}" class="btn_style btn_admin_del">삭제:admin</button>
							<button id="${n.num}" class="btn_style btn_admin_mod">수정:admin</button>
						</c:if>
						</td>
					</tr>
					</c:forEach>	
					
				</table>
				<div id="paging">
			
					<c:if test="${pm.curBlock > 1}">
						<a href="noticeList.notice?curPage=${pm.startPage-1}&b_cls=N&search_txt=${search_txt}"><img alt="btn_pre2" src="/MyPrice/image/myprice_trend/btn_prev2.gif"></a>
					</c:if>
					<c:forEach begin="${pm.startPage}" end="${pm.lastPage}" step="1" var="i"> <!-- for(int i=0; i<11; i++){} -->
						<a href="noticeList.notice?curPage=${i}&b_cls=N&search_txt=${search_txt}">${i}</a>
					</c:forEach>	
					<c:if test="${pm.curBlock < pm.totalBlock}">
						<a href="noticeList.notice?curPage=${pm.lastPage+1}&b_cls=N&search_txt=${search_txt}"><img alt="btn_next2" src="/MyPrice/image/myprice_trend/btn_next2.gif"></a>
					</c:if>
				
				</div>
			</div>
		</section>
	</section>
	
<%@ include file="/template/footer_mp.jsp"  %>
</body>
</html>