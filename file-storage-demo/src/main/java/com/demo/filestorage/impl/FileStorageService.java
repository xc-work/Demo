package com.demo.filestorage.impl;

import com.demo.filestorage.model.FileUploadResponse;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface FileStorageService {

    String upload(byte[] fileBytes, String fileName, String fileExtName) throws IOException;

    String upload(InputStream fileInputStream, String fileName, String fileExtName) throws IOException;


    /**
     * 上传文件
     *
     * @param fileName  文件名
     * @param fileBytes 文件byte数组
     * @return
     */
    FileUploadResponse upload(final String fileName, final byte[] fileBytes);

    /**
     * 上传文件
     *
     * @param fileName        文件名
     * @param fileInputStream 文件流
     * @return
     */
    FileUploadResponse upload(final String fileName, final InputStream fileInputStream);

    /**
     * 上传文件
     *
     * @param fileName 文件名
     * @param file     文件
     * @return
     */
    FileUploadResponse upload(final String fileName, final File file);

    /**
     * 下载文件
     *
     * @param fileKey 文件key
     * @return
     */
    byte[] download(String fileKey);

    /**
     * 文件下载到磁盘
     *
     * @param path   图片路径
     * @param output 输出流 中包含要输出到磁盘的路径
     * @return 失败（-1）,成功（0）
     */
    int download(final String path, final BufferedOutputStream output);

    /**
     * 删除文件
     *
     * @param fileName 文件的全部路径 如：group1/M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg
     * @return
     */
    boolean delete(final String fileName);

    byte[] downloadObjectByProcess(String fileName, String process);

}
