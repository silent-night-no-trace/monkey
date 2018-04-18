package com.google.gateway.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *  加密工具类
 * @author liangz
 */
public class AesUtils {

	private static final Integer SIX_TEEN = 16;
	private static final  Integer TWO = 2;
    /**
     * MD5 加密
     *
     * @param content
     * @param salt
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String md5(String content, String salt) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update((content + "{" + salt.toString() + "}").getBytes(Charset.forName("UTF-8")));
        // 用来将字节转换成 16 进制表示的字符
        char [] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        // MD5 的计算结果是一个 128 位的长整数，用字节表示就是 16 个字节
        byte [] tmp = instance.digest();
        // 每个字节用 16 进制表示的话，使用两个字符，所以表示成 16 进制需要 32 个字符
        char [] str = new char[SIX_TEEN * TWO];
        // 表示转换结果中对应的字符位置
        int k = 0;
        // 从第一个字节开始，对 MD5 的每一个字节转换成 16 进制字符的转换
        for (int i = 0; i < SIX_TEEN; i++) {
            // 取第 i 个字节
            byte byte0 = tmp[i];
            // 取字节中高 4 位的数字转换,>>> 为逻辑右移，将符号位一起右移
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            // 取字节中低 4 位的数字转换
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }

    /**
     * AES 加密
     *
     * @param content    加密内容
     * @param encryptKey 加密秘钥
     * @return 加密数组
     * @throws Exception
     */
    public static byte[] aesEncrypt(String content, String encryptKey) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(encryptKey.getBytes("UTF-8"));
        keyGen.init(128, secureRandom);
        SecretKey secretKey = keyGen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		// 创建密码器
        Cipher cipher = Cipher.getInstance("AES");
		// 初始化
		cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] result = cipher.doFinal(content.getBytes("UTF-8"));
        return result;
    }

    /**
     * AES 解密
     *
     * @param content    解密内容
     * @param encryptKey 解密秘钥
     * @return
     * @throws Exception
     */
    public static String aesDecrypt(byte[] content, String encryptKey) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(encryptKey.getBytes("UTF-8"));
        keyGen.init(128, secureRandom);
        SecretKey secretKey = keyGen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		// 创建密码器
        Cipher cipher = Cipher.getInstance("AES");
		// 初始化
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] result = cipher.doFinal(content);
        return new String(result, "UTF-8");
    }


    /**
     * 获取AES加密后的 十六进制字符串
     *
     * @param content
     * @param encryptKey
     * @return
     * @throws Exception
     */
    public static String aesEncryptHexString(String content, String encryptKey) throws Exception {
        return parseByte2HexString(aesEncrypt(content, encryptKey));
    }


    /**
     * 解密AES加密后的 十六进制字符串
     *
     * @param hexContent
     * @param encryptKey
     * @return
     * @throws Exception
     */
    public static String aesDecryptHexString(String hexContent, String encryptKey) throws Exception {
        return aesDecrypt(parseHexStr2Byte(hexContent), encryptKey);
    }


    /**
     * 将二进制转换成16进制
     *
     * @param buffer
     * @return
     */
    public static String parseByte2HexString(byte [] buffer) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buffer.length; i++) {
            String hex = Integer.toHexString(buffer[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexString
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexString) {
        if (hexString.length() < 1){
            return null;
        }
        byte[] result = new byte[hexString.length() / TWO];
        for (int i = 0; i < hexString.length() / TWO; i++) {
            int high = Integer.parseInt(hexString.substring(i * 2, i * 2 + 1), SIX_TEEN);
            int low = Integer.parseInt(hexString.substring(i * 2 + 1, i * 2 + 2), SIX_TEEN);
            result[i] = (byte) (high * SIX_TEEN + low);
        }
        return result;
    }

}
