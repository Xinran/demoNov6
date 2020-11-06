package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Row {
    private String _rev;
    private String _id;
    private List<String> headers;
    private List<String> rowcontent;

    public Row(List<String> headers, List<String> rowcontent){
        this._id = UUID.randomUUID().toString();
        this.headers = headers;
        this.rowcontent = rowcontent;
    }

    public String get_id() {
        return _id;
    }
}
