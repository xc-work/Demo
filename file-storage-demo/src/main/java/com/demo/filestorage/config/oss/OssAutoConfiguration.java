
package com.demo.filestorage.config.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.demo.filestorage.impl.oss.OssFileStorageService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnClass(OSS.class)
@EnableConfigurationProperties(OssProperties.class)
@ConditionalOnProperty(prefix = "demo.file.storage.oss", name = "enable", havingValue = "true")
public class OssAutoConfiguration implements BeanFactoryAware {

    private BeanFactory beanFactory;
    private final OssProperties ossProperties;

    public OssAutoConfiguration(final OssProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Bean(name = "ossClient")
    public OSS createDefaultOssClient() {
        return this.createOssClient(this.ossProperties);
    }

    @Bean(name = "ossFileStorageService")
    public OssFileStorageService createDefaultOssFileService(@Qualifier("ossClient") final OSS ossClient, final OssProperties ossProperties) {
        return this.createOssStorageService(ossClient,ossProperties);
    }


    private OSS createOssClient(final OssProperties properties) {
        return new OSSClientBuilder().build(
                properties.getEndpoint(),
                properties.getAccessKeyId(),
                properties.getAccessKeySecret());
    }

    private OssFileStorageService createOssStorageService(final OSS ossClient,final OssProperties ossProperties) {
        return new OssFileStorageService(ossClient,ossProperties);
    }


}
