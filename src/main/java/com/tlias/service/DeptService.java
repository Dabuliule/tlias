package com.tlias.service;


import com.tlias.pojo.Dept;

import java.util.List;

public interface DeptService {

    List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);

    Dept get(Integer id);

    void update(Dept dept);
}
