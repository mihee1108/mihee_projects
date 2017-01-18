package mp.ask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.notice.NoticeDAO;
import mp.notice.NoticeDTO;
import mp.util.Action;
import mp.util.ActionForward;

public class AskWriteAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		AskDAO aDao = new AskDAO();
		AskDTO aDto = new AskDTO();
		
		aDto.setC_cls(request.getParameter("c_cls"));
		aDto.setMember_yn(request.getParameter("member_yn"));
		aDto.setReply_yn(request.getParameter("reply_yn"));
		aDto.setWriter(request.getParameter("writer"));
		aDto.setEmail(request.getParameter("email"));
		aDto.setTitle(request.getParameter("title"));
		aDto.setContents(request.getParameter("contents"));
	
		int result = aDao.askWrite(aDto);
		if (result>0) {
			request.setAttribute("result_message", "문의 글 쓰기 성공. 메일답변을 기다려 주세요.!!");
		}else {
			request.setAttribute("result_message", "쓰기 실패 ㅠㅠ");
		}
		
		ActionForward af = new ActionForward();
		request.setAttribute("result_action", "AskWriteAction");
		af.setPath("result_alert.jsp");
		af.setRedirect(true);
		return af;

	}

}
