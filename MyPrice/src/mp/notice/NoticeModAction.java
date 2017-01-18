package mp.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.notice.NoticeDTO;
import mp.util.Action;
import mp.util.ActionForward;

public class NoticeModAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		NoticeDTO nDto = new NoticeDTO();
		int curPage = (Integer.parseInt(request.getParameter("curPage")));
		nDto.setNum(Integer.parseInt(request.getParameter("num")));
		nDto.setB_cls(request.getParameter("b_cls"));
		nDto.setC_cls(request.getParameter("c_cls"));
		nDto.setWriter(request.getParameter("writer"));
		nDto.setTitle(request.getParameter("title"));
		nDto.setContents(request.getParameter("contents"));
			
		NoticeDAO nDao = new NoticeDAO();
		int result = nDao.noticeMod(nDto);
		if (result>0) {
			request.setAttribute("result_message", nDto.getNum()+"번 글 수정 성공 !!");
		}else {
			request.setAttribute("result_message", "수정 실패 ㅠㅠ");
		}
		
		ActionForward af = new ActionForward();
		System.out.println("mod : result_alert.jsp");
		request.setAttribute("result_action", "NoticeModAction");
		request.setAttribute("b_cls", request.getParameter("b_cls"));
		request.setAttribute("curPage", curPage);
		af.setPath("result_alert.jsp");
		af.setRedirect(true);			
	
		return af;
	
	}

}
