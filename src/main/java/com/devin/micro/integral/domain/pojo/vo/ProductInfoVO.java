package com.devin.micro.integral.domain.pojo.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息表vo
 * @author wangdongming
 * @date 2020/08/15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoVO {
    /**
    * 主键
    */
    private Integer id;

    /**
    * 商品名称
    */
    private String productName;

    /**
    * 商品编号
    */
    private String productNumber;

    /**
    * 商品价格
    */
    private BigDecimal productPrice;

    /**
    * 商品库存
    */
    private Integer inventory;

    /**
    * 商品描述
    */
    private String productDesc;

    /**
    * 商品图片
    */
    private String productImage;

    /**
    * 备注
    */
    private String remark;

}