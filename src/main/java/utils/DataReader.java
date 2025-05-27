package utils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class DataReader {

    public List<Map<String, String>> getExcelData(String Path, String SheetName) throws IOException {
        List<Map<String, String>> list = new ArrayList();
        ExcelReader excelobject = new ExcelReader(Path);

        for (int i = 1; i < excelobject.getRowCount(SheetName); i++) {
            Map<String, String> mMap = new LinkedHashMap<>();
            for (int j = 0; j < excelobject.getColumnCount(SheetName, i); j++) {
                mMap.put(excelobject.getCellStringData(SheetName, 0, j), excelobject.getCellStringData(SheetName, i, j));
            }

            list.add(mMap);
        }
        return list;
    }

}

