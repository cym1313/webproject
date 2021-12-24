package cs.dit.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;
import javax.naming.InitialContext;

import java.sql.Connection;
import javax.naming.Context;

import cs.dit.dto.MemberDTO;

public class MemberDAO {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public MemberDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/JSP");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
		if(con != null) {
			con.close();
			con=null;
		}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public ArrayList<MemberDTO> list(){
		String sql = "select * from member"; 
		ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				 MemberDTO dto = new MemberDTO();
				 dto.setId(rs.getString("id"));
				 dto.setPwd(rs.getString("pwd"));
				 dto.setName(rs.getString("name"));
				 dto.setEmail(rs.getString("email"));
				 dto.setJoinDate(rs.getDate("joinDate"));
				 dtos.add(dto);
			}
			rs.close();
			pstmt.close();
			System.out.println("정상적으로 저장됬습니다."); 
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
				close();
			}
		return dtos;
	}
	
public MemberDTO view(String id) {
	String sql = "select pwd, name, email, joinDate from member where id=?";
	MemberDTO dto = new MemberDTO();
	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			 dto.setId(id);
			 dto.setPwd(rs.getString("pwd"));
			 dto.setName(rs.getString("name"));
			 dto.setEmail(rs.getString("email"));
			 dto.setJoinDate(rs.getDate("joinDate"));
		}
		rs.close();
		pstmt.close();
	}catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally {
			close();
		}
	return dto;
}
	
	public boolean insert(MemberDTO dto) {
		String sql = "insert into member (id, pwd, name, email, joinDate) values(?,?,?,?, sysdate)"; 
		boolean check = false; 
		try { 
		con= ds.getConnection(); 
		pstmt= con.prepareStatement(sql); 
		pstmt.setString(1, dto.getId()); 
		pstmt.setString(2, dto.getPwd ()); 
		pstmt.setString(3, dto.getName ()); 
		pstmt.setString (4, dto.getEmail()); 
		int x = pstmt.executeUpdate(); 
		
		if(x<1) {
			System.out.println("정상적으로 저장되지않았습니다."); 
		}else { 
			check=true; 
			}
		pstmt.close(); 
		}catch(SQLException ex) { 
			System.out.println("SQL insert 오류 :" + ex.getLocalizedMessage());
			check = false;
		}
			return check;
	}
	
	public boolean update(MemberDTO dto) {
		String sql = "update member set name=?, pwd=?, email=?, joinDate=? where id=?";
		boolean check = false;
		
		try { 
			con= ds.getConnection(); 
			pstmt= con.prepareStatement(sql); 
			pstmt.setString(1, dto.getName ()); 
			pstmt.setString(2, dto.getPwd ()); 
			pstmt.setString(3, dto.getEmail());
			pstmt.setDate(4, dto.getJoinDate()); 
			pstmt.setString(5, dto.getId());
			int x = pstmt.executeUpdate(); 
			
			if(x<1) {
				System.out.println("정상적으로 저장되지않았습니다."); 
			}else { 
				check=true; 
				}
			pstmt.close(); 
			}catch(SQLException ex) { 
				System.out.println("SQL insert 오류 :" + ex.getLocalizedMessage());
				check = false;
			}
		
		return check;
	}
	
	public boolean delete(String id) {
		String sql = "delete from member where id=?";
		boolean check = false;
		try { 
			con= ds.getConnection(); 
			pstmt= con.prepareStatement(sql); 
			pstmt.setString(1, id); 
			
			int x = pstmt.executeUpdate(); 
			
			if(x<1) {
				System.out.println("정상적으로 저장되지않았습니다."); 
			}else { 
				check=true; 
				}
			pstmt.close(); 
			}catch(SQLException ex) { 
				System.out.println("SQL insert 오류 :" + ex.getLocalizedMessage());
				check = false;
			}
		return check;
	}
}

