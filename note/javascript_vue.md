### JavaScript

#### 1. 引入方式

+ 内部脚本：将JS代码定义在HTML页面中

  - JavaScript代码必须位于&lt;script&gt;&lt;/script&gt;标签之间
  - 在HTML文档中，可以在任意地方，放置任意数量的&lt;script&gt;
  - 一般会把脚本置于&lt;body&gt;元素的底部，可改善显示速度

+ 外部脚本：JS代码定义在外部 JS文件中，然后引入到 HTML页面中

  - 外部JS文件中，只包含JS代码，不包含<script&gt;标签
  - 引入外部js的&lt;script&gt;标签，必须是双标签，因为它不能自我闭合
  
  

#### 2. JS基础语法

+ 书写语法

  - 区分大小写：与 Java 一样，变量名、函数名以及其他一切东西都是区分大小写的
  - 每行结尾的分号可有可无
  - 大括号表示代码块
  - 注释：

    - 单行注释：// 注释内容
    - 多行注释：/* 注释内容 */

+ 输出语句

  | api              | 描述             |
  | ---------------- | ---------------- |
  | window.alert()   | 警告框           |
  | document.write() | 在HTML 输出内容  |
  | console.log()    | 写入浏览器控制台 |

+ 变量

  - JavaScript 是一门**弱类型语言**，变量可以存放**不同类型的值** 。
  - 变量名需要遵循如下规则：
    - 组成字符可以是任何字母、数字、下划线（_）或美元符号（$）
    - 数字不能开头
    - 建议使用驼峰命名

  | 关键字 | 解释                                                         |
  | ------ | ------------------------------------------------------------ |
  | var    | 早期ECMAScript5中用于变量声明的关键字                        |
  | let    | ECMAScript6中新增的用于变量声明的关键字，相比较var，let只在代码块内生效 |
  | const  | 声明常量的，常量一旦声明，不能修改                           |

  var声明的变量作用域比较大，为全局，即使在代码块中声明，代码块外也可以使用；而且可以重复声明。

  let声明的变量只在所在代码块内有效，且不允许重复声明。

  const声明的变量，一旦声明，就无法更改。

+ 数据类型

  虽然 js 是弱数据类型的语言，但是 js 中也存在数据类型。可以分为：**原始类型**、**引用类型**

  | 数据类型  | 描述                                               |
  | --------- | -------------------------------------------------- |
  | number    | 数字（整数、小数、NaN(Not a Number)）              |
  | string    | 字符串，单双引皆可                                 |
  | boolean   | 布尔。true，false                                  |
  | null      | 对象为空                                           |
  | undefined | 当声明的变量未初始化时，该变量的默认值是 undefined |

  我们可以使用`typeof`函数来返回变量的数据类型

  ```html
  <script>
      //原始数据类型
      alert(typeof 3); //number
      alert(typeof 3.14); //number
  
      alert(typeof "A"); //string
      alert(typeof 'Hello');//string
  
      alert(typeof true); //boolean
      alert(typeof false);//boolean
  
      alert(typeof null); //object   需要注意
  
      var a ;
      alert(typeof a); //undefined
  </script>
  </html>
  ```

+ 运算符

  | 运算规则   | 运算符                                                       |
  | ---------- | ------------------------------------------------------------ |
  | 算术运算符 | + , - , * , / , % , ++ , --                                  |
  | 赋值运算符 | = , += , -= , *= , /= , %=                                   |
  | 比较运算符 | &gt; , < , >= , <= , != , == , ===   注意     == 会进行类型转换，=== 不会进行类型转换 |
  | 逻辑运算符 | && , \|\| , !                                                |
  | 三元运算符 | 条件表达式 ? true_value: false_value                         |

  + note

    - ==：只比较值是否相等，不区分数据类型，哪怕类型不一致，==也会自动转换类型进行值得比较
    - ===：不光比较值，还要比较类型，如果类型不一致，直接返回false

    ```html
    <script>
         var age = 20;
         var _age = "20";
         var $age = 20;
     
         alert(age == _age);//true ，只比较值
         alert(age === _age);//false ，类型不一样
         alert(age === $age);//true ，类型一样，值一样
    </script>
    ```

  在 js 中，可以通过`parseInt()`函数将其他类型转换为数值类型

  ```html
  // 类型转换 - 其他类型转为数字
  <script>
      alert(parseInt("12")); //12
      alert(parseInt("12A45")); //12
      alert(parseInt("A45"));//NaN (not a number)
  </script>
  ```

  在进行流程控制时：number类型的0、NAN为false，剩余为true；String类型的空字符串为false，剩余为true；Null和undefined为false。

