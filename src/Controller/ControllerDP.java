package Controller;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Entity.DichVu;
import Services.ServicesDP;
import Util.ValidateDV_CTSDDV;

public class ControllerDP {
	static Scanner sc = new Scanner(System.in);
	static ArrayList<DichVu> list = new ArrayList<>();
	static List<String> listDP = new ArrayList<String>();

	// Nhập vào tháng và hiển thị số lượng từng loại dịch vụ được sử dụng nhiều nhất
	// theo từng tháng
	public static void displayDV() {
		int thang;
		do {
			try {
				System.out.println("Nhập vào Tháng: ");
				thang = Integer.parseInt(sc.nextLine());
				if (ValidateDV_CTSDDV.validateMonth(thang) == true) {
					break;
				} else {
					System.out.println("Nhập vào THÁNG không hợp lệ. Mời nhập lại!");
				}
			} catch (Exception e) {
				System.out.println("Nhập vào tháng không đúng định dạng. Mời nhập lại!");
			}
		} while (true);

		int nam;
		do {
			try {
				System.out.println("Nhập vào Năm: ");
				nam = Integer.parseInt(sc.nextLine());
				if (ValidateDV_CTSDDV.validateYear(nam) == true) {
					break;
				} else {
					System.out.println("Nhập vào NĂM không hợp lệ. Mời nhập lại!");
				}
			} catch (Exception e) {
				System.out.println("Nhập vào NĂM không đúng định dạng. Mời nhập lại!");
			}
		} while (true);

		listDP = ServicesDP.displayBNDV(thang, nam);
		for (String add : listDP) {
			System.out.println(add);
		}
		if (list.size() == 0) {
			System.out.println("Không có dịch vụ nào được sử dụng trong tháng " + thang + " năm " + nam);
		}
	}

