package Entity;

public class DichVu {
	private String maDV;
	private String tenDV;
	private int gia;
	
	public DichVu(String maDV, String tenDV, int gia) {
		super();
		this.maDV = maDV;
		this.tenDV = tenDV;
		this.gia = gia;
	}

	public DichVu() {
		super();
	}

	public String getMaDV() {
		return maDV;
	}

	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}

	public String getTenDV() {
		return tenDV;
	}

	public void setTenDV(String tenDV) {
		this.tenDV = tenDV;
	}

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "\n[Mã dịch vụ: " + maDV + ", Tên dịch vụ: " + tenDV + ",\tGiá: "+ gia + "]";
	}
	
	
	
	
	
}
