# 在线考试系统

## 本地运行
```shell
mvn spring-boot:run
```

## 打包运行
```shell
# output:./target/kaoshi-web.jar 
mvn clean package
# 指定 profile
java -Dspring.profiles.active=dev -jar target/kaoshi-web.jar
```
