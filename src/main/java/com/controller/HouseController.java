package com.controller;

import com.condition.UsersCondition;
import com.github.pagehelper.PageInfo;
import com.pojo.House;
import com.pojo.Users;
import com.service.HouseService;
import com.service.UsersService;
import com.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2019-12-31 18:59:59
 */
@Controller("con")
@RequestMapping("/house")
public class HouseController {

    @Autowired
    HouseService houseService;

    @RequestMapping("/getAllHouse")
    @ResponseBody  //map返回到前台就变成了json格式,运营商后台得到所有被发布的房屋信息
    public Map<String, Object> getAllHouse(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer rows, UsersCondition condition){
        PageInfo<House> pageInfo = houseService.showHouse2(page, rows, condition);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    //异步加载查询房屋信息,显示所有的已经审核通过的房子
    @RequestMapping("/getAllHouse1")
    @ResponseBody  //map返回到前台就变成了json格式,运营商后台得到所有被发布的房屋信息
    public Map<String, Object> getAllHouse1(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer rows, UsersCondition condition){
        PageInfo<House> pageInfo = houseService.showHouse3(page, rows, condition);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }
    //异步请求动态查询房屋信息
    //异步加载查询房屋信息,显示所有的未审核通过的房子
    @RequestMapping("/getAllHouse2")
    @ResponseBody  //map返回到前台就变成了json格式,运营商后台得到所有被发布的房屋信息
    public Map<String, Object> getAllHouse2(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer rows, UsersCondition condition){
        PageInfo<House> pageInfo = houseService.showHouse4(page, rows, condition);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    //批量审核区域信息
    @RequestMapping("/verifyByBatch")
    @ResponseBody
    public Map<String, Object> verifyByBatch(String arr0){
        Map<String, Object> map = new HashMap<>();
        String[] arr = arr0.split(",");
        Integer returnKey = houseService.verifyHouseServiceByBatch(arr);
        map.put("returnKey", returnKey);
        return map;
    }
}
