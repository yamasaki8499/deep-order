package com.deepbar.framework.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * Created by josh on 15/6/29.
 */
public class AlgorithmUtil {

    private static String desKey = "1q0p";

    private static String aesKey = "1q2w3e4r5t6y7u8i";

    private static Logger logger = LogManager.getLogger(AlgorithmUtil.class);

    private static MessageDigest md5MD = null;

    private static KeyGenerator aesKeyGen = null;

    static {
        try {
            md5MD = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            logger.error(e);
        }

        try {
            aesKeyGen = KeyGenerator.getInstance("AES");
            aesKeyGen.init(128);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static byte[] encryptDESToByte(String content, String key) {
        byte[] bytes = null;
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
            cipher.init(Cipher.ENCRYPT_MODE, loadDesKey(key), iv);
            bytes = cipher.doFinal(content.getBytes("UTF-8"));
        } catch (Exception ex) {
            logger.error(ex);
        }
        return bytes;
    }

    public static byte[] decryptDESToByte(String hexContent, String key) {
        byte[] bytes = null;
        try {
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
            byte[] source = hex2byte(hexContent);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, loadDesKey(key), iv);
            bytes = cipher.doFinal(source);
        } catch (Exception ex) {
            logger.error(ex);
        }
        return bytes;
    }

    public static String encryptDESToString(String content, String key) {
        byte[] bytes = null;
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
            cipher.init(Cipher.ENCRYPT_MODE, loadDesKey(key), iv);
            bytes = cipher.doFinal(content.getBytes("UTF-8"));
        } catch (Exception ex) {
            logger.error(ex);
        }
        return bytes2hex(bytes);
    }

    public static String decryptDESToString(String hexContent, String key) {
        byte[] bytes = null;
        try {
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
            byte[] source = hex2byte(hexContent);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, loadDesKey(key), iv);
            bytes = cipher.doFinal(source);
        } catch (Exception ex) {
            logger.error(ex);
        }
        if (bytes == null) {
            return StringUtil.EMPTY;
        }
        return new String(bytes);
    }

    public static byte[] encryptAESToByte(String source) {
        byte[] bytes = null;
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, loadKeyAES(aesKey.getBytes("utf-8")));
            bytes = cipher.doFinal(source.getBytes("utf-8"));
        } catch (Exception ex) {
            logger.error(ex);
        }
        return bytes;
    }

    public static byte[] decryptAESToByte(String source) {
        byte[] bytes = null;
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, loadKeyAES(aesKey.getBytes("utf-8")));
            bytes = cipher.doFinal(hex2byte(source));
        } catch (Exception ex) {
            logger.error(ex);
        }
        return bytes;
    }

    public static String encryptAESToString(String source) {
        byte[] bytes = null;
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, loadKeyAES(aesKey.getBytes("utf-8")));
            bytes = cipher.doFinal(source.getBytes("utf-8"));
        } catch (Exception ex) {
            logger.error(ex);
        }
        return bytes2hex(bytes);
    }

    public static String decryptAESToString(String source) {
        byte[] bytes = null;
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, loadKeyAES(aesKey.getBytes("utf-8")));
            bytes = cipher.doFinal(hex2byte(source));
        } catch (Exception ex) {
            logger.error(ex);
        }
        if (bytes == null) {
            return StringUtil.EMPTY;
        }
        return new String(bytes);
    }

    public static String md5(String s) {
        String encoded = StringUtil.EMPTY;
        if (StringUtil.isBlank(s)) {
            return encoded;
        }
        try {
            md5MD.reset();
            md5MD.update(s.getBytes("UTF-8"));
            byte[] byteArray = md5MD.digest();
            encoded = bytes2hex(byteArray);
        } catch (Exception e) {
            logger.error(e);
        }
        return encoded;
    }

    public static String bytes2hex(byte[] bytes) {
        if (bytes == null) {
            return StringUtil.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String temp = Integer.toHexString(b & 0xff);
            if (temp.length() == 1) {
                sb.append("0");
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    public static byte[] hex2byte(String hex) {
        byte digest[] = new byte[hex.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = hex.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }
        return digest;
    }

    private static SecretKey loadDesKey(String key) {
        SecretKey secretKey = null;
        try {
            byte[] keyBytes = key.getBytes("UTF-8");
            DESKeySpec dks = new DESKeySpec(keyBytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            secretKey = keyFactory.generateSecret(dks);
        } catch (Exception ex) {
            logger.error(ex);
        }
        return secretKey;
    }

    private static SecretKey loadKeyAES(byte[] bytes) {
        SecretKey secretKey = new SecretKeySpec(bytes, "AES");
        return secretKey;
    }

    public static void main(String[] args) throws Exception {

    }
}
