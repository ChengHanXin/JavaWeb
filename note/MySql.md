## MySql

### 1. 概述

+ mysql的常见操作

  ```java
  net start mysql  // 启动mysql服务
      
  net stop mysql  // 停止mysql服务
      
  mysql -uroot -p1234	 // 登录mysql
      
  exit or quit // 退出mysql
  ```

#### 1.1 数据模型

MySql是关系型数据库，基于二维表进行数据存储的：

<div style="text-align:center">
    <img src="images\数据模型.png" alt="混合型">
</div>

- 通过MySQL客户端连接数据库管理系统DBMS，然后通过DBMS操作数据库
- 使用MySQL客户端，向数据库管理系统发送一条SQL语句，由数据库管理系统根据SQL语句指令去操作数据库中的表结构及数据
- 一个数据库服务器中可以创建多个数据库，一个数据库中也可以包含多张表，而一张表中又可以包含多行记录。



#### 1.2 SQL简介

1. SQL语句可以单行或多行书写，以分号结尾。
2. SQL语句可以使用空格/缩进来增强语句的可读性。
3. MySQL数据库的SQL语句不区分大小写。
4. 注释：
   - 单行注释：-- 注释内容   或   # 注释内容(MySQL特有)
   - 多行注释： /* 注释内容 */

SQL语句根据其功能被分为四大类：DDL、DML、DQL、DCL 

| **分类** | **全称**                    | **说明**                                               |
| -------- | --------------------------- | ------------------------------------------------------ |
| DDL      | Data Definition  Language   | 数据定义语言，用来定义数据库对象(数据库，表，字段)     |
| DML      | Data Manipulation  Language | 数据操作语言，用来对数据库表中的数据进行增删改         |
| DQL      | Data Query Language         | 数据查询语言，用来查询数据库中表的记录                 |
| DCL      | Data Control  Language      | 数据控制语言，用来创建数据库用户、控制数据库的访问权限 |



### 2. 数据库设计 DDL

#### 2.1 数据库操作

+ 查询所有数据库：

  ```mysql
  show databases;
  ```

+ 查询（定位）当前数据库：

  ```mysql
  select database();
  ```

+ 创建数据库：

  ```mysql
  create database [if not exists] 数据库名;
  ```

+ 使用数据库（进入该数据库，切换操作）：

  ```mysql
  use 数据库名;
  ```

+ 删除数据库

  ```mysql
  drop database [if exists] 数据库名;
  ```

#### 2.2 表操作

对于表的操作包含四个部分：**创建、查询、修改、删除**。

#### 2.2.1 创建

```mysql
create table  表名(
	字段1  字段1类型 [约束]  [comment  字段1注释 ],
	字段2  字段2类型 [约束]  [comment  字段2注释 ],
	......
	字段n  字段n类型 [约束]  [comment  字段n注释 ] 
) [ comment  表注释 ] ;
```

> 注意： [ ] 中的内容为可选参数； 最后一个字段后面没有逗号

+ 约束

  + 作用：给表中的字段加上一定的规则，用于限制存储在表中的数据，保证数据的正确性、完整性和有效性。

  在MySQL数据库当中，提供了以下5种约束：

  | **约束** | **描述**                                         | **关键字**  |
  | -------- | ------------------------------------------------ | ----------- |
  | 非空约束 | 限制该字段值不能为null                           | not null    |
  | 唯一约束 | 保证字段的所有数据都是唯一、不重复的             | unique      |
  | 主键约束 | 主键是一行数据的唯一标识，要求非空且唯一         | primary key |
  | 默认约束 | 保存数据时，如果未指定该字段值，则采用默认值     | default     |
  | 外键约束 | 让两张表的数据建立连接，保证数据的一致性和完整性 | foreign key |

  > 注意：约束是作用于表中字段上的，可以在创建表/修改表的时候添加约束。

  

+ 数值类型

  MySQL中的数据类型有很多，主要分为三类：数值类型、字符串类型、日期时间类型。

**数值类型**

| 类型        | 大小   | 有符号(SIGNED)范围                                    | 无符号(UNSIGNED)范围                                       | 描述               |
| ----------- | ------ | ----------------------------------------------------- | ---------------------------------------------------------- | ------------------ |
| TINYINT     | 1byte  | (-128，127)                                           | (0，255)                                                   | 小整数值           |
| SMALLINT    | 2bytes | (-32768，32767)                                       | (0，65535)                                                 | 大整数值           |
| MEDIUMINT   | 3bytes | (-8388608，8388607)                                   | (0，16777215)                                              | 大整数值           |
| INT/INTEGER | 4bytes | (-2147483648，2147483647)                             | (0，4294967295)                                            | 大整数值           |
| BIGINT      | 8bytes | (-2^63，2^63-1)                                       | (0，2^64-1)                                                | 极大整数值         |
| FLOAT       | 4bytes | (-3.402823466 E+38，3.402823466351 E+38)              | 0 和 (1.175494351  E-38，3.402823466 E+38)                 | 单精度浮点数值     |
| DOUBLE      | 8bytes | (-1.7976931348623157 E+308，1.7976931348623157 E+308) | 0 和  (2.2250738585072014 E-308，1.7976931348623157 E+308) | 双精度浮点数值     |
| DECIMAL     |        | 依赖于M(精度)和D(标度)的值                            | 依赖于M(精度)和D(标度)的值                                 | 小数值(精确定点数) |

```sql
示例: 
    年龄字段 ---不会出现负数, 而且人的年龄不会太大
	age tinyint unsigned
	
	分数 ---总分100分, 最多出现一位小数
	score double(4,1)
```

**字符串类型**

| 类型       | 大小                  | 描述                         |
| ---------- | --------------------- | ---------------------------- |
| CHAR       | 0-255 bytes           | 定长字符串(需要指定长度)     |
| VARCHAR    | 0-65535 bytes         | 变长字符串(需要指定长度)     |
| TINYBLOB   | 0-255 bytes           | 不超过255个字符的二进制数据  |
| TINYTEXT   | 0-255 bytes           | 短文本字符串                 |
| BLOB       | 0-65 535 bytes        | 二进制形式的长文本数据       |
| TEXT       | 0-65 535 bytes        | 长文本数据                   |
| MEDIUMBLOB | 0-16 777 215 bytes    | 二进制形式的中等长度文本数据 |
| MEDIUMTEXT | 0-16 777 215 bytes    | 中等长度文本数据             |
| LONGBLOB   | 0-4 294 967 295 bytes | 二进制形式的极大文本数据     |
| LONGTEXT   | 0-4 294 967 295 bytes | 极大文本数据                 |

