package mp.alarm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.common.GetInfoDAO;
import mp.common.productPriceDTO;
import mp.member.MemberDAO;
import mp.member.MemberDTO;
import mp.member.MemberSearchDTO;
import mp.util.Action;
import mp.util.ActionForward;
import mp.util.ExcelFileMaker;
import mp.util.PageMaker_hs;

public class AlarmAdminSaveAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		// 가격알람 조회 전체결과를 파일저장할 것이므로 페이징 작업 안함
		AlarmDAO rDao = new AlarmDAO();
		AlarmSearchDTO rsDto = new AlarmSearchDTO();

		rsDto.setId(request.getParameter("id"));
		if ( !(rsDto.getId() == null)) {	// 조건 있으면			
			rsDto.setGoodid(Integer.parseInt(request.getParameter("goodid")=="" ? "0" : request.getParameter("goodid")));
			rsDto.setEntpid(Integer.parseInt(request.getParameter("entpid")=="" ? "0" : request.getParameter("entpid")));
			rsDto.setAlarm_yn(request.getParameter("alarm_yn"));	
		}
		ArrayList<AlarmDTO> ar = null;
		ar = rDao.alarmList(null, rsDto);		
		//System.out.println("ar-Alarm size : " + ar.size());
		// 현재가격 셋팅
		// 현재가격 셋팅
		GetInfoDAO gDao = new GetInfoDAO();
		String[] fris = gDao.getFRIs();	// 최근 금요일 날짜만 필요
		for (int i=0; i<ar.size(); i++) {
			List<productPriceDTO> arProdPrice = gDao.getProdcutPrice(fris[0], "goodId", String.valueOf(ar.get(i).getGoodid()));
			ar.get(i).setThisweek_price(gDao.getProdcutPriceWithEntiId(arProdPrice, ar.get(i).getEntpid()));	// 기준일자 가격
		}
		
		//임의의 VO가 되주는 MAP 객체
		HashMap<String,Object> map=null;
		//가상 DB조회후 목록을 담을 LIST객체
		ArrayList<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
		ArrayList<String> columnList=new ArrayList<String>();
		//DB조회후 데이터를 담았다는 가상의 데이터
		for(int i=0;i<ar.size();i++){
		    map=new LinkedHashMap<String,Object>();	//LinkedHashMap : 무조건 입력한 순서대로 get됨
		    map.put("번호", ar.get(i).getNum());
		    map.put("아이디", ar.get(i).getId());
		    map.put("상품코드", ar.get(i).getGoodid());
		    map.put("상품명", ar.get(i).getGoodname());
		    map.put("점포코드", ar.get(i).getEntpid());
		    map.put("점포명", ar.get(i).getEntpname());
		    map.put("현재가격", ar.get(i).getThisweek_price());
		    map.put("알람가격", ar.get(i).getAlarm_price());
		    map.put("알람시작일자", ar.get(i).getAlarm_sdate());
		    map.put("알람종료일자", ar.get(i).getAlarm_edate());
		    map.put("알람가격이상이하코드", ar.get(i).getAlarm_price_ud());
		    map.put("알람가격이상이하", ar.get(i).getAlarm_price_ud_name());
		    map.put("알람여부", ar.get(i).getAlarm_yn());
		    map.put("기준일자", ar.get(i).getInspectday());
		    map.put("등록일자", ar.get(i).getReg_date());
		    
		    list.add(map);
		    
		}
		//MAP의 KEY값을 담기위함 
		if(list !=null &&list.size() >0){
		    //LIST의 첫번째 데이터의 KEY값만 알면 되므로 
			HashMap<String,Object> m =list.get(0);
		    //MAP의 KEY값을 columnList객체에 ADD
		    for(String k : m.keySet()){
		        columnList.add(k);
		        //System.out.println("col k : " + k);
		    }
		}
		//파일명 구분을 위한 파일생성시각 : 날짜(년/월/일/시/분/초) 구하기
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
		Date currentTime = new Date();
		String dTime = formatter.format(currentTime);
		System.out.println("4. 날짜(년/월/일/시/분/초) 구하기 : " + dTime);
		
		String excelFileName = "adminAlarmList_"+dTime;	
		
		ExcelFileMaker efm = new ExcelFileMaker();
		efm.makeExcelFile(list, columnList, excelFileName);
		
		System.out.println("엑셀파일생성성공 - adminAlarmList");
		
		ActionForward af = new ActionForward();
		// 경로는 ExcelFileMaker.java 파일에서 박혀있음.
		request.setAttribute("result_message", "[ F:\\"+excelFileName+".xlsx ] 경로에 엑셀파일 저장이 되었습니다.");
		af.setPath("result_message.jsp");
		af.setRedirect(true);
		return af;	
	}

}
