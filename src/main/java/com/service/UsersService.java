package com.service;

import com.github.pagehelper.PageInfo;
import com.pojo.Users;
import com.utils.PageUtils;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2019-12-21 11:25:47
 */
public interface UsersService {
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
    PageInfo<Users> findAllUsers(PageUtils pageUtils);

    /**
     *
     *
     * @description:
     * @param Users //封装区域的名字
     * @return: 添加成功就返回1
     * @author: Aiden
     * @time: 2019-12-22 19:01:24
     */
    Integer addUsersService(Users Users);

    /**
     *
     *
     * @description: 根据区域id查询区域，回显与修改弹框
     * @param id
     * @return: Users
     * @author: Aiden
     * @time: 2019-12-23 09:55:15
     */
    Users findUsersById(Integer id);

    /**
     *
     *
     * @description: 点击保存更新按钮后将进行更新业务
     * @param Users，封装了序列化的数据
     * @return:
     * @author: Aiden
     * @time: 2019-12-23 10:08:56
     */
    Integer upUsers(Users Users);

    /**
     *
     *
     * @description:
     * @param id 区域的id
     * @return: 影响的行数
     * @author: Aiden
     * @time: 2019-12-23 14:21:36
     */
     Integer removeUsersById(Integer id);

     /**
      *
      *
      * @description:
      * @param ids 选中的区域id
      * @return: 影响的行数
      * @author: Aiden
      * @time: 2019-12-24 13:46:04
      */
      Integer removeUsersByBatch(Integer[] ids);

      /**
       *
       *
       * @description: 登录时根据用户名判断用户是否存在
       * @param name
       * @return:
       * @author: Aiden
       * @time: 2019-12-26 13:57:24
       */
        boolean findUsersByName(String name);

      /**
       *
       *
       * @description:
       * @param users 用于封装房东注册信息
       * @return:
       * @author: Aiden
       * @time: 2019-12-26 13:46:05
       */
       boolean regsUsers(Users users);

    /**
     *
     *
     * @description:
     * @param users 用于封装管理员注册信息
     * @return:
     * @author: Aiden
     * @time: 2019-12-26 13:46:05
     */
    boolean regsAdminUsers(Users users);


    /**
        *
        *
        * @description: 根据用户名和密码进行登录验证，若验证成功，那么必须返回Users用于展示用户信息
        * @param name
        * @param password
        * @return: Users
        * @author: Aiden
        * @time: 2019-12-26 21:08:30
        */
       Users checkUsers(String name, String password);

    /**
     *
     *
     * @description: 根据用户名和密码进行登录验证，若验证成功，那么必须返回Users用于展示用户信息
     * @param name
     * @param password
     * @return: Users
     * @author: Aiden
     * @time: 2019-12-26 21:08:30
     */
    Users checkAdminUsers(String name, String password);


    /**
     *
     *
     * @description: 根据房屋id修改是否审核通过
     * @param id
     * @return: Users
     * @author: Aiden
     * @time: 2019-12-23 09:55:15
     */
    Integer findUsersById1(String id);

    /**
     *
     *
     * @description: 根据房屋id取消否审核通过
     * @param id
     * @return: Users
     * @author: Aiden
     * @time: 2019-12-23 09:55:15
     */
    Integer findUsersById2(String id);


    /**
     *
     *
     * @description: 修改管理员密码
     * @param null
     * @return:
     * @author: Aiden
     * @time: 2020-1-19 22:13:21
     */
    boolean modifyPasswordOfAdmin(String id , String NewPass);


}






















