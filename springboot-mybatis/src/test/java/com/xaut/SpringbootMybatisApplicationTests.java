package com.xaut;

import com.xaut.mapper.EmpMapper;
import com.xaut.mapper.UserMapper;
import com.xaut.pojo.Emp;
import com.xaut.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class SpringbootMybatisApplicationTests {

    // 该对象为一个接口，不能直接创建其实体对象；但是系统已经将其托管给IOC容器，我们可以使用依赖注入来完成。
    @Autowired
    private UserMapper userMapper;

    @Test
    public void getUserList(){
        List<User> userList =  userMapper.findAll();
        userList.forEach(System.out::println);
    }

    @Autowired
    private EmpMapper empMapper;
    @Test
    public void testDelete(){
        empMapper.delete(16);
    }

    @Test
    public void testInsert(){
        //创建员工对象
        Emp emp = new Emp();
        emp.setUsername("tom3");
        emp.setName("汤姆3");
        emp.setImage("1.jpg");
        emp.setGender((short)1);
        emp.setJob((short)1);
        emp.setEntrydate(LocalDate.of(2000,1,1));
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        emp.setDeptId(1);

        //调用inset方法
        empMapper.insert(emp);
        System.out.println("id为" + emp.getId());
    }

    @Test
    public void testUpdate(){
        //要修改的员工信息
        Emp emp = new Emp();
        emp.setId(18);
        emp.setUsername("Tom1");
        emp.setPassword(null);
        emp.setName("汤姆1");
        emp.setImage("2.jpg");
        emp.setGender((short)1);
        emp.setJob((short)2);
        emp.setEntrydate(LocalDate.of(2012,1,1));
        emp.setCreateTime(null);
        emp.setUpdateTime(LocalDateTime.now());
        emp.setDeptId(2);
        //调用方法，修改员工数据
        empMapper.update(emp);
    }

    @Test
    public void testGetById(){
        Emp emp = empMapper.getById(20);
        System.out.println(emp);
    }

//    @Test
//    public void testGetByInfo(){
//        List<Emp> list = empMapper.list("张", (short)1, LocalDate.of(2000,1,1), LocalDate.of(2020,1,1));
//        System.out.println(list);
//    }
    @Test
    public void testGetByInfo(){
//        List<Emp> list = empMapper.list("张", (short)1, null, null);
        List<Emp> list = empMapper.list(null, (short)1, null, null);
        System.out.println(list);
    }
}
