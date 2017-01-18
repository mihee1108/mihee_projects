package mp.basketPrice;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;

public class BasketGoodNameAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("GoodName 뿌려주자");
		
		BasketInfoDAO bdao = new BasketInfoDAO();
		
		String goodName = request.getParameter("select_detailer");
		System.out.println("goodName???: "+goodName);
		
		ArrayList<BasketInfoDTO> ar = bdao.salePriceList3(goodName);
		System.out.println("ar좀떠주세요:"+ar);
		
		request.setAttribute("goodName", ar);
		
		ActionForward af = new ActionForward();
		af.setPath("myprice_info/basket/basketGoodName.jsp");
		af.setRedirect(true);
		
		return af;
	}

}
