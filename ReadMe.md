<div align="center">  

# FirMetadata
_✨简单的通过Papi读取Metadata的拓展✨_
</div>

<p align="center">
    <img src="https://img.shields.io/badge/支持版本-1.8 ~ 1.21.5-brightgreen?style=flat-square" alt="minecraft-version">
</p>

## 📌 关于
FirMetadata 是一个 PlaceholderAPI 的拓展, 主要通过 papi 对玩家的 Metadata 进行读写.
<br />
您可以需要注意Metadata的一些特性: 
1. 数据不持久,重启服务器数据即消失.
2. set、remove操作需要传入plugin对象,此拓展默认传入的是PlaceholderAPI的插件对象.
3. remove操作无法删除传入对象非PlaceholderAPI的Metadata, 这需要removePlugin操作.

## 🔨 安装
1. 下载/构建最新的版本，将jar文件放入 `plugins/PlaceholderAPI/expansions` 文件夹。
2. 执行命令 `/papi register FirMetaData`。
<br />
  
## 💻 使用方法
您可以使用 [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245)  插件进行操作。

| 占位符                                              | 说明                                                              |
|--------------------------------------------------|-----------------------------------------------------------------|
| `%firmetadata_set,testkey,something%`            | 为玩家设置一个metadata, 键 `testkey`, 值 `something`, 返回设置值              |
| `%firmetadata_setPlugin,testkey,something,CMI%`  | 为玩家设置一个metadata, 键 `testkey`, 值 `something`, 传入插件为 `CMI`, 返回设置值 |
| `%firmetadata_get,testkey%`                      | 获取玩家的metadata, 键 `testkey`                                      |
| `%firmetadata_remove,testkey%`                   | 删除玩家的metadata, 键 `testkey`                                      |
| `%firmetadata_removePlugin,testkey,CMI%`         | 删除玩家的metadata, 键 `testkey`, 传入插件为 `CMI`                         |
| `%firmetadata_getAndRemove,testkey%`             | 获取并删除玩家的metadata, 键 `testkey`                                   |
| `%firmetadata_getAndRemovePlugin,testkey,CMI%`   | 获取并删除玩家的metadata, 键 `testkey`, 传入插件为 `CMI`                      |
| `%firmetadata_has,testkey%`                      | 检查玩家是否有某个metadata, 返回值为 `yes` 或 `no`                            |
