package com.xaut.mapper;

import com.xaut.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    /**
     * 根据id删除数据
     * @param id    用户id
     */
    @Delete("delete from emp where id = #{id}")//使用#{key}方式获取方法中的参数值
    public void delete(Integer id);

    /**
     * 新增员工
     * @param
     */
    // 使用该注解获取插入数据的主键，并将该主键信息赋值给实体类Emp的id成员变量
    @Options(useGeneratedKeys = true, keyProperty="id")
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            " values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    // 由于参数过多，使用实体类Emp对其进行封装。上述sql语句中的占位参数名要与实体类的成员变量名一致
    public void insert(Emp emp);

    /**
     * 更新员工信息
     * @param
     */
    @Update("update emp set username=#{username}, name=#{name}, gender=#{gender}, image=#{image}, job=#{job}, " +
            "entrydate=#{entrydate}, dept_id=#{deptId}, update_time=#{updateTime} where id=#{id}")
    public void update(Emp emp);

    /**
     * 根据员工id查询信息
     * @param id
     */
    @Select("select id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time from emp where id=#{id}")
    public Emp getById(Integer id);

    /**
     * 根据条件查询员工信息
     * @param
     */
//    @Select("select * from emp " +
//            "where name like concat('%',#{name},'%') " +
//            "and gender = #{gender} " +
//            "and entrydate between #{begin} and #{end} " +
//            "order by update_time desc")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);
}
