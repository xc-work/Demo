package com.demo.filestorage.utils;

public class FileUtils {

    /**
     * Java文件操作 获取文件扩展名
     *
     * @param filename 文件名（含扩展名）
     * @return
     */
    public static String getExtensionName(final String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            final int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * Java文件操作 获取不带扩展名的文件名
     *
     * @param filename 文件名（含扩展名）
     * @return
     */
    public static String getFileNameNoEx(final String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            final int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

}