package mp.alarm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import mp.common.GetInfoDAO;
import mp.common.productPriceDTO;
import mp.util.Action;
import mp.util.ActionForward;

public class MyAlarmPricesAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
	
		String json_yn = "N"; 
		if ( !(request.getParameter("json_yn") == null) ) {
			json_yn = request.getParameter("json_yn");
		};		
		String goodid = request.getParameter("goodid");
		long entpid = Long.parseLong(request.getParameter("entpid"));
		String inspectday = request.getParameter("inspectday");
		
		
		GetInfoDAO gDao = new GetInfoDAO();
		List<productPriceDTO> ar = null;
		ar = gDao.getProdcutPrice(inspectday, "goodId", goodid);
		//System.out.println("Write - Price - 가격정보 길이 : " + ar.size() + "/" + goodid+" / "+ entpid);
		int inspect_price = gDao.getProdcutPriceWithEntiId(ar, entpid);	// 기준일자 가격
		
		String[] fris = gDao.getFRIs();
		//System.out.println("Write - 금요일 날짜들 : " + fris[0] +"/"+ fris[1] +"/"+ fris[2]+"/"+ fris[3]);
		ar = gDao.getProdcutPrice(fris[0], "goodId", goodid);
		int thisweek_price = gDao.getProdcutPriceWithEntiId(ar, entpid);	// 이번주 가격
		
		ar = gDao.getProdcutPrice(fris[1], "goodId", goodid);
		int before1w_price = gDao.getProdcutPriceWithEntiId(ar, entpid);	// 일주일전 가격
		
		ar = gDao.getProdcutPrice(fris[2], "goodId", goodid);
		int before1m_price = gDao.getProdcutPriceWithEntiId(ar, entpid);	// 한달전 가격
		System.out.println("price action json_yn : " + json_yn);
		ActionForward af = new ActionForward();
		if (json_yn.equals("Y")) {
			//System.out.println("price action json_yn : yyy ");
			JSONArray jar = new JSONArray();
			JSONObject js = new JSONObject();
			
			js.put("inspect_price", inspect_price);
			js.put("thisweek_price", thisweek_price);
			js.put("before1w_price", before1w_price);
			js.put("before1m_price", before1m_price);
			
			jar.add(js);
			String js2 = js.toJSONString();		
			request.setAttribute("result_message", js2);
			af.setPath("/myPage/result_message.jsp");	
		}else {
			//System.out.println("price action json_yn : nnn ");
			request.setAttribute("goodid", goodid);
			request.setAttribute("entpid", entpid);
			request.setAttribute("inspectday", inspectday);
			request.setAttribute("goodname",gDao.getGoodNameWithId(Integer.parseInt(goodid)));
			request.setAttribute("entpname",gDao.getEntpNameWithId(entpid));
			request.setAttribute("inspect_price", inspect_price);
			request.setAttribute("thisweek_price", thisweek_price);
			request.setAttribute("before1w_price", before1w_price);
			request.setAttribute("before1m_price", before1m_price);
			
			af.setPath("/myPage/myAlarmWrite.jsp");	
		}
		af.setRedirect(true);
		
		return af;
	}
		
}
