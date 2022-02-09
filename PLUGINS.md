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

## 扩展点参考

### 实体

有哪些实体可以使用是由领域模型决定的，而这些实体的行为如何则由你决定。

这是因为所有的实体都通过实体工厂创建，而实体工厂会匹配实体的特定属性，将该实体实例化为对应的子类型。你可以通过扩展实体工厂来改变实体的实例化过程。

领域模型 => TODO::

目前支持的实体工厂有：

- TODO::xxx

拓展一个实体工厂非常简单，如下


### API

你可以很轻易地扩展一个 API，使用 Spring MVC 支持的写法即可，如下

```java
@RestController
@RequestMapping("plugins/foo")
public class FooController {
    @GetMapping
    public String index() {
        // 由于 `FooController` 是在插件定义的 Spring 上下文中的，因此如果你需要获取主程序 Spring 上下文中的 bean，需要通过 `PigeonContext` 的相关方法才行。
        MessageTemplateRepo repo = PigeonContext.getRepo(MessageTemplateRepo.class);
        MessageTemplate template = repo.getOrThrow(4);
        return template.data().getContent();
    }
}
```

> tips: 插件中定义的 API 匹配优先级会低于主程序中定义的 API，因此最好避免路径定义出现冲突
 