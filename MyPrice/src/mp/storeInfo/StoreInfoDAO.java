package mp.storeInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import mp.common.GetInfoDTO;
import mp.util.DBConnector;
import mp.util.PageMaker;

public class StoreInfoDAO {
	
	//entpId를 넣으면 entpId entpName을 돌려주는 메서드
	public ArrayList<StoreInfoDTO> getEntpName(long entpId){
		Connection con = DBConnector.getConnect();
		String sql ="select entpid, entpname from GETSTOREINFOSVC where entpId="+entpId;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<StoreInfoDTO> ar = new ArrayList<>();
		
		try {
			st =con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next()){
				StoreInfoDTO s = new StoreInfoDTO();
				s.setEntpId(rs.getLong(1));
				s.setEntpName(rs.getString(2));
				ar.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);
		}return ar;
	}
	
	//13개 종류 불러오는 메서드
	public ArrayList<GetInfoDTO> getGoodSmlclsCode(){
		Connection con =DBConnector.getConnect();
		String sql = "select si.CODENAME as a, si.CODE as b from GETSTANDARDINFOSVC si "
				+ "where si.CLASSCODE = 'AL' "
				+ "and substr(si.CODE, 7, 9) = '000' and not substr(si.CODE, 5, 9)='00000' "
				+ "order by si.CODENAME";
		
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<GetInfoDTO> ar = new ArrayList<>();
		try {
			st = con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next()){
				GetInfoDTO g = new GetInfoDTO();
				g.setCodeName(rs.getString(1));
				g.setCode(rs.getString(2));
				ar.add(g);
				System.out.println("13분류 제대로 확인?=========");
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);
		}return ar;
		
	} 

	//saleInfo total count
	public int totalCount(String entpId){
		Connection con = DBConnector.getConnect();
		String sql = "select count(entpId) as total from GETSTOREINFOSVC "
					 + "where entpId='"+entpId+"' ";
		                   
		System.out.println("SI 토탈카운트 SQL문: "+sql);
		PreparedStatement st = null;
		ResultSet rs = null;
		int result = 0;
		try {
			st = con.prepareStatement(sql);
			rs=st.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);
		}return result; //성공하면 0이상
		
	}
//GET STORE INFO (2016.12.5)
	public ArrayList<StoreInfoDTO> getStoreInfo (long entpId){
		Connection con = DBConnector.getConnect();
		String makeSql="select * from GETSTOREINFOSVC "
					 + "where entpId='"+entpId+"' "
					 + "order by entpname";
		String sql=makeSql;
		PreparedStatement st= null;
		ResultSet rs=null;
		ArrayList<StoreInfoDTO> ar = new ArrayList<>();
		
		try {
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			
			while(rs.next()){
				StoreInfoDTO s = new StoreInfoDTO();
				s.setEntpId(rs.getLong(2)); //entpId 
				s.setEntpName(rs.getString(3));
				s.setEntpTypeCode(rs.getString(4));
				s.setEntpBrandCode(rs.getString(5));
				s.setEntpAreaCode(rs.getString(6));
				s.setEntpTelno(rs.getString(8));
				s.setPlmkAddrBasic(rs.getString(10));
				s.setPlmkAddrDetail(rs.getString(11));
				s.setxMapCoord(rs.getString(14)); //x좌표
				s.setyMapCoord(rs.getString(15)); //y좌표
				ar.add(s);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnector.disConnect(rs, st, con);
		}return ar;
		
	}
//필터에 따른 결과값
	//Salelist
		public ArrayList<StoreInfoDTO> saleInfoList(PageMaker pm, String entpId){
			Connection con = DBConnector.getConnect();
			
			String makeSql = 
					 "select * from "
					 + "(select rownum R, T.* from "
					 + "(select * from GETSTOREINFOSVC "
					 + "where entpId='"+entpId+"' "
					 + "order by entpname) T) where R between ? and ?";
			String sql=makeSql;
			System.out.println("sql 합친거 확인: "+sql);
			
			PreparedStatement st = null;
			ResultSet rs = null;
			ArrayList<StoreInfoDTO> ar = new ArrayList<>();
			try {
				st = con.prepareStatement(sql);
				st.setInt(1, pm.getStartRow()); //시작줄
				st.setInt(2, pm.getLastRow()); //끝줄
				rs =st.executeQuery();
				System.out.println("rs값: "+rs);
				
				while(rs.next()){
					StoreInfoDTO s = new StoreInfoDTO();
					s.setEntpId(rs.getLong(2)); //entpId 
					s.setEntpName(rs.getString(3));
					s.setEntpTypeCode(rs.getString(4));
					s.setEntpBrandCode(rs.getString(5));
					s.setEntpAreaCode(rs.getString(6));
					s.setEntpTelno(rs.getString(8));
					s.setPlmkAddrBasic(rs.getString(10));
					s.setPlmkAddrDetail(rs.getString(11));
					s.setxMapCoord(rs.getString(14)); //x좌표
					s.setyMapCoord(rs.getString(15)); //y좌표
					ar.add(s);
					System.out.println("=======db확인saleinfo");
					System.out.println(rs.getString(2));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBConnector.disConnect(rs, st, con);
			}return ar;
		}
		
		

}

