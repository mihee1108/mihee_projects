<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Price | 모든 물건들의 가격을 알고 싶어 하는 당신을 위한 사이트</title>
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/reset.css">
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/temp.css">
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/index.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
$(function(){

	// 상품별 전체 가격
	$(".tab_content").hide(); //Hide all content
	$("ul.tabs li:first").addClass("active").show(); //Activate first tab
	$(".tab_content:first").show(); //Show first tab content
	
	// 첫 화면 그래프 로드
	$("#tab_priceInfo").load("/MyPrice/indexPriceInfo.index", 
			{smlclscode:$("ul.tabs li:first").find("a").attr("href").substr(1, 6)		},
			function(){
				google.charts.load('current', {packages: ['corechart', 'line']});
				google.charts.setOnLoadCallback(drawChart);
				// 첫화면은 drawChart 인자값이 안 먹힘. ajax비동기식 때문인듯 (index_priceInfo.jsp 결과가 오기전이라서??)
				function drawChart() {
				      var data = google.visualization.arrayToDataTable([
				      	['', $("#td_goodname"+$(".tr_priceInfo:first").attr("id")).text()],
				      	['3달전', Number($("#td_before3m"+$(".tr_priceInfo:first").attr("id")).text())], 
				      	['1달전', Number($("#td_before1m"+$(".tr_priceInfo:first").attr("id")).text())],
				      	['1주전', Number($("#td_before1w"+$(".tr_priceInfo:first").attr("id")).text())],
				      	['이번주', Number($("#td_thisw"+$(".tr_priceInfo:first").attr("id")).text())]
				      	]);

				      var options = {
				        legend: { position: 'bottom' },
				        backgroundColor: '#ffffff'
				      };

				      var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
				      chart.draw(data, options);
				    }
	});
		
	//On Click Event
	$("ul.tabs li").click(function() {
		$("ul.tabs li").removeClass("active"); //Remove any "active" class
		$(this).addClass("active"); //Add "active" class to selected tab
		$(".tab_content").hide(); //Hide all tab content

		var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
		//$(activeTab).fadeIn(); //Fade in the active ID content
		$(".tab_content").fadeIn();
		$("#tab_priceInfo").load("/MyPrice/indexPriceInfo.index", 
			{smlclscode:activeTab.substr(1, 6)		},
			function(){
				//alert(activeTab.substr(1, 6)+" / "+ $(".tr_priceInfo:first").attr("id"));
				google.charts.load('current', {packages: ['corechart', 'line']});
				google.charts.setOnLoadCallback(drawChart(
						$("#td_goodname"+$(".tr_priceInfo:first").attr("id")).text(),
						 Number($("#td_before3m"+$(".tr_priceInfo:first").attr("id")).text()),
						 Number($("#td_before1m"+$(".tr_priceInfo:first").attr("id")).text()),
						 Number($("#td_before1w"+$(".tr_priceInfo:first").attr("id")).text()),
						 Number($("#td_thisw"+$(".tr_priceInfo:first").attr("id")).text())	 
				));
		});		
		//return false;
	});
	$("#tab_priceInfo").on("mouseover", ".tr_priceInfo", function(){	// tr_member에 이벤트 위임
		var n =$(this).attr("id");
		google.charts.load('current', {packages: ['corechart', 'line']});
		google.charts.setOnLoadCallback(drawChart(
				$("#td_goodname"+n).text(),
				Number($("#td_before3m"+n).text()),
				Number($("#td_before1m"+n).text()),
				Number($("#td_before1w"+n).text()),
				Number($("#td_thisw"+n).text())
		));
	});	
	
	// 공지
	$("#c_notice").load("/MyPrice/customCenter/notice/noticeList.notice", {
	curPage:1,
	b_cls:"N",
	index_yn:"Y"
	},
	function(){
	});

	//chart 메서드
	function drawChart(goodname, before3m, before1m, before1w, thisw) {
	      var data = google.visualization.arrayToDataTable([
	      	['', goodname],
	      	['3달전', Number(before3m)],
	      	['1달전', Number(before1m)],
	      	['1주전', Number(before1w)],
	      	['이번주', Number(thisw)]
	      	]);
	      var options = {
	        legend: { position: 'bottom' },
	        backgroundColor: '#ffffff'
	      };

	      var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
	      chart.draw(data, options);
	}
	
		
});
</script>
</head>
<body>

<%@ include file="/template/header_mp.jsp"  %>

	<section id = "center">
		<section id="c_image"></section>
		<section id="c_priceInfo">
			<ul class="tabs">
		   	 <li><a href="#030101">정육.난류</a></li>
		   	 <li><a href="#030102">채소</a></li>
		   	 <li><a href="#030103">생선류</a></li>
		   	 <li><a href="#030201">곡물가공품</a></li>
		   	 <li><a href="#030202">수산가공품</a></li>
		   	 <li><a href="#030203">낙농.축산가공품</a></li>
		   	 <li><a href="#030204">조미료.장류.식용유</a></li>
		   	 <li><a href="#030205">과자.빙과류</a></li>
		   	 <!--
		   	 <li><a href="#030206">차.음료.주류</a></li>
		   	 <li><a href="#030301">이미용품</a></li>
		   	 <li><a href="#030302">세탁.주방.가사용품</a></li>
		   	 <li><a href="#030303">의류.신변용품</a></li>
		   	 <li><a href="#030304">의약외품</a></li>
		   	  -->
			</ul>
		
			<div class="tab_container">
		   		<div class="tab_content">
		   			<div id="tab_priceInfo"></div>
				    <div id="chart_div"></div>
		    	</div>
			</div>
 
		</section>
		<section id="c_third">
			<div id="c_notice"></div>
		</section>
	</section>
	
<%@ include file="/template/footer_mp.jsp"  %>
</body>
</html>