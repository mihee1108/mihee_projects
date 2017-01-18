<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="t_notice">
	<tr><th class="td_n_out t_n_h"></th><th class="td_n_title t_n_h">공지사항</th><th class="td_n_date t_n_h"><a href = "/MyPrice/customCenter/notice/noticeList.notice?curPage=1&b_cls=N">더보기 &gt;&gt;</a></th></tr>
	<c:forEach items="${list}" var="n" varStatus="n_num">
		<tr>
			<td class="td_n_out"></td>
			<td class="td_n_title"><a href = "/MyPrice/customCenter/notice/noticeList.notice?curPage=1&b_cls=N&num=${n.num}">${n.title}</a></td>
			<td>${n.reg_date}</td>
		</tr>	
	</c:forEach>
</table>
				