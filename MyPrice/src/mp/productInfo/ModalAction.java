package mp.productInfo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;
import mp.util.PageMaker;

public class ModalAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("액션에 들어왔음 ");
		
		ProductInfoDAO pdao = new ProductInfoDAO();
		System.out.println("pdao:"+pdao);
		int totalCount = pdao.getCount();
		int curPage=Integer.parseInt(request.getParameter("curpage"));
		int perPage = 10;
		System.out.println("cur: "+curPage);
		PageMaker pm = new PageMaker(curPage, perPage, totalCount);
		pm.setRowNum();
		
		ArrayList<ProductInfoDTO> ar = pdao.ModalList(pm);
		pm.makePageing();
		request.setAttribute("list", ar);
		request.setAttribute("pageing", pm);
		ActionForward af = new ActionForward();
		af.setPath("template/common/modal.jsp");
		af.setRedirect(true);
		return af;
	}

}
