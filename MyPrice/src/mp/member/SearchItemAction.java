package mp.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;

public class SearchItemAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {

		// 검색단어 기준 조회 : 점포, 상품
		String search_item = request.getParameter("search_item");
		String search_txt = request.getParameter("search_txt");
		MemberDAO sDao = new MemberDAO();
		System.out.println(search_txt);
		ArrayList<SearchItemDTO> ar = sDao.searchItem(search_item, search_txt);
		
		request.setAttribute("list", ar);
		System.out.println("SearchItem : " + search_item);
		ActionForward af = new ActionForward();
		if (search_item.equals("store")) {
			af.setPath("searchStoreList.jsp");
		}else if (search_item.equals("prod")) {
			af.setPath("searchProdList.jsp");
		}
		
		
		af.setRedirect(true);
		return af;
	}

}
