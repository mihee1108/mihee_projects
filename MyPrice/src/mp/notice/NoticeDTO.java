package mp.notice;

import java.sql.Date;

public class NoticeDTO {
	
	private int num;
	private String b_cls;
	private String c_cls;
	private String title;
	private String writer; 
	private String contents;
	private int counts;
	private Date reg_date;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getB_cls() {
		return b_cls;
	}
	public void setB_cls(String b_cls) {
		this.b_cls = b_cls;
	}
	public String getC_cls() {
		return c_cls;
	}
	public void setC_cls(String c_cls) {
		this.c_cls = c_cls;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
