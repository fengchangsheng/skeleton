package com.fcs.common.util;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 工具类
 */
public class CommonUtils {

	public static final String UTF8 = "UTF-8" ;
	
	public static final List<?> EMPTY_LIST = Collections.unmodifiableList(new ArrayList<Object>());
	
	public static final Map<?,?> EMPTY_MAP = Collections.unmodifiableMap(new HashMap<Object, Object>());
	

	public static boolean isEmpty(String input) {
		return input==null || input.trim().isEmpty() ;
	}
	
	public static String emptyIfNull(String input) {
		return input==null ? "" : input.trim() ;
	}
	
	public static String emptyIfNull(Object input) {
		return input == null ? "" : input.toString().trim();
	}
	
	public static String emptyIfNull(String input, String def) {
		input = emptyIfNull(input) ;
		return input.isEmpty() ? def : input ;
	}
	
	public static int parseInt(Object data) {
		return parseInt(data, 0);
	}
	
	public static int parseInt(Object data, int def) {
		if (data != null) {
			try {
				if (data instanceof Number)
					return ((Number) data).intValue();
				return Integer.valueOf(String.valueOf(data));
			} catch (Exception ignore) {}
		}
		return def;
	}
	
	public static long parseLong(Object data) {
		return parseLong(data, 0L);
	}
	
	@Deprecated
	public static long parseLong(Object data, int def) {
		return parseLong(data, (long) def);
	}
	
	public static long parseLong(Object data, long def) {
		if (data != null) {
			try {
				if (data instanceof Number)
					return ((Number) data).longValue();
				return Long.valueOf(String.valueOf(data));
			} catch (Exception ignore) {
			}
		}
		return def;
	}
	
	public static double parseDouble(Object data) {
		return parseDouble(data, (double)0);
	}

	public static double parseDouble(Object data, double def) {
		double value = def;
		if (data != null) {
			try {
				if (data instanceof BigDecimal) {
					value = ((BigDecimal) data).doubleValue();
				} else if (data instanceof Number) {
					value = ((Number) data).doubleValue();
				} else {
					value = Double.valueOf(String.valueOf(data));
				}
			} catch (Exception ignore) {
				//do nothing
			}
		}
		return value == def ? def : MathUtils.roundHalfUp(value);
	}
	
	public static Date parseUnixtime(Object data) {
		if (data == null) {
			return null;
		} else if (data instanceof java.sql.Timestamp) {
			return TimeUtils.parse((java.sql.Timestamp) data);
		} else if (data instanceof Number) {
			return TimeUtils.newDateByUnixTimestamp(((Number) data).longValue());
		} else if (data instanceof String) {
			return TimeUtils.newDateByUnixTimestamp((String) data);
		}
		return null;
	}

	/**
	 * 将unix秒数转换为milliseconds
	 * @param data
	 * @return
	 */
	public static long unixtimeToMilliseonds(Object data) {
		long unixtimestamp = 0;
		if (data != null) {
			if (data instanceof java.sql.Timestamp) {
				unixtimestamp = ((java.sql.Timestamp) data).getTime();
			} else if (data instanceof Number) {
				unixtimestamp = ((Number) data).longValue();
			} else if (data instanceof String) {
				unixtimestamp = Long.valueOf((String) data).longValue();
			}
		}
		return (unixtimestamp == 0 ? 0 : unixtimestamp * 1000);
	}

	public static boolean isEmpty(Object[] array) {
		return array==null || array.length==0 ;
	}
	
	public static boolean isEmpty(Collection<?> c) {
		return c==null || c.isEmpty() ;
	}

	public static boolean isNotEmpty(Collection<?> c) {
		return !isEmpty(c);
	}
	
	
	/**
	 * 在from和to都不为null的情况下, 将集合类from添加到to中去
	 */
	public static <T> boolean addAll(Collection<T> from, Collection<T> to) {
		if(from!=null && to!=null) {
			return to.addAll(from) ;
		}
		return false ;
	}

	public static int size(Collection<?> c) {
		return c==null ? 0 : c.size() ;
	}
	
    @SuppressWarnings("unchecked")
	public static <T> List<T> emptyList() {
    	return (List<T>) EMPTY_LIST;
    }
    
    /**
     * 获取一个空List
     */
    @SuppressWarnings("unchecked")
	public static <T> List<T> emptyList(List<T> list) {
    	return (List<T>) (list==null ? emptyList() : list) ;
    }
    
	public static <T> List<T> newArrayList(Collection<?> c) {
		return new ArrayList<>(size(c));
	}

	public static <T> Set<T> newHashSet(Collection<?> c) {
		return new HashSet<>(size(c), 1.0f) ;
	}

    public static boolean isEmpty(Map<?,?> map) {
    	return map==null || map.isEmpty() ;
    }

    @SuppressWarnings("unchecked")
	public static <K,V> Map<K,V> emptyMap() {
    	return (Map<K,V>) EMPTY_MAP;
    }

