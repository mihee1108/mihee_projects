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
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/member/join.css">
</head>
<body>
<c:if test="${empty mDto}">
	<script>
	alert("로그인 먼저 해주세요.");
	//history.go(-1);
	location.href = "/MyPrice/index_MyPrice.jsp";
	</script>
</c:if>
<script type="text/javascript">
$(function(){
	
	// 개인정보 셋팅
	var phone_1 = document.getElementsByName("phone_1")[0];
	for (var i=0; i<phone_1.length; i++) {
		if (phone_1[i].value == "${mDto.phone_1}") {
			phone_1[i].selected = "selected";
			break;
		}
	}
	var gender = document.getElementsByName("gender");
	for (var i=0; i<gender.length; i++) {
		if (gender[i].value== "${mDto.gender}") {
			gender[i].checked = "checked";
			break;
		}
	}
	var email_yn = document.getElementsByName("email_yn")[0];
	for (var i=0; i<email_yn.length; i++) {
		if (email_yn[i].value== "${mDto.email_yn}") {
			email_yn[i].selected = "selected";
			break;
		}
	}
	var sms_yn = document.getElementsByName("sms_yn")[0];
	for (var i=0; i<sms_yn.length; i++) {
		if (sms_yn[i].value== "${mDto.sms_yn}") {
			sms_yn[i].selected = "selected";
			break;
		}
	}
	$("#btn_submit").click(function(){
		location.href="./myInfoUpdate.jsp";
	});
	
	$("#btn_out").click(function(){
		var out = confirm("모든 물건들의 가격을 알고 싶어 하는 당신을 위한 참가격 사이트를 정.말. 탈퇴 하시겠습니까?? (이후 같은 아이디로 재가입 불가)");
		
		if (out) {
			//alert("${mDto.id}" + "/" + "${mDto.out_yn}");
			document.frm.submit();
		}else {
			//history.go(-1);
		}
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
			<div id="info_image"><p>내 정보</p></div>
			<div id="info_list">
				
				<table>
					<tr><td class="t_l_g1">* 필수정보 *</td><td></td></tr>
					<tr><td class="t_l">아이디</td>
						<td class="t_r_items"><input type="text" id="id" name="id" size=20px value="${mDto.id}" readonly="readonly"></td></tr>
					<tr><td class="t_l">비밀번호</td>
						<td class="t_r_items"><input type="password" id="pw" name="password" class="onlyAlphabetAndNumber" size=20px maxlength="20" value="${mDto.password}" readonly="readonly"><p id="p_pw"></p></td></tr>
					<tr><td class="t_l">비밀번호 확인</td>
						<td class="t_r_items"><input type="password" id="pwc" class="onlyAlphabetAndNumber" size=20px maxlength="20" value="${mDto.password}" readonly="readonly"><p id="p_pwc"></p></td></tr>
					<tr><td class="t_l">핸드폰 번호</td>
						<td>
						<select id="phone_1" name="phone_1" disabled="disabled">
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="019">019</option>
						</select>	
						 - <input type="text" id="phone_2" name="phone_2" class="onlyNumber" size=3px maxlength="4" value="${mDto.phone_2}" readonly="readonly">
						 - <input type="text" id="phone_3" name="phone_3" class="onlyNumber" size=3px maxlength="4" value="${mDto.phone_3}" readonly="readonly">
						</td>
					</tr>
					<tr><td class="t_l">성별</td>
						<td>
							<input type="radio" name="gender" value="F" disabled="disabled">여성
							<input type="radio" name="gender" value="M" disabled="disabled">남성
						</td></tr>
					<tr><td class="t_l">생년월일</td>
						<td class="t_r_items"><input type="text" id="birthday" name="birthday" size=10px maxlength="8" value="${mDto.birthday}" readonly="readonly"><p>* 8자리 숫자로 입력해 주세요. (ex: 1990년 04월 05일 => 19900405)</p></td></tr>
					<tr><td class="t_l">이메일주소</td>
						<td><input type="text" id="email" name="email" size=30px value="${mDto.email}" readonly="readonly"></td></tr>
					<tr><td class="t_l">이메일 수신</td>
						<td class="t_r_items">
							<select id="email_yn" name="email_yn" disabled="disabled">
								<option value="Y">예</option>
								<option value="N">아니오</option>
							</select>
							<p>* 가격알람 정보가 이메일로 수신됩니다.</p>
						</td></tr>
					<tr><td class="t_l">문자 수신</td>
						<td class="t_r_items">
							<select id="sms_yn" name="sms_yn" disabled="disabled">
								<option value="Y">예</option>
								<option value="N">아니오</option>
							</select>
							<p>* 가격알람 정보가 문자로 수신됩니다.</p>
						</td></tr>

					<tr><td id="t_null" colspan="2"></td></tr>
					<tr><td class="t_l_g2">* 선택정보 *</td><td>관심점포를 설정해 주세요. (ex : 자주가는 점포, 가까운 점포 등) </td></tr>
					<tr><td class="t_l">관심점포</td>
						<td class="t_r_items"><input type="text" size=30px value="${mDto.entpname_1}" readonly="readonly"><input type="button" id="search_store_1" class="btn_style" value="찾기"><p>[ 내 주요점포 ]</p></td></tr>
					<tr><td class="t_l"></td>
						<td class="t_r_items"><input type="text" size=30px value="${mDto.entpname_2}" readonly="readonly"><input type="button" id="search_store_2" class="btn_style" value="찾기"></td></tr>
					<tr><td class="t_l"></td>
						<td class="t_r_items"><input type="text" size=30px value="${mDto.entpname_3}" readonly="readonly"><input type="button" id="search_store_3" class="btn_style" value="찾기"></td></tr>
					
					<tr><td id="t_null" colspan="2"></td></tr>
					<tr><td class="t_l">
						<form action="myInfoOut.member" method="post" name="frm">
						<input type="hidden" name="id" value="${mDto.id}">
						<input type="hidden" name="out_yn" value="${mDto.out_yn}">
						<input type="hidden" name="store_1" value="${mDto.store_1}">
						<input type="hidden" name="store_2" value="${mDto.store_2}">
						<input type="hidden" name="store_3" value="${mDto.store_3}">
						<input type="button" id="btn_out" class="btn_style" value="회원탈퇴">
						</form></td>
						<td><input type="button" id="btn_submit" class="btn_style" value="수    정"></td></tr>
				</table>
				
			</div>
		</section>
	</section>
	
<%@ include file="/template/footer_mp.jsp"  %>
</body>
</html>