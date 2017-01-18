<%@page import="java.util.ArrayList"%>
<%@page import="mp.controller.PriceController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		<h1 align="center">${message}</h1>
		<table id="t2">
			
         
         		<tr id="info_list_check">
					<td>업체명</td>
					<td>주소</td>
				</tr>
				<!-- 만약 할인 정보가 있으면 떠야함 -->
				<c:if test="${not empty priceInfo}"> 
				<tr id="info_list_check2">
					<td>상품명</td>
					<td>할인가격</td>
				</tr>
				</c:if>

				<c:forEach items="${saleInfoList}" var="dto"> 
				<tr>
					<td>${dto.entpName}</td>
					 <!-- 기본 주소 및 지도  -->
					<td>${dto.plmkAddrBasic} 
					 <a href="#" onclick="javascript:window.open('/MyPrice/template/myprice_trend/EntpMap.jsp?x=${dto.xMapCoord}&y=${dto.yMapCoord}','new','left=50, top=50, width=800, height=600')">
					<input type="image" src="/MyPrice/image/myprice_trend/ico_map.png" alt="map" style="vertical-align:middle">
					</a>
 					</td>
				</tr>
				<!-- 만약 할인 정보가 있으면 떠야함 -->
				<c:if test="${not empty priceInfo}"> 
				<c:forEach var="list" items="${priceInfo}" varStatus="status">
			 		<tr>
			 			<td>${goodName[status.index]}</td>
						<td>${list.goodPrice} 원 </td>
					</tr>
				</c:forEach>
				
				
						
			  </c:if> 
				</c:forEach>

				
	
		</table>
			