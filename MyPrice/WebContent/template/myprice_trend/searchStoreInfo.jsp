<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>선택하신 판매점의 세부정보 </title>
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/index/searchStore.css">
</head>
<body>
판매점 세부 정보
	<div id="info_1">
	<c:if test="!empty ${store}">
		<c:forEach items="${store}" var="dto">
			판매점 이름: ${dto.entpName}
			판매점 전화번호: ${dto.entpTelno}
			판매 주소:${dto.plmkAddrBasic}
			${dto.plmkAddrDetail}
		</c:forEach>
		</c:if> 
	</div>
	<div id="map" style="width:100%;height:600px;"></div>
	<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=5e353a7e52d2d115e12f936e629481ab"></script>
<script>

var x = <%=request.getAttribute("x")%>
var y = <%=request.getAttribute("y")%>

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new daum.maps.LatLng(x, y), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 마커가 표시될 위치입니다 
var markerPosition  = new daum.maps.LatLng(x, y); 

// 마커를 생성합니다
var marker = new daum.maps.Marker({
    position: markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
// marker.setMap(null);    
</script>
</body>
</html>