package com.fcs.common.constant;

/**
 * Created by fengcs on 2018/2/3.
 */
public class AuthConstant {
    /**
     * 加密算法名
     */
    public static final String hashAlgorithmName = "MD5";

    /**
     * hash次数（两次-再hash）
     */
    public static final int hashIterations = 2;

    /**
     * hex解密支持 （false默认就是base64)
     */
    public static final boolean hexEncodedEnabled = true;

    /**
     * salt 撒盐加密
     */
    public static final String salt = "skeleton";

}
