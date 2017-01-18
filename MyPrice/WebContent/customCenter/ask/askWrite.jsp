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

<script src="/MyPrice/SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript">
$(function(){
    //전역변수선언
    var editor_object = [];
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: editor_object,
        elPlaceHolder: "contents",	// 해당 태그 id
        sSkinURI: "/MyPrice/SE2/SmartEditor2Skin.html",	//  SmartEditor2Skin.html 파일 위치 수정
        htParams : {
            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseToolbar : true,             
            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseVerticalResizer : true,     
            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
            bUseModeChanger : true, 
        }
    });    
  //전송버튼 클릭이벤트
    $("#btn_submit").click(function(){	// 버튼 id명 변경
        //id가 smarteditor인 textarea에 에디터에서 대입
        editor_object.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);	// 해당 태그 id명 변경
		
        if ('${mDto.email_yn}' == 'N'){
        	alert('마이페이지에서 이메일수신여부를 동의해 주세요.');
    	}else if( $('#writer').val().trim()=="" ) {
        	alert('작성자를 입력해 주세요');
			$("#writer").focus();
        }else if( $('#email').val().trim()=="" ){
			alert('이메일을 입력해 주세요');
			$("#email").focus();
		}else if ($('#title').val().trim()=="" ){
			alert('제목을 입력해 주세요');
			$("#title").focus();
		}else if ($('#contents').val().trim()=="" ){
			alert('내용을 입력해 주세요');
			$("#contents").focus();
		}else {
        	$("#frm").submit(); // form submit
		}
    });
    $("#btn_ask_list").click(function(){
    	location.href = "askList.ask?curPage=1&writer=${mDto.id}";
    });
    
});

</script>
</head>
<body>

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
			<form action="askWrite.ask" method="post" id="frm">
				<table id="t_write">
					<tr><td class="t_l">분류</td>
						<td class="t_r">
						<select name="c_cls">
							<option value="A" selected>이용문의</option>
							<option value="B">회원관리</option>
							<option value="Z">기타</option>
						</select>
						</td>
					</tr>
					
					<tr><td class="t_l">작성자</td>
						<td class="t_r">
							<c:if test="${not empty mDto}">
							<input type="hidden" name="member_yn" value="Y">
							<input type="text" id="writer" name="writer" size=20px maxlength="20" value="${mDto.id}" readonly="readonly">
							</c:if>
							<c:if test="${empty mDto}">
							<input type="hidden" name="member_yn" value="N">
							<input type="text" id="writer" name="writer" size=20px>
							</c:if>
						</td></tr>
					<tr><td class="t_l">이메일</td>
						<td class="t_r"><input type="text" id="email" name="email" size=30px value="${mDto.email}"><p style="color:red;font-size:14px;padding-left:10px">* 답변내용은 입력된 이메일주소로 전송됩니다.</p></td></tr>
					<tr><td class="t_l">제목</td>
						<td class="t_r"><input type="text" id="title" name="title" maxlength="500"></td></tr>
					<tr><td class="t_l">내용</td>
						<td class="t_r"><textarea id="contents" class="contents" name="contents"></textarea></td>
						
					<tr><td id="t_null" colspan="2"></td></tr>
					<tr><td class="t_l">
						<c:if test="${not empty mDto}">
							<input type="button" id="btn_ask_list" class="btn_style" value="내 문의">
						</c:if></td>
						<td class="t_r"><input type="button" id="btn_submit" class="btn_style" value="글 작성"></td></tr>
				</table>
			</form>
			</div>
		</section>
	</section>
	
<%@ include file="/template/footer_mp.jsp"  %>
</body>
</html>