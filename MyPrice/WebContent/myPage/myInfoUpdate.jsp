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
	
		var ckPw = false;
		var ckPhone = true;
		var ckBirthday = true;
		
		// PW 두칸 확인 : 4~20자 _(underscore), 영어, 숫자만 가능
		$('.onlyAlphabetAndNumber').keyup(function(event){
			if(!/^[a-zA-Z0-9,_]{4,20}$/.test($(this).val()) ) {
				$("#p_pw").text("4~20자리 영문, _, 숫자만 가능합니다.");
				$(this).focus();
				ckPw = false;
			}else {
				
			}
		});
		// 비번 체크사항 : 일치여부
		var pw = $("#pw").val();
		var pwc = $("#pwc").val();
		$("#pw").keyup(function(){
			$("#pwc").val("");
			ckPw = false;
		});
		$("#pwc").keyup(function(){
			pw = $("#pw").val();
			pwc = $("#pwc").val();
			p_pwc = $("#p_pwc").val();
			if(pw==pwc){
				$("#p_pwc").text("* 비밀번호가 일치 합니다.");
				//p_pwc.innerText = "* 비밀번호가 일치 합니다.";
				ckPw = true;
			}else {
				$("#p_pwc").text("* 비밀번호가 일치 하지 않습니다.");
				ckPw = false;
			}
		});
		
		// 숫자만 가능 : 핸드폰번호 2칸
		$(".onlyNumber").keyup(function(event){
			if(!/^[0-9]{0,4}$/.test($(this).val()) ) {
				alert("숫자만 입력 가능합니다.");
				$(this).val("");
				ckPhone = false;
			}else {
				ckPhone = true;
			}
        });
		
		// 생년월일 8글자 확인
		$("#birthday").keyup(function(event){
			if(!/^[0-9]{0,8}$/.test($(this).val()) ) {// 숫자 & 8글자만 가능
				$(this).val("");
				ckBirthday = false;
			}else{
				ckBirthday = true;
			}
        });
		// 관심점포 search	: 리턴받을 점포코드 & 점포명 id 보내기 
		$("#search_store_1").click(function(){
			window.open("/MyPrice/member/searchItem.jsp?search_item=store&input_id=store_1&input_name=entpname_1", "점포찾기", "width=850,height=600");
		});
		$("#search_store_2").click(function(){
			window.open("/MyPrice/member/searchItem.jsp?search_item=store&input_id=store_2&input_name=entpname_2", "점포찾기", "width=850,height=600");
		});
		$("#search_store_3").click(function(){
			window.open("/MyPrice/member/searchItem.jsp?search_item=store&input_id=store_3&input_name=entpname_3", "점포찾기", "width=850,height=600");
		});
		

		// 필수항목 null여부 & 약관체크 확인
		$("#btn_submit").click(function(){

			if( !ckPw ){
				alert('패스워드를 재설정해 주세요.');
				$("#pw").focus();
			}else if(!ckPhone){
				alert('핸드폰 번호를 재설정해 주세요.');
				$("#phone_2").focus();
			}else if(!ckBirthday) {
				alert('생년월일을 재설정해 주세요.');
				$("#birthday").focus();
			}else if( $('#email').val()=="" ){
				alert('이메일을 입력해 주세요');
				$("#email").focus();
			}else {
				//alert($("input[name=store_1]").val() +"/"+$("input[name=store_2]").val() +"/"+$("input[name=store_3]").val());
				document.frm.submit();
			}

		});
		
});
</script>

