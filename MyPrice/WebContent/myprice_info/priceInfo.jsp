<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Price | 모든 물건들의 가격을 알고 싶어 하는 당신을 위한 사이트</title>

<link rel = "stylesheet" type="text/css" href="/MyPrice/css/reset.css">
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/temp.css">
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/temp_info.css">
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/myprice_info/infoList.css">

<!--  jQuery UI CSS파일  -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<!-- jQuery 기본 js파일 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<!-- jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.1.js"></script> 
<script> 
$(document).ready(function(){
    $('#select1').on('change', function() {
        $.ajax({
           url:'ProductList.price',
           Type:'post',
            data:{ 
                 select13: $('#select1').val(),
          },
           success:function(data){
              $("#select2").html(data);
              /* 초기화 값 있으니까 그것을 넘겨주고 받아주자. */
              //추가 script
              $.ajax({
            	  url:'ProductListSearch.price',
              type:'post',
              data:{
                 select_detailer:$('#select2').val(),
                 },
              success:function(result){
             	 $('#select3').html(result);
             	 
             	$('#findBtn').on('click', function(){
                	$.ajax({
                		url:'ProductFianl.price',
                		type:'post',
                		data:{
                			entpAreaCode:$('#entpAreaCode').val(),
                			entpId:$('#entpId').val(),
                			goodName:$('#select3').val(),	
                		},
                		success:function(result2){
                			$('#goodName').html(result2);
                		},
                	}) //$.ajax끝.
                }); //$('#findBtn').on('click', function()끛
              }
              });
              //추가 script 끝.
              
              $('#select2').on('change', function(){
            	  
                  $.ajax({
                     url:'ProductListSearch.price',
                     type:'post',
                     data:{
                        select_detailer:$('#select2').val(),
                     },
                     success:function(result){
                    	 $('#select3').html(result);
             
                        /* 여기는 셀릭트3번을 눌렀을때 아래DIV에 뿌려주기 */
                         $('#findBtn').on('click', function(){
                        	$.ajax({
                        		url:'ProductFianl.price',
                        		type:'post',
                        		data:{
                        			entpAreaCode:$('#entpAreaCode').val(),
                        			entpId:$('#entpId').val(),
                        			goodName:$('#select3').val(),	
                        		},
                        		success:function(result2){
                        			$('#goodName').html(result2);
                        		},
                        		error : function(){
                        			alert('실패');
                        		}
                        	}) //$.ajax끝.
                        }); //$('#findBtn').on('click', function()끛
                     },//success:function(result)끝
                     //$.ajax끝.
                  });
               });
           },
            
           
        });
    });
    
});

</script>

</head>
<body>
<%@ include file="/template/header_mp.jsp"  %>
 <section id = "center">
      <section id="submenu">
         <div id="sm_image"><p>생필품가격정보</p></div>
         
<%@ include file="/template/common/common.jsp" %>

<section id="info">
           <div id="info_image"><p>생필품가격정보</p></div>
            
           <div id="info_check">
      	<table id="check_1"></table>
            <table id="check_2">
               <tr>
                  <td>
                     <select id="select1" class="filter2" name="select1">
                     <option value="">--선택하세요--</option>
                        <c:forEach items="${list2}" var="i">
                              <option value="${i.code}">${i.codeName}</option>
                           </c:forEach>                 
                     </select>
                     
	                  <select id="select2" class="filter2" name="entpBrandCodee">
	                  
	                  </select>
	                  <select id="select3" class="filter2" name="detail"></select>
                  </td>
                  <td>
               </tr>
            </table>
            <table id="check_3">
            <tr>
               	 <td id="td2"><button id="findBtn">검색</button></td>
               </tr>
            </table>
      </div>
            <div id="goodName">
             	</div>
                       
                        
            
            
      <!-- 게시판 보여주기 끝 -->      
       
   </section>
</section>
<%@ include file="/template/footer_mp.jsp"  %>
</body>
</html>