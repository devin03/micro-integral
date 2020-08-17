package com.devin.micro.integral.domain.pojo.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.*;

/**
 * 商品信息表
 * @author wangdongming
 * @date 2020/08/15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo {
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

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;

    /**
    * 版本号
    */
    private Integer version;
}