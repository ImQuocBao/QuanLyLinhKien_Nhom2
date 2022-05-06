package entity;

public class NhaCungCap {
	private String maNCC, tenNCC, diaChi, quocTich;
	private int trangThai;
	
	public NhaCungCap(String maNCC, String tenNCC, String diaChi, String quocTich) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.diaChi = diaChi;
		this.quocTich = quocTich;
	}

	public NhaCungCap(String maNCC, String tenNCC, String diaChi, String quocTich, int trangThai) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.diaChi = diaChi;
		this.quocTich = quocTich;
		this.trangThai = trangThai;
	}

	public NhaCungCap() {
		super();
	}

	public NhaCungCap(String maNcc) {
		this.maNCC = maNcc;
	}

	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getQuocTich() {
		return quocTich;
	}

	public void setQuocTich(String quocTich) {
		this.quocTich = quocTich;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChi=" + diaChi + ", quocTich=" + quocTich
				+ ", trangThai=" + trangThai + "]";
	}
}

