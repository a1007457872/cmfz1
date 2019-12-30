package com.baizhi.service;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName BannerServiceImpl
 * @Discription
 * @Author
 * @Date 2019/12/19 0019 10:00
 * @Version 1.0
 */
@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerDao bannerDao;
    @Override
    public Map<String, Object> selectAllBanner(Integer page, Integer rows) {
        Map<String,Object> map=new HashMap<>();
        Banner banner=new Banner();
        RowBounds row=new RowBounds((page-1)*rows,rows);
        List<Banner> banners = bannerDao.selectByRowBounds(banner, row);
        int i = bannerDao.selectCount(banner);
        map.put("page",page);
        map.put("rows",banners);
        map.put("total",i%rows==0?i/rows:i/rows+1);
        map.put("records",i);
        return map;
    }

    @Override
    public String add(Banner banner) {
        String id= UUID.randomUUID().toString();
        banner.setId(id);
        banner.setCreateDate(new Date());
        int i = bannerDao.insertSelective(banner);
        if(i==0){
            throw new RuntimeException("添加失败");
        }
        return id;
    }

    @Override
    public void edit(Banner banner) {
        int i = bannerDao.updateByPrimaryKeySelective(banner);
        if(i==0){
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public void del(Banner banner) {
        int i = bannerDao.delete(banner);
        if(i==0){
            throw new RuntimeException("删除失败");
        }
    }
}
