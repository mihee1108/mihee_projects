package mp.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;
import mp.util.PageMaker_hs;

public class MyInfoAdminListAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		
		// 회원 조회
		MemberDAO mDao = new MemberDAO();
		MemberSearchDTO msDto = new MemberSearchDTO();
		
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int perPage = 10;
		msDto.setId(request.getParameter("id"));
		if ( !(msDto.getId() == null)) {
		msDto.setOut_yn(request.getParameter("out_yn"));
		msDto.setGender(request.getParameter("gender"));
		msDto.setPhone(request.getParameter("phone"));
		//System.out.println("m s store: " + request.getParameter("store"));
		msDto.setStore(Integer.parseInt(request.getParameter("store")=="" ? "0" : request.getParameter("store")));
		msDto.setInputdttm_s(request.getParameter("inputdttm_s"));
		msDto.setInputdttm_e(request.getParameter("inputdttm_e"));		
		msDto.setOut_date_s(request.getParameter("out_date_s"));
		msDto.setOut_date_e(request.getParameter("out_date_e"));
		
		}
		
				
		int totalCount = 0;
		if (msDto.getId() == null) {
			totalCount = mDao.getTotalCnt();
		}else {
			totalCount = mDao.getTotalCnt(msDto);
		}
		PageMaker_hs pm = new PageMaker_hs(curPage, perPage, totalCount);
		pm.makePageing();
		ArrayList<MemberDTO> ar = null;
		if (msDto.getId() == null) {
			ar = mDao.memberList(pm);
		}else {
			ar = mDao.memberList(pm, msDto);
		}
		
		request.setAttribute("list", ar);
		request.setAttribute("pm", pm);
		request.setAttribute("curPage", curPage);
		request.setAttribute("msDto", msDto);
		
		ActionForward af = new ActionForward();
		//if (msDto.getId() == null) {
		//	System.out.println("member");
		//	af.setPath("admin_member.jsp");
		//}else {
			System.out.println("memberlist");
			af.setPath("admin_memberList.jsp");
		//}
		
		af.setRedirect(true);
		return af;
	}

}
