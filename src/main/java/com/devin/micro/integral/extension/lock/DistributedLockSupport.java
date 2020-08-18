package com.devin.micro.integral.extension.lock;

/**
 * 分布式锁服务
 * @author wangdongming
 * @date 2020/08/17
 */
public interface DistributedLockSupport {

	/**
	 * 获取分布式锁
	 * @param lockEnum
	 * @param keyParts
	 * @return boolean
	 * @author wangdongming
	 * @date 2020/08/17
	 */
	boolean acquire(DistributedLockEnum lockEnum, String... keyParts);

	/**
	 * 释放分布式锁
	 * @param lockEnum
	 * @param keyParts
	 * @author wangdongming
	 * @date 2020/08/17
	 */
	void release(DistributedLockEnum lockEnum, String... keyParts);

}