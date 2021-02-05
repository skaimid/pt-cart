# 《移动互联系统分析与设计》结课设计 - PT 小货车

项目简介：本项目是受 [极速之星](https://bitpt.cn/) 中“小货车”功能启发，并将这个功能进行推广。本项目是前后端分离项目，后端基于Spring Boot + Spring Data JPA + Spring Security ，前端基于 Vue.js，通过 Restful API 在前后端之间进行数据传递。

## 1. 系统需求分析

## 1.1. 项目可行性分析与描述

本项目主要用于通过生成的 RSS 订阅链接远程控制 Bittorrent 下载软件(如qBittorrent、μTorrent等) 自动进行下载任务。将本程序为每个用户单独生成的 RSS 订阅链接加入用户下载客户端，建立rss订阅并打开自动下载选项，之后无论用户在什么地方（例如在校外），当用户把一个种子通过下载链接或者种子文件加入你的小货车，客户的下载客户端就会自动收到这个种子的消息并进行下载。

<img src="%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/%E6%9C%AA%E6%A0%87%E9%A2%98-1.png" alt="挂着2019年的Banner用着Flash的极速之星小货车功能截图" style="zoom: 50%;" />

在各大高校的PT站中，北理的PT站极速之星似乎是唯一提供此功能的站点；但是随着极速之星由于各种原因逐渐衰落，身边不少玩PT的同学逐渐转战其他高校的PT站如北邮人、蒲公英等，因此这一独占功能就无法使用了。通过本程序可以实现与极速之星类似的功能，同时还能为多个设备设定不同的订阅链接（即后文中的订阅频道），可以为多设备下载不同的种子。此外，本程序还可以通过PT站提供的链接和直接将种子上传到服务器两种方法提供种子源。

与极速原来的小货车功能相比本程序有如下优点：

- 可以支持PT站下载链接和种子文件上传，而不仅限于极速之星
- 可以为多个设备下载提供支持

但是同时本程序也有一些缺点：

- 需要另外的网站才能进行下载，较为麻烦
- 界面引导较差

## 1.2. 系统功能

- 系统功能层次图

  ![未命名表单-第 1 页](%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/%E6%9C%AA%E5%91%BD%E5%90%8D%E8%A1%A8%E5%8D%95-%E7%AC%AC%201%20%E9%A1%B5.png)

- UML用例图

  ![未命名表单-第 2 页](%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/%E6%9C%AA%E5%91%BD%E5%90%8D%E8%A1%A8%E5%8D%95-%E7%AC%AC%202%20%E9%A1%B5-1612341702181.png)

- 客户端与服务器各自应该实现的功能：

  - 客户端
    - 界面展示
    - 提供分发频道展示和增删
    - 提供新建下载任务链接，可通过文件上传或粘贴下载url
  - 服务器
    - 存储用户、分发频道、下载链接、种子信息
    - 鉴别用户权限，授予用户 token
    - 向客户端提供数据接口

- 运行截图

  - 主界面
  
    ![image-20210204140858289](%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/image-20210204140858289.png)
  
  - 登录界面
  
    - 失败
  
      ![image-20210204141117012](%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/image-20210204141117012.png)
  
    - 成功——直接跳转会主界面
  
  - 管理界面
  
    - 增加分发频道
  
      ![image-20210204162832444](%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/image-20210204162832444.png)
  
    - 增加种子下载链接
  
      - 通过 URL
  
        ![Inkedimage-20210204162843371_LI](%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/Inkedimage-20210204162843371_LI.jpg)
  
      - 通过上传种子文件
  
        ![image-20210204162943018](%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/image-20210204162943018.png)
  
    - 删除分发频道
  
      ![image-20210204163537057](%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/image-20210204163537057.png)
  
    - 复制订阅链接
  
      ![image-20210204163546963](%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/image-20210204163546963.png)
  
  - RSS下载界面
  
    ![Inkedimage-20210204165032866_LI](%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/Inkedimage-20210204165032866_LI.jpg)
  
    ![image-20210204165021035](%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/image-20210204165021035.png)
    
  - 重置 RSS token
  
    ![image-20210205090646967](%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/image-20210205090646967.png)



## 1.3. 领域模型

- 类图

  ![Package ptcart](%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/Package%20ptcart.png)

- 包图

  ![未命名表单-第 3 页](%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/%E6%9C%AA%E5%91%BD%E5%90%8D%E8%A1%A8%E5%8D%95-%E7%AC%AC%203%20%E9%A1%B5.png)
  
  以下是前端有关项目结构的分析：
  
  ![未标题-12](%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/%E6%9C%AA%E6%A0%87%E9%A2%98-12.png)

## 1.4. 系统用例分析

![未命名表单-第 5 页](%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/%E6%9C%AA%E5%91%BD%E5%90%8D%E8%A1%A8%E5%8D%95-%E7%AC%AC%205%20%E9%A1%B5.png)





# 2. 系统设计文档

## 2.1. 总体架构概貌。

系统主要分为前端和后端两部分处理，前端主要负责数据的展示、整理、用户交互；后端主要负责数据持久化、数据传递等功能。

- 后端

  后端主要分为数据模型 Model；数据存储接口 Repository；业务逻辑 Service；通信接口 Controller；

  - 数据模型 Model，为后端提供全局一致的数据模型
  - 数据存储接口 Repository， 使用Spring Data JPA，定义数据存储的保存方式，封装调用数据库的接口
  - 业务逻辑 Service，在这里调用 Repository，在这里保存所有的业务逻辑，并且有 Interface 与 Impl，保证系统可以高效拓展
  - 通信接口 Controller，这里调用 Service，同时相应客户端发送的 HTTP 请求，对相应的接口调用做出响应

- 前端

  前端的设计没有后端完备，只是将功能相近的文件放在一起，具体构建可以参见 1.3 前端部分说明。

  这里需要注意的是，前端有一些没有复用的部分也做成了独立的Component，主要原因是某个页面封装了太多相对独立的业务逻辑，由于这些业务逻辑的相对独立性，将其拆分为多个模块有利于减少这个页面整体的复杂程度，似乎有利于在debug时免于在巨长的文件中寻找调试代码，编码时逻辑更为清晰；但是这样拆分需要引入Vuex作为数据存储，也引入了不少麻烦和错误。总而言之，这样还是弊大于利，不仅有为了模块化而模块化的嫌疑，更引入了更为复杂的逻辑，也没有在编程时出现特别巨大的优势反而在修正 CSS 样式时引入不少麻烦。

## 2.2. 本系统中的技术关键点及解决方案

1. 用户身份识别

   用户身份存储使用的是 JWT 认证方法，后端使用继承自 Spring Security 的过滤器，前端则对 Axios 进行一些封装之后为所有接口函数提供了一个统一的，不需要再重复进行生成 Header 的方法。当用户需要登录前端界面时，前端向服务器发送身份认证请求，客户端会向服务器验证当前 JWT 是否合法，只有获得 合法的 JWT 之后才可以跳转至用户对应的数据操作页面。

2. 种子文件存储

   本系统通过 `用户名/文件名` 的形式存储用户所上传的文件，通过将文件对应的 URL 保存起来的方式，保证用户可以实现对文件的下载。只有知道下载链接的人才能下载对应的文件，而获得下载链接则需要通过下一步的鉴权，从而在一定程度上可以保证用户下载的安全性。

3. 如何保护用户的 RSS 订阅链接

   使用 JWT 来保护用户的 RSS 订阅链接显然是不可能的，由于 RSS 客户端不具有鉴权、校验等功能，所以提供给用户的 RSS 订阅链接只能时不包含状态，所以为了防止其他人通过瞎猜用户名等方式来窃取用户的 RSS 请求，所以每个 RSS 链接都带有每个用户独有的 RSS Token。当然这种方法也不能完全保证用户的 RSS 链接足够的安全性——因为不变的 Token 可能会被网络上的抓包等方式所抓取，最终导致信息被泄密，因此这里给客户提供了重置 RSS token 的选项，使得客户可以在察觉到一旦出现泄密状况时，将 RSS Token 重置，保证信息不会再次泄露。

4. 开发过程中出现服务器无法相应客户端的预检请求，以及客户端直接无法获取 JWT

   开发过程中，由于存在复杂跨域请求（DELETE、PUT等请求），在这些请求之前，浏览器向后端会发送一个OPTION预检请求，只有服务器相应这个预检请求，客户端客户端才可以正式发送请求。此外，如果服务器没有在相应的http头中添加AllowedHeaders 、ExposedHeaders，客户端也拿不到对应的Token字段。所以我在Spring Security Config 里面增加了 Cors Config 部分；同时，通过在appliciation.properties中进行设定是否运行跨域，Cors Config 读取该资源来决定当前环境时生成环境还是开发环境，进而确定是否允许跨域访问——显然在正式上线的项目中一般是不允许进行跨域访问的。

5. 一对多、多对多数据类出现 Json 序列化无限递归造成StackOverFlow怎么处理

   需要在get方法中添加@JsonIgnore注解。在网上搜索很多都告诉我要在这个属性上添加这个注解，但是毫无作用，在属性对应的get方法上添加该注解才能起作用。其实@JsonIgnore 源码就有提到。

6. 前端如何面对一套 UI 对于多种设备

   一开始完全不会写 CSS， 后来找了一个好看的网站慢慢对着扒，学习了 flex 布局，就至少可以写一些简单点的布局了，而且在手机和PC上显示都还行。

7. 为什么要使用 Docker 进行部署

   因为本系统涉及的配置较多，如果全靠用户手动配置，可能会出现配置出错，导致系统无法运行，而使用Docker进行部署，一则可以免去用户配置 Mysql 的麻烦，二则免去用户选择Spring配置文件，填写配置文件等麻烦，可以直接使用预制的配置文件，不需要修改；三则可以在开发时不用手动敲命令删除数据库，直接新建新的docker就行了，方便快捷。

8. 当Vue Router 遇上Spring Boot的路由应该如何处理

   在部署的时候，由于是前后端分离项目，可以考虑如下几种部署方法：

   - 使用 Nginx，将不同的路由指向本地不同的端口例如：前端对应的路由(/,/login等)指向127.0.0.1:12001，后端对应的路由(/api/*)指向127.0.0.1:12002
   - 将前端项目打包后，放入后端项目的resource/static目录，和后端项目一起打包

   前一种方法在部署上比较简单，但是需要增加一个前向代理，较为麻烦。

   但是后一种方法如果在非根目录上刷新页面，路由会被 Tomcat 接管，而不是被 Vue Router 接管，直接 404。这里需要再定义一个EmbeddedTomcatConfig implements WebServerFactoryCustomizer\<ConfigurableServletWebServerFactory> ，对 Tomcat 的行为进行重写。

9. 为什么没有使用 Vue 3.0

   在一开始进行前端系统选型的时候，就考虑了React、Vue3、Vue2，在用这几个框架编写了几个小的Demo，当时对 Vue 的理解不够深刻，在 Vue3 配合 Vuex 使用上踩了坑，当时查了半天没有找到结果，也没有想明白，遂放弃使用Vue3 转用 Vue2。（其实后面写着写着就想明白了）

# 3. 系统附加说明

## 3.1. 系统开发运行方法

- 前端
  - 开发环境：vscode 或者 webstorm
  
  - 运行方法：
  
    `yarn` (上传的作业不含 node_modules)
  
    `yarn run serve`
- 后端
  - 开发环境： IntelliJ IDEA 2019.3，JDK 8
  - 运行方法：
    1. 本地建立mysql数据库
    2. 修改 application-dev.properties
    3. 运行项目，并且修改程序命令行参数为`--spring.profiles.active=dev`

## 3.2. 系统部署方法

1. 在前端项目中运行 `yarn build`

2. 将前端项目生成的 `dist` 文件夹中所有文件拷贝至后端 `src/main/resource/static` 文件夹下

3. 运行 `gradlew build`

4. 部署运行项目：
    - 直接运行：
      ```bash
      java -jar ./build/libs/*.jar --spring.profiles.active=prod "--spring.datasource.url=jdbc:mysql://127.0.0.1:3306/testDb?useSSL=false&allowPublicKeyRetrieval=true" --spring.datasource.username=root --spring.datasource.password=password
      ```
      数据库url、用户名、密码等需要根据需要改动，同时可以根据需要修改application-prod.properties里面的参数
      
      ![image-20210205103044325](%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/image-20210205103044325.png)
      
    - 使用 docker （推荐，可以避免数据库配置，文件保存等问题）：
      
      powershell
      
      ```powershell
      docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password mysql
      
      docker build -t pt-cart .
      
      docker run `
      --name pt-cart `
      -p 80:80 `
      --env DATA_PASSWORD=password `
      --env DATA_SOURCE=mysql:3306 `
      --link mysql:mysql `
      pt-cart 
      ```
      bash
      ```bash
      docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password mysql
      
      docker build -t pt-cart .
      
      docker run \
      --name pt-cart \
      -p 80:80 \
      --env DATA_PASSWORD=password \
      --env DATA_SOURCE=mysql:3306 \
      --link mysql:mysql \
      pt-cart 
      ```
    
    效果：![image-20210205101308856](%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/image-20210205101308856.png)
    
    初始化完成之后会创建用户名密码（管理员，不过管理员功能没时间实现了）![未标题-222](%E3%80%8A%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%B3%BB%E7%BB%9F%E5%88%86%E6%9E%90%E4%B8%8E%E8%AE%BE%E8%AE%A1%E3%80%8B%E7%BB%93%E8%AF%BE%E8%AE%BE%E8%AE%A1%20-%20PT%20%E5%B0%8F%E8%B4%A7%E8%BD%A6/%E6%9C%AA%E6%A0%87%E9%A2%98-222.png)
    
5. 访问 [localhost](http://localhost/)

6. 关闭：

    - 如果是直接运行：关闭只需要`Ctrl + c`
    - 如果是使用 docker ：`docker kill pt-cart`

# 4. 本系统的实际开发记录

- 2020.12.17-2020.12.23 搭建后端基础框架，完成后端基本功能
- 2020.12.24-2021.1.3 实验、了解数个前端框架，通过 Demo 的方式完成前端技术选型
- 2021.1.4-2021.1.11 完成前、后端基本功能开发
- 2020.1.27-2021.1.5 文档及部分 Bug 修复，重构部分后端代码