+ 函数

  ```js
  格式一：
      function 函数名(参数列表....){
          
      }
  
  格式一：
      var 函数名 = function(参数列表....){
          
      }
  调用
  直接使用函数名调用即可，可以传递任意个参数，但函数只会接收对应个数的参数
  ```

+ Array对象

  ```js
  定义格式
  var 变量名 = new Array(元素列表); 	// 方式一
  var 变量名 = [ 元素列表 ]; 	// 方式二
  ```

  特点：（类似于java中的集合）

  1. 数组长度可变
  2. 可以存储任意数据类型的值

  ```js
  //特点: 长度可变 类型可变
  var arr = [1,2,3,4];
  arr[10] = 50;
  arr[9] = "A";
  
  console.log(arr[10]);	// 50
  console.log(arr[9]);	// A
  console.log(arr[8]);	// undefined
  ```

  属性和方法

  | 属性   | 描述                         |
  | :----- | :--------------------------- |
  | length | 设置或返回数组中元素的数量。 |

  ```js
  var arr = [1,2,3,4];
  arr[10] = 50;
  	// 输出数组中的所有元素
  	for (let i = 0; i < arr.length; i++) {
  	console.log(arr[i]);
  }
  ```

  | 方法方法  | 描述                                             |
  | :-------- | :----------------------------------------------- |
  | forEach() | 遍历数组中的每个有值得元素，并调用一次传入的函数 |
  | push()    | 将新元素添加到数组的末尾，并返回新的长度         |
  | splice()  | 从数组中删除元素                                 |

  ```js
  //e是形参，接受的是数组遍历时的值
  // 只会输出有值的元素
  arr.forEach(function(e){
       console.log(e);
  })
  
  // 简写形式，类似于lambda表达式
  arr.forEach((e) => {
       console.log(e);
  }) 
  
  //push: 添加元素到数组末尾
  arr.push(7,8,9);
  console.log(arr);
  
  //splice: 删除元素
  // 参数1：表示从哪个索引位置删除
  // 参数2：表示删除元素的个数
  arr.splice(2,2);
  console.log(arr);
  ```

+ String 对象

  ```js
  // 创建格式
  var 变量名 = new String("…") ; //方式一
  var 变量名 = "…" ; //方式二
  ```

  属性和方法

  | 属性   | 描述           |
  | ------ | -------------- |
  | length | 字符串的长度。 |

  | 方法        | 描述                                               |
  | ----------- | -------------------------------------------------- |
  | charAt()    | 返回在指定位置的字符。                             |
  | indexOf()   | 检索指定内容在字符串中的索引位置，返回值是索引     |
  | trim()      | 去除字符串两边的空格                               |
  | substring() | 提取字符串中两个指定的索引号之间的字符，包左不包右 |



+ JSON对象

  JavaScript 自定义对象

  ```js
  var 对象名 = {
      属性名1: 属性值1, 
      属性名2: 属性值2,
      属性名3: 属性值3,
      函数名称: function(形参列表){}
  };
  
  // 调用属性
  对象名.属性名
  
  // 调用函数
  对象名.函数名()
  ```

  ```js
  var user = {
      name: "Tom",
      age: 10,
      gender: "male",
      // eat: function(){
      //      console.log("用膳~");
      //  }
      //简化形式
      eat(){
          console.log("用膳~");
      }
  }
  ```

  JSON对象：**J**ava**S**cript **O**bject **N**otation，JavaScript对象标记法。是通过JavaScript标记法书写的文本。其格式如下：

  ```json
  其中，key必须使用引号并且是双引号标记，value可以是任意数据类型。
  {
      "key":value,
      "key":value,
      "key":value
  }
  ```

  在js中编写 json格式的字符串

  ```js
  // 字符串在双引号中，数组在方括号中，对象在花括号中
  var jsonstr = '{"name":"Tom", "age":18, "addr":["北京","上海","西安"]}';
  alert(jsonstr.name);	// undefined
  // 因为此时 jsonstr 是一个字符串，而不是json对象，所以需要借助函数进行json字符串和json对象的转换
  var obj = JSON.parse(jsonstr);
  alert(obj.name);	// Tom
  alert(JSON.stringify(obj));	// {"name":"Tom", "age":18, "addr":["北京","上海","西安"]}
  ```


