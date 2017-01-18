<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
		<section>
			<div class="mainlogo">
				<a href = "/MyPrice/index_MyPrice.jsp"><img alt="mainlogo" src="/MyPrice/image/mainlogo.jpg"></a>
			</div>
			<div id="menu_header">
				<ul>
					<li><a href = "/MyPrice/customCenter/notice/noticeList.notice?curPage=1&b_cls=N">고객센터</a></li>
					<li><a href = "/MyPrice/myPage/myInfo.jsp">마이페이지</a></li>
					<c:if test="${empty mDto}">
						<li><a href = "/MyPrice/member/join.jsp">회원가입</a></li>
						<li><a href = "/MyPrice/member/login.jsp">로그인</a></li>
					</c:if>
					<c:if test="${not empty mDto}">
						
						<li><a href = "/MyPrice/member/logout.jsp">로그아웃</a></li>
						<li>${mDto.id}님, 반갑습니다.</li>
					</c:if>	
				</ul> 
			</div>
		</section>
		<table>
				<tr><td class = "tdout"></td>
					<td class = "tdin"><a href = "/MyPrice/priceInfo.price?curPage=1">생필품가격정보</a></td>
					<!-- (2016-11-29) 메뉴 위치 변경 : [장바구니가격] <=> [생필품할인정보] -->
					<td class = "tdin"><a href = "/MyPrice/saleInfo.price">생필품할인정보</a></td>
					<td class = "tdin"><a href = "/MyPrice/basketPrice.price?curPage=1">내지역가격정보</a></td>
					<td class = "tdin"><a href = "/MyPrice/priceTrend.price">판매점별가격정보</a></td>
					<!-- (2016-11-29) 메뉴 변경 : 가격보고서 => 가격알람 -->
					<td class = "tdin"><a href = "/MyPrice/myPage/myAlarm.jsp">가격알람</a></td>
					<td class = "tdout"></td>
				</tr>		
		</table>
</header>