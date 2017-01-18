<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
$(function() {
    $('#basket').on('click',function(){ 
        alert("데이터 전송~ "); 
       $.ajax({ 
         url:'BasketFinal2.price',
         type:'post',
         data:{ 
                entpId: $("#entpId").val(),
                code: $("#select1").val(),
         } , 
         success : function(data){ 
        	 		$("#goodName").html(data);
        	 		
         } , 
         error : function(){
                   alert('실패'); 
         } 
  });
}); 
}); 
</script>

                         <table id="final" border="1">
                         	<tr>
                         		<td id="fa1">상품명</td>
                         		<td id="fa2">평균가격</td>
                         		<td id="fa3">현재가격</td>
                         	</tr>
							
								<c:forEach items="${goodName2}" var="i2">
								<tr>
                     				<td class="fa2">${i2.goodName}</td>
                     				<td class="fa2">${i2.goodavrprice}</td>
                     				<td id="fa4">${i2.goodPrice}</td>
                         		</tr>
              					 </c:forEach>
								
                    
                         </table>