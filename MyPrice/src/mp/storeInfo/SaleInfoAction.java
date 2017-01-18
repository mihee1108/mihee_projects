package mp.storeInfo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.common.GetInfoDAO;
import mp.common.GetInfoDTO;
import mp.util.Action;
import mp.util.ActionForward;
import mp.util.PageMaker;

public class SaleInfoAction implements Action {


	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		
		/*System.out.println("storeListAction까지 진입했다..");*/
		
		
		GetInfoDAO gd =  new GetInfoDAO();
		/*System.out.println("sd: "+sd);*/
		

		ArrayList<GetInfoDTO> ar2 = gd.entpList("entpAreaCode", "AR", "GETSTOREINFOSVC");
		ArrayList<GetInfoDTO> ar3 = gd.entpList("entpBrandCode", "BC", "GETSTOREINFOSVC");


		request.setAttribute("entpArea", ar2);
		request.setAttribute("entpBrand", ar3);

		
		
		ActionForward af = new ActionForward();
		af.setPath("myprice_trend/saleInfo.jsp");
		af.setRedirect(true);
		/*System.out.println("saleinfo.jsp로 간다이제..");*/
		return af;
	}

}