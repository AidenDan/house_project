package com.userscontroller;

import com.pojo.Users;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Aiden
 * @version 1.0
 * @description 处理房东请求的控制器
 * @date 2019-12-26 13:43:17
 */
@Controller("usersController2")
@RequestMapping("/u")
public class UsersController {
    @Autowired
    UsersService usersService;

    //注册时的时候要先判断次用户名是否已经存在
    @RequestMapping("/isexist")
    @ResponseBody
    public boolean isUsersExist(String name){
        return usersService.findUsersByName(name);
    }

    @RequestMapping("/insert")
    public String insertUsers(Users users, Model model) {
        boolean b = usersService.regsUsers(users);
        if(b){
            return "redirect:/page/login.jsp";
        }else {
            model.addAttribute("msg01", "用户名已经存在！");
            return "/page/regs.jsp";
        }
    }

    @RequestMapping("/check")
    public String checkUsersByNameByPassword(String name, String password, HttpSession session, HttpServletRequest request){
        Users users = usersService.checkUsers(name, password);
        if(users !=null){
            session.setAttribute("users", users);
            session.setMaxInactiveInterval(60*10);
            /*当登录成功的时候，就要去查询到当前用户的房屋信息，然后在显示到管理页面的主页上*/
            return "/h/showHouse";
        }else {
            request.setAttribute("msg02", "登录失败！");
            return "/page/login.jsp";
        }
    }

    //退出操作
    @RequestMapping("/out")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "/page/login.jsp";
    }


}
