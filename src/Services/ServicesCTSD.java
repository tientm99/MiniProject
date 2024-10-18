package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Entity.ChiTietSDDV;
import Util.ConnectData;

public class ServicesCTSD {
	public static int insertCTSD(ChiTietSDDV SD) {
		Connection con = ConnectData.getConnect();
		int result = 0;
		String insert = "insert into CHITIET_SDDV values (?,?,?,?)";
		try {
			PreparedStatement pstm = con.prepareStatement(insert);
			pstm.setString(1, SD.getMaSD());
			pstm.setString(2, SD.getMaDTNT());
			pstm.setString(3, SD.getMaDV());
			
			java.sql.Date dateSD = new java.sql.Date(SD.getNgaySD().getTime());
			pstm.setDate(4, dateSD);
			
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static int updateCTSD(ChiTietSDDV SD) {
		Connection con = ConnectData.getConnect();
		int result = 0;
		String update = "update CHITIET_SDDV set Ma_DT_NoiTru = ?, Ma_DV = ?, Ngay_Su_Dung = ? where Ma_SD = ?";
		try {
			PreparedStatement pstm = con.prepareStatement(update);
			java.sql.Date dateSD = new java.sql.Date(SD.getNgaySD().getTime());
			pstm.setString(1, SD.getMaDTNT());
			pstm.setString(2, SD.getMaDV());
			pstm.setDate(3,dateSD);
			pstm.setString(4, SD.getMaSD());
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static int deleteCTSD(String maSD) {
		Connection con = ConnectData.getConnect();
		int result = 0;
		String delete = "delete CHITIET_SDDV where Ma_SD = ?";
		try {
			PreparedStatement pstm = con.prepareStatement(delete);
			pstm.setString(1, maSD);
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
