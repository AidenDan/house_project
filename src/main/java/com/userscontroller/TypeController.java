package com.userscontroller;

import com.pojo.Type;
import com.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2019-12-27 14:05:50
 */
@Controller("typeController2")
@RequestMapping("/t")
public class TypeController {

    @Autowired
    TypeService typeService;

    //查询所有的房屋类型
    @RequestMapping("/getType")
    @ResponseBody
    public List<Type> getAllType(){
        return typeService.findAllTypeForPub();
    }
}
