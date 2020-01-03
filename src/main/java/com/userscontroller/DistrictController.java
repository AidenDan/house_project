package com.userscontroller;

import com.github.pagehelper.PageInfo;
import com.pojo.District;
import com.service.DistrictService;
import com.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2019-12-21 11:24:08
 */
@RestController(value = "districtController2")
@RequestMapping("/d")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @RequestMapping("/getAllDistrict")
    public List<District> getAllDistrict(){
        return districtService.findAllDistrictForPub();
    }


}
