	// Nhập vào MÃ DICH VỤ và khoảng thời gian để hiển thị bệnh nhân sử dụng dịch vụ
	// trong khoảng thời gian
	public static void displayBN() {

		list = ServicesDP.listDV();
		System.out.println(list.toString());

		// Nhập vào MÃ DỊCH VỤ
		String maDV = "";
		do {
			System.out.println("Nhập vào mã dịch vụ (có định dạng DVxxx với x là số): ");
			maDV = sc.nextLine();
			if (ValidateDV_CTSDDV.formatMaDV(maDV) == true) {
				if (ValidateDV_CTSDDV.existMaDV(maDV) == true) {
					if (ValidateDV_CTSDDV.validateMaDV(maDV) == true) {
						break;
					} else {
						System.out.println("Mã dịch vụ vừa nhập chưa được sử dụng. Mời nhập lại!");
					}
				} else {
					System.out.println("Mã dịch vụ nhập vào không tồn tại trong bảng DICHVU. Mời nhập lại!");
				}
			} else {
				System.out.println("Mã dịch vụ nhập vào không đúng định dạng. Mời nhập lại!");
			}
		} while (true);

		// Nhập vào khoảng thời gian
		String ngaytruoc = "";
		String ngaysau = "";
		Date ngaytruocFM = new Date();
		Date ngaysauFM = new Date();
		do {
			System.out.println("Nhập vào khoảng thời gian (có định dạng yyyy/mm/dd): ");
			ngaytruoc = sc.nextLine();
			System.out.println("Đến ngày: ");
			ngaysau = sc.nextLine();
			if (ValidateDV_CTSDDV.formatDate(ngaytruoc) == true) {
				if (ValidateDV_CTSDDV.formatDate(ngaysau) == true) {
					try {
						ngaytruocFM = new SimpleDateFormat("yyyy/MM/dd").parse(ngaytruoc);
						ngaysauFM = new SimpleDateFormat("yyyy/MM/dd").parse(ngaysau);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (ngaytruocFM.before(ngaysauFM)) {
						break;
					} else {
						System.out.println("Ngày bắt đầu phải nhỏ hơn ngày sau. Mời nhập lại!");
					}
				} else {
					System.out.println("Ngày nhập vào sai định dạng. Mời nhập lại!");
				}
			} else {
				System.out.println("Ngày nhập vào sai định dạng. Mời nhập lại!");
			}
		} while (true);

		listDP = ServicesDP.displayBN(maDV, ngaytruocFM, ngaysauFM);
		for (String add : listDP) {
			System.out.println(add);
		}
		if (list.size() == 0) {
			System.out.println("Không có dịch vụ nào sử dụng dịch vụ trong khoảng thời gian " + ngaytruoc + " đến ngày "
					+ ngaysau);
		}
	}

	// Nhập vào MÃ DỊCH VỤ để hiển thị tổng doanh thu của dịch vụ theo từng năm
	public static void displayDT() {
		list = ServicesDP.listDV();
		System.out.println(list.toString());

		String maDV = "";
		do {
			System.out.println("Nhập vào mã dịch vụ (có định dạng DVxxx với x là số): ");
			maDV = sc.nextLine();
			if (ValidateDV_CTSDDV.formatMaDV(maDV) == true) {
				if (ValidateDV_CTSDDV.existMaDV(maDV) == true) {
					if (ValidateDV_CTSDDV.validateMaDV(maDV) == true) {
						break;
					} else {
						System.out.println("Mã dịch vụ vừa nhập chưa được sử dụng. Mời nhập lại!");
					}
				} else {
					System.out.println("Mã dịch vụ nhập vào không tồn tại trong bảng DICHVU. Mời nhập lại!");
				}
			} else {
				System.out.println("Mã dịch vụ nhập vào không đúng định dạng. Mời nhập lại!");
			}
		} while (true);

		listDP = ServicesDP.displayDT(maDV);
		for (String add : listDP) {
			System.out.println(add);
		}
		if (list.size() == 0) {
			System.out.println("Không có dịch vụ nào sử dụng");
		}

	}

	// Nhập vào tháng, năm đề hiển thị số lượng bệnh nhân sử dụng dịch vụ theo từng
	// tháng của năm
	public static void displayBNDV() {
		int thang, nam;
		do {
			try {
				System.out.println("Nhập vào Tháng: ");
				thang = Integer.parseInt(sc.nextLine());
				if (ValidateDV_CTSDDV.validateMonth(thang) == true) {
					break;
				} else {
					System.out.println("Nhập vào THÁNG không hợp lệ. Mời nhập lại!");
				}
			} catch (Exception e) {
				System.out.println("Nhập vào tháng không đúng định dạng. Mời nhập lại!");
			}
		} while (true);

		do {
			try {
				System.out.println("Nhập vào Năm: ");
				nam = Integer.parseInt(sc.nextLine());
				if (ValidateDV_CTSDDV.validateYear(nam) == true) {
					break;
				} else {
					System.out.println("Nhập vào NĂM không hợp lệ. Mời nhập lại!");
				}
			} catch (Exception e) {
				System.out.println("Nhập vào NĂM không đúng định dạng. Mời nhập lại!");
			}
		} while (true);
		listDP = ServicesDP.displayBNDVdata(thang, nam);
		for (String add : listDP) {
			System.out.println(add);
		}
		if (list.size() == 0) {
			System.out.println("Không có bệnh nhân sử dụng dịch vụ trong tháng " + thang + " năm " + nam);
		}
	}
	//Tính tổng doanh thu theo từng tháng của năm
	public static void displayTDThu() {
		int nam;
		do {
			try {
				System.out.println("Nhập vào Năm: ");
				nam = Integer.parseInt(sc.nextLine());
				if (ValidateDV_CTSDDV.validateYear(nam) == true) {
					break;
				} else {
					System.out.println("Năm không thể giá trị âm. Mời nhập lại!");
				}
			} catch (NumberFormatException e) {
				System.out.println("Nhập vào năm không đúng định dạng");
			}
		} while (true);
		listDP = ServicesDP.displayTDT(nam);
		for (String add : listDP) {
			System.out.println(add);
		}
	}
}
