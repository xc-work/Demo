package com.demo.filestorage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResponse {
    /**
     * 存储到oss或fastdfs上的文件名(带相对路径)
     */
    private String fileName;

    /**
     * 存储到oss或fastdfs上的key
     */
    private String key;

    /**
     * 上传后在oss或fastdfs上可访问的url地址
     */
    private String url;

    private String fileHash;

    private long fileSize;
}
