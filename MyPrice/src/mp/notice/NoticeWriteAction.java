package mp.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.notice.NoticeDAO;
import mp.notice.NoticeDTO;
import mp.util.Action;
import mp.util.ActionForward;

public class NoticeWriteAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		NoticeDAO nDao = new NoticeDAO();
		NoticeDTO nDto = new NoticeDTO();
		
		nDto.setB_cls(request.getParameter("b_cls"));
		nDto.setC_cls(request.getParameter("c_cls"));
		nDto.setWriter(request.getParameter("writer"));
		nDto.setTitle(request.getParameter("title"));
		nDto.setContents(request.getParameter("contents"));
	
		int result = nDao.noticeWrite(nDto);
		if (result>0) {
			request.setAttribute("result_message", "글 쓰기 성공 !!");
		}else {
			request.setAttribute("result_message", "쓰기 실패 ㅠㅠ");
		}
		
		ActionForward af = new ActionForward();
		request.setAttribute("result_action", "NoticeWriteAction");
		request.setAttribute("b_cls", request.getParameter("b_cls"));
		af.setPath("result_alert.jsp");
		af.setRedirect(true);
		return af;

	}

}
