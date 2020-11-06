package com.example.demo;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Service {

    private static final Logger logger = LoggerFactory.getLogger(Service.class);
    private static final String CLOUDANT_DB = "demodata";
    private static final String file = "./PracticeFile.csv";

    /**
     * save the file to DB with UID, headers and the content
     * @param headers the headers array list
     * @param rows the rows as arraylist, which has each string as a row
     */
    public void saveRawFileToDB(CloudantClient cloudantClient, List<String> headers, List<String> rows) {
        Database db = cloudantClient.database(CLOUDANT_DB, true);
        Row row = new Row(headers, rows);
        db.save(row);
    }

    public ArrayList<List<String>> getHeadersRows(String oddeven) {
        ArrayList<List<String>> headersrows = new ArrayList<>();
        BufferedReader reader;
        int count = 1;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            List<String> headers = Arrays.asList(line.trim().split(","));
            List<String> rows = new ArrayList<>();
            line = reader.readLine();
            while (line != null) {
                if( oddeven.equals("odd")) {
                    if(count % 2 == 1)
                        rows.add(line);
                } else if(oddeven.equals("even")) {
                    if(count % 2 == 0)
                        rows.add(line);
                } else rows.add(line);
                count++;
                line = reader.readLine();
            }
            headersrows.add(headers);
            headersrows.add(rows);
            reader.close();
        } catch (FileNotFoundException e) {
            logger.error("The file is not found.");
        } catch (Exception e) {
            logger.error("The input is not a valid file.");
        }
        return headersrows;
    }
}
