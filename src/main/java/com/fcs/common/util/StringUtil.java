package com.fcs.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	
	public static String[] split(String url, String string) {
		return url.split(string) ;
	}

	public static String trim(String input)
	{
		return input==null ? null : input.trim() ;
	}


	public static boolean isNotEmpty(String input) {
		return input!=null && !input.trim().isEmpty() ;
	}


	public static boolean isEmpty(String input) {
		return input==null || input.trim().isEmpty() ;
	}
	
	/**
	 * 过滤SQL字符串,防止SQL inject
	 * @param sql
	 * @return String
	 */
	public static String encodeSQL(String sql)
	{
		if (sql == null)
		{
			return "";
		}
		// 不用正则表达式替换，直接通过循环，节省cpu时间
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < sql.length(); ++i)
		{
			char c = sql.charAt(i);
			switch(c)
			{
			case '\\':
				sb.append("\\\\");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\'':
				sb.append("\'\'");
				break;
			case '\"':
				sb.append("\\\"");
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	

	
	
	/**
	 * 转换&#123;这种编码为正常字符<br/>
	 * 有些手机会将中文转换成&#123;这种编码,这个函数主要用来转换成正常字符.
	 * @param str
	 * @return String
	 */
	public static String decodeNetUnicode(String str)
	{
		if(str == null) 
			return null;
		
        String pStr = "&#(\\d+);";
        Pattern p = Pattern.compile(pStr);
        Matcher m = p.matcher(str);
        StringBuffer sb = new StringBuffer();
		while (m.find()) {
			String mcStr = m.group(1);
			int charValue = StringUtil.convertInt(mcStr, -1);
			String s = charValue > 0 ? (char) charValue + "" : "";
			m.appendReplacement(sb, Matcher.quoteReplacement(s));
		}
        m.appendTail(sb);
        return sb.toString();		
	}
	
    /**
     * 获取字符型参数，若输入字符串为null，则返回设定的默认值
     * @param str 输入字符串
     * @param defaults 默认值 
     * @return 字符串参数
     */
    public static String convertString(String str, String defaults)
    {
        if(str == null)
        {
            return defaults;
        }
        else
        {
            return str;
        }
    }
    
	
	/**
	 * 
	 * @param input
	 * @param def
	 * @return
	 */
	public static int convertInt(String input, int def) {
		try
		{
			return Integer.valueOf(input) ;
		} catch (Exception e) {}
		return def ;
	}
	
	  /**
     * 获取long型参数，若输入字符串为null或不能转为long，则返回设定的默认值
     * @param str 输入字符串
     * @param defaults 默认值
     * @return long参数
     */
    public static long convertLong(String str, long defaults)
    {
        if(str == null)
        {
            return defaults;
        }        
        try
        {
            return Long.parseLong(str);
        }
        catch(Exception e)
        {
            return defaults;
        }
    }
    
    /**
     * 获取double型参数，若输入字符串为null或不能转为double，则返回设定的默认值
     * @param str 输入字符串
     * @param defaults 默认值
     * @return double型参数
     */
    public static double convertDouble(String str, double defaults)
    {
        if(str == null)
        {
            return defaults;
        }
        try
        {
            return Double.parseDouble(str);
        }
        catch(Exception e)
        {
            return defaults;
        }
    }
    
    /**
     * 获取short型参数，若输入字符串为null或不能转为short，则返回设定的默认值
     * @param str 输入字符串
     * @param defaults 默认值
     * @return short型参数
     */
    public static short convertShort(String str, short defaults)
    {
        if(str == null)
        {
            return defaults;
        }
        try
        {
            return Short.parseShort(str);
        }
        catch(Exception e)
        {
            return defaults;
        }
    }
    
    /**
     * 获取float型参数，若输入字符串为null或不能转为float，则返回设定的默认值
     * @param str 输入字符串
     * @param defaults 默认值
     * @return float型参数
     */
    public static float convertFloat(String str, float defaults)
    {
        if(str == null)
        {
            return defaults;
        }
        try
        {
            return Float.parseFloat(str);
        }
        catch(Exception e)
        {
            return defaults;
        }
    }
    
    /**
     * 获取boolean型参数，若输入字符串为null或不能转为boolean，则返回设定的默认值
     * @param str 输入字符串
     * @param defaults 默认值
     * @return boolean型参数
     */
    public static boolean convertBoolean(String str, boolean defaults)
    {
        if(str == null)
        {
            return defaults;
        }
        try
        {
            return Boolean.parseBoolean(str);
        }
        catch(Exception e)
        {
            return defaults;
        }
    }

    
    /**
	 * 隐藏用户名
	 * 
	 * @param username
	 * @return
	 */
	public static String hideStringUsername(String username) {
		//不搞这么复杂吧，直接取用户名第一个和最后一个就可以了。并且之前的方法对于只有2个字的用户名加不了掩码的
		if (CommonUtils.isEmpty(username)) return null;
		StringBuilder builder = new StringBuilder(4);
		builder.append(username.substring(0, 1));
		builder.append("***");
		builder.append(username.substring(username.length() - 1));
		return builder.toString();
	}
}
