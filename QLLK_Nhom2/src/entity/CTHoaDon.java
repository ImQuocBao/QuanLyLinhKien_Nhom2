package entity;

public class CTHoaDon {
	private LinhKien linhKien;
	private HoaDon hoaDon;
	private int soLuong;
	
	public CTHoaDon(LinhKien linhKien, HoaDon hoaDon, int soLuong) {
		super();
		this.linhKien = linhKien;
		this.hoaDon = hoaDon;
		this.soLuong = soLuong;
	}
	
	public CTHoaDon() {
		super();
	}

	public LinhKien getLinhKien() {
		return linhKien;
	}
	public void setLinhKien(LinhKien linhKien) {
		this.linhKien = linhKien;
	}
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	@Override
	public String toString() {
		return "CTHoaDon [linhKien=" + linhKien + ", hoaDon=" + hoaDon + ", soLuong=" + soLuong + "]";
	}
	
	
}
