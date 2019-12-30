package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Cmfz1ApplicationTests {
    @Autowired
    AdminDao adminDao;
    @Test
    public void test1() {
        List<Admin> admins = adminDao.selectAll();
        admins.forEach(admin -> System.out.println(admin));
    }
    @Test
    public void test2(){
        Admin admin=new Admin();
        admin.setId("3");
        admin.setUsername("admin");
        List<Admin> select = adminDao.select(admin);
        select.forEach(admin1 -> System.out.println(admin1));
    }
    @Test
    public void test3(){
        Admin admin=new Admin();
        int i = adminDao.selectCount(admin);
        System.out.println(i);
    }
    @Test
    public void test4(){
        Example e=new Example(Admin.class);
        e.createCriteria().andEqualTo("username","admin");
        List<Admin> admins = adminDao.selectByExample(e);
        admins.forEach(admin -> System.out.println(admin));
    }
    @Test
    public void test5(){
        Example e=new Example(Admin.class);
        e.createCriteria().andIsNull("username").andLike("nickname","小%");
        List<Admin> admins = adminDao.selectByExample(e);
        admins.forEach(admin -> System.out.println(admin));
    }
    @Test
    public void test6(){
        int page=1;
        int rows=3;
        Admin a=new Admin();
        //offset:起始的下标
        //limit:每页展示条数
        RowBounds row=new RowBounds((page-1)*rows,rows);
        List<Admin> admins = adminDao.selectByRowBounds(a, row);
        admins.forEach(admin -> System.out.println(admin));
    }
    @Test
    public void test7(){
        Admin a=new Admin();
        a.setId("5");
        a.setUsername("jeck");
        a.setPassword("444444");
        adminDao.insertSelective(a);
    }
    @Test
    public void test8(){
        //insert插入所有的值   insertSelective插入非空值
        Admin a=new Admin();
        a.setId("6");
        a.setUsername("jeck");
        a.setPassword("444444");
        adminDao.insert(a);
    }
    @Test
    public void test9(){
        Admin a=new Admin();
        a.setId("3");
        a.setUsername("jeck");
        a.setPassword("444444");
        adminDao.updateByPrimaryKey(a);
    }
    @Test
    public void test10(){
        Admin a=new Admin();
        a.setId("3");
        a.setUsername("jeck");
        a.setPassword("444445");
        int i = adminDao.updateByPrimaryKeySelective(a);
        System.out.println(i);
    }
    @Test
    public void test11(){
        Admin a=new Admin();
        a.setUsername("jeck");
        adminDao.delete(a);
    }
}
