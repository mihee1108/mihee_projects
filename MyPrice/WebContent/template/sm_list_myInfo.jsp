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
	<li class="sm_list_m" id=""><a href = "/MyPrice/myPage/myInfo.jsp">내 정보</a></li>	
	<c:if test="${mDto.id eq 'admin'}">
	<li class="sm_list_m" id=""><a href = "/MyPrice/myPage/admin_alarm.jsp">회원가격알람:admin</a></li>
	<li class="sm_list_m" id=""><a href = "/MyPrice/myPage/admin_member.jsp">회원관리:admin</a></li>
	</c:if>
</ul>