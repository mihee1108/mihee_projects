package mp.basketPrice;

public class BasketInfoDTO {
	//���߿� �Ⱓ �߰��ϱ�,�����߰��ϱ� �׷��� 3������ �� ���� ����
	private long entpId;  		//�Ǹ��� ���̵�
	private String entpName;	//�Ǹ���
    private int goodId;  		//��ǰ ���̵�	
    private String goodPrice;  	//��ǰ ����
    private int goodavrPrice; //��ǰ ��հ�
    private String goodName;	//��ǰ�� �̸�    
    private int productEntpCode;//������ü�ڵ�
    private String goodSmlclsCode;	//��ǰ ��ȣ
    private	double xMapCoord;  //x���� ��ǥ         
	private	double yMapCoord;  //y���� ��ǥ
    /*
    UT:��ǰ ���� ���� �ڵ�, ��ǰ �뷮 ���� �ڵ�
    AL:��ǰ �Һз� �ڵ�, �Һз��ڵ���� �Ļ�,��,�������.... �ڼ��� ��ǰ �̸��� ���� ��ǰ���̵�
    BU:��ǰ ���� �ڵ�
    AR:��ü ���� �ڵ�, ���� �� �ڵ�
    DP:��ȭ�� ,LM:������Ʈ,MF:������,SM:���۸���,TR:�������
       �Ʒ����� �ڵ�� �ڵ���ӿ��� �����.
    */
    private String code;		//������ ����	
    private String code2;
    private String codeName;	//��з�
    private String codeName_2;	//�ߺз�
    private String codeName_3;	//�Һз�
    private String plmkAddrBasic; //���ּ�
    private String entptypeCode; //�Ǹ��� �ڵ�(�̰Ŷ� ���� �ڵ忡�� ����)
    private String plusoneYn;  //���÷����� ����
    private String goodDcYn;  //��ǰ ���� ����
    private String  goodDcStartDay;  //��ǰ ���� ������
    private String  goodDcEndDay;  //��ǰ ���� ������
    private int discountPrice;  //���� ����(���� �߰��ؾ� ��)
    private String goodInspectDay;  //��ǰ ���� ��
    
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
