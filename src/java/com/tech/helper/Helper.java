package com.tech.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Helper {
    public static boolean deleteFile(String path){
        boolean flag = false;
        try {
            File file = new File(path);
            if (file.exists()) {
                flag = file.delete();
                if (flag) {
                    System.out.println("File deleted successfully: " + path);
                } else {
                    System.out.println("Failed to delete file: " + path);
                }
            } else {
                System.out.println("File not found: " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    // Save the uploaded file to the specified path
    public static boolean updateFile(InputStream is, String path){
        boolean flag = false;
        try {
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            
            File file = new File(path);
            // Ensure the directory exists
            file.getParentFile().mkdirs();
            
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(buffer);
                flag = true;
                System.out.println("File saved successfully: " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
