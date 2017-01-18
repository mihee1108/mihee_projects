package mp.alarm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.notice.NoticeDAO;
import mp.util.Action;
import mp.util.ActionForward;

public class MyAlarmDelAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		AlarmDAO rDao = new AlarmDAO();
		int result = rDao.alarmDelete(num);

		request.setAttribute("result_message", result);
		
		ActionForward af = new ActionForward();
		af.setPath("result_message.jsp");
		af.setRedirect(true);
		return af;
	}

}
