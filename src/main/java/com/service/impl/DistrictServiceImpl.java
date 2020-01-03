package com.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.DistrictMapper;
import com.mapper.StreetMapper;
import com.pojo.District;
import com.pojo.DistrictExample;
import com.service.DistrictService;
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
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    DistrictMapper districtMapper;
    @Autowired
    StreetMapper streetMapper;

    @Override
    public PageInfo<District> findAllDistrict(PageUtils pageUtils) {
        DistrictExample districtExample = new DistrictExample();
        //开启分页
        PageHelper.startPage(pageUtils.getPage(), pageUtils.getRows());
        //查询所有的区域
        List<District> districtList = districtMapper.selectByExample(districtExample);
        //封装区域信息到PageInfo
        return new PageInfo<>(districtList);
    }

    @Override
    public Integer addDistrictService(District district) {
        //动态插入，有这个条件就插入这个条件
        return districtMapper.insertSelective(district);
    }

    //根据区域id查询区域，用于回显
    @Override
    public District findDistrictById(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    //更新区域
    @Override
    public Integer upDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    //删除区域的同时要把区域对应的街道给删除，执行了2个SQL，开启事物
    @Transactional
    @Override
    public Integer removeDistrictById(Integer id) {
        Integer i1 = districtMapper.deleteByPrimaryKey(id);
      /*  int i =10;
        int j =0;
        i = i/j;*/
        Integer i2 = streetMapper.delStreetByDistrict_Id(id);
        return 1;
    }

    @Override
    public Integer removeDistrictByBatch(Integer[] ids) {
        return districtMapper.delDisrictByBatch(ids);
    }

    //查询所有的区域用于发布
    @Override
    public List<District> findAllDistrictForPub() {
        DistrictExample districtExample = new DistrictExample();
        return districtMapper.selectByExample(districtExample);
    }
}
