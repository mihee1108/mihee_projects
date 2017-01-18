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
	// 글 정보 셋팅
	var c_cls = document.getElementsByName("c_cls")[0];
	for (var i=0; i<c_cls.length; i++) {
		if (c_cls[i].value == "${aDto.c_cls}") {
			c_cls[i].selected = "selected";
			break
		}
	}
	
	var member_yn = document.getElementsByName("member_yn")[0];
	for (var i=0; i<member_yn.length; i++) {
		if (member_yn[i].value == "${aDto.member_yn}") {
			member_yn[i].selected = "selected";
			break;
		}
	}
	
	var reply_yn = document.getElementsByName("reply_yn")[0];
	for (var i=0; i<reply_yn.length; i++) {
		if (reply_yn[i].value == "${aDto.reply_yn}") {
			reply_yn[i].selected = "selected";
			break;
		}
	}
	$("#c_cls").attr("disabled", "disabled");
	$("#member_yn").attr("disabled", "disabled");
	$("#reply_yn").attr("disabled", "disabled");
	
	
    //전역변수선언
    var editor_object = [];
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: editor_object,
        elPlaceHolder: "reply_contents",	// 해당 태그 id
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
        editor_object.getById["reply_contents"].exec("UPDATE_CONTENTS_FIELD", []);	// 해당 태그 id명 변경

        if ($('#reply_contents').val().trim()=="<p>&nbsp;</p>" ){
			alert('내용을 입력해 주세요');
			$("#reply_contents").focus();
		}else {
			$("#c_cls").attr("disabled", false);
			$("#member_yn").attr("disabled", false);
			$("#reply_yn").attr("disabled", false);
			$("#reply_yn").val("Y");
			//$("div[name=contents]").val('${aDto.contents}');	// div value가 태그때문에 정상적으로 안 먹히므로
			//alert('${aDto.contents}');
			var reply_email_yn = confirm("메일답변을 전송 하시겠습니까??");
			if(reply_email_yn) {
				$("#frm").submit(); // form submit	
			}
        	
		}
    });
    
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
			<div id="info_image"><p>1:1문의 관리 - 답변</p></div>
			<div id="info_list">
			<form action="askReply.ask" method="post" id="frm">
				<input type="hidden" name="curPage" value="${curPage}">
				<input type="hidden" name="num" value="${aDto.num}">
				<input type="hidden" name="contents" value='${aDto.contents}'>
					
				<table id="t_write">
					<tr><td class="t_l">분류</td>
						<td class="t_r">
						<select id="c_cls" name="c_cls">
							<option value="A">이용문의</option>
							<option value="B">회원관리</option>
							<option value="Z">기타</option>
						</select>
						</td>
					</tr>
					<tr><td class="t_l">회원</td>
						<td class="t_r">
						<select id="member_yn" name="member_yn">
							<option value="Y">회원</option>
							<option value="N">비회원</option>
						</select>
						</td>
					</tr>
					<tr><td class="t_l">답변</td>
						<td class="t_r">
						<select id="reply_yn" name="reply_yn">
							<option value="Y">답변완료</option>
							<option value="N">미답변</option>
						</select>
						
						</td>
					</tr>
					
					<tr><td class="t_l">작성자</td>
						<td class="t_r"><input type="text" id="writer" name="writer" size=20px value="${aDto.writer}" readonly="readonly"></td></tr>
					<tr><td class="t_l">작성일자</td>
						<td class="t_r"><input type="text" id="reg_date" name="reg_date" size=20px value="${aDto.reg_date}" readonly="readonly"></td></tr>
					<tr><td class="t_l">이메일</td>
						<td class="t_r"><input type="text" id="email" name="email" size=30px value="${aDto.email}" readonly="readonly"></td></tr>
					<tr><td class="t_l">제목</td>
						<td class="t_r"><input type="text" id="title" name="title" value="${aDto.title}" readonly="readonly"></td></tr>
					<tr><td class="t_l">문의내용</td>
						<td class="t_r"><div id="contents" name="contents" class="contents" html='true'>${aDto.contents}</div></td>
					
					<tr><td id="t_null" colspan="2"></td></tr>
					<tr><td class="t_l">답변자</td>
						<td class="t_r"><input type="text" id="reply_writer" name="reply_writer" size=20px value="${mDto.id}" readonly="readonly"></td></tr>	
					<tr><td class="t_l">답변내용</td>
						<td class="t_r"><textarea id="reply_contents" class="contents" name="reply_contents">${aDto.reply_contents}</textarea></td>
					
					<tr><td id="t_null" colspan="2"></td></tr>
					<tr><td class="t_l"></td>
						<td class="t_r"><input type="button" id="btn_submit" class="btn_style" value="답변 보내기"></td></tr>
				</table>
			</form>
			</div>
		</section>
	</section>
	
<%@ include file="/template/footer_mp.jsp"  %>
</body>
</html>