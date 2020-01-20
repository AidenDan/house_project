package com.controller;

import com.github.pagehelper.PageInfo;
import com.pojo.Users;
import com.service.UsersService;
import com.utils.MD5Utils;
import com.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2019-12-21 11:24:08
 */
@Controller
public class UsersController {

    @Autowired
    private UsersService UsersService;

    //动态分页查询所有的用户
    @RequestMapping("/getAllUsersByPage")
    @ResponseBody
    public Map<String, Object> getAllUsersByPage(PageUtils pageUtils){
        Map<String, Object> map = new HashMap<>();
        PageInfo<Users> pageInfo = UsersService.findAllUsers(pageUtils);
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        System.out.println("操蛋"+map);
        return map;
    }

    //添加区域信息
    @RequestMapping("/addUsers")
    @ResponseBody
    public Map<String, Object> addUsers(Users Users){
        Map<String, Object> map = new HashMap<>();
        Users.setPassword(MD5Utils.md5Encrypt(Users.getPassword()));
        Integer returnKey = UsersService.addUsersService(Users);
        map.put("returnKey", returnKey);
        return map;
    }

    //根据id查询区域信息
    @RequestMapping("/getUsersById")
    @ResponseBody
    public Users getUsersById(Integer id){
        return UsersService.findUsersById(id);
    }

    //修改区域信息
    @RequestMapping("/upUsers")
    @ResponseBody
    public Map<String, Object> upUsers(Users Users){
        Integer returnKey = UsersService.upUsers(Users);
        Map<String, Object> map = new HashMap<>();
        map.put("returnKey", returnKey);
        return  map;
    }

    //删除区域信息
    @RequestMapping("/delUsersById")
    @ResponseBody
    public Map<String, Object> delUsersById(Integer id){
        Map<String, Object> map = new HashMap<>();
        try {
            //删除失败的话就把异常信息try cath起来
            Integer returnKey = UsersService.removeUsersById(id);
            map.put("returnKey", returnKey);
        } catch (Exception e) {
            map.put("returnKey", -1);
        }
        return  map;
    }

    //批量删除区域信息
    @RequestMapping("/delUsersByBatch")
    @ResponseBody
    public Map<String, Object> delUsersByBatch(String ids){
        Map<String, Object> map = new HashMap<>();
        String[] split = ids.split(",");
        Integer[] arr = new Integer[split.length];
        for (int i = 0; i <split.length ; i++) {
            arr[i] = new Integer(split[i] );
        }        
        Integer returnKey = UsersService.removeUsersByBatch(arr);
        map.put("returnKey", returnKey);
        return map;
    }

    //根据房屋id审核信息
    @RequestMapping("/getUsersById1")
    @ResponseBody
    public Map<String, Object> getUsersById1(String id){
        Integer i = UsersService.findUsersById1(id);
        HashMap<String, Object> map = new HashMap<>();
            map.put("returnKey", i);
            return map;
    }

    //根据房屋id取消审核信息
    @RequestMapping("/getUsersById2")
    @ResponseBody
    public Map<String, Object> getUsersById2(String id){
        Integer i = UsersService.findUsersById2(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("returnKey", i);
        return map;
    }

    //管理员登录时，先验证当前用户是否为管理员
    @RequestMapping("/adminCheck")
    public String checkAdminUsersByNameByPassword(String name, String password, HttpSession session, HttpServletRequest request){
        Users users = UsersService.checkAdminUsers(name, password);
        if(users !=null){
            session.setAttribute("users", users);
            session.setMaxInactiveInterval(60*10);
            /*当登录成功的时候，就要去查询到当前用户的房屋信息，然后在显示到管理页面的主页上*/
            return "/admin/admin.jsp";
        }else {
            request.setAttribute("msg02", "登录失败！");
            return "/page/login.jsp";
        }
    }

    //管理员用户注册
    @RequestMapping("/insertAdmin")
    public String insertAdminUsers(Users users, Model model) {
        boolean b = UsersService.regsAdminUsers(users);
        if(b){
            return "redirect:/admin/adminlogin.jsp";
        }else {
            model.addAttribute("msg01", "用户名已经存在！");
            return "/admin/regs.jsp";
        }
    }

    //退出操作
    @RequestMapping("/out")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "redirect:/admin/adminlogin.jsp";
    }

    //管理员修改 密码,密码修改完应该重新登录
    @RequestMapping("/editAdminPassword/{id}/{NewPass}/{RePass}")
    public String editAdminPassword(@PathVariable("id") String id, @PathVariable("NewPass") String NewPass, @PathVariable("RePass") String RePass, HttpServletRequest request){
        if(!NewPass.equals(RePass)){
            request.setAttribute("msg03", "fail");
            return "/admin/admin.jsp";
        }else {
            boolean b = UsersService.modifyPasswordOfAdmin(id, NewPass);
            if(b){
                request.setAttribute("msg04", "success");
                return "/admin/adminlogin.jsp";
            }else {
                request.setAttribute("msg03", "fail");
                return "/admin/admin.jsp";
            }


        }

    }

}
















