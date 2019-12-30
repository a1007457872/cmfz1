package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserServiceImpl
 * @Discription
 * @Author
 * @Date 2019/12/18 0018 9:10
 * @Version 1.0
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;
    @Override
    public void login(Admin admin, String code, String enCode, HttpServletRequest request) {
        System.out.println(enCode+"----"+code);
        if(!enCode.equals(code))throw new RuntimeException("验证码输入错误");
        Admin a=new Admin();
        a.setUsername(admin.getUsername());
        Admin admin1 = adminDao.selectOne(a);
        if(admin1==null)throw new RuntimeException("用户名不存在");
        if(!admin1.getPassword().equals(admin.getPassword()))throw new RuntimeException("密码错误");
        request.getSession().setAttribute("loginAdmin",admin1);
    }
}
