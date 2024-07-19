### Ajax

+ 作用：与服务器进行数据交互

  - 与服务器进行数据交换：通过Ajax可以给服务器发送请求，并获取服务器响应的数据。
  - 异步交互：可以在**不重新加载整个页面**的情况下，与服务器交换数据并**更新部分网页**的技术，如：搜索联想、用户名是否可用的校验等等。

+ 同步请求与异步请求

  + 同步请求流程

    浏览器页面在发送请求给服务器，在服务器处理请求的过程中，浏览器页面不能做其他操作。只能等到服务器响应结束后，浏览器页面才能继续进行其他操作。

    <div style="text-align:center">
        <img src="images\同步请求.png" alt="混合型">
    </div>

  + 异步请求流程

    浏览器页面发送请求给服务器，在服务器处理请求的过程中，浏览器页面还可以做其他操作。

    **可以在不重新加载整个页面的情况下，与服务器交换数据并更新部分网络**

+ Axios

  Axios的使用比较简单，主要分为2步：

  - 引入Axios文件

    ~~~html
    <script src="js/axios-0.18.0.js"></script>
    ~~~

  - 使用Axios发送请求，并获取响应结果，官方提供的api很多，此处给出2种，如下

    - 发送 get 请求

      ~~~js
      axios({
          method:"get",
          url:"http://localhost:8080/ajax-demo1/aJAXDemo1?username=zhangsan"
      }).then(function (resp){
          alert(resp.data);
      })
      ~~~

    - 发送 post 请求

      ```js
      axios({
          method:"post",
          url:"http://localhost:8080/ajax-demo1/aJAXDemo1",
          data:"username=zhangsan"
      }).then(function (resp){
          alert(resp.data);
      });
      ```

    axios()是用来发送异步请求的，小括号中使用 js的JSON对象传递请求相关的参数：

    - method属性：用来设置请求方式的。取值为 get 或者 post。
    - url属性：用来书写请求的资源路径。如果是 get 请求，需要将请求参数拼接到路径的后面，格式为： url?参数名=参数值&参数名2=参数值2。
    - data属性：作为请求体被发送的数据。也就是说如果是 post 请求的话，数据需要作为 data 属性的值。

    then() 需要传递一个匿名函数。我们将 then()中传递的匿名函数称为 **回调函数**，意思是该匿名函数在发送请求时不会被调用，而是在成功响应后调用的函数。而该回调函数中的 resp 参数是对响应的数据进行封装的对象，通过 resp.data 可以获取到响应的数据。
  
  Axios还针对不同的请求，提供了别名方式的api,具体如下：

  | 方法                               | 描述           |
  | ---------------------------------- | -------------- |
  | axios.get(url [, config])          | 发送get请求    |
  | axios.delete(url [, config])       | 发送delete请求 |
  | axios.post(url [, data[, config]]) | 发送post请求   |
  | axios.put(url [, data[, config]])  | 发送put请求    |
  
  我们目前只关注get和post请求，所以在上述的入门案例中，我们可以将get请求代码改写成如下：
  
  ~~~js
  axios.get("http://localhost:8080/ajax-demo1/aJAXDemo1?username=zhangsan").then(result => {
      console.log(result.data);
  })
  ~~~
  
  post请求改写成如下：
  
  ~~~js
  axios.post("http://localhost:8080/ajax-demo1/aJAXDemo1","id=zhangsan").then(result => {
      console.log(result.data);
  })
  ~~~

### Vue

+ vue标准目录介绍

  <div style="text-align:center">
      <img src="images\vue目录.png" alt="混合型">
  </div>

  ```vue
  // 执行流程
  main.js ----> APP.vue -----> 特定的vue页面 
  ```

  

