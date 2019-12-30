package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AdminController
 * @Discription
 * @Author
 * @Date 2019/12/18 0018 9:46
 * @Version 1.0
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @RequestMapping("login")
    public @ResponseBody
    Map<String,Object> login(Admin admin, String code, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        String enCode=(String)request.getSession().getAttribute("code");
        try {
            adminService.login(admin,code,enCode,request);
            map.put("status",true);
        } catch (Exception e) {
            map.put("status",false);
            map.put("message",e.getMessage());
            e.printStackTrace();
        }
        return map;
    }
}
