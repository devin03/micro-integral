package com.devin.micro.integral.domain.dao;

import com.devin.micro.integral.domain.pojo.model.ProductInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品信息表mapper
 * @author wangdongming
 * @date 2020/08/15
 */
@Mapper
public interface ProductInfoMapper extends BaseMapper<ProductInfo>{

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