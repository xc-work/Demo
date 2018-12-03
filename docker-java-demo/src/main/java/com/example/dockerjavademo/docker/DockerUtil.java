package com.example.dockerjavademo.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.SearchImagesCmd;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.SearchItem;
import com.github.dockerjava.core.DockerClientBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: LiXinchao
 * @Date: 18-11-14 下午2:18
 */
@Component
public class DockerUtil implements CommandLineRunner {

    public void test() {

        DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://10.0.0.161:2375").build();
        Info info = dockerClient.infoCmd().exec();

        System.out.println(info);

        List<SearchItem> exec = dockerClient.searchImagesCmd("docker/whalesay").exec();
        for ( SearchItem item :exec){
            System.out.println(item.getDescription());
        }


    }


    @Override
    public void run(String... args) throws Exception {

        System.out.println("test start --------------");
        test();
        System.out.println("test end --------------");
    }
}
