<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

				<table id="t_list">
					<tr><th class="td_out"></th><th class="alarm_yn">알람</th><th class="id">아이디</th><th class="id">상품</th><th class="id">점포</th><th class="id">현재가격</th><th class="id">알람가격</th><th class="alarm_sdate">알람기간</th></tr>
					<c:forEach items="${list}" var="r" varStatus="r_num">
						<tr id="${r_num.index+1}" class="tr_alarm">
							<td></td>
							<td class="td_alarm">${r.alarm_yn}</td>
							<td class="td_alarm">${r.id}</td>
							<td class="td_alarm">${r.goodname}</td>
							<td class="td_alarm">${r.entpname}</td>
							<td class="td_alarm">
								<c:if test="${r.thisweek_price eq 0}">가격정보 없음	</c:if>
								<c:if test="${r.thisweek_price ne 0}">${r.thisweek_price} 원</c:if>
							</td>
							<td class="td_alarm">${r.alarm_price} 원 ${r.alarm_price_ud_name}</td>
							<td class="td_alarm">${r.alarm_sdate}~${r.alarm_edate}</td>
						</tr>	
					</c:forEach>	
					
				</table>
				<div id="paging">
					<div class="paging_margin"></div>
					<c:if test="${pm.curBlock > 1}">
						<li id="${pm.startPage-1}" class="paging"><img alt="btn_pre2" src="/MyPrice/image/myprice_trend/btn_prev2.gif"></li>
					</c:if>
					<c:forEach begin="${pm.startPage}" end="${pm.lastPage}" step="1" var="i"> 
						<li id="${i}" class="paging">${i}</li>
					</c:forEach>	
					<c:if test="${pm.curBlock < pm.totalBlock}">
						<li id="${pm.lastPage+1}" class="paging"><img alt="btn_next2" src="/MyPrice/image/myprice_trend/btn_next2.gif"></li>
					</c:if>
				</div>
