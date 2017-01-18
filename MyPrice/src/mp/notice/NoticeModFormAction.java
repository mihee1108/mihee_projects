package mp.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;

public class NoticeModFormAction implements Action {
	
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		
		String b_cls = request.getParameter("b_cls");
		int num = Integer.parseInt(request.getParameter("num"));
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		
		NoticeDAO nDao = new NoticeDAO();
		NoticeDTO nDto = nDao.noticeView(num);
		
		ActionForward af = new ActionForward();
		if (nDto!=null) {
			request.setAttribute("nDto", nDto);
			af.setPath("noticeModForm.jsp");
		}else {
			request.setAttribute("result_message", "해당 글이 없습니다.");
			request.setAttribute("result_action", "NoticeModForm");
			request.setAttribute("b_cls", b_cls);
			af.setPath("result_alert.jsp");	
		}
		request.setAttribute("curPage", curPage);
		af.setRedirect(true);	
		
		return af;
		
	}

}
