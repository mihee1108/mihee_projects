package mp.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mp.common.GetInfoDTO;
import mp.member.MemberDTO;
import mp.util.DBConnector;
import mp.util.PageMaker_hs;

public class MemberDAO {
	
	// 아이디 중복 확인
	public int memberIDCheck(String id){
		//System.out.println("id : " + id);
		PreparedStatement st=null;
		ResultSet rs = null;
		Connection con = DBConnector.getConnect();
		String sql = "select * from memberInfo where id=?";
		int result = 1;
		
		try {
			st = con.prepareStatement(sql);
			st.setString(1, id.trim());
			rs = st.executeQuery();
			
			if (!(rs.next())) {
				result = 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);
		}
		//System.out.println("result : " + result);
		return result;
	}
	// 회원정보 insert
	public int memberInsert(MemberDTO mDto) {
		int result=0;
		PreparedStatement st=null;
		Connection con = DBConnector.getConnect();
		System.out.println("mDto.getId().trim() : " + mDto.getId().trim());
		String sql = "insert into memberInfo values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'N', null, ?, ?, ?, sysdate)";
		try {
		
			st = con.prepareStatement(sql);
			st.setString(1, mDto.getId().trim());
			st.setString(2, mDto.getPassword().trim());
			st.setString(3, mDto.getPhone_1().trim());
			st.setString(4, mDto.getPhone_2().trim());
			st.setString(5, mDto.getPhone_3().trim());
			st.setString(6, mDto.getGender().trim());
			st.setString(7, mDto.getBirthday().trim());
			st.setString(8, mDto.getEmail().trim());
			st.setString(9, mDto.getEmail_yn().trim());
			st.setString(10, mDto.getSms_yn().trim());
			st.setInt(11, mDto.getStore_1());
			st.setInt(12, mDto.getStore_2());
			st.setInt(13, mDto.getStore_3());
			
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

	// 아이디, 비밀번호 찾기
	public String memberSearch(MemberDTO mDto) {
			PreparedStatement st=null;
			ResultSet rs = null;
			Connection con = DBConnector.getConnect();
			String sql="";
			String result;
			
			sql = "select * from memberInfo where phone_1=? and phone_2=? and phone_3=? and birthday=? and email=? and out_yn='N' and (id is not null or id=?)";
			Boolean searchId = true;
			System.out.println("mDto.getId() : " + mDto.getId());
			if ( !(mDto.getId() == null) ){
				searchId = false;
			}
			
			try {
				st = con.prepareStatement(sql);
				st.setString(1, mDto.getPhone_1().trim());
				st.setString(2, mDto.getPhone_2().trim());
				st.setString(3, mDto.getPhone_3().trim());
				st.setString(4, mDto.getBirthday().trim());
				st.setString(5, mDto.getEmail().trim());
				st.setString(6, mDto.getId().trim());
								
				rs = st.executeQuery();
				
				if (rs.next()) {	
					mDto = new MemberDTO();	// 결과는 1줄 or 0줄. 읽을 수 있는 데이터가 있으면 
					mDto.setId(rs.getString("id"));
					mDto.setPassword(rs.getString("password"));
					System.out.println(searchId);
					if(searchId){
						result = mDto.getId();
					}else {
						result = mDto.getPassword();
					}
				}else {
					result=null;
				}
				
			} catch (Exception e) {
				result=null;
				e.printStackTrace();
			}finally {
				DBConnector.disConnect(rs, st, con);
			}		
			return result;		
		}
	
	// 로그인 체크
	public MemberDTO memberLogin(MemberDTO mDto) {
		PreparedStatement st=null;
		ResultSet rs = null;
		Connection con = DBConnector.getConnect();
			
		String sql = "select mi.id, mi.password, mi.phone_1, mi.phone_2, mi.phone_3, "
			+ "mi.gender, mi.birthday, mi.email, mi.email_yn, mi.sms_yn, "
			+ "mi.out_yn, mi.out_date, mi.store_1, mi.store_2, mi.store_3, "
			+ "mi.inputdttm, s1.entpname as entpname_1, s2.entpname as entpname_2, s3.entpname as entpname_3 "
			+ "from memberInfo mi, GETSTOREINFOSVC s1, GETSTOREINFOSVC s2, GETSTOREINFOSVC s3 "
			+ "where mi.store_1=s1.entpid(+) and mi.store_2=s2.entpid(+) and mi.store_3=s3.entpid(+) and mi.out_yn='N' and mi.id=? and mi.password=?";
		try {
			st = con.prepareStatement(sql);
			st.setString(1, mDto.getId().trim());
			st.setString(2, mDto.getPassword().trim());
			rs = st.executeQuery();
			
			if (rs.next()) {	
				mDto = new MemberDTO();/* 결과는 1줄 or 0줄. 읽을 수 있는 데이터가 있으면 */
				mDto.setId(rs.getString("id"));
				mDto.setPassword(rs.getString("password"));
				mDto.setPhone_1(rs.getString("phone_1"));
				mDto.setPhone_2(rs.getString("phone_2"));
				mDto.setPhone_3(rs.getString("phone_3"));
				mDto.setGender(rs.getString("gender"));
				mDto.setBirthday(rs.getString("birthday"));
				mDto.setEmail(rs.getString("email"));
				mDto.setEmail_yn(rs.getString("email_yn"));
				mDto.setSms_yn(rs.getString("sms_yn"));
				mDto.setOut_yn(rs.getString("out_yn"));
				mDto.setOut_date(rs.getDate("out_date"));
				mDto.setStore_1(rs.getInt("store_1"));
				mDto.setStore_2(rs.getInt("store_2"));
				mDto.setStore_3(rs.getInt("store_3"));
				mDto.setEntpname_1(rs.getString("entpname_1"));
				mDto.setEntpname_2(rs.getString("entpname_2"));
				mDto.setEntpname_3(rs.getString("entpname_3"));
				
			}else {
				mDto=null;
			}
			System.out.println("login id : " + mDto.getId());
		} catch (Exception e) {
			mDto=null;	// 중간에 끊기면 로그인 실패로 리턴
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);
		}		
		return mDto;		
	}
	
	// 회원정보 update
	public int memberUpdate(MemberDTO mDto) {
		int result=0;
		PreparedStatement st=null;
		
		Connection con = DBConnector.getConnect();
		
		String sql = "update memberInfo set password=?, phone_1=?, phone_2=?, phone_3=?, gender=?, birthday=?, email=?, email_yn=?, sms_yn=?, store_1=?, store_2=?, store_3=? where id=?";

		try {
			st = con.prepareStatement(sql);
			st.setString(1, mDto.getPassword());
			st.setString(2, mDto.getPhone_1());
			st.setString(3, mDto.getPhone_2());
			st.setString(4, mDto.getPhone_3());
			st.setString(5, mDto.getGender());
			st.setString(6, mDto.getBirthday());
			st.setString(7, mDto.getEmail());
			st.setString(8, mDto.getEmail_yn());
			st.setString(9, mDto.getSms_yn());
			st.setInt(10, mDto.getStore_1());
			st.setInt(11, mDto.getStore_2());
			st.setInt(12, mDto.getStore_3());
			st.setString(13, mDto.getId());
			
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(st, con);
		}
		
		return result;
	}
	// 회원탈퇴 update
	public int memberOut(MemberDTO mDto) {
		int result=0;
		PreparedStatement st=null;
		Connection con = DBConnector.getConnect();
		System.out.println("mDto.getOut_yn() : " + mDto.getId() + "/" + mDto.getOut_yn());
		String sql = "update memberInfo set out_yn='Y', out_date=sysdate where id=? and out_yn=?";
		try {
			st = con.prepareStatement(sql);
			st.setString(1, mDto.getId());
			st.setString(2, mDto.getOut_yn());
			
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(st, con);
		}
		System.out.println("Out result : " + result);
		return result;
	}
	
	// getTotalCnt 전체 회원 수
	public int getTotalCnt() {
		int total = 0;
		Connection con = DBConnector.getConnect();
		
		String sql = "select count(id) as total from memberInfo order by out_yn, inputdttm desc";

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
		System.out.println("member total:" + total);
		return total;
	}

	// 검색조건에 따른 회원 수
	public int getTotalCnt(MemberSearchDTO msDto) {
		int total = 0;
		Connection con = DBConnector.getConnect();
		
		String sql = "select count(id) as total from memberInfo where id is not null ";
		 
		if ( !(msDto.getId().equals("")) ) {
			 sql = sql + " and id = '" + msDto.getId() + "' "; 
		}
		if ( !(msDto.getOut_yn().equals(""))) {
			sql = sql + " and out_yn = '" + msDto.getOut_yn() + "' ";
		}
		if ( !(msDto.getGender().equals(""))) {
			sql = sql + " and gender = '" + msDto.getGender() + "' ";
		}
		if ( !(msDto.getPhone().equals("")) ) {
			sql = sql + " and phone_1||phone_2||phone_3 like '%" + msDto.getPhone() + "%' ";
		}
		if ( !(msDto.getStore() == 0) ) {
			sql = sql + " and (store_1 = " + msDto.getStore() + " or store_2 = " + msDto.getStore() + " or store_3 = " + msDto.getStore() + ")";
		}
		if ( !(msDto.getInputdttm_s().equals("")) ) {
			sql = sql + " and inputdttm " + " between to_date('" + msDto.getInputdttm_s() + "', 'YYYY-MM-DD') and to_date('" + msDto.getInputdttm_e() +"', 'YYYY-MM-DD')+ 1 ";
		}
		if ( !(msDto.getOut_date_s().equals(""))) {
			sql = sql + " and out_date " + " between to_date('" + msDto.getOut_date_s() + "', 'YYYY-MM-DD') and to_date('" + msDto.getOut_date_e()+"', 'YYYY-MM-DD')+ 1 ";
		}	
		sql = sql + "order by out_yn, inputdttm desc";
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
		System.out.println("member_search total:" + total);
		return total;
	}
	
	
	// 멤버 전체리스트
	public ArrayList<MemberDTO> memberList(PageMaker_hs pm) {
		
		Connection con = DBConnector.getConnect();

		String sql = "select * "
			+ "from (select rownum as r, m.* "
			+ "from (select mi.id, mi.password, mi.phone_1, mi.phone_2, mi.phone_3, "
			+ "mi.gender, mi.birthday, mi.email, mi.email_yn, mi.sms_yn, "
			+ "mi.out_yn, mi.out_date, mi.store_1, mi.store_2, mi.store_3, "
			+ "mi.inputdttm, s1.entpname as entpname_1, s2.entpname as entpname_2, s3.entpname as entpname_3 "
			+ "from memberInfo mi, GETSTOREINFOSVC s1, GETSTOREINFOSVC s2, GETSTOREINFOSVC s3 "
			+ "where mi.store_1=s1.entpid(+) and mi.store_2=s2.entpid(+) and mi.store_3=s3.entpid(+) "			
			+ " order by mi.out_yn, mi.inputdttm desc) M) "
			+ "where r between ? and ?";
		
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> ar = new ArrayList<>(); 
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, pm.getRowStart());
			st.setInt(2, pm.getRowLast());
		
			rs = st.executeQuery();
		
			while (rs.next()) {
				MemberDTO mDto = new MemberDTO();
				mDto.setId(rs.getString("id"));
				mDto.setPassword(rs.getString("password"));
				mDto.setPhone_1(rs.getString("phone_1"));
				mDto.setPhone_2(rs.getString("phone_2"));
				mDto.setPhone_3(rs.getString("phone_3"));
				mDto.setGender(rs.getString("gender"));
				mDto.setBirthday(rs.getString("birthday"));
				mDto.setEmail(rs.getString("email"));
				mDto.setEmail_yn(rs.getString("email_yn"));
				mDto.setSms_yn(rs.getString("sms_yn"));
				mDto.setOut_yn(rs.getString("out_yn"));
				mDto.setOut_date(rs.getDate("out_date"));
				mDto.setStore_1(rs.getInt("store_1"));
				mDto.setStore_2(rs.getInt("store_2"));
				mDto.setStore_3(rs.getInt("store_3"));
				mDto.setInputdttm(rs.getDate("inputdttm"));
				mDto.setEntpname_1(rs.getString("entpname_1"));
				mDto.setEntpname_2(rs.getString("entpname_2"));
				mDto.setEntpname_3(rs.getString("entpname_3"));
				
				ar.add(mDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);
		}
			
		return ar;
		
	}
	
