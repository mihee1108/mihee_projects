<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script type="text/javascript">
	$(function(){
	
		var ckId = false;
		var ckPw = false;
		var ckPhone = false;
		var ckBirthday = false;
		
		// 아이디 중복확인 버튼
		$('#id').keyup(function(event){
			ckId = false;
			$("#p_id").text("");
        });
		$("#checkId").click(function(){
			var idcheck = $("#id").val();
	
			if(!/^[a-zA-Z0-9,_]{4,20}$/.test($("#id").val()) ) {
				$("#p_id").text("4~20자리 영문, _, 숫자만 가능합니다.");
				$("#id").focus();
				$("#id").val("");
				ckId = false;
			}else {
				$.post("memberIdCheck.member", {id:idcheck}, function(results){
					results=results.trim();
					if(results=="0"){
						$("#p_id").text("사용 가능한 ID 입니다.");
						ckId = true;
					}else {
						$("#p_id").text("[" + idcheck+ "]는 이미 존재하는 ID 입니다.");
						$("#id").focus();
						$("#id").val("");
						ckId = false;
					}
				});
			}

		});
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
			if(!/^[0-9]{3,4}$/.test($(this).val()) ) {
				ckPhone = false;
			}else {
				ckPhone = true;
			}
        });
		
		// 생년월일 숫자 & 8글자 확인
		$("#birthday").keyup(function(event){
			if(!/^[0-9]{8}$/.test($(this).val()) ) {// 숫자 & 8글자만 가능
				ckBirthday = false;
			}else{
				ckBirthday = true;
			}
        });
		// 관심점포 search	: 리턴받을 점포코드 & 점포명 id 보내기 
		$("#search_store_1").click(function(){
			window.open("searchItem.jsp?search_item=store&input_id=store_1&input_name=entpname_1", "점포찾기", "width=850,height=600");
		});
		$("#search_store_2").click(function(){
			window.open("searchItem.jsp?search_item=store&input_id=store_2&input_name=entpname_2", "점포찾기", "width=850,height=600");
		});
		$("#search_store_3").click(function(){
			window.open("searchItem.jsp?search_item=store&input_id=store_3&input_name=entpname_3", "점포찾기", "width=850,height=600");
		});
		
		// 필수항목 null여부 & 약관체크 확인
		$("#btn_submit").click(function(){

			if ( !ckId ) {
				alert('아이디 중복체크를 통해 정상적인 아이디를 설정해 주세요.');
				$("#id").focus();
			}else if( !ckPw ){
				alert('패스워드를 재설정해 주세요.');
				$("#pw").focus();
			}else if(!ckPhone){
				alert('핸드폰 번호를 재설정해 주세요. (3~4자리의 숫자만 가능)');
				$("#phone_2").val("");
				$("#phone_3").val("");
				$("#phone_2").focus();
			}else if(!ckBirthday) {
				alert('생년월일을 재설정해 주세요. (8자리 숫자만 가능)');
				$("#birthday").val("");
				$("#birthday").focus();
			}else if( $('#email').val()=="" ){
				alert('이메일을 입력해 주세요');
				$("#email").focus();
			}else if (!(document.getElementById("cb_contract").checked)) {
				alert('약관에 동의해 주세요.');
				$("#cb_contract").focus();
			}else {
				//alert($("#store_1").val() +"/"+$("#entpname_1").val());
				document.frm.submit();
				
			}

		});
		
});
</script>

<%@ include file="/template/header_mp.jsp"  %>

	<section id = "center">
		<section id="submenu">
			<div id="sm_image"><p>회원가입</p></div>
			<div id="sm_list">
				<%@ include file="/template/sm_list_login.jsp"  %>
				
			</div>
		</section>
		<form action="memberJoin.member" method="post" name="frm">
		<section id="info">
			<div id="info_image"><p>회원가입</p></div>
			<div id="info_list">
				<table>
					<tr><td class="t_l_g1">* 필수정보 *</td><td></td></tr>
					<tr><td class="t_l">아이디</td>
						<td class="t_r_items"><input type="text" id="id" name="id" size=20px maxlength="20"><input type="button" class="btn_style" id="checkId" value="중복확인"><p id="p_id"></p></td></tr>
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
						 - <input type="text" id="phone_2" name="phone_2" class="onlyNumber" size=3px maxlength="4">
						 - <input type="text" id="phone_3" name="phone_3" class="onlyNumber" size=3px maxlength="4">
						</td>
					</tr>
					<tr><td class="t_l">성별</td>
						<td>
							<input type="radio" name="gender" value="F" checked>여성
							<input type="radio" name="gender" value="M">남성
						</td></tr>
					<tr><td class="t_l">생년월일</td>
						<td class="t_r_items"><input type="text" id="birthday" name="birthday" size=10px maxlength="8"><p>* 8자리 숫자로 입력해 주세요. (ex: 1990년 04월 05일 => 19900405)</p></td></tr>
					<tr><td class="t_l">이메일주소</td>
						<td><input type="text" id="email" name="email" size=30px></td></tr>
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
					<tr><td class="t_l">관심점포</td>
						<td class="t_r_items"><input type="text" id="entpname_1" name="entpname_1" size=20px readonly="readonly"><input type="button" id="search_store_1" class="btn_style" value="찾기"><p>[ 내 주요점포 ]</p></td></tr>
					<tr><td class="t_l"></td>
						<td class="t_r_items"><input type="text" id="entpname_2" name="entpname_2" size=20px readonly="readonly"><input type="button" id="search_store_2" class="btn_style" value="찾기"></td></tr>
					<tr><td class="t_l"></td>
						<td class="t_r_items"><input type="text" id="entpname_3" name="entpname_3" size=20px readonly="readonly"><input type="button" id="search_store_3" class="btn_style" value="찾기"></td></tr>
					
					<tr><td id="t_null" colspan="2"></td></tr>
					<tr><td class="t_l_g3">약관</td><td><input type="checkbox" id="cb_contract" size=10px>아래 약관의 모든 내용을 확인하였고, 모든 내용에 동의 합니다.</td><tr>
					<tr><td class="t_l"></td><td><textarea rows="5" cols="80" disabled="disabled">약관내용</textarea></td></tr>
					
					<tr><td id="t_null" colspan="2"></td></tr>
					<tr><td class="t_l">
							<input type="hidden" id="store_1" name="store_1">
							<input type="hidden" id="store_2" name="store_2">
							<input type="hidden" id="store_3" name="store_3">
						</td><td><input type="button" id="btn_submit" class="btn_style" value="회원가입"></td></tr>
				</table>
			</div>
		</section>
		</form>
	</section>
	
<%@ include file="/template/footer_mp.jsp"  %>
</body>
</html>