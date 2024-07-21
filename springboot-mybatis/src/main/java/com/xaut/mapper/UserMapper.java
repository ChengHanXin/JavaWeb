package com.xaut.mapper;

import com.xaut.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// 在运行时，会自动生成该接口的实现类对象（代理对象），并且将该对象交给IOC容器管理
@Mapper
public interface UserMapper {

    // 获取user中的所有信息，并将每一条数据封装为User实体类对象，最后放入List集合中返回
    @Select("select * from user")
    public List<User> findAll();

}
