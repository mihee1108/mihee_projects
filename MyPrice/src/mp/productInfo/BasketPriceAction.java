package mp.productInfo;



import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;
import mp.util.PageMaker;

public class BasketPriceAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		ProductInfoDAO pdao = new ProductInfoDAO();
		System.out.println("pdao:"+pdao);
		int totalCount = pdao.getCount();
		int curPage=Integer.parseInt(request.getParameter("curPage"));
		int perPage=10;
		
		PageMaker pm = new PageMaker(curPage, perPage, totalCount);
		pm.setRowNum();
		
		ArrayList<ProductInfoDTO> ar = pdao.basketPriceList(pm);
		pm.makePageing();
		
		request.setAttribute("list", ar);
		request.setAttribute("pageing", pm);
		ActionForward af = new ActionForward();
		af.setPath("myprice_info/basketPrice.jsp");
		af.setRedirect(true);
		
		return af;
	}

}
