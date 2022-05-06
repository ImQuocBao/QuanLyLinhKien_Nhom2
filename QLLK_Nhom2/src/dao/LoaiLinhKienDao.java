package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDB;
import entity.LoaiLinhKien;

public class LoaiLinhKienDao {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;
	
	public LoaiLinhKienDao() {
		super();
	}

	private void closeConnection() throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (preStm != null) {
			preStm.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public LoaiLinhKien getLLKTheoID(String maLoaiLK) throws ClassNotFoundException, SQLException {
		LoaiLinhKien llk = null;
		try {
			Connection con = ConnectDB.getConnection(); 
			String sql = "SELECT * FROM LOAILINHKIEN where MALOAILINHKIEN='"+ maLoaiLK.trim() + "'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				String maLLK = rs.getString(1);
				String TenLLK = rs.getString(2);
				llk = new LoaiLinhKien(maLLK, TenLLK);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return llk;
	}

	public List<LoaiLinhKien> getAll() throws SQLException, ClassNotFoundException {
		List<LoaiLinhKien> dsLLk = new ArrayList<LoaiLinhKien>();
		try {
			Connection con = ConnectDB.getConnection(); 
			String sql = "SELECT * FROM LOAILINHKIEN";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				
				String maLLK = rs.getString(1);
				String TenLLK = rs.getString(2);
				LoaiLinhKien llk = new LoaiLinhKien(maLLK, TenLLK);
				dsLLk.add(llk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsLLk;
	}

	public String getMaTheoTen(String tenLoaiLK) throws SQLException, ClassNotFoundException {
		String llk = null;
		try {
			Connection con = ConnectDB.getConnection(); 
			String sql = "SELECT * FROM LOAILINHKIEN where TENLOAILINHKIEN='"+ tenLoaiLK.trim() + "'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				llk = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return llk;
	}
	
	public LoaiLinhKien getLLKTheoTen(String tenDMNeed) throws Exception{
		LoaiLinhKien dm = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM LOAILINHKIEN where TENLOAILINHKIEN=N'"+tenDMNeed+"'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				String maDM = rs.getString(1);
				String tenDM = rs.getString(2);
				dm = new LoaiLinhKien(maDM, tenDM);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dm;
	}
	
}
