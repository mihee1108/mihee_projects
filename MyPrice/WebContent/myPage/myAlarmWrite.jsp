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
<c:if test="${mDto.email_yn ne 'Y'}">
	<script>
	alert("[마이페이지 - 이메일수신 : 예]으로 설정되어 있어야 [가격알람]서비스를 이용하실 수 있습니다.");
	this.window.close();
	</script>
</c:if>

 

<script type="text/javascript">
$(function(){	
	// 가격알람 추가할 때, 인자값으로 받아야 하는 필수 항목 3개 : param.goodid, param.entpid, param.inspectday
	var goodid="${goodid}";	//"${param.goodid}";
	var entpid="${entpid}";
	var inspectday="${inspectday}"; 
	var goodname="${goodname}"; 
	var entpname="${entpname}";
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1;
	if (dd<10) {
		dd = '0' + dd;
	}
	if (mm < 10) {
		mm = '0' + mm;
	}
	var todayStr = today.getFullYear() + '-'+mm + '-'+dd;	
	
	$("#entpid").append("<option value="+entpid+">"+entpname+"</option>");
	if ( !("${mDto.store_1}"=="0") ) {
		$("#entpid").append("<option value='${mDto.store_1}'>${mDto.entpname_1}</option>");	
	}
	if ( !("${mDto.store_2}"=="0") ) {
		$("#entpid").append("<option value='${mDto.store_2}'>${mDto.entpname_2}</option>");
	}
	if ( !("${mDto.store_3}"=="0") ) {
		$("#entpid").append("<option value='${mDto.store_3}'>${mDto.entpname_3}</option>");		
	}
	
	$("#inspectday").text(inspectday);
	$("#todayStr").text(todayStr);

	$("input[name=alarm_yn]").prop("disabled", true);
	
	// 점포변경되면 가격정보 다시 가져오기
	$("#entpid").change(function() {
		//alert("change");
		getPrices();	
	});  
	
	//alert(goodid +"/"+ entpid +"/"+ inspectday +"/"+ todayStr +" / "+ goodname +" / "+ entpname);
	$("#btn_submit").click(function(){
		// 중복 체크 : id, goodid, entpid, alarm_price, alarm_sdate
		$.post("myAlarmUniqueCheck.alarm", {
			num:0,
			id:"${mDto.id}",
			goodid:goodid,
			entpid:$("#entpid").val(),
			alarm_price:$("input[name=alarm_price]").val(),
			alarm_sdate:$("input[name=alarm_sdate]").val()
			}, function(results){
				results=results.trim();
				if(results>"0"){
					alert("중복데이터가 있습니다.\r[내 가격알람] 화면에서 [상품명, 점포명, 알람가격, 시작일자] 기준으로 중복여부 확인해 주세요.");	
					window.close();
				}else {
					
					/* 왜 안됨
					var ckPrice = false
					if(!/^[0-9]$/.test($("input[name=alarm_price]").val()) ) {// 숫자만 가능
						ckPrice = false;
					}else{
						ckPrice = true;
					}
					*/
					if ($("input[name=alarm_price]").val()=="" || $("input[name=alarm_sdate]").val() == "" || $("input[name=alarm_edate]").val() == "") {
						// date 형식은 날짜형식에 맞지 않으면 value값이 ""으로 됨.
						alert("가격알람 정보를 모두 입력해 주세요.");
					}else if($("input[name=alarm_sdate]").val() > $("input[name=alarm_edate]").val()) {
						alert("알람종료일자는 알람시작일자보다 작을 수 없습니다.");
					}else if(todayStr > $("input[name=alarm_edate]").val()) {
						alert("알람종료일자는 오늘 날짜보다 작을 수 없습니다.");
					}else if(todayStr > $("input[name=alarm_sdate]").val()) {
						alert("알람시작일자는 오늘 날짜보다 작을 수 없습니다.");
					}else {
						var alarm_write = confirm("입력한 내역으로 저장 하시겠습니까??");
						if (alarm_write) {
							$.post("myAlarmWrite.alarm", {
								id:"${mDto.id}",
								goodid:goodid,
								entpid:$("#entpid").val(),
								alarm_price:$("input[name=alarm_price]").val(),
								alarm_sdate:$("input[name=alarm_sdate]").val(),
								alarm_edate:$("input[name=alarm_edate]").val(),
								alarm_price_ud:$("select[name=alarm_price_ud]").val(),
								alarm_yn:$("input[name=alarm_yn]:checked").val(),
								inspectday:inspectday
							}, function(results){
								results=results.trim();
								if(results>"0"){
									alert("가격알람 정보 저장 성공!!\r\r저장한 내 가격알람 정보는 [가격알람]화면에서  확인하실 수 있습니다.");
									window.close();
								}else {
									alert("가격알람 정보 저장 실패");
								}
							});
						}
						
						
					}
					
				}
		});
		
	});
	
	function getPrices() {
		// 가격 가져오기 : json 형식
	    $.ajax({
			type:"GET",
			url:"/MyPrice/myPage/myAlarmPrices.alarm",
			data:{
				json_yn:"Y",
				goodid:goodid,
				entpid:$("#entpid").val(),
				inspectday:inspectday
			},
			success: function (prices) {
				prices = JSON.parse(prices);
				//alert("json prices : " + prices.inspect_price + " / " + prices.thisweek_price +" / " + prices.before1w_price +" / " + prices.before1m_price);
				if (prices.inspect_price=="0") {
					$("#inspect_price").text("가격정보 없음");
				}else {
					$("#inspect_price").text(prices.inspect_price+" 원");	
				}
				if (prices.thisweek_price=="0") {
					$("#thisweek_price").text("가격정보 없음");
				}else {
					$("#thisweek_price").text(prices.thisweek_price+" 원");	
				}
				if (prices.before1w_price=="0") {
					$("#before1w_price").text("가격정보 없음");
				}else {
					$("#before1w_price").text(prices.before1w_price+" 원");	
				}
				if (prices.before1m_price=="0") {
					$("#before1m_price").text("가격정보 없음");
				}else {
					$("#before1m_price").text(prices.before1m_price+" 원");	
				}
			},
			error: function(error) {
				//alert("json error : " +this.readyState+" / "+this.status);
			}
		});
	}
	
});
</script> 
<table>
<tr><td id="sm_image" rowspan="8"><p>내 가격알람</p></td><td class="td_text" colspan="5"><p>* 기준일자 : 가격정보가 배포된 일자. 매주 금요일 밤에 전국 가격정보가 배포됩니다.</p></td></tr>
<tr><td class="td_text" colspan="5"></td></tr>
<tr><td class="td_name">상품명</td><td id="goodname" class="td_data">${goodname}</td>
	<td class="td_name">점포명</td><td class="td_data">
		<select id="entpid" name="entpid">
		</select>
	</td><td class="td_btn"></td></tr>
