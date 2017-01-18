package mp.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;

public class MemberSearchAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		
		MemberDTO mDto = new MemberDTO();
		mDto.setId(request.getParameter("id"));
		mDto.setPhone_1(request.getParameter("phone_1"));
		mDto.setPhone_2(request.getParameter("phone_2"));
		mDto.setPhone_3(request.getParameter("phone_3"));
		mDto.setBirthday(request.getParameter("birthday"));
		mDto.setEmail(request.getParameter("email"));
		//System.out.println(mDto.getId() +"/"+ mDto.getPhone_1() +"/"+ mDto.getPhone_2() +"/"+ mDto.getPhone_3() +"/"+ mDto.getBirthday() +"/"+ mDto.getEmail());
		MemberDAO mDao = new MemberDAO();
		String result = mDao.memberSearch(mDto);
		
		if ( result == null) {
			request.setAttribute("result_DAO", "일치하는 정보가 없습니다.");	
		}else {
			request.setAttribute("result_DAO", result);	
		}
		//System.out.println("result : " + result);
		ActionForward af = new ActionForward();
		af.setPath("result_DAO.jsp");
		af.setRedirect(true);
		
		return af;
		
	}

}
