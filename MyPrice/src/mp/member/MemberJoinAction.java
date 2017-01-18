package mp.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;

public class MemberJoinAction implements Action{
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		MemberDTO mDto = new MemberDTO();
		mDto.setId(request.getParameter("id"));
		mDto.setPassword(request.getParameter("password"));
		mDto.setPhone_1(request.getParameter("phone_1"));
		mDto.setPhone_2(request.getParameter("phone_2"));
		mDto.setPhone_3(request.getParameter("phone_3"));
		mDto.setGender(request.getParameter("gender"));
		mDto.setBirthday(request.getParameter("birthday"));
		mDto.setEmail(request.getParameter("email"));
		mDto.setEmail_yn(request.getParameter("email_yn"));
		mDto.setSms_yn(request.getParameter("sms_yn"));
		mDto.setStore_1(Integer.parseInt(request.getParameter("store_1")=="" ? "0" : request.getParameter("store_1")));
		mDto.setStore_2(Integer.parseInt(request.getParameter("store_2")=="" ? "0" : request.getParameter("store_2")));
		mDto.setStore_3(Integer.parseInt(request.getParameter("store_3")=="" ? "0" : request.getParameter("store_3")));
		
		MemberDAO mDao = new MemberDAO();
		int result = mDao.memberInsert(mDto);
		System.out.println("result : " + result);
		
		ActionForward af = new ActionForward();
		if ( result == 0) {
			request.setAttribute("result_message", "회원가입 실패");
		}else {
			request.setAttribute("result_message", mDto.getId()+"님, 회원가입 성공!!");	
		}
		System.out.println("mDto.getId() : " + mDto.getId());
		
		request.setAttribute("result_action", "MemberJoinAction");
		af.setPath("result_alert.jsp");
		af.setRedirect(true);
		
		return af;
		
		
		
	}

}
