package com.devin.micro.integral.extension;

/**
 * 枚举接口
 * @author wangdongming
 * @date 2020/08/14
 */
public interface IntEnumInter<E extends Enum<E>> {

    int intValue();

    @Override
    String toString();

}