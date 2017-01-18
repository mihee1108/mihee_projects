<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

				<table id="t_list">
					<tr><th class="td_out"></th><th class="id">아이디</th><th class="id">탈퇴여부</th><th class="phone">핸드폰번호</th><th class="phone">내 주요점포</th><th class="phone">가입일자</th><th class="phone">탈퇴일자</th></tr>
					<c:forEach items="${list}" var="m" varStatus="m_num">
						<tr id="${m_num.index+1}" class="tr_member">
							<td></td>
							<td class="td_member">${m.id}</td>
							<td class="td_member t_out_yn">${m.out_yn}</td>
							<td class="td_member">${m.phone_1}-${m.phone_2}-${m.phone_3}</td>
							<td class="td_member">${m.entpname_1}</td>
							<td class="td_member">${m.inputdttm}</td>
							<td class="td_member">${m.out_date}</td>
						</tr>	
						<tr><td colspan="7" id="td_show${m_num.index+1}" class="td_contents">
							<table>
								<tr><th class="td_contents_th">성별</th><td class="td_contents_td">${m.gender}</td>		<th class="td_contents_th td_contents_th_2">이메일수신동의</th><td class="td_contents_td td_contents_td_2">${m.email_yn}</td>	<th class="td_contents_th">관심점포 1</th><td class="td_contents_td">${m.entpname_1}</td></tr>
								<tr><th class="td_contents_th">생년월일</th><td class="td_contents_td">${m.birthday}</td>	<th class="td_contents_th td_contents_th_2">문자수신동의</th><td class="td_contents_td td_contents_td_2">${m.sms_yn}</td>		<th class="td_contents_th">관심점포 2</th><td class="td_contents_td">${m.entpname_2}</td></tr>
								<tr><th class="td_contents_th">이메일주소</th><td class="td_contents_td">${m.email}</td>	<th class="td_contents_th td_contents_th_2"></th><td class="td_contents_td td_contents_td_2"></td>						<th class="td_contents_th">관심점포 3</th><td class="td_contents_td">${m.entpname_3}</td></tr>
							</table>
						</td></tr>
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