+ BOM对象

  BOM的全称是Browser Object Model，翻译过来是浏览器对象模型。也就是JavaScript将浏览器的各个组成部分封装成了对象。我们要操作浏览器的部分功能，可以通过操作BOM对象的相关属性或者函数来完成。

  BOM中提供了如下5个对象：

  | 对象名称  | 描述           |
  | :-------- | :------------- |
  | Window    | 浏览器窗口对象 |
  | Navigator | 浏览器对象     |
  | Screen    | 屏幕对象       |
  | History   | 历史记录对象   |
  | Location  | 地址栏对象     |
1.  Window对象

   window对象提供了获取其他BOM对象的属性：

   | 属性      | 描述                  |
   | --------- | --------------------- |
   | history   | 用于获取history对象   |
   | location  | 用于获取location对象  |
   | Navigator | 用于获取Navigator对象 |
   | Screen    | 用于获取Screen对象    |

   window对象常用函数：

   | 函数          | 描述                                                         |
   | ------------- | ------------------------------------------------------------ |
   | alert()       | 显示带有一段消息和一个确认按钮的警告框。                     |
   | comfirm()     | 显示带有一段消息以及确认按钮(True)和取消按钮(False)的对话框。 |
   | setInterval() | 按照指定的周期（以毫秒计）来调用函数或计算表达式。(不断执行) |
   | setTimeout()  | 在指定的毫秒数后调用函数或计算表达式。（只执行一次）         |

   ```js
   var flag = confirm("您确认删除该记录吗?");
   alert(flag);	// 确认--true；取消--false
   
   //定时器 - setInterval -- 周期性的执行某一个函数
   var i = 0;
   setInterval(function(){
        i++;
        console.log("定时器执行了"+i+"次");
   },2000);
   
   //定时器 - setTimeout -- 延迟指定时间执行一次 
   setTimeout(function(){
   	alert("JS");
   },3000);
   ```

2. Location对象

   location是指代浏览器的地址栏对象，对于这个对象，我们常用的是href属性，用于获取或者设置浏览器的地址信息，添加如下代码：

   ```js
   //获取浏览器当前地址栏信息
   alert(location.href);
   //设置浏览器地址栏信息
   location.href = "https://www.itcast.cn";	//执行到这一句会跳转到该地址网页
   ```

+ DOM对象

  Document Object Model 文档对象模型。也就是 JavaScript 将 HTML 文档的各个组成部分封装为对象，包含以下几个部分：

  - Document：整个文档对象
  - Element：元素对象
  - Attribute：属性对象
  - Text：文本对象
  - Comment：注释对象

  <div style="text-align:center">
      <img src="images\DOM对象.png" alt="混合型">
  </div>

  主要作用如下：

  - 改变 HTML 元素的内容

  - 改变 HTML 元素的样式（CSS）

  - 对 HTML DOM 事件作出反应

  - 添加和删除 HTML 元素

    

  获取DOM对象

  | 函数                              | 描述                                     |
  | --------------------------------- | ---------------------------------------- |
  | document.getElementById()         | 根据id属性值获取，返回单个Element对象    |
  | document.getElementsByTagName()   | 根据标签名称获取，返回Element对象数组    |
  | document.getElementsByName()      | 根据name属性值获取，返回Element对象数组  |
  | document.getElementsByClassName() | 根据class属性值获取，返回Element对象数组 |

  ```html
  <body>
      <img id="h1" src="img/off.gif">  <br><br>
  
      <div class="cls">传智教育</div>   <br>
      <div class="cls">黑马程序员</div>  <br>
  
      <input type="checkbox" name="hobby"> 电影
      <input type="checkbox" name="hobby"> 旅游
      <input type="checkbox" name="hobby"> 游戏
  </body>
  <script>
  //1. 获取Element元素
  
  //1.1 获取元素-根据ID获取
   var img = document.getElementById('h1');
   alert(img);
      
  //1.2 获取元素-根据标签获取 - div
  var divs = document.getElementsByTagName('div');
  for (let i = 0; i < divs.length; i++) {
       alert(divs[i]);
  }
      
  //1.3 获取元素-根据name属性获取
  var ins = document.getElementsByName('hobby');
  for (let i = 0; i < ins.length; i++) {
      alert(ins[i]);
  }
      
  //1.4 获取元素-根据class属性获取
  var divs = document.getElementsByClassName('cls');
  for (let i = 0; i < divs.length; i++) {
       alert(divs[i]);
  }
  </script>
  ```

  操作DOM对象属性

  ```js
  //1. 点亮灯泡 : src 属性值
  //首先获取img标签对象
  var img = document.getElementById('h1');
  //然后修改img标签对象的src属性值，进行图片的切换
  img.src = "img/on.gif";
  
  // 还有很多操作对象属性的方法，可以查阅文档进行修改
  ```

  

