package com.service;

import com.github.pagehelper.PageInfo;
import com.pojo.Type;
import com.utils.PageUtils;

import java.util.List;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2019-12-21 11:25:47
 */
public interface TypeService {
    /* *
     *
     *
     * @description: 分页查询所有的区域
     * @param PageUtils 封装了页码page和此页记录条数rows
     * @return: PageInfo
     * @author: Aiden
     * @time: 2019-12-21 11:26:36
     *
     */
    PageInfo<Type> findAllType(PageUtils pageUtils);

    /**
     *
     *
     * @description:
     * @param Type //封装区域的名字
     * @return: 添加成功就返回1
     * @author: Aiden
     * @time: 2019-12-22 19:01:24
     */
    Integer addTypeService(Type Type);

    /**
     *
     *
     * @description: 根据区域id查询区域，回显与修改弹框
     * @param id
     * @return: Type
     * @author: Aiden
     * @time: 2019-12-23 09:55:15
     */
    Type findTypeById(Integer id);

    /**
     *
     *
     * @description: 点击保存更新按钮后将进行更新业务
     * @param Type，封装了序列化的数据
     * @return:
     * @author: Aiden
     * @time: 2019-12-23 10:08:56
     */
    Integer upType(Type Type);

    /**
     *
     *
     * @description:
     * @param id 区域的id
     * @return: 影响的行数
     * @author: Aiden
     * @time: 2019-12-23 14:21:36
     */
     Integer removeTypeById(Integer id);

     /**
      *
      *
      * @description:
      * @param ids 选中的区域id
      * @return: 影响的行数
      * @author: Aiden
      * @time: 2019-12-24 13:46:04
      */
     Integer removeTypeByBatch(Integer[] ids);

     /**
      *
      *
      * @description:
      * @param
      * @return: 所有的房屋类型
      * @author: Aiden
      * @time: 2019-12-27 14:08:31
      */
      List<Type> findAllTypeForPub();

}






















