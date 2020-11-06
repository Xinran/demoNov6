package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestReadCsv {

    /**
     * Test the header and rows are stored correctly in the array.
     */
    @Test
    public void TestHeadersRows() {
        Service s = new Service();
        assertEquals(4, s.getHeadersRows("odd").get(0).size());
        assertEquals(5, s.getHeadersRows("odd").get(1).size());
        assertEquals(5, s.getHeadersRows("even").get(1).size());
        assertEquals(10, s.getHeadersRows("").get(1).size());
    }
}
