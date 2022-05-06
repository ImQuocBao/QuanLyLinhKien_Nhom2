package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDB;
import entity.LinhKien;
import entity.LoaiLinhKien;
import entity.NhaCungCap;

public class LinhKienDao {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;
	private NhaCungCapDao nccDao = new NhaCungCapDao();
	private LoaiLinhKienDao llkDao = new LoaiLinhKienDao();

	public LinhKienDao() {
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

	public List<LinhKien> getallSP() throws ClassNotFoundException, SQLException {
		List<LinhKien> dsSP = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM LINHKIEN where [TRANGTHAI]=1";
			java.sql.Statement statement = con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsSP = new ArrayList<>();
			while (rs.next()) {
				String maLk = rs.getString(1);
				String tenLk = rs.getString(2);
				int donGia = rs.getInt(3);
				int soLuong = rs.getInt(4);
				String maLoaiLK = rs.getString(5);
				int trangThai = rs.getInt(6);
				String maNcc = rs.getString(7);
				LoaiLinhKien loaiLK = llkDao.getLLKTheoID(maLoaiLK);
				NhaCungCap ncc = nccDao.getNCCTheoID(maNcc);
				LinhKien s = new LinhKien(maLk, tenLk, donGia, soLuong, trangThai, loaiLK, ncc);
				dsSP.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return dsSP;
	}

	public boolean themLk(LinhKien lk) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Insert into LINHKIEN values(?,?,?,?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setString(1, lk.getMaLK());
			statement.setString(2, lk.getTenLK());
			statement.setInt(3, lk.getDonGia());
			statement.setInt(4, lk.getSoLuong());
			statement.setString(5, lk.getLoaiLK().getMaLoaiLK());
			statement.setInt(6, 1);
			statement.setString(7, lk.getNhaCungCap().getMaNCC());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;

	}

	public boolean delete(String maLk) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "UPDATE LINHKIEN SET TRANGTHAI=0 where MALINHKIEN='" + maLk + "'";
			statement = con.prepareStatement(sql);

			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;

	}

	public boolean capnhat(LinhKien lk) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "UPDATE LINHKIEN SET TENLINHKIEN='" + lk.getTenLK() + "', DONGIA= '" + lk.getDonGia()
					+ "', SOLUONG= '" + lk.getSoLuong() + "', MALOAILINHKIEN='" + lk.getLoaiLK().getMaLoaiLK()
					+ "', MANCC='" + lk.getNhaCungCap().getMaNCC() + "' where MALINHKIEN='" + lk.getMaLK().trim() + "'";
			statement = con.prepareStatement(sql);

			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;

	}

