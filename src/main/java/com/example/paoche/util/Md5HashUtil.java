package com.example.paoche.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5HashUtil {

    public static String hashPassword(String password,String salt,Integer iterations){
        Md5Hash md5Hash = new Md5Hash(password,salt,iterations);
        String credentialPassword = md5Hash.toHex();
        return credentialPassword;
    }
}
