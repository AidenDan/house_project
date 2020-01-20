package com.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.UsersMapper;
import com.mapper.StreetMapper;
import com.pojo.Users;
import com.pojo.UsersExample;
import com.service.UsersService;
import com.utils.MD5Utils;
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
public class UserServiceImpl implements UsersService {
    @Autowired
    UsersMapper UsersMapper;
    /*@Autowired
    StreetMapper streetMapper;*/

    @Override
    public PageInfo<Users> findAllUsers(PageUtils pageUtils) {
        UsersExample UsersExample = new UsersExample();
        com.pojo.UsersExample.Criteria criteria = UsersExample.createCriteria();
        if(pageUtils.getCname() !=null){
            criteria.andNameLike("%"+pageUtils.getCname()+"%");
        }
        if(pageUtils.getCtelephone() !=null){
            criteria.andTelephoneLike("%"+pageUtils.getCtelephone()+"%");
        }
        //开启分页
        PageHelper.startPage(pageUtils.getPage(), pageUtils.getRows());
        //查询所有的区域
        List<Users> UsersList = UsersMapper.selectByExample(UsersExample);
        //封装区域信息到PageInfo
        return new PageInfo<>(UsersList);
    }

    @Override
    public Integer addUsersService(Users Users) {
        //动态插入，有这个条件就插入这个条件
        return UsersMapper.insertSelective(Users);
    }

    //根据区域id查询区域，用于回显
    @Override
    public Users findUsersById(Integer id) {
        return UsersMapper.selectByPrimaryKey(id);
    }

    //更新区域
    @Override
    public Integer upUsers(Users Users) {
        return UsersMapper.updateByPrimaryKeySelective(Users);
    }

    //删除区域的同时要把区域对应的街道给删除，执行了2个SQL，开启事物
    @Transactional
    @Override
    public Integer removeUsersById(Integer id) {
        Integer i1 = UsersMapper.deleteByPrimaryKey(id);
      /*  int i =10;
        int j =0;
        i = i/j;*/
        //Integer i2 = streetMapper.delStreetByUsers_Id(id);
        return 1;
    }

    @Override
    public Integer removeUsersByBatch(Integer[] ids) {
        return UsersMapper.delUersByBatch(ids);
    }

    //处理房东请求的控制器
    @Override
    public boolean findUsersByName(String name) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(name);
        List<Users> usersList = UsersMapper.selectByExample(usersExample);
        return usersList != null && usersList.size() > 0;
    }

    //房东注册功能
    @Override
    public boolean regsUsers(Users users) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(users.getName());
        List<Users> usersList = UsersMapper.selectByExample(usersExample);
        if(usersList != null && usersList.size() > 0){
            return false;
        }else {
            //用户有管理员与非管理员，非管理员就是房东
            users.setIsadmin(0);
            //MD5对密码进行加密
            String s = MD5Utils.md5Encrypt(users.getPassword());
            users.setPassword(s);
            int i = UsersMapper.insertSelective(users);
            return i>0;
        }
    }

    //管理猿用户注册
    @Override
    public boolean regsAdminUsers(Users users) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(users.getName());
        List<Users> usersList = UsersMapper.selectByExample(usersExample);
        if(usersList != null && usersList.size() > 0){
            return false;
        }else {
            //用户有管理员与非管理员，非管理员就是房东
            users.setIsadmin(1);
            //MD5对密码进行加密
            String s = MD5Utils.md5Encrypt(users.getPassword());
            users.setPassword(s);
            int i = UsersMapper.insertSelective(users);
            return i>0;
        }
    }

    //根据用户名和密码进行登录验证，若验证成功，那么必须返回Users用于展示用户信息
    @Override
    public Users checkUsers(String name, String password){
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(name);
        String pwd = MD5Utils.md5Encrypt(password);
        criteria.andPasswordEqualTo(pwd);
        criteria.andIsadminEqualTo(0);
        List<Users> usersList = UsersMapper.selectByExample(usersExample);
        if  (usersList !=null && usersList.size()>0)
        return usersList.get(0);
        else
            return null;
    }


    //管理员登录验证
    @Override
    public Users checkAdminUsers(String name, String password) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(name);
        String pwd = MD5Utils.md5Encrypt(password);
        criteria.andPasswordEqualTo(pwd);
        criteria.andIsadminEqualTo(1);
        List<Users> usersList = UsersMapper.selectByExample(usersExample);
        if  (usersList !=null && usersList.size()>0)
            return usersList.get(0);
        else
            return null;
    }

    //根据房屋id审核是否通过发布
    @Override
    public Integer findUsersById1(String id) {
        return UsersMapper.makePass(id);
    }

    //取消房屋审核通过
    @Override
    public Integer findUsersById2(String id) {
        return UsersMapper.makeNoPass(id);
    }

    //修改管理员密码
    @Override
    public boolean modifyPasswordOfAdmin(String id , String NewPass) {
        String pwd = MD5Utils.md5Encrypt(NewPass);
        Integer integer = UsersMapper.updateAdminPassword(id, pwd);
        return integer>0;
    }
}





















