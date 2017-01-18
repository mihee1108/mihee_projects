package mp.alarm;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;

public class MyAlarmUniqueAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		
		AlarmDTO rDto = new AlarmDTO();
		
		rDto.setNum(Integer.parseInt(request.getParameter("num")));
		rDto.setId(request.getParameter("id"));
		rDto.setGoodid(Integer.parseInt(request.getParameter("goodid")));
		rDto.setEntpid(Integer.parseInt(request.getParameter("entpid")));
		rDto.setAlarm_price(Integer.parseInt(request.getParameter("alarm_price")));
		rDto.setAlarm_sdate(Date.valueOf(request.getParameter("alarm_sdate")));
		
		AlarmDAO rDao = new AlarmDAO();
		int counts = rDao.alarmUniqueCheck(rDto);
		System.out.println(counts+"/unique:"+ rDto.getNum() +"/"+ rDto.getId() +"/"+rDto.getGoodid()+"/"+ rDto.getEntpid() +"/"+rDto.getAlarm_price() +"/"+ rDto.getAlarm_sdate());
		request.setAttribute("result_message", counts);
		
		ActionForward af = new ActionForward();
		af.setPath("result_message.jsp");
		af.setRedirect(true);
		return af;
	}

}