**char 与 varchar 都可以描述字符串，char是定长字符串，指定长度多长，就占用多少个字符，和字段值的长度无关 。而varchar是变长字符串，指定的长度为最大占用长度 。相对来说，char的性能会更高些。**

```sql
示例： 
    用户名 username ---长度不定, 最长不会超过50
	username varchar(50)
	
	手机号 phone ---固定长度为11
	phone char(11)
```

**日期时间类型**

| 类型      | 大小 | 范围                                       | 格式                | 描述                     |
| --------- | ---- | ------------------------------------------ | ------------------- | ------------------------ |
| DATE      | 3    | 1000-01-01 至  9999-12-31                  | YYYY-MM-DD          | 日期值                   |
| TIME      | 3    | -838:59:59 至  838:59:59                   | HH:MM:SS            | 时间值或持续时间         |
| YEAR      | 1    | 1901 至 2155                               | YYYY                | 年份值                   |
| DATETIME  | 8    | 1000-01-01 00:00:00 至 9999-12-31 23:59:59 | YYYY-MM-DD HH:MM:SS | 混合日期和时间值         |
| TIMESTAMP | 4    | 1970-01-01 00:00:01 至 2038-01-19 03:14:07 | YYYY-MM-DD HH:MM:SS | 混合日期和时间值，时间戳 |

```sql
示例: 
	生日字段  birthday ---生日只需要年月日  
	birthday date
	
	创建时间 createtime --- 需要精确到时分秒
	createtime  datetime
```

+ 案例

  <div style="text-align:center">
      <img src="images\创建表案例.png" alt="混合型">
  </div>

  ```mysql
  create table emp (
    id int unsigned primary key auto_increment comment 'ID',
    username varchar(20) not null unique comment '用户名',
    password varchar(32) default '123456' comment '密码',
    name varchar(10) not null comment '姓名',
    gender tinyint unsigned not null comment '性别, 说明: 1 男, 2 女',
    image varchar(300) comment '图像',
    job tinyint unsigned comment '职位, 说明: 1 班主任,2 讲师, 3 学工主管, 4 教研主管',
    entrydate date comment '入职时间',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间'
  ) comment '员工表';
  ```

  在这个案例中，除了页面原型需要的字段，我们还添加了所需要的业务基础字段(id主键、插入时间、修改时间)

  > 说明：
  >
  > - create_time：记录的是当前这条数据插入的时间。 
  >
  > - update_time：记录当前这条数据最后更新的时间。

  

 #### 2.2.2 其他操作

```mysql
-- 查询
-- 查询当前数据库所有表
show tables;
-- 查看指定表结构
desc 表名 ;
-- 查询指定表的建表语句
show create table 表名 ;

-- 修改
-- 添加字段
alter table 表名 add  字段名  类型(长度)  [comment 注释]  [约束];
-- 修改数据类型
alter table 表名 modify  字段名  新数据类型(长度);
alter table 表名 change  旧字段名  新字段名  类型(长度)  [comment 注释]  [约束];
-- 删除字段
alter table 表名 drop 字段名;
-- 修改表名
rename table 表名 to 新表名;

-- 删除表
drop  table [ if exists ]  表名;
```



### 3. 数据库操作 DML

DML英文全称是Data Manipulation Language(数据操作语言)，用来对数据库中表的数据记录进行增、删、改操作。

- 添加数据（INSERT）
- 修改数据（UPDATE）
- 删除数据（DELETE） 

#### 3.1 增加(insert)

insert语法：

- 向指定字段添加数据

  ~~~mysql
  insert into 表名 (字段名1, 字段名2) values (值1, 值2);
  ~~~

- 全部字段添加数据

  ~~~mysql
  insert into 表名 values (值1, 值2, ...);
  ~~~

- 批量添加数据（指定字段）

  ~~~mysql
  insert into 表名 (字段名1, 字段名2) values (值1, 值2), (值1, 值2);
  ~~~

- 批量添加数据（全部字段）

  ~~~mysql
  insert into 表名 values (值1, 值2, ...), (值1, 值2, ...);
  ~~~



案例1：向tb_emp表的username、name、gender字段插入数据

~~~mysql
-- 因为设计表时create_time, update_time两个字段不能为NULL，所以也做为要插入的字段
insert into tb_emp(username, name, gender, create_time, update_time)
values ('wuji', '张无忌', 1, now(), now());
~~~

案例2：向tb_emp表的所有字段插入数据

~~~mysql
insert into tb_emp(id, username, password, name, gender, image, job, entrydate, create_time, update_time)
values (null, 'zhirou', '123', '周芷若', 2, '1.jpg', 1, '2010-01-01', now(), now());
~~~

案例3：批量向tb_emp表的username、name、gender字段插入数据

~~~mysql
insert into tb_emp(username, name, gender, create_time, update_time)
values ('weifuwang', '韦一笑', 1, now(), now()),
       ('fengzi', '张三疯', 1, now(), now());
~~~

Insert操作的注意事项：

1. 插入数据时，指定的字段顺序需要与值的顺序是一一对应的。

2. 字符串和日期型数据应该包含在引号中。

3. 插入的数据大小，应该在字段的规定范围内。



#### 3.2 修改(update)

update语法：

```sql
update 表名 set 字段名1 = 值1 , 字段名2 = 值2 , .... [where 条件] ;
```

案例1：将tb_emp表中id为1的员工，姓名name字段更新为'张三'

```sql
update tb_emp set name='张三',update_time=now() where id=1;
```

案例2：将tb_emp表的所有员工入职日期更新为'2010-01-01'

```sql
update tb_emp set entrydate='2010-01-01',update_time=now();
```

