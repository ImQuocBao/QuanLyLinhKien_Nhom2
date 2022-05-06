package entity;

public class LoaiLinhKien {

	private String maLoaiLK, tenLoaiLK;

	public LoaiLinhKien() {
		super();
	}

	public LoaiLinhKien(String maLoaiLK, String tenLoaiLK) {
		super();
		this.maLoaiLK = maLoaiLK;
		this.tenLoaiLK = tenLoaiLK;
	}

	public LoaiLinhKien(String maDm) {
		this.maLoaiLK = maDm;
	}

	public String getMaLoaiLK() {
		return maLoaiLK;
	}

	public void setMaLoaiLK(String maLoaiLK) {
		this.maLoaiLK = maLoaiLK;
	}

	public String getTenLoaiLK() {
		return tenLoaiLK;
	}

	public void setTenLoaiLK(String tenLoaiLK) {
		this.tenLoaiLK = tenLoaiLK;
	}

	@Override
	public String toString() {
		return "LoaiLinhKien [maLoaiLK=" + maLoaiLK + ", tenLoaiLK=" + tenLoaiLK + "]";
	}
}
