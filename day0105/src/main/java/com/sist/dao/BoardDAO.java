package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.db.ConnectionProvider;
import com.sist.vo.BoardVO;

public class BoardDAO {
	int pageSIZE = 10;		//한 화면에 보여줄 레코드의 수
	int totalRecord = 0;	//전체 레코드의 수
	int totalPage = 0;		//전체 페이지의 수
	
	public int getTotalRecord(String writer) {
		String sql = "select count(*) from board ";
		if(writer != null) {
			sql+="where writer='"+writer+"'";
		}
		try {
			Connection conn = ConnectionProvider.getconConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				totalRecord = rs.getInt(1);
			}
			ConnectionProvider.close(conn, stmt, rs);
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return totalRecord;
	}
	
	public int getTotalPage(String writer) {
		int totalRecord = getTotalRecord(writer);
		totalPage = totalRecord/pageSIZE;
		if((totalRecord%pageSIZE)!=0) {
			totalPage++;
		}
		return totalPage;
	}
	
	public int update(BoardVO b) {
		int re = -1;
		String sql = "update board set "
				+ "title=?,"
				+ "content=?, "
				+ "fname=?, "
				+ "b_ref=?, "
				+ "b_level=?,"
				+ "b_step=?"
				+ "where no=? and pwd=?";
		try {
			Connection conn = ConnectionProvider.getconConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setString(3, b.getFname());
			pstmt.setInt(4, b.getNo());
			pstmt.setString(5, b.getPwd());
			pstmt.setInt(6, b.getB_ref());
			pstmt.setInt(7, b.getB_level());
			pstmt.setInt(8, b.getB_step());
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return re;
	}
	
	public int deleteBoard(int no,String pwd) {
		int re=-1;
		String sql="delete board where no="+no+"and pwd="+pwd;
		try {
			Connection conn = ConnectionProvider.getconConnection();
			Statement stmt = conn.createStatement();
			re = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return re;
	}
	
	public BoardVO detail(int no) {
		BoardVO vo = new BoardVO();
		String sql = "select * from board where no="+no;
		try {
			Connection conn = ConnectionProvider.getconConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				vo.setNo(no);
				vo.setTitle(rs.getString(2));
				vo.setWriter(rs.getString(3));
				vo.setPwd(rs.getString(4));
				vo.setContent(rs.getString(5));
				vo.setRegdate(rs.getDate(6));
				vo.setHit(rs.getInt(7));
				vo.setFname(rs.getString(8));
				vo.setIp(rs.getString(9));
				vo.setB_ref(rs.getInt(10));
				vo.setB_level(rs.getInt(11));
				vo.setB_step(rs.getInt(12));
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return vo;
	}
	
	public int insert(BoardVO b) {
		String sql = "insert into board values(?,?,?,?,?,sysdate,1,?,?,?,?,?)";
		int re = -1;
		try {
			Connection conn = ConnectionProvider.getconConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getNo());
			pstmt.setString(2, b.getTitle());
			pstmt.setString(3, b.getWriter());
			pstmt.setString(4, b.getPwd());
			pstmt.setString(5, b.getContent());
			pstmt.setString(6, b.getFname());
			pstmt.setString(7, b.getIp());
			pstmt.setInt(8, b.getB_ref());
			pstmt.setInt(9, b.getB_level());
			pstmt.setInt(10, b.getB_step());
			re = pstmt.executeUpdate();
			ConnectionProvider.close(conn, pstmt);
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return re;
	}
	
	public ArrayList<BoardVO> findAll(int pageNum,String writer){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		int page = pageNum*pageSIZE; 
		String sql = "select g.* from (SELECT b.*, rownum rnum FROM (select * from board";
		if(writer!=null) {
			sql += " where writer='"+writer+"'";
		}
		sql += " order by b_ref desc, b_step) b ORDER BY rownum) g where g.rnum BETWEEN ? and ?";
		try {
			Connection conn = ConnectionProvider.getconConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page-9);
			pstmt.setInt(2, page);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getDate(6),
						rs.getInt(7),
						rs.getString(8),
						rs.getString(9),
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12)
						);
				list.add(vo);
			}
			ConnectionProvider.close(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("findAll 예외발생:"+e.getMessage());
		}
		return list;
	}

	public int getNextNO() {
		int no = 0;
		String sql = "select nvl(max(no),0)+1 from board";
		try {
			Connection conn = ConnectionProvider.getconConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				no = rs.getInt(1);
			}
			
			ConnectionProvider.close(conn, stmt, rs);
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return no;
	}
	
	public void upCountingBoard(int no) {
		String sql = "update board set hit=(select hit from board where no=?)+1 where no=?";
		try {
			Connection conn = ConnectionProvider.getconConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setInt(2, no);
			pstmt.executeUpdate();
			ConnectionProvider.close(conn, pstmt);
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public void updateStep(int b_ref, int b_step) {
		String sql = "update board set b_step = b_step + 1 where b_ref=? and b_step>?";
		try {
			Connection conn = ConnectionProvider.getconConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_ref);
			pstmt.setInt(2, b_step);
			pstmt.executeQuery();
			ConnectionProvider.close(conn, pstmt);
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
}
