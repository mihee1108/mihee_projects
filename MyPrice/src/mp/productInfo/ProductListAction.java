package mp.productInfo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;
import mp.util.PageMaker;

public class ProductListAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		
		ProductInfoDAO pdao = new ProductInfoDAO();
		System.out.println("pdao : "+pdao);
		
		String sel = request.getParameter("select13");
		System.out.println("sel:"+sel);
		
		ArrayList<ProductInfoDTO> ar6 = pdao.salePriceList2(sel);
		System.out.println("ar6:"+ar6);

		request.setAttribute("list3", ar6);
		
		ActionForward af = new ActionForward();
		af.setPath("myprice_info/priceInfoList.jsp");
		af.setRedirect(true);;
		
		return af;
	}

}
