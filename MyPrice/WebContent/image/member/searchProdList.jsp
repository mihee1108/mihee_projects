<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<table>
		<tr><td colspan="4"><p>* 검색된 결과 중 상품명을 선택하여 클릭 후 [선택] 버튼을 눌러주세요.</p></td></tr>
		<tr><th>상품명</th><th class="td_codename">대분류</th><th class="td_codename">중분류</th><th class="td_codename">품목</th></tr>
		<c:forEach items="${list}" var="s" varStatus="s_num">
			<tr><td id="${s.goodid}" class="td_itemName">${s.goodname}</td><td>${s.codename_1}</td><td>${s.codename_2}</td><td>${s.codename_3}</td></tr>
		</c:forEach>
	</table>
