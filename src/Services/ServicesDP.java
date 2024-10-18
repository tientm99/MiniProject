package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Entity.ChiTietSDDV;
import Entity.DichVu;
import Util.ConnectData;

public class ServicesDP {

	// hiển thị số lượng bệnh nhân sử dụng dịch vụ theo từng tháng của năm
	public static List<String> displayBNDV(int thang, int nam) {
		Connection con = ConnectData.getConnect();
		List<String> list = new ArrayList<String>();
		String display = "select dv.Ma_DV, dv.Ten_DV,count(sd.Ma_DV) as LanSuDung  from DICHVU dv\r\n"
				+ "join CHITIET_SDDV sd on dv.Ma_DV=sd.Ma_DV\r\n"
				+ "where  MONTH(sd.Ngay_Su_Dung) = ? and YEAR(sd.Ngay_Su_Dung) = ?\r\n"
				+ "group by dv.Ma_DV, dv.Ten_DV\r\n"
				+ "order by count(sd.Ma_DV) desc";
		try {
			PreparedStatement pstm = con.prepareStatement(display);
			pstm.setInt(1, thang);
			pstm.setInt(2, nam);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				int so = rs.getInt(3);
				list.add("Mã dịch vụ: " + ma + "Tên dịch vụ: " + ten + "\tSố Lần sử dụng: " + so);
			}
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
		return list;

	}

