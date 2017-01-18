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
<script type="text/javascript">
$(function(){

	$("#btn_submit").click(function(){

		$("#result_DAO").load("memberSearch.member", {
								phone_1:$("#phone_1").val(),
								phone_2:$("#phone_2").val(),
								phone_3:$("#phone_3").val(),
								birthday:$("#birthday").val(),
								email:$("#email").val()
								});
		$("#result_DAO").css("border", "1px #0066ff solid");
		$("#result_DAO").css("font-weight", "bold");
		$("#result_DAO").css("font-size", "20px");
		$("#result_DAO").css("text-align", "center");
		$("#result_DAO").css("color", "#0066ff");
	});

})
</script>
</head>
<body>
<%@ include file="/template/header_mp.jsp"  %>

	<section id = "center">
		<section id="submenu">
			<div id="sm_image"><p>로그인</p></div>
			<div id="sm_list">
				<%@ include file="/template/sm_list_login.jsp"  %>
			</div>
		</section>
		<section id="info">
			<div id="info_image"><p>아이디 찾기</p></div>
			<div id="info_list">
				<table>
					<tr><td class="t_l_g1">* 확인정보 *</td><td>아래 항목을 모두 입력 후 확인버튼 클릭해 주세요.</td></tr>
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
					<tr><td class="t_l">생년월일</td>
						<td class="t_r_items"><input type="text" id="birthday" name="birthday" size=10px maxlength="8"><p>* 8자리 숫자로 입력해 주세요. (ex: 1990년 04월 05일 => 19900405)</p></td></tr>
					<tr><td class="t_l">이메일주소</td>
						<td><input type="text" id="email" name="email" size=30px></td></tr>
						
					<tr><td id="t_null" colspan="2"></td></tr>
					<tr><td class="t_l_g1">검색 아이디</td>
						<td><div id="result_DAO">
						
						</div></td></tr>										
					
					<tr><td id="t_null" colspan="2"></td></tr>
					<tr><td class="t_l"></td><td><input type="button" id="btn_submit" class="btn_style" value="검    색"></td></tr>
				</table>
			</div>
		</section>
		
	</section>
	
<%@ include file="/template/footer_mp.jsp"  %>
</body>
</html>