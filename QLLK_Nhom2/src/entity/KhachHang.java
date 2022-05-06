package entity;

public class KhachHang {

	private String maKH, tenKH, diaChi, gioiTinh, soDienThoai;
	public KhachHang(String maKH, String tenKH, String soDienThoai,String diaChi, String gioiTinh) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.soDienThoai = soDienThoai;
	}
	
	public KhachHang(String maKH) {
		super();
		this.maKH = maKH;
	}

	public KhachHang() {
		super();
	}
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", diaChi=" + diaChi + ", gioiTinh=" + gioiTinh
				+ ", soDienThoai=" + soDienThoai + "]";
	}
}
