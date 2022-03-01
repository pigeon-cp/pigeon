# [Incubating] Pigeon

`Pigeon` 是一款开源的、采用插件化架构的**业务消息中心**，旨在充当软件系统与用户之间的沟通桥梁。

`Pigeon` 具备强大的能力（带下划线_的特性表示正在实现中，将在未来的版本提供），包括：
- 普通消息/模板消息等单条发送，支持邮件、短信、站内信、APP 推送、微信消息等多种渠道
- 海量、低延时模板消息群发
- _广播、多播消息
- _事件消息，轻松集成现有业务
- _防骚扰机制（如频繁对同一用户发送短信的话会触发拦截机制）
- _现有用户体系快速接入
- _丰富的发送策略（如降级消息、复合消息等），尽最大努力触达用户
- 主流服务商（阿里云/腾讯云等）支持（通过插件）
- 插件化机制（更多能力等你自由扩展）

要了解 `Pigeon` 的架构，请前往 ‣‣ [系统架构](docs/Arch.md)

[CHANGELOG.md](./CHANGELOG.md)

## 快速启动

**准备工作**

1. 需要先准备好数据库表，执行 DDL [schema.sql](./docs/schema.sql)
2. 将要加载的插件 jar （例如 [pigeon-aliyun](https://github.com/pigeon-cp/pigeon-aliyun)）放在 `/usr/local/pigeon/plugins` 目录下

> 你也可以使用 `pigeon-cli migrate` 来完成数据表创建操作

### 本地运行

```shell
$ git clone ...
$ mvn package
$ java -jar target/pigeon.jar \
    --spring.datasource.url=jdbc:mysql://127.0.0.1:3306/pigeon
    --spring.datasource.username=
    --spring.datasource.password=
    --spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```

> tips: 
> 1. 你也可以通过启动配置 `pigeon.plugins.path` 指定插件所在目录
> 2. h2 仅供演示使用，考虑到兼容性及性能等问题，开发及部署阶段建议替换成 mysql

访问 Swagger UI 以查看可用接口 `http://127.0.0.1:8081/swagger-ui.html`

### Docker 容器

```bash
$ docker run -p 8081:8081 \
-v /usr/local/pigeon/plugins:/usr/local/pigeon/plugins \
-d \
--name pigeon \
pigeon:latest \
--spring.profiles.active=prod \
--swagger.enabled=true
# specify more properties here..
```
 
## 配置一览 

|**配置名**|**类型**|**默认值**|**说明**|
| -- | -- | -- | -- |
|spring.profiles.active|local/dev/prod|-|启动环境，影响其它配置的默认值|
|spring.datasource.url|string|-|数据库访问地址|
|spring.datasource.username|string|-|数据库账号|
|spring.datasource.password|string|-|数据库密码|
|spring.datasource.driver-class-name|com.mysql.jdbc.Driver 或 org.h2.Driver|-|数据库驱动|
|spring.h2.console.enabled|boolean|false|是否启用 h2 控制台|
|swagger.enabled|boolean|true, prod: false|是否启用 swagger ui|

## 插件开发

See [PLUGINS.md](./PLUGINS.md).

