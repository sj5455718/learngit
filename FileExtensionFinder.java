package com.haiyisoft.cloud.api;
import java.io.File;
import java.util.*;

public class FileExtensionFinder {

    // 递归查找目录下所有文件的后缀
    private static void findFileExtensions(File directory, Set<String> extensions) {
        if (directory == null || !directory.exists() || !directory.isDirectory()) {
            return;
        }

        File[] entries = directory.listFiles();
        if (entries != null) {
            for (File entry : entries) {
                if (entry.isDirectory()) {
                    findFileExtensions(entry, extensions);
                } else {
                    String fileName = entry.getName();
                    int dotIndex = fileName.lastIndexOf('.');
                    if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
                        String extension = fileName.substring(dotIndex + 1).toLowerCase();
                        extensions.add(extension);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        File directory = new File("D:\\workspace-temp\\JOPMS_WEB_YKZX_WFW");
        Set<String> extensions = new LinkedHashSet<>(); // 使用LinkedHashSet保持插入顺序

        findFileExtensions(directory, extensions);

        System.out.println("目录 " + directory.getAbsolutePath() + " 下的文件后缀有:");
        if (extensions.isEmpty()) {
            System.out.println("未找到有后缀的文件。");
        } else {
            extensions.forEach(System.out::println);
        }
    }
}    