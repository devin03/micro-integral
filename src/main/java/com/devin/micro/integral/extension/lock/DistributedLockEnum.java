package com.devin.micro.integral.extension.lock;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁服务枚举类<br>
 * 枚举的名字将作为锁KEY的前缀部分，代表需要分布式锁的业务部分，generateDistributedKey方法用来生成真正的锁KEY
 * 锁定具体业务的具体资源。<br>
 * 举例：债权还款业务，那么可以定义BUS_LOAN_REPAY,作为枚举
 * 调用generateDistributedKey(DistributedLockEnum.BUS_LOAN_REPAY,"loanId","orderId"),意义：同一笔债权还同一笔订单需要同步执行
 * @author wangdongming
 * @date 2020/08/17
 */
public enum DistributedLockEnum {
	/**
	 * 测试使用
	 */
	FOR_TEST_NO_TRY,
	/**
	 * 测试使用2
	 */
	FOR_TEST_WITH_TIMEOUT(3, TimeUnit.SECONDS),
	/**
	 * Redis 获取当前时间服务（SDK）
	 */
	REDIS_CURRENT_TIMESTAMP(5, TimeUnit.SECONDS),

	;

	/**
	 * 获取锁的尝试超时时间，0不等待，拿不到锁直接返回
	 */
	private long timeout = 0L;

	/**
	 * 获取锁的尝试超时时间单位（默认：毫秒）
	 */
	private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

	private DistributedLockEnum() {
	}

	private DistributedLockEnum(long timeout, TimeUnit timeUnit) {
		this.timeout = timeout;
		this.timeUnit = timeUnit;
	}

	public long getTimeout() {
		return timeout;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	/**
	 * 生成分布式锁的KEY<br>
	 * 例如：FOR_TEST_NO_TRY_part1_part2_part3
	 * @param lockEnum
	 * @param keyParts
	 * @return String
	 * @author wangdongming
	 * @date 2020/08/17
	 */
	public static String generateDistributedKey(DistributedLockEnum lockEnum, String... keyParts) {
		StringBuilder sb = new StringBuilder("SDK-LOCK-").append(lockEnum.name()).append("-");
		if (keyParts != null && keyParts.length > 0) {
			for (String keyPart : keyParts) {
				sb.append(keyPart);
				sb.append("-");
			}
		}
		// 去掉最后一个下划线
		return sb.substring(0, sb.length() - 1);
	}

}
