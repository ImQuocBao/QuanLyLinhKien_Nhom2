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
import entity.CTHoaDon;
import entity.HoaDon;
import entity.LinhKien;

public class CTHoaDonDao {

	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;

	public CTHoaDonDao() {
		super();
		// TODO Auto-generated constructor stub
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

	public CTHoaDon getCTHD(String maHD,String maLk) {
		CTHoaDon cthd = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM CTHOADON where MAHD='" + maHD + "' AND MALINHKIEN='"+maLk+"'";
			java.sql.Statement statement = con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				int soLuong = rs.getInt(3);
				LinhKienDao spDao = new LinhKienDao();
				HoaDon hd = new HoaDon(maHD);
				LinhKien sp = spDao.getLKTheoID(maLk);
				cthd = new CTHoaDon(sp, hd, soLuong);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cthd;
	}
	
	public List<CTHoaDon> getallCTHD(String maHDCanTim) throws Exception {
		List<CTHoaDon> dsHD = null;
		CTHoaDon cthd;
		LinhKienDao lkDao = new LinhKienDao();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM CTHOADON where MAHD='" + maHDCanTim + "'";
			java.sql.Statement statement = con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsHD = new ArrayList<>();
			while (rs.next()) {
				String maLk = rs.getString(1);
				String maHD = rs.getString(2);
				int soLuong = rs.getInt(3);
				HoaDon hd = new HoaDon(maHD);
				LinhKien lk = lkDao.getLKTheoID(maLk);
				cthd = new CTHoaDon(lk, hd, soLuong);
				dsHD.add(cthd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return dsHD;
	}

	public boolean themCTHD(CTHoaDon dto) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Insert into CTHOADON values(?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setString(1, dto.getLinhKien().getMaLK());
			statement.setString(2, dto.getHoaDon().getMaHD());
			statement.setInt(3, dto.getSoLuong());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean delete(String maHD, String maSP) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "DELETE from CTHOADON where MAHD='" + maHD + "' AND MALINHKIEN='" + maSP + "'";
			statement = con.prepareStatement(sql);
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public List<CTHoaDon> getallSP(Date from, Date end) throws Exception{
		List<CTHoaDon> dsHD = null;
		CTHoaDon cthd;
		LinhKienDao lkDao = new LinhKienDao();
		HoaDonDao hdDao = new HoaDonDao();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = " select sp.MASP, sp.TENSP,SUM(cthd.SOLUONG) as 'So_Luong',SUM(cthd.THANHTIEN) as 'Thanh_tien'\r\n"
					+ " from HOADON hd join CTHOADON cthd on hd.MAHD=cthd.MAHD join SANPHAM sp on sp.MASP = cthd.MASP\r\n"
					+ " where NGAYTAO BETWEEN '"+from.toString()+"' and '"+end.toString()+"'\r\n"
					+ "  group by sp.MASP, TENSP\r\n"
					+ " order by So_Luong desc,'Thanh_tien'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsHD = new ArrayList<>();
			while (rs.next()) {
				String MALINHKIEN = rs.getString(1);
				String MAHD = rs.getString(2);
				int soLuong = rs.getInt(3);
				LinhKien lk = lkDao.getLKTheoID(MALINHKIEN);
				HoaDon hd = hdDao.getHDTheoID(MAHD);
				cthd = new CTHoaDon(lk, hd, soLuong);
				dsHD.add(cthd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsHD;
	}

	public boolean checkCTHD(CTHoaDon ctHoaDon) throws Exception {
		CTHoaDonDao ctHoaDonDao = new CTHoaDonDao();
		Connection con = ConnectDB.getConnection();
		int n = 0;
		LinhKienDao dao = new LinhKienDao();
		dao.capNhatSoLuongSP(ctHoaDon.getLinhKien().getSoLuong() - ctHoaDon.getSoLuong(), ctHoaDon.getLinhKien().getMaLK());
		
		String sql = "SELECT * FROM CTHOADON where MAHD='" + ctHoaDon.getHoaDon().getMaHD() + "' AND MALINHKIEN='"
				+ ctHoaDon.getLinhKien().getMaLK() + "'";
		java.sql.Statement statement = con.createStatement();
		rs = ((java.sql.Statement) statement).executeQuery(sql);
		while (rs.next()) {
			String maLk = rs.getString(1);
			String maHd = rs.getString(2);
			int soLuong = rs.getInt(3);
			ctHoaDonDao.capNhat((ctHoaDon.getSoLuong() + soLuong), maHd, maLk);
			n = 1;
		}
		return n > 0;
	}
	
	public boolean capNhat(int soLuong, String maHD, String maLk) throws Exception {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Update CTHOADON set SOLUONG=" + soLuong + " where MAHD='" + maHD
					+ "' AND MALINHKIEN='" + maLk + "'";
			statement = con.prepareStatement(sql);
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	//
	public ArrayList<CTHoaDon> getallCTHD(){
		ArrayList<CTHoaDon> dsHD=new ArrayList<CTHoaDon>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			con = ConnectDB.getConnection();
			String sql="select LINHKIEN.MALINHKIEN,CTHOADON.SOLUONG,HOADON.MAHD\r\n"
					+ "from LINHKIEN inner join CTHOADON ON LINHKIEN.MALINHKIEN=CTHOADON.MALINHKIEN inner join HOADON on CTHOADON.MAHD=HOADON.MaHD";
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				LinhKien malKien=new LinhKien(rs.getString(1));
				int soLuong = rs.getInt(2);
				HoaDon mahd=new HoaDon(rs.getString(3));
				CTHoaDon cthd = new CTHoaDon(malKien, mahd, soLuong);
				dsHD.add(cthd);
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
	
	
	public ArrayList<CTHoaDon> getSP(String ngaybd,String ngaykt) throws ClassNotFoundException {
		ArrayList<CTHoaDon> dsHD = new ArrayList<CTHoaDon>();
		PreparedStatement stmt=null;
		Connection con=null;
		ResultSet rs=null;
		try {
			 con = ConnectDB.getConnection();
			String sql = "select LINHKIEN.MALINHKIEN,CTHOADON.SOLUONG,HOADON.MAHD"
					+ " from LINHKIEN inner join CTHOADON "
					+ "ON LINHKIEN.MALINHKIEN=CTHOADON.MALINHKIEN inner join HOADON"
					+ " on CTHOADON.MAHD=HOADON.MaHD"
					+ " WHERE HOADON.NGAYLAPHD>=? and HOADON.NGAYLAPHD <=? order by CTHOADON.SOLUONG DESC";
			stmt=  con.prepareStatement(sql);
			stmt.setString(1, ngaybd);
			stmt.setString(2, ngaykt);
			rs=stmt.executeQuery();
			while (rs.next()) {
				LinhKien malKien=new LinhKien(rs.getString(1));
				int soLuong = rs.getInt(2);
				HoaDon mahd=new HoaDon(rs.getString(3));
				CTHoaDon cthd = new CTHoaDon(malKien, mahd, soLuong);
				dsHD.add(cthd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsHD;
	}
	
}
