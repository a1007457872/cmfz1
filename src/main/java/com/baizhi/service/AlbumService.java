package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.Map;

public interface AlbumService {
    public Map<String,Object> selectAllAlbum(Integer page, Integer rows);
    public String add(Album album);
    public void edit(Album album);
    public void del(Album album);
}
