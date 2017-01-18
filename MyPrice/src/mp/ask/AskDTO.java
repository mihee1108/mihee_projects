package mp.ask;

import java.sql.Date;

public class AskDTO {
	private int num;
	private String c_cls;
	private String member_yn;
	private String reply_yn;
	private String writer;
	private String email;
	private String title; 
	private String contents;
	private Date reg_date;
	private String reply_writer;
	private String reply_contents;
	private Date reply_date;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getC_cls() {
		return c_cls;
	}
	public void setC_cls(String c_cls) {
		this.c_cls = c_cls;
	}
	public String getMember_yn() {
		return member_yn;
	}
	public void setMember_yn(String member_yn) {
		this.member_yn = member_yn;
	}
	public String getReply_yn() {
		return reply_yn;
	}
	public void setReply_yn(String reply_yn) {
		this.reply_yn = reply_yn;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getReply_writer() {
		return reply_writer;
	}
	public void setReply_writer(String reply_writer) {
		this.reply_writer = reply_writer;
	}
	public String getReply_contents() {
		return reply_contents;
	}
	public void setReply_contents(String reply_contents) {
		this.reply_contents = reply_contents;
	}
	public Date getReply_date() {
		return reply_date;
	}
	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}
	

}
