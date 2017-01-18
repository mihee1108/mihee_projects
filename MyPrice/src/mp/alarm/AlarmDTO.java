package mp.alarm;

import java.sql.Date;

public class AlarmDTO {
	
	private int num;
	private String id;
	private int goodid;
	private long entpid;
	private int alarm_price;
	private Date alarm_sdate;
	private Date alarm_edate;
	private String alarm_price_ud;
	private String alarm_yn;
	private String inspectday;
	private Date reg_date;
	private String goodname;
	private String entpname;
	private String alarm_price_ud_name;
	private String id_email;
	private int thisweek_price;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getGoodid() {
		return goodid;
	}
	public void setGoodid(int goodid) {
		this.goodid = goodid;
	}
	public long getEntpid() {
		return entpid;
	}
	public void setEntpid(long entpid) {
		this.entpid = entpid;
	}
	public int getAlarm_price() {
		return alarm_price;
	}
	public void setAlarm_price(int alarm_price) {
		this.alarm_price = alarm_price;
	}
	public Date getAlarm_sdate() {
		return alarm_sdate;
	}
	public void setAlarm_sdate(Date alarm_sdate) {
		this.alarm_sdate = alarm_sdate;
	}
	public Date getAlarm_edate() {
		return alarm_edate;
	}
	public void setAlarm_edate(Date alarm_edate) {
		this.alarm_edate = alarm_edate;
	}
	public String getAlarm_price_ud() {
		return alarm_price_ud;
	}
	public void setAlarm_price_ud(String alarm_price_ud) {
		this.alarm_price_ud = alarm_price_ud;
	}
	public String getAlarm_yn() {
		return alarm_yn;
	}
	public void setAlarm_yn(String alarm_yn) {
		this.alarm_yn = alarm_yn;
	}
	public String getInspectday() {
		return inspectday;
	}
	public void setInspectday(String inspectday) {
		this.inspectday = inspectday;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getGoodname() {
		return goodname;
	}
	public void setGoodname(String goodname) {
		this.goodname = goodname;
	}
	public String getEntpname() {
		return entpname;
	}
	public void setEntpname(String entpname) {
		this.entpname = entpname;
	}
	public String getAlarm_price_ud_name() {
		return alarm_price_ud_name;
	}
	public void setAlarm_price_ud_name(String alarm_price_ud_name) {
		this.alarm_price_ud_name = alarm_price_ud_name;
	}
	public String getId_email() {
		return id_email;
	}
	public void setId_email(String id_email) {
		this.id_email = id_email;
	}
	public int getThisweek_price() {
		return thisweek_price;
	}
	public void setThisweek_price(int thisweek_price) {
		this.thisweek_price = thisweek_price;
	}
	
	
}
