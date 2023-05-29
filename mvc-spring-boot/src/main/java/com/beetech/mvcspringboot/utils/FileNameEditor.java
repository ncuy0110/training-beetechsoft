package com.beetech.mvcspringboot.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileNameEditor {
    public static String appendDatetime(String fileName) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");
        String formattedDate = formatter.format(new Date());

        int dotIndex = fileName.lastIndexOf(".");
        String extension = "";
        String newFilename = fileName;
        if (dotIndex != -1) {
            extension = fileName.substring(dotIndex);
            newFilename = fileName.substring(0, dotIndex);
        }

        return newFilename + "_" + formattedDate + extension;
    }
}
