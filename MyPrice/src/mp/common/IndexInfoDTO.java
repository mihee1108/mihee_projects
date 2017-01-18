package mp.common;

public class IndexInfoDTO {
	
	private String smlclscode;
	private String smlclscodename;
	private String goodid;
	private String goodname;
	private String gooddan;
	private int thisweek_price;
	private int before1w_price;
	private int before1m_price;
	private int before3m_price;
	private double price_gap;
	public String getSmlclscode() {
		return smlclscode;
	}
	public void setSmlclscode(String smlclscode) {
		this.smlclscode = smlclscode;
	}
	public String getSmlclscodename() {
		return smlclscodename;
	}
	public void setSmlclscodename(String smlclscodename) {
		this.smlclscodename = smlclscodename;
	}
	public String getGoodid() {
		return goodid;
	}
	public void setGoodid(String goodid) {
		this.goodid = goodid;
	}
	public String getGoodname() {
		return goodname;
	}
	public void setGoodname(String goodname) {
		this.goodname = goodname;
	}
	public String getGooddan() {
		return gooddan;
	}
	public void setGooddan(String gooddan) {
		this.gooddan = gooddan;
	}
	public int getThisweek_price() {
		return thisweek_price;
	}
	public void setThisweek_price(int thisweek_price) {
		this.thisweek_price = thisweek_price;
	}
	public int getBefore1w_price() {
		return before1w_price;
	}
	public void setBefore1w_price(int before1w_price) {
		this.before1w_price = before1w_price;
	}
	public int getBefore1m_price() {
		return before1m_price;
	}
	public void setBefore1m_price(int before1m_price) {
		this.before1m_price = before1m_price;
	}
	public int getBefore3m_price() {
		return before3m_price;
	}
	public void setBefore3m_price(int before3m_price) {
		this.before3m_price = before3m_price;
	}
	public double getPrice_gap() {
		return price_gap;
	}
	public void setPrice_gap(double price_gap) {
		this.price_gap = price_gap;
	}
	
				
}
