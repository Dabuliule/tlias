package com.tlias.mapper;


import com.tlias.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;


@Mapper
public interface EmpMapper {

    List<Emp> selectByConditions(String name, Short gender, LocalDate begin, LocalDate end);

    void deleteByIds(List<Integer> ids);

    @Insert("INSERT INTO emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "VALUES (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Emp emp);

    @Select("select *from emp where id = #{id}")
    Emp selectById(Integer id);

    void update(Emp emp);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(String username, String password);

    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}