> 注意事项:
>
> 1. 修改语句的条件可以有，也可以没有，如果没有条件，则会修改整张表的所有数据。
>
> 2. 在修改数据时，一般需要同时修改公共字段update_time，将其修改为当前操作时间。



#### 3.3 删除(delete)

delete语法：

```SQL
delete from 表名  [where  条件] ;
```

案例1：删除tb_emp表中id为1的员工

```sql
delete from tb_emp where id = 1;
```

案例2：删除tb_emp表中所有员工

```sql
delete from tb_emp;
```

> 注意事项:
>
> ​	• DELETE 语句的条件可以有，也可以没有，如果没有条件，则会删除整张表的所有数据。
>
> ​	• DELETE 语句不能删除某一个字段的值(可以使用UPDATE，将该字段值置为NULL即可)。
>
> ​	• 当进行删除全部数据操作时，会提示询问是否确认删除所有数据，直接点击Execute即可。 



### 4. 数据库操作 DQL

DQL英文全称是Data Query Language(数据查询语言)，用来查询数据库表中的记录。

DQL查询语句，语法结构如下：

```mysql
SELECT
	字段列表
FROM
	表名列表
WHERE
	条件列表
GROUP  BY
	分组字段列表
HAVING
	分组后条件列表
ORDER BY
	排序字段列表
LIMIT
	分页参数
```

上述完整语法可以分为以下几个类：

- 基本查询（不带任何条件）
- 条件查询（where）
- 分组查询（group by）
- 排序查询（order by）
- 分页查询（limit）



#### 4.1 基本查询

在基本查询的DQL语句中，不带任何的查询条件，语法如下：

- 查询多个字段

  ~~~mysql
  select 字段1, 字段2, 字段3 from  表名;
  ~~~

- 查询所有字段（通配符）

  ~~~mysql
  select *  from  表名;
  ~~~

- 设置别名

  ~~~mysql
  select 字段1 [ as 别名1 ] , 字段2 [ as 别名2 ]  from  表名;
  ~~~

- 去除重复记录

  ~~~mysql
  select distinct 字段列表 from  表名;
  ~~~

```mysql
--  =================== DQL: 基本查询 ======================
-- 1. 查询指定字段 name,entrydate 并返回
select name, entrydate from tb_emp;

-- 2. 查询返回所有字段
-- 建议使用这种，效率高
select id, username, password, name, gender, image, job, entrydate, create_time, update_time from tb_emp;
select * from tb_emp;

-- 3. 查询所有员工的 name,entrydate, 并起别名(姓名、入职日期)
select name as 姓名, entrydate as 入职日期 from tb_emp;

-- 4. 查询已有的员工关联了哪几种职位(不要重复)
select job from tb_emp;
select distinct job from tb_emp;
```



#### 4.2 条件查询

**语法：**

```sql
select  字段列表  from   表名   where   条件列表 ; -- 条件列表：意味着可以有多个条件
```

学习条件查询就是学习条件的构建方式，而在SQL语句当中构造条件的运算符分为两类：

- 比较运算符
- 逻辑运算符

常用的比较运算符如下: 

| **比较运算符**       | **功能**                                 |
| -------------------- | ---------------------------------------- |
| >                    | 大于                                     |
| >=                   | 大于等于                                 |
| <                    | 小于                                     |
| <=                   | 小于等于                                 |
| =                    | 等于                                     |
| <> 或 !=             | 不等于                                   |
| between ...  and ... | 在某个范围之内(含最小、最大值)           |
| in(...)              | 在in之后的列表中的值，多选一             |
| like 占位符          | 模糊匹配(_匹配单个字符, %匹配任意个字符) |
| is null              | 是null                                   |

常用的逻辑运算符如下:

| **逻辑运算符** | **功能**                    |
| -------------- | --------------------------- |
| and 或 &&      | 并且 (多个条件同时成立)     |
| or 或 \|\|     | 或者 (多个条件任意一个成立) |
| not 或 !       | 非 , 不是                   |

```mysql
--  =================== DQL: 条件查询 ======================
-- 1. 查询 姓名 为 杨逍 的员工
select * from tb_emp where name='杨逍';

-- 2. 查询 id小于等于5 的员工信息
select * from tb_emp where id <= 5;

-- 3. 查询 没有分配职位(null) 的员工信息
select * from tb_emp where job is null;

-- 4. 查询 有职位 的员工信息
select * from tb_emp where job is not null;

-- 5. 查询 密码不等于 '123456' 的员工信息
select * from tb_emp where password != '123456';

-- 6. 查询 入职日期 在 '2000-01-01' (包含) 到 '2010-01-01'(包含) 之间的员工信息
select * from tb_emp where entrydate >= '2000-01-01' and entrydate <= '2010-01-01';
select * from tb_emp where entrydate between '2000-01-01' and '2010-01-01';

-- 7. 查询 入职时间 在 '2000-01-01' (包含) 到 '2010-01-01'(包含) 之间 且 性别为女 的员工信息
select * from tb_emp where entrydate between '2000-01-01' and '2010-01-01' and gender = 2;

-- 8. 查询 职位是 2 (讲师), 3 (学工主管), 4 (教研主管) 的员工信息
select * from tb_emp where job = 2 or job = 3 or job = 4;
select * from tb_emp where job in (2, 3, 4);

-- 9. 查询 姓名 为两个字的员工信息
select * from tb_emp where name like '__';

-- 10. 查询 姓 '张' 的员工信息
select * from tb_emp where name like '张%';
```



#### 4.3 分组查询

在介绍分组查询之前，我们需要先了解一下聚合函数：

聚合函数进行对一列的值进行计算，然后返回一个结果值（将一列数据作为一个整体，进行纵向计算）

```mysql
select  聚合函数(字段列表)  from  表名 ;
```

注意 : **聚合函数会忽略空值，对NULL值不作为统计**。

常用聚合函数：

| **函数** | **功能** |
| -------- | -------- |
| count    | 统计数量 |
| max      | 最大值   |
| min      | 最小值   |
| avg      | 平均值   |
| sum      | 求和     |

