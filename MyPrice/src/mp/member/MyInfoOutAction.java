package mp.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.member.MemberDAO;
import mp.member.MemberDTO;
import mp.util.Action;
import mp.util.ActionForward;

public class MyInfoOutAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		MemberDTO mDto = new MemberDTO();		
		mDto.setId(request.getParameter("id"));
		mDto.setOut_yn(request.getParameter("out_yn"));

		MemberDAO mDao = new MemberDAO();
		int result = mDao.memberOut(mDto);
		
		ActionForward af = new ActionForward();
		if ( result == 1) {
			request.setAttribute("result_message", mDto.getId()+"님, 안녕히 가세요. ^^//");
			request.getSession().invalidate();
		}else {
			request.setAttribute("result_message", "회원탈퇴 실패");	
		}
		request.setAttribute("result_action", "MyInfoOutAction");
		
		af.setPath("result_alert.jsp");
		af.setRedirect(true);
		return af;
	}

}
