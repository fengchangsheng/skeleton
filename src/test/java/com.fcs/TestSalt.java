package com.fcs;

import org.apache.shiro.crypto.hash.AbstractHash;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created by fengcs on 2018/2/3.
 */
public class TestSalt {

    public static void main(String[] args) {
        String pass = "123";
        String salt = "skeleton";
        System.out.println(new Md5Hash(pass).toString());
        System.out.println(new Md5Hash(pass,salt).toString());
    }

    public static Hash getHash(String salt) {
//        AbstractHash hash = newHashInstance();
//        hash.setBytes(storedBytes);
//        return hash;
        return new SimpleHash("MD5", "123", salt, 2);
    }

    protected AbstractHash newHashInstance() {
        String hashAlgorithmName = "MD5";
        return new SimpleHash(hashAlgorithmName);
    }
}
