package mp.alarm;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.notice.NoticeDAO;
import mp.notice.NoticeDTO;
import mp.util.Action;
import mp.util.ActionForward;

public class MyAlarmUpdateAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		AlarmDTO rDto = new AlarmDTO();
		
		rDto.setNum(Integer.parseInt(request.getParameter("num")));
		rDto.setAlarm_yn(request.getParameter("alarm_yn"));
		rDto.setAlarm_price(Integer.parseInt(request.getParameter("alarm_price")));
		rDto.setAlarm_price_ud(request.getParameter("alarm_price_ud"));
		rDto.setAlarm_sdate(Date.valueOf(request.getParameter("alarm_sdate")));
		rDto.setAlarm_edate(Date.valueOf(request.getParameter("alarm_edate")));
		
		AlarmDAO rDao = new AlarmDAO();
		int result = rDao.alarmUpdate(rDto);
		
		request.setAttribute("result_message", result);
		
		ActionForward af = new ActionForward();
		af.setPath("result_message.jsp");
		af.setRedirect(true);
		return af;

	}

}
