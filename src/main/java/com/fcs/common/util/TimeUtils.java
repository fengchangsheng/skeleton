package com.fcs.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author admin
 *
 */
public class TimeUtils {

	
	/**
	 * UnixTime of Day
	 */
	public static final long UNIXTIME_STD_DAY = TimeUnit.SECONDS.convert(1, TimeUnit.DAYS) ;
	
	
	
	
	
	/**
	 * 取得yyyy-MM-dd HH:mm:ss格式的当前时间
	 * 
	 * @return
	 */
	public static String yyyyMMddHHmmssNow() {
		return yyyyMMddHHmmss(new Date());
	}
	
	/**
	 * 取得yyyy-MM-dd HH:mm:ss格式的当前时间
	 * 
	 * @return
	 */
	public static String yyyyMMddHHmmss() {
		return yyyyMMddHHmmss(new Date());
	}
	
	
	/**
	 * 取得yyyy-MM-dd HH:mm:ss格式的当前时间
	 * 
	 * @return
	 */
	public static String yyyyMMddHHmmss(long timestamp) {
		return yyyyMMddHHmmss(new Date(timestamp));
	}

	
	
	/**
	 * 取得yyyy-MM-dd HH:mm:ss格式的时间
	 * 
	 * @return
	 */
	public static String yyyyMMddHHmmss(Date date) {
		return dateFormat4yyyyMMddHHmmss().format(date);
	}



	/**
	 * 将yyyy-MM-dd HH:mm:ss转换为Date
	 * 
	 * @param formatdate
	 * @return
	 */
	public static Date yyyyMMddHHmmss(String formatdate) {
		try 
		{
			return dateFormat4yyyyMMddHHmmss().parse(formatdate);
		} catch (Exception e){throw new IllegalStateException("parse["+formatdate+"] with ex.", e);}
	}
	
	public static Date yyyyMMdd(String formatdate) {
		try 
		{
			return dateFormat4yyyyMMdd().parse(formatdate);
		} catch (Exception e){throw new IllegalStateException("parse["+formatdate+"] with ex.", e);}
	}
	
	public static long getMilliseconds(String unixtime) {
		return Long.valueOf(unixtime)*1000 ;
	}
	
	
	public static Date setUnixTime(Date date, String unixtime) {
		if(date==null) date = new Date() ;
		date.setTime(getMilliseconds(unixtime)) ;
		return date ;
	}
	
	
	
	public static String format(DateFormat format, Date date) {
		try{
			return format.format(date) ;
		} catch (Exception e){throw new IllegalArgumentException("format["+date+"] with ex.", e);}
	}
	
	/**
	 * 
	 * @return
	 */
	public static SimpleDateFormat dateFormatyyyyMMddHHmmss() {
		return new SimpleDateFormat("yyyyMMddHHmmss") ;
	}
	
	/**
	 * 
	 * @return
	 */
	public static SimpleDateFormat dateFormat4yyyyMMddHHmmss() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public static SimpleDateFormat dateFormat2yyyyMMddHHmmss() {
		return new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒") ;
	}
	
	/**
	 * 
	 * @return yyyy-MM-dd格式
	 */
	public static SimpleDateFormat dateFormat4yyyyMMdd() {
		return new SimpleDateFormat("yyyy-MM-dd") ;
	}
	
	
	/**
	 * 
	 * @return yyyyMMdd格式
	 */
	public static SimpleDateFormat yyyyMMddFormatter() {
		return new SimpleDateFormat("yyyyMMdd") ;
	}
	
	/**
	 * 
	 * @return yyyy年MM月dd日格式
	 */
	public static DateFormat yyyyMMddChianiseFormatter() {
		return new SimpleDateFormat("yyyy年MM月dd日") ;
	}
	
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String yyyyMMdd(Date date) {
		return dateFormat4yyyyMMdd().format(date) ;
	}
	
