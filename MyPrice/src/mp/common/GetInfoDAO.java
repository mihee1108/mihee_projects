package mp.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.NodeList;

import mp.alarm.AlarmDTO;
import mp.member.MemberDTO;
import mp.storeInfo.StoreInfoDTO;
import mp.util.DBConnector;
import mp.util.PageMaker;

public class GetInfoDAO {
	
	// (2016-12-02) 상품 리스트 : 삭제
	// (2016-12-02) 점포 리스트 : 삭제
	
	// (2016-12-04) 상품 별 전체 평균가격
	public ArrayList<IndexInfoDTO> getIndexPrice(String smlclscode){
		Connection con = DBConnector.getConnect();
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<IndexInfoDTO> ar = new ArrayList<>(); 
		String[] fris = getFRIs();
		
		String sql = "select substr(p.goodSmlclsCode, 1, 6) as smlscls, c2.codename as smlsclsname, p.goodid, p.goodname, p.goodtotalcnt||c1.codename as gooddan, " 
				+ "round(avg(pp.goodprice)) as thisw,  "    
				+ "round(avg(b1w.goodprice)) as before1w, round(avg(b1m.goodprice)) as before1m, round(avg(b3m.goodprice)) as before3m, "
				+ "decode(round((( round(avg(pp.goodprice))-round(avg(b1w.goodprice)) ) / round(avg(b1w.goodprice))) * 100, 2), null, 0,round((( round(avg(pp.goodprice))-round(avg(b1w.goodprice)) ) / round(avg(b1w.goodprice))) * 100, 2) ) as gap "
				+ "from GETPRODUCTINFOSVC p, GETSTANDARDINFOSVC c1, GETSTANDARDINFOSVC c2, GETPRODUCTPRICEINFOSVC pp, "
				+ "GETPRODUCTPRICEINFOSVC b1w, GETPRODUCTPRICEINFOSVC b1m, GETPRODUCTPRICEINFOSVC b3m "
				+ "where substr(p.goodSmlclsCode, 1, 6) = ? "
				+ "and p.goodtotaldivcode = c1.code "
				+ "and c1.classcode = 'UT' "
				+ "and substr(p.goodSmlclsCode, 1, 6) = substr(c2.code, 1, 6) "
				+ "and c2.classcode = 'AL' "
				+ "and substr(c2.code, 7, 9) = '000' "
				+ "and substr(c2.code, 5, 9) <> '00000' "
				+ "and p.goodid = pp.goodid "
				+ "and pp.goodInspectDay = ? "
				+ "and pp.goodid = b1w.goodid(+) "
				+ "and pp.entpid = b1w.entpid(+) "
				+ "and b1w.goodInspectDay(+) = ? "
				+ "and pp.goodid = b1m.goodid(+) "
				+ "and pp.entpid = b1m.entpid(+) "
				+ "and b1m.goodInspectDay(+) = ? "
				+ "and pp.goodid = b3m.goodid(+) "
				+ "and pp.entpid = b3m.entpid(+) "
				+ "and b3m.goodInspectDay(+) = ? "
				+ "group by substr(p.goodSmlclsCode, 1, 6), c2.codename, p.goodid, p.goodname, p.goodtotalcnt||c1.codename "
				+ "order by 1, 3";
	
		System.out.println(sql);
		try {
			st = con.prepareStatement(sql);
			st.setString(1, smlclscode);
			st.setString(2, fris[0]);
			st.setString(3, fris[1]);
			st.setString(4, fris[2]);
			st.setString(5, fris[3]);
			
			rs = st.executeQuery();
		
			while (rs.next()) {
				IndexInfoDTO iDto = new IndexInfoDTO();
				iDto.setSmlclscode(rs.getString(1));
				iDto.setSmlclscodename(rs.getString(2));
				iDto.setGoodid(rs.getString(3));
				iDto.setGoodname(rs.getString(4));
				iDto.setGooddan(rs.getString(5));
				iDto.setThisweek_price(rs.getInt(6));
				iDto.setBefore1w_price(rs.getInt(7));
				iDto.setBefore1m_price(rs.getInt(8));
				iDto.setBefore3m_price(rs.getInt(9));
				iDto.setPrice_gap(rs.getDouble(10));
				
				ar.add(iDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);
		}
				
		return ar;
	}
	
	
	// (2016-12-01) 점포명칭
	public String getEntpNameWithId(long entpid) {
		PreparedStatement st = null;
		ResultSet rs = null;		
		Connection con = DBConnector.getConnect();
		String entpname = "";
		
		String sql = "select TRIM(ENTPNAME) from GETSTOREINFOSVC WHERE ENTPID = ?";
		System.out.println(sql);
		
		try {
			st = con.prepareStatement(sql);
			st.setLong(1, entpid);
			rs = st.executeQuery();
			
			if (rs.next()) {	
				entpname = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);
		}
		return entpname;
		
	}
	// (2016-12-01) 상품명칭
	public String getGoodNameWithId(int goodid) {
		PreparedStatement st = null;
		ResultSet rs = null;		
		Connection con = DBConnector.getConnect();
		String goodname = "";
		
		String sql = "select TRIM(goodNAME) from GETPRODUCTINFOSVC where goodID = ?";
		System.out.println(sql);
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, goodid);
			rs = st.executeQuery();
			
			if (rs.next()) {	
				goodname = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);
		}
		return goodname;
		
	}
	
	// (2016-11-30 : 김혜선) 오늘날짜 기준으로 4개 값 리턴 : 1)진전 금요일날짜, 2)일주일전 날짜의 진전 금요일날짜, 3)한달전 날짜의 직전 금요일날짜, 4) 세달전 날짜의 직전 금요일날짜
	public String[] getFRIs() {
		Connection con = DBConnector.getConnect();
		PreparedStatement st = null;
		ResultSet rs = null;
		String[] fris = new String[4];
		String day = "";
		String toFri = "";
		
		String sql="select to_char(sysdate, 'd') from dual";	// 오늘날짜 요일
		try {
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if (rs.next()) {	
				day = rs.getString(1);
			}
			toFri = getDay(day);
			st.close();
			rs.close();
			sql = "select to_char(sysdate-"+toFri+", 'YYYYMMDD') from dual";	// 1)
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if (rs.next()) {	
				fris[0] = rs.getString(1);
			}
			st.close();
			rs.close();
			sql = "select to_char(sysdate-"+ (Integer.parseInt(toFri)+7) +", 'YYYYMMDD') from dual";	// 2)
			//System.out.println(sql);
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if (rs.next()) {	
				fris[1] = rs.getString(1);
			}
			st.close();
			rs.close();
			
			sql = "select to_char(add_months(sysdate, -1), 'd') from dual";	//한달전 날짜의 요일
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if (rs.next()) {	
				day = rs.getString(1);
			}
			toFri = getDay(day);
			st.close();
			rs.close();
			sql = "select to_char(add_months(sysdate, -1)-"+toFri+", 'YYYYMMDD') from dual";	// 3)
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if (rs.next()) {	
				fris[2] = rs.getString(1);
			}
			st.close();
			rs.close();
			
			sql = "select to_char(add_months(sysdate, -3), 'd') from dual";	//세달전 날짜의 요일
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if (rs.next()) {	
				day = rs.getString(1);
			}
			toFri = getDay(day);
			st.close();
			rs.close();
			sql = "select to_char(add_months(sysdate, -3)-"+toFri+", 'YYYYMMDD') from dual";	// 4)
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if (rs.next()) {	
				fris[3] = rs.getString(1);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);
		}
		return fris;
	}
	
	
	// (2016-11-30 : 김혜선) 요일 숫자 입력 => 해당 요일의 직전 금요일까지의 마이너스 날짜 수
	public String getDay(String day) {
		String toFriNum = "";
		
		if (day.equals("1")) {	// 일요일이면
			toFriNum = "2";
		}else if (day.equals("2")) {
			toFriNum = "3";
		}else if (day.equals("3")) {
			toFriNum = "4";
		}else if (day.equals("4")) {	// 수요일이면
			toFriNum = "5";
		}else if (day.equals("5")) {
			toFriNum = "6";
		}else if (day.equals("6")) {
			toFriNum = "0";
		}else {
			toFriNum = "1";	// 토요일이면
		}
		
		return toFriNum;
	}
	
	// (2016-11-30 : 김혜선) 상품가격ar에서 entpId기준으로 가격 가지고 오기
	public int getProdcutPriceWithEntiId(List<productPriceDTO> ar, long entpId) {
		int goodPrice = 0;
		//System.out.println("getProdcutPriceWithEntiId - 가격정보 길이 / entpId : " + ar.size() + " / "+entpId);
		for (int i=0; i<ar.size(); i++) {
			//System.out.println(ar.get(i).getGoodInspectDay() +" / "+ ar.get(i).getEntpId() +" / "+ ar.get(i).getGoodId() +" / "+ ar.get(i).getGoodPrice());
			if (ar.get(i).getEntpId() == entpId) {
				goodPrice = ar.get(i).getGoodPrice(); 
				break;
			}
		}
		return goodPrice;
	}
	
	//(2016-11-29 : 김미희) productPrice 메서드
	//(2016-11-30 : 김혜선) url 연결에 = 추가 / ar.add(p)을 for문 한단계 밖으로 이동
	public List<productPriceDTO> getProdcutPrice(String goodInspectDay, String ChoiceId, String realId){
		//choiceId 변수에는 entpId= 혹은 goodId= 를 넣어햐함
		//realId 변수에는 각 choiceId에서 선택한 파라미터에 따른 실제 코드를 넣어주어야함.
		String makeDay = goodInspectDay;
		if (!(goodInspectDay.length() == 8)) {
			String[] array = makeDay.split("-"); //2016-11-04식으로 들어온 값에서 '-'제거
			makeDay=array[0]+array[1]+array[2]; //20161104 로 변환한 값을 makeDay에 넣기, 이값을 db에 보냄
		}
					
		String url = "http://openapi.price.go.kr/openApiImpl/ProductPriceInfoService"
			          + "/getProductPriceInfoSvc.do?serviceKey="
			          + "rGPbxtOrWH0ExHZaaP7PBV%2FcPPnSkpK6vB0B2xeeqHge0SW%2FgiU8K8U3oc48%2BhLxDeQWGAr1plT%2BKKu1t4PA8w%3D%3D&goodInspectDay=" 
			          + makeDay + "&" + ChoiceId +"="+ realId; 
		System.out.println(url);
		int count=0;
		ArrayList<productPriceDTO> ar =new ArrayList<>();
			   try{
			      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
			      DocumentBuilder builder = factory.newDocumentBuilder(); 
			      org.w3c.dom.Document document = builder.parse(url);
			      System.out.println("document: "+document);
			         
			      if(document!=null){
			          NodeList list = document.getElementsByTagName("result");
			          System.out.println("차일드 노드의 엘리먼트 수"+list.item(0).getChildNodes().getLength());
			          //1번for
			          for(int i = 0; i < list.getLength(); i++){
			        //자식들을 찾는다.
			           /*
			            * 여기서 자식인 node가 가리키는 것은 iros.openapi.service.vo.goodPriceVO임.
			            */
			        	//2번for
			            for(org.w3c.dom.Node node = list.item(i).getFirstChild(); node!=null;node = node.getNextSibling()){
			               //여기서 또 자식들을 찾는다.
			               //왜냐면 이것을 열면 goodInspectday, goodId 등이 있으니까!
			            	//마지막for
			            	productPriceDTO p = new productPriceDTO(); //for문안에 productPrice객체 생성 추가
			            	for(org.w3c.dom.Node node2 = node.getFirstChild(); node2!=null;node2 = node2.getNextSibling()){
			        	        
			        	 
			        	        if(node2.getNodeName().equals("goodInspectDay")){
			        	        	p.setGoodInspectDay(node2.getTextContent());
			        	        	System.out.println("InspectDay : "+node2.getTextContent());
			        	        }
			        	        else if(node2.getNodeName().equals("entpId")){
			        	        	p.setEntpId(Long.parseLong(node2.getTextContent()));
			        	        	System.out.println("entpId : "+node2.getTextContent());
			        	        }
			        	        else if(node2.getNodeName().equals("goodId")){
			        	        	p.setGoodId(Integer.parseInt(node2.getTextContent()));
			        	        	System.out.println("goodId : "+node2.getTextContent());
			        	        }
			        	        else if(node2.getNodeName().equals("goodPrice")){
			        	        	p.setGoodPrice(Integer.parseInt(node2.getTextContent()));
			        	        	System.out.println("goodPrice : "+node2.getTextContent());
			        	        }
			        	        else if(node2.getNodeName().equals("plusoneYn")){
			        	        	p.setPlusoneYn(node2.getTextContent());
			        	        	System.out.println("plusoneYn : "+node2.getTextContent());
			        	        }
			        	        else if(node2.getNodeName().equals("goodDcYn")){
			        	        	p.setGoodDcYn(node2.getTextContent());
			        	        	System.out.println("goodDceYn : "+node2.getTextContent());
			        	        }
			        	        else if(node2.getNodeName().equals("goodDcStartDay")){
			        	        	p.setGoodDcStartDay(node2.getTextContent());
			        	        	System.out.println("goodDcStartDay : "+node2.getTextContent());
			        	        }
			        	        else if(node2.getNodeName().equals("goodDcEndDay")){
			        	        	p.setGoodDcEndDay(node2.getTextContent());
			        	        	System.out.println("goodDcEndDay : "+node2.getTextContent());
			        	        }
			        	        else if(node2.getNodeName().equals("inputDttm")){
			        	        	//(InputDttm)이곳은 그냥 데이터 확인용 DTO에는 안담김 
			        	        	System.out.println("inputDttm : "+node2.getTextContent());
			        	        	count++;
			        	        	System.out.println("count : "+count);
			        	        	System.out.println("----------------------");
			        	        }
			        	        
			                        
			            	}//마지막 for문 끝
			            	ar.add(p); //arrayList<prodcutPriceDTO>에 담음
			            }//2번 for문 끝
			          }//1번 for문 끝
			         }//if문 끝
			  }//try 끝 
			  catch (Exception e){ 
			        e.printStackTrace();
			  }return ar;
	}//전체메서드 끝
	
	//필터에 따른 결과값
		//Salelist 
			public ArrayList<StoreInfoDTO> saleInfoList(PageMaker pm, String entpArea, String entpType,String entpBrand ,String entpName){
				Connection con = DBConnector.getConnect();
				String makeSql = 
						"select * from "
						+ "(select rownum R, T.* from "
						+ "(select c1.codename as a, c1.code as a1, "
						+ "c2.codename as b, c2.code as b2, "
						+ "c3.codename as c, c3.code as c2, "
						+ "s.entpName as d, s.entpid as d2, "
						
						+ "s.plmkAddrBasic, s.xMapCoord, s.yMapCoord "
						+ "from GETSTOREINFOSVC s, getStandardInfoSvc c1, getStandardInfoSvc c2, GETSTANDARDINFOSVC c3 "
						+ "where s.entpAreaCode = c1.code "
						+ "and c1.classcode = 'AR' "
						+ "and s.entpTypeCode = c2.code "
						+ "and c2.classcode = 'BU' "
						+ "and s.entpBrandCode = c3.code "
						+ "and c3.CLASSCODE = 'BC' ";	
				makeSql = makeSql + "and c1.code = '"+entpArea+"' "; //지역 코드 받기 ex)서울특별시: 020100000
				makeSql = makeSql + "and c2.code = '"+entpType+"' "; //업태 코드 받기 ex)대형마트: LM
				makeSql = makeSql + "and c3.code = '"+entpBrand+"' "; //업체 코드 받기 ex)홈플러스 : HOME
				makeSql = makeSql + "and s.entpName like '%"+entpName+"%' "; //업체 이름 받기 ex)홈플러스 김해점, 여기 들어간 문구면 다 검색
				makeSql = makeSql + "order by 2) T) where R between ? and ?";
				
				
				
				
				String sql=makeSql;
				System.out.println("sql 합친거 확인: "+sql);
				PreparedStatement st = null;
				ResultSet rs = null;
				ArrayList<StoreInfoDTO> ar = new ArrayList<>();
				try {
					st = con.prepareStatement(sql);
					st.setInt(1, pm.getStartRow()); //시작줄
					st.setInt(2, pm.getLastRow()); //끝줄
					rs =st.executeQuery();
					System.out.println("rs값: "+rs);
					while(rs.next()){
						StoreInfoDTO s = new StoreInfoDTO();
						s.setEntpAreaCodeName(rs.getString(2));//판매점 지역 이름 
						s.setEntpAreaCode(rs.getString(3));
						s.setEntpTypeCodeName(rs.getString(4));//업태 이름
						s.setEntpTypeCode(rs.getString(5));
						s.setEntpBrandCodeName(rs.getString(6));//판매점 브랜드 코드 이름
						s.setEntpBrandCode(rs.getString(7));
						s.setEntpName(rs.getString(8));//업체 이름
						s.setEntpId(rs.getInt(9));
					
						s.setPlmkAddrBasic(rs.getString(10)); //지역기본주소명
						s.setxMapCoord(rs.getString(11)); //x좌표
						s.setyMapCoord(rs.getString(12)); //y좌표
						ar.add(s);
						System.out.println("=======db확인saleinfo");
						System.out.println(rs.getString(2));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					DBConnector.disConnect(rs, st, con);
				}return ar;
			}


	//가게 지점명과 코드 (entpname/entpid)
		public ArrayList<GetInfoDTO> StoreList(){
			Connection con =DBConnector.getConnect();
			String sql="select entpid, entpname from GETSTOREINFOSVC";
			PreparedStatement st= null;
			ResultSet rs= null;
			ArrayList<GetInfoDTO> ar = new ArrayList<>();
			try {
				st=con.prepareStatement(sql);
				rs =st.executeQuery();
				while(rs.next()){
					GetInfoDTO g = new GetInfoDTO();
					g.setDoubleCode(rs.getDouble(1));
					g.setCodeName(rs.getString(2));
					ar.add(g);
					 /*System.out.println("=============== DB확인용");
				     System.out.println(rs.getString(2));
				     System.out.println(rs.getInt(1));*/
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBConnector.disConnect(rs, st, con);
			}return ar;
		}
	 
	 //check_info(3개의 테이블과 StandardInfo 조인 관련 메서드)
	  public ArrayList<GetInfoDTO> entpList(String Code, String classCode, String getsomethinginfo){
	   Connection con = DBConnector.getConnect();
	   String makeSql = "select DISTINCT( c1.codename ), ";
	       makeSql = makeSql + "s."+Code;
	       makeSql = makeSql + " from " + getsomethinginfo + " s, getStandardInfoSvc c1 where ";
	       makeSql = makeSql + Code + "= c1.code and c1.classcode =" +"'"+classCode+"' ";
	       makeSql = makeSql + "order by c1.codename";
	       
	   String sql= makeSql;
	   PreparedStatement st = null;
	   ResultSet rs = null;
	   ArrayList<GetInfoDTO> ar = new ArrayList<>();
	   System.out.println("합쳐진 sql 확인용: "+ sql);
	   try {
	    st=con.prepareStatement(sql);
	    rs=st.executeQuery();
	    System.out.println("st: "+st);
	    System.out.println("rs: "+rs);
	    while(rs.next()){
	     GetInfoDTO s = new GetInfoDTO();
	     s.setCodeName(rs.getString(1));
	     s.setCode(rs.getString(2));
	     ar.add(s);
	     System.out.println("=============== DB확인용");
	     System.out.println(rs.getString(2));
	     System.out.println(rs.getString(1));
	    }
	   } catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	   }finally {
	    DBConnector.disConnect(rs, st, con);
	   }return ar;
	  }
}
