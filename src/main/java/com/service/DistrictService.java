package com.service;

import com.github.pagehelper.PageInfo;
import com.pojo.District;
import com.utils.PageUtils;

import java.util.List;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2019-12-21 11:25:47
 */
public interface DistrictService {
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
    PageInfo<District> findAllDistrict(PageUtils pageUtils);

    /**
     *
     *
     * @description:
     * @param district //封装区域的名字
     * @return: 添加成功就返回1
     * @author: Aiden
     * @time: 2019-12-22 19:01:24
     */
    Integer addDistrictService(District district);

    /**
     *
     *
     * @description: 根据区域id查询区域，回显与修改弹框
     * @param id
     * @return: District
     * @author: Aiden
     * @time: 2019-12-23 09:55:15
     */
    District findDistrictById(Integer id);

    /**
     *
     *
     * @description: 点击保存更新按钮后将进行更新业务
     * @param district，封装了序列化的数据
     * @return:
     * @author: Aiden
     * @time: 2019-12-23 10:08:56
     */
    Integer upDistrict(District district);

    /**
     *
     *
     * @description:
     * @param id 区域的id
     * @return: 影响的行数
     * @author: Aiden
     * @time: 2019-12-23 14:21:36
     */
     Integer removeDistrictById(Integer id);

     /**
      *
      *
      * @description:
      * @param ids 选中的区域id
      * @return: 影响的行数
      * @author: Aiden
      * @time: 2019-12-24 13:46:04
      */
      Integer removeDistrictByBatch(Integer[] ids);

      /**
       *
       *
       * @description:
       * @param null
       * @return: 查询所有的区域用于发布
       * @author: Aiden
       * @time: 2019-12-27 14:30:44
       */
       List<District> findAllDistrictForPub();

}






















