<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Price | 모든 물건들의 가격을 알고 싶어 하는 당신을 위한 사이트</title>

<link rel = "stylesheet" type="text/css" href="/MyPrice/css/reset.css">
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/temp.css">
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/myprice_info/basketPrice.css">

<!--  jQuery UI CSS파일  -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<!-- jQuery 기본 js파일 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<!-- jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<script>
$(function() {
    $('#findBtn').on('click',function(){ 
         
       $.ajax({ 
         url:'BasketFinal.price',
         type:'post',
         data:{ 
                entpId: $("#entpId").val(),
                code: $("#select1").val(),
         } , 
         success : function(data){ 
        	 		$("#goodName").html(data);
        	 		
         } , 
         error : function(){
                   alert('현재 조회하신 정보가 존재하지 않습니다.'); 
         } 
  });
}); 
}); 
</script>
</head>
<body>
<%@ include file="/template/header_mp.jsp"  %>
<section id = "center">
		<section id="submenu">
			<div id="sm_image"><p>지역정보</p></div>
<%@ include file="/template/common/common.jsp" %>
		<!-- 정보 검색 테이블 -->
		<section id="info">
			<div id="info_image"><p>선택한 지역의 가격 정보</p></div>
			
			<div id="info_check">					
				<!-- <div id="font">내 장바구니 목록</div>
				<table id="check_1">
					<tr class = "basket_chart">
						<td class="t2" id="chartInfo">상품정보</td>
						<td class="t2" id="chartPrice">평균가</td>
						<td class="t2" id="areaPrice">내지역최저가격</td>
					</tr>					
				</table> -->
				<table id="check_1"></table>
				<table id="check_2">
					<tr>
						<td>
							<select id="select1" class="filter2" name="select1">
                       		 	<c:forEach items="${list2}" var="i">
                             	 	<option value="${i.code}">${i.codeName}</option>
                           </c:forEach>       
                           </select>
               	 <td id="td2"><button id="findBtn">검색</button></td>
                           </td>
					</tr>
				</table>
				<table id="check_3">
            <tr>
               </tr>
            </table>
            
			</div>
				<div id="goodName">
				</div>
</section>
		</section>
<%@ include file="/template/footer_mp.jsp"  %>
</body>
</html>