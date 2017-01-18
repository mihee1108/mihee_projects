package mp.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;

public class NoticeCountsAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {

		int num = Integer.parseInt(request.getParameter("num"));

		NoticeDAO nDao = new NoticeDAO();
		int upCount = nDao.noticeCounts(num);
		System.out.println("notice counts : " + upCount);
		ActionForward af = new ActionForward();
		
		request.setAttribute("result_DAO", upCount);
		af.setPath("result_DAO.jsp");
		af.setRedirect(true);	
		
		return af;

	}

}
