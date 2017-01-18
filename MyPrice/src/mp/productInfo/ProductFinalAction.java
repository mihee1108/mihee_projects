package mp.productInfo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import mp.common.GetInfoDAO;
import mp.common.GetInfoDTO;
import mp.common.productPriceDTO;
import mp.util.Action;
import mp.util.ActionForward;
import mp.util.PageMaker;

public class ProductFinalAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("마지막 부분 진입 !");
		 
	    ProductInfoDAO pdao = new ProductInfoDAO();
	    GetInfoDAO gDao =  new GetInfoDAO();
	    
	    System.out.println("pdao : "+pdao);
	      
	    int totalCount=pdao.getCount();//storeinfo의 totalcount(entpid)
	    System.out.println("토탈카운트: "+totalCount);
		
		String entpAreaCode = request.getParameter("entpAreaCode");
		System.out.println("entpAreaCode3: "+entpAreaCode);
		
		long entpId = Long.parseLong(request.getParameter("entpId"));
		System.out.println("entpId3: "+entpId);
		
		String goodId =request.getParameter("goodName");
		System.out.println("goodId3: "+goodId);
		
		List<productPriceDTO> ar = null;
		//List<ProductInfoDTO> ar2 =  pdao.salePriceList();
		String[] fris = gDao.getFRIs();
		ar = gDao.getProdcutPrice(fris[0], "goodId", goodId);
		int thisweek_price = gDao.getProdcutPriceWithEntiId(ar, entpId);
		System.out.println("thisweek_price: "+thisweek_price);
		ar = gDao.getProdcutPrice(fris[1], "goodId", goodId);
		int before1w_price = gDao.getProdcutPriceWithEntiId(ar, entpId);
		ar = gDao.getProdcutPrice(fris[2], "goodId", goodId);
		int before1m_price = gDao.getProdcutPriceWithEntiId(ar, entpId);
		ar = gDao.getProdcutPrice(fris[3], "goodId", goodId);
		int before3m_price = gDao.getProdcutPriceWithEntiId(ar, entpId);
		System.out.println("ar_final: " +ar);
		
		
		request.setAttribute("thisweek_price", thisweek_price);
		request.setAttribute("before1w_price", before1w_price);
		request.setAttribute("before1m_price", before1m_price);
		request.setAttribute("before3m_price", before3m_price);
		request.setAttribute("goodId", goodId);
		request.setAttribute("entpId", entpId);
		request.setAttribute("fris", fris[0]);
		
		
		ActionForward af = new ActionForward();
		af.setPath("myprice_info/final.jsp");	
		af.setRedirect(true);;
		
		return af;
	}

}
