package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.Map;

public interface BannerService {
    public Map<String,Object> selectAllBanner(Integer page,Integer rows);
    public String add(Banner banner);
    public void edit(Banner banner);
    public void del(Banner banner);
}
