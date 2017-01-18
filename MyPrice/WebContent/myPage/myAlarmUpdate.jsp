<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Price | 모든 물건들의 가격을 알고 싶어 하는 당신을 위한 사이트</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/reset.css">
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/member/alarm.css">
</head>
<body>
<c:if test="${empty mDto}">
	<script>
	alert("로그인 먼저 해주세요.");
	this.window.close();
	</script>
</c:if>
 
 <script type="text/javascript">
	
$(function(){// 페이지 처음 열었을 때 조회
	//alert("글번호 : ${param.num}");

	$('input[name="alarm_yn"]:radio[value="${rDto.alarm_yn}"]').prop('checked',true);
	$("select[name='alarm_price_ud']").val("${rDto.alarm_price_ud}").attr("selected", "selected");
	var alarm_sdate = $("input[name=alarm_sdate]").val();
	
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1;
	if (dd<10) {
		dd = '0' + dd;
	}
	if (mm < 10) {
		mm = '0' + mm;
	}
	var todayStr = today.getFullYear() + '-'+mm + '-'+dd 
	//	alert(" : " + todayStr + " / ${rDto.alarm_edate}");
	if ("${rDto.alarm_edate}" < todayStr ) {
		//alert("${rDto.alarm_edate}");
		$(".alarm_update").prop("disabled", true);
		$("#btn_submit").css("display", "none");
	}
	
	$("#btn_del").click(function(){
		var alarm_del = confirm("이 가격알람내역을 삭제하시겠습니까??");
		if (alarm_del) {
			$.post("myAlarmDel.alarm", {num:'${param.num}'}, function(results){
				results=results.trim();
				if(results>"0"){
					alert("가격알람 정보 삭제 성공!!");
					$(opener.document).find("#btn_search").trigger("click");
					window.close();
				}else {
					alert("가격알람 정보 삭제 실패");
				}
			});
		}
	});
	
	$("#btn_submit").click(function(){
		// 중복 체크 : id, goodid, entpid, alarm_price, alarm_sdate
		$.post("myAlarmUniqueCheck.alarm", {
			num:"${rDto.num}",
			id:"${rDto.id}",
			goodid:"${rDto.goodid}",
			entpid:"${rDto.entpid}",
			alarm_price:$("input[name=alarm_price]").val(),
			alarm_sdate:$("input[name=alarm_sdate]").val()
			}, function(results){
			results=results.trim();
			if(results>"0"){
				alert("중복데이터가 있습니다.\r[상품명, 점포명, 알람가격, 시작일자] 기준으로 중복여부 확인해 주세요.");	
				window.close();
			}else {
				if ($("input[name=alarm_price]").val()=="" || $("input[name=alarm_sdate]").val() == "" || $("input[name=alarm_edate]").val() == "") {
					// date 형식은 날짜형식에 맞지 않으면 value값이 ""으로 됨.
					alert("수정 할 가격알람 정보를 확인해 주세요.");
				}else if($("input[name=alarm_sdate]").val() > $("input[name=alarm_edate]").val()) {
					alert("알람종료일자는 알람시작일자보다 작을 수 없습니다.");
				}else if(todayStr > $("input[name=alarm_edate]").val()) {
					alert("알람종료일자는 오늘 날짜보다 작을 수 없습니다.");
				}else if(alarm_sdate > $("input[name=alarm_sdate]").val()) {
					alert("알람시작일자는 처음 설정한 시작일자보다 작을 수 없습니다.");
				}else {
				
					var alarm_update = confirm("변경된 내역으로 수정 하시겠습니까??");
					if (alarm_update) {
						//alert('${param.num}'+"/"+ $("input[name=alarm_yn]:checked").val() +"/"+$("input[name=alarm_price]").val() +"/"+$("select[name=alarm_price_ud]").val()+"/"+ $("input[name=alarm_sdate]").val() +"/"+ $("input[name=alarm_edate]").val());
						$.post("myAlarmUpdate.alarm", {
							num:'${param.num}',
							alarm_yn:$("input[name=alarm_yn]:checked").val(),
							alarm_price:$("input[name=alarm_price]").val(),
							alarm_price_ud:$("select[name=alarm_price_ud]").val(),
							alarm_sdate:$("input[name=alarm_sdate]").val(),
							alarm_edate:$("input[name=alarm_edate]").val()
							}, function(results){
							results=results.trim();
							if(results>"0"){
								alert("가격알람 정보 수정 성공!!");
								$(opener.document).find("#btn_search").trigger("click");
								window.close();
							}else {
								alert("가격알람 정보 수정 실패");
							}
						});
					}	
				}
				
			}
		});
		
		
	});
	
});
</script> 

