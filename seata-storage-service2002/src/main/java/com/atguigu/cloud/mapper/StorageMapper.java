package com.atguigu.cloud.mapper;

import com.atguigu.cloud.entities.Storage;
import tk.mybatis.mapper.common.Mapper;
import org.apache.ibatis.annotations.Param;

public interface StorageMapper extends Mapper<Storage> {
    /**
     * 扣减库存
     */
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}