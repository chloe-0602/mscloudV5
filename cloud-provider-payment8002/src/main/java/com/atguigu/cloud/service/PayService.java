package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Pay;

import java.util.List;

public interface PayService {
    public Integer add(Pay pay);
    public Integer delete(Integer id);
    public Integer update(Pay pay);

    public Pay getById(Integer id);
    public List<Pay> getAll();
}
