# Pages Manager

可本地管理Pages服务内容，一键生成漂亮的文档界面。[在线预览](https://durcframework.gitee.io/pages-doc)

简单、轻便，无需安装数据库。

- 框架：spring-boot
- 数据库：sqlite

## 原理

1. 本地维护一组markdown文档
2. 将markdown文件上传到git
3. 项目开通Pages服务生成网页（基于[docsify](https://docsify.js.org/)）

## 使用方式

- 下载项目
- IDE导入maven工程
- 运行PagesmanagerSpringbootApplication.java启动
- 浏览器打开`http://localhost:8088`

## 创建新项目

- 首先在git上新建一个分支，这个分支仅针对pages文档，码云pages服务选择这个分支
- clone新建的分支到本地（使用Git下载指定分支命令为：git clone -b 分支名仓库地址）
- 启动服务，浏览器打开`http://localhost:8088`，点击左侧的`添加项目`
- 依次填入项目信息，点击保存。确保填写正确，不然无法推送到码云。
- 在左边选择新建好的项目。

    1. 点击表头`添加目录`，新建一个父节点
    2. 点击父节点的操作列`添加页面`，输入内容
    3. 点击`发布文档`
    
- 转到码云，开通Pages服务，选择分支，部署目录填`pagesmanager-dest/docs`

**注意**：Pages部署目录一定要填`pagesmanager-dest/docs`不然无法查看文档。

## 问题交流：

Q群：328419269







