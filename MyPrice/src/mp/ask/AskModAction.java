package mp.ask;

import java.sql.Date;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.notice.NoticeDAO;
import mp.notice.NoticeDTO;
import mp.util.Action;
import mp.util.ActionForward;

public class AskModAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		AskDTO aDto = new AskDTO();
		int curPage = (Integer.parseInt(request.getParameter("curPage")));
		aDto.setNum(Integer.parseInt(request.getParameter("num")));
		aDto.setC_cls(request.getParameter("c_cls"));
		aDto.setMember_yn(request.getParameter("member_yn"));
		aDto.setReply_yn(request.getParameter("reply_yn"));
		aDto.setWriter(request.getParameter("writer"));
		aDto.setEmail(request.getParameter("email"));
		aDto.setTitle(request.getParameter("title"));
		aDto.setContents(request.getParameter("contents"));
		aDto.setReg_date(Date.valueOf(request.getParameter("reg_date")));
		aDto.setReply_writer(request.getParameter("reply_writer"));
		aDto.setReply_contents(request.getParameter("reply_contents"));
		aDto.setReply_date(Date.valueOf(request.getParameter("reply_date")));
		
		System.out.println("Mod reply_yn : " + request.getParameter("reply_yn"));
		AskDAO aDao = new AskDAO();
		int result = aDao.askMod(aDto);
		if (result>0) {
			if (aDto.getReply_yn().equals("Y") && aDto.getReply_date() == null) {
				request.setAttribute("result_message", aDto.getNum()+"번 문의 글에 대한 답변메일을 전송하였습니다.");
			}else {
				request.setAttribute("result_message", aDto.getNum()+"번 문의 글 수정 성공 !!");	
			}
			
		}else {
			request.setAttribute("result_message", "수정 실패 ㅠㅠ");
		}
		
		ActionForward af = new ActionForward();
		
		request.setAttribute("result_action", "AskModAction");
		request.setAttribute("curPage", curPage);
		af.setPath("result_alert.jsp");
		af.setRedirect(true);			
	
		return af;

	}

}
