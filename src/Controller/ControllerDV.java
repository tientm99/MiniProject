package Controller;

import java.util.ArrayList;

import java.util.Scanner;

import Entity.DichVu;
import Services.ServiceDV;
import Services.ServicesDP;
import Util.ValidateDV_CTSDDV;

public class ControllerDV {
	static Scanner sc = new Scanner(System.in);
	static ArrayList<DichVu> list = new ArrayList<>();

	// Nhập dữ liệu để thêm vào bảng DỊCH VỤ
	public static void insertDV() {

		// Nhập mã dịch vụ
		String maDV = "";
		do {
			System.out.println("Nhập vào mã dịch vụ (có định dạng DVxxx với x là số): ");
			maDV = sc.nextLine();
			if (ValidateDV_CTSDDV.formatMaDV(maDV) == true) {
				if (ValidateDV_CTSDDV.existMaDV(maDV) == false) {
					break;
				} else {
					System.out.println("Mã dịch vụ vừa nhập đã tồn tại trong bảng DICHVU. Mời nhập lại!");
				}
			} else {
				System.out.println("Mã dịch vụ nhập vào không đúng định dạng. Mời nhập lại!");
			}
		} while (true);

		// Nhập tên dịch vụ
		System.out.println("Nhập vào tên dịch vụ: ");
		String tenDV = sc.nextLine();

		// Nhập giá của dịch vụ
		int gia;
		do {
			System.out.println("Nhập vào giá của dịch vụ: ");
			try {
				gia = Integer.parseInt(sc.nextLine());
				if (ValidateDV_CTSDDV.gia(gia) == true) {
					break;
				} else {
					System.out.println("Giá của dịch vụ không được chứa giá trị âm. Mời nhập lại!");
				}
			} catch (NumberFormatException e) {
				System.out.println("Giá của dịch vụ nhập vào không đúng. Mời nhập lại!");
			}
		} while (true);

		DichVu DV = new DichVu(maDV, tenDV, gia);
		int result = 0;
		result = ServiceDV.insertDV(DV);
		if (result != 0) {
			System.out.println("Insert dữ liệu thành công");
		} else {
			System.out.println("Insert dữ liệu không thành công");
		}
	}

	// Nhập dữ liệu để cập nhật dữ liệu ở bảng DỊCH VỤ
	public static void updateDV() {

		list = ServicesDP.listDV();
		System.out.println(list.toString());
		
		// Nhập mã dịch vụ
		String maDV = "";
		do {
			System.out.println("Nhập vào mã dịch vụ muốn cập nhật (có định dạng DVxxx với x là số): ");
			maDV = sc.nextLine();
			if (ValidateDV_CTSDDV.formatMaDV(maDV) == true) {
				if (ValidateDV_CTSDDV.existMaDV(maDV) == true) {
					break;
				} else {
					System.out.println("Mã dịch vụ vừa nhập không tồn tại trong bảng DICHVU. Mời nhập lại!");
				}
			} else {
				System.out.println("Mã dịch vụ nhập vào không đúng định dạng. Mời nhập lại!");
			}
		} while (true);

		// Nhập tên dịch vụ
		System.out.println("Nhập vào tên dịch vụ muốn cập nhật: ");
		String tenDV = sc.nextLine();

		// Nhập giá của dịch vụ
		int gia;
		do {
			System.out.println("Nhập vào giá của dịch vụ: ");
			try {
				gia = Integer.parseInt(sc.nextLine());
				if (ValidateDV_CTSDDV.gia(gia) == true) {
					break;
				} else {
					System.out.println("Giá của dịch vụ không được chứa giá trị âm. Mời nhập lại!");
				}
			} catch (NumberFormatException e) {
				System.out.println("Giá của dịch vụ nhập vào không đúng. Mời nhập lại!");
			}
		} while (true);
		DichVu DV = new DichVu(maDV, tenDV, gia);
		int result = 0;
		result = ServiceDV.updateDV(DV);
		if (result != 0) {
			System.out.println("Update dữ liệu thành công");
		} else {
			System.out.println("Update dữ liệu không thành công");
		}
	}

	// Nhập dữ liệu để xóa dữ liệu ở bảng DỊCH VỤ
	public static void deleteDV() {
		
		list = ServicesDP.listDV();
		System.out.println(list.toString());

		// Nhập vào mã dịch vụ muốn xóa
		String maDV = "";
		do {
			System.out.println("Nhập vào mã dịch vụ muốn xóa (có định dạng DVxxx với x là số): ");
			maDV = sc.nextLine();
			if (ValidateDV_CTSDDV.formatMaDV(maDV) == true) {
				if (ValidateDV_CTSDDV.existMaDV(maDV) == true) {
					if(ValidateDV_CTSDDV.validateMaDV(maDV) == false) {
						break;
					}
					else {
						System.out.println("Mã dịch vụ đang tồn tại trong bảng CHITIET_SDDV. Mời nhập lại!");
					}
				} else {
					System.out.println("Mã dịch vụ vừa nhập không tồn tại trong bảng DICHVU. Mời nhập lại!");
				}
			} else {
				System.out.println("Mã dịch vụ nhập vào không đúng định dạng. Mời nhập lại!");
			}
		} while (true);

		int result = 0;
		result = ServiceDV.deleteDV(maDV);
		if (result != 0) {
			System.out.println("Delete dữ liệu thành công");
		} else {
			System.out.println("Delete dữ liệu không thành công");
		}
	}
}
