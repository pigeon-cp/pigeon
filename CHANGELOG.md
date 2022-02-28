# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

### [0.1.2](https://github.com/pigeon-cp/pigeon/compare/v0.1.1...v0.1.2) (2022-02-28)


### Features

* add error code into response body when DomainException happened ([3504e2f](https://github.com/pigeon-cp/pigeon/commit/3504e2fbaeca32b795ab18795b0d53da30b15bc4))
* handle UnsupportedExtensionsException & return more info ([a6b3551](https://github.com/pigeon-cp/pigeon/commit/a6b35518c4c27e950dd76186d9fcd241e397a1be))
* provide api for create template & add swagger annotations to params and models ([60090dd](https://github.com/pigeon-cp/pigeon/commit/60090dd68a78550d307c77db6383aff64c01fbd1))
* provide api for create third account ([c190a3d](https://github.com/pigeon-cp/pigeon/commit/c190a3d2957f0fc62c515d7916fa1c5158dc096f))
* provide api for execute tactics async ([1023f21](https://github.com/pigeon-cp/pigeon/commit/1023f21b90ba3416e37b22a4f25f2f8134dcebb5))
* provide PluginMappingChainHandlerMapping to load handler defined in plugin context ([262cf4c](https://github.com/pigeon-cp/pigeon/commit/262cf4c6de4cedf3f2ea1a9f8ccd27e26522df67))
* support deliver mass on boost ([9d8a053](https://github.com/pigeon-cp/pigeon/commit/9d8a053a374152e69c70d9af29efb51b63d4fa8f))
* support load extra plugins by specify paths ([0ec8c6e](https://github.com/pigeon-cp/pigeon/commit/0ec8c6ead8c351ffc05fe8576e8335eff29c8f91))
* support send template message with custom ext & introduce hibernate validator ([3a1377b](https://github.com/pigeon-cp/pigeon/commit/3a1377b7cbbbb6ec65ab6e125bd10f2fb26abc78))


### Bug Fixes

* make params support pass obj & add fields docs ([7616bf2](https://github.com/pigeon-cp/pigeon/commit/7616bf242f1da20a2fbfeeb4cc6672d0c8c06d52))
* not required fields ([ffa95dd](https://github.com/pigeon-cp/pigeon/commit/ffa95dd70e4c6c84e6b575cf7c79e040fc84624c))

## 0.1.1 (2022-01-24)

### Bug Fixes

* message controller available on prod ([538c557](https://github.com/pigeon-cp/pigeon/commit/538c5579c86582d053807d7aa81c38f8fa75545c))

## 0.1 (2022-01-24)

### Features

* 支持普通消息、模板消息 ([194c63b](https://github.com/pigeon-cp/pigeon/commit/194c63bf8f23f302dc630b5353c5659d2190f89b))
* 支持消息群发 ([2dab9b1](https://github.com/pigeon-cp/pigeon/commit/2dab9b15ba6dcdb2685ad08199dda222a5a6da0a))