	// 멤버 전체리스트
	public ArrayList<MemberDTO> memberList(PageMaker_hs pm, MemberSearchDTO msDto) {
			
		Connection con = DBConnector.getConnect();

		String sql = "select * "
			+ "from (select rownum as r, m.* "
			+ "from (select mi.id, mi.password, mi.phone_1, mi.phone_2, mi.phone_3, "
			+ "mi.gender, mi.birthday, mi.email, mi.email_yn, mi.sms_yn, "
			+ "mi.out_yn, mi.out_date, mi.store_1, mi.store_2, mi.store_3, "
			+ "mi.inputdttm, s1.entpname as entpname_1, s2.entpname as entpname_2, s3.entpname as entpname_3 "
			+ "from memberInfo mi, GETSTOREINFOSVC s1, GETSTOREINFOSVC s2, GETSTOREINFOSVC s3 "
			+ "where mi.store_1=s1.entpid(+) and mi.store_2=s2.entpid(+) and mi.store_3=s3.entpid(+) ";			
		
		if ( !msDto.getId().equals("") ) {
			 sql = sql + " and mi.id = '" + msDto.getId() + "' "; 
		}
		if ( !msDto.getOut_yn().equals("")) {
			sql = sql + " and mi.out_yn = '" + msDto.getOut_yn() + "' ";
		}
		if ( !msDto.getGender().equals("")) {
			sql = sql + " and mi.gender = '" + msDto.getGender() + "' ";
		}
		if ( !msDto.getPhone().equals("")) {
			sql = sql + " and mi.phone_1||mi.phone_2||mi.phone_3 like '%" + msDto.getPhone() + "%' ";
		}
		if ( !(msDto.getStore() == 0) ) {
			sql = sql + " and (mi.store_1 = " + msDto.getStore() + " or mi.store_2 = " + msDto.getStore() + " or mi.store_3 = " + msDto.getStore() + ")";
		}
		if ( !msDto.getInputdttm_s().equals("")) {
			sql = sql + " and mi.inputdttm " + " between to_date('" + msDto.getInputdttm_s() + "', 'YYYY-MM-DD') and to_date('" + msDto.getInputdttm_e() +"', 'YYYY-MM-DD')+ 1 ";
		}
		if ( !msDto.getOut_date_s().equals("")) {
			sql = sql + " and mi.out_date " + " between to_date('" + msDto.getOut_date_s() + "', 'YYYY-MM-DD') and to_date('" + msDto.getOut_date_e()+"', 'YYYY-MM-DD')+ 1 ";
		}
		
		sql = sql + " order by mi.out_yn, mi.inputdttm desc) M) " 
					+ "where r between ? and ?";
		System.out.println(sql);
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> ar = new ArrayList<>(); 
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, pm.getRowStart());
			st.setInt(2, pm.getRowLast());
		
