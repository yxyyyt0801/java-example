package com.sciatta.java.jdk.classloader.crypt;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Base64;

/**
 * Created by Rain on 2026/11/2<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * CryptClassLoader
 */
public class CryptClassLoader extends ClassLoader{

    public static void main(String[] args) {
        CryptClassLoader cryptClassLoader = new CryptClassLoader();
        try {
            Class<?> cryptClass = cryptClassLoader.findClass("com.sciatta.java.jdk.classloader.crypt.HelloWorld");  // 完整包路径
            Object instance = cryptClass.getDeclaredConstructor().newInstance();
            Method sayHello = cryptClass.getMethod("sayHello");
            System.out.println("====");
            System.out.println(sayHello.invoke(instance));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

     @Override
    protected Class<?> findClass(String name) {
        byte[] classData = decode();
        return defineClass(name, classData, 0, classData.length);
    }

     private byte[] decode() {
         byte[] encode = encode();
         System.out.println("加密：" + new String(encode));

         byte[] decode = Base64.getDecoder().decode(encode);
         System.out.println("解密：" + new String(decode));
         return decode;
     }

    private byte[] encode() {
        String resourcePath = "com/sciatta/java/jdk/classloader/crypt/HelloWorld.class";    // 完整包路径
        try (InputStream in = CryptClassLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (in == null) {
                throw new IOException("未找到类文件: " + resourcePath);
            }
            return Base64.getEncoder().encode(in.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
