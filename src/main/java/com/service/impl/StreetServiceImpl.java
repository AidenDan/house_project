package com.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.StreetMapper;
import com.mapper.StreetMapper;
import com.pojo.Street;
import com.pojo.StreetExample;
import com.service.StreetService;
import com.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2019-12-21 11:26:05
 */
@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    StreetMapper StreetMapper;
    /*@Autowired
    StreetMapper streetMapper;*/

    @Override
    public PageInfo<Street> findAllStreet(PageUtils pageUtils) {
        StreetExample StreetExample = new StreetExample();
        //开启分页
        PageHelper.startPage(pageUtils.getPage(), pageUtils.getRows());
        //查询所有的区域
        List<Street> StreetList = StreetMapper.selectByExample(StreetExample);
        //封装区域信息到PageInfo
        return new PageInfo<>(StreetList);
    }

    @Override
    public Integer addStreetService(Street Street) {
        //动态插入，有这个条件就插入这个条件
        return StreetMapper.insertSelective(Street);
    }

    //根据区域id查询区域，用于回显
    @Override
    public Street findStreetById(Integer id) {
        return StreetMapper.selectByPrimaryKey(id);
    }

    //更新区域
    @Override
    public Integer upStreet(Street Street) {
        return StreetMapper.updateByPrimaryKeySelective(Street);
    }

    //删除区域的同时要把区域对应的街道给删除，执行了2个SQL，开启事物
    @Transactional
    @Override
    public Integer removeStreetById(Integer id) {
        Integer i1 = StreetMapper.deleteByPrimaryKey(id);
      /*  int i =10;
        int j =0;
        i = i/j;*/
        //Integer i2 = streetMapper.delStreetByStreet_Id(id);
        return 1;
    }

    @Override
    public Integer removeStreetByBatch(Integer[] ids) {
        return StreetMapper.delStreetByBatch(ids);
    }

    //查询所有的街区用于发布
    @Override
    public List<Street> findAllStreetForPub(Integer districtId) {
        StreetExample streetExample = new StreetExample();
        StreetExample.Criteria criteria = streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(districtId);
        return StreetMapper.selectByExample(streetExample);
    }
}
