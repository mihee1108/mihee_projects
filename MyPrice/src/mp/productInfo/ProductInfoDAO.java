package mp.productInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mp.util.DBConnector;
import mp.util.PageMaker;

public class ProductInfoDAO {
   
   // 13개의 중분류 불러오기 
      public ArrayList<ProductInfoDTO> salePriceList(){
         Connection con = DBConnector.getConnect();
         PreparedStatement st = null;
         ResultSet rs = null;
         ArrayList<ProductInfoDTO> ar = new ArrayList<>();
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
               ProductInfoDTO p = new ProductInfoDTO();
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
      public ArrayList<ProductInfoDTO> salePriceList2(String code){
          Connection con = DBConnector.getConnect();
          PreparedStatement st = null;
          ResultSet rs = null;
          ArrayList<ProductInfoDTO> ar = new ArrayList<>();
          //처음 선택하세요. 추가..
          ProductInfoDTO _t = new ProductInfoDTO();
          
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
                ProductInfoDTO p = new ProductInfoDTO();
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
      public ArrayList<ProductInfoDTO> salePriceList3(String code){
    	  Connection con = DBConnector.getConnect();
          System.out.println("con"+con);
          PreparedStatement st = null;
          ResultSet rs = null;
          ArrayList<ProductInfoDTO> ar = new ArrayList<>();
          
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
  	                ProductInfoDTO p = new ProductInfoDTO();
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
      public ArrayList<ProductInfoDTO> salePriceList4(String AreaCode, String StoreCode){
         Connection con = DBConnector.getConnect();
          System.out.println("con"+con);
          PreparedStatement st = null;
          ResultSet rs = null;
          ArrayList<ProductInfoDTO> ar = new ArrayList<>();
          System.out.println("areacode : "+AreaCode);
          
          //이부분 건드렸습니다.
          if(AreaCode.length()<5)
        	  AreaCode="000000";
          
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
                     ProductInfoDTO p = new ProductInfoDTO();
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
   public ArrayList<ProductInfoDTO> productInfoList(PageMaker pm){
      Connection con = DBConnector.getConnect();
      System.out.println("con"+con);
      PreparedStatement st = null;
      ResultSet rs = null;
      ArrayList<ProductInfoDTO> ar = new ArrayList<>();
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
            ProductInfoDTO p = new ProductInfoDTO();
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
   public ArrayList<ProductInfoDTO> ModalList(PageMaker pm){
      Connection con = DBConnector.getConnect();
      PreparedStatement st = null;
      ResultSet rs = null;
      ArrayList<ProductInfoDTO> ar = new ArrayList<>();
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
            ProductInfoDTO p = new ProductInfoDTO();
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
   public ArrayList<ProductInfoDTO> basketPriceList(PageMaker pm){
      Connection con = DBConnector.getConnect();
      PreparedStatement st = null;
      ResultSet rs = null;
      ArrayList<ProductInfoDTO> ar = new ArrayList<>();
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
            ProductInfoDTO p = new ProductInfoDTO();
            p.setGoodName(rs.getString(2));
            p.setGoodPrice(rs.getInt(3));
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
   
}