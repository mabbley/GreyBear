package com.bear.common.core;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.apache.commons.codec.binary.Base64;

import java.security.*;

/**
 * Created by mby on 2019/4/17.
 */
public class EncryptUtils {

    private static final String sala = "bear2019";

    public static String hmacMd5(String param){
        return hmacMd5Sala(param,sala);
    }

    public static String hmacSha256(String param){
        return hmacSha256Sala(param,sala);
    }

    public static String hmacSha512(String param){
        return hmacSha512Sala(param,sala);
    }

    public static String hmacMd5Sala(String param,String sala){
        return Hashing.hmacMd5(sala.getBytes()).newHasher().putString(param, Charsets.UTF_8).hash().toString();
    }

    public static String hmacSha256Sala(String param,String sala){
        return Hashing.hmacSha256(sala.getBytes()).newHasher().putString(param, Charsets.UTF_8).hash().toString();
    }

    public static String hmacSha512Sala(String param,String sala){
        return Hashing.hmacSha512(sala.getBytes()).newHasher().putString(param, Charsets.UTF_8).hash().toString();
    }


    //生成密钥对
    static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512); //可以理解为：加密后的密文长度，实际原文要小些 越大 加密解密越慢
        KeyPair keyPair = keyGen.generateKeyPair();
        return keyPair;
    }

    //用md5生成内容摘要，再用RSA的私钥加密，进而生成数字签名
    static String getMd5Sign(String content , PrivateKey privateKey) throws Exception {
        byte[] contentBytes = content.getBytes("utf-8");
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKey);
        signature.update(contentBytes);
        byte[] signs = signature.sign();
        return Base64.encodeBase64String(signs);
    }

    //对用md5和RSA私钥生成的数字签名进行验证
    static boolean verifyWhenMd5Sign(String content, String sign, PublicKey publicKey) throws Exception {
        byte[] contentBytes = content.getBytes("utf-8");
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(publicKey);
        signature.update(contentBytes);
        return signature.verify(Base64.decodeBase64(sign));
    }

    //用sha1生成内容摘要，再用RSA的私钥加密，进而生成数字签名
    static String getSha1Sign(String content , PrivateKey privateKey) throws Exception {
        byte[] contentBytes = content.getBytes("utf-8");
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateKey);
        signature.update(contentBytes);
        byte[] signs = signature.sign();
        return Base64.encodeBase64String(signs);
    }

    //对用md5和RSA私钥生成的数字签名进行验证
    static boolean verifyWhenSha1Sign(String content, String sign, PublicKey publicKey) throws Exception {
        byte[] contentBytes = content.getBytes("utf-8");
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initVerify(publicKey);
        signature.update(contentBytes);
        return signature.verify(Base64.decodeBase64(sign));
    }
}
