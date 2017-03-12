package com.fcs.common.util;

import java.math.BigDecimal;

/**
 * 
 * @author wuqq
 *
 */
public class MathUtils {
	
	public static BigDecimal ZERO_DECIMAL = new BigDecimal(0);
	
	/**
	 * 
	 * @param one
	 * @param other
	 * @return
	 */
	public static BigDecimal add(BigDecimal one, BigDecimal other) {
		if (one == null && other == null)
			return ZERO_DECIMAL;
		else if (one == null) {
			return other;
		} else if (other == null) {
			return one;
		}
		return one.add(other);
	}
	
	/**
	 * 多个double累加
	 * 
	 * @param args
	 * @return
	 */
	public static double add(double... args) {
		double sum = 0;
		if (args != null) {
			for (int i = 0; i < args.length; i++)
				sum += args[i];
		}
		return roundHalfUp(sum);
	}
	
	/**
	 * 
	 * @param decimal
	 * @return
	 */
	public static double roundHalfUp(BigDecimal decimal) {
		return roundHalfUp(decimal, 2) ;
	}
	
	/**
	 * 
	 * @param decimal
	 * @param scale
	 * @return
	 */
	public static double roundHalfUp(BigDecimal decimal, int scale) {
		return decimal.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 四舍五入,保留小数点后两位
	 * @param value
	 * @return
	 */
	public static double roundHalfUp(double value) {
		return roundHalfUp(value, 2) ;
	}
	
	/**
	 * 四舍五入
	 * @param value
	 * @param scale 保留小数点后位数
	 * @return
	 */
	public static double roundHalfUp(double value, int scale) {
		BigDecimal decimal = new BigDecimal(value);
		return decimal.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 往下近似,保留小数点后两位
	 * @param decimal
	 * @return
	 */
	public static double roundDown(BigDecimal decimal) {
		return roundDown(decimal, 2);
	}

	/**
	 * 往下近似
	 * @param decimal
	 * @param scale
	 * @return
	 */
	public static double roundDown(BigDecimal decimal, int scale) {
		return decimal.setScale(scale, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * 往下近似,保留小数点后两位
	 * @param value
	 * @return
	 */
	public static double roundDown(double value) {
		return roundDown(value, 2);
	}

	/**
	 * 往下近似
	 * @param value
	 * @param scale 保留小数点后位数
	 * @return
	 */
	public static double roundDown(double value, int scale) {
		BigDecimal decimal = new BigDecimal(value);
		return decimal.setScale(scale, BigDecimal.ROUND_DOWN).doubleValue();
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static BigDecimal roundHalfUpForDecimal(double value) {
		BigDecimal decimal = new BigDecimal(value);
		return decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	
	/**
	 * 计算百分比
	 * 
	 * @param child
	 * @param mother
	 * @return
	 */
	public static double score(double child, double mother) {
		return Math.floor(child * 100 / mother);
	}
	
	public static void main(String[] args) {
		System.out.println(roundHalfUp(10));
	}
}
//1. varchar
//2. php=>10.0
