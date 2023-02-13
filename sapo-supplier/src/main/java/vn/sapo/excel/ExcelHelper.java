package vn.sapo.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.supplier.dto.CreateSupplierParam;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    static String[] HEADER = {"Tên nhà cung cấp", "Mã nhà cung cấp", "Mã nhóm nhà cung cấp", "Email", "Điện thoại", "Website",
            "FAX", "Mã số thuế", "Mô tả", "Chính sách giá mặc định", "Kỳ hạn thanh toán mặc định", "Phương thức thanh toán mặc định",
            "Người liên hệ", "SDT người liên hệ", "Email người liên hệ", "Nhãn", "Địa chỉ 1", "Địa chỉ 2", "Tỉnh/Thành Phố",
            "Quận/Huyện", "Nợ hiện tại", "Tags"};

    static String SHEET = "DuLieuNhap";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<CreateSupplierParam> excelToSuppliers(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<CreateSupplierParam> suppliers = new ArrayList<CreateSupplierParam>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();

                CreateSupplierParam supplier = new CreateSupplierParam();
//                supplier.setEmployeeId(6);

                CreateAddressParam address = new CreateAddressParam();
                address.setProvinceId(-1);
                address.setDistrictId(-1);
                address.setWardId(-1);

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            supplier.setFullName(currentCell.getStringCellValue());
                            break;
                        case 1:
                            if (currentCell.getStringCellValue() == null)
                                supplier.setSupplierCode("SPL000" + supplier.getId());
                            supplier.setSupplierCode(currentCell.getStringCellValue());
                            break;
                        case 2:
//                                ma nhom nha cung cap
                            break;
                        case 3:
                            supplier.setEmail(currentCell.getStringCellValue());
                            break;
                        case 4:
                            supplier.setPhone(currentCell.getStringCellValue());
                            break;
                        case 5:
                            supplier.setWebsite(currentCell.getStringCellValue());
                            break;
                        case 6:
                            supplier.setFax(currentCell.getStringCellValue());
                            break;
                        case 7:
                            supplier.setTaxCode(currentCell.getStringCellValue());
                            break;
                        case 8:
                            supplier.setDescription(currentCell.getStringCellValue());
                            break;
                        case 9:
//                     chính sách giá mặc định
                            break;
                        case 10:
//                      Kỳ hạn thanh toán mặc định
                            break;
                        case 11:
                         supplier.setPaymentMethodId(currentCell.getStringCellValue());
                            break;
                        case 12:
                            address.setFullName(currentCell.getStringCellValue());
                            break;
                        case 13:
                            address.setPhoneNumber(currentCell.getStringCellValue());
                            break;
                        case 14:
                            address.setEmail(currentCell.getStringCellValue());
                            break;
                        case 15:
//                            nhãn
                            break;
                        case 16:
//                            địa chỉ 1
                            break;
                        case 17:
//                            địa chỉ 2
                            break;
                        case 18:
                            address.setProvinceName(currentCell.getStringCellValue());
                            break;
                        case 19:
                            address.setDistrictName(currentCell.getStringCellValue());
                            break;
                        case 20:
//                            nợ hiện tại
                            break;
                        case 21:
//                              tags
                            break;
                        default:
                            break;
                    }
                    cellIdx++;

                }
                supplier.setCreateAddressParam(address);
                suppliers.add(supplier);
            }
            workbook.close();
            return suppliers;

        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());

        }

    }

}