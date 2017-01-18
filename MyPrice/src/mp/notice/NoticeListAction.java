package mp.notice;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;
import mp.util.PageMaker_hs;
import mp.notice.*;

public class NoticeListAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		
		// 글 전체 조회
		NoticeDAO nDao = new NoticeDAO();
		String index_yn = request.getParameter("index_yn");
		if (index_yn == null) {
			index_yn = "N";
		}
		String b_cls = request.getParameter("b_cls");
		String search_txt = request.getParameter("search_txt");
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int perPage = 10;
		if(search_txt == null) {
			search_txt="";
		}
		String num = "";
		if ( !(request.getParameter("num") == null) ) {
			num = request.getParameter("num");
		}
		int totalCount = nDao.getTotalCnt(b_cls, search_txt);
		PageMaker_hs pm = new PageMaker_hs(curPage, perPage, totalCount);
		pm.makePageing();
		ArrayList<NoticeDTO> ar = nDao.noticeList(pm, b_cls, search_txt, index_yn);
		
		request.setAttribute("list", ar);
		request.setAttribute("pm", pm);
		request.setAttribute("curPage", curPage);
		request.setAttribute("search_txt", search_txt);
		//request.setAttribute("perPage", perPage);
		
		ActionForward af = new ActionForward();
		if (index_yn.equals("Y")) {
			//System.out.println("index_yn : " + index_yn);
			af.setPath("/template/index/index_notice.jsp");
		}else {
			//System.out.println("index_yn : " + index_yn);
			request.setAttribute("num", num);
			af.setPath("notice.jsp");	
		}
		
		af.setRedirect(true);
		
		return af;
		
	}

}
