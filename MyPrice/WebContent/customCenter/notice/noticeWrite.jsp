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

        // 이부분에 에디터 validation 검증
        
        $("#frm").submit(); // form submit
    })
});

</script>
</head>
<body>
<c:if test="${empty mDto or mDto.id ne 'admin'}">
	<script>
	alert("관리자 화면 입니다.");
	//history.go(-1);
	location.href = "/MyPrice/index_MyPrice.jsp";
	</script>
</c:if>
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
			<form action="noticeWrite.notice" method="post" id="frm">
				<input type="hidden" name="writer" value="${mDto.id}">
				<input type="hidden" name="b_cls" value="${param.b_cls}">
				<table id="t_write">
					<tr><td class="t_l">분류</td>
						<td class="t_r">
						<c:if test="${param.b_cls eq 'N'}">
						<select name="c_cls">
							<option value="P">필독</option>
							<option value="N" selected>일반</option>
						</select>	
						</c:if>
						<c:if test="${param.b_cls eq 'F'}">
						<select name="c_cls">
							<option value="A" selected>이용문의</option>
							<option value="B">회원관리</option>
							<option value="Z">기타</option>
						</select>
						</c:if>
						</td>
					</tr>
					<tr><td class="t_l">제목</td>
						<td class="t_r"><input id="title" type="text" name="title" maxlength="500"></td></tr>
					<tr><td class="t_l">내용</td>
						<td class="t_r"><textarea id="contents" name="contents" class="contents"></textarea></td>
						
					<tr><td id="t_null" colspan="2"></td></tr>
					<tr><td class="t_l"></td><td class="t_r"><input type="button" id="btn_submit" class="btn_style" value="글 작성"></td></tr>
				</table>
			</form>
			</div>
		</section>
	</section>
	
<%@ include file="/template/footer_mp.jsp"  %>
</body>
</html>