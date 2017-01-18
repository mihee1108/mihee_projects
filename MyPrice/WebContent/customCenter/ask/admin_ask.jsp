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
<style type="text/css">
.title {
	width: 40%;
}
</style>
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
	$("#btn_search").click(function(){
		location.href = "/MyPrice/customCenter/ask/askAdminList.ask?curPage=1&search_txt="+$("#search_txt").val();
	});
	
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
	
	$(".btn_admin_reply").click(function(){
		location.href = "askReplyForm.ask?num=" + $(this).attr("id") + "&curPage=" + ${curPage};
	});
	$(".btn_admin_mod").click(function(){
		location.href = "askModForm.ask?num=" + $(this).attr("id") + "&curPage=" + ${curPage};
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
			<div id="info_image"><p>1:1문의 관리</p></div>
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
					<tr><th class="td_out"></th><th class="num">번호</th><th class="num">답변</th><th class="num">회원</th><th class="num">작성자</th><th class="title">제목</th><th class="reg_date">작성날짜</th></tr>

					<c:forEach items="${list}" var="a" varStatus="num">
						<tr>
							<td></td>
							<td>${a.num}</td>
							<td>${a.reply_yn}</td>
							<td>${a.member_yn}</td>
							<td>${a.writer}</td>
							<td class="td_title" id="${a.num}">${a.title}<input type="hidden" value="${a.num}"></td>
							<td>${a.reg_date}</td>
						</tr>
						<tr>
							<td colspan="8" id="td_show${a.num}" class="td_contents"><p>${a.contents}</p></td>
						</tr>
						<tr>
							<td colspan="8" id="td_r_show${a.num}" class="td_contents"><li>[ 답변내용 ]</li><br><p>${a.reply_contents}</p><br>
							<c:if test="${a.reply_yn eq 'N'}">
								<button id="${a.num}" class="btn_style btn_admin_reply">답변:admin</button>
							</c:if>
							<c:if test="${a.reply_yn eq 'Y'}">
								<button id="${a.num}" class="btn_style btn_admin_mod">수정:admin</button>
								<li>[ 답변일자 : ${a.reply_date} ]</li>
							</c:if>
							</td>
						</tr>
					</c:forEach>	
					
				</table>
				<div id="paging">
			
					<c:if test="${pm.curBlock > 1}">
						<a href="askAdminList.ask?curPage=${pm.startPage-1}&search_txt=${search_txt}"><img alt="btn_pre2" src="/MyPrice/image/myprice_trend/btn_prev2.gif"></a>
					</c:if>
					<c:forEach begin="${pm.startPage}" end="${pm.lastPage}" step="1" var="i"> <!-- for(int i=0; i<11; i++){} -->
						<a href="askAdminList.ask?curPage=${i}&search_txt=${search_txt}">${i}</a>
					</c:forEach>	
					<c:if test="${pm.curBlock < pm.totalBlock}">
						<a href="askAdminList.ask?curPage=${pm.lastPage+1}&search_txt=${search_txt}"><img alt="btn_next2" src="/MyPrice/image/myprice_trend/btn_next2.gif"></a>
					</c:if>
				
				</div>
			</div>
		</section>
	</section>
	
<%@ include file="/template/footer_mp.jsp"  %>
</body>
</html>