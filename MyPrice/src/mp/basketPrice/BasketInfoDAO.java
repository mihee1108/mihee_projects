package mp.basketPrice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.NodeList;

import mp.common.productPriceDTO;
import mp.util.DBConnector;
import mp.util.PageMaker;

public class BasketInfoDAO {
	//final뿌려주기
	//필터에 따른 결과값
	//Salelist
	private int total;
	private int size;
	private int avr;
	
		public ArrayList<BasketInfoDTO> basketFianl(String entpId){
			Connection con = DBConnector.getConnect();
			
			String makeSql = 
					 "select * from GETSTOREINFOSVC "
					 + "where entpId='"+entpId+"' "
					 + "order by entpname";
			String sql=makeSql;
			System.out.println("sql 합친거 확인: "+sql);
			
			PreparedStatement st = null;
			ResultSet rs = null;
			ArrayList<BasketInfoDTO> ar = new ArrayList<>();
			try {
				st = con.prepareStatement(sql);
				rs =st.executeQuery();
				System.out.println("rs값: "+rs);
				
				while(rs.next()){
					BasketInfoDTO s = new BasketInfoDTO();
					s.setEntpId(rs.getLong(2)); //entpId 
					s.setEntpName(rs.getString(3));
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
	
   //code와 entpId를 넣어주면 그것에 알맞는 상품 이름들을 일단 불러오기
	
	public ArrayList<BasketInfoDTO> basketNameList(String code)
	{
		System.out.println("basketNameList의 잘들어옴");
		Connection con = DBConnector.getConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<BasketInfoDTO> ar = new ArrayList<>();
        String _code=code.substring(0, 6);
        String sql = "select pi.GOODID as a, pi.GOODNAME as b "
        		+"from GETSTANDARDINFOSVC si, GETPRODUCTINFOSVC pi "
        		+"where si.CODE=pi.GOODSMLCLSCODE "
        		+"and substr(si.code,1,6)= '";
        		sql = sql + _code +"'";
        try {
       	System.out.println("sql : "+sql);
           st = con.prepareStatement(sql);
           rs = st.executeQuery();
           System.out.println("rs값: "+rs);
           while(rs.next()){
              BasketInfoDTO p = new BasketInfoDTO();
              p.setGoodId(rs.getInt("a"));
              p.setGoodName(rs.getString("b"));
              ar.add(p);
           }
        } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
        }finally{
           DBConnector.disConnect(rs, st, con);
        }
        return ar;
	}
	
   // 13개의 중분류 불러오기 
      public ArrayList<BasketInfoDTO> salePriceList(){
         Connection con = DBConnector.getConnect();
         PreparedStatement st = null;
         ResultSet rs = null;
         ArrayList<BasketInfoDTO> ar = new ArrayList<>();
         System.out.println("생필품할인정보 들어옴");
         String sql = "select si.CODENAME as a, si.CODE as b from GETSTANDARDINFOSVC si "
        		 +"where si.CLASSCODE = 'AL' "
        		 +"and substr(si.CODE, 7, 9) = '000' and not substr(si.CODE, 5, 9)='00000' "
        		 +"order by si.CODENAME";
         try {
        	System.out.println("sql : "+sql);
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            System.out.println("rs값: "+rs);
            while(rs.next()){
               BasketInfoDTO p = new BasketInfoDTO();
               p.setCodeName(rs.getString("a"));
               p.setCode(rs.getString("b"));
               ar.add(p);
            }
         } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }finally{
            DBConnector.disConnect(rs, st, con);
         }
         return ar;
      }
   // 중분류 위에서 가져온거 뿌려주기
      public ArrayList<BasketInfoDTO> salePriceList2(String code){
          Connection con = DBConnector.getConnect();
          PreparedStatement st = null;
          ResultSet rs = null;
          ArrayList<BasketInfoDTO> ar = new ArrayList<>();
          System.out.println("생필품할인정보 들어옴");
          String makeSql ="select CODENAME as a, CODE as b from GETSTANDARDINFOSVC " 
        				+"where substr(code, 1, 6) = (select substr(code, 1, 6) from GETSTANDARDINFOSVC " 
        				+"where ";
        				makeSql = makeSql + "code = '" +code+"')"
        				+"and substr(code, 7, 9) not in ('000') order by CODENAME";
          try {
         	System.out.println("sql : "+makeSql);
             st = con.prepareStatement(makeSql);
             
             rs = st.executeQuery();
             System.out.println("rs값: "+rs);
             while(rs.next()){
                BasketInfoDTO p = new BasketInfoDTO();
                p.setCodeName(rs.getString("a"));
                p.setCode(rs.getString("b"));
                ar.add(p);
             }
          } catch (Exception e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
          }finally{
             DBConnector.disConnect(rs, st, con);
          }
          return ar;
       }
      //중분류에 대한 제품들 이름 다가져오기
      public ArrayList<BasketInfoDTO> salePriceList3(String code){
    	  Connection con = DBConnector.getConnect();
          System.out.println("con"+con);
          PreparedStatement st = null;
          ResultSet rs = null;
          ArrayList<BasketInfoDTO> ar = new ArrayList<>();
    	  
          String makeSql = 
        		  "select getproductInfosvc.goodname as a, getstandardinfosvc.code as b,GETPRODUCTINFOSVC.GOODID from GETSTANDARDINFOSVC " 
  		  		+"join GETPRODUCTINFOSVC on getstandardInfosvc.code = getproductinfosvc.goodsmlclscode "
  		  		+"where getstandardinfosvc.code= ";
  		  		makeSql = makeSql+"'"+code+"'"
  		  		+ "order by getproductInfosvc.goodname"; 
          
  		  	 try {
  	         	System.out.println("sql3 : "+makeSql);
  	             st = con.prepareStatement(makeSql);
  	             rs = st.executeQuery();
  	             System.out.println("rs값: "+rs);
  	             while(rs.next()){
  	                BasketInfoDTO p = new BasketInfoDTO();
  	                p.setGoodName(rs.getString("a"));
  	                p.setCode(rs.getString("b"));
  	                p.setGoodId(rs.getInt("goodId"));
  	                ar.add(p);
  	             }
  	          } catch (Exception e) {
  	             // TODO Auto-generated catch block
  	             e.printStackTrace();
  	          }finally{
  	             DBConnector.disConnect(rs, st, con);
  	          }
  	          return ar;
  	       }
      //             판매점 이름 뿌리기
      public ArrayList<BasketInfoDTO> salePriceList4(String AreaCode, String StoreCode){
         Connection con = DBConnector.getConnect();
          System.out.println("con"+con);
          PreparedStatement st = null;
          ResultSet rs = null;
          ArrayList<BasketInfoDTO> ar = new ArrayList<>();
          String areaCodeMake = AreaCode.substring(0, 4);
          System.out.println("areaCode파싱: "+areaCodeMake);
         
          String makeSql = 
                "select c2.code as a, c2.codename as b, c1.code as c, c1.codename as d, s.entpid as e, s.entpname as f "
            +"from GETSTOREINFOSVC s, GETSTANDARDINFOSVC c1, GETSTANDARDINFOSVC c2 "
            +"where s.ENTPBRANDCODE = c1.code "
            +"and c1.classcode = 'BC' "
            +"and c2.CLASSCODE = 'AR'  "
            +"and substr(c2.code, 5, 9) = '00000' "
            +"and substr(c2.code, 1, 4) = substr(s.ENTPAREACODE, 1, 4) "
            + "and substr(c2.code, 1, 4) = '"+areaCodeMake+"'"
            + "and c1.code = '"+StoreCode+"' "
            + "order by s.entpname";

              try {
                 System.out.println("sql3 : "+makeSql);
                  st = con.prepareStatement(makeSql);
                  rs = st.executeQuery();
                  System.out.println("rs값: "+rs);
                  while(rs.next()){
                     BasketInfoDTO p = new BasketInfoDTO();
                     p.setCode(rs.getString("a"));
                     p.setCodeName(rs.getString("b"));
                     p.setCode2(rs.getString("c"));
                     p.setCodeName_2(rs.getString("d"));
                     p.setEntpId(rs.getInt("e"));
                     p.setEntpName(rs.getString("f"));
                     ar.add(p);
                  }
               } catch (Exception e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }finally{
                  DBConnector.disConnect(rs, st, con);
               }
               return ar;
            }

      
      
   //매장이름들
   public ArrayList<BasketInfoDTO> productInfoList(PageMaker pm){
      Connection con = DBConnector.getConnect();
      System.out.println("con"+con);
      PreparedStatement st = null;
      ResultSet rs = null;
      ArrayList<BasketInfoDTO> ar = new ArrayList<>();
      System.out.println("start"+pm.getStartRow());
      System.out.println("last"+pm.getLastRow());
      
      String sql= "select * from "
            + "(select rownum R, T.* from "
            + "(select entpId, entpName from GETSTOREINFOSVC) T) "
            + "where R between ? and ?";
      
      try {
         st = con.prepareStatement(sql);
         st.setInt(1, pm.getStartRow());
         st.setInt(2, pm.getLastRow());
         rs = st.executeQuery();
         System.out.println("rs값: "+rs);
         
         while(rs.next()){
            BasketInfoDTO p = new BasketInfoDTO();
            p.setEntpId(rs.getInt("entpId"));
            p.setEntpName(rs.getString("entpName"));
            ar.add(p);
            
         }
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally{
         DBConnector.disConnect(rs, st, con);
      }
      return ar;
   }
   
   //장바구니 안의 modal 뿌려주기
   public ArrayList<BasketInfoDTO> ModalList(PageMaker pm){
      Connection con = DBConnector.getConnect();
      PreparedStatement st = null;
      ResultSet rs = null;
      ArrayList<BasketInfoDTO> ar = new ArrayList<>();
      String sql = "select * from "
            + "(select rownum R, T. * from "
            + "(select p.GOODSMLCLSCODE as a, p.GOODNAME as b,c1.codename as c, "
            + "c2.codename as d, c3.code as e, c3.codename as f "
            + "from GETPRODUCTINFOSVC p, GETSTANDARDINFOSVC c1, GETSTANDARDINFOSVC c2, GETSTANDARDINFOSVC c3 "
            + "where c1.classcode='AL' "
            + "and substr(c1.code,5,9) = '00000' "
            + "and substr(p.GOODSMLCLSCODE, 1, 4) = substr(c1.code, 1, 4) "
            + "and c2.classcode='AL' "
            + "and substr(c2.code,7,9) = '000' "
            + "and substr(p.GOODSMLCLSCODE, 1, 6) = substr(c2.code, 1, 6)  "
            + "and c3.classcode='AL' "
            + "and p.GOODSMLCLSCODE = c3.code) T) "
            + "where R between ? and ?";
      
      try {
         st = con.prepareStatement(sql);
         st.setInt(1, pm.getStartRow());
         st.setInt(2, pm.getLastRow());
         rs = st.executeQuery();
         System.out.println("rs값: "+rs);
         
         while(rs.next()){
            BasketInfoDTO p = new BasketInfoDTO();
            p.setGoodSmlclsCode(rs.getString(2));
            p.setGoodName(rs.getString(3));
            p.setCodeName(rs.getString(4));
            p.setCodeName_2(rs.getString(5));
            p.setCode(rs.getString(6));
            p.setCodeName_3(rs.getString(7));
            ar.add(p);
         }
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally{
         DBConnector.disConnect(rs, st, con);
      }
      return ar;
      
   }
   
   //장바구니 가격
   public ArrayList<BasketInfoDTO> basketPriceList(PageMaker pm){
      Connection con = DBConnector.getConnect();
      PreparedStatement st = null;
      ResultSet rs = null;
      ArrayList<BasketInfoDTO> ar = new ArrayList<>();
      String sql= "select * from "
            +"(select rownum R, T.* from " 
            +"(select p.GOODNAME as a, pp.goodprice as b, pp.GOODINSPECTDAY as c "
            +"from GETPRODUCTINFOSVC p, GETPRODUCTPRICEINFOSVC pp "
            +"where p.goodid = pp.goodid "
            +"and pp.GOODINSPECTDAY = '20161118') T) "
            +"where R between ? and ?";
      try {
         st = con.prepareStatement(sql);
         st.setInt(1, pm.getStartRow());
         st.setInt(2, pm.getLastRow());
         rs = st.executeQuery();
         System.out.println("rs값: "+rs);
         
         while(rs.next()){
            BasketInfoDTO p = new BasketInfoDTO();
            p.setGoodName(rs.getString(2));
            p.setGoodPrice(rs.getString(3));
            p.setGoodInspectDay(rs.getString(4));
            ar.add(p);
         }
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally{
         DBConnector.disConnect(rs, st, con);
      }
      return ar;
   }
   //총 글의 갯수
   public int getCount(){
      Connection con = DBConnector.getConnect();
      String sql = "select count(entpId) from GETSTOREINFOSVC";
      PreparedStatement st = null;
      ResultSet rs = null;
      int result = 0;
      
      try {
         st=con.prepareStatement(sql);
         rs=st.executeQuery();
         rs.next();
         result = rs.getInt(1);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         DBConnector.disConnect(rs, st, con);
      }
      return result;
   }
   
   public ArrayList<productPriceDTO> getProdcutPrice(String goodInspectDay, String ChoiceId, int j, String entpId){
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
			          + makeDay + "&" + ChoiceId +"="+ j; 
		System.out.println("url : "+url);
		total=0;
		int price=0;
		long temp_entp_id=0;
		int count = 0;
		ArrayList<productPriceDTO> ar =new ArrayList<>();
			   try{
			      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
			      DocumentBuilder builder = factory.newDocumentBuilder(); 
			      org.w3c.dom.Document document = builder.parse(url);
			      //System.out.println("document: "+document);
			         
			      if(document!=null){
			          NodeList list = document.getElementsByTagName("result");
			          size=list.item(0).getChildNodes().getLength();
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
			        	        	//System.out.println("InspectDay : "+node2.getTextContent());
			        	        }
			        	        else if(node2.getNodeName().equals("entpId")){
			        	        	//이쪽을 바꿔보자.
			        	        	temp_entp_id=Long.parseLong(node2.getTextContent());
			        	        	p.setEntpId(temp_entp_id);
			        	        	//System.out.println("entpId : "+node2.getTextContent());
			        	        }
			        	        else if(node2.getNodeName().equals("goodId")){
			        	        	p.setGoodId(Integer.parseInt(node2.getTextContent()));
			        	        	//System.out.println("goodId : "+node2.getTextContent());
			        	        }
			        	        else if(node2.getNodeName().equals("goodPrice")){
			        	        	price=Integer.parseInt(node2.getTextContent());
			        	        	total=total+price;
			        	        	p.setGoodPrice(price);
			        	        	//System.out.println("goodPrice : "+node2.getTextContent());
			        	        }
			        	        else if(node2.getNodeName().equals("plusoneYn")){
			        	        	p.setPlusoneYn(node2.getTextContent());
			        	        	//System.out.println("plusoneYn : "+node2.getTextContent());
			        	        }
			        	        else if(node2.getNodeName().equals("goodDcYn")){
			        	        	p.setGoodDcYn(node2.getTextContent());
			        	        	//System.out.println("goodDceYn : "+node2.getTextContent());
			        	        }
			        	        else if(node2.getNodeName().equals("goodDcStartDay")){
			        	        	p.setGoodDcStartDay(node2.getTextContent());
			        	        	//System.out.println("goodDcStartDay : "+node2.getTextContent());
			        	        }
			        	        else if(node2.getNodeName().equals("goodDcEndDay")){
			        	        	p.setGoodDcEndDay(node2.getTextContent());
			        	        	//System.out.println("goodDcEndDay : "+node2.getTextContent());
			        	        }
			        	        else if(node2.getNodeName().equals("inputDttm")){
			        	        	//(InputDttm)이곳은 그냥 데이터 확인용 DTO에는 안담김 
			        	        	//System.out.println("inputDttm : "+node2.getTextContent());
			        	        	count++;
			        	        	//System.out.println("count : "+count);
			        	        	//System.out.println("----------------------");
			        	        }
			        	        
			        	            
			            	}//마지막 for문 끝
			            	//System.out.println("entp_id_check "+temp_entp_id+" "+Long.parseLong(entpId));
			            	if(temp_entp_id==Long.parseLong(entpId))
			            	{
			  
			            	    ar.add(p); //arrayList<prodcutPriceDTO>에 담음
			            	}
			            }//2번 for문 끝
			          }//1번 for문 끝
			         }//if문 끝
			  }//try 끝 
			  catch (Exception e){ 
			        e.printStackTrace();
			  }return ar;
	}//전체메서드 끝
   
   public int get_avr()
   {
	   return 2*total/size;
   }
}