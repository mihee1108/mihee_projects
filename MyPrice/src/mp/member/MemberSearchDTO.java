package mp.member;

import java.sql.Date;

public class MemberSearchDTO {
	
	private String id;
	private String phone;
	private String gender;
	private String out_yn;
	private String out_date;
	private int store;
	private String inputdttm_s;
	private String inputdttm_e;
	private String out_date_s;
	private String out_date_e;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getOut_yn() {
		return out_yn;
	}
	public void setOut_yn(String out_yn) {
		this.out_yn = out_yn;
	}
	public String getOut_date() {
		return out_date;
	}
	public void setOut_date(String out_date) {
		this.out_date = out_date;
	}
	public int getStore() {
		return store;
	}
	public void setStore(int store) {
		this.store = store;
	}
	public String getInputdttm_s() {
		return inputdttm_s;
	}
	public void setInputdttm_s(String inputdttm_s) {
		this.inputdttm_s = inputdttm_s;
	}
	public String getInputdttm_e() {
		return inputdttm_e;
	}
	public void setInputdttm_e(String inputdttm_e) {
		this.inputdttm_e = inputdttm_e;
	}
	public String getOut_date_s() {
		return out_date_s;
	}
	public void setOut_date_s(String out_date_s) {
		this.out_date_s = out_date_s;
	}
	public String getOut_date_e() {
		return out_date_e;
	}
	public void setOut_date_e(String out_date_e) {
		this.out_date_e = out_date_e;
	}
	
	
}
