
package com.demo.filestorage.impl.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.*;
import com.demo.filestorage.config.oss.OssProperties;
import com.demo.filestorage.impl.FileStorageService;
import com.demo.filestorage.model.FileUploadResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Objects;

@Slf4j
public class OssFileStorageService implements FileStorageService {


    private final OSS ossClient;
    private final OssProperties ossProperties;

    public OssFileStorageService(final OSS ossClient, final OssProperties ossProperties) {
        this.ossClient = ossClient;
        this.ossProperties = ossProperties;
    }


    @Override
    public String upload(final byte[] fileBytes, final String fileName, final String fileExtName) throws IOException {
        return this.upload(new ByteArrayInputStream(fileBytes), fileName, fileExtName);
    }

    @Override
    public String upload(final InputStream fileInputStream, String fileName, final String fileExtName) throws IOException {
        if (!fileName.contains(".")) {
            fileName = fileName + "." + fileExtName.replaceAll("\\.", "");
        }
        String result = null;
        final PutObjectResult putObjectResult = this.ossClient.putObject(this.ossProperties.getBucketName(), this.ossProperties.getFolder() + "/" + fileName, fileInputStream);

        if (Objects.nonNull(putObjectResult.getETag())) {
            result = this.ossProperties.getDomain() + this.ossProperties.getFolder() + "/" + fileName;
        }
        return result;
    }


    @Override
    public FileUploadResponse upload(final String fileName, final byte[] fileBytes) {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes);
        return this.upload(fileName, inputStream);
    }

    @Override
    public FileUploadResponse upload(final String fileName, final InputStream fileInputStream) {
        final String key = this.ossProperties.getFolder() + "/" + fileName;
        final PutObjectResult putObjectResult = this.ossClient.putObject(this.ossProperties.getBucketName(), key, fileInputStream);

        String url = null;
        if (Objects.nonNull(putObjectResult.getETag())) {
            url = this.ossProperties.getDomain() + key;
        }

        final FileUploadResponse response = FileUploadResponse.builder().key(key).url(url).fileName(fileName).fileHash("").fileSize(0).build();
        return response;
    }

    @Override
    public FileUploadResponse upload(final String fileName, final File file) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (final FileNotFoundException e) {
            log.error("upload file to oss error:{}", e);
        }
        return this.upload(fileName, fileInputStream);
    }


    @Override
    public byte[] download(final String fileName) {
        final OSSObject ossObject = this.ossClient.getObject(new GetObjectRequest(this.ossProperties.getBucketName(), this.ossProperties.getFolder() + "/" + fileName));
        final InputStream inputStream = ossObject.getObjectContent();
        byte[] bytes = null;
        try {
            bytes = IOUtils.toByteArray(inputStream);
        } catch (final IOException e) {
            log.error("Get object from ali cloud oss failed", e);
        }
        return bytes;
    }

    @Override
    public int download(final String path, final BufferedOutputStream output) {
        return 0;
    }

    @Override
    public boolean delete(final String fileName) {
        try {
            this.ossClient.deleteObject(this.ossProperties.getBucketName(), this.ossProperties.getFolder() + "/" + fileName);
            return true;
        } catch (final Exception ex) {
            log.error("delete oss object failure", ex);
            return false;
        }
    }

    /**
     * 下载对象，传入阿里云OSS数据处理参数
     *
     * @param fileName
     * @param process  数据处理参数
     * @return
     */
    @Override
    public byte[] downloadObjectByProcess(final String fileName, final String process) {
        final ProcessObjectRequest request = new ProcessObjectRequest(this.ossProperties.getBucketName(), this.ossProperties.getFolder() + "/" + fileName, process);
        final GenericResult result = this.ossClient.processObject(request);
        final ResponseMessage responseMessage = result.getResponse();
        final InputStream inputStream = responseMessage.getContent();
        byte[] bytes = null;
        try {
            bytes = IOUtils.toByteArray(inputStream);
        } catch (final IOException e) {
            log.error("get object from Ali cloud oss failed", e);
        }
        return bytes;
    }

}
