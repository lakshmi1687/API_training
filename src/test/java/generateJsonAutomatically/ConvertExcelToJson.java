package generateJsonAutomatically;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.json.JSONArray;

public class ConvertExcelToJson {
	@Test
	public void convertExcelDataToJson() {
        String filePath = ".//testdata//userdata.xlsx";

        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            JSONArray jsonArray = new JSONArray();
            
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                JSONObject jsonObject = new JSONObject();

                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    String columnName = sheet.getRow(0).getCell(j).getStringCellValue();

                    if (cell != null) {
                        CellType cellType = cell.getCellType();
                        Object cellValue = null;

                        if (cellType == CellType.STRING) {
                            cellValue = cell.getStringCellValue();
                            
                        } else if (cellType == CellType.NUMERIC) {
                            cellValue = cell.getNumericCellValue();
                            
                        } else if (cellType == CellType.BOOLEAN) {
                            cellValue = cell.getBooleanCellValue();
                            
                        }

                        jsonObject.put(columnName, cellValue);
                    }
                }

                jsonArray.put(jsonObject);
            }

            System.out.println(jsonArray.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

