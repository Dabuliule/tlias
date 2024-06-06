package com.tlias.mapper;


import com.tlias.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface DeptMapper {

    @Select("select * from dept")
    List<Dept> selectAll();

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into dept (name, create_time, update_time) value (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    @Select("select * from dept where id = #{id}")
    Dept selectById(Integer id);

    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
