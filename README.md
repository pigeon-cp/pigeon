# Pigeon

`Pigeon` 是一款开源的、采用插件化架构的**业务消息中心**，旨在充当软件系统与用户之间的沟通桥梁。

`Pigeon` 具备强大的能力，包括：
- 普通消息/模板消息等发送
- _海量、低延时消息批量发送
- _防骚扰用户如频繁对同一用户发送短信）
- 现有用户体系接入
- 主流服务商（阿里云/腾讯云等）支持
- 插件化机制（更多能力等你自由扩展）

要了解 `Pigeon` 的架构，请前往 ‣‣ [系统架构](docs/Arch.md)

[CHANGELOG.md](./CHANGELOG.md)

## 快速启动

需要先准备好数据库表，执行 DDL `docs/sql/init.schema.sql` 即可

### 本地

将要加载的插件 jar 放在 `/usr/local/pigeon/plugins` 目录下，执行以下命令即可

```shell
$ mvn package
$ java -jar target/pigeon.jar \
    --spring.profiles.active=local \
    --spring.datasource.url={mysql_url} \
    --spring.datasource.password={mysql_psd}
```

> tips: 你也可以通过启动配置 `pigeon.plugins.path` 指定插件所在目录

访问 swagger ui 以查看可用接口 `http://127.0.0.1:8081/swagger-ui.html`

### Docker

docker run xxx

TODO::

### 插件调试

```shell
$ mvn package
$ java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=7896 \
    -jar target/pigeon.jar \
    --spring.profiles.active=local \
    --spring.datasource.url={mysql_url} \
    --spring.datasource.password={mysql_psd} \
    --pigeon.plugins.path={your_plugin_src_root}
```

启动你的插件项目，让 debugger 连接到 7896 端口即可

