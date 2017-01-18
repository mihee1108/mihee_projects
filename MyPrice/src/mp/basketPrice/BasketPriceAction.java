package mp.basketPrice;



import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.common.GetInfoDAO;
import mp.common.GetInfoDTO;
import mp.productInfo.ProductInfoDAO;
import mp.productInfo.ProductInfoDTO;
import mp.util.Action;
import mp.util.ActionForward;
import mp.util.PageMaker;

public class BasketPriceAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		int curPage=1;
	      int perPage=100;      //충분한 수량을 받아오기 위해서 
	      try{
	         curPage = Integer.parseInt(request.getParameter("curPage"));
	      }catch(Exception e){
	         e.printStackTrace();
	      }
	      
	      
	      System.out.println("ProductInfoAction까지 진입했다..");
	      
	      ProductInfoDAO pdao = new ProductInfoDAO();
	      GetInfoDAO gd =  new GetInfoDAO();
	      
	      
	      System.out.println("pdao : "+pdao);
	      
	      int totalCount=pdao.getCount();//storeinfo의 totalcount(entpid)
	      System.out.println("토탈카운트: "+totalCount);
	      
	      PageMaker pm = new PageMaker(curPage, perPage, totalCount);
	      pm.setRowNum();
	      
	      ArrayList<ProductInfoDTO> ar = pdao.productInfoList(pm);
	      ArrayList<GetInfoDTO> ar2 = gd.entpList("entpAreaCode", "AR", "GETSTOREINFOSVC");
	      ArrayList<GetInfoDTO> ar3 = gd.entpList("entpBrandCode", "BC", "GETSTOREINFOSVC");
	      System.out.println("ar: "+ar);
	      
	      
	      ArrayList<ProductInfoDTO> ar4 = pdao.salePriceList();
	      System.out.println("ar4"+ar4);
	      
	      
	      pm.makePageing();
	      
	      request.setAttribute("list", ar);
	      request.setAttribute("pageing", pm);
	      request.setAttribute("entpArea", ar2);
	      request.setAttribute("entpBrand", ar3);
	      request.setAttribute("list2", ar4);
	      
	      ActionForward af = new ActionForward();
	      af.setPath("myprice_info/basketPrice.jsp");
	      af.setRedirect(true);;
	      
	      return af;
	}

}
