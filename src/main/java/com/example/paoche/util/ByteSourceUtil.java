package com.example.paoche.util;

import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import java.io.Serializable;

public class ByteSourceUtil implements ByteSource, Serializable {

    public static ByteSource getSimpleByteSource(String salt){
        return Util.bytes(salt);
    }
    @Override
    public byte[] getBytes() {
        return new byte[0];
    }

    @Override
    public String toHex() {
        return null;
    }

    @Override
    public String toBase64() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
