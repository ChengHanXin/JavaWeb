### Maven

#### **1. 作用**

+ 依赖管理：方便快捷的管理项目依赖的资源（jar包），避免版本冲突问题。在pom.xml文件中进行依赖配置。

+ 统一项目结构：提供标准、统一的项目结构

  <div style="text-align:center">
      <img src="images\Maven项目结构.png" alt="混合型">
  </div>

  目录说明： 

  - src/main/java: java源代码目录
  - src/main/resources:  配置文件信息
  - src/test/java: 测试代码
  - src/test/resources: 测试配置文件信息

+ 项目构建：Maven提供了标准的、跨平台的自动化项目构建方式

#### **2. Maven 仓库**

+ 本地仓库：本地计算机上用来存储jar包的目录

+ 中央仓库：由maven团队维护的全球唯一的仓库

+ 远程仓库：一般由公司搭建的私有仓库

  <div style="text-align:center">
      <img src="images\maven仓库.png" alt="混合型">
  </div>

  当项目中使用坐标引入对应依赖jar包后，首先会查找本地仓库中是否有对应的jar包

  * 如果有，则在项目直接引用

  * 如果没有，则去中央仓库中下载对应的jar包到本地仓库

#### **3. Maven 坐标**

在Maven中，我们通过pom.xml来管理整个项目的依赖。

Maven坐标主要组成

* groupId：定义当前Maven项目隶属组织名称（通常是域名反写，例如：com.itheima）
* artifactId：定义当前Maven项目名称（通常是模块名称，例如 order-service、goods-service）
* version：定义当前项目版本号

我们可以使用这个坐标来定位到一个唯一的资源，然后将其引入到我们的项目。当然，也可以根据这个坐标来引入自己创建的jar包。

#### **4. Maven 依赖**

1. 依赖传递

   + 直接依赖：在当前项目中通过依赖配置建立的依赖关系
   + 间接依赖：被依赖的资源如果依赖其他资源，当前项目间接依赖其他资源

   <div style="text-align:center">
       <img src="images\maven依赖传递.png" alt="混合型">
   </div>

   - projectA依赖了projectB。对于projectA 来说，projectB 就是直接依赖。
   - 而projectB依赖了projectC及其他jar包。 那么此时，在projectA中也会将projectC的依赖传递下来。对于projectA 来说，projectC就是间接依赖。

2. 排除依赖

   在上述例子中，如果A不想依赖C，可以通过**排除依赖**实现

   + 排除依赖：指主动断开依赖的资源。（被排除的资源无需指定版本）

     ```xml
     <dependency>
         <groupId>com.itheima</groupId>
         <artifactId>maven-projectB</artifactId>
         <version>1.0-SNAPSHOT</version>
        
         <!--排除依赖, 主动断开依赖的资源-->
         <exclusions>
         	<exclusion>
                 <groupId>junit</groupId>
                 <artifactId>junit</artifactId>
             </exclusion>
         </exclusions>
     </dependency>
     ```

3. 依赖范围

   在项目中导入依赖的jar包后，默认情况下，可以在任何地方使用。

   如果希望限制依赖的使用范围，可以通过<scope>标签设置其作用范围

   1. 主程序范围有效（main文件夹范围内）
   2. 测试程序范围有效（test文件夹范围内）
   3. 是否参与打包运行（package指令范围内）

   scope标签的取值范围：

   | **scope**值     | **主程序** | **测试程序** | **打包（运行）** | **范例**    |
   | --------------- | ---------- | ------------ | ---------------- | ----------- |
   | compile（默认） | Y          | Y            | Y                | log4j       |
   | test            | -          | Y            | -                | junit       |
   | provided        | Y          | Y            | -                | servlet-api |
   | runtime         | -          | Y            | Y                | jdbc驱动    |

   

#### 5. Maven生命周期

为了对所有的构建过程进行抽象和统一，描述了一次项目构建，经历了哪些阶段。

Maven的生命周期是抽象的，他的实际任务是通过插件来完成的。

- clean：清理工作。

- default：核心工作。如：编译、测试、打包、安装、部署等。

- site：生成报告、发布站点等。

<div style="text-align:center">
    <img src="images\maven生命周期.png" alt="混合型">
</div>


我们看到这三套生命周期，里面有很多很多的阶段，这么多生命周期阶段，其实我们常用的并不多，主要关注以下几个：

+ clean：清理工作，负责移除上一次构建生成的文件（target文件夹）
+ compile：编译项目源代码
+ test：使用合适的单元测试框架运行测试（junit）
+ package：将编译后的文件进行打包，如 jar、war 等
+ install：将打包后的项目安装到本地仓库

