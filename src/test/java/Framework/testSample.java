package Framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class testSample {
    public static void main(String[] args) throws IOException {
//        DataDriven d = new DataDriven();
//        ArrayList data = d.getData("Purchase");
//        System.out.println(data);

        ExcelDataExtractor e = new ExcelDataExtractor();
        ArrayList data1 = e.extractData();
//        System.out.println(data1);
        data1.stream().forEach(s-> System.out.println(s));
    }
}
