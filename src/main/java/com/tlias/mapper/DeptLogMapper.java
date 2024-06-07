package com.tlias.mapper;

import com.tlias.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface DeptLogMapper {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Insert("insert into dept_log (create_time, description) value (#{createTime}, #{description})")
    void insert(DeptLog deptLog);
}
