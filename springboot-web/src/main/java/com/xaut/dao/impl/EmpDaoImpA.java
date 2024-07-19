package com.xaut.dao.impl;

import com.xaut.dao.EmpDao;
import com.xaut.pojo.Emp;
import com.xaut.utils.XmlParserUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpDaoImpA implements EmpDao {

    @Override
    public List<Emp> getEmpList() {
        String filePath = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        List<Emp> empList = XmlParserUtils.parse(filePath, Emp.class);
        return empList;
    }
}
