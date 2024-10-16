package test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class ExcelTest {

    @Test
    public void mappingTest() throws Exception {
        File file = new File("/Users/bongjunhyeong/Desktop/무제폴더/기타(파일)/danalent_다날엔터_IP 자체제작_202408_Online_report.xlsx");

        String jsonMapping = "{ \"mappings\": [\n" +
                        "    {\"csvTitle\": \"분배월\", \"variableName\": \"sttl_ym_cd\"},\n" +
                        "    {\"csvTitle\": \"서비스월\", \"variableName\": \"service_ym_cd\"},\n" +
                        "    {\"csvTitle\": \"서비스\", \"variableName\": \"store_name\"},\n" +
                        "    {\"csvTitle\": \"서비스상세\", \"variableName\": \"service\"},\n" +
                        "    {\"csvTitle\": \"앨범코드\", \"variableName\": \"osp_album_code\"},\n" +
                        "    {\"csvTitle\": \"트랙코드\", \"variableName\": \"osp_track_code\"},\n" +
                        "    {\"csvTitle\": \"권리사앨범코드\", \"variableName\": \"owner_album_code\"},\n" +
                        "    {\"csvTitle\": \"권리사트랙코드\", \"variableName\": \"owner_track_code\"},\n" +
                        "    {\"csvTitle\": \"UPC\", \"variableName\": \"upc\"},\n" +
                        "    {\"csvTitle\": \"ISRC\", \"variableName\": \"isrc\"},\n" +
                        "    {\"csvTitle\": \"앨범명\", \"variableName\": \"album_name\"},\n" +
                        "    {\"csvTitle\": \"트랙명\", \"variableName\": \"track_name\"},\n" +
                        "    {\"csvTitle\": \"아티스트명\", \"variableName\": \"artist_name\"},\n" +
                        "    {\"csvTitle\": \"다운로드\", \"variableName\": \"dn_cnt\"},\n" +
                        "    {\"csvTitle\": \"스트리밍\", \"variableName\": \"st_cnt\"},\n" +
                        "    {\"csvTitle\": \"복합수량\", \"variableName\": \"comb_cnt\"},\n" +
                        "    {\"csvTitle\": \"전체 판매금액\", \"variableName\": \"sell_price\"},\n" +
                        "    {\"csvTitle\": \"실 정산금액\", \"variableName\": \"sttl_price\"} ] }";

        try {

            JSONObject json = new JSONObject(jsonMapping);
            JSONArray mappings = json.getJSONArray("mappings");

            Workbook workbook = new XSSFWorkbook(new FileInputStream(file));

            Sheet sheet = workbook.getSheetAt(0);

            Row headerRow = sheet.getRow(0);
            Map<String, Integer> headerMap = new HashMap<>();
            Iterator<Cell> headerIterator = headerRow.cellIterator();
            while (headerIterator.hasNext()) {
                Cell cell = headerIterator.next();
                headerMap.put(cell.getStringCellValue(), cell.getColumnIndex());
            }

            List<Map<String, Object>> rowDataList = new ArrayList<>();

            for(int i=1; i<=sheet.getLastRowNum(); i++) {
                Row dataRow = sheet.getRow(i);
                Map<String, Object> rowData = new HashMap<>();

                for(int j=0; j<mappings.length(); j++) {
                    JSONObject mapping = mappings.getJSONObject(j);
                    String csvTitle = mapping.getString("csvTitle");
                    String variableName = mapping.getString("variableName");

                    if(headerMap.containsKey(csvTitle)) {
                        int colIndex = headerMap.get(csvTitle);
                        Cell cell = dataRow.getCell(colIndex);
                        Object cellValue = getCellValue(cell);

                        if(cellValue != null) {
                            rowData.put(variableName, cellValue);
                        }
                    }
                }
                if(!rowData.isEmpty()) {
                    rowDataList.add(rowData);
                }
            }

            System.out.println(rowDataList);

            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static Object getCellValue(Cell cell) {
        if(cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if(DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                }else {
                    return cell.getNumericCellValue();
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            default:
                return null;
        }
    }

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

        List<List<String>> dataList = new ArrayList<List<String>>();

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
                List<String> rowData = new ArrayList<>();
                for(int i=0; i<headers.size(); i++){
                    Cell cell = row.getCell(i);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING :
                                System.out.println(cell.getStringCellValue());
                                rowData.add(cell.getStringCellValue());
                                break;
                            case NUMERIC :
                                if(DateUtil.isCellDateFormatted(cell)) {
                                    rowData.add(String.valueOf(cell.getDateCellValue()));
                                }else {
                                    rowData.add(String.valueOf(cell.getNumericCellValue()));
                                }
                                System.out.println(cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                rowData.add(String.valueOf(cell.getBooleanCellValue()));
                                break;
                            default:
                                rowData.add(null);
                        }
                    } else {
                        rowData.add(null);
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

        for(List<String> data : dataList){
            System.out.println(data);
        }


    }

}
