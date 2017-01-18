<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
	<li class="sm_list_m" id=""><a href = "/MyPrice/customCenter/notice/noticeList.notice?curPage=1&b_cls=N">공지사항</a></li>
	<li class="sm_list_m" id=""><a href = "/MyPrice/customCenter/notice/noticeList.notice?curPage=1&b_cls=F">FAQ</a></li>
	<li class="sm_list_m" id=""><a href = "/MyPrice/customCenter/ask/askWrite.jsp">1:1문의</a></li>
	<c:if test="${mDto.id eq 'admin'}">
	<li class="sm_list_m" id=""><a href = "/MyPrice/customCenter/ask/askAdminList.ask?curPage=1">1:1문의 관리:admin</a></li>
	</c:if>
</ul>