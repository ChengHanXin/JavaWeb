### SpringBootWeb请求响应

+ 概述

  ​		在SpringBoot中内嵌了web服务器（Tomcat）。在我们开发web程序时，定义了一个控制器类Controller，浏览器的请求会被部署在Tomcat中的Controller接收，然后Controller再给浏览器一个响应。

  ​		然而，在Tomcat中实际上是不识别我们定义的Controller，那我们定义的Controller是如何处理请求的呢？

  ​		其实，在SpringBoot中内置了一个核心的Servlet程序**DispatcherServlet**，称之为核心控制器。它负责接收页面发送的请求，然后根据执行的规则，将请求再转发给后面的请求处理器Controller，当处理完成之后，最终再由DispatcherServlet给浏览器响应数据。

  ​		当浏览器发送一个请求时，会携带请求行、请求头等，到达后，Tomcat会负责解析这些请求数据，然后将解析后的请求数据传递给Serlet程序的HttpServeletRequest对象，由该对象进行请求分发到对应的Controller对象中进行处理；处理完成之后将响应通过HttpServletResponse对象返回给浏览器。

<div style="text-align:center">
    <img src="images\访问流程.jpg" alt="混合型">
</div>



#### 1. 请求

#### 1.1 简单参数

+ 原始方式

  在原始的web程序中，需要通过Servlet中提供的API：HttpServletRequest（请求对象），获取请求的相关信息，比如获取请求参数。

  在对应的Controller中，我们想要获取Request对象，可以直接在方法的形参中声明HttpServletRequest对象。然后就可以通过该对象来获取请求参数：

  ```java
  //根据指定的参数名获取请求参数的数据值
  String  request.getParameter("参数名")
  ```

  ```java
  @RequestMapping("/simpleRequest")
  // 原始servlt方式 简单参数
  public String simpleRequest(HttpServletRequest request){
      String name = request.getParameter("name");
      String ageStr = request.getParameter("age");
      int age = Integer.parseInt(ageStr);
      System.out.println(name + ":" + age);
      return "ok";
  }
  ```

+ SpringBoot方式

  在SpringBoot中，对原始的API进行了封装，接收参数的形式更加简单。如果是简单参数，可以通过在方法的形参中定义与请求参数相同的变量名来直接接收请求参数：

  ```java
  @RequestMapping("/simpleParam")
  // springboot 简单参数
  public String simpelRequest(String name, int age){
      // http://localhost:8080/simpleParam?name=tom&age=20
      // 需要保证请求的参数名字一一对应，即定义同名的参数，而且这种方式会自动进行类型转换
      System.out.println(name + ":" + age);
      return "ok";
  }
  ```

  如果方法的形参名与请求参数名不一致，则会导致controller方法接收不到对应的参数

  此时，可以使用@RequestParam注解，通过指定该注解的name属性为请求的参数名，来完成映射：

  ```java
  @RequestMapping("/simpleParam")
      // springboot 简单参数
      public String simpelRequest(@RequestParam(name = "name") String userName, int age){
          // http://localhost:8080/simpleParam?name=tom&age=20
          // 如果参数名字不统一，则需要添加@RequestParam注解，将其name属性设置为请求参数名，完成映射
          // 该注解中有一个属性required，默认为true表示必须需要这个参数；设置为false，可以在请求时不传递该参数
          System.out.println(userName + ":" + age);
          return "ok";
      }
  ```

#### 1.2 实体参数

在使用简单参数进行数据传递时，前端传递了多少个参数，后端Controller方法中的形参就要定义多少个参数。如果参数过多，就会比较繁琐。

此时，我们可以将请求参数封装到一个实体对象中。在进行数据封装时，**实体类的成员变量名要和请求参数名相同**。

+ 简单实体对象

```java
@RequestMapping("/simplePoJo")
// 简单实体对象，使用一个类将请求参数封装起来，通过一个实体类来接收对象
public String simplePoJo(User user){
    // http://localhost:8080/simplePoJo?name=tom&age=10
    System.out.println(user);
    return "OK";
}
```

```java
public class User {
    private String name;
    private int age;
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
```

