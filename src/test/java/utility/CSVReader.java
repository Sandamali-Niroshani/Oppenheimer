package utility;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<String> getNatidsFromCSV(String filePath) {
        List<String> natidList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Split CSV by comma
                if (values.length > 0) {
                    String natid = values[0].trim(); // Extract first column (natid)
                    natidList.add(natid);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return natidList;
    }
}