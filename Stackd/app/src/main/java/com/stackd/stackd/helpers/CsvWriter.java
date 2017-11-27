package com.stackd.stackd.helpers;

/**
 * Created by lana on 11/27/17.
 */

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class CsvWriter {
    public CsvWriter() throws FileNotFoundException {

        PrintWriter pw = new PrintWriter(new File(Environment.getExternalStorageDirectory().toString() +"/Export_test.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("id");
        sb.append(',');
        sb.append("Name");
        sb.append('\n');

        sb.append("1");
        sb.append(',');
        sb.append("Prashant Ghimire");
        sb.append('\n');

        pw.write(sb.toString());
        pw.close();
        System.out.println("done!");
    }

}