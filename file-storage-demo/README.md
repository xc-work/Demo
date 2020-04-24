
# demo-file-storage 使用说明

实现了使用OSS和FastDFS上传文件和下载文件。


## 引入依赖

```
<dependency>
    <groupId>com.demo.commons</groupId>
    <artifactId>demo-file-storage</artifactId>
    <version>1.0.3-SNAPSHOT</version>
</dependency>


```


## application.properties配置文件

通过配置文件中的`demo.file.storage.*.enable`可以选择注入OSS或者FastDFS的实现


OSS配置

```
demo.file.storage.oss.enable=true
demo.file.storage.oss.endpoint=http://oss-cn-beijing.aliyuncs.com
demo.file.storage.oss.accessKeyId=L2342353465436N
demo.file.storage.oss.accessKeySecret=1163h45335345NMy453538n
demo.file.storage.oss.bucketName=img-dev
demo.file.storage.oss.folder=demo
demo.file.storage.oss.domain=http://demo.ltd/
```

FastDFS配置

```
demo.file.storage.fastdfs.enable=true
demo.file.storage.fastdfs.connect_timeout=60
demo.file.storage.fastdfs.network_timeout=60
demo.file.storage.fastdfs.charset=UTF-8
demo.file.storage.fastdfs.tracker_http_port=8888
demo.file.storage.fastdfs.anti_steal_token=no
demo.file.storage.fastdfs.secret_key=FastDFS1234567890
demo.file.storage.fastdfs.tracker_servers=1.0.0.1:22122
```


## 项目中注入服务

```
@Resource
private FileStorageService fileStorageService;
```



## 方法使用实例

详情见demo-commons-demo模块

```
public void upload() throws IOException {
    String path = "/opt/test/apple.jpg";
    File file =  new File(path);
    String upload = fileStorageService.upload(FileUtils.readFileToByteArray(file), "apple", "jpg");
    log.info("upload url: {}",upload);
}

public void download() throws IOException {
    String key = "apple";
    byte[] download = fileStorageService.download(key);
    String path = "/opt/test/apple2.jpg";
    InputStream inputStreams = new ByteArrayInputStream(download);
    Files.copy(inputStreams, Paths.get(path));
}

```
