package mp.productInfo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;

public class ProductGoodNameAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("상품뿌려주기까지 진입");
		
		ProductInfoDAO pdao = new ProductInfoDAO();
		System.out.println("pdao : "+pdao);
		
		String sel3 = request.getParameter("goodName");
		System.out.println("goodName: "+sel3);
		
		ArrayList<ProductInfoDTO> detail_3 = pdao.salePriceList3(sel3);
		
		request.setAttribute("list4", detail_3);
		
		ActionForward af = new ActionForward();
		af.setPath("myprice_info/priceSearch.jsp");
		af.setRedirect(true);
		
		return af;
	}

}
