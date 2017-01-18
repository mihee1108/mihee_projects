package mp.common;

public class productPriceDTO {
	  private String goodInspectDay;
	  private long entpId;	// (2016-11-30 : 김혜선) long타입으로 변경
	  private int goodId;
	  private int goodPrice; //plusoneYn 혹은 goodDcYn이 "Y"값일 시 goodPrice는 할인 가격이 됨
	  private String plusoneYn;
	  private String goodDcYn;

	  private String goodDcStartDay; // 할인 안해도 있을수도..
	  private String goodDcEndDay; // 할인 안해도 있을을수도..
	public String getGoodInspectDay() {
		return goodInspectDay;
	}
	public void setGoodInspectDay(String goodInspectDay) {
		this.goodInspectDay = goodInspectDay;
	}
	public long getEntpId() {
		return entpId;
	}
	public void setEntpId(long entpId) {
		this.entpId = entpId;
	}
	public int getGoodId() {
		return goodId;
	}
	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}
	public int getGoodPrice() {
		return goodPrice;
	}
	public void setGoodPrice(int goodPrice) {
		this.goodPrice = goodPrice;
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
	  
	
}
