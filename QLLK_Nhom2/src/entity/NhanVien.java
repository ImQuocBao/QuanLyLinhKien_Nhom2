package entity;

public class NhanVien {

	private String maNV;
	private String tenNV;
	private String cMND;
	private String sDT;
	private String diaChi;
	private String chucVu;
	public NhanVien() {
		super();
	}
	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}
	public NhanVien(String maNV, String tenNV, String cMND, String sDT, String diaChi,String chucVu) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.cMND = cMND;
		this.sDT = sDT;
		this.diaChi = diaChi;
		this.chucVu = chucVu;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getcMND() {
		return cMND;
	}
	public void setcMND(String cMND) {
		this.cMND = cMND;
	}
	public String getsDT() {
		return sDT;
	}

	public void setsDT(String sDT) {
		this.sDT = sDT;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	
	public String getChuVu() {
		return chucVu;
	}

	public void setChuVu(String chucVu) {
		this.chucVu = chucVu;
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", cMND=" + cMND + ", sDT=" + sDT + ", diaChi=" + diaChi
				+", chuVu="+chucVu+ "]";
	}
	
}
