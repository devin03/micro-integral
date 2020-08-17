package com.devin.micro.integral.domain.dao;

/**
 * dao层基准接口
 * @author wangdongming
 * @date 2020/08/15
 */
public interface BaseMapper<T> {
    /**
     * 获取单条数据
     * @param id
     * @return T
     * @author wangdongming
     * @date 2020/08/15
     */
    T getById(Integer id);

}
