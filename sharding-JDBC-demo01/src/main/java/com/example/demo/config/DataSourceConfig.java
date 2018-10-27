package com.example.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import io.shardingjdbc.core.api.MasterSlaveDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * Created by Administrator on 2018/10/18.
 */
@Configuration
public class DataSourceConfig {
    @Bean
    @Profile("sharding-jdbc/sharding-jdbc-dev")
    public static DataSource dataSource() throws FileNotFoundException, SQLException, IOException {
        InputStream inputStream = ClassUtils.class.getClassLoader().getResourceAsStream("sharding-jdbc/sharding-jdbc-dev.yml");
        return MasterSlaveDataSourceFactory.createDataSource(inputStream2ByteArray(inputStream));
    }

    @Bean
    @Profile("sharding-jdbc/sharding-jdbc-test")
    public static DataSource dataSource2() throws FileNotFoundException, SQLException, IOException {
        InputStream inputStream = ClassUtils.class.getClassLoader().getResourceAsStream("sharding-jdbc/sharding-jdbc-test.yml");
        return MasterSlaveDataSourceFactory.createDataSource(inputStream2ByteArray(inputStream));

    }

    //intputStream 转化为 Byte[]
    public static final byte[] inputStream2ByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inputStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

}
