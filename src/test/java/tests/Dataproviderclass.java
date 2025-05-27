package tests;

import org.testng.annotations.DataProvider;
import utils.DataReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Dataproviderclass {

    @DataProvider(name = "SearchTerm")
    public Object[][] get_search_term() throws IOException {
        DataReader datareader = new DataReader();
        List<Map<String, String>> maze = datareader.getExcelData("src/test/java/Data/TestData.xlsx", "SearchData");
        // Rows - Number of times your test has to be repeated.
        // Columns - Number of parameters in test data.
        Object[][] logindetails = new Object[maze.size()][1];
        int i = 0;
        for (Map<String, String> mmap : maze) {
            logindetails[i][0] = mmap;
            i++;
        }

        return logindetails;
    }

    @DataProvider(name = "SearchCenter")
    public Object[][] searchCenter() throws IOException {
        DataReader datareader = new DataReader();
        List<Map<String, String>> maze = datareader.getExcelData("src/test/java/Data/TestData.xlsx", "SearchCenter");
        // Rows - Number of times your test has to be repeated.
        // Columns - Number of parameters in test data.
        Object[][] logindetails = new Object[maze.size()][1];
        int i = 0;
        for (Map<String, String> mmap : maze) {
            logindetails[i][0] = mmap;
            i++;
        }

        return logindetails;
    }
}
