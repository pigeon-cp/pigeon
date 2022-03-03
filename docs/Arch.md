# Pigeon 整体架构

`Pigeon` 的定位是业务消息中台，而中台项目的核心问题在于：**如何在满足通用需求的同时灵活应对个性化需求**，并由此帮助企业信息系统的开发实现降本增效。

为了解决这个问题，`Pigeon` 采用了`插件化`的架构。

在 `Pigeon` 中，主要项目有四类：
1. [pigeon-core](https://github.com/pigeon-cp/pigeon-core)，核心项目，定义了领域模型及插件扩展点，会被 2&3 依赖
2. 主程序，即当前项目，通过 API 对其它业务提供能力，同时可通过加载插件程序来扩展自身能力
3. 插件程序，遵循一定规范并实现了我们定义的扩展点的项目，可以被主程序在运行时加载（如 [pigeon-aliyun](https://github.com/pigeon-cp/pigeon-aliyun)）
3. [pigeon-cli](https://github.com/pigeon-cp/pigeon-cli)，提供 Pigeon 实例管理客户端，以及插件开发/调试辅助等功能

<a href="https://ibb.co/s9wJTxm"><img src="https://i.ibb.co/B2ZKhWz/arch.jpg" width=700 heigh=500 alt="arch" border="0"></a>
