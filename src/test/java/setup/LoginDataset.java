package setup;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginDataset {


//  Reads login test data (email & password) from a CSV file and returns it as a 2D Object array.
    public Object[][] getCSVData() throws IOException {
        String filePath = "./src/test/resources/users.csv";
   // Create a CSVParser to read the file
   // withFirstRecordAsHeader() means the first row is treated as header (column names: email, password)
        CSVParser csvParser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withFirstRecordAsHeader());

   // Temporary list to store each record as an Object[] (email, password)
        List<Object> data = new ArrayList<>();
   // Loop through each row (record) in the CSV file
        for (CSVRecord csvRecord : csvParser) {
       // Extract values based on column names from the header
            String email = csvRecord.get("email");
            String password = csvRecord.get("password");

       // Store the email and password as an Object[] and add it to the list
            data.add(new Object[]{email, password});
        }

        // Convert the list into a 2D Object array and return
        // Example return format: [ [email1, pass1], [email2, pass2], ... ]
        return data.toArray(new Object[0][]);
    }
}
