package com.xaut.controller;

import com.xaut.pojo.Address;
import com.xaut.pojo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResponseController {

    @RequestMapping("/hi")
    public String hi(){
        System.out.println("hello world");
        return "hello";
    }

    @RequestMapping("/getAdd")
    public Result getAdd(){
        Address address = new Address();
        address.setProvince("陕西");
        address.setCity("西安");
//        return address;
        return Result.success(address);
    }

    @RequestMapping("/getAddList")
    public List<Address> getAddList(){
        List<Address> list = new ArrayList<Address>();
        Address address1 = new Address();
        address1.setProvince("陕西");
        address1.setCity("西安");

        Address address2 = new Address();
        address2.setProvince("陕西");
        address2.setCity("宝鸡");

        list.add(address1);
        list.add(address2);

        return list;
    }
}
