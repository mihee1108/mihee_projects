<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
                         <table id="final" border="1">
                         	<tr>
                         		<td class="fa1">현재 가격</td>
                         		<td class="fa1">1주전 가격</td>
                         		<td class="fa1">1달전 가격</td>
                         		<td class="fa1">3달전 가격</td>	
                         		
                         		<td class="fa1">가격알람</td>
                         	</tr>
							
							<tr>
                         		<td class="fa2">${thisweek_price}</td>
                         		<td class="fa2">${before1w_price}</td>
                         		<td class="fa2">${before1m_price}</td>
                         		<td class="fa2">${before3m_price}</td>
                         		<td class="fa2"><input type="button" value="가격알람" id="btn_alarmInsert"></td>
                         	</tr>
                         </table>
                         <div id="c_searchStore">
                         </div>
<script>
$("#btn_alarmInsert").click(function(){
	//alert("클릭함");
	window.open("/MyPrice/myPage/myAlarmPrices.alarm?goodid=${goodId}&entpid=${entpId}&inspectday=${fris}", "MyPrice", "width=1050,height=270");
	 });
	 $(document).ready(function(){
		 $('#btn_basketPrice').on('click', function(){
			 if(confirm("장바구니에 추가하시겠습니까?")){
				 $.ajax({
					 url:'basketPrice2.price',
					 Type:'post',
					 data:{
						 
					 }
				 })
			 }else{
				 return;
			 }
		 });
	 });
	//indexSearchStore.index _미희 작업 부분
	   $("#c_searchStore").load("goGrape.price", {
	      message:"진입성공",
	      today:"${thisweek_price}", //현재가격 이곳에 파라미터로 받은 값을 넣어주면 됨
	  	  week:"${before1w_price}", //1주전가격 
	  	  month:"${before1m_price}", //1달전가격 
	  	  months:"${before3m_price}",  //3달전가격 
	      },
	      function(result){
	         $("#c_searchStore").html(result);
	      });
</script>





