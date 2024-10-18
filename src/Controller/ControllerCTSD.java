package Controller;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Entity.ChiTietSDDV;
import Services.ServicesCTSD;
import Services.ServicesDP;
import Util.ValidateDV_CTSDDV;

public class ControllerCTSD {
	static Scanner sc = new Scanner(System.in);
	static ArrayList<ChiTietSDDV> list = new ArrayList<>();
	
	// Nhập dữ liệu để thêm vào bảng CHITIET_SDDV
	public static void insertCTSD() {

		// Nhập vào MÃ SỬ DỤNG dịch vụ
		String maSD = "";
		do {
			System.out.println("Nhập vào mã sử dụng (có định dạng SDxxxx với x là số): ");
			maSD = sc.nextLine();
			if (ValidateDV_CTSDDV.formatMaSD(maSD) == true) {
				if (ValidateDV_CTSDDV.existMaSD(maSD) == false) {
					break;
				} else {
					System.out.println("Mã sử dụng đã tồn tại trong bảng CHITIET_SDDV. Mời nhập lại!");
				}
			} else {
				System.out.println("Mã sử dụng nhập vào không đúng định dạng. Mời nhập lại!");
			}
		} while (true);

		// Nhập vào MÃ ĐIỀU TRỊ NỘI TRÚ
		String maDTNT = "";
		do {
			System.out.println("Nhập vào mã điều trị nội trú (có định dạng NTxxxx với x là số): ");
			maDTNT = sc.nextLine();
			if (ValidateDV_CTSDDV.formatMaDTNT(maDTNT) == true) {
				if (ValidateDV_CTSDDV.existMaDTNT(maDTNT) == true) {
					break;
				}
				else {
					System.out.println("Mã điều trị nội trú nhập vào không tồn tại trong bảng DT_NoiTru. Mời nhập lại!");
				}
			}
			else {
				System.out.println("Mã điều trị nội trú nhập vào không đúng định dạng. Mời nhập lại!");
			}
		} while (true);

		// Nhập vào MÃ DỊCH VỤ
		String maDV = "";
		do {
			System.out.println("Nhập vào mã dịch vụ (có định dạng DVxxx với x là số): ");
			maDV = sc.nextLine();
			if (ValidateDV_CTSDDV.formatMaDV(maDV) == true) {
				if (ValidateDV_CTSDDV.existMaDV(maDV) == true) {
					break;
				} else {
					System.out.println("Mã dịch vụ nhập vào không tồn tại trong bảng DICHVU. Mời nhập lại!");
				}
			} else {
				System.out.println("Mã dịch vụ nhập vào không đúng định dạng. Mời nhập lại!");
			}
		} while (true);

		// Nhập vào NGÀY SỬ DỤNG dịch vụ
		String ngaySD = "";
		Date dateSD = new Date();
		do {
			System.out.println("Nhập vào ngày sử dụng dịch vụ (có định dạng yyyy/mm/dd): ");
			ngaySD = sc.nextLine();
			if (ValidateDV_CTSDDV.formatDate(ngaySD) == true) {
				try {
					dateSD = new SimpleDateFormat("yyyy/MM/dd").parse(ngaySD);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;
			}
			else {
				System.out.println("Ngày sử dụng nhập vào không đúng định dạng. Mời nhập lại!");
			}
		} while (true);

		ChiTietSDDV SD = new ChiTietSDDV(maSD, maDTNT, maDV, dateSD);
		int result = ServicesCTSD.insertCTSD(SD);
		if (result != 0) {
			System.out.println("Insert dữ liệu thành công");
		} else {
			System.out.println("Insert dữ liệu không thành công");
		}
	}

	// Nhập dữ liệu để cập nhật ở bảng CHITIET_SDDV
	public static void updateCTSD() {

		list = ServicesDP.listSDDV();
		System.out.println(list.toString());
		
		// Nhập vào MÃ SỬ DỤNG dịch vụ muốn cập nhật
		String maSD = "";
		do {
			System.out.println("Nhập vào mã sử dụng muốn cập nhật (có định dạng SDxxxx với x là số): ");
			maSD = sc.nextLine();
			if (ValidateDV_CTSDDV.formatMaSD(maSD) == true) {
				if (ValidateDV_CTSDDV.existMaSD(maSD) == true) {
					break;
				} else {
					System.out.println("Mã sử dụng không tồn tại trong bảng CHITIET_SDDV. Mời nhập lại!");
				}
			} else {
				System.out.println("Mã sử dụng nhập vào không đúng định dạng. Mời nhập lại!");
			}
		} while (true);

		// Nhập vào MÃ ĐIỀU TRỊ NỘI TRÚ
		String maDTNT = "";
		do {
			System.out.println("Nhập vào mã điều trị nội trú cập nhật (có định dạng NTxxxx với x là số): ");
			maDTNT = sc.nextLine();
			if (ValidateDV_CTSDDV.formatMaDTNT(maDTNT) == true) {
				if (ValidateDV_CTSDDV.existMaDTNT(maDTNT) == true) {
					break;
				}
				else {
					System.out.println("Mã điều trị nội trú nhập vào không tồn tại trong bảng DT_NoiTru. Mời nhập lại!");
				}
			}
			else {
				System.out.println("Mã điều trị nội trú nhập vào không đúng định dạng. Mời nhập lại!");
			}
		} while (true);

		// Nhập vào MÃ DỊCH VỤ
		String maDV = "";
		do {
			System.out.println("Nhập vào mã dịch vụ cập nhật (có định dạng DVxxx với x là số): ");
			maDV = sc.nextLine();
			if (ValidateDV_CTSDDV.formatMaDV(maDV) == true) {
				if (ValidateDV_CTSDDV.existMaDV(maDV) == true) {
					break;
				} else {
					System.out.println("Mã dịch vụ nhập vào không tồn tại trong bảng DICHVU. Mời nhập lại!");
				}
			} else {
				System.out.println("Mã dịch vụ nhập vào không đúng định dạng. Mời nhập lại!");
			}
		} while (true);

		// Nhập vào NGÀY SỬ DỤNG dịch vụ
		String ngaySD = "";
		Date dateSD = new Date();
		do {
			System.out.println("Nhập vào ngày sử dụng dịch vụ cập nhật (có định dạng yyyy/mm/dd): ");
			ngaySD = sc.nextLine();
			if (ValidateDV_CTSDDV.formatDate(ngaySD) == true) {
				try {
					dateSD = new SimpleDateFormat("yyyy/MM/dd").parse(ngaySD);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;
			}
			else {
				System.out.println("Ngày sử dụng nhập vào không đúng định dạng. Mời nhập lại!");
			}
		} while (true);

		ChiTietSDDV SD = new ChiTietSDDV(maSD, maDTNT, maDV, dateSD);
		int result = ServicesCTSD.updateCTSD(SD);
		if (result != 0) {
			System.out.println("Update dữ liệu thành công");
		} else {
			System.out.println("Update dữ liệu không thành công");
		}
	}

	// Nhập dữ liệu để xóa ở bảng CHITIET_SDDV
	public static void deleteCTSD() {
		
		list = ServicesDP.listSDDV();
		System.out.println(list.toString());

		// Nhập vào MÃ SỬ DỤNG dịch vụ muốn cập nhật
		String maSD = "";
		do {
			System.out.println("Nhập vào mã sử dụng muốn xóa (có định dạng SDxxxx với x là số): ");
			maSD = sc.nextLine();
			if (ValidateDV_CTSDDV.formatMaSD(maSD) == true) {
				if (ValidateDV_CTSDDV.existMaSD(maSD) == true) {
					break;
				} else {
					System.out.println("Mã sử dụng không tồn tại trong bảng CHITIET_SDDV. Mời nhập lại!");
				}
			} else {
				System.out.println("Mã sử dụng nhập vào không đúng định dạng. Mời nhập lại!");
			}
		} while (true);

		int result = ServicesCTSD.deleteCTSD(maSD);
		if (result != 0) {
			System.out.println("Delete dữ liệu thành công");
		} else {
			System.out.println("Delete dữ liệu không thành công");
		}
	}
}
