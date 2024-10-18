package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectData {
	public static Connection getConnect() {
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://DESKTOP-LJE8EVA\\SQLEXPRESS:1433;databaseName=BENHVIEN;trustServerCertificate=true";
			String userName = "sa";
			String passWord = "12345";
			con = DriverManager.getConnection(url, userName, passWord);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("lỗi");
		}
		return con;
	}
}