```mysql
--  =================== DQL: 分组查询 ======================
-- 聚合函数
-- 1. 统计该企业员工数量
select count(*) from tb_emp;    -- 推荐使用
select count(job) from tb_emp;   -- 使用字段统计，不统计值为null的记录
select count(1) from tb_emp;    -- 使用常量统计

-- 2. 统计该企业员工 ID 的平均值
select avg(id) from tb_emp;

-- 3. 统计该企业最早入职的员工
select min(entrydate) from tb_emp;

-- 4. 统计该企业最迟入职的员工
select max(entrydate) from tb_emp;

-- 5. 统计该企业员工的 ID 之和
select sum(id) from tb_emp;
```

**分组： 按照某一列或者某几列，把相同的数据进行合并输出。**

> 分组其实就是按列进行分类(指定列下相同的数据归为一类)，然后可以对分类完的数据进行合并计算。
>
> 分组查询通常会使用聚合函数进行计算。

语法：

~~~mysql
select  字段列表  from  表名  [where 条件]  group by 分组字段名  [having 分组后过滤条件];
~~~

```mysql
-- 分组
-- 1. 根据性别分组 , 统计男性和女性员工的数量
select gender, count(*) from tb_emp group by gender;

-- 2. 先查询入职时间在 '2015-01-01' (包含) 以前的员工 , 并对结果根据职位分组 , 获取员工数量大于等于2的职位
select job, count(*) from tb_emp where entrydate <= '2015-01-01' group by job having count(*) >= 2;
```

+ 如果需要对分组后的结果进行过滤，使用having关键字

**where与having区别（面试题）**

- 执行时机不同：where是分组之前进行过滤，不满足where条件，不参与分组；而having是分组之后对结果进行过滤。
- 判断条件不同：where不能对聚合函数进行判断，而having可以。



#### 4.4 排序查询

语法：

```mysql
select  字段列表  
from   表名   
[where  条件列表] 
[group by  分组字段 ] 
order  by  字段1  排序方式1 , 字段2  排序方式2 … ;
```

- 排序方式：

  - ASC ：升序（默认值）

  - DESC：降序

```mysql
--  =================== 排序查询 ======================
-- 1. 根据入职时间, 对员工进行升序排序
select * from tb_emp order by entrydate asc;

-- 2. 根据入职时间, 对员工进行降序排序
select * from tb_emp order by entrydate desc;

-- 3. 根据 入职时间 对公司的员工进行 升序排序 ， 入职时间相同 , 再按照 更新时间 进行降序排序
select * from tb_emp order by entrydate asc, update_time desc;
```

**在进行多字段排序时，当第一个字段的值相同时，才会根据第二个字段进行排序！**



#### 4.5 分页查询

分页查询语法：

```sql
select  字段列表  from   表名  limit  起始索引, 查询记录数 ;
```

```mysql
--  =================== 分页查询 ======================
-- 1. 从起始索引0开始查询员工数据, 每页展示5条记录
select * from tb_emp limit 0, 5;

-- 2. 查询 第1页 员工数据, 每页展示5条记录
select * from tb_emp limit 0, 5;

-- 3. 查询 第2页 员工数据, 每页展示5条记录
select * from tb_emp limit 5, 5;

-- 4. 查询 第3页 员工数据, 每页展示5条记录
select * from tb_emp limit 10, 5;
```

**注意事项:**

1. 起始索引从0开始。        计算公式：起始索引 = （查询页码 - 1）* 每页显示记录数

2. 分页查询是数据库的方言，不同的数据库有不同的实现，MySQL中是LIMIT

3. 如果查询的是第一页数据，起始索引可以省略，直接简写为 limit  条数



#### 4.6 案例分析

+ 员工查询

  <div style="text-align:center">
      <img src="images\案例一需求.png" alt="混合型">
  </div>

  ```mysql
  -- 姓名：张
  -- 性别：男
  -- 入职时间：2000-01-01  ~  2015-12-31
  -- 每页显示10条数据
  -- 按照修改时间倒序排序
  select id, username, password, name, gender, image, job, entrydate, create_time, update_time
  from tb_emp
  where name like '张%' and gender = 1 and entrydate between '2000-01-01' and '2015-12-31'
  order by update_time desc
  limit 0 , 10;
  ```

+ 员工信息统计

  <div style="text-align:center">
      <img src="images\案例二需求.png" alt="混合型">
  </div>

  ```mysql
  /*
  	1. 查询男性员工和女性员工的人数
  	此时，查询出来为1--24；2--5，需要将1转换为男性员工，2转换为女性员工输出
  */
  select gender, count(*) from tb_emp group by gender;
  -- 修改后，使用if(条件表达式, true取值 , false取值)来对应
  select if(gender = 1, '男性员工', '女性员工') as 性别, count(*) from tb_emp group by gender;
  ```

  ```mysql
  /*
  	2. 查询员工职位分布
  	case 表达式 when 值1 then 结果1 [when 值2  then  结果2 ...] [else result]     end
  */
  select
      (case job when 1 then '班主任' when 2 then '讲师' when 3 then '学工主管' when 4 then '教研主管' else '未分配职位' end) as 职位,
      count(*) from tb_emp group by job;
  ```

  

### 5. 多表设计

​		在实际开发中，业务之间会存在各种各样的关系，其对应的表结构之间也存在着各种联系：

​        1. 一对多

​		2. 多对多

​		3. 一对一

#### 5.1 一对多

​	在上述例子中，对于每一个员工来说，有其所属部门（学工部、教研部）；对于每个部门来说，其下有很多对应的员工。

​	一个部门对应多个员工；一个员工对应一个部门；他们之间的关系是：一对多。

​	对于这种关系，一的那张表称为**父表**，多的那张表称为**子表**。

​	为了实现，可以在子表中添加一个额外的字段来关联父表。在上述例子中，就是在员工表中添加一个字段来指示该员工属于哪个部门。

​	为了在数据库层面实现这种关系，可以通过数据库中的**外键约束**来解决。

> 外键约束：让两张表的数据建立连接，保证数据的一致性和完整性。  
>
> 对应的关键字：foreign key

外键约束的语法：

