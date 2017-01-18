package mp.alarm;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;

public class MyAlarmWriteAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		AlarmDTO rDto = new AlarmDTO();
		
		rDto.setId(request.getParameter("id"));
		rDto.setGoodid(Integer.parseInt(request.getParameter("goodid")));
		rDto.setEntpid(Integer.parseInt(request.getParameter("entpid")));
		rDto.setAlarm_price(Integer.parseInt(request.getParameter("alarm_price")));
		rDto.setAlarm_sdate(Date.valueOf(request.getParameter("alarm_sdate")));
		rDto.setAlarm_edate(Date.valueOf(request.getParameter("alarm_edate")));
		rDto.setAlarm_price_ud(request.getParameter("alarm_price_ud"));
		rDto.setAlarm_yn(request.getParameter("alarm_yn"));
		rDto.setInspectday(request.getParameter("inspectday"));
		
		AlarmDAO rDao = new AlarmDAO();
		int result = rDao.alarmInsert(rDto);
		
		request.setAttribute("result_message", result);
		
		ActionForward af = new ActionForward();
		af.setPath("result_message.jsp");
		af.setRedirect(true);
		return af;
	}

}
