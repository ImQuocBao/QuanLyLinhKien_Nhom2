package entity;

public class LinhKien {
	private String maLK, tenLK;
	private int donGia, soLuong;
	private int trangThai;
	private LoaiLinhKien loaiLK;
	private NhaCungCap nhaCungCap;
	
	public LinhKien(String maLK, String tenLK, int donGia, int soLuong, int trangThai, LoaiLinhKien loaiLK,
			NhaCungCap nhaCungCap) {
		super();
		this.maLK = maLK;
		this.tenLK = tenLK;
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.trangThai = trangThai;
		this.loaiLK = loaiLK;
		this.nhaCungCap = nhaCungCap;
	}
	
	public LinhKien() {
		super();
	}
	public LinhKien(String maLK) {
		super();
		this.maLK = maLK;
	}
	
	public String getMaLK() {
		return maLK;
	}
	public void setMaLK(String maLK) {
		this.maLK = maLK;
	}
	public String getTenLK() {
		return tenLK;
	}
	public void setTenLK(String tenLK) {
		this.tenLK = tenLK;
	}
	public int getDonGia() {
		return donGia;
	}
	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	public LoaiLinhKien getLoaiLK() {
		return loaiLK;
	}
	public void setLoaiLK(LoaiLinhKien loaiLK) {
		this.loaiLK = loaiLK;
	}
	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}
	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	@Override
	public String toString() {
		return "LinhKien [maLK=" + maLK + ", tenLK=" + tenLK + ", donGia=" + donGia + ", soLuong=" + soLuong
				+ ", trangThai=" + trangThai + ", loaiLK=" + loaiLK + ", nhaCungCap=" + nhaCungCap + "]";
	}
}
