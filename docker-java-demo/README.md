## 参考：
https://blog.csdn.net/qq_36956154/article/details/81335886
https://github.com/docker-java/docker-java



## 步骤：

1. vi /lib/systemd/system/docker.service

2. 找到Execstart=/usr/bin/dockerd后加上-H tcp://0.0.0.0:2375 -H unix://var/run/docker.sock  退出并且保存

3. systemctl daemon-reload
   
4. service docker restart//重启启动docker

5. docker-java推荐使用maven项目引入相关的jar包依赖：
  ```
   <dependency>
         <groupId>com.github.docker-java</groupId>
         <artifactId>docker-java</artifactId>
         <!-- use latest version https://github.com/docker-java/docker-java/releases -->
         <version>3.X.Y</version>
   </dependency>
```
6. 编码
```
DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://xxx.xxx.xxx.xxx:2375").build();
Info info = dockerClient.infoCmd().exec();
System.out.println(info);
  ```