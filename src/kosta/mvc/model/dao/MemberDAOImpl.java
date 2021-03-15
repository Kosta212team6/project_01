package kosta.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kosta.mvc.model.dto.MemberDTO;
import kosta.mvc.util.DBUtil;

public class MemberDAOImpl implements MemberDAO {

	// select mid, mpwd, mstatus from Member where mID='choi' and mPwd=2222;
	@Override
	public MemberDTO login(String mID, String mPwd) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select mid, mpwd, mstatus from Member where mID=? and mPwd=?";
		MemberDTO memberDTO = null;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, mID);
			ps.setString(2, mPwd);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				memberDTO = new MemberDTO(rs.getString(1), rs.getString(2), rs.getInt(3));
			}
			
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		
		return memberDTO;
	}

	@Override
	public List<MemberDTO> memberSelectAll() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		String sql = "SELECT MID, MNAME, MPHONE, MABLE, NCODE FROM MEMBER WHERE MSTATUS=1";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String mID = rs.getString("mid");
				String mName = rs.getString("mname");
				String mPhone = rs.getString("mphone");
				String mAble = rs.getString("mable");
				int nCode = rs.getInt("ncode");
				
				MemberDTO memberDTO = new MemberDTO(mID, mName, mPhone, null, mAble, 0, nCode);
				list.add(memberDTO);
			}
			
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public List<MemberDTO> overdueMember() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		String sql = "SELECT MID, MNAME, MPHONE, MABLE, NCODE FROM MEMBER "
				+ "WHERE MSTATUS=1 AND SYSDATE < TO_CHAR(MABLE, 'YYYYMMDD'); ";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String mID = rs.getString("mid");
				String mName = rs.getString("mname");
				String mPhone = rs.getString("mphone");
				String mAble = rs.getString("mable");
				int nCode = rs.getInt("ncode");
				
				MemberDTO memberDTO = new MemberDTO(mID, mName, mPhone, null, mAble, 0, nCode);
				list.add(memberDTO);
			}
			
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public boolean checkIDMember(String NowID) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT MID FROM MEMBER WHERE MID=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, NowID);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return false;
	}

	@Override
	public int createMember(MemberDTO memberDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		int result = 0;
		String sql = "INSERT INTO MEMBER(MID, MNAME, MPHONE, MPWD, MABLE, MSTATUS, NCODE) "
				+ "VALUES(?, ?, ?, ?, SYSDATE, 1, 10)";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, memberDTO.getmID());
			ps.setString(2, memberDTO.getmName());
			ps.setString(3, memberDTO.getmPhone());
			ps.setString(4, memberDTO.getmPwd());
			
			result = ps.executeUpdate();
			
		} finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
	}

	@Override
	public List<MemberDTO> myInFo(String mID) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		String sql = "SELECT MID, MNAME, MPHONE, MABLE, NCODE FROM MEMBER WHERE mid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, mID);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String ID = rs.getString("mid");
				String mName = rs.getString("mname");
				String mPhone = rs.getString("mphone");
				String mAble = rs.getString("mable");
				int nCode = rs.getInt("ncode");
				
				MemberDTO memberDTO = new MemberDTO(ID, mName, mPhone, null, mAble, 0, nCode);
				list.add(memberDTO);
			}
			
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public int UpdatePassword(String mID, String mPwd) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE member SET mpwd=? WHERE mid=?";
		int result = 0;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, mPwd);
			ps.setString(2,mID);
			result = ps.executeUpdate();
		}finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
		
	}

	@Override
	public int UpdatePhoneNumber(String mID, String mPhone) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE member SET mPhone=? WHERE mid=?";
		int result = 0;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, mPhone);
			ps.setString(2,mID);
			result = ps.executeUpdate();
		}finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
	}

	@Override
	public int cancelAccount(String mID, String mPwd) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE MEMBER SET MSTATUS=0 WHERE MID=? AND MPWD=?";
		int result = 0;
		
		try {
			con= DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, mID);
			ps.setString(2, mPwd);
			
			result = ps.executeUpdate();
		} finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
		
	}


	
	
}
