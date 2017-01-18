<%@page import="mp.util.DBConnector"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
  pageEncoding="UTF-8"%> 
<%@page import="java.net.URL 
, java.net.URLConnection 
, java.io.InputStream 
, java.io.InputStreamReader 
, java.io.BufferedReader 
, javax.xml.parsers.* 
, org.w3c.dom.* 
" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
<title>Insert title here</title> 
</head> 
<body> 
<%! 
public boolean isTextNode(Node n){ 
   return n.getNodeName().equals("#text"); 
} 
%> 
<% 
// 아래 변수 값 (goodInspectDay / entpId)을 각각 변경하고 저장 => 실행 => console 창에 insert count 나옴.
// 단, sql 에러 결과는 확인할 수 없으므로 사전에 중복데이터 있는지 필히 확인해야 함. (수행안되는 이유 1순위 : 중복데이터)
// GETPRODUCTPRICEINFOSVC 테이블에만 data insert 하는 프로그램
// 수행 전 확인 내용 : 1. 연결된 DB정보(DBconnection 내용 확인) / 2. 인자값 2개의 기존데이터 중복 여부
//String goodInspectDay = "20161125";
//String entpId = "10";
int[] goodInspectDay = {20161014 };
int[] entpId = {1, 
		10, 
		100, 
		103, 
		104, 
		105, 
		106, 
		107, 
		109, 
		11, 
		110, 
		111, 
		112, 
		113, 
		114, 
		115, 
		116, 
		117, 
		118, 
		119, 
		12, 
		120, 
		121, 
		122, 
		123, 
		124, 
		126, 
		127, 
		128, 
		129, 
		13, 
		130, 
		131, 
		132, 
		133, 
		134, 
		135, 
		136, 
		138, 
		139, 
		140, 
		141, 
		143, 
		145, 
		147, 
		148, 
		149, 
		15, 
		150, 
		151, 
		152, 
		153, 
		154, 
		155, 
		156, 
		157, 
		158, 
		159, 
		16, 
		160, 
		161, 
		162, 
		163, 
		164, 
		165, 
		166, 
		167, 
		168, 
		169, 
		17, 
		170, 
		171, 
		172, 
		173, 
		174, 
		175, 
		176, 
		18, 
		180, 
		182, 
		19, 
		198, 
		2, 
		20, 
		21, 
		217, 
		22, 
		23, 
		237, 
		239, 
		24, 
		240, 
		241, 
		242, 
		243, 
		244, 
		245, 
		246, 
		247, 
		248, 
		249, 
		25, 
		250, 
		252, 
		253, 
		254, 
		255, 
		256, 
		257, 
		258, 
		259, 
		26, 
		260, 
		261, 
		263, 
		264, 
		265, 
		266, 
		267, 
		268, 
		27, 
		270, 
		271, 
		277, 
		29, 
		297, 
		3, 
		30, 
		31, 
		317, 
		32, 
		33, 
		337, 
		34, 
		35, 
		357, 
		358, 
		359, 
		360, 
		361, 
		362, 
		363, 
		364, 
		365, 
		366, 
		367, 
		368, 
		369, 
		37, 
		370, 
		371, 
		372, 
		373, 
		374, 
		375, 
		376, 
		377, 
		378, 
		379, 
		380, 
		381, 
		382, 
		383, 
		384, 
		385, 
		386, 
		387, 
		388, 
		389, 
		390, 
		391, 
		392, 
		393, 
		394, 
		395, 
		396, 
		397, 
		398, 
		4, 
		40, 
		400, 
		401, 
		402, 
		403, 
		405, 
		406, 
		407, 
		408, 
		409, 
		41, 
		410, 
		411, 
		412, 
		413, 
		414, 
		415, 
		416, 
		417, 
		418, 
		42, 
		43, 
		44, 
		45, 
		46, 
		47, 
		49, 
		5, 
		50, 
		51, 
		52, 
		53, 
		54, 
		55, 
		56, 
		567, 
		57, 
		58, 
		6, 
		60, 
		600, 
		61, 
		62, 
		621, 
		63, 
		64, 
		640, 
		641, 
		642, 
		644, 
		65, 
		66, 
		660, 
		67, 
		68, 
		681, 
		682, 
		683, 
		684, 
		685, 
		686, 
		687, 
		688, 
		689, 
		69, 
		690, 
		691, 
		692, 
		693, 
		694, 
		695, 
		696, 
		697, 
		698, 
		699, 
		7, 
		70, 
		700, 
		702, 
		703, 
		704, 
		705, 
		706, 
		707, 
		708, 
		709, 
		71, 
		710, 
		711, 
		712, 
		713, 
		714, 
		715, 
		716, 
		717, 
		718, 
		719, 
		72, 
		720, 
		721, 
		722, 
		723, 
		724, 
		725, 
		726, 
		727, 
		728, 
		729, 
		73, 
		730, 
		731, 
		732, 
		733, 
		734, 
		735, 
		736, 
		737, 
		738, 
		739, 
		74, 
		740, 
		741, 
		742, 
		743, 
		744, 
		745, 
		746, 
		747, 
		748, 
		749, 
		750, 
		751, 
		752, 
		753, 
		754, 
		755, 
		756, 
		757, 
		758, 
		759, 
		76, 
		760, 
		761, 
		762, 
		763, 
		764, 
		765, 
		766, 
		768, 
		769, 
		77, 
		770, 
		771, 
		772, 
		773, 
		774, 
		775, 
		776, 
		777, 
		778, 
		779, 
		78, 
		780, 
		781, 
		782, 
		783, 
		784, 
		785, 
		786, 
		787, 
		79, 
		8, 
		80, 
		81, 
		82, 
		83, 
		84, 
		85, 
		86, 
		88, 
		89, 
		9, 
		90, 
		92, 
		93, 
		94, 
		95, 
		96, 
		97, 
		99
};
for (int b=0; b<goodInspectDay.length; b++) {
	for (int a=0; a<entpId.length; a++) {
	//System.out.println("entpId : " + entpId[a]);

String url = "http://openapi.price.go.kr/openApiImpl/ProductPriceInfoService/getProductPriceInfoSvc.do?serviceKey=rGPbxtOrWH0ExHZaaP7PBV%2FcPPnSkpK6vB0B2xeeqHge0SW%2FgiU8K8U3oc48%2BhLxDeQWGAr1plT%2BKKu1t4PA8w%3D%3D&goodInspectDay=" + goodInspectDay[b] + "&" + "entpId=" + entpId[a]; 
URL url3 = new URL(url); 
URLConnection connection; 
InputStream is; 
InputStreamReader isr; 
BufferedReader br; 
String strXML = new String(); 
try{ 
   connection = url3.openConnection(); 
   is = connection.getInputStream(); 
   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
   DocumentBuilder builder = factory.newDocumentBuilder(); 
   Document document = builder.parse(is); 
   %> 
   <table border="2"> 
   <tr> 
   <th>goodInspectDay</th> 
   <th>entpId</th> 
   <th>goodId</th> 
   <th>goodPrice</th> 
   <th>plusoneYn</th> 
   <th>goodDcYn</th>
   <th>goodDcStartDay</th>
   <th>goodDcEndDay</th>
   <th>inputDttm</th>  
   </tr> 
   <% 
   Element  element = document.getDocumentElement(); 
   NodeList respon = element.getChildNodes(); 
   int cnt = 0;
   for (int i=0; i<respon.getLength(); i++){ 
      Node responNode = respon.item(i); 
      if (isTextNode(responNode)) 
      continue; 
      NodeList result = responNode.getChildNodes();  
      
      for (int k=0; k<result.getLength(); k++ ){
    	  Node resultNode = result.item(k);
    	  if (isTextNode(resultNode)) 
    	  	continue;
    	  NodeList iros = resultNode.getChildNodes();
      
	      %> 
	      <tr> 
	      <% 
	      String sql = "insert into getProductPriceInfoSvc(";
	      String sql_val = " values(";
	      for (int j=0; j<iros.getLength(); j++ ){ 
	         Node irosNode = iros.item(j); 
	         if ( isTextNode(irosNode))  
	         continue; 
	         %> 
	         <td><%= irosNode.getFirstChild().getNodeValue() %></td>  
	         <%	
	         
	         
	         if (irosNode.getNodeName().equals("inputDttm")) {
	        	 sql = sql + irosNode.getNodeName() + ") ";
	        	 sql_val = sql_val + " sysdate )";
	         }else if ( irosNode.getNodeName().equals("entpId") || irosNode.getNodeName().equals("goodId") || irosNode.getNodeName().equals("goodPrice") ) {
	        	 sql = sql + irosNode.getNodeName() + ", ";
	        	 sql_val = sql_val + irosNode.getFirstChild().getNodeValue() + ", ";
	         }else {
	        	 sql = sql + irosNode.getNodeName() + ", ";
	        	 sql_val = sql_val + "'" + irosNode.getFirstChild().getNodeValue() + "', ";
	         }
	        
	      }  
	      //System.out.println(sql + sql_val);	
	      
	      	int error=0;	// 에러 표시 의미 없는 값임.
			PreparedStatement st=null;
			
			Connection con = DBConnector.getConnect();
			con.setAutoCommit(false);	// ORA-12519 에러 방지를 위해 적용
			try {
				st = con.prepareStatement(sql+sql_val);				
				error = st.executeUpdate();
				con.commit();
				con.setAutoCommit(true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBConnector.disConnect(st, con);
			}		
			cnt++;
			//System.out.println(error);
	      
	      %> 
	      </tr> 
      <%}
      
   } 
 System.out.println("goodInspectDay / entpId / insert cnt : " + goodInspectDay[b] + " / " + entpId[a] + " / " +cnt);
   %> 
   </table> 
   <% 
   }catch(Exception e){ 
   e.printStackTrace(); 
}

} // entpId for문
} // goodInspectDay for문
%> 
</body> 
</html> 