    @SuppressWarnings("unchecked")
	public static <K,V> Map<K,V> emptyMap(Map<K,V> map) {
    	return (Map<K, V>) (map == null ? EMPTY_MAP : map) ;
    }

	public static <K,V> Map<K,V> stableMap(int size) {
    	return new HashMap<K, V>(size, 1.0f) ;
    }
    
	/**
	 * 获取数组指定位置的值,越界则返回def
	 * @param array
	 * @param index
	 * @param def
	 */
	public static <T> T indexGet(T[] array, int index, T def) {
		int arrayLength = array == null ? 0 : array.length;
		return indexGet(array, arrayLength, index, def);
	}

	/**
	 * 获取数组指定位置的值,越界则返回def
	 * @param array
	 * @param arrayLength
	 * @param index
	 * @param def
	 */
	public static <T> T indexGet(T[] array, int arrayLength, int index, T def) {
		if (index >= 0 && index < arrayLength) {
			return array[index];
		}
		return def;
	}
	
	/**
	 * 过滤html标签和部分特殊字符
	 * @param content
	 * @return
	 */
	public static String filterHtml(String content) {
		if (CommonUtils.isEmpty(content))
			return "";

		Map<String, String> regs = new HashMap<String, String>();
		regs.put("<([^>]*)>", "");
		regs.put("(&nbsp;)", " ");
		regs.put("(&apos;)", "'");
		regs.put("(&quot;)", "\"");
		regs.put("(&ldquo;)", "“");
		regs.put("(&rdquo;)", "”");
		regs.put("(&lt;)", "<");
		regs.put("(&gt;)", ">");
		regs.put("(&ndash;)", "-");

		Pattern p = null;
		Matcher m = null;
		for (Map.Entry<String, String> entry : regs.entrySet()) {
			p = Pattern.compile(entry.getKey(), Pattern.CASE_INSENSITIVE); //横杠
			m = p.matcher(content);
			content = m.replaceAll(entry.getValue());
		}
		return content;
	}

	/**
	 * 兼容旧数据
	 * @param str
	 * @return
	 */
	@Deprecated
	public static String replaceHref(String str) {
		if (str == null)
			return null;
		String firstStr = "<a href='/invest/a", lastStr = ".html' target=_blank>";
		String firstReplace = "<a href='/invest-page.html?id=", lastReplace = "' target=_blank>";
		int first = str.indexOf(firstStr);
		if (first >= 0) {
			str = str.replaceAll(firstStr, firstReplace);
			str = str.replaceAll(lastStr, lastReplace);
		}
		return str;
	}

	public static IllegalStateException illegalStateException(Throwable t) {
		return new IllegalStateException(t) ;
	}
	
	public static IllegalStateException illegalStateException(String message) {
		return new IllegalStateException(message) ;
	}
	
	public static IllegalStateException illegalStateException(String message, Throwable t) {
		return new IllegalStateException(message, t) ;
	}

	public static IllegalArgumentException illegalArgumentException(String message) {
		return new IllegalArgumentException(message) ;
	}

	public static UnsupportedOperationException unsupportedMethodException(){
		return new UnsupportedOperationException("unsupport this method");
	}

	public static Throwable foundRealThrowable(Throwable t) {
		Throwable cause = t.getCause() ;
		if(cause==null) return t ;
		return foundRealThrowable(cause) ;
	}

	/**
	 * 格式化异常
	 */
	public static String formatThrowable(Throwable t) {
		if (t == null) return "";
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		pw.flush();
		sw.flush();
		return sw.toString();
	}
	
	
	
	public static String formatThrowableForHtml(Throwable t) {
		String ex = formatThrowable(t) ;
		return ex.replaceAll("\n\t", " ") ;
	}
	
	
	
	/**
	 * 实例化对象,注意该对象必须有无参构造函数
	 * 
	 * @param klass
	 * @return
	 */
	public static <T> T newInstance(Class<T> klass) {
		try {
			return (T) klass.newInstance();
		} catch (Exception e){ throw new IllegalArgumentException("instance class[" + klass.getName() + "] with ex:", e); }
	}

	
	/**
	 * 
	 * @param klass
	 * @param cstTypes
	 * @param cstParameters
	 * @return
	 */
	public static <T> T newInstance(Class<T> klass, Class<?>[] cstTypes, Object... cstParameters) {
		try {
			Constructor<T> cst = klass.getConstructor(cstTypes) ;
			return cst.newInstance(cstParameters) ;
		} catch (Exception e){ throw new IllegalArgumentException("instance class[" + klass.getName() + "], cstTypes="+Arrays.toString(cstTypes)+", "+Arrays.toString(cstParameters)+" with ex:", e); }
	}
	
	
	
