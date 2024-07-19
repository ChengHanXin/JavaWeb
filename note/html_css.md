### HTML & CSS

+ 概念

  + HTML：HyperText Markup Language，超文本标记语言。

  - 超文本：超越了文本的限制，比普通文本更强大。除了文字信息，还可以定义图片、音频、视频等内容。

  - 标记语言：由标签构成的语言

    - HTML标签都是预定义好的。例如：使用 <h1> 标签展示标题，使用<a>展示超链接，使用<img>展示图片，<video>展示视频。
    - HTML代码直接在浏览器中运行，HTML标签由浏览器解析。

  - CSS：Cascading Style Sheet，层叠样式表，用于控制页面的样式（表现）。

    

+ 组成部分

  ```html
  主要由head和body两个部分，其中head中的字标签title是用来定义网页的标题的，里面定义的内容会显示在浏览器网页的标题位置。而body中编写的内容，就网页中显示的核心内容。
  
  <html>
  	<head>
  		<title>HTML 快速入门</title>
  	</head>
  	<body>
  		<h1>Hello HTML</h1>
  		<img src="1.jpg"/>
  	</body>
  </html>
  ```

  

+ HTML标签

1. 图片标签 img

   ```html
   A. 图片标签: <img>
   
   B. 常见属性: 
   	src: 指定图像的url (可以指定 绝对路径 , 也可以指定 相对路径)
   	width: 图像的宽度 (像素 / 百分比 , 相对于父元素的百分比)
   	height: 图像的高度 (像素 / 百分比 , 相对于父元素的百分比)
   	
   	备注: 一般width 和 height 我们只会指定一个，另外一个会自动的等比例缩放。
   	
   C. 路径书写方式:
       绝对路径:
   	1. 绝对磁盘路径: C:\Users\Administrator\Desktop\HTML\img\news_logo.png   			 
       2. 绝对网络路径: https://i2.sinaimg.cn/dy/deco/2012/0613/yocc20120613img01/news_logo.png
   
       相对路径:
           ./ : 当前目录 , ./ 可以省略的
           ../: 上一级目录
   ```

2. 标题标签 h 系列

   ```html
   A. 标题标签: <h1> - <h6>
       
   	<h1>111111111111</h1>
   	<h2>111111111111</h2>
   	<h3>111111111111</h3>
   	<h4>111111111111</h4>
   	<h5>111111111111</h5>
   	<h6>111111111111</h6>
   	
   B. 效果 : h1为一级标题，字体也是最大的 ； h6为六级标题，字体是最小的。
   ```

3. 水平分页线标签、换行标签、段落标签

   ```html
   <hr>   <br>   <p>
   ```
   
4. 超链接

   ```html
   标签: <a href="..." target="...">央视网</a>
   属性:
     href: 指定资源访问的url
     target: 指定在何处打开资源链接
     _self: 默认值，在当前页面打开
     _blank: 在空白页面打开
   ```

5. 视频及音频标签

   ```html
   视频标签: &lt;video>
     属性: 
       - src: 规定视频的url
       - controls: 显示播放控件
       - width: 播放器的宽度
       - height: 播放器的高度
   
   音频标签: &lt;audio>
     属性:
       - src: 规定音频的url
       - controls: 显示播放控件
   ```

   



+ CSS引入方式

  具体有3种引入方式，语法如下表格所示：

  | 名称     | 语法描述                                                     | 示例                                           |
  | -------- | ------------------------------------------------------------ | ---------------------------------------------- |
  | 行内样式 | 在标签内使用style属性，属性值是css属性键值对                 | &lt;h1 style="xxx:xxx;">中国新闻网&lt;/h1>     |
  | 内嵌样式 | 定义&lt;style&gt;标签，在标签内部定义css样式，一般定义在head内 | &lt;style> h1 {...} &lt;/style>                |
  | 外联样式 | 定义&lt;link&gt;标签，通过href属性引入外部css文件            | &lt;link rel="stylesheet" href="css/news.css"> |

  对于上述3种引入方式，企业开发的使用情况如下：

  1. 内联样式会出现大量的代码冗余，不方便后期的维护，所以不常用。
  2. 内部样式，通过定义css选择器，让样式作用于当前页面的指定的标签上。
  3. 外部样式，html和css实现了完全的分离，企业开发常用方式。

