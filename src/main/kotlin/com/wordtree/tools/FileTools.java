package com.wordtree.tools;

import java.io.File;
import java.net.URL;

public class FileTools {
    public static String getResource(String name){
        return FileTools.class.getClassLoader().getResource("static")+name;
    }

    public static URL getResource(){
        return FileTools.class.getClassLoader().getResource("img");
    }

    public static String getFileExtension(File file) {
        String extension = "";
        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                if (name.lastIndexOf(".") != -1){
                    extension = name.substring(name.lastIndexOf("."));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            extension = "";
        }
        return extension;
    }

    public static File createFile(String src) throws Exception {
        File file = null;
        String fileChoose = ReadUtil.getPropertie("fileChoose");
        if (fileChoose != null && !fileChoose.equals("")){
            file = new File(fileChoose);
        }
        if (src != null){
            file = new File(src);
            if (!file.exists()) {
                file.mkdirs();
            }
        }else if (file == null){
            file = new File("D:/wordtree");
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return file;
    }
}
