package Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidateDV_CTSDDV {

	// Validate MÃ DỊCH VỤ không đúng định dạng
	public static boolean formatMaDV(String maDV) {
		if(maDV.matches("DV[0-9]{3}")) {
			return true;
		}
		return false;
	}

	// Validate MÃ DỊCH VỤ không tồn tại trong bảng DỊCH VỤ
	public static boolean existMaDV(String maDV) {
		Connection con = ConnectData.getConnect();
		String selectmaDV = " select Ma_DV from DICHVU where Ma_DV = ?";
		try {
			PreparedStatement pstm = con.prepareStatement(selectmaDV);
			pstm.setString(1, maDV);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Validate MÃ SỬ DỤNG không đúng định dạng
	public static boolean formatMaSD(String maSD) {
		if (maSD.matches("SD[0-9]{4}")) {
			return true;
		}
		return false;
	}

	// Validate MÃ SỦ DỤNG tồn tại trong bảng CHITIET_SDDV
	public static boolean existMaSD(String maSD) {
		Connection con = ConnectData.getConnect();
		String select = "select Ma_SD from CHITIET_SDDV where Ma_SD = ?";
		try {
			PreparedStatement pstm = con.prepareStatement(select);
			pstm.setString(1, maSD);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 'Validate MÃ ĐIỀU TRỊ NỘI TRÚ không đúng định dạng
	public static boolean formatMaDTNT(String maDTNT) {
		if (maDTNT.matches("NT[0-9]{4}")) {
			return true;
		}
		return false;
	}

	// Validate MÃ ĐIỀU TRỊ NỘI TRÚ không tồn tại trong cơ sở dữ liệu
	public static boolean existMaDTNT(String maDTNT) {
		Connection con = ConnectData.getConnect();
		String select= " select Ma_DT_NoiTru from DT_NOITRU where Ma_DT_NoiTru = ?";
		try {
			PreparedStatement pstm = con.prepareStatement(select);
			pstm.setString(1, maDTNT);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Validate NGÀY SỬ DỤNG dịch vụ trong bảng CHITIET_SDDV
	public static boolean formatDate(String ngaySD) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/M/d");

			try {
				LocalDate.parse(ngaySD, formatter);
				return true;
			} catch (DateTimeParseException e1) {
				try {
					LocalDate.parse(ngaySD, formatter1);
					return true;
				} catch (DateTimeParseException e2) {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Validate MÃ DỊCH VỤ
	public static boolean validateMaDV(String maDV) {
		Connection con = ConnectData.getConnect();
		String select = "select Ma_DV from CHITIET_SDDV where Ma_DV = ?";
		try {
			PreparedStatement pstm = con.prepareStatement(select);
			pstm.setString(1, maDV);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// Validate GIÁ của từng loại dịch vụ trong bảng DICHVU
	public static boolean gia(int gia) {
		if (gia > 0) {
			return true;
		}
		return false;
	}

	// Validate MONTH
	public static boolean validateMonth(int thang) {
		if (thang > 0 && thang <= 12) {
			return true;
		}
		return false;
	}
	
	//Validate NĂM
	public static boolean validateYear(int nam) {
		if (nam > 0) {
			return true;
		}
		return false;
	}

	// Validate MENU
	public static boolean validateMenu(int num) {
		if (num > 0 && num <= 4) {
			return true;
		}
		return false;
	}

	// Validate MAIN
	public static boolean validateMain(int num) {
		if (num > 0 && num <= 8) {
			return true;
		}
		return false;
	}
	
	
	//Validate tồn tại dữ liệu trong bảng DICHVU
	public static boolean checkDV() {
		Connection con = ConnectData.getConnect();
		String select = "select * from DICHVU ";
		try {
			PreparedStatement pstm = con.prepareStatement(select);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//Validate tồn tại dữ liệu trong bảng CHITIET_SDDV
	public static boolean checkSDDV() {
		Connection con = ConnectData.getConnect();
		String select = "select * from CHITIET_SDDV ";
		try {
			PreparedStatement pstm = con.prepareStatement(select);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
