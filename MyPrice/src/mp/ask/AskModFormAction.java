package mp.ask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;

public class AskModFormAction implements Action {
	
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		
		AskDAO aDao = new AskDAO();
		AskDTO aDto = aDao.askView(num);
		
		ActionForward af = new ActionForward();
		if (aDto!=null) {
			request.setAttribute("aDto", aDto);
			af.setPath("askModForm.jsp");
		}else {
			request.setAttribute("result_message", "해당 글이 없습니다.");
			request.setAttribute("result_action", "AskModFormAction");
			af.setPath("result_alert.jsp");	
		}
		request.setAttribute("curPage", curPage);
		af.setRedirect(true);	
		
		return af;
		
	}

}
