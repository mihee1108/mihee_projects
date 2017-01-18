package mp.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mp.util.PageMaker_hs;

import mp.member.MemberDTO;
import mp.notice.*;
import mp.util.DBConnector;

public class NoticeDAO {
	// 검색글 총 글 갯 수 리턴
	public int getTotalCnt(String b_cls, String search_txt) {
		int total = 0;
		Connection con = DBConnector.getConnect();
		//System.out.println(b_cls +"/"+ search_txt);
		String sql="";
		if(search_txt.equals("") ){
			sql = "select count(num) as total from noticeBoard where b_cls=?";
		}else {
			sql = "select count(num) as total from noticeBoard where b_cls=? and title || contents like ?";	
		}
		//System.out.println(sql);
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = con.prepareStatement(sql);
			if(search_txt.equals("") ){
				st.setString(1, b_cls);
			}else {
				st.setString(1, b_cls);
				st.setString(2, "%"+search_txt+"%");	
			}
			
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
		//System.out.println("total:" + total);
		return total;
	}
	/*
	//총 글 갯 수 리턴
	public int getTotalCnt(String b_cls) {
		int total = 0;
		Connection con = DBConnector.getConnect();
		String sql = "select count(num) as total from noticeBoard where b_cls=?";
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = con.prepareStatement(sql);
			st.setString(1, b_cls);
			
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
		return total;
	}
	*/
	// 공지글 전체 리스트 조회
	public ArrayList<NoticeDTO> noticeList(PageMaker_hs pm, String b_cls, String search_txt, String index_yn) {
		
		Connection con = DBConnector.getConnect();
		String sql ="";
		if((index_yn.equals("Y"))) {
			System.out.println("DAO index_yn : Y");
			sql = "select * "
					+ "from (select rownum as r, t.* " 
					+ "from (select * from noticeBoard where b_cls='N' and c_cls='N' order by num desc) T) " 
					+ "where r <= 5 "
					+ "order by num desc";
		}else if(b_cls.equals("N") && search_txt.equals("") ) {
			System.out.println("111");
			sql = "select * "
					+ "from (select rownum as r, t.* "
					+ "from (select * from noticeBoard where b_cls='N' and c_cls='P' order by num desc) T) "
					+ "union all "
					+ "select * "
					+ "from (select rownum as r, t.* "
					+ "from (select * from noticeBoard where b_cls='N' and c_cls='N' order by num desc) T) "
					+ "where r between ? and ? "
					+ "order by 4 desc, 1";
		}else if ( !(search_txt.equals("")) ) {
			System.out.println("222");
			sql = "select * "
					+ "from (select rownum as r, t.* "
					+ "from (select * from noticeBoard where b_cls=? and title || contents like ? order by num desc) T) "
					+ "where r between ? and ?";
		}else if((search_txt.equals(""))) {
			System.out.println("333");
			sql = "select * "
					+ "from (select rownum as r, t.* "
					+ "from (select * from noticeBoard where b_cls=? order by num desc) T) "
					+ "where r between ? and ? ";
		}
		
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<NoticeDTO> ar = new ArrayList<>(); 
		try {
			st = con.prepareStatement(sql);
			if((index_yn.equals("Y"))) {
				
			}else if(b_cls.equals("N") && search_txt.equals("") ) {
				st.setInt(1, pm.getRowStart());
				st.setInt(2, pm.getRowLast());
			}else if ( !(search_txt.equals("")) ) {
				st.setString(1, b_cls);
				st.setString(2, "%"+search_txt+"%");
				st.setInt(3, pm.getRowStart());
				st.setInt(4, pm.getRowLast());
			}else if((search_txt.equals(""))) {
				st.setString(1, b_cls);
				st.setInt(2, pm.getRowStart());
				st.setInt(3, pm.getRowLast());
			}
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				NoticeDTO nDto = new NoticeDTO();
				nDto.setNum(rs.getInt("num"));
				nDto.setB_cls(rs.getString("b_cls"));
				nDto.setC_cls(rs.getString("c_cls"));
				nDto.setWriter(rs.getString("writer"));
				nDto.setTitle(rs.getString("title"));
				nDto.setContents(rs.getString("contents"));
				nDto.setCounts(rs.getInt("counts"));
				nDto.setReg_date(rs.getDate("reg_date"));
				
				ar.add(nDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);
		}
		return ar;
	}
	// 글 쓰기
	public int noticeWrite(NoticeDTO nDto) {
		int result=0;
		PreparedStatement st=null;
		ResultSet rs = null;
				
		Connection con = DBConnector.getConnect();
		
		String sql = "insert into noticeBoard values(notice_seq.nextval, ?, ?, ?, ?, ?, 0, sysdate)";
		System.out.println(nDto.getB_cls() +"/"+ nDto.getC_cls() +"/"+ nDto.getWriter() +"/"+ nDto.getTitle() +"/"+ nDto.getContents());
		try {
			st = con.prepareStatement(sql);
			st.setString(1, nDto.getB_cls());
			st.setString(2, nDto.getC_cls());
			st.setString(3, nDto.getWriter());
			st.setString(4, nDto.getTitle());
			st.setString(5, nDto.getContents());
			result = st.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(st, con);
		}		
		return result;
	}

