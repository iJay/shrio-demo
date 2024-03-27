package com.example.shirojsp01.utils;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.util.ByteSource;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;

/**
 * @author hanbing
 * @date 2024-03-27
 */
public class MySimpleByteSource implements ByteSource, Serializable {

    private static final long serialVersionUID = 1L;

    private byte[] bytes;
    private String cachedHex;
    private String cachedBase64;

    public MySimpleByteSource() {
    }

    public MySimpleByteSource(byte[] bytes) {
        this.bytes = bytes;
    }

    public MySimpleByteSource(String string) {
        this.bytes = string.getBytes();
    }

    public MySimpleByteSource(File file) {
        this.bytes = (new MySimpleByteSource.BytesHelper()).getBytes(file);
    }

    public MySimpleByteSource(InputStream stream) {
        this.bytes = (new MySimpleByteSource.BytesHelper()).getBytes(stream);
    }

    public static boolean isCompatible(Object o) {
        return o instanceof byte[] || o instanceof char[] || o instanceof String || o instanceof ByteSource || o instanceof File || o instanceof InputStream;
    }

    public MySimpleByteSource(char[] chars) {
        this.bytes = CodecSupport.toBytes(chars);
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public byte[] getBytes() {
        return this.bytes;
    }

    @Override
    public String toHex() {
        if (this.cachedHex == null) {
            this.cachedBase64 = Hex.encodeToString(this.getBytes());
        }
        return this.cachedHex;
    }

    @Override
    public String toBase64() {
        if(this.cachedBase64 == null) {
            this.cachedBase64 = Base64.encodeToString(this.getBytes());
        }
        return this.cachedBase64;
    }

    @Override
    public boolean isEmpty() {
        return this.bytes == null || this.bytes.length == 0;
    }

    @Override
    public int hashCode() {
        return this.bytes != null && this.bytes.length != 0? Arrays.hashCode(this.bytes):0;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(o instanceof ByteSource) {
            ByteSource bs = (ByteSource)o;
            return Arrays.equals(this.getBytes(), bs.getBytes());
        } else {
            return false;
        }
    }

    private static final class BytesHelper extends CodecSupport {
        private BytesHelper() {
        }

        public byte[] getBytes(File file) {
            return this.toBytes(file);
        }

        public byte[] getBytes(InputStream stream) {
            return this.toBytes(stream);
        }
    }
}
