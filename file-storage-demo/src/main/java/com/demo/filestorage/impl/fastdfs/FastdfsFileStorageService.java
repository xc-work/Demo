
package com.demo.filestorage.impl.fastdfs;

import com.demo.filestorage.config.fastdfs.FastdfsProperties;
import com.demo.filestorage.impl.FileStorageService;
import com.demo.filestorage.model.FileUploadResponse;
import com.demo.filestorage.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.StorageClient1;

import java.io.*;

@Slf4j
public class FastdfsFileStorageService implements FileStorageService {

    private final StorageClient1 fastdfsClient;
    private final FastdfsProperties fastdfsProperties;

    public FastdfsFileStorageService(final StorageClient1 fastdfsClient, final FastdfsProperties fastdfsProperties) {
        this.fastdfsClient = fastdfsClient;
        this.fastdfsProperties = fastdfsProperties;
    }


    @Override
    public String upload(final byte[] fileBytes, final String fileName, String fileExtName) throws IOException {
        fileExtName = fileExtName.replaceAll("\\.", "");
        String result = null;
        try {
            result = this.fastdfsClient.upload_file1(fileBytes, fileExtName, null);
        } catch (final Exception e) {
            log.error("upload file to fastdfs error", e);
        }
        final String ip = this.fastdfsProperties.getTracker_servers().substring(0, this.fastdfsProperties.getTracker_servers().indexOf(":"));
        result = "http://" + ip + ":" + this.fastdfsProperties.getTracker_http_port() + "/" + result;
        return result;
    }


    @Override
    public String upload(final InputStream fileInputStream, final String fileName, final String fileExtName) throws IOException {
        final byte[] bytes = IOUtils.toByteArray(fileInputStream);
        return this.upload(bytes, null, fileExtName);
    }


    @Override
    public FileUploadResponse upload(final String fileName, final byte[] fileBytes) {
        String result = null;
        try {
            result = this.fastdfsClient.upload_file1(fileBytes, FileUtils.getExtensionName(fileName), null);
        } catch (final Exception e) {
            log.error("upload file to fastdfs error:{}", e);
        }

        final String ip = this.fastdfsProperties.getTracker_servers().substring(0, this.fastdfsProperties.getTracker_servers().indexOf(":"));
        final String url = "http://" + ip + ":" + this.fastdfsProperties.getTracker_http_port() + "/" + result;

        final FileUploadResponse response = FileUploadResponse.builder().key(result).url(url).fileName(fileName).fileHash("").fileSize(0).build();
        return response;
    }

    @Override
    public FileUploadResponse upload(final String fileName, final InputStream fileInputStream) {
        byte[] bytes = new byte[0];
        try {
            bytes = IOUtils.toByteArray(fileInputStream);
        } catch (final IOException e) {
            log.error("upload file to fasdfs error:{}", e);
        }
        return this.upload(fileName, bytes);
    }

    @Override
    public FileUploadResponse upload(final String fileName, final File file) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (final FileNotFoundException e) {
            log.error("upload file to fasdfs error:{}", e);
        }
        return this.upload(fileName, fileInputStream);
    }


    @Override
    public byte[] download(final String fileKey) {
        byte[] bytes = new byte[0];
        try {
            bytes = this.fastdfsClient.download_file1(fileKey);
        } catch (final IOException e) {
            log.error("download file from fastdfs error >> error:[{}]", e);
        } catch (final MyException e) {
            log.error("download file from fastdfs error >> error:[{}]", e);
        }
        return bytes;
    }

    @Override
    public int download(final String path, final BufferedOutputStream output) {
        int result = -1;
        try {
            final byte[] b = this.fastdfsClient.download_file1(path);
            try {
                if (b != null) {
                    output.write(b);
                    result = 0;
                }
            } catch (final Exception e) {
            } //用户可能取消了下载
            finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (final IOException e) {
                        log.error("error==>>[{}]", e);
                    }
                }
            }
        } catch (final Exception e) {
            log.error("error==>>[{}]", e);
        }
        return result;
    }

    @Override
    public boolean delete(final String fileName) {
        int result = -1;
        try {
            result = this.fastdfsClient.delete_file1(fileName);
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final MyException e) {
            e.printStackTrace();
        }
        return result > 0;
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

        byte[] bytes = new byte[0];
        try {
            bytes = this.fastdfsClient.download_file1(fileName);
        } catch (final IOException e) {
            log.error("download file from fastdfs error >> error:[{}]", e);
        } catch (final MyException e) {
            log.error("download file from fastdfs error >> error:[{}]", e);
        }
        return bytes;
    }

}
