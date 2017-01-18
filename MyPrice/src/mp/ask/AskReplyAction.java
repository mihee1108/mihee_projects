package mp.ask;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;
import mp.util.EmailSender;


public class AskReplyAction implements Action {

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
		
		System.out.println("Mod reply_yn : " + request.getParameter("reply_yn"));
		AskDAO aDao = new AskDAO();
		int result = aDao.askReply(aDto);
		
		EmailSender es = new EmailSender();
		String subject = "[MyPrice_답변] " + request.getParameter("title");
		String body = "<b>안녕하세요, [My Price, 참가격] 입니다.<br>" + request.getParameter("writer")+" 님 질문에 대한 답변 입니다.<br><br><br>"
				+ "[ 질문 내용 ] <br></b> <p style='width: 100%;background-color: #fafafa;'>" + request.getParameter("contents") + "</p><br><b>"
				+ "[ 답    변 ]</b><br><p style='width: 100%;background-color: #fafafa;'>" + request.getParameter("reply_contents") + "</p><br>"; 
		
		es.sendEmail(request.getParameter("writer"), request.getParameter("email"), subject, body);
		
		if (result>0) {
			request.setAttribute("result_message", aDto.getNum()+"번 문의 글에 대한 답변메일을 전송하였습니다.");		
		}else {
			request.setAttribute("result_message", "답변메일 전송 실패 ㅠㅠ");
		}
		
		ActionForward af = new ActionForward();
		
		request.setAttribute("result_action", "AskReplyAction");
		request.setAttribute("curPage", curPage);
		af.setPath("result_alert.jsp");
		af.setRedirect(true);			
	
		return af;

	}

}
