<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 아래 두개를 무조건 첨부해야함 > grape파일에 있 -->
<link rel="stylesheet" href="/MyPrice/grape/Nwagon.css" type="text/css">
<script src="/MyPrice/grape/Nwagon.js"></script>
<!-- http://nuli.navercorp.com/sharing/nwagon#nWagon-chart3 참고주 -->
</head>
<body>
<div id="Nwagon"></div>    <!-- div 이름 수정가능 하지만 무조건 div를 스크립트보다 윗쪽에 놔두어야 실행가능 -->
<!-- <%=request.getParameter("y")%> 이런식으로 가지고 와서 var값에 넣어주면 -->
<script>
	var today =<%=request.getParameter("today")%> //현재가격 이곳에 파라미터로 받은 값을 넣어주면 됨
	var week=<%=request.getParameter("week")%> //1주전가격 
	var month=<%=request.getParameter("month")%> //1달전가격 
	var months=<%=request.getParameter("months")%> //3달전가격 
	
	var options = {
		'legend': {
			names: ['현재가격','1주전가격','1달전가격','3달전가격'], //오빠꺼 기준으로 정했음 
			hrefs: [
				'http://nuli.navercorp.com/sharing/blog/post/1132444',
				'http://nuli.navercorp.com/sharing/blog/post/1132442',
				'http://nuli.navercorp.com/sharing/blog/post/1132439',
				'http://nuli.navercorp.com/sharing/blog/post/1132426',
			]
		},
		'dataset': {
			title: '시기별 생필품 가격정보', 
			values: [today,week,month,months], //이부분에 변수로 가격을 넣어주세요 [현재가격, 1주전가격, 1달전가격, 3달전가격]
			colorset: ['#DC143C', '#FF8C00', "#30A1CE"]
		},
		'chartDiv': 'Nwagon', //div 이름 정해주세요. 이 jsp상은 Nwagon
		'chartType': 'column',
		'chartSize': { width: 500, height: 300 }, 
		'maxValue': 10000, //최고가를 넣어주세요 없을시 적당한 가격을 입력하면
		'increment': 5000 //기준치값을넣어주세요 => 현재각격을 넣는것이 좋을
	};"WebContent/forgrape.jsp"
	Nwagon.chart(options);
</script>
</body>
</html>