	// hiển thị bệnh nhân sử dụng dịch vụ trong khoảng thời gian
	public static List<String> displayBN(String maDV, Date ngaytruoc, Date ngaysau) {
		Connection con = ConnectData.getConnect();
		List<String> list = new ArrayList<String>();
		String display = "Select BN.Ma_BN as MA_BN, BN.Ten_BN as TEN_BN, count(BN.Ma_BN) as SoLanSuDung from BENHNHAN as BN\r\n"
				+ "join CT_KHAMBENH as KB on BN.Ma_BN = KB.Ma_BN\r\n"
				+ "join DT_NOITRU as DTNT on KB.Ma_Kham_Benh = DTNT.Ma_Kham_Benh\r\n"
				+ "join CHITIET_SDDV as SD on DTNT.Ma_DT_NoiTru = SD.Ma_DT_NoiTru\r\n"
				+ "where SD.Ma_DV = ? and SD.Ngay_Su_Dung between ? and ?\r\n" + "group by BN.Ma_BN,  BN.Ten_BN\r\n"
				+ "order by count(BN.Ma_BN) desc";
		try {
			PreparedStatement pstm = con.prepareStatement(display);
			pstm.setString(1, maDV);
			java.sql.Date ngaytruocSQL = new java.sql.Date(ngaytruoc.getTime());
			java.sql.Date ngaysauSQL = new java.sql.Date(ngaysau.getTime());
			pstm.setDate(2, ngaytruocSQL);
			pstm.setDate(3, ngaysauSQL);

			ResultSet rs = pstm.executeQuery();
			while (rs.next() == true) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				int so =rs.getInt(3);
				list.add("Mã bệnh nhân: " + ma + "Tên bệnh nhân: " + ten + "\tSố lần sử dụng: " + so);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// hiển thị tổng doanh thu của dịch vụ theo từng năm
	public static List<String> displayDT(String maDV) {
		Connection con = ConnectData.getConnect();
		List<String> list = new ArrayList<String>();
		String display = "select sd.Ma_DV as MA_DV, dv.Ten_DV as TEN_DV, year(sd.Ngay_Su_Dung) as Nam, count(sd.Ma_DV) as LanSuDung, sum(dv.Gia) as TongDoanhThu  from CHITIET_SDDV sd \r\n"
				+ "join DICHVU dv on dv.Ma_DV=sd.Ma_DV \r\n" + "where sd.Ma_DV = ? \r\n"
				+ "GROUP BY sd.Ma_DV, dv.Ten_DV, year(sd.Ngay_Su_Dung)";
		try {
			PreparedStatement pstm = con.prepareStatement(display);
			pstm.setString(1, maDV);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String ma = rs.getString("MA_DV");
				String ten = rs.getString("TEN_DV");
				int nam = rs.getInt("Nam");
				int so = rs.getInt("LanSuDung");
				int tong = rs.getInt("TongDoanhThu");
				list.add("Mã dịch vụ: "+ma+"\t Tên dịch vụ: "+ten+"\t Năm: "+nam+"\t Lần sử dụng: "+so+"\t Tổng doanh thu: "+tong);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// hiển thị số lượng bệnh nhân sử dụng dịch vụ theo từng tháng của năm
	public static List<String> displayBNDVdata(int thang, int nam) {
		Connection con = ConnectData.getConnect();
		List<String> list = new ArrayList<String>();
		String display = "select sd.Ma_DV as MA_DV, dv.Ten_DV as TEN_DV, COUNT(distinct bn.Ma_BN) as So_BN_SUDUNG from BENHNHAN bn\r\n"
				+ "join CT_KHAMBENH ct on bn.Ma_BN = ct.Ma_BN\r\n"
				+ "join DT_NOITRU dt on dt.Ma_Kham_Benh = ct.Ma_Kham_Benh\r\n"
				+ "join CHITIET_SDDV sd on sd.Ma_DT_NoiTru = dt.Ma_DT_NoiTru\r\n"
				+ "join DICHVU dv on sd.Ma_DV=dv.Ma_DV\r\n"
				+ "where MONTH(sd.Ngay_Su_Dung)= ? and YEAR(sd.Ngay_Su_Dung)= ?\r\n" + "group by sd.Ma_DV, Ten_DV";
		try {
			PreparedStatement pstm = con.prepareStatement(display);
			pstm.setInt(1, thang);
			pstm.setInt(2, nam);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String ma = rs.getString("Ma_DV");
				String ten = rs.getString("Ten_DV");
				int so = rs.getInt("So_BN_SUDUNG");
				list.add("Mã dịch vụ: "+ma+"\t Tên dịch vụ: "+ten+"\t Số bệnh nhân sử dụng: "+so);
			}
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
		return list;
	}

	public static ArrayList<DichVu> listDV() {
		Connection con = ConnectData.getConnect();
		ArrayList<DichVu> list = new ArrayList<>();
		String listDV = "select * from DICHVU";
		try {
			PreparedStatement pstm = con.prepareStatement(listDV);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String maDV = rs.getString(1);
				String tenDV = rs.getString(2);
				int gia = rs.getInt(3);
				DichVu DV = new DichVu(maDV, tenDV, gia);
				list.add(DV);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<ChiTietSDDV> listSDDV() {
		Connection con = ConnectData.getConnect();
		ArrayList<ChiTietSDDV> list = new ArrayList<>();
		String listDV = "select * from CHITIET_SDDV";
		try {
			PreparedStatement pstm = con.prepareStatement(listDV);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String maSD = rs.getString(1);
				String maDTNT = rs.getString(2);
				String maDV = rs.getString(3);
				java.sql.Date ngaySD = rs.getDate(4);
				ChiTietSDDV SD = new ChiTietSDDV(maSD, maDTNT, maDV, ngaySD);
				list.add(SD);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static ArrayList<String> displayTDT(int nam) {
		Connection con = ConnectData.getConnect();
		ArrayList<String> list = new ArrayList<>();
		String display = "SELECT \r\n"
				+ "DSThang.Thang AS THANG, \r\n"
				+ "YEAR(sd.Ngay_Su_Dung) AS NAM, \r\n"
				+ "COUNT(sd.Ma_SD) AS SoLanDKSDDV, \r\n"
				+ "ISNULL(SUM(dv.Gia), 0) AS TongDoanhThu  \r\n"
				+ "FROM \r\n"
				+ "(SELECT 1 AS Thang UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL \r\n"
				+ "SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL \r\n"
				+ "SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12) AS DSThang\r\n"
				+ "LEFT JOIN \r\n"
				+ "CHITIET_SDDV sd ON MONTH(sd.Ngay_Su_Dung) = DSThang.Thang\r\n"
				+ "AND YEAR(sd.Ngay_Su_Dung) = ?\r\n"
				+ "LEFT JOIN \r\n"
				+ "DICHVU dv ON dv.Ma_DV = sd.Ma_DV\r\n"
				+ "GROUP BY \r\n"
				+ "DSThang.Thang, YEAR(sd.Ngay_Su_Dung)\r\n"
				+ "ORDER BY \r\n"
				+ "thang";
		try {
			PreparedStatement pstm = con.prepareStatement(display);
			pstm.setInt(1, nam);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				int thang = rs.getInt(1);
				int nam1 = rs.getInt(2);
				int solansudung = rs.getInt(3);
				int tongdoanhthu = rs.getInt(4);
				list.add("Tháng: "+thang+"\tNăm: "+nam1+"\tSố lần ĐK sử dụng dịch vụ: "+solansudung+"\tTổng doanh thu: "+tongdoanhthu);
			}
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
		return list;
	}
	

}
