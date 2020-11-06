package com.example.demo;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private CloudantClient cloudantClient;

    @GetMapping("/readcsv")
    @ResponseBody
    public List<String> readCSV(@RequestParam String type) {
        //check parameter
        ArrayList<List<String>> headersrows = new ArrayList<>();
        if(!type.equals("odd") && !type.equals("even") && !(type.length()==0)){
            logger.error("The parameter for odd or even is not valid");
        } else {
            Service s = new Service();
            headersrows = s.getHeadersRows(type);
            s.saveRawFileToDB(cloudantClient, headersrows.get(0), headersrows.get(1));
        }
        return  headersrows.get(1);
    }

}
