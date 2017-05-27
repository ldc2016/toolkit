package com.vip.own.utils;

import java.io.*;

/**
 * Created by dacheng.liu on 2017/5/26.
 */
public class FileReaderUtils {


    public static String getMetaData(BufferedReader reader){
        try {
            String metaData = reader.readLine();
            return metaData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedReader initFileReader(String path){
        try {
            File filePath = new File(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            return reader;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
