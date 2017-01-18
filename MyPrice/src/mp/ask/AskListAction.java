package mp.ask;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.notice.NoticeDAO;
import mp.notice.NoticeDTO;
import mp.util.Action;
import mp.util.ActionForward;
import mp.util.PageMaker_hs;

public class AskListAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		// 작성자 문의글 전체 조회
		AskDAO aDao = new AskDAO();
		
		String writer = request.getParameter("writer");
		String search_txt = request.getParameter("search_txt");
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int perPage = 10;
		if(search_txt == null) {
			search_txt="";
		}
		int totalCount = aDao.getTotalCnt(writer, search_txt);
		PageMaker_hs pm = new PageMaker_hs(curPage, perPage, totalCount);
		pm.makePageing();
		ArrayList<AskDTO> ar = aDao.askList(pm, writer, search_txt);
		
		request.setAttribute("list", ar);
		request.setAttribute("pm", pm);
		request.setAttribute("curPage", curPage);
		request.setAttribute("search_txt", search_txt);
		//request.setAttribute("perPage", perPage);
		
		ActionForward af = new ActionForward();
		af.setPath("askList.jsp");
		af.setRedirect(true);
		return af;
			
	}

}
