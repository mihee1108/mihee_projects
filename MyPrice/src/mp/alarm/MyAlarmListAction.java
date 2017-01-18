package mp.alarm;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.common.GetInfoDAO;
import mp.common.productPriceDTO;
import mp.util.Action;
import mp.util.ActionForward;
import mp.util.PageMaker_hs;

public class MyAlarmListAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		// 개인회원 가격알람 조회
		// 현재가격 항목 추가 해야 함
		AlarmDAO rDao = new AlarmDAO();
		AlarmSearchDTO rsDto = new AlarmSearchDTO();
				
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int perPage = 10;
		System.out.println(request.getParameter("id"));
		rsDto.setId(request.getParameter("id"));
		rsDto.setGoodid(Integer.parseInt(request.getParameter("goodid")=="" ? "0" : request.getParameter("goodid")));
		rsDto.setEntpid(Integer.parseInt(request.getParameter("entpid")=="" ? "0" : request.getParameter("entpid")));
		rsDto.setAlarm_yn(request.getParameter("alarm_yn"));
				
		int totalCount = rDao.getTotalCnt(rsDto);

		PageMaker_hs pm = new PageMaker_hs(curPage, perPage, totalCount);
		pm.makePageing();
		
		ArrayList<AlarmDTO> ar = null;
		ar = rDao.alarmList(pm, rsDto);
		
		// 현재가격 셋팅
		GetInfoDAO gDao = new GetInfoDAO();
		String[] fris = gDao.getFRIs();	// 최근 금요일 날짜만 필요
		for (int i=0; i<ar.size(); i++) {
			List<productPriceDTO> arProdPrice = gDao.getProdcutPrice(fris[0], "goodId", String.valueOf(ar.get(i).getGoodid()));
			ar.get(i).setThisweek_price(gDao.getProdcutPriceWithEntiId(arProdPrice, ar.get(i).getEntpid()));	// 기준일자 가격
		}
	
		request.setAttribute("list", ar);
		request.setAttribute("pm", pm);
		request.setAttribute("curPage", curPage);
		request.setAttribute("rsDto", rsDto);
		
		ActionForward af = new ActionForward();

		System.out.println("myalarmlist");
		af.setPath("myAlarmList.jsp");

		af.setRedirect(true);
		return af;
	}

}
