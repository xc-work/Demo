
package com.demo.filestorage.config.fastdfs;

import com.demo.filestorage.impl.fastdfs.FastdfsFileStorageService;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;


@Configuration
@ConditionalOnClass(StorageClient1.class)
@EnableConfigurationProperties(FastdfsProperties.class)
@ConditionalOnProperty(prefix = "demo.file.storage.fastdfs", name = "enable", havingValue = "true")
public class FastdfsAutoConfiguration implements BeanFactoryAware {

    private BeanFactory beanFactory;

    private final FastdfsProperties fastdfsProperties;

    public FastdfsAutoConfiguration(final FastdfsProperties fastdfsProperties) {
        this.fastdfsProperties = fastdfsProperties;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Bean(name = "fastdfsClient")
    public StorageClient1 createDefaultFastdfsClient() throws IOException, MyException {
        return this.createFastdfsClient(this.fastdfsProperties);
    }

    @Bean(name = "fastdfsFileStorageService")
    public FastdfsFileStorageService createDefaultFastdfsFileService(@Qualifier("fastdfsClient") final StorageClient1 fastdfsClient, final FastdfsProperties fastdfsProperties) {
        return this.createFastdfsStorageService(fastdfsClient, fastdfsProperties);
    }

    private StorageClient1 createFastdfsClient(final FastdfsProperties fastdfsProperties) throws IOException, MyException {

        Properties props = new Properties();
        props.put(ClientGlobal.PROP_KEY_TRACKER_SERVERS, fastdfsProperties.getTracker_servers());
        props.put(ClientGlobal.CONF_KEY_HTTP_ANTI_STEAL_TOKEN, fastdfsProperties.getAnti_steal_token());
        props.put(ClientGlobal.CONF_KEY_CHARSET, fastdfsProperties.getCharset());
        props.put(ClientGlobal.CONF_KEY_CONNECT_TIMEOUT, fastdfsProperties.getConnect_timeout());
        props.put(ClientGlobal.CONF_KEY_NETWORK_TIMEOUT, fastdfsProperties.getNetwork_timeout());
        props.put(ClientGlobal.PROP_KEY_HTTP_SECRET_KEY, fastdfsProperties.getSecret_key());
        props.put(ClientGlobal.CONF_KEY_HTTP_TRACKER_HTTP_PORT, fastdfsProperties.getTracker_http_port());
        ClientGlobal.initByProperties(props);

        String httpSecretKey = ClientGlobal.getG_secret_key();
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);

        return storageClient1;
    }

    private FastdfsFileStorageService createFastdfsStorageService(final StorageClient1 fastdfsClient, final FastdfsProperties fastdfsProperties) {
        return new FastdfsFileStorageService(fastdfsClient, fastdfsProperties);
    }


}