```mysql
-- 创建表时指定
create table 表名(
	字段名    数据类型,
	...
	[constraint]   [外键名称]  foreign  key (外键字段名)   references   主表 (主表列名)	
);


-- 建完表后，添加外键
alter table  表名  add constraint  外键名称  foreign key(外键字段名) references 主表(主表列名);
```

那接下来，我们就为员工表的dept_id 建立外键约束，来关联部门表的主键。

```mysql
-- 修改表： 添加外键约束
alter table tb_emp  
add  constraint  fk_dept_id  foreign key (dept_id)  references  tb_dept(id);
```

​	**物理外键和逻辑外键**

- 物理外键
  - 概念：使用foreign key定义外键关联另外一张表。
  - 缺点：
    - 影响增、删、改的效率（需要检查外键关系）。
    - 仅用于单节点数据库，不适用与分布式、集群场景。
    - 容易引发数据库的死锁问题，消耗性能。

- 逻辑外键
  - 概念：在业务层逻辑中，解决外键关联。
  - 通过逻辑外键，就可以很方便的解决上述问题。

> **在现在的企业开发中，很少会使用物理外键，都是使用逻辑外键。 甚至在一些数据库开发规范中，会明确指出禁止使用物理外键 foreign key **



#### 5.2 一对一

一对一关系表在实际开发中应用起来比较简单，通常是用来做单表的拆分，也就是将一张大表拆分成两张小表，将大表中的一些基础字段放在一张表当中，将其他的字段放在另外一张表当中，以此来提高数据的操作效率。

> 一对一的应用场景： 用户表(基本信息+身份信息)
>
> <div style="text-align:center">
>     <img src="images\一对一场景.png" alt="混合型">
> </div>
>
> - 基本信息：用户的ID、姓名、性别、手机号、学历
> - 身份信息：民族、生日、身份证号、身份证签发机关，身份证的有效期(开始时间、结束时间)
>
> 如果在业务系统当中，对用户的基本信息查询频率特别的高，但是对于用户的身份信息查询频率很低，此时出于提高查询效率的考虑，我就可以将这张大表拆分成两张小表，第一张表存放的是用户的基本信息，而第二张表存放的就是用户的身份信息。他们两者之间一对一的关系，一个用户只能对应一个身份证，而一个身份证也只能关联一个用户。

那么在数据库层面怎么去体现上述两者之间是一对一的关系呢？

其实一对一我们可以看成一种特殊的一对多。一对多我们是怎么设计表关系的？是不是在多的一方添加外键。同样我们也可以通过外键来体现一对一之间的关系，我们只需要在任意一方来添加一个外键就可以了。

<div style="text-align:center">
    <img src="images\一对一实现.png" alt="混合型">
</div>

> 一对一 ：在任意一方加入外键，关联另外一方的主键，并且设置外键为唯一的(UNIQUE)



SQL脚本：

~~~mysql
-- 用户基本信息表
create table tb_user(
    id int unsigned  primary key auto_increment comment 'ID',
    name varchar(10) not null comment '姓名',
    gender tinyint unsigned not null comment '性别, 1 男  2 女',
    phone char(11) comment '手机号',
    degree varchar(10) comment '学历'
) comment '用户基本信息表';

-- 用户身份信息表
create table tb_user_card(
    id int unsigned  primary key auto_increment comment 'ID',
    nationality varchar(10) not null comment '民族',
    birthday date not null comment '生日',
    idcard char(18) not null comment '身份证号',
    issued varchar(20) not null comment '签发机关',
    expire_begin date not null comment '有效期限-开始',
    expire_end date comment '有效期限-结束',
    user_id int unsigned not null unique comment '用户ID',
    constraint fk_user_id foreign key (user_id) references tb_user(id)
) comment '用户身份信息表';
~~~



#### 5.3 多对多

多对多的关系在开发中属于也比较常见的。比如：学生和老师的关系，一个学生可以有多个授课老师，一个授课老师也可以有多个学生。在比如：学生和课程的关系，一个学生可以选修多门课程，一个课程也可以供多个学生选修。

案例：学生与课程的关系

- 关系：一个学生可以选修多门课程，一门课程也可以供多个学生选择

- 实现关系：建立第三张中间表，中间表至少包含两个外键，分别关联两方主键

<div style="text-align:center">
    <img src="images\多对多实现.png" alt="混合型">
</div>

SQL脚本：

~~~mysql
-- 学生表
create table tb_student(
    id int auto_increment primary key comment '主键ID',
    name varchar(10) comment '姓名',
    no varchar(10) comment '学号'
) comment '学生表';
-- 学生表测试数据
insert into tb_student(name, no) values ('黛绮丝', '2000100101'),('谢逊', '2000100102'),('殷天正', '2000100103'),('韦一笑', '2000100104');

-- 课程表
create table tb_course(
   id int auto_increment primary key comment '主键ID',
   name varchar(10) comment '课程名称'
) comment '课程表';
-- 课程表测试数据
insert into tb_course (name) values ('Java'), ('PHP'), ('MySQL') , ('Hadoop');

-- 学生课程表（中间表）
create table tb_student_course(
   id int auto_increment comment '主键' primary key,
   student_id int not null comment '学生ID',
   course_id  int not null comment '课程ID',
   constraint fk_courseid foreign key (course_id) references tb_course (id),
   constraint fk_studentid foreign key (student_id) references tb_student (id)
)comment '学生课程中间表';
-- 学生课程表测试数据
insert into tb_student_course(student_id, course_id) values (1,1),(1,2),(1,3),(2,2),(2,3),(3,4);
~~~



### 6. 多表查询

多表查询：查询时从多张表中获取所需数据

> 单表查询的SQL语句：select  字段列表  from  表名;
>
> 那么要执行多表查询，只需要使用逗号分隔多张表即可，如： select   字段列表  from  表1, 表2;

