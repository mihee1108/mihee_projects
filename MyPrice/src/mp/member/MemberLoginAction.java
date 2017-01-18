package mp.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.member.MemberDAO;
import mp.member.MemberDTO;
import mp.util.Action;
import mp.util.ActionForward;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		MemberDTO mDto = new MemberDTO();
		mDto.setId(request.getParameter("id"));
		mDto.setPassword(request.getParameter("password"));
		
		MemberDAO mDao = new MemberDAO();
		mDto = mDao.memberLogin(mDto);
		
		ActionForward af = new ActionForward();
		if(mDto!=null) {
			request.getSession().setAttribute("mDto", mDto);
			request.getSession().setMaxInactiveInterval(3600);	// 세션 한시간으로 늘리기
			request.setAttribute("result_message", mDto.getId()+"님, 안녕하세요.");
		}else {
			request.setAttribute("result_message", "로그인 실패 : 회원정보가 없습니다.");
		}
		
		request.setAttribute("result_action", "MemberLoginAction");
		af.setPath("result_alert.jsp");
		af.setRedirect(true);
		
		return af;
	}

}