<table>
<tr><td id="sm_image" rowspan="8"><p>내 가격알람</p></td><td class="td_text" colspan="5"><p>* 기준일자 : 가격정보가 배포된 일자. 매주 금요일 밤에 전국 가격정보가 배포됩니다.</p></td></tr>
<tr><td class="td_text" colspan="5"><p>* 종료일자가 지난 가격알람내용은 삭제만 가능합니다. / [마이페이지-이메일수신:예] 설정을 확인해 주세요.</p></td></tr>
<tr><td class="td_name">상품명</td><td class="td_data">${rDto.goodname}</td>
	<td class="td_name">점포명</td><td class="td_data">${rDto.entpname}</td>
	<td class="td_btn"><button id="btn_del" class="btn_style">이 가격알람 삭제</button></td></tr>
<tr><td class="td_name">기준일자</td><td class="td_data">${rDto.inspectday}</td>			
	<td class="td_name">등록일자</td><td class="td_data">${rDto.reg_date}</td><td class="td_btn"></td></tr>
<tr><td class="td_name">기준일자시점 가격</td>
	<td class="td_data">
		<c:if test="${inspect_price eq 0}">가격정보 없음	</c:if>
		<c:if test="${inspect_price ne 0}">${inspect_price} 원</c:if>
	</td>	
	<td class="td_name">알람상태</td>
		<td class="td_data"><input type="radio" name="alarm_yn" class="alarm_update" value="Y">알람 중
							<input type="radio" name="alarm_yn" class="alarm_update" value="N">알람중지</td><td class="td_btn"></td></tr>
<tr><td class="td_name">현재가격</td>
	<td class="td_data">
		<c:if test="${thisweek_price eq 0}">가격정보 없음	</c:if>
		<c:if test="${thisweek_price ne 0}">${thisweek_price} 원</c:if>
	</td>
	<td class="td_name">알람가격</td>
	<td class="td_data"><input type="text" name="alarm_price" class="alarm_update" size=15px value="${rDto.alarm_price}">원 
		<select name="alarm_price_ud">
			<option value="UP">이상</option>
			<option value="DW">이하</option>
		</select>
	
	</td><td class="td_btn"></td></tr>
<tr><td class="td_name">일주 전 가격</td>
	<td class="td_data">
		<c:if test="${before1w_price eq 0}">가격정보 없음	</c:if>
		<c:if test="${before1w_price ne 0}">${before1w_price} 원</c:if>
	</td>
	<td class="td_name">알람 시작일자</td><td class="td_data"><input type="date" name="alarm_sdate" class="alarm_update" value="${rDto.alarm_sdate}"></td><td class="td_btn"></td></tr>
<tr><td class="td_name">한달 전 가격</td>
	<td class="td_data">
		<c:if test="${before1m_price eq 0}">가격정보 없음	</c:if>
		<c:if test="${before1m_price ne 0}">${before1m_price} 원</c:if>
	</td>
	<td class="td_name">알람 종료일자</td><td class="td_data"><input type="date" name="alarm_edate" class="alarm_update" value="${rDto.alarm_edate}"></td>
	<td class="td_btn"><input type="hidden" name="num" value="${rDto.num}">
		<button id="btn_submit" class="btn_style alarm_update">변경내용 저장</button></td></tr>


</table>


</body>
</html>