+ 复杂实体对象

  当一个实体类中，有一个或多个属性，这些属性可能是另一个实体对象类型的。如，在上述User类中，还有一个属性Address，而这个属性也是一个实体类，包含了省和市两个属性。

  复杂实体对象的封装，需要遵守如下规则：

  - **请求参数名与形参对象属性名相同，按照对象层次结构关系即可接收嵌套实体类属性参数。**

  ```java
  public class Address {
      private String province;
      private String city;
      public String getProvince() {
          return province;
      }
      public void setProvince(String province) {
          this.province = province;
      }
      public String getCity() {
          return city;
      }
      public void setCity(String city) {
          this.city = city;
      }
      @Override
      public String toString() {
          return "Address{" +
                  "province='" + province + '\'' +
                  ", city='" + city + '\'' +
                  '}';
      }
  }
  
  public class User {
      private String name;
      private int age;
      private Address address;
      public String getName() {
          return name;
      }
      public void setName(String name) {
          this.name = name;
      }
      public int getAge() {
          return age;
      }
      public void setAge(int age) {
          this.age = age;
      }
      public Address getAddress() {
          return address;
      }
      public void setAddress(Address address) {
          this.address = address;
      }
      @Override
      public String toString() {
          return "User{" +
                  "name='" + name + '\'' +
                  ", age=" + age +
                  ", address=" + address +
                  '}';
      }
  }
  ```

#### 1.3 数组集合参数

使用场景：在HTML表单中，有一个表单项支持多选（复选框），可以一次提交多个值。

我们在接收时可以使用两种方式：1. 数组	2. 集合

+ 数组

  数组参数：**请求参数名与形参数组名称相同，且同一名称的请求参数有多个，定义数组类型形参即可接收参数**。

  ```java
  http://localhost:8080/arrayParam?hobby=game&hobby=java
  ```

  ```java
  @RequestMapping("/arrayParam")
  public String arrayParam(String[] hobby){
      System.out.println(Arrays.toString(hobby));
      return "OK";
  }
  ```

+ 集合

  集合参数：**请求参数名与形参集合名相同，且同一名称的请求参数有多个，使用@RequestParam注解绑定参数关系**。

  > 默认情况下，请求中参数名相同的多个值，是封装到数组。如果要封装到集合，要使用@RequestParam绑定参数关系

  ```java
  // http://localhost:8080/listParam?hobby=game&hobby=java
  @RequestMapping("/listParam")
  public String listParam(@RequestParam List<String> hobby){
      System.out.println(hobby);
      return "OK";
  }
  ```

  

#### 1.4 日期参数

在一些特殊的场景，会涉及到对日期类型数据的封装。而由于日期的格式多种多样，我们在对其进行封装的时候需要通过@DataTimeFormat注解的pattern属性来规定日期的格式。

- @DateTimeFormat注解的pattern属性中指定了哪种日期格式，前端的日期参数就必须按照指定的格式传递。
- 后端controller方法中，需要使用Date类型或LocalDateTime类型，来封装传递的参数。

```java
// http://localhost:8080/dataParam?updateDate=2024-07-17 23:00:00
@RequestMapping("/dataParam")
public String dataParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDate updateDate){
    System.out.println(updateDate);
    return "OK";
}
```



#### 1.5 JSON参数

在发送Json格式的数据时，我们需要使用post方式，将json数据保存在body中

在Controller中接收Json数据时：

- 传递json格式的参数，在Controller中会使用**实体类**进行封装。 
- 封装规则：**JSON数据键名与形参对象属性名相同，定义POJO类型形参即可接收参数。需要使用 @RequestBody标识。**
- @RequestBody注解：将JSON数据映射到形参的实体类对象中（JSON中的key和实体类中的属性名保持一致）

```java
// http://localhost:8080/jsonParam
{
    "name": "TOM",
    "age": "18",
    "address": {
        "province": "陕西",
        "city": "西安"
    }
}
@RequestMapping("/jsonParam")
public String jsonParam(@RequestBody User user){
    System.out.println(user);
    return "OK";
}

public class Address {
    private String province;
    private String city;
	//省略GET , SET 方法
}

public class User {
    private String name;
    private Integer age;
    private Address address;  
    //省略GET , SET 方法
}    
```



