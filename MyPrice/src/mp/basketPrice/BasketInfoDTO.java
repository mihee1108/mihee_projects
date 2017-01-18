package mp.basketPrice;

public class BasketInfoDTO {
	//나중에 기간 추가하기,사진추가하기 그러면 3곳에서 다 쓸수 있음
	private long entpId;  		//판매점 아이디
	private String entpName;	//판매점
    private int goodId;  		//상품 아이디	
    private String goodPrice;  	//상품 가격
    private int goodavrPrice; //상품 평균가
    private String goodName;	//상품의 이름    
    private int productEntpCode;//제조업체코드
    private String goodSmlclsCode;	//상품 번호
    private	double xMapCoord;  //x지도 좌표         
	private	double yMapCoord;  //y지도 좌표
    /*
    UT:상품 단위 구분 코드, 상품 용량 구분 코드
    AL:상품 소분류 코드, 소분류코드까지 식빵,빵,국수등등.... 자세한 상품 이름은 위에 상품아이디
    BU:상품 업태 코드
    AR:업체 지역 코드, 지역 상세 코드
    DP:백화점 ,LM:대형마트,MF:제조사,SM:슈퍼마켓,TR:전통시장
       아래에서 코드와 코드네임에서 사용함.
    */
    private String code;		//위에서 설명	
    private String code2;
    private String codeName;	//대분류
    private String codeName_2;	//중분류
    private String codeName_3;	//소분류
    private String plmkAddrBasic; //상세주소
    private String entptypeCode; //판매점 코드(이거랑 위에 코드에서 조인)
    private String plusoneYn;  //원플러스원 여부
    private String goodDcYn;  //상품 할인 여부
    private String  goodDcStartDay;  //상품 할인 시작일
    private String  goodDcEndDay;  //상품 할인 종료일
    private int discountPrice;  //할인 가격(직접 추가해야 함)
    private String goodInspectDay;  //상품 조사 일
    
    public void setavrprice(int i)
    {
    	this.goodavrPrice=i;
    }
    
    public int getgoodavrprice()
    {
    	return goodavrPrice;
    }
    
    public String getCode2() {
		return code2;
	}
	public void setCode2(String code2) {
		this.code2 = code2;
	}
	public double getxMapCoord() {
		return xMapCoord;
	}
	public void setxMapCoord(double xMapCoord) {
		this.xMapCoord = xMapCoord;
	}
	public double getyMapCoord() {
		return yMapCoord;
	}
	public void setyMapCoord(double yMapCoord) {
		this.yMapCoord = yMapCoord;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public String getGoodSmlclsCode() {
		return goodSmlclsCode;
	}
	public void setGoodSmlclsCode(String goodSmlclsCode) {
		this.goodSmlclsCode = goodSmlclsCode;
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
	public int getGoodId() {
		return goodId;
	}
	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}
	public String getGoodPrice() {
		return goodPrice;
	}
	public void setGoodPrice(String goodPrice) {
		this.goodPrice = goodPrice;
	}
	public int getProductEntpCode() {
		return productEntpCode;
	}
	public void setProductEntpCode(int productEntpCode) {
		this.productEntpCode = productEntpCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getPlmkAddrBasic() {
		return plmkAddrBasic;
	}
	public void setPlmkAddrBasic(String plmkAddrBasic) {
		this.plmkAddrBasic = plmkAddrBasic;
	}
	public String getEntptypeCode() {
		return entptypeCode;
	}
	public void setEntptypeCode(String entptypeCode) {
		this.entptypeCode = entptypeCode;
	}
	public String getPlusoneYn() {
		return plusoneYn;
	}
	public void setPlusoneYn(String plusoneYn) {
		this.plusoneYn = plusoneYn;
	}
	public String getGoodDcYn() {
		return goodDcYn;
	}
	public void setGoodDcYn(String goodDcYn) {
		this.goodDcYn = goodDcYn;
	}
	public String getGoodDcStartDay() {
		return goodDcStartDay;
	}
	public void setGoodDcStartDay(String goodDcStartDay) {
		this.goodDcStartDay = goodDcStartDay;
	}
	public String getGoodDcEndDay() {
		return goodDcEndDay;
	}
	public void setGoodDcEndDay(String goodDcEndDay) {
		this.goodDcEndDay = goodDcEndDay;
	}
	public int getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}
	public String getGoodInspectDay() {
		return goodInspectDay;
	}
	public void setGoodInspectDay(String goodInspectDay) {
		this.goodInspectDay = goodInspectDay;
	}
	public String getCodeName_2() {
		return codeName_2;
	}
	public void setCodeName_2(String codeName_2) {
		this.codeName_2 = codeName_2;
	}
	public String getCodeName_3() {
		return codeName_3;
	}
	public void setCodeName_3(String codeName_3) {
		this.codeName_3 = codeName_3;
	}
	
    
   
    
    
}
