package mp.ask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mp.util.PageMaker_hs;

import mp.member.MemberDTO;
import mp.notice.*;
import mp.util.DBConnector;

public class AskDAO {
	// 검색글 총 글 갯 수 리턴
	public int getTotalCnt(String writer, String search_txt) {
		int total = 0;
		Connection con = DBConnector.getConnect();
		
		String sql = "select count(num) as total from askBoard where writer like ? and title || contents || reply_contents like ?";	
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = con.prepareStatement(sql);
			System.out.println(sql);
			if(writer.equals("")) {
				st.setString(1, "%%");
			}else {
				st.setString(1, writer);
			}
			st.setString(2, "%"+search_txt+"%");
			
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
		System.out.println("total:" + total);
		return total;
	}
	// 글 쓰기
	public int askWrite(AskDTO aDto) {
		int result=0;
		PreparedStatement st=null;
		ResultSet rs = null;
				
		Connection con = DBConnector.getConnect();
		
		String sql = "insert into askBoard values(ask_seq.nextval, ?, ?, 'N', ?, ?, ?, ?, sysdate, null, null, null)";
		System.out.println(aDto.getC_cls() +"/"+ aDto.getWriter() +"/"+ aDto.getTitle() +"/"+ aDto.getContents());
		try {
			st = con.prepareStatement(sql);
			st.setString(1, aDto.getC_cls());
			st.setString(2, aDto.getMember_yn());
			st.setString(3, aDto.getWriter());
			st.setString(4, aDto.getEmail());
			st.setString(5, aDto.getTitle());
			st.setString(6, aDto.getContents());
			
			result = st.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(st, con);
		}		
		return result;
	}

	// 문의글 전체 리스트 조회
	public ArrayList<AskDTO> askList(PageMaker_hs pm, String writer, String search_txt) {
		
		Connection con = DBConnector.getConnect();
		String sql = "select * "
					+ "from (select rownum as r, t.* "
					+ "from (select * from askBoard where writer like ? and title||contents||reply_contents like ? order by reply_yn, num desc) T) "
					+ "where r between ? and ? ";
				
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<AskDTO> ar = new ArrayList<>(); 
		try {
			st = con.prepareStatement(sql);
			System.out.println("askList" + sql );
			if(writer.equals("")) {
				st.setString(1, "%%");
			}else {
				st.setString(1, writer);
			}
			st.setString(2, "%"+search_txt+"%");
			st.setInt(3, pm.getRowStart());
			st.setInt(4, pm.getRowLast());
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				AskDTO aDto = new AskDTO();
				aDto.setNum(rs.getInt("num"));
				aDto.setC_cls(rs.getString("c_cls"));
				aDto.setMember_yn(rs.getString("member_yn"));
				aDto.setReply_yn(rs.getString("reply_yn"));
				aDto.setWriter(rs.getString("writer"));
				aDto.setEmail(rs.getString("email"));
				aDto.setTitle(rs.getString("title"));
				aDto.setContents(rs.getString("contents"));
				aDto.setReg_date(rs.getDate("reg_date"));
				aDto.setReply_writer(rs.getString("reply_writer"));
				aDto.setReply_contents(rs.getString("reply_contents"));
				aDto.setReply_date(rs.getDate("reply_date"));
				
				ar.add(aDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);
		}
		return ar;
	}
	// 문의 글 한개 조회 for ReplyForm, ModForm
	public AskDTO askView(int num) {
		Connection con = DBConnector.getConnect();
		PreparedStatement st = null;
		ResultSet rs = null;
		AskDTO aDto = null; 
					
		String sql="";
		try {
			sql = "select num, c_cls, member_yn, reply_yn, writer, email, title, contents, reg_date, nvl(reply_writer, '') as reply_writer, nvl(reply_contents, '') as reply_contents, nvl(reply_date, '') as reply_date from askBoard where num=?";
			st = con.prepareStatement(sql);
			st.setInt(1, num);
			rs = st.executeQuery();	// select결과는 ResultSet타입으로 리턴됨. 
		
			if (rs.next()) {	
				aDto = new AskDTO(); // 결과는 1줄 or 0줄. 읽을 수 있는 데이터가 있으면
				
				aDto.setNum(rs.getInt("num"));
				aDto.setC_cls(rs.getString("c_cls"));
				aDto.setMember_yn(rs.getString("member_yn"));
				aDto.setReply_yn(rs.getString("reply_yn"));
				aDto.setWriter(rs.getString("writer"));
				aDto.setEmail(rs.getString("email"));
				aDto.setTitle(rs.getString("title"));
				aDto.setContents(rs.getString("contents"));
				aDto.setReg_date(rs.getDate("reg_date"));
				aDto.setReply_writer(rs.getString("reply_writer"));
				aDto.setReply_contents(rs.getString("reply_contents"));
				aDto.setReply_date(rs.getDate("reply_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);		
		}
		return aDto;
	}
	
	// 문의 글 메일답변
	public int askReply(AskDTO aDto) {
		Connection con = DBConnector.getConnect();
		PreparedStatement st=null;
		int result = 0;
		String sql = "update askBoard set reply_yn=?, reply_writer=?, reply_contents=?, reply_date=sysdate where num=?";	
		
		try {
			st = con.prepareStatement(sql);
				
			st.setString(1, aDto.getReply_yn());
			st.setString(2, aDto.getReply_writer());
			st.setString(3, aDto.getReply_contents());
			st.setInt(4, aDto.getNum());
			
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(st, con);			
		}
		
		return result;
	}
	
	// 문의 글 단순 수정
	public int askMod(AskDTO aDto) {
		Connection con = DBConnector.getConnect();
		PreparedStatement st=null;
		int result = 0;
		String	sql = "update askBoard set reply_yn=?, email=?, reply_writer=?, reply_contents=? where num=?";
		
		try {
			st = con.prepareStatement(sql);
				
			st.setString(1, aDto.getReply_yn());
			st.setString(2, aDto.getEmail());
			st.setString(3, aDto.getReply_writer());
			st.setString(4, aDto.getReply_contents());
			st.setInt(5, aDto.getNum());
			
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(st, con);			
		}
		
		return result;
	}
		
	
	/*	
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
	*/					
}
