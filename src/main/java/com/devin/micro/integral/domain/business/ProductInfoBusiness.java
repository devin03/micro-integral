package com.devin.micro.integral.domain.business;

import com.devin.micro.integral.domain.pojo.model.ProductInfo;
import com.devin.micro.integral.domain.pojo.vo.ProductInfoVO;
import com.devin.micro.integral.domain.service.ProductInfoService;
import com.devin.micro.integral.extension.response.GlobalResponseEntity;
import com.devin.micro.integral.extension.util.CalendarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品信息逻辑处理
 * @author wangdongming
 * @date 2020/08/15
 */
@Service
public class ProductInfoBusiness {

    public static final Logger LOG = LoggerFactory.getLogger(ProductInfoBusiness.class);

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private FileHandleBusiness fileHandleBusiness;

    /**
     * 添加商品
     * @param productInfoVO 商品参数信息
     * @param request 请求
     * @return GlobalResponseEntity
     * @author wangdongming
     * @date 2020/08/15
     */
    public GlobalResponseEntity addProductInfo(ProductInfoVO productInfoVO, HttpServletRequest request){
        LOG.info("addProductInfo request param is {}",productInfoVO);
        String imageUrls = fileHandleBusiness.batchFileUploadHandle(request);
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(productInfoVO,productInfo);
        productInfo.setProductImage(imageUrls);
        productInfo.setCreateTime(CalendarUtil.getCurrentDate());
        productInfo.setUpdateTime(CalendarUtil.getCurrentDate());
        int count = productInfoService.insertSelective(productInfo);
        Assert.isTrue(count > 0,String.format("add product info fail,product number is %s",productInfoVO.getProductNumber()));
        return GlobalResponseEntity.success();
    }


}
