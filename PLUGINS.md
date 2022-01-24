# Plugins Guide

开发一个插件，首先你要了解 `Pigeon` 的[架构](docs/Arch.md)

## 插件开发脚手架

使用脚手架可以简化你的插件项目前期搭建过程

TODO::

## 插件调试

插件调试是插件开发中非常重要的一节，借助 JDAP 即可。

如果使用 jar 启动，可以加入以下 JVM 参数

```shell
$ mvn package
$ java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=7896 \
    -jar pigeon.jar
```

如果使用窗口启动，通过环境变量启用 Debug 模式即可

```bash
docker run
-e DEBUG_MODE \
... \
pigeon:latest
```

然后启动你的插件项目，让 debugger 连接到 7896 端口即可开始断点调试
