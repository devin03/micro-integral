package com.devin.micro.integral.extension.util;

import com.devin.micro.integral.extension.IntEnumInter;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 枚举工具类
 * @author wangdongming
 * @date 2020/08/17
 */
public class EnumUtil {

	private EnumUtil() {
	}

	public static <T extends Enum<T>> int encode(EnumSet<T> set) {
		if (set == null) {
			return 0;
		}

		int ret = 0;

		for (T val : set) {
			ret |= 1 << val.ordinal();
		}

		return ret;
	}

	public static <T extends Enum<T>> EnumSet<T> decode(Class<T> type, int code) {
		Map<Integer, T> codeMap = new HashMap<Integer, T>();

		for (T val : EnumSet.allOf(type)) {
			codeMap.put(val.ordinal(), val);
		}

		EnumSet<T> result = EnumSet.noneOf(type);
		while (code != 0) {
			int ordinal = Integer.numberOfTrailingZeros(code);
			code ^= Integer.lowestOneBit(code);
			result.add(codeMap.get(ordinal));
		}

		return result;
	}

	/**
	 * 根据String获取enum对象
	 * 
	 * @return
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getEnumByValue(String value, Class<T> c) {
		if (StringUtils.isNotEmpty(value)) {
			for (Object o : c.getEnumConstants()) {
				if (o.toString().equals(value)) {
					return (T) o;
				}
			}
		}
		return null;
	}

	/**
	 * 根据name获取enum对象
	 * 
	 * @return
	 */
	public static <T extends Enum<T>> T getEnumByName(String value, Class<T> type) {
		for (T val : EnumSet.allOf(type)) {
			if (val.name().equals(value)) {
				return val;
			}
		}
		return null;
	}

	/**
	 * 根据intValue获取enum
	 * 
	 */
	public static <T extends Enum<T> & IntEnumInter<T>> T getEnumByValue(int value, Class<T> type) {
		for (T val : EnumSet.allOf(type)) {
			if (val.intValue() == value) {
				return val;
			}
		}
		throw new UnsupportedOperationException("cannot find enum by value:"+value);
	}

	/**
	 * valueByOrdinalToEnum
	 * 
	 */
	public static <T extends Enum<T>> T getEnumByOrdinal(int value, Class<T> c) {
		for (T val : c.getEnumConstants()) {
			if (val.ordinal() == value) {
				return (T) val;
			}
		}
		return null;
	}

	/**
	 * EnumSetToList
	 * 
	 * @param set
	 * @return
	 */
	public static List<String> getEnumNamesByEnumSet(EnumSet<?> set) {
		List<String> list = new ArrayList<String>();
		if (null == set || set.size() == 0) {
			return list;
		}
		for (Enum<?> enum1 : set) {
			list.add(enum1.name());
		}
		return list;
	}

	/**
	 * getIntValuesByEnumSet
	 * 
	 * @param set
	 * @return
	 * @since JDK 1.6
	 */
	public static List<Integer> getIntValuesByEnumSet(EnumSet<? extends IntEnumInter<?>> set) {
		List<Integer> list = new ArrayList<Integer>();
		if (null == set || set.size() == 0) {
			return list;
		}
		for (IntEnumInter<?> enum1 : set) {
			list.add(enum1.intValue());
		}
		return list;
	}
}