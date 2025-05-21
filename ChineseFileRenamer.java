package com.haiyisoft.cloud.api;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ChineseFileRenamer {

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
    private static void findChineseFiles(File directory, List<File> resultList) {
        if (directory == null || !directory.exists() || !directory.isDirectory()) {
            return;
        }

        File[] entries = directory.listFiles();
        if (entries != null) {
            for (File entry : entries) {
                String name = entry.getName();
                if (isChinese(name) && hasNoExtension(name)) {
                    resultList.add(entry);
                }
                if (entry.isDirectory()) {
                    findChineseFiles(entry, resultList);
                }
            }
        }
    }

    // 重命名文件，添加.txt后缀
    private static void renameFiles(List<File> filesToRename) {
        if (filesToRename.isEmpty()) {
            System.out.println("没有需要重命名的文件。");
            return;
        }

        System.out.println("找到 " + filesToRename.size() + " 个符合条件的文件/目录:");
        for (int i = 0; i < filesToRename.size(); i++) {
            System.out.println((i + 1) + ". " + filesToRename.get(i).getAbsolutePath());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\n是否要为这些文件/目录添加.txt后缀? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("y")) {
            int successCount = 0;
            int failureCount = 0;
            for (File file : filesToRename) {
                String newName = file.getName() + ".txt";
                File newFile = new File(file.getParent(), newName);
                if (file.renameTo(newFile)) {
                    System.out.println("成功: " + file.getAbsolutePath() + " -> " + newFile.getAbsolutePath());
                    successCount++;
                } else {
                    System.out.println("失败: " + file.getAbsolutePath() + " -> " + newFile.getAbsolutePath());
                    failureCount++;
                }
            }
            System.out.println("\n操作完成: 成功 " + successCount + " 个, 失败 " + failureCount + " 个");
        } else {
            System.out.println("操作已取消。");
        }
        scanner.close();
    }

    public static void main(String[] args) {
        File directory = new File("D:\\workspace-temp\\front_dev");
        List<File> filesToRename = new ArrayList<>();
        
        findChineseFiles(directory, filesToRename);
        renameFiles(filesToRename);
    }
}    