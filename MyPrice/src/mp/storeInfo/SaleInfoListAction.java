package mp.storeInfo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import mp.common.GetInfoDAO;
import mp.common.productPriceDTO;
import mp.util.Action;
import mp.util.ActionForward;
import mp.util.PageMaker;

public class SaleInfoListAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {

		String startDay=request.getParameter("startDay");
		String endDay=request.getParameter("endDay");
		String entpArea=request.getParameter("entpArea");
		String entpBrand=request.getParameter("entpBrand");
		String entpId=request.getParameter("entpId"); 
		int curPage=Integer.parseInt(request.getParameter("curPage"));
		int perPage=Integer.parseInt(request.getParameter("perPage"));
	
		
		StoreInfoDAO sd = new StoreInfoDAO();
		int totalCount=sd.totalCount(entpId);
		
		
		PageMaker pm = new PageMaker(curPage, perPage, totalCount);
		pm.setRowNum();
		pm.makePageing();
		
		GetInfoDAO g = new GetInfoDAO();
		ArrayList<StoreInfoDTO> ar = sd.saleInfoList(pm, entpId);
		List<productPriceDTO> ar2 = g.getProdcutPrice(startDay, "entpId", entpId);
	
		//goodId를 한글로 바꾸는 작업하기 !!!!!!!!!!!!!(2016.12.2 꼭하고가기) -했
		
		ArrayList<productPriceDTO> getDiscountInfo = new ArrayList<>(); //세일 제품 정보를 담을 DTO 
		String message="";
		ArrayList<String> getgoodname= new ArrayList<>(); //goodname 한글명 담은 리스
		
		for(int i=1;i<ar2.size();i++){
			if( ar2.get(i).getGoodId() != 0){ 
				//price의 null값 체킹
				if(ar2.get(i).getPlusoneYn().trim().equals("Y") || ar2.get(i).getGoodDcYn().trim().equals("Y") ){
				System.out.println(message);
				productPriceDTO setDiscountInfo = new productPriceDTO(); //세일하는 제품 정보만 뽑기
				setDiscountInfo.setEntpId(ar2.get(i).getEntpId());
				setDiscountInfo.setGoodId(ar2.get(i).getGoodId());
				setDiscountInfo.setGoodDcYn(ar2.get(i).getGoodDcYn());
				setDiscountInfo.setPlusoneYn(ar2.get(i).getPlusoneYn());
				setDiscountInfo.setGoodPrice(ar2.get(i).getGoodPrice());
			
				String goodName=g.getGoodNameWithId(ar2.get(i).getGoodId());
			    getgoodname.add(goodName);
				
				getDiscountInfo.add(setDiscountInfo); // 항목이 Y 인 정보만 뽑아서 DTO에 add하기
				
				 message="해당 지점의 할인 상품이 존재합니다";
			}
		}	
	}//for문 마무리
		
		request.setAttribute("goodName", getgoodname); //GOODNAME한글
		request.setAttribute("priceInfo", getDiscountInfo); //할인할 경우만 보냄
		request.setAttribute("message", message);
		request.setAttribute("saleInfoList", ar); //디비에서 가져옴 
		request.setAttribute("pageing", pm);
	
		ActionForward af = new ActionForward();
		af.setPath("template/myprice_trend/saleInfoTable.jsp");
		af.setRedirect(true);
		return af;	
}
}