#### 1.6 路径参数

之前的请求参数都是通过放在post请求中的请求体或者get请求中的url中，在现在的开发中，还会出现直接在请求的url中传递参数：

```java
http://localhost:8080/user/1		
http://localhost:880/user/1/0
```

路径参数：

- 前端：通过请求URL直接传递参数
- 后端：使用{…}来标识该路径参数，需要使用@PathVariable获取路径参数

```java
// http://localhost:8080/pathParam/100
@RequestMapping("/pathParam/{id}")
public String pathParam(@PathVariable Integer id){
    System.out.println(id);
    return "OK";
}
```

传递多个路径参数

```java
// http://localhost:8080/pathParam/100/gloriah
@RequestMapping("/pathParam/{id}/{name}")
public String pathParam(@PathVariable Integer id, @PathVariable String name){
    System.out.println(id + name);
    return "OK";
}
```



#### 2. 响应

Controller程序除了可以接收请求之外，还可以对发起的请求进行对应的响应

#### 2.1 @ResponseBody

​		在之前的Controller方法中，我们直接return了一个字符串，该字符串被响应给了浏览器，这是通过@ResponseBody实现的。

**@ResponseBody注解：**

- 类型：方法注解、类注解
- 位置：书写在Controller方法上或类上
- 作用：将方法返回值直接响应给浏览器
  - 如果返回值类型是实体对象/集合，将会转换为JSON格式后在响应给浏览器

​	但是之前的程序中我们只在类上添加了@RestController注解，在方法上使用了@RequestMapping注解，是怎么实现响应的？

​	原因：在类上添加的@RestController注解，是一个组合注解。

- @RestController = @Controller + @ResponseBody 

```java
hello
@RequestMapping("/hi")
public String hi(){
    System.out.println("hello world");
    return "hello";
}    

{
    "province": "陕西",
    "city": "西安"
}
@RequestMapping("/getAdd")
public Address getAdd(){
    Address address = new Address();
    address.setProvince("陕西");
    address.setCity("西安");
    return address;
}

[
    {
        "province": "陕西",
        "city": "西安"
    },
    {
        "province": "陕西",
        "city": "宝鸡"
    }
]
@RequestMapping("/getAddList")
public List<Address> getAddList(){
    List<Address> list = new ArrayList<Address>();
    Address address1 = new Address();
    address1.setProvince("陕西");
    address1.setCity("西安");

    Address address2 = new Address();
    address2.setProvince("陕西");
    address2.setCity("宝鸡");

    list.add(address1);
    list.add(address2);

    return list;
}
```

#### 2.2 统一响应结果

​	通过上述例子，可以看到，返回的响应格式互不统一，没有规范可言。

​	所以，需要定义一个统一的返回结果结构：

统一的返回结果使用类来描述，在这个结果中包含：

- 响应状态码：当前请求是成功，还是失败

- 状态码信息：给页面的提示信息

- 返回的数据：给前端响应的数据（字符串、对象、集合）

```java
public class Result {
    private Integer code;//响应码，1 代表成功; 0 代表失败
    private String msg;  //响应码 描述字符串
    private Object data; //返回的数据

    public Result() { }
    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    //增删改 成功响应(不需要给前端返回数据)
    public static Result success(){
        return new Result(1,"success",null);
    }
    //查询 成功响应(把查询结果做为返回数据响应给前端)
    public static Result success(Object data){
        return new Result(1,"success",data);
    }
    //失败响应
    public static Result error(String msg){
        return new Result(0,msg,null);
    }
}
```

修改后的Controller

```java
{
    "code": 1,
    "msg": "success",
    "data": {
        "province": "陕西",
        "city": "西安"
    }
}
@RequestMapping("/getAdd")
public Result getAdd(){
    Address address = new Address();
    address.setProvince("陕西");
    address.setCity("西安");
    return Result.success(address);
}
```

#### 2.3 案例

需求：加载并解析xml文件中的数据，完成数据处理，并在页面展示

