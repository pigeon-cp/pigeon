# Pigeon

`Pigeon` 是一款开源业务消息中心，支持各大主流服务商（如阿里云、腾讯云等）.

## 快速启动

需要先准备好数据库表，执行 DDL `docs/sql/init.schema.sql` 即可

### 本地

将要加载的插件 jar 放在 `/usr/local/pigeon/plugins` 目录下，执行以下命令即可

```shell
$ mvn package
$ java -jar target/pigeon.jar \
    --spring.profiles.active=local \
    --spring.datasource.url={mysql_url} \
    --spring.datasource.password={mysql_psd} \
```

> tips: 你也可以通过启动配置 `pigeon.plugins.path` 修改插件所在目录

访问 swagger ui 以查看可用接口 `http://127.0.0.1:8081/swagger-ui.html`

### Docker

docker run xxx

TODO::

