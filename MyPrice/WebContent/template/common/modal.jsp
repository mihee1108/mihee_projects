<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel = "stylesheet" type="text/css" href="/MyPrice/css/common/modal.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 모달부분인데 잘고쳐야함  -->			
<div id="id01" class="w3-modal">
    <div class="w3-modal-content">
      <header class="w3-container w3-teal">
        <span onclick="document.getElementById('id01').style.display='none'"
        class="w3-closebtn">&times;</span>
        <div id="container" class="clearfix">
    <div class="wrap clearfix">
    	<div id="contents">
		<!--내용 시작-->
		
<script type="text/javaScript" src="/tprice/js/portal//dailynecessitypriceinfo/shoppingbasketcomparison/shoppingBasketSearchGoods.js"></script>

<form name="listForm" id="listForm" method="post">
<input type="hidden" id="hid_searchConM" value="">
<input type="hidden" id="hid_searchConS" value="">
<input type="hidden" id="hid_goodName" value="">
<input type="hidden" name="pageNo" value="">

<div class="layer_pop" style="width:850px;height:600px">
	<h2 class="h0">상품조회</h2>
	<div class="layer_pop_area">
		<p class="em_b_purple"><img style="width:19px;height:19px;" src="/MyPrice/image/myprice_info/maybeBuy.jpg" alt="장바구니">&nbsp; 아이콘 체크 시  장바구니 가격비교 상품으로 등록됩니다.</p>
	
		<table class="layer_pop_t1 search_area" border="1" summary="상품검색 으로 대분류, 중분류, 소분류, 상품명 입력 항목 제공">
			<caption>상품검색조건 선택</caption>
			<colgroup>
				<col>
				<col>
				<col>
				<col>
				<col>
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">품목군</th>
					<td>
						<select name="searchConM" id="searchConM" title="중분류 선택"><option value="">전체</option><option value="030201000">곡물가공품</option><option value="030101000">정육·난류</option><option value="030202000">수산가공품</option><option value="030203000">낙농·축산가공품</option><option value="030204000">조미료·장류·식용유</option><option value="030102000">채소</option><option value="030205000">과자·빙과류</option><option value="030206000">차·음료·주류</option><option value="030301000">이미용품</option><option value="030302000">세탁·주방·가사용품</option><option value="030303000">의류·신변용품</option><option value="030304000">의약외품</option><option value="030103000">생선류</option></select>
					</td>
					<th scope="row">품목</th>
					<td>
						<select name="searchConS" id="searchConS" title="소분류 선택"><option value="">전체</option><option value="030204011">간장</option><option value="030205003">감자칩</option><option value="030302001">건전지</option><option value="030101001">계란</option><option value="030302008">고무장갑</option><option value="030204009">고추장</option><option value="030206008">과일주스</option><option value="030204003">과일통조림</option><option value="030301022">곽티슈</option><option value="030304009">구강청정제</option><option value="030201009">국수</option><option value="030202001">김밥김</option><option value="030205007">껌</option><option value="030201005">냉동만두</option><option value="030206004">녹차류</option><option value="030102006">단무지</option><option value="030101003">닭고기</option><option value="030201015">당면</option><option value="030101004">돼지고기</option><option value="030204010">된장</option><option value="030301004">두루마리화장지</option><option value="030201008">두부</option><option value="030206011">두유</option><option value="030204015">딸기잼</option><option value="030201011">라면</option><option value="030302009">랩</option><option value="030303001">런닝셔츠</option><option value="030203002">마가린</option><option value="030204012">마요네즈</option><option value="030206016">막걸리</option><option value="030202003">맛살</option><option value="030206012">맥주</option><option value="030205010">모나카류아이스크림</option><option value="030102003">무</option><option value="030301018">물휴지</option><option value="030201007">밀가루</option><option value="030301002">바디워시</option><option value="030203008">발효유</option><option value="030102001">배추</option><option value="030203001">버터</option><option value="030204002">벌꿀</option><option value="030301001">베이비로션</option><option value="030201012">부침가루</option><option value="030302015">부탄까스</option><option value="030203006">분유</option><option value="030206001">비타민음료</option><option value="030205009">빙과류</option><option value="030201001">빵</option><option value="030206006">사이다</option><option value="030302013">살균소독제</option><option value="030205004">새우깡</option><option value="030301013">생리대</option><option value="030202006">생선통조림</option><option value="030206007">생수</option><option value="030206015">생수(묶음)</option><option value="030204007">설탕</option><option value="030302005">섬유유연제</option><option value="030302003">섬유탈취제</option><option value="030301006">세면용비누</option><option value="030302011">세정제</option><option value="030302004">세탁세제</option><option value="030204006">소금</option><option value="030203003">소시지</option><option value="030206013">소주</option><option value="030304001">소화제</option><option value="030301021">손세정제</option><option value="030101002">쇠고기</option><option value="030303002">스타킹</option><option value="030201019">스프</option><option value="030302012">습기제거제</option><option value="030201013">식빵</option><option value="030204005">식용유</option><option value="030204001">식초</option><option value="030206018">식혜</option><option value="030204016">쌈장</option><option value="030301019">썬크림</option><option value="030201010">씨리얼</option><option value="030205008">아이스크림</option><option value="030102002">양파</option><option value="030202002">어묵</option><option value="030301015">여성용 로션</option><option value="030301014">여성용 스킨</option><option value="030301003">여성용 에센스</option><option value="030301020">염모제</option><option value="030203005">우유</option><option value="030302002">위생백</option><option value="030206019">이온음료</option><option value="030301011">일반린스</option><option value="030301017">일반면도날</option><option value="030301010">일반샴푸</option><option value="030301012">종이기저귀</option><option value="030302006">주방세제</option><option value="030201002">즉석국</option><option value="030201004">즉석덮밥</option><option value="030201014">즉석밥</option><option value="030201017">즉석우동</option><option value="030201020">즉석죽</option><option value="030206014">차,음료</option><option value="030204004">참기름</option><option value="030202004">참치캔</option><option value="030201018">초밥류</option><option value="030205005">초코파이</option><option value="030205006">초콜릿</option><option value="030301007">치약</option><option value="030203007">치즈</option><option value="030204014">카레</option><option value="030205001">캔디</option><option value="030206002">캔커피</option><option value="030206003">커피믹스</option><option value="030206020">컵커피</option><option value="030204013">케찹</option><option value="030206005">콜라</option><option value="030102005">콩나물</option><option value="030205002">크래커</option><option value="030302007">키친타월</option><option value="030102004">포기김치</option><option value="030302014">표백제</option><option value="030304002">피로회복제</option><option value="030301016">핸드로션</option><option value="030203004">햄류</option><option value="030302010">호일</option><option value="030204008">혼합조미료</option><option value="030103001">갈치</option><option value="030103002">참조기</option><option value="030103003">고등어</option><option value="030103004">오징어</option><option value="030103005">삼치</option><option value="030102007">시금치</option><option value="030102008">당근</option><option value="030102009">감자</option><option value="030102010">고구마</option><option value="030102011">버섯</option><option value="030102012">오이</option><option value="030102013">풋고추</option><option value="030102014">호박</option><option value="030102015">대파</option><option value="030102016">마늘</option></select>
					</td>
					<th scope="row">상품</th>
					<td>
						<label for="goodName" class="skip">상품명 입력</label>
						<input type="text" id="goodName" name="goodName" value="" title="상품명 입력" class="ui-autocomplete-input" autocomplete="off">
						<input type="image" id="btn_search" src="/MyPrice/image/myprice_info/modal_search.jpg" alt="조회">
						
						<a href="#n" id="btn_close" class="btn_down orange" style="float:right">완료</a>
					</td>
				</tr>
			</tbody>
		</table>
		
		<table class="layer_pop_t1" border="1" summary="상품목으로 대분류, 중분류, 소분류, 제조사, 상품명 제공">
			<caption>상품 목록</caption>
			<colgroup>
				<col>
				<col>
				<col>
				<col>
				<col>
				<col width="20px">
			</colgroup>
			<thead>
				<tr>
					<th>대분류</th>
					<th>중분류</th>
					<th>소분류</th>
					<th>제조사</th>
					<th>상품명</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach items ="${ModalList}" var="x">
			<tr>
					<td>${x.goodSmlclsCode}</td>
					<td>정육·난류</td>
					<td>계란</td>
					<td>CJ프레시안</td>
					<td>알짜란(15구)</td>
					<td style="text-align: center;">
						<button type="button" id="btn_addBasket" title="선택"><img src="/MyPrice/image/myprice_info/modal_maybebuy.jpg" alt="선택"></button>
						<input type="hidden" id="hid_goodId" value="706">
						<input type="hidden" id="hid_goodName" value="알짜란(15구)">
						<input type="hidden" id="hid_fileRgtnSeq" value="10302">
					</td>
				
			</tr>
				</c:forEach>
				<tr>
				<th colspan="5" id="curPage">
				
				<c:if test="${pageing.curBlock >= 1}">
					<a href="storeInfo.store?curPage=${pageing.startNum-1}">
					<img alt="btn_pre1" src="/MyPrice/image/myprice_trend/btn_prev1.gif"></a> <!-- 큰 이전 버튼 -->
					<a href="storeInfo.store?curPage=${pageing.startNum-10}">
					<img alt="btn_pre2" src="/MyPrice/image/myprice_trend/btn_prev2.gif"></a> <!-- 작은 이전 버튼 -->
				</c:if>
				<c:forEach begin="${pageing.startNum}" end="${pageing.lastNum}" var="i">
					<a href="storeInfo.store?curPage=${i}" id="pageNum">${i}</a>
				</c:forEach>
				<c:if test="${pageing.curBlock < pageing.totalBlock}">
					<a href="storeInfo.store?curPage=${pageing.lastNum+1}">
					<img alt="btn_next2" src="/MyPrice/image/myprice_trend/btn_next2.gif"></a> <!-- 작은 다음 버튼 -->
					<a href="storeInfo.store?curPage=${pageing.lastNum+10}">	
					<img alt="btn_next1" src="/MyPrice/image/myprice_trend/btn_next1.gif"></a> <!-- 큰 다음 버튼 -->
				</c:if>
				</th>
				</tr>
			</tbody>
		</table>
		
	</div>
		<div class="close"><a href="/MyPrice/basketPrice.price?curPage=1#n" id="btn_popClose"><img src="/MyPrice/image/myprice_info/modal_close.jpg" alt="닫기"></a></div>
</div>
</form>
		<!--내용 끝-->
		</div>
	</div>
</div>
      </header>
     
    </div>
  </div>