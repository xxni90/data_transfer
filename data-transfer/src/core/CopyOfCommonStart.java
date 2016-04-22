package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class CopyOfCommonStart {

    public static void main(String[] args) throws IOException {
        CommonTemplate mtl = new CommonTemplate("mtl");
        mtl.init();

        CsvWriter csvWriter = new CsvWriter("/Users/mishi/Documents/workspace/data-transfer/files/target1.csv", ',',
                                            Charset.forName("UTF-8"));
        InputStream is = null;
        CsvReader reader = null;

        is = new FileInputStream("/Users/mishi/Documents/workspace/data-transfer/files/0302/220.csv");

        reader = new CsvReader(is, ';', Charset.forName("UTF-8"));

        String[] oneRow = new String[] {};

        // 解析表头

        String[] row = reader.getValues();
        for (int i = 0; reader.readRecord(); i++) {
            oneRow = reader.getValues();
            String charge = String.format("%.2f",
                                          mtl.getTemplate()
                                             .get(Integer.valueOf(oneRow[7]))
                                             .get(Integer.valueOf(oneRow[6])));
            oneRow[8] = charge;
            csvWriter.writeRecord(oneRow);

        }
        csvWriter.close();
    }
}
