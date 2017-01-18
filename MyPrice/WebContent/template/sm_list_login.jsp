<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
$(function(){
	/* li - id가 셋팅이 안되는 듯 */
	/* 서브메뉴 한글이름도 셋팅되게 하면 좋을 듯 */
	$(".sm_list_m").click(function(){
		$(this).attr("id", "sm_list_on");
		
		//alert(this.innerHTML + " : " + $(this).attr("id") );
		
	});
	
});

</script>

<ul>
	<li class="sm_list_m" id=""><a href = "/MyPrice/member/login.jsp">로그인</a></li>
	<li class="sm_list_m" id=""><a href = "/MyPrice/member/searchId.jsp">아이디 찾기</a></li>
	<li class="sm_list_m" id=""><a href = "/MyPrice/member/searchPw.jsp">비밀번호 찾기</a></li>
	<li class="sm_list_m" id=""><a href = "/MyPrice/member/join.jsp">회원가입</a></li>
</ul>