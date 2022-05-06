package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDB;
import entity.DonHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;


public class HoaDonDao {

	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;
	
	// BAO XU LY DON HANG
	public HoaDonDao() {
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
	
	public List<DonHang> getallDH() throws SQLException, ClassNotFoundException{
		List<DonHang> dsDH = null;
		DonHang dh;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM HOADON where TRANGTHAI=0";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsDH = new ArrayList<>();
			while (rs.next()) {
				String maDM = rs.getString(1);
				Date ngayTao = rs.getDate(2);
				String maKH = rs.getString(3);
				String maNV = rs.getString(4);
				int tongTien = rs.getInt(5);
				int tienMat = rs.getInt(6);
				NhanVien nv = new NhanVien(maNV);
				KhachHang kh = new KhachHang(maKH);
				dh = new DonHang(maDM, ngayTao, kh, nv, tongTien, tienMat);
				dsDH.add(dh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsDH;
	}
	
	public int demSoDH() throws SQLException, ClassNotFoundException {
		int soDH = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "select COUNT(MAHD) as 'Tong' from HOADON Where MAHD like '%DH%'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				soDH = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return soDH;
	}
	
	
	public DonHang getDHTheoID(String maDHCanTim) throws Exception{
		DonHang dh = null ;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM HOADON where MAHD='"+maDHCanTim+"'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				
				String maDM = rs.getString(1);
				Date ngayTao = rs.getDate(2);
				String maKH = rs.getString(3);
				String maNV = rs.getString(4);
				int tongTien = rs.getInt(5);
				int tienMat = rs.getInt(6);
				NhanVien nv = new NhanVien(maNV);
				KhachHang kh = new KhachHang(maKH);
				dh = new DonHang(maDM, ngayTao, kh, nv, tongTien, tienMat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dh;
	}
	
	public HoaDon getHDTheoID(String maDHCanTim) throws Exception{
		HoaDon dh = null ;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM HOADON where MAHD='"+maDHCanTim+"'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				String maDM = rs.getString(1);
				Date ngayTao = rs.getDate(2);
				String maKH = rs.getString(3);
				String maNV = rs.getString(4);
				int tongTien = rs.getInt(5);
				int tienMat = rs.getInt(6);
				NhanVien nv = new NhanVien(maNV);
				KhachHang kh = new KhachHang(maKH);
				dh = new HoaDon(maDM, ngayTao, kh, nv, tongTien, tienMat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dh;
	}

	public boolean themDH(HoaDon hd) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Insert into HOADON values(?,?,?,?,?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setString(1, hd.getMaHD());
			statement.setDate(2, hd.getNgayTao());
			statement.setString(3, hd.getKhachHang().getMaKH());
			statement.setString(4, hd.getNhanVien().getMaNV());
			statement.setInt(5, hd.getTongThanhTien());
			statement.setInt(6, hd.getTienMat());
			statement.setInt(7, hd.getTienDu());
			statement.setInt(8, 0);
			
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;		
	}
	public boolean capnhatTongTien(int tongTien, String maDH) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Update HOADON set TONGTHANHTIEN="+tongTien+",TIENMAT ="+tongTien+" where MAHD='"+maDH+"'";
			statement = con.prepareStatement(sql);
			
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean capnhatTrangThaiDonHang(String maDH) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Update HOADON set TRANGTHAI= " + 1 + " where MAHD='"+maDH+"'";
			statement = con.prepareStatement(sql);
			
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	

	// NAM XU LY HOA DON
	public ArrayList<HoaDon> getallHD(){
		ArrayList<HoaDon> dsHD=new ArrayList<HoaDon>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			con = ConnectDB.getConnection();
			String sql="select*from HOADON";
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				String mahd=rs.getString(1);
				Date ngaylap=rs.getDate(2);
				KhachHang makh=new KhachHang(rs.getString(3));
				NhanVien manv=new NhanVien(rs.getString(4));
				int tongtien=rs.getInt(5);
				int tiendua=rs.getInt(6);
				HoaDon hDon=new HoaDon(mahd,ngaylap,makh,manv,tongtien,tiendua);
				dsHD.add(hDon);
				}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return dsHD;
	}
	
	
	public ArrayList<HoaDon> getHDtheoNgay(String ngaybd, String ngaykt){
		ArrayList<HoaDon> dsHD=new ArrayList<HoaDon>();
		Connection con;
		PreparedStatement stmt=null;
		try {
			con = ConnectDB.getConnection();
			String sql="select*from HOADON WHERE NGAYLAPHD >=? and NGAYLAPHD <=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, ngaybd);
			stmt.setString(2, ngaykt);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				String mahd=rs.getString(1);
				Date ngaylap=rs.getDate(2);
				KhachHang makh=new KhachHang(rs.getString(3));
				NhanVien manv=new NhanVien(rs.getString(4));
				int tongtien=rs.getInt(5);
				int tiendua=rs.getInt(6);
				
				HoaDon hDon=new HoaDon(mahd,ngaylap,makh,manv,tongtien,tiendua);
				dsHD.add(hDon);}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return dsHD;
	}
	public int Tongtien(String ngaybd, String ngaykt) {
		Connection con;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		int tongtien=0;
		try {
			con = ConnectDB.getConnection();
			String sql="select sum(TongThanhTien) from HOADON WHERE NGAYLAPHD >=? and NGAYLAPHD <=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, ngaybd);
			stmt.setString(2, ngaykt);
			//Statement statement =  con.createStatement();
			rs =stmt.executeQuery();
			while (rs.next()) {
				tongtien = rs.getInt(1);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tongtien;
	}
	
	public HoaDon getHoaDonMa(String ma){
		HoaDon hDon=new HoaDon();
		Connection con;
		PreparedStatement stmt=null;
		try {
			con = ConnectDB.getConnection();
			String sql="select *from HoaDon where MAHD=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, ma);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				String mahd=rs.getString(1);
				Date ngaylap=rs.getDate(2);
				KhachHang makh=new KhachHang(rs.getString(3));
				NhanVien manv=new NhanVien(rs.getString(4));
				int tongtien=rs.getInt(5);
				int tiendua=rs.getInt(6);
				
				hDon=new HoaDon(mahd,ngaylap,makh,manv,tongtien,tiendua);
				
				}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return hDon;

}
}
