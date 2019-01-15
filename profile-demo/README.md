#### 启动方式

```bash
nohup java -jar baapIaas.jar --spring.profiles.active=dev > nohup.out &
nohup java -jar baapIaas.jar --spring.profiles.active=test > nohup.out &
nohup java -jar baapIaas.jar --spring.profiles.active=prod > nohup.out &
```