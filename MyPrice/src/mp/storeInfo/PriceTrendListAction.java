package mp.storeInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.common.GetInfoDAO;
import mp.common.productPriceDTO;
import mp.util.Action;
import mp.util.ActionForward;

public class PriceTrendListAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		String entpArea=request.getParameter("entpArea");
		String entpBrand=request.getParameter("entpBrand");
		long entpId=Long.parseLong(request.getParameter("entpId")); //long 타입 entpId
		String strEntpId=request.getParameter("entpId"); //String 타입 entpId
	

		StoreInfoDAO s = new StoreInfoDAO();
		GetInfoDAO g= new GetInfoDAO();
	
		String[] today=g.getFRIs();//금요일 반환 메서드 by언니
		
		List<productPriceDTO> ar = g.getProdcutPrice(today[0], "entpId", strEntpId);
		List<productPriceDTO> ar2 = g.getProdcutPrice(today[1],"entpId", strEntpId);
		
		ArrayList<productPriceDTO> getPriceInfo = new ArrayList<>(); //실제로 정상 price만 담을 ar = 이번주 
		ArrayList<productPriceDTO> getPriceInfo2 = new ArrayList<>(); //실제로 정상 price만 담을 ar =전주 
		ArrayList<Integer> setGoodId = new ArrayList(); //null테스트한 goodId만 셋팅한 ar = 이번주 
		ArrayList<Integer> setGoodId2 = new ArrayList(); //null테스트한 goodId만 셋팅한 ar = 전주  
		ArrayList<String> getGoodId = new ArrayList<>();//goodName 받아온거 셋팅 할 ar = 이번주 
		ArrayList<String> getGoodId2 = new ArrayList<>();//goodName 받아온거 셋팅 할 ar = 전주 

		//이번주 기준 가격정보 뽑아내기 
		for(int i=0;i<ar.size();i++){
			if(ar.get(i).getGoodId() != 0){
				//price의 null값 체킹
				productPriceDTO setPriceInfo = new productPriceDTO(); //세일하는 제품 정보만 뽑기
				setPriceInfo .setEntpId(ar.get(i).getEntpId());
				setPriceInfo .setGoodId(ar.get(i).getGoodId());
				setPriceInfo .setGoodDcYn(ar.get(i).getGoodDcYn());
				setPriceInfo .setPlusoneYn(ar.get(i).getPlusoneYn());
				setPriceInfo .setGoodPrice(ar.get(i).getGoodPrice());
				getPriceInfo.add(setPriceInfo);
				
				setGoodId.add(setPriceInfo.getGoodId()); //int타입의 null통과 goodId만 넣음
			}
		}
		
		for(int j=0;j<setGoodId.size();j++){
			String goodName=g.getGoodNameWithId(setGoodId.get(j).intValue());
			/*System.out.println("받은 goodName: "+g.getGoodNameWithId(setGoodId.get(j).intValue()));*/
			getGoodId.add(goodName);
			
		}
		
		//전주 기준 가격 뽑아내기 
		for(int i=0;i<ar2.size();i++){
			if(ar2.get(i).getGoodId() != 0){
				//price의 null값 체킹
				productPriceDTO setPriceInfo2 = new productPriceDTO(); //세일하는 제품 정보만 뽑기
				setPriceInfo2 .setEntpId(ar.get(i).getEntpId());
				setPriceInfo2 .setGoodId(ar.get(i).getGoodId());
				setPriceInfo2 .setGoodDcYn(ar.get(i).getGoodDcYn());
				setPriceInfo2 .setPlusoneYn(ar.get(i).getPlusoneYn());
				setPriceInfo2 .setGoodPrice(ar.get(i).getGoodPrice());
				getPriceInfo2.add(setPriceInfo2);
				
				setGoodId2.add(setPriceInfo2.getGoodId()); //int타입의 null통과 goodId만 넣음
			}
		}
		
		for(int e=0;e<setGoodId2.size();e++){
			String goodName=g.getGoodNameWithId(setGoodId2.get(e).intValue());
			/*System.out.println("받은 goodName: "+g.getGoodNameWithId(setGoodId.get(j).intValue()));*/
			getGoodId2.add(goodName);
			
		}
		
		ArrayList<StoreInfoDTO> getEntpInfo=s.getEntpName(entpId);
		
		//entpId로 entpId와 entpName가져옴=>ar형태		
		request.setAttribute("getEntpInfo", getEntpInfo);//선택한entpId에 따른 정보 => 딱 한개옴(코드/이름)
		request.setAttribute("getPriceInfo", getPriceInfo); //가격정보 => 모두옴 이중에 가격/할인여부/플원여부 = 이번주 
		request.setAttribute("getPriceInfo2", getPriceInfo2); //가격정보 => 모두옴 이중에 가격/할인여부/플원여부 = 전주 
		request.setAttribute("getGoodId", getGoodId); //가격 검색에 따른 goodName 모아져 있는 ar = 이번주
		request.setAttribute("getGoodId2", getGoodId2); //가격 검색에 따른 goodName 모아져 있는 ar = 전주 
		

		//area코드, brand코드, entpid코드, 13코드로 
		//업체명, 상품명, 선택한 업체가격, 평균가격, 현재의 최저 가격
		
		
		ActionForward af = new ActionForward();
		af.setPath("template/myprice_trend/priceTrendTable.jsp");
		af.setRedirect(true);
		return af;
	}

}