<%@ include file="/template/header_mp.jsp"  %>

	<section id = "center">
		<section id="submenu">
			<div id="sm_image"><p>마이페이지</p></div>
			<div id="sm_list">
				<%@ include file="/template/sm_list_login.jsp"  %>
				
			</div>
		</section>
		<form action="myInfoUpdate.member" method="post" name="frm">
		<section id="info">
			<div id="info_image"><p>내 정보</p></div>
			<div id="info_list">
				<table>
					<tr><td class="t_l_g1">* 필수정보 *</td><td></td></tr>
					<tr><td class="t_l">아이디</td>
						<td class="t_r_items"><input type="text" id="id" name="id" size=20px value="${mDto.id}" readonly="readonly"></td></tr>
					<tr><td class="t_l">비밀번호</td>
						<td class="t_r_items"><input type="password" id="pw" name="password" class="onlyAlphabetAndNumber" size=20px maxlength="20"><p id="p_pw"></p></td></tr>
					<tr><td class="t_l">비밀번호 확인</td>
						<td class="t_r_items"><input type="password" id="pwc" class="onlyAlphabetAndNumber" size=20px maxlength="20"><p id="p_pwc"></p></td></tr>
					<tr><td class="t_l">핸드폰 번호</td>
						<td>
						<select id="phone_1" name="phone_1">
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="019">019</option>
						</select>	
						 - <input type="text" id="phone_2" name="phone_2" class="onlyNumber" size=3px maxlength="4" value="${mDto.phone_2}">
						 - <input type="text" id="phone_3" name="phone_3" class="onlyNumber" size=3px maxlength="4" value="${mDto.phone_3}">
						</td>
					</tr>
					<tr><td class="t_l">성별</td>
						<td>
							<input type="radio" name="gender" value="F">여성
							<input type="radio" name="gender" value="M">남성
						</td></tr>
					<tr><td class="t_l">생년월일</td>
						<td class="t_r_items"><input type="text" id="birthday" name="birthday" size=10px maxlength="8" value="${mDto.birthday}"><p>* 8자리 숫자로 입력해 주세요. (ex: 1990년 04월 05일 => 19900405)</p></td></tr>
					<tr><td class="t_l">이메일주소</td>
						<td><input type="text" id="email" name="email" size=30px value="${mDto.email}"></td></tr>
					<tr><td class="t_l">이메일 수신</td>
						<td class="t_r_items">
							<select name="email_yn">
								<option value="Y">예</option>
								<option value="N">아니오</option>
							</select>
							<p>* 가격알람 정보가 이메일로 수신됩니다.</p>
						</td></tr>
					<tr><td class="t_l">문자 수신</td>
						<td class="t_r_items">
							<select name="sms_yn">
								<option value="Y">예</option>
								<option value="N">아니오</option>
							</select>
							<p>* 가격알람 정보가 문자로 수신됩니다.</p>
						</td></tr>

					<tr><td id="t_null" colspan="2"></td></tr>
					<tr><td class="t_l_g2">* 선택정보 *</td><td>관심점포를 설정해 주세요. (ex : 자주가는 점포, 가까운 점포 등) </td></tr>
					<tr><td class="t_l"></td><td class="t_r_items"><p>관심점포 삭제는 [찾기] 화면에서 점포 선택을 하지 않고 저장하시면 빈칸으로 삭제됩니다.</p></td></tr>
					<tr><td class="t_l">관심점포</td>
						<td class="t_r_items"><input type="text" id="entpname_1" size=30px value="${mDto.entpname_1}" readonly="readonly"><input type="button" id="search_store_1" class="btn_style" value="찾기"><p>[ 내 주요점포 ]</p></td></tr>
					<tr><td class="t_l"></td>
						<td class="t_r_items"><input type="text" id="entpname_2" size=30px value="${mDto.entpname_2}" readonly="readonly"><input type="button" id="search_store_2" class="btn_style" value="찾기"></td></tr>
					<tr><td class="t_l"></td>
						<td class="t_r_items"><input type="text" id="entpname_3" size=30px value="${mDto.entpname_3}" readonly="readonly"><input type="button" id="search_store_3" class="btn_style" value="찾기"></td></tr>
					
					<tr><td id="t_null" colspan="2"></td></tr>
					<tr><td class="t_l">
							<input type="hidden" name="out_yn" value="${mDto.out_yn}">
							<input type="hidden" id="store_1" name="store_1" value="${mDto.store_1}">
							<input type="hidden" id="store_2" name="store_2" value="${mDto.store_2}">
							<input type="hidden" id="store_3" name="store_3" value="${mDto.store_3}">
						</td>
						<td><input type="button" id="btn_submit" class="btn_style" value="저    장"></td></tr>
				</table>
			</div>
		</section>
		</form>
	</section>
	
<%@ include file="/template/footer_mp.jsp"  %>
</body>
</html>