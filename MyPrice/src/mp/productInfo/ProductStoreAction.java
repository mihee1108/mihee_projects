package mp.productInfo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;

public class ProductStoreAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("STOREACTION!!!!!!!");
		ProductInfoDAO pdao = new ProductInfoDAO();
		System.out.println("pdao : "+pdao);
		
		
		String sel = request.getParameter("store");
		System.out.println("sel4:"+sel); //entpBrandCode
		String sel2 = request.getParameter("storeCode");
		System.out.println("sel5: "+sel2); //entpAreacode
		
		ArrayList<ProductInfoDTO> ar7 = pdao.salePriceList4(sel2, sel);	
		//System.out.println("ar7:"+ar7.get(1).getEntpId());
		
		request.setAttribute("list4", ar7);	
		
		ActionForward af = new ActionForward();
		af.setPath("myprice_info/list2.jsp");	
		af.setRedirect(true);
		
		return af;
	}

}