	// 글 한개 조회 for ModForm
	public NoticeDTO noticeView(int num) {
		Connection con = DBConnector.getConnect();
		PreparedStatement st = null;
		ResultSet rs = null;
		NoticeDTO nDto = null; 
					
		String sql="";
		try {
			/*
			// 조회수 1 증가 
			sql = "update noticeBoard set counts = counts+1 where num=?";
			st = con.prepareStatement(sql);
		
			st.setInt(1, num);
			st.executeUpdate();
			st.close();
			*/	
			sql = "select * from noticeBoard where num=?";
			st = con.prepareStatement(sql);
			st.setInt(1, num);
			rs = st.executeQuery();	// select결과는 ResultSet타입으로 리턴됨. 
		
			if (rs.next()) {	
				nDto = new NoticeDTO(); // 결과는 1줄 or 0줄. 읽을 수 있는 데이터가 있으면
				
				nDto.setNum(rs.getInt("num"));
				nDto.setB_cls(rs.getString("b_cls"));
				nDto.setC_cls(rs.getString("c_cls"));
				nDto.setWriter(rs.getString("writer"));
				nDto.setTitle(rs.getString("title"));
				nDto.setContents(rs.getString("contents"));
				nDto.setCounts(rs.getInt("counts"));
				nDto.setReg_date(rs.getDate("reg_date"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);		
		}
		return nDto;
	}
	// 글 삭제
	public int noticeDelete(int num) {
		Connection con = DBConnector.getConnect();
		PreparedStatement st = null;
		int result = 0;
			
		String sql = "delete noticeBoard where num = ?";
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
	// 글 수정
	public int noticeMod(NoticeDTO nDto) {
		Connection con = DBConnector.getConnect();
		PreparedStatement st=null;
		int result = 0;
			
		String sql = "update noticeBoard set c_cls=?, title=?, contents=? where num=?";
		try {
			st = con.prepareStatement(sql);
			st.setString(1, nDto.getC_cls());
			st.setString(2, nDto.getTitle());
			st.setString(3, nDto.getContents());
			st.setInt(4, nDto.getNum());
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(st, con);			
		}
		
		return result;
	}
	// 조회수 1 증가 noticeCounts
	public int noticeCounts(int num) {
		Connection con = DBConnector.getConnect();
		PreparedStatement st=null;
		ResultSet rs = null;
		int result = 0;
		int count = 0;
		System.out.println("counts 글: " + num);	
		String sql = "update noticeBoard set counts = counts+1 where num=?";
	
		try {
			st = con.prepareStatement(sql);	
			st.setInt(1, num);
			st.executeUpdate();
			st.close();
			
			sql = "select counts from noticeBoard where num=?";
			st = con.prepareStatement(sql);
			st.setInt(1, num);
			rs = st.executeQuery();	// select결과는 ResultSet타입으로 리턴됨. 
		
			if (rs.next()) {	
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(st, con);			
		}	
		
		return count;
	}
	

}
