package Entity;

import java.util.Date;

public class ChiTietSDDV {
	private String maSD;
	private String maDTNT;
	private String maDV;
	private Date ngaySD;
	
	public ChiTietSDDV() {
		super();
	}

	

	public ChiTietSDDV(String maSD, String maDTNT, String maDV, Date ngaySD) {
		super();
		this.maSD = maSD;
		this.maDTNT = maDTNT;
		this.maDV = maDV;
		this.ngaySD = ngaySD;
	}



	public String getMaSD() {
		return maSD;
	}

	public void setMaSD(String maSD) {
		this.maSD = maSD;
	}

	public String getMaDTNT() {
		return maDTNT;
	}

	public void setMaDTNT(String maDTNT) {
		this.maDTNT = maDTNT;
	}

	public String getMaDV() {
		return maDV;
	}

	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}

	public Date getNgaySD() {
		return ngaySD;
	}

	public void setNgaySD(Date ngaySD) {
		this.ngaySD = ngaySD;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "\n[Mã sử dụng: " + maSD + ", Mã điều trị nội trú: " + maDTNT + ", Mã dịch vụ: " + maDV + ", Ngày sử dụng: " + ngaySD + "]";
	}

	
	
}
