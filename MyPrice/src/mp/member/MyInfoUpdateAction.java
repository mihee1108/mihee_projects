package mp.member;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.member.MemberDAO;
import mp.member.MemberDTO;
import mp.util.Action;
import mp.util.ActionForward;

public class MyInfoUpdateAction implements Action {

	@Override
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
		mDto.setOut_yn(request.getParameter("out_yn"));
		mDto.setStore_1(Integer.parseInt(request.getParameter("store_1")=="" ? "0" : request.getParameter("store_1")));
		mDto.setStore_2(Integer.parseInt(request.getParameter("store_2")=="" ? "0" : request.getParameter("store_2")));
		mDto.setStore_3(Integer.parseInt(request.getParameter("store_3")=="" ? "0" : request.getParameter("store_3")));

		MemberDAO mDao = new MemberDAO();
		int result = mDao.memberUpdate(mDto);
		System.out.println("out_yn : " + mDto.getOut_yn());
		ActionForward af = new ActionForward();
		if ( result == 0) {
			request.setAttribute("result_message", "회원정보 수정 실패");
		}else {
			mDto = mDao.memberLogin(mDto);	// 관신점포명을 위해 개인정보 한번 더 조회
			request.getSession().setAttribute("mDto", mDto);
			request.setAttribute("result_message", mDto.getId()+"님, 회원정보 수정 성공!!");	
		}
		request.setAttribute("result_action", "MyInfoUpdateAction");
		af.setPath("result_alert.jsp");
		af.setRedirect(true);
		return af;
	}

}
