
package com.demo.filestorage.config.fastdfs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "demo.file.storage.fastdfs")
public class FastdfsProperties {

    private String connect_timeout;
    private String network_timeout;
    private String charset;
    private String tracker_http_port;
    private String anti_steal_token;
    private String secret_key;
    private String tracker_servers;

}
