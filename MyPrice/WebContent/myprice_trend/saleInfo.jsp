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

<!--  jQuery UI CSS파일  -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<!-- jQuery 기본 js파일 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<!-- jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>  


<script type="text/javascript">
$(function() {
	// enableDay 에 입력된 날짜외에는 모두 disable 됩니다.
	var enableDay = ["5-8-2016","12-8-2016","19-8-2016","26-8-2016", 
	                 "2-9-2016", "9-9-2016", "16-9-2016",
	                 "23-9-2016", "30-9-2016", "7-10-2016", "14-10-2016",
	                 "21-10-2016", "28-10-2016", "4-11-2016", "11-11-2016", 
	                 "18-11-2016", "25-11-2016", "2-12-2016"];
	function selectableDays(date) {
		dummy = date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear();
		if ($.inArray(dummy, enableDay) > -1) {
			return [true, ""];
		}

		return [false, ""];
	}

    $("#datepicker1").datepicker({
            dateFormat: 'yy-mm-dd',
            changeMonth: true, 
             dayNames: ['일요일','월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
             dayNamesMin: ['일','월', '화', '수', '목', '금', '토'], 
             monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
             monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
             minDate: "-5M", 
             maxDate: "0D", 
             beforeShowDay: selectableDays
            
     });
    $("#datepicker2").datepicker({
        dateFormat: 'yy-mm-dd',
        changeMonth: true, 
         dayNames: ['일요일','월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
         dayNamesMin: ['일','월', '화', '수', '목', '금', '토'], 
         monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
         monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
         minDate: "-4M", 
         maxDate: "0D", 
         beforeShowDay: selectableDays
        
 });
    
    $('#findBtn').on('click',function(){ 
       
       $.ajax({ 
         url:'saleInfoList.price',
         type:'post',
         data:{ 
                startDay: $("#datepicker1").datepicker().val(),
                endDay: $("#datepicker2").datepicker().val(),
                entpArea: $("#entpAreaCode").val(),
                entpBrand: $("#entpBrandCode").val(),
                entpId: $("#entpId").val(),
                curPage: 1,
                perPage: 10,
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
			<div id="sm_image"><p>할인정보</p></div>
<%@ include file="/template/common/common.jsp" %>
<section id="info">
         <div id="info_image"><p>생필품 할인정보</p></div>
         <div id ="info_check">
     <table id="check_1">
               <tr>
                  <td id="td1">
                  <h2>[각 주별 금요일 기준으로 할인정보가 업데이트 됩니다.]</h2>
                  <h3> * 할인정보가 없을 시 검색하신 판매점의 간략한 정보만 검색됩니다.</h3>
                  </td>
                  <td id="td2"><button id="findBtn">검색</button></td>
               </tr>
            </table>
            <table id="check_2">
               <tr>
                  <td><input type="text" id="datepicker1" class="datepicker"></td>
                  <td><input type="text" id="datepicker2" class="datepicker"></td>
               </tr>
            </table>
         </div>
         <br>
         <!-- 정보 검색 관련 부분  -->
         <div id="info_list">
         	<!-- 정보 검색 결과 테이블 -->
         	<div id="info_list_table">
         	
         	</div>
         </div>
      </section>
   </section>
<%@ include file="/template/footer_mp.jsp"  %>
</body>
</html>