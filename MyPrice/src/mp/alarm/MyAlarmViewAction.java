package mp.alarm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.common.GetInfoDAO;
import mp.common.productPriceDTO;
import mp.util.Action;
import mp.util.ActionForward;

public class MyAlarmViewAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
				
		int num = Integer.parseInt(request.getParameter("num"));
		
		AlarmDAO rDao = new AlarmDAO();
		AlarmDTO rDto = rDao.alarmView(num);
		
		GetInfoDAO gDao = new GetInfoDAO();
		List<productPriceDTO> ar = null;
		ar = gDao.getProdcutPrice(rDto.getInspectday(), "goodId", String.valueOf(rDto.getGoodid()));
		//System.out.println("가격정보 길이 : " + ar.size() + "/" + rDto.getEntpid() +" / "+ rDto.getGoodid());
		int inspect_price = gDao.getProdcutPriceWithEntiId(ar, (long)rDto.getEntpid());	// 기준일자 가격
		
		String[] fris = gDao.getFRIs();
		System.out.println("금요일 날짜들 : " + fris[0] +"/"+ fris[1] +"/"+ fris[2]+"/"+ fris[3]);
		ar = gDao.getProdcutPrice(fris[0], "goodId", String.valueOf(rDto.getGoodid()));
		int thisweek_price = gDao.getProdcutPriceWithEntiId(ar, (long)rDto.getEntpid());	// 이번주 가격
		
		ar = gDao.getProdcutPrice(fris[1], "goodId", String.valueOf(rDto.getGoodid()));
		int before1w_price = gDao.getProdcutPriceWithEntiId(ar, (long)rDto.getEntpid());	// 일주일전 가격
		
		ar = gDao.getProdcutPrice(fris[2], "goodId", String.valueOf(rDto.getGoodid()));
		int before1m_price = gDao.getProdcutPriceWithEntiId(ar, (long)rDto.getEntpid());	// 한달전 가격
				
		ActionForward af = new ActionForward();
		if (rDto!=null) {
			System.out.println(rDto.getGoodname());
			request.setAttribute("rDto", rDto);
			request.setAttribute("inspect_price", inspect_price);
			request.setAttribute("thisweek_price", thisweek_price);
			request.setAttribute("before1w_price", before1w_price);
			request.setAttribute("before1m_price", before1m_price);
			af.setPath("myAlarmUpdate.jsp");
		}else {
			request.setAttribute("result_message", "해당 알람정보가 없습니다.");
			request.setAttribute("result_action", "MyAlarmViewAction");
			af.setPath("result_message.jsp");	
		}
		af.setRedirect(true);	
		
		return af;
	
		
	}

	private int getProdcutPriceWithEntiId(List<productPriceDTO> ar, long goodid) {
		// TODO Auto-generated method stub
		return 0;
	}

}