生命周期的顺序是：clean --> validate --> compile --> test --> package --> verify --> install --> site --> deploy 

我们需要关注的就是：clean -->  compile --> test --> package  --> install 

> 说明：在同一套生命周期中，我们在执行后面的生命周期时，前面的生命周期都会执行。

>  思考：当运行package生命周期时，clean、compile生命周期会不会运行？
>
>  ​		clean不会运行，compile会运行。  因为compile与package属于同一套生命周期，而clean与package不属于同一套生命周期。





### HTTP

+ Http：Hyper Text Transfer Protocol(超文本传输协议)，规定了浏览器与服务器之间数据传输的规则。

+ 特点

  + 基于TCP协议：面向连结、安全

  + 基于请求-相应模型：一次请求对应一次响应

    请求和响应是一一对应的，没有请求，就没有响应

  + 该协议是**无状态协议**：对于数据没有记忆功能，每次请求-响应都是独立的

    > 无状态指的是客户端发送HTTP请求给服务端之后，服务端根据请求响应数据，响应完后，不会记录任何信息。
    >
    > - 缺点:  多次请求间不能共享数据
    > - 优点:  速度快
    >
    > 请求之间无法共享数据会引发的问题：
    >
    > - 如：京东购物。加入购物车和去购物车结算是两次请求
    > - 由于HTTP协议的无状态特性，加入购物车请求响应结束后，并未记录加入购物车是何商品
    > - 发起去购物车结算的请求后，因为无法获取哪些商品加入了购物车，会导致此次请求无法正确展示数据
    >
    > 具体使用的时候，我们发现京东是可以正常展示数据的，原因是Java早已考虑到这个问题，并提出了使用会话技术(Cookie、Session)来解决这个问题。具体如何来做，我们后面课程中会讲到。

#### 1. http 请求协议

+ 请求协议：浏览器将数据以请求格式发送给服务器

  > 包括：请求行、请求头、请求体

+ Get 方式的请求协议

  对于get方式的请求来说，其只有请求行和请求体，其请求参数在请求行中。

  <div style="text-align:center">
      <img src="images\get请求.png" alt="混合型">
  </div>

  * 请求行 ：HTTP请求中的第一行数据。由：`请求方式`、`资源路径`、`协议/版本`组成（之间使用空格分隔）

    * 请求方式：GET  
    * 资源路径：/brand/findAll?name=OPPO&status=1
      * 请求路径：/brand/findAll
      * 请求参数：name=OPPO&status=1
        * 请求参数是以key=value形式出现
        * 多个请求参数之间使用`&`连接
      * 请求路径和请求参数之间使用`?`连接 			 
    * 协议/版本：HTTP/1.1  

  * 请求头 ：第二行开始，上图黄色部分内容就是请求头。格式为key: value形式 

    - http是个无状态的协议，所以在请求头设置浏览器的一些自身信息和想要响应的形式。这样服务器在收到信息后，就可以知道是谁，想干什么了

    常见的HTTP请求头有:

    ~~~
    Host: 表示请求的主机名
    
    User-Agent: 浏览器版本。 例如：Chrome浏览器的标识类似Mozilla/5.0 ...Chrome/79 ，IE浏览器的标识类似Mozilla/5.0 (Windows NT ...)like Gecko
    
    Accept：表示浏览器能接收的资源类型，如text/*，image/*或者*/*表示所有；
    
    Accept-Language：表示浏览器偏好的语言，服务器可以据此返回不同语言的网页；
    
    Accept-Encoding：表示浏览器可以支持的压缩类型，例如gzip, deflate等。
    
    Content-Type：请求主体的数据类型
    
    Content-Length：数据主体的大小（单位：字节）
    ~~~

+ Post 方式的请求协议

  <div style="text-align:center">
      <img src="images\post请求.png" alt="混合型">
  </div>

  - 请求行(以上图中红色部分)：包含请求方式、资源路径、协议/版本

    - 请求方式：POST
    - 资源路径：/brand
    - 协议/版本：HTTP/1.1

  - 请求头(以上图中黄色部分)   

  - 请求体(以上图中最后一行) ：存储请求参数 

    - 请求体和请求头之间是有一个空行隔开（作用：用于标记请求头结束）

      

+ 两种请求方式区别

  | 区别方式     | GET请求                                                      | POST请求             |
  | ------------ | ------------------------------------------------------------ | -------------------- |
  | 请求参数     | 请求参数在请求行中。<br/>例：/brand/findAll?name=OPPO&status=1 | 请求参数在请求体中   |
  | 请求参数长度 | 请求参数长度有限制(浏览器不同限制也不同)                     | 请求参数长度没有限制 |
  | 安全性       | 安全性低。原因：请求参数暴露在浏览器地址栏中。               | 安全性相对高         |