```java
@RestController
public class EmpController {
    @RequestMapping("/listEmp")
    public Result list(){
        //1. 加载并解析emp.xml
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        //System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);

        //2. 对数据进行转换处理 - gender, job
        empList.stream().forEach(emp -> {
            //处理 gender 1: 男, 2: 女
            String gender = emp.getGender();
            if("1".equals(gender)){
                emp.setGender("男");
            }else if("2".equals(gender)){
                emp.setGender("女");
            }

            //处理job - 1: 讲师, 2: 班主任 , 3: 就业指导
            String job = emp.getJob();
            if("1".equals(job)){
                emp.setJob("讲师");
            }else if("2".equals(job)){
                emp.setJob("班主任");
            }else if("3".equals(job)){
                emp.setJob("就业指导");
            }
        });
        //3. 响应数据
        return Result.success(empList);
    }
}
```



#### 3. 解耦

在我们进行程序设计以及程序开发时，尽可能让每一个接口、类、方法的职责更单一些（单一职责原则）。

> 单一职责原则：一个类或一个方法，就只做一件事情，只管一块功能。
>
> 这样就可以让类、接口、方法的复杂度更低，可读性更强，扩展性更好，也更利用后期的维护。

上述案例的处理逻辑呢，从组成上看可以分为三个部分：

- 数据访问（Controller）：负责业务数据的维护操作，包括增、删、改、查等操作。
- 逻辑处理（Service）：负责业务逻辑处理的代码。
- 请求处理、响应数据（Dao）：负责，接收页面的请求，给页面响应数据。

执行流程

- 前端发起的请求，由Controller层接收（Controller响应数据给前端）
- Controller层调用Service层来进行逻辑处理（Service层处理完后，把处理结果返回给Controller层）
- Serivce层调用Dao层（逻辑处理过程中需要用到的一些数据要从Dao层获取）
- Dao层操作文件中的数据（Dao拿到的数据会返回给Service层）

而在我们上面的代码中，数据访问、逻辑处理、接收请求并响应数据都放在一起，不便维护。

**控制层：**接收前端发送的请求，对请求进行处理，并响应数据

```java
@RestController
public class EmpController {
    //业务层对象
    private EmpService empService = new EmpServiceA();

    @RequestMapping("/listEmp")
    public Result list(){
        //1. 调用service层, 获取数据
        List<Emp> empList = empService.listEmp();

        //3. 响应数据
        return Result.success(empList);
    }
}
```

**业务逻辑层：**处理具体的业务逻辑

- 业务接口

~~~java
//业务逻辑接口（制定业务标准）
public interface EmpService {
    //获取员工列表
    public List<Emp> listEmp();
}
~~~

- 业务实现类

```java
//业务逻辑实现类（按照业务标准实现）
public class EmpServiceA implements EmpService {
    //dao层对象
    private EmpDao empDao = new EmpDaoA();

    @Override
    public List<Emp> listEmp() {
        //1. 调用dao, 获取数据
        List<Emp> empList = empDao.listEmp();

        //2. 对数据进行转换处理 - gender, job
        empList.stream().forEach(emp -> {
            //处理 gender 1: 男, 2: 女
            String gender = emp.getGender();
            if("1".equals(gender)){
                emp.setGender("男");
            }else if("2".equals(gender)){
                emp.setGender("女");
            }

            //处理job - 1: 讲师, 2: 班主任 , 3: 就业指导
            String job = emp.getJob();
            if("1".equals(job)){
                emp.setJob("讲师");
            }else if("2".equals(job)){
                emp.setJob("班主任");
            }else if("3".equals(job)){
                emp.setJob("就业指导");
            }
        });
        return empList;
    }
}
```

**数据访问层：**负责数据的访问操作，包含数据的增、删、改、查

- 数据访问接口

~~~java
//数据访问层接口（制定标准）
public interface EmpDao {
    //获取员工列表数据
    public List<Emp> listEmp();
}
~~~

- 数据访问实现类

```java
//数据访问实现类
public class EmpDaoA implements EmpDao {
    @Override
    public List<Emp> listEmp() {
        //1. 加载并解析emp.xml
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);
        return empList;
    }
}
```



#### 3.1 分层解耦