<tr><td class="td_name">기준일자</td><td id="inspectday" class="td_data"></td>			
	<td class="td_name">등록일자</td><td id="todayStr" class="td_data"></td><td class="td_btn"></td></tr>
<tr><td class="td_name">기준일자시점 가격</td>
	<td id="inspect_price" class="td_data">
		<c:if test="${inspect_price eq 0}">가격정보 없음	</c:if>
		<c:if test="${inspect_price ne 0}">${inspect_price} 원</c:if>
	</td>	
	<td class="td_name">알람상태</td>
		<td class="td_data"><input type="radio" name="alarm_yn" class="alarm_update" value="Y" checked>알람 중
							<input type="radio" name="alarm_yn" class="alarm_update" value="N">알람중지</td><td class="td_btn"></td></tr>
<tr><td class="td_name">현재가격</td>
	<td id="thisweek_price" class="td_data">
		<c:if test="${thisweek_price eq 0}">가격정보 없음	</c:if>
		<c:if test="${thisweek_price ne 0}">${thisweek_price} 원</c:if>
	</td>
	<td class="td_name">알람가격</td>
	<td class="td_data"><input type="text" name="alarm_price" class="alarm_update" size=15px>원 
		<select name="alarm_price_ud">
			<option value="UP">이상</option>
			<option value="DW">이하</option>
		</select>
	
	</td><td class="td_btn"></td></tr>
<tr><td class="td_name">일주 전 가격</td>
	<td id="before1w_price" class="td_data">
		<c:if test="${before1w_price eq 0}">가격정보 없음	</c:if>
		<c:if test="${before1w_price ne 0}">${before1w_price} 원</c:if>
	</td>
	<td class="td_name">알람 시작일자</td><td class="td_data"><input type="date" name="alarm_sdate" class="alarm_update" value="${todayStr}"></td><td class="td_btn"></td></tr>
<tr><td class="td_name">한달 전 가격</td>
	<td id="before1m_price" class="td_data">
		<c:if test="${before1m_price eq 0}">가격정보 없음	</c:if>
		<c:if test="${before1m_price ne 0}">${before1w_price} 원</c:if>
	</td>
	<td class="td_name">알람 종료일자</td><td class="td_data"><input type="date" name="alarm_edate" class="alarm_update"></td>
	<td class="td_btn">
		<button id="btn_submit" class="btn_style alarm_update">입력내용 저장</button></td></tr>


</table>

</body>
</html>