package mp.member;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.util.Action;
import mp.util.ActionForward;
import mp.util.ExcelFileMaker;

public class MyInfoAdminSaveAction implements Action {

	@Override
	public ActionForward process(HttpServletRequest request, HttpServletResponse response) {
		// 회원 조회 전체결과를 파일저장할 것이므로 페이징 작업 안함
		MemberDAO mDao = new MemberDAO();
		MemberSearchDTO msDto = new MemberSearchDTO();
				
		msDto.setId(request.getParameter("id"));
		if ( !(msDto.getId() == null)) {	// 조건 있으면			
			msDto.setOut_yn(request.getParameter("out_yn"));
			msDto.setGender(request.getParameter("gender"));
			msDto.setPhone(request.getParameter("phone"));
			msDto.setStore(Integer.parseInt(request.getParameter("store")=="" ? "0" : request.getParameter("store")));
			msDto.setInputdttm_s(request.getParameter("inputdttm_s"));
			msDto.setInputdttm_e(request.getParameter("inputdttm_e"));		
			msDto.setOut_date_s(request.getParameter("out_date_s"));
			msDto.setOut_date_e(request.getParameter("out_date_e"));
			
		}
		ArrayList<MemberDTO> ar = null;
		ar = mDao.memberList(msDto);
		
		System.out.println("ar size : " + ar.size());
		
		//임의의 VO가 되주는 MAP 객체
		HashMap<String,Object> map=null;
		//가상 DB조회후 목록을 담을 LIST객체
		ArrayList<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
		ArrayList<String> columnList=new ArrayList<String>();
		//DB조회후 데이터를 담았다는 가상의 데이터
		for(int i=0;i<ar.size();i++){
		    map=new LinkedHashMap<String,Object>();	//LinkedHashMap : 무조건 입력한 순서대로 get됨
		    map.put("아이디", ar.get(i).getId());
		    map.put("비밀번호", ar.get(i).getPassword());
		    map.put("전화번호1", ar.get(i).getPhone_1());
		    map.put("전화번호2", ar.get(i).getPhone_2());
		    map.put("전화번호3", ar.get(i).getPhone_3());
		    map.put("성별", ar.get(i).getGender());
		    map.put("생년월일", ar.get(i).getBirthday());
		    map.put("이메일주소", ar.get(i).getEmail());
		    map.put("이메일 수신여부", ar.get(i).getEmail_yn());
		    map.put("문자 수신여부", ar.get(i).getSms_yn());
		    map.put("가입일자", ar.get(i).getInputdttm());
		    map.put("탈퇴여부", ar.get(i).getOut_yn());
		    map.put("탈퇴일자", ar.get(i).getOut_date());
		    map.put("관심점포1", ar.get(i).getStore_1());
		    map.put("관심점포2", ar.get(i).getStore_2());
		    map.put("관심점포3", ar.get(i).getStore_3());
		    map.put("관심점포명1", ar.get(i).getEntpname_1());
		    map.put("관심점포명2", ar.get(i).getEntpname_2());
		    map.put("관심점포명3", ar.get(i).getEntpname_3());
	
	
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
		
		String excelFileName = "adminMemberList_"+dTime;	
		
		ExcelFileMaker efm = new ExcelFileMaker();
		efm.makeExcelFile(list, columnList, excelFileName);
		
		System.out.println("엑셀파일생성성공 - adminMemberList");
		
		ActionForward af = new ActionForward();
		// 경로는 ExcelFileMaker.java 파일에서 박혀있음.
		request.setAttribute("result_message", "[ F:\\"+excelFileName+".xlsx ] 경로에 엑셀파일 저장이 되었습니다.");
		af.setPath("result_message.jsp");
		af.setRedirect(true);
		return af;
		
	}

}
