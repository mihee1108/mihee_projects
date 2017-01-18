package mp.productInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;

public class GrapeAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {

		int today = Integer.parseInt(request.getParameter("today"));
		int week = Integer.parseInt(request.getParameter("week"));
		int month = Integer.parseInt(request.getParameter("month"));
		int months = Integer.parseInt(request.getParameter("months"));
		
		System.out.println("today들어요냐: "+request.getParameter("today"));
		System.out.println("today: "+today);
		System.out.println("week: "+week);
		

		request.setAttribute("today", today);
		request.setAttribute("week", week);
		request.setAttribute("month", month);
		request.setAttribute("months", months);
				
		ActionForward af = new ActionForward();
		af.setPath("forgrape.jsp");	
	    af.setRedirect(true);;
				
		return af;
	}

}