			rs = st.executeQuery();
		
			while (rs.next()) {
				MemberDTO mDto = new MemberDTO();
				mDto.setId(rs.getString("id"));
				mDto.setPassword(rs.getString("password"));
				mDto.setPhone_1(rs.getString("phone_1"));
				mDto.setPhone_2(rs.getString("phone_2"));
				mDto.setPhone_3(rs.getString("phone_3"));
				mDto.setGender(rs.getString("gender"));
				mDto.setBirthday(rs.getString("birthday"));
				mDto.setEmail(rs.getString("email"));
				mDto.setEmail_yn(rs.getString("email_yn"));
				mDto.setSms_yn(rs.getString("sms_yn"));
				mDto.setOut_yn(rs.getString("out_yn"));
				mDto.setOut_date(rs.getDate("out_date"));
				mDto.setStore_1(rs.getInt("store_1"));
				mDto.setStore_2(rs.getInt("store_2"));
				mDto.setStore_3(rs.getInt("store_3"));
				mDto.setInputdttm(rs.getDate("inputdttm"));
				mDto.setEntpname_1(rs.getString("entpname_1"));
				mDto.setEntpname_2(rs.getString("entpname_2"));
				mDto.setEntpname_3(rs.getString("entpname_3"));
				
				ar.add(mDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);
		}
				
