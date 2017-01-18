<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mp.productInfo.ProductInfoDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Price | 모든 물건들의 가격을 알고 싶어 하는 당신을 위한 사이트</title>
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/reset.css">
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/temp.css">
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/temp_info.css">
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/common/common_new.css">
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/common/info_check.css">
<!-- 미희 css 추가 2016.12.02 radio버튼 디자인 -->
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/myprice_priceTrend/goodSmlclsCode.css">

<!--  jQuery UI CSS파일  -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<!-- jQuery 기본 js파일 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<!-- jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>  


<script type="text/javascript">
$(function() {
    $('#findBtn').on('click',function(){ 
        
       $.ajax({ 
         url:'priceTrendList.price',
         type:'post',
         data:{ 
                entpArea: $("#entpAreaCode").val(),
                entpBrand: $("#entpBrandCode").val(),
                entpId: $("#entpId").val(),
         } , 
         success : function(data){ 
        	 		$("#info_list_table").html(data);
         } , 
         error : function(){ 
                   alert('실패'); 
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
			<div id="sm_image"><p>판매점 별 가격정보</p></div>
<%@ include file="/template/common/common.jsp" %>
<section id="info">
         <div id="info_image"><p>판매점 별 가격정보</p></div>
         <!-- 검색 버튼 및 간단소개글 -->
         
         <div id ="info_check">
    	 <table id="check_1">
               <tr>
                  <td id="td1">
                  <h2>[선택하신 판매점에서 판매하는 모든 상품가격 정보를 알 수 있습니다.]</h2>
                  </td>
                  <td id="td2"><button id="findBtn">검색</button></td>
               </tr>
      </table>
         </div>
         <br>
         <!-- 정보 검색 관련 부분  -->
         <div id="info_list">
         <!-- 정보 검색 결과 테이블  -->
         	<div id="info_list_table">
         	</div>
         </div>
      </section>
   </section>
<%@ include file="/template/footer_mp.jsp"  %>
</body>
</html>