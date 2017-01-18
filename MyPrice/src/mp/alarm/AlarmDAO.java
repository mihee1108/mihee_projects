package mp.alarm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mp.member.MemberDTO;
import mp.notice.NoticeDTO;
import mp.util.DBConnector;
import mp.util.PageMaker_hs;

public class AlarmDAO {
	// 검색조건에 따른 가격알람 수
	public int getTotalCnt(AlarmSearchDTO rsDto) {
		int total = 0;
		Connection con = DBConnector.getConnect();
		
		String sql = "select count(id) as total from priceAlarm where id is not null ";
		
		if ( !rsDto.getId().equals("") ) {
			 sql = sql + " and id = '" + rsDto.getId() + "' "; 
		}
		if ( !(rsDto.getGoodid() == 0) ) {
			sql = sql + " and goodid = " + rsDto.getGoodid() + " ";
		}
		if ( !(rsDto.getEntpid() == 0) ) {
			sql = sql + " and entpid = " + rsDto.getEntpid() + " ";
		}
		if ( !rsDto.getAlarm_yn().equals("")) {
			sql = sql + " and alarm_yn = '" + rsDto.getAlarm_yn() + "' ";
		}
				
		sql = sql + "order by alarm_yn desc, reg_date desc";
		System.out.println(sql);
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = con.prepareStatement(sql);
			
			rs = st.executeQuery();
			
			if (rs.next()) {	
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);
		}
		System.out.println("alarm_search total:" + total);
		return total;
	}
	
	
	// 가격알람 리스트
		public ArrayList<AlarmDTO> alarmList(PageMaker_hs pm, AlarmSearchDTO rsDto) {
				
		Connection con = DBConnector.getConnect();

		String sql = "select * "
			+ "from (select rownum as r, a.* "
			+ "from (select ar.num, ar.id, ar.goodId, ar.entpId, ar.alarm_price, ar.alarm_sdate, ar.alarm_edate, ar.alarm_price_ud, ar.alarm_yn, ar.InspectDay, ar.reg_date, p.goodname, s.entpname, decode(ar.alarm_price_ud, 'UP', '이상', 'DW', '이하') as alarm_price_ud_name from priceAlarm ar, getProductInfoSvc p, getStoreInfoSvc s where ar.goodid = p.goodid(+) and ar.entpid = s.entpid(+) ";
		if ( !rsDto.getId().equals("") ) {
			 sql = sql + " and ar.id = '" + rsDto.getId() + "' "; 
		}
		if ( !(rsDto.getGoodid() == 0) ) {
			sql = sql + " and ar.goodid = " + rsDto.getGoodid() + " ";
		}
		if ( !(rsDto.getEntpid() == 0) ) {
			sql = sql + " and ar.entpid = " + rsDto.getEntpid() + " ";
		}
		if ( !rsDto.getAlarm_yn().equals("")) {
			sql = sql + " and ar.alarm_yn = '" + rsDto.getAlarm_yn() + "' ";
		}	
		
		sql = sql + " order by alarm_yn desc, reg_date desc) a) ";
		if ( !(pm == null) ) {
			sql = sql +"where r between ? and ?"; 
		}
	
		System.out.println(sql);
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<AlarmDTO> ar = new ArrayList<>(); 
		try {
			st = con.prepareStatement(sql);
			if ( !(pm == null) ) {
				st.setInt(1, pm.getRowStart());
				st.setInt(2, pm.getRowLast());	
			}
			
			rs = st.executeQuery();
		
			while (rs.next()) {
				AlarmDTO rDto = new AlarmDTO();
				rDto.setNum(rs.getInt("num"));
				rDto.setId(rs.getString("id"));
				rDto.setGoodid(rs.getInt("goodid"));
				rDto.setEntpid(rs.getLong("entpid"));
				rDto.setAlarm_price(rs.getInt("alarm_price"));
				rDto.setAlarm_price_ud(rs.getString("alarm_price_ud"));
				rDto.setAlarm_sdate(rs.getDate("alarm_sdate"));
				rDto.setAlarm_edate(rs.getDate("alarm_edate"));
				rDto.setAlarm_yn(rs.getString("alarm_yn"));
				rDto.setInspectday(rs.getString("inspectday"));
				rDto.setReg_date(rs.getDate("reg_date"));
				rDto.setGoodname(rs.getString("goodname"));
				rDto.setEntpname(rs.getString("entpname"));
				rDto.setAlarm_price_ud_name(rs.getString("alarm_price_ud_name"));
			
				ar.add(rDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);
		}
				
		return ar;
			
	}
		
	// 가격알람 한개 조회
	public AlarmDTO alarmView(int num) {
		Connection con = DBConnector.getConnect();
		PreparedStatement st = null;
		ResultSet rs = null;
		AlarmDTO rDto = null; 
					
		String sql="select ar.num, ar.id, ar.goodId, ar.entpId, ar.alarm_price, ar.alarm_sdate, ar.alarm_edate, ar.alarm_price_ud, ar.alarm_yn, ar.InspectDay, ar.reg_date, p.goodname, s.entpname, decode(ar.alarm_price_ud, 'UP', '이상', 'DW', '이하') as alarm_price_ud_name from pricealarm ar, GETSTOREINFOSVC s, GETPRODUCTINFOSVC p where ar.goodid = p.goodid(+) and ar.entpid = s.entpid(+) and ar.num=?";
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, num);
			rs = st.executeQuery();	// select결과는 ResultSet타입으로 리턴됨. 
		
			if (rs.next()) {	
				rDto = new AlarmDTO(); // 결과는 1줄 or 0줄. 읽을 수 있는 데이터가 있으면
				
				rDto.setNum(rs.getInt("num"));
				rDto.setId(rs.getString("id"));
				rDto.setGoodid(rs.getInt("goodid"));
				rDto.setEntpid(rs.getLong("entpid"));
				rDto.setAlarm_price(rs.getInt("alarm_price"));
				rDto.setAlarm_price_ud(rs.getString("alarm_price_ud"));
				rDto.setAlarm_sdate(rs.getDate("alarm_sdate"));
				rDto.setAlarm_edate(rs.getDate("alarm_edate"));
				rDto.setAlarm_yn(rs.getString("alarm_yn"));
				rDto.setInspectday(rs.getString("inspectday"));
				rDto.setReg_date(rs.getDate("reg_date"));
				rDto.setGoodname(rs.getString("goodname"));
				rDto.setEntpname(rs.getString("entpname"));
				rDto.setAlarm_price_ud_name(rs.getString("alarm_price_ud_name"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);		
		}
		return rDto;
	}
	// 가격알람 삭제
	public int alarmDelete(int num) {
		Connection con = DBConnector.getConnect();
		PreparedStatement st = null;
		int result = 0;
			
		String sql = "delete pricealarm where num = ?";
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, num);
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.disConnect(st, con);	
		}
		return result;
	}
	// 가격알람 update
	public int alarmUpdate(AlarmDTO rDto) {
		int result=0;
		PreparedStatement st=null;
		
		Connection con = DBConnector.getConnect();
		
		String sql = "update pricealarm set alarm_yn=?, alarm_price=?, alarm_price_ud=?, alarm_sdate=?, alarm_edate=? where num=?";

		try {
			st = con.prepareStatement(sql);
			st.setString(1, rDto.getAlarm_yn());
			st.setInt(2, rDto.getAlarm_price());
			st.setString(3, rDto.getAlarm_price_ud());
			st.setDate(4, rDto.getAlarm_sdate());
			st.setDate(5, rDto.getAlarm_edate());
			st.setInt(6, rDto.getNum());
			
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(st, con);
		}
		
		return result;
	}
	// 가격알람 중복 확인 : 아이디, 상품코드, 점포코드, 알람가격, 알람시작일자
	public int alarmUniqueCheck(AlarmDTO rDto) {
		Connection con = DBConnector.getConnect();
		PreparedStatement st = null;
		ResultSet rs = null;
		int counts = 1;		
		String sql="select count(id) from pricealarm where num <> ? and id=? and goodid=? and entpid=? and alarm_price=? and alarm_sdate=?";
		System.out.println(sql);
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, rDto.getNum());
			st.setString(2, rDto.getId());
			st.setInt(3, rDto.getGoodid());
			st.setLong(4, rDto.getEntpid());
			st.setInt(5, rDto.getAlarm_price());
			st.setDate(6, rDto.getAlarm_sdate());
			
			rs = st.executeQuery();	// select결과는 ResultSet타입으로 리턴됨. 
		
			if (rs.next()) {	
				counts = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);		
		}
		System.out.println("uniq count : " + counts);
		return counts;
	}
		
	// 가격알람 상태 업데이트 : 알람상태='Y' & 오늘날짜 기준 종료일자 지난 데이터 => 알람상태='N' 
	public int alarmUpdateYn() {
		int result=0;
		PreparedStatement st=null;
		
		Connection con = DBConnector.getConnect();
		
		String sql = "update pricealarm set alarm_yn='N' where alarm_yn ='Y' and alarm_edate < sysdate-1";

		try {
			st = con.prepareStatement(sql);
			
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(st, con);
		}
		
		return result;
	}

	// 가격알람 insert
	public int alarmInsert(AlarmDTO rDto) {
		int result=0;
		PreparedStatement st=null;
		Connection con = DBConnector.getConnect();
		
		String sql = "insert into pricealarm values(alarm_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
		try {
		
			st = con.prepareStatement(sql);
			st.setString(1, rDto.getId().trim());
			st.setInt(2, rDto.getGoodid());
			st.setLong(3, rDto.getEntpid());
			st.setInt(4, rDto.getAlarm_price());
			st.setDate(5, rDto.getAlarm_sdate());
			st.setDate(6, rDto.getAlarm_edate());
			st.setString(7, rDto.getAlarm_price_ud());
			st.setString(8, rDto.getAlarm_yn());
			st.setString(9, rDto.getInspectday());
			
			result = st.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(st, con);
		}		
		System.out.println("result : " + result);
		return result;
	}
	
	// 가격알람 단체메일 대상자리스트 추출
		public ArrayList<AlarmDTO> alarmEmailList() {
			Connection con = DBConnector.getConnect();
			PreparedStatement st = null;
			ResultSet rs = null;
			ArrayList<AlarmDTO> ar = new ArrayList<>(); 

			String sql="select r.id, r.goodid, p.goodname, r.entpid, s.entpname, r.alarm_price, "
					+"r.alarm_price_ud, decode(r.alarm_price_ud, 'UP', '이상', 'DW', '이하') as alarm_price_ud_name, "
					+"r.alarm_sdate, r.alarm_edate, r.reg_date, m.email " 
					+"from pricealarm r, memberinfo m, GETPRODUCTINFOSVC p, GETSTOREINFOSVC s "
					+"where r.alarm_yn = 'Y' "
					+"and sysdate between r.alarm_sdate and r.alarm_edate+1 "
					+"and r.id = m.id "
					+"and m.email_yn = 'Y' "
					+"and r.goodid = p.goodid "
					+"and r.entpid = s.entpid";
			System.out.println(sql);
			try {
				st = con.prepareStatement(sql);
				rs = st.executeQuery();	// select결과는 ResultSet타입으로 리턴됨. 
			
				while (rs.next()) {
					AlarmDTO rDto = new AlarmDTO();
					rDto.setId(rs.getString("id"));
					rDto.setGoodid(rs.getInt("goodid"));
					rDto.setEntpid(rs.getLong("entpid"));
					rDto.setAlarm_price(rs.getInt("alarm_price"));
					rDto.setAlarm_price_ud(rs.getString("alarm_price_ud"));
					rDto.setAlarm_sdate(rs.getDate("alarm_sdate"));
					rDto.setAlarm_edate(rs.getDate("alarm_edate"));
					rDto.setReg_date(rs.getDate("reg_date"));
					rDto.setGoodname(rs.getString("goodname"));
					rDto.setEntpname(rs.getString("entpname"));
					rDto.setAlarm_price_ud_name(rs.getString("alarm_price_ud_name"));
					rDto.setId_email(rs.getString("email"));
				
					ar.add(rDto);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBConnector.disConnect(rs, st, con);		
			}
			return ar;
		}
	
}
