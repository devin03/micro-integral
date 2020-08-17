package com.devin.micro.integral.domain.service;

import com.devin.micro.integral.domain.pojo.model.ProductInfo;

/**
 * 商品信息接口
 * @author wangdongming
 * @date 2020/08/15
 */
public interface ProductInfoService{

    /**
     * 插入商品信息记录
     * @param record 商品信息
     * @return int
     * @author wangdongming
     * @date 2020/08/15
     */
    int insertSelective(ProductInfo record);

    /**
     * 根据id更新商品信息
     * @param record 商品信息
     * @return int
     * @author wangdongming
     * @date 2020/08/15
     */
    int updateByPrimaryKeySelective(ProductInfo record);

}
