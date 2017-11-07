package com.kivii.grabdoll.util;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class FileUtils {

    /**
     * 清除指定目录下的文件
     *
     * @param dir，要删除的目录
     * @return 返回删除的文件数
     */
    public static void clearFolder(File dir) {
        if (dir != null && dir.isDirectory()) {
            for (File child : dir.listFiles()) {
                if (child.isDirectory()) {
                    clearFolder(child);
                    child.delete();
                } else {
                    delete(child);
                }
            }
        }
    }

    /**
     * 获取指定目录下的所有文件大小
     *
     * @param dir
     * @return
     */
    public static long getFolderSize(File dir) {
        long size = 0;
        if (dir != null && dir.isDirectory()) {
            for (File child : dir.listFiles()) {
                if (child.isDirectory()) {
                    size += getFolderSize(child);
                } else {
                    size += child.length();
                }
            }
        }
        return size;
    }

    /**
     * 获取文件名后缀
     *
     * @param fileName
     * @return
     */
    public static String getFileSuffix(String fileName) {
        if (TextUtils.isEmpty(fileName))
            return "";
        int index = fileName.lastIndexOf(".");
        if (index < 0)
            return "";
        return fileName.substring(index);
    }

    /**
     * 删除文件
     *
     * @param file，要删除的文件
     * @return
     */
    public static boolean delete(File file) {
        if (file.exists()) {
            file.setWritable(true);
            return file.delete();
        }
        return true;
    }

    /**
     * 复制单个文件
     *
     * @param srcPath  String 原文件路径 如：c:/fqf.txt
     * @param destPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static boolean copy(String srcPath, String destPath) {
        try {
            File srcFile = new File(srcPath);
            if (srcFile.exists()) { // 文件存在时
                File destFile = new File(destPath);
                delete(destFile);
                destFile.createNewFile();

                InputStream inStream = new FileInputStream(srcPath); // 读入原文件
                FileOutputStream outStream = new FileOutputStream(destPath);
                try {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inStream.read(buffer)) != -1) {
                        outStream.write(buffer, 0, length);
                    }
                } finally {
                    inStream.close();
                    outStream.close();
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean move(File srcFile, String destDir) {
        File dir = new File(destDir);
        return srcFile.renameTo(new File(dir, srcFile.getName()));
    }

    public static boolean rename(File srcFile, String newName) {
        return srcFile.renameTo(new File(srcFile.getParent(), newName));
    }

    /**
     * @param file 创建文件
     * @return 是否成功
     */
    public static boolean createFile(File file) {
        File dir = file.getParentFile();
        boolean hasDir = dir.exists() && dir.isDirectory();
        if (!hasDir) {
            hasDir = dir.mkdirs();
        }
        if (hasDir) {
            boolean hasFile = file.exists() && file.isFile();
            if (!hasFile) {
                try {
                    hasFile = file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return hasFile;
        }
        return false;
    }

}