在程序设计中，一般遵循两个原则：

+ 高内聚：一个模块中各个元素之间的紧密程度越高越好
+ 低耦合：各个模块之间的依赖、关联程度越低越好

在上述案例中，我们使用三层架构对其进行了改写，实现了高内聚和低耦合。但是在Controller中，我们需要创建Service中的实现类对象；在Service中，我们需要创建Dao中的对象。这导致其没有完全解耦。

解决思路：

+ 提供一个容器，容器中存储一些对象（EmpServiceImpA、EmpDaoImpA）
+ 那个程序需要哪些对象，就从这个容器中去获取

这样来，我们就不用在Controller和Service中去new对应的对象，达到了解耦的目的。

我们想要实现上述解耦操作，就涉及到Spring中的两个核心概念：

- **控制反转：** Inversion Of Control，简称IOC。对象的创建控制权由程序自身转移到外部（容器），这种思想称为控制反转。

  > 对象的创建权由程序员主动创建转移到容器(由容器创建、管理对象)。这个容器称为：IOC容器或Spring容器

- **依赖注入：** Dependency Injection，简称DI。容器为应用程序提供运行时，所依赖的资源，称之为依赖注入。

  > 程序运行时需要某个资源，此时容器就为其提供这个资源。
  >
  > 例：EmpController程序运行时需要EmpService对象，Spring容器就为其提供并注入EmpService对象

**IOC容器中创建、管理的对象，称之为：bean对象**



#### 3.2 IOC & DI

为了实现控制反转和依赖注入，Spring为我们提供了两个注解：@Component 、@Autowired 

+ @Component

  将该注解加在类上，就可以将该类交给IOC容器管理

+ @Autowired 

  将该注解加在需要的成员变量上，就可以在运行时使用IOC容器将对应**类型**的对象赋给它

1. IOC

   要把某个对象交给IOC容器管理，需要在对应的类上加上如下注解之一：

   | 注解        | 说明                 | 位置                                            |
   | :---------- | -------------------- | ----------------------------------------------- |
   | @Controller | @Component的衍生注解 | 标注在控制器类上                                |
   | @Service    | @Component的衍生注解 | 标注在业务类上                                  |
   | @Repository | @Component的衍生注解 | 标注在数据访问类上（由于与mybatis整合，用的少） |
   | @Component  | 声明bean的基础注解   | 不属于以上三类时，用此注解                      |

   在IOC容器中，每一个Bean都有一个自己的名字，默认为类名首字母小写。我们也可以通过注解的value属性来指定这个类在容器中的名字。

   我们所声明的注解想要生效，需要被组件扫描注解@ComponentScan扫描，其默认扫描范围为SpringBoot启动类所在包及其子包。

2. DI

   依赖注入，是指IOC容器要为程序去提供运行时所依赖的资源（对象）。

   @Autowired注解，默认是按照**类型**进行自动装配的（去IOC容器中找某个类型的对象，然后完成注入操作）

   如果在IOC容器中，存在多个类型相同的bean对象？

   <div style="text-align:center">
       <img src="images\多个同类型bean.jpg" alt="混合型">
   </div>

   为了应对这种情况，Spring提供了几种解决方案：

   - @Primary

     当存在多个相同类型的Bean注入时，使用该注解，来确定默认实现类

     <div style="text-align:center">
         <img src="images\primary注解.png" alt="混合型">
     </div>

   - @Qualifier

     指定当前要注入的bean对象，在value属性中，指定注入的bean的名称。

     该注解不能单独使用，必须配合@Autowried使用

     <div style="text-align:center">
         <img src="images\qualifier注解.png" alt="混合型">
     </div>

   - @Resource

     使用该注解是按照**bean的名称**进行注入的，通过name属性指定要注入的bean的名称

     <div style="text-align:center">
         <img src="images\resource注解.png" alt="混合型">
     </div>

   > 面试题 ： @Autowird 与 @Resource的区别
   >
   > - @Autowired 是spring框架提供的注解，而@Resource是JDK提供的注解
   > - @Autowired 默认是按照类型注入，而@Resource是按照名称注入

   

​	







  





