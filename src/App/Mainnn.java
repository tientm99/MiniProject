package App;

import java.util.Scanner;

import Controller.ControllerCTSD;
import Controller.ControllerDP;
import Controller.ControllerDV;
import Util.ValidateDV_CTSDDV;

public class Mainnn {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("1. Thêm, sửa, xóa thông tin vào bảng dịch vụ: ");
			System.out.println("2. Thêm, sửa, xóa thông tin vào bảng chi tiết sử dụng dịch vụ: ");
			System.out.println("3. Thống kê từng loại dịch vụ được sử dụng nhiều nhất theo từng tháng: ");
			System.out.println(
					"4. Tìm bệnh nhân đã sử dụng dịch vụ X(X nhập từ bàn phím) trong khoảng thời gian Y(Y nhập từ bàn phím): ");
			System.out.println("5. Hiển thị tổng doanh thu của dịch vụ theo từng năm");
			System.out.println("6. Hiển thị số lượng bệnh nhân sử dụng dịch vụ theo từng tháng của năm");
			System.out.println("7. Hiển thị tổng doanh thu theo từng tháng của năm(Tháng nào không có doanh thu thì cho bằng 0)");
			System.out.println("8. Thoát");
			System.out.println("----------------------------------------------------------------");
			System.out.println("XIN MỜI CHỌN CHỨC NĂNG: ");
			try {
				int num = Integer.parseInt(sc.nextLine());
				if (ValidateDV_CTSDDV.validateMain(num) == true) {
					switch (num) {
					case 1:
						DICHVU();
						break;
					case 2:
						CHITIET_SDDV();
						break;
					case 3:
						ControllerDP.displayDV();
						break;
					case 4:
						ControllerDP.displayBN();
						break;
					case 5:
						ControllerDP.displayDT();
						break;
					case 6:
						ControllerDP.displayBNDV();
						break;
					case 7:
						ControllerDP.displayTDThu();
						break;
					}
					if (num == 8) {
						break;
					}
				} else {
					System.out.println("Nhập vào menu không đúng. Mời nhập lại!");
				}
			} catch (NumberFormatException e) {
				System.out.println("Nhập vào menu không đúng. Mời nhập lại!");
			}

		} while (true);

	}

	public static void DICHVU() {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("1. Thêm thông tin vào bảng dịch vụ: ");
			System.out.println("2. Cập nhật thông tin bảng dịch vụ: ");
			System.out.println("3. Xóa thông tin bảng dịch vụ: ");
			System.out.println("4. Thoát");
			System.out.println("----------------------------------------------------------------");
			System.out.println("XIN MỜI CHỌN CHỨC NĂNG: ");
			try {
				int num = Integer.parseInt(sc.nextLine());
				if (ValidateDV_CTSDDV.validateMenu(num) == true) {
					if (ValidateDV_CTSDDV.checkDV() == true) {
						switch (num) {
						case 1:
							ControllerDV.insertDV();
							break;
						case 2:
							ControllerDV.updateDV();
							break;
						case 3:
							ControllerDV.deleteDV();
							break;
						}
						if (num == 4) {
							break;
						}
					} else {
						if (num == 1) {
							ControllerDV.insertDV();
						} else {
							if (num == 4) {
								break;
							} else {
								System.out.println("Chưa có dữ liệu trong bảng DICHVU. Mời chọn chức năng insert");
							}
						}
					}
				} else {
					System.out.println("Nhập vào menu không đúng. Mời nhập lại!");
				}
			} catch (NumberFormatException e) {
				System.out.println("Nhập vào menu không đúng. Mời nhập lại!");
			}

		} while (true);
	}

	public static void CHITIET_SDDV() {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("1. Thêm thông tin vào bảng chi tiết sử dụng dịch vụ: ");
			System.out.println("2. Cập nhật thông tin bảng chi tiết sử dụng dịch vụ: ");
			System.out.println("3. Xóa thông tin bảng chi tiết sử dụng dịch vụ: ");
			System.out.println("4. Thoát");
			System.out.println("----------------------------------------------------------------");
			System.out.println("XIN MỜI CHỌN CHỨC NĂNG: ");
			try {
				int num = Integer.parseInt(sc.nextLine());
				if (ValidateDV_CTSDDV.validateMenu(num) == true) {
					if (ValidateDV_CTSDDV.checkSDDV() == true) {
						switch (num) {
						case 1:
							ControllerCTSD.insertCTSD();
							break;
						case 2:
							ControllerCTSD.updateCTSD();
							break;
						case 3:
							ControllerCTSD.deleteCTSD();
							break;
						}
						if (num == 4) {
							break;
						}
					} else {
						if (num == 1) {
							ControllerCTSD.insertCTSD();
						} else {
							if (num == 4) {
								break;
							} else {
								System.out
										.println("Chưa có dữ liệu trong bảng CHITIET_SDDV. Mời chọn chức năng insert");
							}
						}
					}
				} else {
					System.out.println("Nhập vào menu không đúng. Mời nhập lại!");
				}
			} catch (NumberFormatException e) {
				System.out.println("Nhập vào menu không đúng. Mời nhập lại!");
			}

		} while (true);
	}
}
