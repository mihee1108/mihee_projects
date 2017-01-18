<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table_priceInfo">
	<tr class="table_t" colspan="7"><p class="table_t">${smlclscodename} 주간 가격정보</p></tr>
    <tr><td class="table_h name">상품</td><td class="table_h price">단위</td><td class="table_h price">이번주</td><td class="table_h price">등락율</td><td class="table_h price">1주일전</td><td class="table_h price">1개월전</td><td class="table_h price">3개월전</td></tr>
    <c:forEach items="${list}" var="p">
	<tr id="${p.goodid}" class="tr_priceInfo">
		<td id="td_goodname${p.goodid}" class="td_priceInfo">${p.goodname}</td>
		<td class="td_priceInfo">${p.gooddan}</td>
		<td id="td_thisw${p.goodid}" class="td_priceInfo">
			<c:if test="${p.thisweek_price eq 0}">x</c:if>
			<c:if test="${p.thisweek_price ne 0}">${p.thisweek_price}</c:if>
		</td>
		<td class="td_priceInfo">${p.price_gap}%</td>
		<td id="td_before1w${p.goodid}" class="td_priceInfo">
			<c:if test="${p.before1w_price eq 0}">x</c:if>
			<c:if test="${p.before1w_price ne 0}">${p.before1w_price}</c:if></td>
		<td id="td_before1m${p.goodid}" class="td_priceInfo">
			<c:if test="${p.before1m_price eq 0}">x</c:if>
			<c:if test="${p.before1m_price ne 0}">${p.before1m_price}</c:if></td>
		<td id="td_before3m${p.goodid}" class="td_priceInfo">
			<c:if test="${p.before3m_price eq 0}">x</c:if>
			<c:if test="${p.before3m_price ne 0}">${p.before3m_price}</c:if></td>
	</tr>	
	</c:forEach>	
</table>