+ CSS选择器

  当我们需要对页面中的元素及标签设置css样式时，可以通过以下三种方式选择设置该该样式的标签：**（id选择器 > 类选择器 > 元素选择器）**

  <div style="text-align:center">
      <img src="images\CSS选择器.png" alt="混合型">
  </div>

  + 其中，id选择器中的id在页面中不可重复；类选择器中的类名可以重复。
  + 当我们有一个内容没有标签去包含时，但又想给这段内容设置css样式，可以使用span标签将其包含，该标签没有实际的语义含义。

  

+ 页面布局

  - 盒子：页面中所有的元素（标签），都可以看做是一个盒子，由盒子将页面中的元素包含在一个矩形区域内，通过盒子的视角更方便的进行页面布局

  - 盒子模型组成：内容区域（content）、内边距区域（padding）、边框区域（border）、外边距区域（margin）

  <div style="text-align:center">
      <img src="images\盒子模型.png" alt="混合型">
  </div>

  - 布局标签：实际开发网页中，会大量频繁的使用 div 和 span 这两个没有语义的布局标签。

  - 标签：<div> <span>

  - 特点：

    - div标签：

      - 一行只显示一个（独占一行）

      - 宽度默认是父元素的宽度，高度默认由内容撑开

      - 可以设置宽高（width、height）

    - span标签：

      - 一行可以显示多个

      - 宽度和高度默认由内容撑开

      - 不可以设置宽高（width、height）

+ 表格标签

  - &lt;table> : 用于定义整个表格, 可以包裹多个 &lt;tr>， 常用属性如下： 
    - border：规定表格边框的宽度
    - width：规定表格的宽度
    - cellspacing: 规定单元之间的空间
  - &lt;tr> : 表格的行，可以包裹多个 &lt;td>  
  - &lt;td> : 表格单元格(普通)，可以包裹内容 , 如果是表头单元格，可以替换为 &lt;th>  

  ```html
  <!DOCTYPE html>
  <html lang="en">
  <head>
      <title>HTML-表格</title>
      <style>
          td {
              text-align: center; /* 单元格内容居中展示 */
          }
      </style>
  </head>
  <body>
      <table border="1px" cellspacing="0"  width="600px">
          <tr>
              <th>序号</th>
              <th>品牌Logo</th>
              <th>品牌名称</th>
              <th>企业名称</th>
          </tr>
          <tr>
              <td>1</td>
              <td> <img src="img/huawei.jpg" width="100px"> </td>
              <td>华为</td>
              <td>华为技术有限公司</td>
          </tr>
          <tr>
              <td>2</td>
              <td> <img src="img/alibaba.jpg"  width="100px"> </td>
              <td>阿里</td>
              <td>阿里巴巴集团控股有限公司</td>
          </tr>
      </table>
  </body>
  </html>
  ```



+ 表单标签

  - 表单场景: 表单就是在网页中负责数据采集功能的，如：注册、登录的表单。 

  - 表单标签: &lt;form>

  - 表单属性:

    - action: 规定表单提交时，向何处发送表单数据，表单提交的URL。
    - method: 规定用于发送表单数据的方式，常见为： GET、POST。
      - GET：表单数据是拼接在url后面的， 如： xxxxxxxxxxx?username=Tom&age=12，url中能携带的表单数据大小是有限制的。
      - POST： 表单数据是在请求体（消息体）中携带的，大小没有限制。

  - 表单项标签: 不同类型的input元素、下拉列表、文本域等。

    - input: 定义表单项，通过type属性控制输入形式
    - select: 定义下拉列表
    - textarea: 定义文本域

  - note

    表单中的所有表单项，要想能够正常的采集数据，在提交的时候能提交到服务端，表单项必须指定name属性。 否则，无法提交该表单项。

    ```html
    用户名: <input type="text" name="username">
    ```

+ 表单项

  在一个表单中，可以存在很多的表单项，而虽然表单项的形式各式各样，但是表单项的标签其实就只有三个，分别是：

  - &lt;input>: 表单项 , 通过type属性控制输入形式。

    | type取值                 | **描述**                             |
    | ------------------------ | ------------------------------------ |
    | text                     | 默认值，定义单行的输入字段           |
    | password                 | 定义密码字段                         |
    | radio                    | 定义单选按钮                         |
    | checkbox                 | 定义复选框                           |
    | file                     | 定义文件上传按钮                     |
    | date/time/datetime-local | 定义日期/时间/日期时间               |
    | number                   | 定义数字输入框                       |
    | email                    | 定义邮件输入框                       |
    | hidden                   | 定义隐藏域                           |
    | submit / reset / button  | 定义提交按钮 / 重置按钮 / 可点击按钮 |

  - &lt;select>: 定义下拉列表, &lt;option> 定义列表项

  - &lt;textarea>: 文本域

  

  

  

### JavaScript





