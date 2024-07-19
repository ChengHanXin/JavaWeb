package com.xaut.controller;


import com.xaut.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
public class RequestController {

    @RequestMapping("/simpleRequest")
    // 原始servlt方式 简单参数
    public String simpleRequest(HttpServletRequest request){
        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        int age = Integer.parseInt(ageStr);
        System.out.println(name + ":" + age);
        return "ok";
    }

//    @RequestMapping("/simpleParam")
//    // springboot 简单参数
//    public String simpelRequest(String name, int age){
//        // http://localhost:8080/simpleParam?name=tom&age=20
//        // 需要保证请求的参数名字一一对应，即定义同名的参数，而且这种方式会自动进行类型转换
//        System.out.println(name + ":" + age);
//        return "ok";
//    }

    @RequestMapping("/simpleParam")
    // springboot 简单参数
    public String simpelRequest(@RequestParam(name = "name") String userName, int age){
        // http://localhost:8080/simpleParam?name=tom&age=20
        // 如果参数名字不统一，则需要添加@RequestParam注解，将其name属性设置为请求参数名，完成映射
        // 该注解中有一个属性required，默认为true表示必须需要这个参数；设置为false，可以在请求时不传递该参数
        System.out.println(userName + ":" + age);
        return "ok";
    }

    @RequestMapping("/simplePoJo")
    // 简单实体对象，使用一个类将请求参数封装起来，通过一个实体类来接收对象
    public String simplePoJo(User user){
        // http://localhost:8080/simplePoJo?name=tom&age=10
        System.out.println(user);
        return "OK";
    }

    @RequestMapping("/arrayParam")
    public String arrayParam(String[] hobby){
        System.out.println(Arrays.toString(hobby));
        return "OK";
    }

    @RequestMapping("/listParam")
    public String listParam(@RequestParam List<String> hobby){
        System.out.println(hobby);
        return "OK";
    }

    @RequestMapping("/dataParam")
    public String dataParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDate updateDate){
        System.out.println(updateDate);
        return "OK";
    }

    @RequestMapping("/jsonParam")
    public String jsonParam(@RequestBody User user){
        System.out.println(user);
        return "OK";
    }

//    @RequestMapping("/pathParam/{id}")
//    public String pathParam(@PathVariable Integer id){
//        System.out.println(id);
//        return "OK";
//    }

    @RequestMapping("/pathParam/{id}/{name}")
    public String pathParam(@PathVariable Integer id, @PathVariable String name){
        System.out.println(id + name);
        return "OK";
    }
}
