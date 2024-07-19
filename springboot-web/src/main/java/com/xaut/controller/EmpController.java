package com.xaut.controller;

import com.xaut.pojo.Emp;
import com.xaut.pojo.Result;
import com.xaut.service.EmpService;
import com.xaut.service.impl.EmpServiceImpA;
import com.xaut.utils.XmlParserUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Consumer;

@RestController
public class EmpController {
    // 使用接口多态
//    @Qualifier("empServiceImpA")
//    @Autowired
    @Resource(name = "empServiceImpB")
    private EmpService empService;

    @RequestMapping("/listEmp")
    public Result getEmp(){
        List<Emp> empList = empService.listEmp();

        // 返回响应
        return Result.success(empList);
    }
}

//public Result getEmp(){
//    // 解析xml文件
//    String filePath = this.getClass().getClassLoader().getResource("emp.xml").getFile();
//    List<Emp> empList = XmlParserUtils.parse(filePath, Emp.class);
//    // 数据转换
////        empList.stream().forEach(
////                emp -> {
////                    // <!-- 1: 男, 2: 女 -->
////                    if ("1".equals(emp.getGender())){
////                        emp.setGender("男");
////                    } else if ("2".equals(emp.getGender())){
////                        emp.setGender("女");
////                    }
////
////                    // 1: 讲师, 2: 班主任 , 3: 就业指导
////                    if ("1".equals(emp.getJob())){
////                        emp.setJob("讲师");
////                    } else if ("2".equals(emp.getJob())){
////                        emp.setJob("班主任");
////                    } else if ("3".equals(emp.getJob())){
////                        emp.setJob("就业指导");
////                    }
////                }
////        );
//
//    empList.forEach(new Consumer<Emp>() {
//        @Override
//        public void accept(Emp emp) {
//            if ("1".equals(emp.getGender())){
//                emp.setGender("男");
//            } else if ("2".equals(emp.getGender())){
//                emp.setGender("女");
//            }
//
//            // 1: 讲师, 2: 班主任 , 3: 就业指导
//            if ("1".equals(emp.getJob())){
//                emp.setJob("讲师");
//            } else if ("2".equals(emp.getJob())){
//                emp.setJob("班主任");
//            } else if ("3".equals(emp.getJob())){
//                emp.setJob("就业指导");
//            }
//        }
//    });
//
//    // 返回响应
//    return Result.success(empList);
//}
