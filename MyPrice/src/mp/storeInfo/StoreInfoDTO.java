package mp.storeInfo;

public class StoreInfoDTO {
	private	long entpId; //업체 아이디
	private	String entpName;  // 업체명          
	private	String entpTypeCode;  //업체 업태 코드 => SM:슈퍼마켓 TR:전통시장 LM:대형마트 DP:백화점 CS:편의점
	private	String entpTypeCodeName;  //업체 업태 코드 한글명
	
	private	String entpAreaCode;  //업체 지역 코드     
	private	String entpAreaCodeName; //업체 지역 코드 한글명
	
	private	String areaDetailCode;  //지역 상세 코드   
	private	String areaDetailCodeName; //지역 상세 코드 한글명
	
	private String entpBrandCode; //업체 코드
	private String entpBrandCodeName; //업체 코드 한글명
	
	private	String entpTelno;  //전화번호         
	private	String postNo;  //우편번호            
	private	String plmkAddrBasic;  //지역 기본 주소명    
	private	String plmkAddrDetail; //지번 상세 주소명    
	private	String roadAddrBasic;  //도로 기본 주소명    
	private	String roadAddrDetail; //도로 상세 주소명    
	private	String xMapCoord;  //x지도 좌표         
	private	String yMapCoord;  //y지도 좌표
	//2016.12.01 추가 
	private String goodId;
	private String goodName;
	
	
	
	
	
	
	public String getGoodId() {
		return goodId;
	}
	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public String getEntpTypeCodeName() {
		return entpTypeCodeName;
	}
	public void setEntpTypeCodeName(String entpTypeCodeName) {
		this.entpTypeCodeName = entpTypeCodeName;
	}
	public String getEntpAreaCodeName() {
		return entpAreaCodeName;
	}
	public void setEntpAreaCodeName(String entpAreaCodeName) {
		this.entpAreaCodeName = entpAreaCodeName;
	}
	public String getAreaDetailCodeName() {
		return areaDetailCodeName;
	}
	public void setAreaDetailCodeName(String areaDetailCodeName) {
		this.areaDetailCodeName = areaDetailCodeName;
	}
	public String getEntpBrandCode() {
		return entpBrandCode;
	}
	public void setEntpBrandCode(String entpBrandCode) {
		this.entpBrandCode = entpBrandCode;
	}
	public String getEntpBrandCodeName() {
		return entpBrandCodeName;
	}
	public void setEntpBrandCodeName(String entpBrandCodeName) {
		this.entpBrandCodeName = entpBrandCodeName;
	}
	public long getEntpId() {
		return entpId;
	}
	public void setEntpId(long entpId) {
		this.entpId = entpId;
	}
	public String getEntpName() {
		return entpName;
	}
	public void setEntpName(String entpName) {
		this.entpName = entpName;
	}
	public String getEntpTypeCode() {
		return entpTypeCode;
	}
	public void setEntpTypeCode(String entpTypeCode) {
		this.entpTypeCode = entpTypeCode;
	}
	public String getEntpAreaCode() {
		return entpAreaCode;
	}
	public void setEntpAreaCode(String entpAreaCode) {
		this.entpAreaCode = entpAreaCode;
	}
	public String getAreaDetailCode() {
		return areaDetailCode;
	}
	public void setAreaDetailCode(String areaDetailCode) {
		this.areaDetailCode = areaDetailCode;
	}
	public String getEntpTelno() {
		return entpTelno;
	}
	public void setEntpTelno(String entpTelno) {
		this.entpTelno = entpTelno;
	}
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	public String getPlmkAddrBasic() {
		return plmkAddrBasic;
	}
	public void setPlmkAddrBasic(String plmkAddrBasic) {
		this.plmkAddrBasic = plmkAddrBasic;
	}
	public String getPlmkAddrDetail() {
		return plmkAddrDetail;
	}
	public void setPlmkAddrDetail(String plmkAddrDetail) {
		this.plmkAddrDetail = plmkAddrDetail;
	}
	public String getRoadAddrBasic() {
		return roadAddrBasic;
	}
	public void setRoadAddrBasic(String roadAddrBasic) {
		this.roadAddrBasic = roadAddrBasic;
	}
	public String getRoadAddrDetail() {
		return roadAddrDetail;
	}
	public void setRoadAddrDetail(String roadAddrDetail) {
		this.roadAddrDetail = roadAddrDetail;
	}
	public String getxMapCoord() {
		return xMapCoord;
	}
	public void setxMapCoord(String xMapCoord) {
		this.xMapCoord = xMapCoord;
	}
	public  String getyMapCoord() {
		return yMapCoord;
	}
	public void setyMapCoord( String yMapCoord) {
		this.yMapCoord = yMapCoord;
	}   
	
	
}

