package mp.basketPrice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.common.GetInfoDAO;
import mp.common.productPriceDTO;
import mp.util.Action;
import mp.util.ActionForward;

public class BasketFinalAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<BasketInfoDTO> t;
		System.out.println("Final진입했어Final");
		
		BasketInfoDAO bdao = new BasketInfoDAO();
		List<productPriceDTO> t_price;
		ArrayList<BasketInfoDTO> temp=new ArrayList<BasketInfoDTO>();
		BasketInfoDTO tt;
		GetInfoDAO gdao=new GetInfoDAO();
		String[] Fri=gdao.getFRIs();
		//goodInspectDay는 셋팅에 따라서 달라질 수 있음.
		String goodInspectDay=Fri[0]; //전전 금요일 날짜를 얻어온다.
		String total_string;
		int total=0;
		
		System.out.println(goodInspectDay);
		System.out.println("bdao92193: "+bdao);
		
		String entpId = request.getParameter("entpId");
		System.out.println("entpId234234: "+entpId);
		
		String code = request.getParameter("code");
		System.out.println("code234234:"+code);
		
		//1. code와 entpId를 가지고 db 에서 값들을 얻어온다.
		t=bdao.basketNameList(code);
		for(int i=0;i<t.size();i++)
		{
			//System.out.println(t.get(i).getGoodId());
			//System.out.println(t.get(i).getGoodName());
			
			//2. goodId를 기준으로 한 xml 파일을 열어서 얻어온다.
			t_price=bdao.getProdcutPrice(goodInspectDay,"goodId",t.get(i).getGoodId(),entpId);
			System.out.println("size : "+t_price.size());
			
			tt=new BasketInfoDTO();
			
			if(t_price.size()>0)
			{
				total=t_price.get(0).getGoodPrice();
				total_string=String.valueOf(total);
			}
			
			else
			{
				total=-1;
				total_string="물건없음";
			}
			
			tt.setGoodName(t.get(i).getGoodName());
			tt.setGoodPrice(total_string);
			tt.setavrprice(bdao.get_avr());
			//System.out.println("price : "+tt.getGoodPrice());
			temp.add(tt);
		}
		
		request.setAttribute("goodName2", temp);
		
		ActionForward af = new ActionForward();
		af.setPath("myprice_info/basket/basketFinal.jsp");	
		af.setRedirect(true);
		
		return af;
	}

}