+ JS 事件监听

  我们可以给一些事件如单击等绑定函数，当事件被触发时，可以自动去执行相应的函数功能。

  + 事件绑定
  
  1. 通过Html标签中的事件属性进行绑定
  
     ```html
     <input type="button" id="btn1" value="事件绑定1" onclick="on()">
     <script>
         function on(){
             alert("按钮1被点击了...");
         }
     </script>
     ```
  
  2. 通过DOM中Element元素的事件属性进行绑定
  
     ```html
     <input type="button" id="btn2" value="事件绑定2">
     <script>
         document.getElementById('btn2').onclick = function(){
             alert("按钮2被点击了...");
         }
     </script>
     ```
  
  + 常见事件
  
    | 事件属性名  | 说明                     |
    | ----------- | ------------------------ |
    | onclick     | 鼠标单击事件             |
    | onblur      | 元素失去焦点             |
    | onfocus     | 元素获得焦点             |
    | onload      | 某个页面或图像被完成加载 |
    | onsubmit    | 当表单提交时触发该事件   |
    | onmouseover | 鼠标被移到某元素之上     |
    | onmouseout  | 鼠标从某元素移开         |



### Vue

**1. 快速入门**

```html
<script src="js/vue.js"></script>

<body>
    <div id="app">
        <input type="text" v-model="message">
        {{message}}
    </div>
</body>

- el：用来指定哪儿些标签受 Vue 管理。该属性取值#app中的app需要是受管理的标签的id属性值
- data: 用来定义数据模型
- methods: 用来定义函数。这个我们在后面就会用到
<script>
    //定义Vue对象
    new Vue({
        el: "#app", //vue接管区域
        data:{
            message: "Hello Vue"
        }
    })
</script>
```

**2. 指令介绍**

| **指令** | **作用**                                                     |
| :------- | ------------------------------------------------------------ |
| v-bind   | 为HTML标签绑定属性值，如设置  href , css样式等。当vue对象中的数据模型发生变化时，标签的属性值会随之变化。 |
| v-model  | 在表单元素上创建**双向数据绑定**，即视图数据发生变化时，数据模型中的数据也会变化；当数据模型中的数据变化时，视图数据也会随之变化。 |

```html
<html lang="en">
<head>
    <title>Vue-指令-v-bind</title>
    <script src="js/vue.js"></script>
</head>
/*首先两个链接指向的地址均为百度，当input中输入一个新的网址时，此时vue中数据区域的url就会跟着变化；再次点击链接就会访问新的网址*/
<body>
    <div id="app">
        <a v-bind:href="url">链接1</a>
        <a :href="url">链接2</a>		//简写形式
        <input type="text" v-model="url">	// 双向绑定
    </div>
</body>
<script>
    //定义Vue对象
    new Vue({
        el: "#app", //vue接管区域
        data:{
           url: "https://www.baidu.com"
        }
    })
</script>
</html>
```

| **指令** | **作用**           |
| -------- | ------------------ |
| v-on     | 为HTML标签绑定事件 |

