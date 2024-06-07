package com.tlias.service.impl;

import com.tlias.mapper.DeptLogMapper;
import com.tlias.mapper.DeptMapper;
import com.tlias.mapper.EmpMapper;
import com.tlias.pojo.Dept;
import com.tlias.pojo.DeptLog;
import com.tlias.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    private final DeptMapper deptMapper;
    private final EmpMapper empMapper;
    private final DeptLogMapper deptLogMapper;

    @Autowired
    public DeptServiceImpl(DeptMapper deptMapper, EmpMapper empMapper, DeptLogMapper deptLogMapper) {
        this.deptMapper = deptMapper;
        this.empMapper = empMapper;
        this.deptLogMapper = deptLogMapper;
    }

    @Override
    public List<Dept> list() {
        return deptMapper.selectAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        try {
            deptMapper.deleteById(id);
            empMapper.deleteByDeptId(id);
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setDescription("执行了删除部门的操作,此次解散的是" + id + "号部门");
            deptLog.setCreateTime(LocalDateTime.now());
            deptLogMapper.insert(deptLog);
        }
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept get(Integer id) {
        return deptMapper.selectById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