#### 2. http响应协议

与HTTP的请求一样，HTTP响应的数据也分为3部分：**响应行**、**响应头** 、**响应体** 

<div style="text-align:center">
    <img src="images\响应.png" alt="混合型">
</div>

* 响应行(以上图中红色部分)：响应数据的第一行。响应行由`协议及版本`、`响应状态码`、`状态码描述`组成

  * 协议/版本：HTTP/1.1
  * 响应状态码：200
  * 状态码描述：OK

* 响应头(以上图中黄色部分)：响应数据的第二行开始。格式为key：value形式

  * http是个无状态的协议，所以可以在请求头和响应头中设置一些信息和想要执行的动作，这样，对方在收到信息后，就可以知道你是谁，你想干什么

  常见的HTTP响应头有:

  ~~~
  Content-Type：表示该响应内容的类型，例如text/html，image/jpeg ；
  
  Content-Length：表示该响应内容的长度（字节数）；
  
  Content-Encoding：表示该响应压缩算法，例如gzip ；
  
  Cache-Control：指示客户端应如何缓存，例如max-age=300表示可以最多缓存300秒 ;
  
  Set-Cookie: 告诉浏览器为当前页面所在的域设置cookie ;
  ~~~

- 响应体(以上图中绿色部分)： 响应数据的最后一部分。存储响应的数据

  - 响应体和响应头之间有一个空行隔开（作用：用于标记响应头结束）

    

- 响应状态码

  | 状态码分类 | 说明                                                         |
  | ---------- | ------------------------------------------------------------ |
  | 1xx        | **响应中**——临时状态码，表示请求已经接受，告诉客户端应该继续请求或者如果它已经完成则忽略它 |
  | 2xx        | **成功**——表示请求已经被成功接收，处理已完成                 |
  | 3xx        | **重定向**——重定向到其它地方：它让客户端再发起一个请求以完成整个处理。 |
  | 4xx        | **客户端错误**——处理发生错误，责任在客户端，如：客户端的请求一个不存在的资源，客户端未被授权，禁止访问等 |
  | 5xx        | **服务器端错误**——处理发生错误，责任在服务端，如：服务端抛出异常，路由出错，HTTP版本不支持等 |

  | 状态码  | 英文描述                               | 解释                                                         |
  | ------- | -------------------------------------- | ------------------------------------------------------------ |
  | ==200== | **`OK`**                               | 客户端请求成功，即**处理成功**，这是我们最想看到的状态码     |
  | 302     | **`Found`**                            | 指示所请求的资源已移动到由`Location`响应头给定的 URL，浏览器会自动重新访问到这个页面 |
  | 304     | **`Not Modified`**                     | 告诉客户端，你请求的资源至上次取得后，服务端并未更改，你直接用你本地缓存吧。隐式重定向 |
  | 400     | **`Bad Request`**                      | 客户端请求有**语法错误**，不能被服务器所理解                 |
  | 403     | **`Forbidden`**                        | 服务器收到请求，但是**拒绝提供服务**，比如：没有权限访问相关资源 |
  | ==404== | **`Not Found`**                        | **请求资源不存在**，一般是URL输入有误，或者网站资源被删除了  |
  | 405     | **`Method Not Allowed`**               | 请求方式有误，比如应该用GET请求方式的资源，用了POST          |
  | 428     | **`Precondition Required`**            | **服务器要求有条件的请求**，告诉客户端要想访问该资源，必须携带特定的请求头 |
  | 429     | **`Too Many Requests`**                | 指示用户在给定时间内发送了**太多请求**（“限速”），配合 Retry-After(多长时间后可以请求)响应头一起使用 |
  | 431     | **` Request Header Fields Too Large`** | **请求头太大**，服务器不愿意处理请求，因为它的头部字段太大。请求可以在减少请求头域的大小后重新提交。 |
  | ==500== | **`Internal Server Error`**            | **服务器发生不可预期的错误**。服务器出异常了，赶紧看日志去吧 |
  | 503     | **`Service Unavailable`**              | **服务器尚未准备好处理请求**，服务器刚刚启动，还未初始化好   |

  状态码大全：https://cloud.tencent.com/developer/chapter/13553 



#### 3. http 协议解析

​	对于一次请求-响应，我们作为后端开发人员，需要对请求数据进行解析，然后按照请求要求来将对应的返回数据包装成为符合http协议的响应，返回给客户端。

​	但是这些处理过于繁琐，此时我们可以使用一些web服务器来自动处理，如Tomcat。

​	web服务器可以对http协议的操作进行封装，使得我们不必直接对协议进行操作。





   

  

  