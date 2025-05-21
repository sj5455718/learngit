package com.haiyisoft.cloud.api;
import java.io.File;
import java.util.regex.Pattern;

public class ChineseFileFinder {

    // 检查字符串是否仅包含中文字符
    private static boolean isChinese(String str) {
        String regex = "^[\\u4e00-\\u9fa5]+$";
        return Pattern.matches(regex, str);
    }

    // 检查字符串是否无后缀
    private static boolean hasNoExtension(String str) {
        return !str.contains(".");
    }

    // 递归查找符合条件的文件或目录
    public static void findChineseFiles(File directory) {
        if (directory == null || !directory.exists() || !directory.isDirectory()) {
            System.out.println("指定的路径不存在或不是一个目录");
            return;
        }

        File[] entries = directory.listFiles();
        if (entries != null) {
            for (File entry : entries) {
                String name = entry.getName();
                if (isChinese(name) && hasNoExtension(name)) {
                    System.out.println(entry.getAbsolutePath());
                }
                if (entry.isDirectory()) {
                    findChineseFiles(entry);
                }
            }
        }
    }

    public static void main(String[] args) {
        File directory = new File("D:\\workspace-temp\\gadas2");
        findChineseFiles(directory);
    }
}    