```mysql
-- 准备两张表  员工表 和 部门表；其中员工表使用dept_id字段作为外键来连接部门表（使用逻辑外键）

-- 部门表
create table tb_dept
(
    id          int unsigned primary key auto_increment comment '主键ID',
    name        varchar(10) not null unique comment '部门名称',
    create_time datetime    not null comment '创建时间',
    update_time datetime    not null comment '修改时间'
) comment '部门表';

-- 员工表
create table tb_emp
(
    id          int unsigned primary key auto_increment comment 'ID',
    username    varchar(20)      not null unique comment '用户名',
    password    varchar(32) default '123456' comment '密码',
    name        varchar(10)      not null comment '姓名',
    gender      tinyint unsigned not null comment '性别, 说明: 1 男, 2 女',
    image       varchar(300) comment '图像',
    job         tinyint unsigned comment '职位, 说明: 1 班主任,2 讲师, 3 学工主管, 4 教研主管, 5 咨询师',
    entrydate   date comment '入职时间',
    dept_id     int unsigned comment '部门ID',
    create_time datetime         not null comment '创建时间',
    update_time datetime         not null comment '修改时间'
) comment '员工表';
```

查询用户表和部门表中的数据：

~~~mysql
-- 直接这样查询，会返回 N(emp)*N(dept)个数据，很多数据是无效的   笛卡尔积
select * from  tb_emp , tb_dept;
~~~

为了消除无效的笛卡尔积，需要给多表查询加上连接查询条件：

多表查询可以分为：

1. 连接查询

   - 内连接：相当于查询A、B交集部分数据（显示交集的数据，会导致A或B的数据显示不完整）

   <div style="text-align:center">
       <img src="images\分类图.png" alt="混合型">
   </div>

2. 外连接

   - 左外连接：查询左表所有数据(包括两张表交集部分数据)（全部显示左表数据）

   - 右外连接：查询右表所有数据(包括两张表交集部分数据)（全部显示右表数据）

3. 子查询

   

#### 6.1 内连接查询

内连接查询：查询两表或多表中交集部分数据。

内连接从语法上可以分为：

- 隐式内连接

- 显式内连接

隐式内连接语法：

``` mysql
select  字段列表   from   表1 , 表2   where  条件 ... ;
```

显式内连接语法：

``` mysql
select  字段列表   from   表1  [ inner ]  join 表2  on  连接条件 ... where 其他条件 ;
```

```mysql
-- ============================= 内连接 ==========================
-- A. 查询员工的姓名 , 及所属的部门名称 (隐式内连接实现)
select tb_emp.name, tb_dept.name from tb_emp, tb_dept where tb_emp.dept_id = tb_dept.id;
select e.name, d.name from tb_emp e, tb_dept d where e.dept_id = d.id;  -- 给表其别名，简化操作

-- B. 查询员工的姓名 , 及所属的部门名称 (显式内连接实现)
select e.name, d.name from tb_emp e inner join tb_dept d on e.dept_id = d.id ;
```



#### 6.2 外连接查询

外连接分为两种：左外连接 和 右外连接。

左外连接语法结构：

```mysql
select  字段列表   from   表1  left  [ outer ]  join 表2  on  连接条件 ... ;
```

> 左外连接相当于查询表1(左表)的所有数据，当然也包含表1和表2交集部分的数据。

右外连接语法结构：

```mysql
select  字段列表   from   表1  right  [ outer ]  join 表2  on  连接条件 ... ;
```

> 右外连接相当于查询表2(右表)的所有数据，当然也包含表1和表2交集部分的数据。

```mysql
-- =============================== 外连接 ============================
-- A. 查询员工表 所有 员工的姓名, 和对应的部门名称 (左外连接)
-- 会显示左表的所有内容，包括两张表交集的部分
select e.name, d.name from tb_emp e left outer join tb_dept d on e.dept_id = d.id;

-- B. 查询部门表 所有 部门的名称, 和对应的员工名称 (右外连接)
select d.name, e.name from tb_emp e right outer join tb_dept d on e.dept_id = d.id;
```



#### 6.3 子查询

SQL语句中嵌套select语句，称为嵌套查询，又称子查询。

```sql
SELECT  *  FROM   t1   WHERE  column1 =  ( SELECT  column1  FROM  t2 ... );
```

> 子查询外部的语句可以是insert / update / delete / select 的任何一个，最常见的是 select。

根据子查询结果的不同分为：

1. 标量子查询（子查询结果为单个值[一行一列]）

2. 列子查询（子查询结果为一列，但可以是多行）

3. 行子查询（子查询结果为一行，但可以是多列）

4. 表子查询（子查询结果为多行多列[相当于子查询结果是一张表]）

子查询可以书写的位置：

1. where之后
2. from之后
3. select之后



##### 6.3.1 标量子查询

子查询返回的结果是单个值(数字、字符串、日期等)，最简单的形式，这种子查询称为标量子查询。

常用的操作符： =   <>   >    >=    <   <=   

```mysql
-- 标量子查询
-- A. 查询 "教研部" 的所有员工信息
-- a. 获取 教研部 对应的id
select id from tb_dept where name = '教研部';
-- b. 获取该 id 下的所有员工信息
select * from tb_emp where dept_id = 2;
select * from tb_emp where dept_id = (select id from tb_dept where name = '教研部');

-- B. 查询在 "方东白" 入职之后的员工信息
-- a. 在员工表中查询 方东白 的入职时间
select entrydate from tb_emp where name = '方东白';
-- b. 获取入职时间在该时间之后的员工信息
select * from tb_emp where entrydate > '2012-11-01';
select * from tb_emp where entrydate > (select entrydate from tb_emp where name = '方东白');
```



##### 6.3.2 列子查询

子查询返回的结果是一列(可以是多行)，这种子查询称为列子查询。

常用的操作符：

| **操作符** | **描述**                     |
| ---------- | ---------------------------- |
| IN         | 在指定的集合范围之内，多选一 |
| NOT IN     | 不在指定的集合范围之内       |

```mysql
-- 列子查询
-- A. 查询 "教研部" 和 "咨询部" 的所有员工信息
-- a. 获取 这两个部门对应的 id
select id from tb_dept where name = '教研部' or name = '咨询部';  -- 结果为一列
-- b. 获取这两个部门id对应的员工信息
select * from tb_emp where dept_id in (2, 3);
select * from tb_emp where dept_id in (select id from tb_dept where name = '教研部' or name = '咨询部');
```



##### 6.3.3 行子查询

子查询返回的结果是一行(可以是多列)，这种子查询称为行子查询。

