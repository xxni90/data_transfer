package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class Start {

    public static void main(String[] args) throws IOException {
        DHLTemplate dhl = new DHLTemplate();
        dhl.init();
        CsvWriter csvWriter = new CsvWriter("/Users/mishi/Documents/workspace/data-transfer/files/target.csv", ',',
                                            Charset.forName("UTF-8"));
        InputStream is = null;
        CsvReader reader = null;
//        is = new FileInputStream("/Users/mishi/Documents/workspace/data-transfer/files/20751CI28_DHL.csv");

        is = new FileInputStream("/Users/mishi/Documents/workspace/data-transfer/files/20751CI28_DHL.csv");

        reader = new CsvReader(is, ',', Charset.forName("UTF-8"));

        while (reader.readRecord()) {
            String[] row = reader.getValues();
            String charge = "";
            if (row[4].trim().equalsIgnoreCase("SM Parcel Plus Expedited".trim())) {
                int qy = Integer.valueOf(row[5].trim().substring(row[5].length() - 2, row[5].length()));
                if (qy == 2) {
                    qy = 1;
                }
                int wight = Integer.valueOf(row[6].trim()) / 16;
                charge = String.format("%.2f", dhl.getPpe().get(qy).get(wight));

            } else if (row[4].trim().equalsIgnoreCase("SM Parcels Expedited".trim())) {
                int wight = Integer.valueOf(row[6].trim());
                charge = String.format("%.2f", dhl.getPe().get(wight));
            } else if (row[4].trim().equalsIgnoreCase("SM Parcels Ground".trim())) {
                int wight = Integer.valueOf(row[6].trim());
                charge = String.format("%.2f", dhl.getPg().get(wight));
            } else if (row[4].trim().equalsIgnoreCase("SM Parcel Plus Ground".trim())) {
                int qy = Integer.valueOf(row[5].trim().substring(row[5].length() - 2, row[5].length()));
                int wight = Integer.valueOf(row[6].trim()) / 16;
                if (qy == 2) {
                    qy = 1;
                }
                charge = String.format("%.2f", dhl.getPpg().get(qy).get(wight));
            } else {

            }
            if (charge.equals("")) {
                csvWriter.writeRecord(row);
            } else {
                String[] row_new = new String[row.length];
                for (int i = 0; i < row.length; i++) {
                    row_new[i] = row[i];
                }
                row_new[row_new.length - 1] = charge;
                csvWriter.writeRecord(row_new);
            }

        }
        csvWriter.close();
    }
}
