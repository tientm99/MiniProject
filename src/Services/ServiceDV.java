package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Entity.DichVu;
import Util.ConnectData;

public class ServiceDV {
	
	//Thêm dữ liệu vào bảng DỊCH VỤ
	public static int insertDV(DichVu DV) {
		Connection con = Util.ConnectData.getConnect();
		int result = 0;
		String insert = "insert into DICHVU values (?,?,?)";
		try {
			PreparedStatement pstm = con.prepareStatement(insert);
			pstm.setString(1, DV.getMaDV());
			pstm.setString(2, DV.getTenDV());
			pstm.setInt(3, DV.getGia());
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
	
	//Cập nhật dữ liệu ở bảng DỊCH VỤ
	public static int updateDV(DichVu DV) {
		Connection con = ConnectData.getConnect();
		int result = 0;
		String update = "update DICHVU set Ten_DV = ?, Gia = ? where Ma_DV = ?";
		try {
			PreparedStatement pstm = con.prepareStatement(update);
			pstm.setString(1, DV.getTenDV());
			pstm.setInt(2, DV.getGia());
			pstm.setString(3, DV.getMaDV());
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
	
	//Xóa dữ liệu ở bảng DỊCH VỤ
	public static int deleteDV(String maDV) {
		Connection con = ConnectData.getConnect();
		int result = 0;
		String delete = "delete DICHVU where Ma_DV = ?";
		try {
			PreparedStatement pstm = con.prepareStatement(delete);
			pstm.setString(1, maDV);
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