	public static String yyyyMMddHHMMSS(Date date){
		return dateFormat2yyyyMMddHHmmss().format(date);
	}
	
	public static Date newDateByUnixTimestamp(long unixTimestamp) {
		return unixTimestamp==0 ? null : new Date(unixTimestamp*1000) ;
	}
	
	public static long getUnixTimestamp(Date date) {
		return date == null ? 0 : date.getTime()/1000 ;
	}
	
	public static long unixtimestampOfNow() {
		return System.currentTimeMillis()/1000 ;
	}
	
	
	public static String stringOfUnixtimestampNow() {
		return String.valueOf(System.currentTimeMillis()/1000) ;
	}
	
	public static String stringOfUnixtimestamp(Date date) {
		return String.valueOf(date.getTime()/1000) ;
	}
	
	public static String yyyyMMddChianise(Date date) {
		return yyyyMMddChianiseFormatter().format(date) ;
	}
	public static void main(String[] args) {
		Date now = newDateByUnixTimestamp(1385027819) ;
		System.out.println(TimeUtils.yyyyMMdd(now));
	}
	
	public static Date newDateByUnixTimestamp(String timestamp) {
		timestamp = CommonUtils.emptyIfNull(timestamp) ;
		if(timestamp.isEmpty()) return null ;
		return newDateByUnixTimestamp(Long.valueOf(timestamp)) ;
	}
	
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateZero(Date date) {
		Calendar c = Calendar.getInstance() ;
		if(date!=null) c.setTime(date) ;
		setCalendarToZero(c) ;
		return c.getTime() ;		
	}
	
	/**
	 * 获取今天的截至时间
	 * @return
	 */
	public static Date getTodayLimit() {
		return getDateEndTime(new Date()) ;
	}
	
	
	
	/**
	 * 获取某一天的开始时间
	 * @param date
	 * @return
	 */
	public static Date getDateStartTime(Date date) {
		if(date==null) return null ;
		Calendar c = Calendar.getInstance() ;
		c.setTime(date) ;
		setCalendarToZero(c) ;
		return c.getTime() ;
	}
	
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public static void setCalendarToZero(Calendar c) {
		c.set(Calendar.HOUR_OF_DAY, 0) ;
		c.set(Calendar.MINUTE, 0) ;
		c.set(Calendar.SECOND, 0) ;
		c.set(Calendar.MILLISECOND, 0) ;
	}
	
	
	/**
	 * 获取某一天的截至时间
	 * @param date
	 * @return
	 */
	public static Date getDateEndTime(Date date) {
		if(date==null) return null ;
		Calendar c = Calendar.getInstance() ;
		c.setTime(date) ;
		c.add(Calendar.DAY_OF_MONTH, 1) ;
		setCalendarToZero(c) ;
		return c.getTime() ;
	}
	/**
	 * 
	 * @return
	 */
	public static Date getTodayZero() {
		return getDateZero(null) ;
	}

	
	public static Date parse(java.sql.Timestamp timestamp) {
		if(timestamp==null) return null ;
		return new Date(timestamp.getTime()*1000) ;
	}
	
	
	/**
	 * 
	 * @param birthday
	 * @return
	 */
	public static int getAge(Date birthday) {
		long day = TimeUnit.DAYS.convert(System.currentTimeMillis()-birthday.getTime(), TimeUnit.MILLISECONDS) ;
		return (int)(day/365) ;
	}

	/**
	 * 得到当月第一天开始时间
	 * @param now
	 * @return
	 */
	public static Date getMonthStartTime(Date date){
		if(date==null) return null ;
		Calendar c = Calendar.getInstance() ;
		c.setTime(date) ;
		setCalendarToZero(c);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime() ;
	}
	
