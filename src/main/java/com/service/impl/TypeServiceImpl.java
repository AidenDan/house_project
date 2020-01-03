package com.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.TypeMapper;
import com.mapper.StreetMapper;
import com.pojo.Type;
import com.pojo.TypeExample;
import com.service.TypeService;
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
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeMapper TypeMapper;
    @Autowired
    StreetMapper streetMapper;

    @Override
    public PageInfo<Type> findAllType(PageUtils pageUtils) {
        TypeExample TypeExample = new TypeExample();
        //开启分页
        PageHelper.startPage(pageUtils.getPage(), pageUtils.getRows());
        //查询所有的区域
        List<Type> TypeList = TypeMapper.selectByExample(TypeExample);
        //封装区域信息到PageInfo
        return new PageInfo<>(TypeList);
    }

    @Override
    public Integer addTypeService(Type Type) {
        //动态插入，有这个条件就插入这个条件
        return TypeMapper.insertSelective(Type);
    }

    //根据区域id查询区域，用于回显
    @Override
    public Type findTypeById(Integer id) {
        return TypeMapper.selectByPrimaryKey(id);
    }

    //更新区域
    @Override
    public Integer upType(Type Type) {
        return TypeMapper.updateByPrimaryKeySelective(Type);
    }

    //删除区域的同时要把区域对应的街道给删除，执行了2个SQL，开启事物
    @Transactional
    @Override
    public Integer removeTypeById(Integer id) {
        Integer i1 = TypeMapper.deleteByPrimaryKey(id);
      /*  int i =10;
        int j =0;
        i = i/j;*/
       // Integer i2 = streetMapper.delStreetByType_Id(id);
        return 1;
    }

    @Override
    public Integer removeTypeByBatch(Integer[] ids) {
        return TypeMapper.delTypeByBatch(ids);
    }

    //查询所有的房屋类型
    @Override
    public List<Type> findAllTypeForPub() {
        TypeExample typeExample = new TypeExample();
        return TypeMapper.selectByExample(typeExample);
    }
}
