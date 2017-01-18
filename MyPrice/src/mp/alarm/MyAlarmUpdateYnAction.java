package mp.alarm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;

public class MyAlarmUpdateYnAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		
		String today = request.getParameter("today");
		
		AlarmDAO rDao = new AlarmDAO();
		int result = rDao.alarmUpdateYn();
		
		request.setAttribute("result_message", result);
		
		ActionForward af = new ActionForward();
		af.setPath("result_message.jsp");
		af.setRedirect(true);
		return af;	

	}

}
