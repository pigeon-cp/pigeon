# Pigeon 插件开发指南

在阅读以下文档前，你需要先安装 [pigeon-cli](https://github.com/pigeon-cp/pigeon-cli)。

如果你对 `Pigeon` 的整体架构不了解，建议先阅读[架构设计](docs/Arch.md)。

## 快速开始

创建插件项目

```bash
$ pigeon-cli plugin
```

启动主程序并进入调试

```shell
$ pigeon-cli debug
Java debugger connecting profile: -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=56789
Listening for transport dt_socket at address: 56789
```

根据 cli 的提示，选择你喜欢的调试工具（如 idea, eclipse 等自带的 debugger）进行连接即可进入调试

> 插件调试本质上仍然是借助 [jdwp](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/introclientissues005.html)，cli 工具只是作为辅助简化了调试的启动过程

扩展一个新的消息类型

```java
// FooMessageFactory.java
@Extension
public class FooMessageFactory implements MessageFactory {
    @Override
    public Message create(Long id, Criteria criteria) {
        return new FooMessage(id);
    }

    @Override
    public boolean match(Long id, Criteria o) {
        return Objects.equals(o.getSpType(), "FOO");
    }
}
```

```java
// FooMessage.java
public class FooMessage extends Message {
    public FooMessage(Long id) {
        super(id);
    }

    @Override
    public boolean isRealTime() {
        return true;
    }

    @Override
    protected void doDelivery() {
        MessageDO data = this.data();
        System.out.printf("To %s.\n%s\n%s\n  - by %s",
                data.getTarget(),
                data.getTitle(),
                data.getContent(),
                data.getSender()
        );
    }

    @Override
    public MessageServiceProvider getServiceProvider() {
        return null;
    }
}
```

发送一条 Foo 类型的消息

```bash
$ pigeon-cli
> apis.messages.send('FOO', {target: "taccisum", title: "demo", content: "hello pigeon", channel: "FOO"})
```

## 更多参考资料

- [系统架构](docs/Arch.md)
- [领域模型](https://github.com/pigeon-cp/pigeon-core#models)
- [领域事件](https://github.com/pigeon-cp/pigeon-core#events)
- [扩展点一览](https://github.com/pigeon-cp/pigeon-core#extension-points)

