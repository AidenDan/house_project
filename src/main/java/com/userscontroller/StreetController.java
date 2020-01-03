package com.userscontroller;

import com.pojo.Street;
import com.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2019-12-27 15:07:39
 */
@RestController(value = "streetController2")
@RequestMapping("/s")
public class StreetController {
    @Autowired
    StreetService streetService;

    @RequestMapping("/getStreet")
    public List<Street> getAllStreetForPub(Integer districtId){
        return streetService.findAllStreetForPub(districtId);
    }
}
