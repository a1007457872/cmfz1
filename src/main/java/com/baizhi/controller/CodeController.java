package com.baizhi.controller;

import com.baizhi.util.SecurityCode;
import com.baizhi.util.SecurityImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @ClassName CodeController
 * @Discription
 * @Author
 * @Date 2019/12/18 0018 9:46
 * @Version 1.0
 */
@Controller
@RequestMapping("code")
public class CodeController {
    @RequestMapping("getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response){
        String code = SecurityCode.getSecurityCode();
        request.getSession().setAttribute("code",code);
        System.out.println("获取的验证码："+code);
        BufferedImage image = SecurityImage.createImage(code);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image,"png",outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