	public static Date getMonthEndTime(Date date){
		if(date==null) return null ;
		Date newDate = getMonthStartTime(date);
		Calendar c = Calendar.getInstance() ;
		c.setTime(newDate) ;
		setCalendarToZero(c);
		c.set(Calendar.MONTH,c.get(Calendar.MONTH)+1);
		return c.getTime() ;
	}
	/**
	 * 得到本周第一天开始时间
	 * @param now
	 * @return
	 */
	public static Date getWeekStartTime(Date date){
		if(date==null) return null ;
		Calendar c = Calendar.getInstance() ;
		c.setTime(date) ;
		setCalendarToZero(c);
		c.set(Calendar.DAY_OF_WEEK, 1);
		return c.getTime() ;
	}
	
	/**
	 * 得到昨天开始时间
	 * @param now
	 * @return
	 */
	public static Date getYesterdayStartTime(Date date){
		if(date==null) return null ;
		Calendar c = Calendar.getInstance() ;
		c.setTime(date) ;
		setCalendarToZero(c);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.getTime() ;
	}
	
	/**
	 * 得到今天之前多少天的开始时间 — +value
	 * 或今天之后多少天的开始时间  -value
	 * @param date
	 * @return
	 */
	public static Date getDayOffStartTime(Date date,int value){
		
		if(date==null) return null ;
		Calendar c = Calendar.getInstance() ;
		c.setTime(date) ;
		setCalendarToZero(c);
		c.add(Calendar.DAY_OF_MONTH,value);
		return c.getTime() ;
	}
	
	/**
	 * 得到几天前或几天后的今天时间
	 * @param date
	 * @param value
	 * @return
	 */
	public static Date getDayOffTime(Date date,int value){
		
		if(date==null) return null ;
		Calendar c = Calendar.getInstance() ;
		c.setTime(date) ;
		c.add(Calendar.DAY_OF_MONTH,value);
		setCalendarToZero(c);
		return c.getTime() ;
	}
	
	public static Date getYearStartTime(){
		Calendar c = Calendar.getInstance() ;
		setCalendarToZero(c);
		c.set(Calendar.DAY_OF_YEAR, 1);
		return c.getTime() ;
	}
	/**
	 * 获得指定日期的下一天时间
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +1);
		return calendar.getTime();
	}
	
	/**
	 * 获得指定日期前一天时间
	 * @param date
	 * @return
	 */
	public static Date getBeforeDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}
	
	/**
	 * 获得当前时间 value小时后的时间
	 * @param value
	 * @return
	 */
	public static int getDiffSecond(int value){
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
	    c.add(Calendar.HOUR, value);
	    date=c.getTime();
	    int second = (int)(getUnixTimestamp(date) - getUnixTimestamp(new Date()));
	    return second;
	}
	
	/**
	 * 根据秒获得时间差
	 * @param second
	 * @return
	 */
	 @SuppressWarnings("unused")
	public static String getTimeDiff(int second) {
		int h = 0, d = 0, s = 0;
		int temp = second % 3600;
		if (second > 3600) {
			h = second / 3600;
			if (temp != 0) {
				if (temp > 60) {
					d = temp / 60;
					if (temp % 60 != 0) {
						s = temp % 60;
					}
				} else {
					s = temp;
				}
			}
		} else {
			d = second / 60;
			if (second % 60 != 0) {
				s = second % 60;
			}
		}

		return h + "小时" + d + "分钟";
	}
	 
	 
	 
	public static String getUnixtimestampOfDateStartTime(String Date){
		if(!CommonUtils.isEmpty(Date)){
			Date date1 = TimeUtils.yyyyMMdd(Date);
			return TimeUtils.stringOfUnixtimestamp(TimeUtils.getDateStartTime(date1));
		}
		return "";
	}
	
	public static String getUnixtimestampOfDateEndTime(String Date){
		if(!CommonUtils.isEmpty(Date)){
			Date date2 = TimeUtils.yyyyMMdd(Date);
			return TimeUtils.stringOfUnixtimestamp(TimeUtils.getDateEndTime(date2));
		}
		return "";
	}
}