	/**
	 * 
	 * @param className
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(String className) {
		try {
			return (T) newInstance(classForName(className)) ;
		} catch (Exception e){ throw new IllegalArgumentException("instance class[" + className + "] with ex:", e); }
	}
	
	
	
	
	/**
	 * 
	 * @param className
	 * @return
	 */
	public static Class<?> classForName(String className) {
		try 
		{
			return Class.forName(className, false, Thread.currentThread().getContextClassLoader()) ;
			
		} catch(Exception ignore) {
			try 
			{
				return Class.forName(className) ;
			} catch (Exception e){ throw new IllegalArgumentException("classForName(" + className + ")  with ex:", e); }
		}
	}

	
	
	
	/**
	 * 
	 * @param className
	 * @param classLoader
	 * @return
	 */
	public static Class<?> loadClass(String className, ClassLoader classLoader) {
		try {
			return classLoader.loadClass(className) ;
		} catch (Exception e){ throw new IllegalArgumentException("loadClass(" + className + ")  with ex:", e); }
	}
	
	
	/**
	 * 
	 * @param filename
	 * @return
	 */
	public static InputStream getInputStreamFromClassPath(String filename) {
		if(CommonUtils.isEmpty(filename)) return null ;
		
		URL url = Thread.currentThread().getContextClassLoader().getResource(filename) ;
		System.out.println("[load file from classpath]:name={" + filename + "}, locator={" + url+"}");
		if(url==null) return null ;
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
	}
	
	
	
	
	/**
	 * 合并多个路径
	 * 
	 * @param paths
	 * @return
	 */
	public static String mergePath(String...paths) {
		if(isEmpty(paths)) return null ;
		StringBuilder builder = new StringBuilder(paths[0]) ;
		for(int i=1; i<paths.length; i++)
		{
			String path = emptyIfNull(paths[i]) ;
			path = path.startsWith(File.separator) ? path.substring(1) : path ;
			path = path.endsWith(File.separator) ? path.substring(0, path.length()-1) : path ;
			builder.append(File.separator).append(path) ;
		}
		return builder.toString() ;
	}
	
	
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	public static String urlDecodeUTF8(String input) {
		if (input == null)
			return null;
		try {
			return URLDecoder.decode(input, UTF8);
		} catch (Exception e) {
			throw illegalStateException(e);
		}
	}
	
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	public static String urlEncodeUTF8(String input) {
		if (input == null)
			return null;
		try {
			return URLEncoder.encode(input, UTF8);
		} catch (Exception e) {
			throw illegalStateException(e);
		}
	}

	
	
	/**
	 * 
	 * @param map
	 * @param key
	 * @param value
	 */
	public static <K, V> void putIfNotNull(Map<K, V> map, K key, V value) {
		if (map == null) 
			return;
		if (key != null && value != null)
			map.put(key, value);
	}

	
	/**
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(Map<?, ?> map, Object key) {
		return (T) (map==null ? null : map.get(key)) ;
	}
	
	
	/**
	 * 
	 * @param map
	 * @param key
	 * @param def
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(Map<?, ?> map, Object key, T def) {
		T t = null ;
		try {
			Object v = get(map, key) ;
			if(v==null) {
				t = def ;
			}
			else
			{
				if(def == null) {
					t = (T) v ;
				} else {
					Object to = TypesUtils.getDefaultValue(def.getClass()) ;
					t = (T)TypesUtils.cashTo(v, to) ;
				}
			}
		}catch(Exception ignore){}
		return t==null ? def : t ;
	}
	
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	public static RuntimeException convertRuntimeException(Throwable e) {
		RuntimeException ex = null ;
		if(e!=null) {
			if(e instanceof RuntimeException) {
				ex = (RuntimeException) e ;
			} else {
				ex = new RuntimeException(e) ;
			}
		}
		return ex ;
	}
	
	
	/**
	 * 简单的方式判断一个字符串是否是手机号码
	 * @param input
	 * @return
	 */
	public static boolean isPhone(String input) {
		//1. length and first char
		if(input==null || input.length()!=11 || !input.startsWith("1")) return false ;
		//2. number
		boolean is = isNumber(input) ;
		return is ;
	}
	
	
	/**
	 * 判断一个字段串是否是整形数字
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isNumber(String input) {
		if(input==null) return false ;
		for(int index=0; index<input.length(); index++) {
			if (!Character.isDigit(input.charAt(index))) return false ;
		}
		return true ;
	}
	
	
	
	private static Boolean isWindowsOS = null ;
	
	
	/**
	 * 是否是windows操作系统
	 * 
	 * @return
	 */
	public static boolean isWindowsOS() {
		if(isWindowsOS==null)
		{
			isWindowsOS = (System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1) ;
		}
		return isWindowsOS.booleanValue() ;
	}
	
	
	public static void main(String[] args) {
//		System.out.println(isNumber("123"));
//		System.out.println(isPhone("13800138000"));
//		double b= 50.001 ;
//		int a = (int) b ;
//		System.out.println(a);
		Integer.parseInt("1.333") ;
	}
}