v-on语法绑定事件时，事件名相比较js中的事件名，没有on

```html
<html lang="en">
<head>
    <title>Vue-指令-v-on</title>
    <script src="js/vue.js"></script>
</head>
<body>
    <div id="app">
        <input type="button" value="点我一下" v-on:click="handle()">
        <input type="button" value="点我一下" @click="handle()">
    </div>
</body>
<script>
    //定义Vue对象
    new Vue({
        el: "#app", //vue接管区域
        data:{
           
        },
        methods: {
        handle: function(){
           alert("你点我了一下...");
        }
    })
</script>
</html>
```

| 指令      | 描述                                                     |
| --------- | -------------------------------------------------------- |
| v-if      | 条件性的渲染某元素，判定为true时渲染,否则不渲染          |
| v-if-else |                                                          |
| v-else    |                                                          |
| v-show    | 根据条件展示某元素，区别在于切换的是css中display属性的值 |

```html
<html lang="en">
<head>
    <title>Vue-指令-v-if与v-show</title>
    <script src="js/vue.js"></script>
</head>
<body>
    /*使用v-if系列进行判断是否展示，浏览器只会渲染条件为真的语句*/
    <div id="app">        
        年龄<input type="text" v-model="age">经判定,为:
        <span v-if="age <= 35">年轻人(35及以下)</span>
        <span v-else-if="age > 35 && age < 60">中年人(35-60)</span>
        <span v-else>老年人(60及以上)</span>

        <br><br>
	/*使用v-show进行判断是否展示，浏览器会渲染所有语句；只是条件为假的语句其css中的display被设置为none*/
        年龄<input type="text" v-model="age">经判定,为:
        <span v-show="age <= 35">年轻人(35及以下)</span>
        <span v-show="age > 35 && age < 60">中年人(35-60)</span>
        <span v-show="age >= 60">老年人(60及以上)</span>
    </div>
</body>
<script>
    //定义Vue对象
    new Vue({
        el: "#app", //vue接管区域
        data:{
           age: 20
        },
    })
</script>
</html>
```

| **指令** | **作用**                               |
| -------- | -------------------------------------- |
| v-for    | 列表渲染，遍历容器的元素或者对象的属性 |

```html
<标签 v-for="变量名 in 集合模型数据">
    {{变量名}}
</标签>

<标签 v-for="(变量名,索引变量) in 集合模型数据">
    <!--索引变量是从0开始，所以要表示序号的话，需要手动的加1-->
   {{索引变量 + 1}} {{变量名}}
</标签>

<html lang="en">
<head>
    <title>Vue-指令-v-for</title>
    <script src="js/vue.js"></script>
</head>
<body>
     <div id="app">
     <div v-for="addr in addrs">{{addr}}</div>
     <hr>
     <div v-for="(addr,index) in addrs">{{index + 1}} : {{addr}}</div>
</div>
</body>
<script>
    //定义Vue对象
    new Vue({
        el: "#app", //vue接管区域
        data:{
           addrs:["北京", "上海", "西安", "成都", "深圳"]
        }
    })
</script>
</html>
```

**3. Vue生命周期**

vue的生命周期：指的是vue对象从创建到销毁的过程。vue的生命周期包含8个阶段：每触发一个生命周期事件，会**自动执行**一个生命周期方法，这些生命周期方法也被称为钩子方法。其完整的生命周期如下图所示：

| 状态          | 阶段周期 |
| ------------- | -------- |
| beforeCreate  | 创建前   |
| created       | 创建后   |
| beforeMount   | 挂载前   |
| mounted       | 挂载完成 |
| beforeUpdate  | 更新前   |
| updated       | 更新后   |
| beforeDestroy | 销毁前   |
| destroyed     | 销毁后   |

```html
<html lang="en">
<head>
    <title>Vue-指令-v-for</title>
    <script src="js/vue.js"></script>
</head>
<body>
    <div id="app">

    </div>
</body>
<script>
    //定义Vue对象
    new Vue({
        el: "#app", //vue接管区域
        data:{
           
        },
        methods: {
            
        },
        mounted () {
            alert("vue挂载完成,发送请求到服务端")
        }
    })
</script>
</html>
```

