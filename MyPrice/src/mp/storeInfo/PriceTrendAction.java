package mp.storeInfo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.common.GetInfoDAO;
import mp.common.GetInfoDTO;
import mp.util.Action;
import mp.util.ActionForward;

public class PriceTrendAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("priceTrendAction접근성공");
		StoreInfoDAO sd = new StoreInfoDAO();
		GetInfoDAO gd =  new GetInfoDAO();
		
		ArrayList<GetInfoDTO> ar2 = gd.entpList("entpAreaCode", "AR", "GETSTOREINFOSVC");
		ArrayList<GetInfoDTO> ar3 = gd.entpList("entpBrandCode", "BC", "GETSTOREINFOSVC");
		
		request.setAttribute("entpArea", ar2);
		request.setAttribute("entpBrand", ar3);
		
		ActionForward af = new ActionForward();
		af.setPath("myprice_trend/priceTrend.jsp");
		af.setRedirect(true);
		return af;
	}

}
