package mp.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;

public class MemberIdCheckAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		MemberDAO mDao = new MemberDAO();
		//System.out.println("id mp: " + id);
		int result = mDao.memberIDCheck(id);
		request.setAttribute("result_DAO", result);
		
		ActionForward af = new ActionForward();
		af.setPath("result_DAO.jsp");
		af.setRedirect(true);
		
		return af;
	}

}
