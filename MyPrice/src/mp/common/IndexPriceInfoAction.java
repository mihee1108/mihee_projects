package mp.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;

public class IndexPriceInfoAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		
		String smlclscode = request.getParameter("smlclscode");
		GetInfoDAO gDao = new GetInfoDAO();
		IndexInfoDTO iDto = new IndexInfoDTO();
		
		ArrayList<IndexInfoDTO> ar = gDao.getIndexPrice(smlclscode);
		//for (int i=0; i<ar.size();i++) {System.out.println("index price info : " + smlclscode +" / "+ ar.get(i).getSmlclscode()+" / "+ ar.get(i).getGoodname());	}
		request.setAttribute("smlclscodename", ar.get(0).getSmlclscodename());
		request.setAttribute("list", ar);	
				
		ActionForward af = new ActionForward();
		af.setPath("template/index/index_priceInfo.jsp");
		af.setRedirect(true);
		return af;
		
	}

}
