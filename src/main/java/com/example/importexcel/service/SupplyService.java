package com.example.importexcel.service;

import com.example.importexcel.model.Product;
import com.example.importexcel.model.Supplier;
import com.example.importexcel.model.Supply;
import com.example.importexcel.repository.ProductRepository;
import com.example.importexcel.repository.SupplierRepository;
import com.example.importexcel.repository.SupplyRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class SupplyService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplyRepository supplyRepository;

    public void importExcel(MultipartFile file) throws IOException {

        Workbook workbook = new XSSFWorkbook(file.getInputStream());

        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            int rowNumber = i + 1;

            Row row = sheet.getRow(i);

            if (row == null || row.getCell(0) == null
                    || row.getCell(0).getStringCellValue().trim().isEmpty()) {
                continue;
            }

            String productName = row.getCell(0)
                    .getStringCellValue()
                    .trim();

            Product product = productRepository
                    .findByName(productName)
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Row " + rowNumber
                                            + ": Product not found: "
                                            + productName));


            String supplierName = row.getCell(1)
                    .getStringCellValue()
                    .trim();


            Supplier supplier = supplierRepository
                    .findByName(supplierName)
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Row " + rowNumber
                                            + ": Supplier not found: "
                                            + supplierName));


            Supply supply = new Supply();

            supply.setProduct(product);
            supply.setSupplier(supplier);

            supply.setQty(
                    (int) row.getCell(2)
                            .getNumericCellValue()
            );

            supply.setBuyPrice(
                    row.getCell(3)
                            .getNumericCellValue()
            );

            supply.setSupplyDate(
                    row.getCell(4)
                            .getLocalDateTimeCellValue()
                            .toLocalDate()
            );


            supplyRepository.save(supply);
        }

        workbook.close();
    }
}