	public List<String> getAllSpFromLLK(String tenLoai) throws ClassNotFoundException, SQLException {
		List<String> dsLk = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select [TENLINHKIEN] FROM LINHKIEN sp join LOAILINHKIEN lsp on sp.MALOAILINHKIEN = lsp.MALOAILINHKIEN Where lsp.TENLOAILINHKIEN=N'"+tenLoai+"' and sp.TRANGTHAI=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsLk = new ArrayList<>();
			while (rs.next()) {
				String tenSP = rs.getString(1);
				dsLk.add(tenSP);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsLk;
	}

	public LinhKien getMaSPTenSP(String ten) throws ClassNotFoundException, SQLException {
		LinhKien ds = null ;
		LoaiLinhKienDao lspDao = new LoaiLinhKienDao();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "select * from LINHKIEN where TENLINHKIEN like N'%"+ten+"%' and [TRANGTHAI]=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				String maLK = rs.getString(1);
				String tenLK = rs.getString(2);
				int donGia = rs.getInt(3);
				int soLuong = rs.getInt(4);
				String maLLK = rs.getString(5);
				int trangThai = rs.getInt(6);
				String maNcc = rs.getString(7);
				LoaiLinhKien loaiLK = lspDao.getLLKTheoID(maLLK);
				NhaCungCap ncc = nccDao.getNCCTheoID(maNcc);
				ds = new LinhKien(maLK, tenLK, donGia, soLuong, trangThai, loaiLK, ncc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return ds;
	}

	public LinhKien getLKTheoID(String maLk) throws ClassNotFoundException, SQLException {
		LinhKien lk = null;
		LoaiLinhKienDao lspDao = new LoaiLinhKienDao();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "  Select * from LINHKIEN where MALINHKIEN='"+ maLk +"'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				String maLK = rs.getString(1);
				String tenLK = rs.getString(2);
				int donGia = rs.getInt(3);
				int soLuong = rs.getInt(4);
				String maLLK = rs.getString(5);
				int trangThai = rs.getInt(6);
				String maNcc = rs.getString(7);
				LoaiLinhKien loaiLK = lspDao.getLLKTheoID(maLLK);
				NhaCungCap ncc = nccDao.getNCCTheoID(maNcc);
				lk = new LinhKien(maLK, tenLK, donGia, soLuong, trangThai, loaiLK, ncc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return lk;
	}
	
	public ArrayList<LinhKien> getLKTheoLoai(String maLLK) {
		ArrayList<LinhKien> dsLKTheoLoai = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			String sql = "SELECT * FROM LINHKIEN WHERE MALOAILINHKIEN = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, maLLK);
			rs = preStm.executeQuery();
			while(rs.next()) {
				String maLK = rs.getString("MALINHKIEN");
				String tenLK1 = rs.getString("TENLINHKIEN");
				int donGia = rs.getInt("DONGIA");
				int soLuong = rs.getInt("SOLUONG");
				LoaiLinhKien loaiLK = new LoaiLinhKien(rs.getString("MALOAILINHKIEN"));
				int trangThai = rs.getInt("TRANGTHAI");
				NhaCungCap ncc = new NhaCungCap(rs.getString("MANCC"));
				LinhKien lk = new LinhKien(maLK, tenLK1, donGia, soLuong, trangThai, loaiLK, ncc);
				dsLKTheoLoai.add(lk);
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dsLKTheoLoai;
	}
	
	public ArrayList<LinhKien> getLKTheoNCC(String maNCC) {
		ArrayList<LinhKien> dsLKTheoNCC = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			String sql = "SELECT * FROM LINHKIEN WHERE MANCC = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, maNCC);
			rs = preStm.executeQuery();
			while(rs.next()) {
				String maLK = rs.getString("MALINHKIEN");
				String tenLK1 = rs.getString("TENLINHKIEN");
				int donGia = rs.getInt("DONGIA");
				int soLuong = rs.getInt("SOLUONG");
				LoaiLinhKien loaiLK = new LoaiLinhKien(rs.getString("MALOAILINHKIEN"));
				int trangThai = rs.getInt("TRANGTHAI");
				NhaCungCap ncc = new NhaCungCap(rs.getString("MANCC"));
				LinhKien lk = new LinhKien(maLK, tenLK1, donGia, soLuong, trangThai, loaiLK, ncc);
				dsLKTheoNCC.add(lk);
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dsLKTheoNCC;
	}
	
	public ArrayList<LinhKien> getLKNCCLoai(String maNCC, String maLLK) {
		ArrayList<LinhKien> dsLKTheoNCCLoai = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			String sql = "SELECT * FROM LINHKIEN WHERE MANCC LIKE ? AND MALOAILINHKIEN = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, maNCC);
			preStm.setString(2, maLLK);
			rs = preStm.executeQuery();
			while(rs.next()) {
				String maLK = rs.getString("MALINHKIEN");
				String tenLK1 = rs.getString("TENLINHKIEN");
				int donGia = rs.getInt("DONGIA");
				int soLuong = rs.getInt("SOLUONG");
				LoaiLinhKien loaiLK = new LoaiLinhKien(rs.getString("MALOAILINHKIEN"));
				int trangThai = rs.getInt("TRANGTHAI");
				NhaCungCap ncc = new NhaCungCap(rs.getString("MANCC"));
				LinhKien lk = new LinhKien(maLK, tenLK1, donGia, soLuong, trangThai, loaiLK, ncc);
				dsLKTheoNCCLoai.add(lk);
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dsLKTheoNCCLoai;
	}
	
	
	public ArrayList<LinhKien> getLKTheoTen(String tenLK) {
		ArrayList<LinhKien> dsLKTheoTen = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			String sql = "SELECT * FROM LINHKIEN WHERE TENLINHKIEN LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%"+ tenLK + "%");
			rs = preStm.executeQuery();
			while(rs.next()) {
				String maLK = rs.getString("MALINHKIEN");
				String tenLK1 = rs.getString("TENLINHKIEN");
				int donGia = rs.getInt("DONGIA");
				int soLuong = rs.getInt("SOLUONG");
				LoaiLinhKien loaiLK = new LoaiLinhKien(rs.getString("MALOAILINHKIEN"));
				int trangThai = rs.getInt("TRANGTHAI");
				NhaCungCap ncc = new NhaCungCap(rs.getString("MANCC"));
				LinhKien lk = new LinhKien(maLK, tenLK1, donGia, soLuong, trangThai, loaiLK, ncc);
				dsLKTheoTen.add(lk);
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dsLKTheoTen;
	}
	
	public ArrayList<LinhKien> getLKTenLoai(String tenLK, String maLLK) {
		ArrayList<LinhKien> dsLKTheoTenLoai = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			String sql = "SELECT * FROM LINHKIEN WHERE TENLINHKIEN LIKE ? AND MALOAILINHKIEN = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%"+ tenLK + "%");
			preStm.setString(2, maLLK);
			rs = preStm.executeQuery();
			while(rs.next()) {
				String maLK = rs.getString("MALINHKIEN");
				String tenLK1 = rs.getString("TENLINHKIEN");
				int donGia = rs.getInt("DONGIA");
				int soLuong = rs.getInt("SOLUONG");
				LoaiLinhKien loaiLK = new LoaiLinhKien(rs.getString("MALOAILINHKIEN"));
				int trangThai = rs.getInt("TRANGTHAI");
				NhaCungCap ncc = new NhaCungCap(rs.getString("MANCC"));
				LinhKien lk = new LinhKien(maLK, tenLK1, donGia, soLuong, trangThai, loaiLK, ncc);
				dsLKTheoTenLoai.add(lk);
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dsLKTheoTenLoai;
	}
	
	public String getTenLoaiLK(String maLoaiLK) {
		String tenLoaiLK = "";
		try {
			conn = ConnectDB.getConnection();
			String sql = "SELECT * FROM LOAILINHKIEN WHERE MALOAILINHKIEN = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, maLoaiLK);
			rs = preStm.executeQuery();
			if(rs.next()) {
				tenLoaiLK = rs.getString("TENLOAILINHKIEN");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tenLoaiLK;
	}
	
	public String getTenNCC(String maNCC) {
		String tenNCC = "";
		try {
			conn = ConnectDB.getConnection();
			String sql = "SELECT * FROM NHACUNGCAP WHERE MANCC = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, maNCC);
			rs = preStm.executeQuery();
			if(rs.next()) {
				tenNCC = rs.getString("TENNCC");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tenNCC;
	}
	
	public ArrayList<LinhKien> getLKTenLoaiNCC(String tenLK, String maNCC, String maLLK) {
		ArrayList<LinhKien> dsLKTheoTenLoaiNCC = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			String sql = "SELECT * FROM LINHKIEN WHERE TENLINHKIEN LIKE ? AND MANCC = ? AND MALOAILINHKIEN = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%"+ tenLK + "%");
			preStm.setString(2, maNCC);
			preStm.setString(3, maLLK);
			rs = preStm.executeQuery();
			while(rs.next()) {
				String maLK = rs.getString("MALINHKIEN");
				String tenLK1 = rs.getString("TENLINHKIEN");
				int donGia = rs.getInt("DONGIA");
				int soLuong = rs.getInt("SOLUONG");
				LoaiLinhKien loaiLK = new LoaiLinhKien(rs.getString("MALOAILINHKIEN"));
				int trangThai = rs.getInt("TRANGTHAI");
				NhaCungCap ncc = new NhaCungCap(rs.getString("MANCC"));
				LinhKien lk = new LinhKien(maLK, tenLK1, donGia, soLuong, trangThai, loaiLK, ncc);
				dsLKTheoTenLoaiNCC.add(lk);
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dsLKTheoTenLoaiNCC;
	}
	
	public boolean capNhat(LinhKien sp,String maSP) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Update SANPHAM set TENSP=N'"+sp.getTenLK()+"',DONGIA="+sp.getDonGia()+",SOLUONG="+sp.getSoLuong()+",MALSP=N'"+sp.getLoaiLK().getMaLoaiLK()+"' where MASP='"+maSP+"'";
			statement = con.prepareStatement(sql);

			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean capNhatDonGiaSP(Double giaSPNew,String maSP) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Update SANPHAM set DONGIA="+giaSPNew+" where MASP='"+maSP+"'";
			statement = con.prepareStatement(sql);

			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean capNhatSoLuongSP(int soLuongSPNew,String maSP) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Update LINHKIEN set SOLUONG="+soLuongSPNew+" where MALINHKIEN='"+maSP+"'";
			statement = con.prepareStatement(sql);

			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	//
	public ArrayList<LinhKien> getallLK(){
		ArrayList<LinhKien> dsHD=new ArrayList<LinhKien>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			con = ConnectDB.getConnection();
			String sql="select*from LINHKIEN Where TRANGTHAI=1 order by SOLUONG desc";
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				String maLK = rs.getString("MALINHKIEN");
				String tenLK1 = rs.getString("TENLINHKIEN");
				int donGia = rs.getInt("DONGIA");
				int soLuong = rs.getInt("SOLUONG");
				LoaiLinhKien loaiLK = new LoaiLinhKien(rs.getString("MALOAILINHKIEN"));
				int trangThai = rs.getInt("TRANGTHAI");
				NhaCungCap ncc = new NhaCungCap(rs.getString("MANCC"));
				LinhKien lk = new LinhKien(maLK, tenLK1, donGia, soLuong, trangThai, loaiLK, ncc);
				//

//				String ma=rs.getString(1);
//				String ten=rs.getString(2);
//				Double gia=rs.getDouble(3);
//				int soluong=rs.getInt(4);
//				boolean trangthai=rs.getBoolean(6);
//				LoaiLinhKien maloaiKien= new LoaiLinhKien(rs.getString(5));
//				NhaCungCap nhaCungCap=new NhaCungCap(rs.getString(7));
//				LinhKien lk=new LinhKien(ma,ten,soluong,gia,trangthai,maloaiKien,nhaCungCap);
				dsHD.add(lk);
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
	
	public LinhKien getLinhKienMa(String ma){
		LinhKien lk= new LinhKien();
		Connection con;
		PreparedStatement stmt=null;
		try {
			con = ConnectDB.getConnection();
			String sql="select *from LINHKIEN where MALINHKIEN=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, ma);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				String maLK = rs.getString("MALINHKIEN");
				String tenLK1 = rs.getString("TENLINHKIEN");
				int donGia = rs.getInt("DONGIA");
				int soLuong = rs.getInt("SOLUONG");
				LoaiLinhKien loaiLK = new LoaiLinhKien(rs.getString("MALOAILINHKIEN"));
				int trangThai = rs.getInt("TRANGTHAI");
				NhaCungCap ncc = new NhaCungCap(rs.getString("MANCC"));
				lk = new LinhKien(maLK, tenLK1, donGia, soLuong, trangThai, loaiLK, ncc);
				
//				String malk=rs.getString(1);
//				String ten=rs.getString(2);
//				int soluong=rs.getInt(4);
//				double gia=rs.getDouble(3);
//				LoaiLinhKien loaiLinhKien=new LoaiLinhKien(rs.getString(5));
//				boolean trangthai=rs.getBoolean(6);
//				NhaCungCap nhaCungCap=new NhaCungCap(rs.getString(7));
//				
//				lk=new LinhKien(malk,ten,soluong,gia,trangthai,loaiLinhKien,nhaCungCap);
				}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return lk;
}
}
