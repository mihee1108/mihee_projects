package mp.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.notice.NoticeDAO;
import mp.util.Action;
import mp.util.ActionForward;

public class NoticeDelAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		NoticeDAO nDao = new NoticeDAO();
		String b_cls = request.getParameter("b_cls");
		int num = Integer.parseInt(request.getParameter("num"));
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		
		int result = nDao.noticeDelete(num);
		
		String message = "";
		if (result>0) {
			message = num + "번 글 삭제 성공!!";
		}else {
			message = "글 삭제 실패 ㅠㅠ";
		}
		request.setAttribute("result_message", message);
		request.setAttribute("result_action", "NoticeDelAction");
		request.setAttribute("curPage", curPage);
		request.setAttribute("b_cls", b_cls);
		
		ActionForward af = new ActionForward();
		af.setPath("result_alert.jsp");
		af.setRedirect(true);
		return af;
		
	}

}
