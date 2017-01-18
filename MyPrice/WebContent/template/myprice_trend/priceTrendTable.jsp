<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		<!-- 테이블 -->
		<table id="t2">
			<tr id="info_list_check">
					<td>상품명</td>
					<td>선택한업체가격</td>
					<td>할인여부</td>
					<td>1+1여부</td>
			</tr>
			
			<c:forEach var="list" items="${getPriceInfo}" varStatus="status">
			 		<tr id="info_list_table">
			 			<td>${getGoodId[status.index]}</td>
						<td>${list.goodPrice} 원 </td>
						<td>${list.goodDcYn}  </td>
						<td>${list.plusoneYn}</td>
					</tr>
		 </c:forEach>		
		</table>	
	