常用的操作符：= 、<> 、IN 、NOT IN

```mysql
-- 行子查询
-- A. 查询与 "韦一笑" 的入职日期 及 职位都相同的员工信息 ;
-- a. 获取 韦一笑 的入职日期及职位信息
select entrydate, job from tb_emp where name = '韦一笑';   -- 结果为一行
-- b. 获取入职时间和职位与韦一笑都相同的员工信息
select * from tb_emp where (entrydate, job) = ('2007-01-01', 2);
select * from tb_emp where (entrydate, job) = (select entrydate, job from tb_emp where name = '韦一笑');
```



##### 6.3.4 表子查询

子查询返回的结果是多行多列，常作为临时表，这种子查询称为表子查询。

```mysql
-- 表子查询
-- A. 查询入职日期是 "2006-01-01" 之后的员工信息 , 及其部门信息(保证员工表完整，使用外连接查询)
-- a. 查询入职日期是 "2006-01-01" 之后的员工信息
select * from tb_emp where entrydate > '2006-01-01';    -- 结果为一张表
-- b. 在获取的子表中，联合部门表，得到最终结果
select e.*, d.name from (select * from tb_emp where entrydate > '2006-01-01') e left outer join tb_dept d on e.dept_id = d.id;
```



#### 6.4 案例分析

- 分类表：category
- 菜品表：dish
- 套餐表：setmeal
- 套餐菜品关系表：setmeal_dish

<div style="text-align:center">
    <img src="images\多表案例.png" alt="混合型">
</div>

**需求实现**

1. 查询价格低于 10元 的菜品的名称 、价格 及其 菜品的分类名称

~~~mysql
/*查询技巧：
     明确1：查询需要用到哪些字段
        菜品名称、菜品价格 、 菜品分类名
     明确2：查询的字段分别归属于哪张表
        菜品表：[菜品名称、菜品价格]
        分类表：[分类名]
     明确3：如查多表，建立表与表之间的关联
        菜品表.caategory_id = 分类表.id
     其他：（其他条件、其他要求）
        价格 < 10
*/
select d.name , d.price , c.name
from dish AS d , category AS c
where d.category_id = c.id
      and d.price < 10;
~~~

2. 查询所有价格在 10元(含)到50元(含)之间 且 状态为"起售"的菜品名称、价格及其分类名称 (即使菜品没有分类 , 也要将菜品查询出来)

~~~mysql
select d.name , d.price, c.name
from dish AS d left join category AS c on d.category_id = c.id
where d.price between 10 and 50
      and d.status = 1;
~~~

3. 查询每个分类下最贵的菜品, 展示出分类的名称、最贵的菜品的价格 

~~~mysql
select c.name , max(d.price)
from dish AS d , category AS c
where d.category_id = c.id
group by c.name;
~~~

4. 查询各个分类下 菜品状态为 "起售" , 并且 该分类下菜品总数量大于等于3 的 分类名称

~~~mysql
/*查询技巧：
     明确1：查询需要用到哪些字段
        分类名称、菜品总数量
     明确2：查询用到的字段分别归属于哪张表
        分类表：[分类名]
        菜品表：[菜品状态]
     明确3：如查多表，建立表与表之间的关联
        菜品表.caategory_id = 分类表.id
     其他：（其他条件、其他要求）
        条件：菜品状态 = 1 (1表示起售)
        分组：分类名
        分组后条件： 总数量 >= 3
*/
select c.name , count(*)
from dish AS d , category AS c
where d.category_id = c.id
      and d.status = 1 -- 起售状态
group by c.name  -- 按照分类名分组
having count(*)>=3; -- 各组后筛选菜品总数据>=3
~~~

5. 查询出 "商务套餐A" 中包含了哪些菜品 （展示出套餐名称、价格, 包含的菜品名称、价格、份数）

~~~mysql
select s.name, s.price, d.name, d.price, sd.copies
from setmeal AS s , setmeal_dish AS sd , dish AS d
where s.id = sd.setmeal_id and sd.dish_id = d.id
      and s.name='商务套餐A';
~~~

6. 查询出低于菜品平均价格的菜品信息 (展示出菜品名称、菜品价格)

~~~mysql
-- 1.计算菜品平均价格
select avg(price) from dish;    -- 查询结果：37.736842
-- 2.查询出低于菜品平均价格的菜品信息
select * from dish where price < 37.736842;

-- 合并以上两条SQL语句
select * from dish where price < (select avg(price) from dish);
~~~



### 7. 事务

场景：学工部整个部门解散了，该部门及部门下的员工都需要删除了。

- 操作：

  ```sql
  -- 删除学工部
  delete from dept where id = 1;  -- 删除成功
  
  -- 删除学工部的员工
  delete from emp where dept_id = 1; -- 删除失败（操作过程中出现错误：造成删除没有成功）
  ```

- 问题：如果删除部门成功了，而删除该部门的员工时失败了，此时就造成了数据的不一致。

​	要解决上述的问题，就需要通过数据库中的事务来解决。

+ 事务是一组操作的集合，它是一个不可分割的工作单位。事务会把所有的操作作为一个整体一起向系统提交或撤销操作请求，即这些操作要么同时成功，要么同时失败。

+ 事务作用：保证在一个事务中多次操作数据库表中数据时，要么全都成功,要么全都失败。

  

MYSQL中有两种方式进行事务的操作：

1. 自动提交事务：即执行一条sql语句提交一次事务。（默认MySQL的事务是自动提交）
2. 手动提交事务：先开启，再提交 

事务操作有关的SQL语句：

| SQL语句                        | 描述             |
| ------------------------------ | ---------------- |
| start transaction;  /  begin ; | 开启手动控制事务 |
| commit;                        | 提交事务         |
| rollback;                      | 回滚事务         |

> 手动提交事务使用步骤：
>
> - 第1种情况：开启事务  =>  执行SQL语句   =>  成功  =>  提交事务
> - 第2种情况：开启事务  =>  执行SQL语句   =>  失败  =>  回滚事务



使用事务控制删除部门和删除该部门下的员工的操作：

```sql
-- 开启事务
start transaction ;

-- 删除学工部
delete from tb_dept where id = 1;

-- 删除学工部的员工
delete from tb_emp where dept_id = 1;
```

