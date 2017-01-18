<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<table>
		<tr><td colspan="3"><p>* 검색된 결과 중 점포명을 선택하여 클릭 후 [점포선택] 버튼을 눌러주세요.</p></td></tr>
		<tr><th>점포명</th><th class="td_entpAddrBasic">점포 주소</th><th class="td_entpAddrDetail">점포 상세주소</th></tr>
		<c:forEach items="${list}" var="s" varStatus="s_num">
			<tr><td id="${s.entpid}" class="td_itemName">${s.entpname}</td><td>${s.plmkaddrbasic}</td><td>${s.plmkaddrdetail}</td></tr>
		</c:forEach>
	</table>
