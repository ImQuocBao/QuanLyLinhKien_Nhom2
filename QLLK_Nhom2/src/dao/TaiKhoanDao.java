package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.NhanVien;
import connect.ConnectDB;

public class TaiKhoanDao {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;
	
	public TaiKhoanDao() {
		super();
	}
	
	@SuppressWarnings("unused")
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
	
	public String checkNV(String userName, String password) {
		String maNV = "";
		try {
			Connection con = ConnectDB.getConnection();
			Statement statement =  con.createStatement();
			
			String sql = "select MANV from TAIKHOAN where TENDANGNHAP='"+userName+"' and MATKHAU='"+password+"'";
			
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			
			while (rs.next()) {
				System.out.println(rs.getString(1));
				maNV = rs.getString(1);
			}
		} catch (Exception e) {
		}
		return maNV;
	}
	
	public boolean create(NhanVien nv) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		
		try {
			
			statement = con.prepareStatement("insert into TAIKHOAN ( [MANV],[TENTK],[MATKHAU]) values(?, ?, ?)");
			statement.setString(1, nv.getMaNV());
			statement.setNString(2, nv.getMaNV());
			statement.setNString(3, "1111");
			n = statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n > 0;
	}
}
