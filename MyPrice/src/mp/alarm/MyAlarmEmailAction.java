package mp.alarm;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.common.GetInfoDAO;
import mp.common.productPriceDTO;
import mp.util.Action;
import mp.util.ActionForward;
import mp.util.EmailSender;

public class MyAlarmEmailAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		AlarmDAO aDao = new AlarmDAO();
		GetInfoDAO gDao = new GetInfoDAO();
		boolean sendYn = false;
		int sendCnt = 0;
		
		ArrayList<AlarmDTO> arList = aDao.alarmEmailList();
		String[] fris = gDao.getFRIs();	// 최근 금요일 날짜만 필요
		System.out.println("가격알람 이메일 대상자 : " + arList.size());
		for (int i=0; i<arList.size(); i++) {
			List<productPriceDTO> arProdPrice = gDao.getProdcutPrice(fris[0], "goodId", String.valueOf(arList.get(i).getGoodid()));
			arList.get(i).setThisweek_price(gDao.getProdcutPriceWithEntiId(arProdPrice, arList.get(i).getEntpid()));	// 기준일자 가격
			//System.out.println(fris[0] +" / "+arList.get(i).getId() +" / "+arList.get(i).getGoodid() +" / "+arList.get(i).getEntpid() +" / "+arList.get(i).getAlarm_price_ud() +" / this"+arList.get(i).getThisweek_price() +"/ar "+arList.get(i).getAlarm_price());
			if (arList.get(i).getAlarm_price_ud().equals("UP")) {
				//System.out.println("Up");
				if (arList.get(i).getThisweek_price() >= arList.get(i).getAlarm_price() && !(arList.get(i).getThisweek_price()==0)) {
					System.out.println("이상 이번주 가격 / 알람가격 : "  +" / "+ arList.get(i).getThisweek_price() +" / "+ arList.get(i).getAlarm_price());
					sendYn = true;
				}
			}else {
				//System.out.println("Down");
				if (arList.get(i).getThisweek_price() <= arList.get(i).getAlarm_price() && !(arList.get(i).getThisweek_price()==0) ) {
					System.out.println("이하  이번주 가격 / 알람가격 : " + arList.get(i).getId() +" / "+ arList.get(i).getThisweek_price() +" / "+ arList.get(i).getAlarm_price());
					sendYn = true;
				}
			}
			
			if (sendYn) {
				
				// 이메일 보내기
				EmailSender es = new EmailSender();
				String subject = "[MyPrice_가격알람] " + arList.get(i).getId()+"님, [" + arList.get(i).getGoodname() +"] 상품에 대한 가격알람메일 입니다.";
				String body = "<b>안녕하세요, [My Price, 참가격] 입니다.<br>" + arList.get(i).getId()+" 님이 " + arList.get(i).getReg_date() + "일자에 등록한 가격알람 서비스 입니다.<br><br>"
						+ "<p style='color: red;'>* 알람 요청기간 동안 매주 금요일 가격알람 메일이 발송됩니다.<br> 원하지 않으시면 [참가격사이트 - 가격알람] 메뉴에서 가격알람 요청내용을 수정하시기 바랍니다.</p><br><br>"
						+ "[ 가격알람 등록 내용 ] <br></b>"
						+ " <p style='width: 100%;background-color: #fafafa;'>알람 상품명 : " + arList.get(i).getGoodname() + "<br>"
								+ "알람 점포명 : " + arList.get(i).getEntpname() +  "<br>"
								+ "알람 가격 : "+ arList.get(i).getAlarm_price() + "원 " + arList.get(i).getAlarm_price_ud_name()+"<br>"
								+ "알람 요청하신 기간 : " + arList.get(i).getAlarm_sdate() +" ~ " + arList.get(i).getAlarm_edate() + "<br>"
								 + "</p><br><br>"
						+ "<b>[ 현재 가격 정보 ]</b><br><p style='width: 100%;background-color: #fafafa;'>"
								+ arList.get(i).getEntpname() + " - " + arList.get(i).getGoodname() + " : " + arList.get(i).getThisweek_price() + "원</p><br>"; 
				
				es.sendEmail(arList.get(i).getId(), arList.get(i).getId_email(), subject, body);
				System.out.println("이메일 보내기 완료: " + arList.get(i).getId());
				sendCnt++;
				sendYn = false;
			}
	
		}
		System.out.println("이메일 발송 건수 : " + sendCnt);
		ActionForward af = new ActionForward();
		request.setAttribute("result_message", sendCnt);
		af.setPath("result_message.jsp");	
		af.setRedirect(true);
		return af;
	
	}

}
