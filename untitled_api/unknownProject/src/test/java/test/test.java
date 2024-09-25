package test;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class test {

    @Test
    public void test234(){

        File file = new File("/Users/bongjunhyeong/Desktop/무제폴더/기타(파일)/exceltestFiletrans.xlsx");

        if(!file.exists()){
            file.mkdirs();
        }

        System.out.println("test");
    }

    @Test
    public void copy() throws IOException {

        String originPath = "/Users/bongjunhyeong/Desktop/무제폴더/기타(파일)/exceltestFiletrans.xlsx";
        String targetPath = "src/test/resources/test.xlsx";

        File originFile = new File(originPath);
        File targetFile = new File(targetPath);

        Assertions.assertAll(() -> Files.copy(originFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING));

    }

    @Test
    public void test123() {

        List<Map<String, Object>> dataList = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("/Users/bongjunhyeong/Desktop/무제폴더/기타(파일)/exceltestFiletrans.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            Row hederRow = rowIterator.next();
            List<String> headers = new ArrayList<>();
            for (Cell cell : hederRow) {
                headers.add(cell.getStringCellValue());
            }

            while(rowIterator.hasNext()){
                Row row = rowIterator.next();
                Map<String, Object> rowData = new HashMap<>();
                for(int i=0; i<headers.size(); i++){
                    Cell cell = row.getCell(i);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING :
                                System.out.println(cell.getStringCellValue());
                                rowData.put("11", cell.getStringCellValue());
                                break;
                            case NUMERIC :
                                if(DateUtil.isCellDateFormatted(cell)) {
                                    rowData.put("22", cell.getDateCellValue());
                                }else {
                                    rowData.put("33", cell.getNumericCellValue());
                                }
                                System.out.println(cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                rowData.put("44", cell.getBooleanCellValue());
                                break;
                            default:
                                rowData.put("55", null);
                        }
                    } else {
                        rowData.put("66", null);
                    }
                }
                dataList.add(rowData);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(dataList);

        for(Map<String, Object> data : dataList){
            System.out.println(data);
        }


    }

}
