package com.controller;

import com.github.pagehelper.PageInfo;
import com.pojo.District;
import com.service.DistrictService;
import com.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Id;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2019-12-21 11:24:08
 */
@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @RequestMapping("/getAllDistrictByPage")
    public Map<String, Object> getAllDistrictByPage(PageUtils pageUtils){
        Map<String, Object> map = new HashMap<>();
        PageInfo<District> pageInfo = districtService.findAllDistrict(pageUtils);
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        System.out.println("操蛋"+map);
        return map;
    }

    //添加区域信息
    @RequestMapping("/addDistrict")
    public Map<String, Object> addDistrict(District district){
        Map<String, Object> map = new HashMap<>();
        Integer returnKey = districtService.addDistrictService(district);
        map.put("returnKey", returnKey);
        return map;
    }

    //根据id查询区域信息
    @RequestMapping("/getDistrictById")
    public District getDistrictById(Integer id){
        return districtService.findDistrictById(id);
    }

    //修改区域信息
    @RequestMapping("/upDistrict")
    public Map<String, Object> upDistrict(District district){
        Integer returnKey = districtService.upDistrict(district);
        Map<String, Object> map = new HashMap<>();
        map.put("returnKey", returnKey);
        return  map;
    }

    //删除区域信息
    @RequestMapping("/delDistrictById")
    public Map<String, Object> delDistrictById(Integer id){
        Map<String, Object> map = new HashMap<>();
        try {
            //删除失败的话就把异常信息try cath起来
            Integer returnKey = districtService.removeDistrictById(id);
            map.put("returnKey", returnKey);
        } catch (Exception e) {
            map.put("returnKey", -1);
        }
        return  map;
    }

    //批量删除区域信息
    @RequestMapping("/delDistrictByBatch")
    public HashMap<String, Object> delDistrictByBatch(String ids){
        HashMap<String, Object> map = new HashMap<>();
        String[] split = ids.split(",");
        Integer[] arr = new Integer[split.length];
        for (int i = 0; i <split.length ; i++) {
            arr[i] = new Integer(split[i] );
        }
        Integer returnKey = districtService.removeDistrictByBatch(arr);
        map.put("returnKey", returnKey);
        return map;
    }
}
















