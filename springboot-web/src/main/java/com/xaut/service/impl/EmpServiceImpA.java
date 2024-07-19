package com.xaut.service.impl;

import com.xaut.dao.EmpDao;
import com.xaut.dao.impl.EmpDaoImpA;
import com.xaut.pojo.Emp;
import com.xaut.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public class EmpServiceImpA implements EmpService {

    @Autowired
    private EmpDao empDao;

    @Override
    public List<Emp> listEmp() {
        List<Emp> empList = empDao.getEmpList();

        empList.forEach(new Consumer<Emp>() {
            @Override
            public void accept(Emp emp) {
                if ("1".equals(emp.getGender())){
                    emp.setGender("男");
                } else if ("2".equals(emp.getGender())){
                    emp.setGender("女");
                }

                // 1: 讲师, 2: 班主任 , 3: 就业指导
                if ("1".equals(emp.getJob())){
                    emp.setJob("讲师");
                } else if ("2".equals(emp.getJob())){
                    emp.setJob("班主任");
                } else if ("3".equals(emp.getJob())){
                    emp.setJob("就业指导");
                }
            }
        });
        return empList;
    }
}
