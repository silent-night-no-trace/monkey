package com.baidu.demo.util;

import java.security.MessageDigest;

/**
 * @author liangz
 * @date 2018/4/12 17:04
 * MD5 工具类
 **/
public class MD5Util {
	/**
	 * 加密使用的盐
	 */
	private static final String SALT = "iqurongQJQ";

	public static String encode(String password) {
		password = password + SALT;
		return processEncode(password);
	}

	private static String processEncode(String password) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");

			char[] charArray = password.toCharArray();
			byte[] byteArray = new byte[charArray.length];

			for (int i = 0; i < charArray.length; i++) {
				byteArray[i] = (byte) charArray[i];
			}

			byte[] md5Bytes = md5.digest(byteArray);
			StringBuffer hexValue = new StringBuffer();

			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;

				if (val < 16) {
					hexValue.append("0");
				}

				hexValue.append(Integer.toHexString(val));
			}

			return hexValue.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		System.out.println("aaaa: "+encode("123456"));
	}
}
