<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	$(document).ready(function(){
		$('#entpBrandCode').on('change', function(){
			$.ajax({
				url:'ProductStore.price',
				Type:'post',
				data:{
					store: $('#entpBrandCode').val(),
					storeCode: $('#entpAreaCode').val(),
				},
				success:function(result){
					$('#entpId').html(result);
				},
				error:function(){
					alert("선택하지 지역과 업체에 맞는 판매점이 존재하지 않습니다.");
				}
			});
		});
		
		//추가
		$('#entpAreaCode').on('change', function(){
			$.ajax({
				url:'ProductStore.price',
				Type:'post',
				data:{
					store: $('#entpBrandCode').val(),
					storeCode: $('#entpAreaCode').val(),
				},
				success:function(result){
					$('#entpId').html(result);
				},
				error:function(){
					alert("선택하지 지역과 업체에 맞는 판매점이 존재하지 않습니다.");
				}
			});
		}); //추가끝
	});
</script>

         <div id="info_check_detail">
             <!-- 지역 선택 필터-->  
            <div class="filter">
               <h4>지역</h4>
               <select name="entpAreaCode" id="entpAreaCode"  class="filter2" title="지역 선택">
					<option value="">--선택하세요--</option>
					<c:forEach items="${entpArea}" var="entpA">
						<option value="${entpA.code}">${entpA.codeName}</option>
					</c:forEach>
			   </select>
            </div>
            
            <!-- 업체 선택 필터-->
            <div class="filter">
               <h4>업체</h4>
               <select id="entpBrandCode" name="entpBrandCode"  class="filter2" title="업체 선택">
					<option value="">--선택하세요--</option>
					<c:forEach items="${entpBrand}" var="entpB">
						<option value="${entpB.code}">${entpB.codeName}</option>
					</c:forEach>
			  </select>
            </div>
            
            <!-- 판매점 선택 필터-->
            <div class="filter">
            	<h4>판매점</h4>
            	<select id="entpId" name="entpName" class="filter2" title="판매점 선택">
            	</select>
            </div>
         </div>
      </section>           
   