		return ar;
			
	}
	
	// 엑셀파일 저장을 위한 전체리스트 - 페이징 없음
		public ArrayList<MemberDTO> memberList(MemberSearchDTO msDto) {
				
			Connection con = DBConnector.getConnect();

			String sql = "select * "
				+ "from (select rownum as r, m.* "
				+ "from (select mi.id, mi.password, mi.phone_1, mi.phone_2, mi.phone_3, "
							+ "mi.gender, mi.birthday, mi.email, mi.email_yn, mi.sms_yn, "
							+ "mi.out_yn, mi.out_date, mi.store_1, mi.store_2, mi.store_3, "
							+ "mi.inputdttm, s1.entpname as entpname_1, s2.entpname as entpname_2, s3.entpname as entpname_3 "
							+ "from memberInfo mi, GETSTOREINFOSVC s1, GETSTOREINFOSVC s2, GETSTOREINFOSVC s3 "
							+ "where mi.store_1=s1.entpid(+) and mi.store_2=s2.entpid(+) and mi.store_3=s3.entpid(+) ";
			
			if ( !msDto.getId().equals("") ) {
				 sql = sql + " and mi.id = '" + msDto.getId() + "' "; 
			}
			if ( !msDto.getOut_yn().equals("")) {
				sql = sql + " and mi.out_yn = '" + msDto.getOut_yn() + "' ";
			}
			if ( !msDto.getGender().equals("")) {
				sql = sql + " and mi.gender = '" + msDto.getGender() + "' ";
			}
			if ( !msDto.getPhone().equals("")) {
				sql = sql + " and mi.phone_1||mi.phone_2||mi.phone_3 like '%" + msDto.getPhone() + "%' ";
			}
			if ( !(msDto.getStore() == 0) ) {
				sql = sql + " and (mi.store_1 = " + msDto.getStore() + " or mi.store_2 = " + msDto.getStore() + " or mi.store_3 = " + msDto.getStore() + ")";
			}
			if ( !msDto.getInputdttm_s().equals("")) {
				sql = sql + " and mi.inputdttm " + " between to_date('" + msDto.getInputdttm_s() + "', 'YYYY-MM-DD') and to_date('" + msDto.getInputdttm_e() +"', 'YYYY-MM-DD')+ 1 ";
			}
			if ( !msDto.getOut_date_s().equals("")) {
				sql = sql + " and mi.out_date " + " between to_date('" + msDto.getOut_date_s() + "', 'YYYY-MM-DD') and to_date('" + msDto.getOut_date_e()+"', 'YYYY-MM-DD')+ 1 ";
			}
			
			sql = sql + " order by mi.out_yn, mi.inputdttm desc) M) ";
			System.out.println(sql);
			PreparedStatement st = null;
			ResultSet rs = null;
			ArrayList<MemberDTO> ar = new ArrayList<>(); 
			try {
				st = con.prepareStatement(sql);
				
				rs = st.executeQuery();
			
				while (rs.next()) {
					MemberDTO mDto = new MemberDTO();
					mDto.setId(rs.getString("id"));
					mDto.setPassword(rs.getString("password"));
					mDto.setPhone_1(rs.getString("phone_1"));
					mDto.setPhone_2(rs.getString("phone_2"));
					mDto.setPhone_3(rs.getString("phone_3"));
					mDto.setGender(rs.getString("gender"));
					mDto.setBirthday(rs.getString("birthday"));
					mDto.setEmail(rs.getString("email"));
					mDto.setEmail_yn(rs.getString("email_yn"));
					mDto.setSms_yn(rs.getString("sms_yn"));
					mDto.setOut_yn(rs.getString("out_yn"));
					mDto.setOut_date(rs.getDate("out_date"));
					mDto.setStore_1(rs.getInt("store_1"));
					mDto.setStore_2(rs.getInt("store_2"));
					mDto.setStore_3(rs.getInt("store_3"));
					mDto.setInputdttm(rs.getDate("inputdttm"));
					mDto.setEntpname_1(rs.getString("entpname_1"));
					mDto.setEntpname_2(rs.getString("entpname_2"));
					mDto.setEntpname_3(rs.getString("entpname_3"));
					
					
					ar.add(mDto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBConnector.disConnect(rs, st, con);
			}
					
			return ar;
				
	}
	
	// 검색어 기준 점포리스트 조회 (페이징 안함)
	public ArrayList<SearchItemDTO> searchItem(String search_item, String search_txt) {
		
		Connection con = DBConnector.getConnect();
		String sql ="";
		if (search_item.equals("store")) {
			sql = "select entpid, entpname, plmkaddrbasic, plmkaddrdetail from GETSTOREINFOSVC where entpname || plmkaddrbasic || plmkaddrdetail like ? order by 2";	
		}else if (search_item.equals("prod")) {
			sql = "select c1.codename as codename_1, c2.codename as codename_2, c3.codename as codename_3, p.GOODNAME, p.goodid "
					+ "from GETPRODUCTINFOSVC p, GETSTANDARDINFOSVC c1, GETSTANDARDINFOSVC c2, GETSTANDARDINFOSVC c3 "
					+ "where c1.classcode='AL' "
					+ "and substr(c1.code,5,9) = '00000'  "
					+ "and substr(p.GOODSMLCLSCODE, 1, 4) = substr(c1.code, 1, 4) "
					+ "and c2.classcode='AL' "
					+ "and substr(c2.code,7,9) = '000'  "
					+ "and substr(p.GOODSMLCLSCODE, 1, 6) = substr(c2.code, 1, 6) "
					+ "and c3.classcode='AL' "
					+ "and p.GOODSMLCLSCODE = c3.code "
					+ "and c1.codename||c2.codename||c3.codename||p.GOODNAME like ? "
					+ "order by 1, 2, 3, 4";
		}
		
			
		System.out.println(sql);
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<SearchItemDTO> ar = new ArrayList<>(); 
		try {
			st = con.prepareStatement(sql);
			st.setString(1,"%" + search_txt.trim() + "%");
			
			rs = st.executeQuery();
			if (search_item.equals("store")) {
				while (rs.next()) {
					SearchItemDTO sDto = new SearchItemDTO();
					sDto.setEntpid(rs.getInt("entpid"));
					sDto.setEntpname(rs.getString("entpname"));
					sDto.setPlmkaddrbasic(rs.getString("plmkaddrbasic"));
					sDto.setPlmkaddrdetail(rs.getString("plmkaddrdetail"));				
					
					ar.add(sDto);
				}
			}else if (search_item.equals("prod")) {
				while (rs.next()) {
					SearchItemDTO sDto = new SearchItemDTO();
					sDto.setGoodid(rs.getInt("goodid"));
					sDto.setGoodname(rs.getString("goodname"));
					sDto.setCodename_1(rs.getString("codename_1"));
					sDto.setCodename_2(rs.getString("codename_2"));
					sDto.setCodename_3(rs.getString("codename_3"));				
					
					ar.add(sDto);
				}
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
