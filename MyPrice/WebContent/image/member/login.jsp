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
<style type="text/css">
/* 별도 css 파일 없음 */
#login_box {
 width: 300px;
 margin: 0 auto;
 margin-top: 80px;
}
#login_box > div{
	width: 100%;
	height: 70px;
	margin-top: 10px;	
}

.login_txt{
	width: 100%;
	height: 50%;
	border: 1px #0066ff solid;
	overflow: hidden;
}
.login_txt > input {
	width:100%;
	border:none;
	position:relative;
	margin-left: 10px;
	margin-top: 10px; 
}
#login_empty_id, #login_empty_pw{
	color: red;
	font-size: 14px;
}

#btn_login {
	width:100%;
	height:80%;
	display:block;
	color:white;
	font-weight:bold;
	font-size:20px;
	background-color:#0066ff;
	border:none;
	text-align:center;
	cursor:pointer;
	border-radius:0;
}
#login_sub > a{
	float:left;
	margin-left: 20px;
	color:  #636363;
	text-decoration: none;/* 언더라인 없애고 */
	font-size: 14px;
}
</style>
<script type="text/javascript">
	$(function(){
		
		$('#id').keyup(function(event){
			$("#login_empty_id").css("display", "none");
        });
		$('#password').keyup(function(event){
			$("#login_empty_pw").css("display", "none");
        });
		
		$("#btn_login").click(function(){
			var result = true;
			//alert($("#id").val() + " / " + $("#password").val());
			if ($("#id").val() == "") {
				$("#login_empty_id").css("display", "block");
				result = false;
			};
			if ($("#password").val() == "") {
				$("#login_empty_pw").css("display", "block");
				result = false;
			};
			
			if ( result) {
				document.frm.submit();
			}
			
		});
	});
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
			<div id="info_image"><p>로그인</p></div>
			<div id="info_list">
			<form action="memberLogin.member" method="post" name="frm">
				<div id="login_box">
					<div>		
						<div class="login_txt"><input type="text" id="id" name="id" placeholder="아이디" tabindex="1" maxlength="20" size=30></div>
						<div id="login_empty_id" style="display:none;">아이디를 입력해주세요.</div>					
					</div>
					<div>
						<div class="login_txt"><input type="password" id="password" name="password" placeholder="비밀번호" tabindex="2" maxlength="20" size=30></div>
						<div id="login_empty_pw" style="display:none;">비밀번호를 입력해주세요.</div>
					</div>
					<div id="login_submit"><input type="button" id="btn_login" value="로그인" tabindex="3"></div>
					<div id="login_sub">
						<a href = "/MyPrice/member/searchId.jsp">아이디 찾기 </a>
						<a href = "/MyPrice/member/searchPw.jsp">비밀번호 찾기</a>
						<a href = "/MyPrice/member/join.jsp">회원가입</a>
					</div>
				</div>
			</form>	
			</div>
		</section>
		
	</section>
	
<%@ include file="/template/footer_mp.jsp"  %>
</body>
</html>