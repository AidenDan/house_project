package com.controller;

import com.github.pagehelper.PageInfo;
import com.pojo.Users;
import com.service.UsersService;
import com.utils.MD5Utils;
import com.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2019-12-21 11:24:08
 */
@RestController
@RequestMapping("/Users")
public class UsersController {

    @Autowired
    private UsersService UsersService;

    //动态分页查询所有的用户
    @RequestMapping("/getAllUsersByPage")
    public Map<String, Object> getAllUsersByPage(PageUtils pageUtils){
        Map<String, Object> map = new HashMap<>();
        PageInfo<Users> pageInfo = UsersService.findAllUsers(pageUtils);
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        System.out.println("操蛋"+map);
        return map;
    }

    //添加区域信息
    @RequestMapping("/addUsers")
    public Map<String, Object> addUsers(Users Users){
        Map<String, Object> map = new HashMap<>();
        Users.setPassword(MD5Utils.md5Encrypt(Users.getPassword()));
        Integer returnKey = UsersService.addUsersService(Users);
        map.put("returnKey", returnKey);
        return map;
    }

    //根据id查询区域信息
    @RequestMapping("/getUsersById")
    public Users getUsersById(Integer id){
        return UsersService.findUsersById(id);
    }

    //修改区域信息
    @RequestMapping("/upUsers")
    public Map<String, Object> upUsers(Users Users){
        Integer returnKey = UsersService.upUsers(Users);
        Map<String, Object> map = new HashMap<>();
        map.put("returnKey", returnKey);
        return  map;
    }

    //删除区域信息
    @RequestMapping("/delUsersById")
    public Map<String, Object> delUsersById(Integer id){
        Map<String, Object> map = new HashMap<>();
        try {
            //删除失败的话就把异常信息try cath起来
            Integer returnKey = UsersService.removeUsersById(id);
            map.put("returnKey", returnKey);
        } catch (Exception e) {
            map.put("returnKey", -1);
        }
        return  map;
    }

    //批量删除区域信息
    @RequestMapping("/delUsersByBatch")
    public Map<String, Object> delUsersByBatch(String ids){
        Map<String, Object> map = new HashMap<>();
        String[] split = ids.split(",");
        Integer[] arr = new Integer[split.length];
        for (int i = 0; i <split.length ; i++) {
            arr[i] = new Integer(split[i] );
        }        
        Integer returnKey = UsersService.removeUsersByBatch(arr);
        map.put("returnKey", returnKey);
        return map;
    }

    //根据房屋id审核信息
    @RequestMapping("/getUsersById1")
    public Map<String, Object> getUsersById1(String id){
        Integer i = UsersService.findUsersById1(id);
        HashMap<String, Object> map = new HashMap<>();
            map.put("returnKey", i);
            return map;
    }
}
















