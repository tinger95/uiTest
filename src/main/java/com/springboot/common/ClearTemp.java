package com.springboot.common;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class ClearTemp {
    public static void clearTempFile() throws Exception {

        try {
            File objTempFolder = new File(System.getProperty("java.io.tmpdir"));
            for (File objEach : objTempFolder.listFiles()) {
                if (objEach.getName().toString().toLowerCase().contains("scoped_dir")) {
                    if (objEach.isDirectory()) {
                        FileUtils.deleteDirectory(objEach);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
