package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.ConnectDB;
import entity.NhanVien;

public class NhanVienDao {

	Connection conn;
	PreparedStatement preStm;
	Statement statement;
	ResultSet rs;
	
	public NhanVienDao() {
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
	};
	
	public NhanVien getNVTheoMa(String maNVCanTim) throws SQLException, ClassNotFoundException {
		NhanVien dsNv = null;
		try {
			Connection con = ConnectDB.getConnection();
			Statement statement = con.createStatement();
			String sql = "select * from NHANVIEN where MANV = '" + maNVCanTim + "'";
			
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			
			while (rs.next()) {
				
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				String cMND = rs.getString(3);
				String soDT = rs.getString(4);
				String diaChi = rs.getString(5);
				String chucVu = rs.getString(6);

				dsNv = new NhanVien(maNV, tenNV, cMND, soDT, diaChi, chucVu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return dsNv;
	}
	
	public ArrayList<NhanVien> getAllNV() {
		ArrayList<NhanVien> dsNV = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			String sql = "SELECT * FROM NHANVIEN";
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maNV = rs.getString("MANV");
				String tenNV = rs.getString("TENNV");
				String cmnd = rs.getString("CMND");
				String sdt = rs.getString("SDT");
				String diaChi = rs.getString("DIACHI");
				String chucVu = rs.getString("CHUCVU");
				NhanVien nv = new NhanVien(maNV, tenNV, cmnd, sdt, diaChi, chucVu);
				dsNV.add(nv);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dsNV;
	}
	
	public ArrayList<NhanVien> getNVTheoTen(String tenNV) {
		ArrayList<NhanVien> dsNVTheoTen = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			String sql = "SELECT * FROM NHANVIEN WHERE TENNV LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%"+ tenNV + "%");
			rs = preStm.executeQuery();
			while(rs.next()) {
				String maNV = rs.getString("MANV");
				String tenNV1 = rs.getString("TENNV");
				String cmnd = rs.getString("CMND");
				String sdt = rs.getString("SDT");
				String diaChi = rs.getString("DIACHI");
				String chucVu = rs.getString("CHUCVU");
				NhanVien nv = new NhanVien(maNV, tenNV1, cmnd, sdt, diaChi, chucVu);
				dsNVTheoTen.add(nv);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dsNVTheoTen;
	}
	
	public ArrayList<NhanVien> getNVTheoSDT(String sdt) {
		ArrayList<NhanVien> dsNVTheoSDT = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			String sql = "SELECT * FROM NHANVIEN WHERE sdt LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%"+ sdt);
			rs = preStm.executeQuery();
			while(rs.next()) {
				String maNV = rs.getString("MANV");
				String tenNV1 = rs.getString("TENNV");
				String cmnd = rs.getString("CMND");
				String sdt1 = rs.getString("SDT");
				String diaChi = rs.getString("DIACHI");
				String chucVu = rs.getString("CHUCVU");
				NhanVien nv = new NhanVien(maNV, tenNV1, cmnd, sdt1, diaChi, chucVu);
				dsNVTheoSDT.add(nv);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dsNVTheoSDT;
	}
	
	public ArrayList<NhanVien> getNVTheoTenSDT(String tenNV, String sdt) {
		ArrayList<NhanVien> dsNVTheoSDT = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			String sql = "SELECT * FROM NHANVIEN WHERE TENNV LIKE ? AND SDT LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%"+ tenNV + "%");
			preStm.setString(2, "%" + sdt);
			rs = preStm.executeQuery();
			while(rs.next()) {
				String maNV = rs.getString("MANV");
				String tenNV1 = rs.getString("TENNV");
				String cmnd = rs.getString("CMND");
				String sdt1 = rs.getString("SDT");
				String diaChi = rs.getString("DIACHI");
				String chucVu = rs.getString("CHUCVU");
				NhanVien nv = new NhanVien(maNV, tenNV1, cmnd, sdt1, diaChi, chucVu);
				dsNVTheoSDT.add(nv);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dsNVTheoSDT;
	}
	
	//minh huyen
	
	public ArrayList<NhanVien> getAllNhanVien() {
		ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "select * from NHANVIEN";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				String cMND = rs.getString(3);
				String soDT = rs.getString(4);
				String diaChi = rs.getString(5);
				String chucVu = rs.getString(6);

				NhanVien ds = new NhanVien(maNV, tenNV, cMND, soDT, diaChi, chucVu);
				dsnv.add(ds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsnv;
	}
	
	
	
	public void updateNhanVien(NhanVien nv) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = ("update NHANVIEN SET TENNV = ?, CMND = ?, SDT = ?,DIACHI=?,CHUCVU=? WHERE MANV=?");
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, nv.getTenNV());
			stmt.setString(2, nv.getcMND());
			stmt.setString(3, nv.getsDT());
			stmt.setString(4, nv.getDiaChi());
			stmt.setString(5, nv.getChuVu());
			stmt.setString(6, nv.getMaNV());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		
	}
	public void insertNhanVien(NhanVien nv) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("Insert into NHANVIEN values(?, ?, ?, ?, ?, ?)");
			stmt.setString(1, nv.getMaNV());
			stmt.setString(2, nv.getTenNV());
			stmt.setString(3, nv.getcMND());
			stmt.setString(4, nv.getsDT());
			stmt.setString(5, nv.getDiaChi());
			stmt.setString(6, nv.getChuVu());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		
	}
	public void deleteNhanVien(String id) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from NhanVien where maNV = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			stmt.close();
		}
	}
	


}
