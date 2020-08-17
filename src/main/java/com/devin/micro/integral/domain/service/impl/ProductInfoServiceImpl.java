package com.devin.micro.integral.domain.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.devin.micro.integral.domain.pojo.model.ProductInfo;
import com.devin.micro.integral.domain.dao.ProductInfoMapper;
import com.devin.micro.integral.domain.service.ProductInfoService;

/**
 * 商品信息接口实现类
 * @author wangdongming
 * @date 2020/08/15
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService{

    @Resource
    private ProductInfoMapper productInfoMapper;

    @Override
    public int insertSelective(ProductInfo record) {
        return productInfoMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(ProductInfo record) {
        return productInfoMapper.updateByPrimaryKeySelective(record);
    }

}
