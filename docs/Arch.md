# Pigeon 整体架构

`Pigeon` 的定位业务消息中台，而中台项目的核心矛盾是如何在满足`通用需求`的同时灵活应对`个性需求`。答案在于`插件化`的架构。

在 `Pigeon` 中，主要项目有三类：
1. [pigeon-core](https://github.com/pigeon-cp/pigeon-core)，核心项目，定义了领域模型及插件扩展点，会被 2&3 依赖
2. 主程序，即当前项目，通过 API 对其它业务提供能力，同时可通过加载插件程序来扩展自身能力
3. 插件程序，遵循一定规范并实现了我们定义的扩展点的项目，可以被主程序在运行时加载（如 [pigeon-aliyun](https://github.com/pigeon-cp/pigeon-aliyun)）


<a href="https://ibb.co/1bfqX4h"><img src="https://i.ibb.co/XVZs5GN/arch.jpg" width=300 heigh=300 alt="arch" border="0" /></a>