- 上述的这组SQL语句，如果如果执行成功，则提交事务

```sql
-- 提交事务 (成功时执行)
commit ;
```

- 上述的这组SQL语句，如果如果执行失败，则回滚事务

```sql
-- 回滚事务 (出错时执行)
rollback ;
```



面试题：事务有哪些特性？

- 原子性（Atomicity）：事务是不可分割的最小单元，要么全部成功，要么全部失败。
- 一致性（Consistency）：事务完成时，必须使所有的数据都保持一致状态。
- 隔离性（Isolation）：数据库系统提供的隔离机制，保证事务在不受外部并发操作影响的独立环境下运行。
- 持久性（Durability）：事务一旦提交或回滚，它对数据库中的数据的改变就是永久的。

> 事务的四大特性简称为：ACID



- **原子性（Atomicity）** ：原子性是指事务包装的一组sql是一个不可分割的工作单元，事务中的操作要么全部成功，要么全部失败。

- **一致性（Consistency）**：一个事务完成之后数据都必须处于一致性状态。

​		如果事务成功的完成，那么数据库的所有变化将生效。

​		如果事务执行出现错误，那么数据库的所有变化将会被回滚(撤销)，返回到原始状态。

- **隔离性（Isolation）**：多个用户并发的访问数据库时，一个用户的事务不能被其他用户的事务干扰，多个并发的事务之间要相互隔离。

​		一个事务的成功或者失败对于其他的事务是没有影响。

- **持久性（Durability）**：一个事务一旦被提交或回滚，它对数据库的改变将是永久性的，哪怕数据库发生异常，重启之后数据亦然存在。



### 8. 索引

索引(index)：是帮助数据库高效获取数据的**数据结构** 。

- 简单来讲，就是使用索引可以提高查询的效率。

优点：

1. 提高数据查询的效率，降低数据库的IO成本。
2. 通过索引列对数据进行排序，降低数据排序的成本，降低CPU消耗。

缺点：

1. 索引会占用存储空间。
2. 索引大大提高了查询效率，同时却也降低了insert、update、delete的效率，因为这几个操作需要对索引的数据重新调整，来保持原本的数据结构。

#### 8.1 结构

MySQL数据库支持的索引结构有很多，如：Hash索引、B+Tree索引、Full-Text索引等。

我们平常所说的索引，如果没有特别指明，都是指默认的 B+Tree 结构组织的索引。

之前所学的**二叉查找树** 和 **红黑树**会导致树过于高，因为他们一个节点只能有两个儿子节点。这会导致查询的效率低下。

> 说明：如果数据结构是红黑树，那么查询1000万条数据，根据计算树的高度大概是23左右，这样确实比之前的方式快了很多，但是如果高并发访问，那么一个用户有可能需要23次磁盘IO，那么100万用户，那么会造成效率极其低下。所以为了减少红黑树的高度，那么就得增加树的宽度，就是不再像红黑树一样每个节点只能保存一个数据，可以引入另外一种数据结构，一个节点可以保存多个数据，这样宽度就会增加从而降低树的高度。这种数据结构例如BTree就满足。

<div style="text-align:center">
    <img src="images\B+树.png" alt="混合型">
</div>

B+Tree结构：

- 每一个节点，可以存储多个key（有n个key，就有n个指针）
- 节点分为：叶子节点、非叶子节点
  - 叶子节点，就是最后一层子节点，**所有的数据都存储在叶子节点上**
  - 非叶子节点，不是树结构最下面的节点，用于索引数据，存储的的是：key+指针
- 为了提高范围查询效率，**叶子节点形成了一个双向链表**，便于数据的排序及区间范围查询



> **拓展：**
>
> 非叶子节点都是由key+指针域组成的，一个key占8字节，一个指针占6字节，而一个节点总共容量是16KB，那么可以计算出一个节点可以存储的元素个数：16*1024字节 / (8+6)=1170个元素。
>
> - 查看mysql索引节点大小：show global status like 'innodb_page_size';    -- 节点大小：16384
>
> 当根节点中可以存储1170个元素，那么根据每个元素的地址值又会找到下面的子节点，每个子节点也会存储1170个元素，那么第二层即第二次IO的时候就会找到数据大概是：1170*1170=135W。也就是说B+Tree数据结构中只需要经历两次磁盘IO就可以找到135W条数据。
>
> 对于第二层每个元素有指针，那么会找到第三层，第三层由key+数据组成，假设key+数据总大小是1KB，而每个节点一共能存储16KB，所以一个第三层一个节点大概可以存储16个元素(即16条记录)。那么结合第二层每个元素通过指针域找到第三层的节点，第二层一共是135W个元素，那么第三层总元素大小就是：135W*16结果就是2000W+的元素个数。
>
> 结合上述分析B+Tree有如下优点：
>
> - 千万条数据，B+Tree可以控制在小于等于3的高度
> - 所有的数据都存储在叶子节点上，并且底层已经实现了按照索引进行排序，还可以支持范围查询，叶子节点是一个双向链表，支持从小到大或者从大到小查找



#### 8.2 语法

**创建索引**

~~~mysql
create  [ unique ]  index 索引名 on  表名 (字段名,... ) ;
~~~

案例：为tb_emp表的name字段建立一个索引

~~~mysql
create index idx_emp_name on tb_emp(name);
~~~

> 在创建表时，如果添加了主键和唯一约束，就会默认创建：主键索引、唯一约束
>
> <div style="text-align:center">
>     <img src="images\索引.png" alt="混合型">
> </div>



> 注意事项：
>
> - 主键字段，在建表时，会自动创建主键索引（查找效率最高）
> - 添加唯一约束时，数据库实际上会添加唯一索引



**查看索引**

~~~mysql
show  index  from  表名;
~~~

案例：查询 tb_emp 表的索引信息

~~~mysql
show  index  from  tb_emp;
~~~



**删除索引**

~~~mysql
drop  index  索引名  on  表名;
~~~

案例：删除 tb_emp 表中name字段的索引

~~~mysql
drop index idx_emp_name on tb_emp;
~~~

















