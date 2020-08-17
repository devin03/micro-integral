package com.devin.micro.integral.domain.controller;

import com.devin.micro.integral.domain.business.FileHandleBusiness;
import com.devin.micro.integral.domain.business.ProductInfoBusiness;
import com.devin.micro.integral.domain.pojo.vo.ProductInfoVO;
import com.devin.micro.integral.extension.response.GlobalResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品信息controller
 * @author wangdongming
 * @date 2020/08/15
 */
@RestController
@RequestMapping("/product/info")
public class ProductInfoController {

    public static final Logger LOG = LoggerFactory.getLogger(ProductInfoController.class);

    @Autowired
    private ProductInfoBusiness productInfoBusiness;
    @Autowired
    private FileHandleBusiness fileHandleBusiness;

    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public GlobalResponseEntity addProductInfo(ProductInfoVO productInfoVO,HttpServletRequest request){
        return productInfoBusiness.addProductInfo(productInfoVO,request);
    }

    /**
     * 文件上传
     * @param request
     * @return GlobalResponseEntity
     * @author wangdongming
     * @date 2020/08/16
     */
    @RequestMapping("/uploadFile")
    public GlobalResponseEntity uploadFile(HttpServletRequest request){
        fileHandleBusiness.batchFileUploadHandle(request);
        return GlobalResponseEntity.success();
    }

}
