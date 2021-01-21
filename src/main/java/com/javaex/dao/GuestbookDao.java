package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	//필드
	@Autowired
	private DataSource dataSource;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private int count = 0;
	
	private void connectDB() {
		try {
			
			conn = dataSource.getConnection();

		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
	}
	
	private void closeRs() {
		// 5. 자원정리
	    try {
	        if (rs != null) {
	            rs.close();
	        }            	
	    	if (pstmt != null) {
	        	pstmt.close();
	        }
	    	if (conn != null) {
	        	conn.close();
	        }
	    } catch (SQLException e) {
	    	System.out.println("error:" + e);
	    }
	}
	
	public List<GuestbookVo> getList() {
		
		List<GuestbookVo> GBList = new ArrayList<GuestbookVo>();
		
		connectDB();
		
		try {		
			// 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query +=" select	no,";
			query +="			name,";
			query +="			password,";
			query +="			content,";
			query +="			reg_date";
			query +=" from guestbook";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
								
				GuestbookVo gbVo = new GuestbookVo(rs.getInt("no"), rs.getString("name"), rs.getString("password"), rs.getString("content"), rs.getString("reg_date"));
				
				GBList.add(gbVo);
			}
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		
		closeRs();
		
		return GBList;
	}
	
	public int insert(GuestbookVo gbVo) {
		
		connectDB();
		
		try {		
			// 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query +=" insert into guestbook";
			query +=" values (seq_guestbook_no.nextval, ?, ?, ?, sysdate)";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, gbVo.getName());
			pstmt.setString(2, gbVo.getPassword());
			pstmt.setString(3, gbVo.getContent());
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			//System.out.println("[DAO] : " +count+ "건 등록");
			
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		
		closeRs();
		
		return count;
	}
	
	public int delete(GuestbookVo gbVo) {
		
		connectDB();
		
		try {		
			// 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query +=" delete guestbook";
			query +=" where no = ?";
			query +=" and password = ?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, gbVo.getNo());
			pstmt.setString(2, gbVo.getPassword());
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println("[DAO] : " +count+ "건 삭제");
			
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		
		closeRs();
		
		return count;
		
	}

}