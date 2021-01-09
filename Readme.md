# PT Cart

一个简单的 PT/BT 小货车系统

Inspired by bitpt

---

## 简介

本项目是一个利用 rss 实现的自动推送系统。你可以将本系统提供的 rss
链接加入你的下载客户端，建立rss订阅并打开自动下载选项，之后无论你在什么地方，当你在种子浏览页面把一个种子加入你的小货车，你的下载客户端就会自动收到这个种子的消息并进行下载。

本项目可以使用两种方法上传 torrent：

- 直接粘贴 PT 站所提供的下载链接（多数 PT 站都有提供包含自己 passkey 的下载链接）
- 直接上传 `.torrent` 文件

同时，你也可以为多个设备分别设定不同的下载频道。

## 部署方法

1. 在前端项目中运行 `yarn build`
2. 将前端项目生成的 `dist` 文件夹中所有文件拷贝至 `src/main/resource/static` 文件夹下
3. 运行 `gradlew build`
4. 部署运行项目：
    - 直接运行（不推荐）：
      ```bash
      java -jar ./build/libs/*.jar 
      --spring.profiles.active=prod
      --spring.datasource.url=jdbc:mysql://127.0.0.1:13306/ptCart?useSSL=false&allowPublicKeyRetrieval=true
      --spring.datasource.username=root
      --spring.datasource.password=pass
      ```
      数据库url、用户名、密码等需要根据需要改动
    - 使用 docker：
      ```bash
      docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password mysql
      
      docker build -t pt-cart .
      
      docker run
      --name pt-cart
      -p 80:80
      --env DATA_PASSWORD=password
      --env DATA_SOURCE=mysql:3306
      --link mysql:mysql
      pt-cart
      ```

5. 访问 [localhost](http://localhost/)