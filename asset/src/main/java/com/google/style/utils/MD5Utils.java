package com.google.style.utils;

/**
 * md5 工具类
 *
 * @author liangz
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Slf4j
public class MD5Utils {
	private static final String SALT = "1qazxsw2";

	private static final String ALGORITH_NAME = "md5";

	private static final int HASH_ITERATIONS = 2;

//	public static String encrypt(String pwd) {
//		String newPassword = new SimpleHash(ALGORITH_NAME, pwd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
//		return newPassword;
//	}
//
//	public static String encrypt(String username, String pwd) {
//		String newPassword = new SimpleHash(ALGORITH_NAME, pwd, ByteSource.Util.bytes(username + SALT),
//				HASH_ITERATIONS).toHex();
//		return newPassword;
//	}


	private static final String hexDigIts[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

	/**
	 * MD5加密
	 *
	 * @param origin      字符
	 * @param charsetName 编码
	 * @return
	 */
	public static String MD5Encode(String origin, String charsetName) {
		String resultString = null;
		try {
			resultString = origin;
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (StringUtils.isEmpty(charsetName)) {
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			} else {
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetName)));
			}
		} catch (Exception e) {
		}
		return resultString;
	}

	/**
	 * MD5加密
	 *
	 * @param username username
	 * @param pwd      pwd
	 * @return String
	 */
	public static String generatePwd(String username, String pwd) {
		String resultString = null;
		try {
			resultString = username + pwd;
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes(StandardCharsets.UTF_8)));

		} catch (Exception e) {
			log.error("生成密码出错: " + e.getMessage());
		}
		return resultString;
	}


	public static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	public static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n += 256;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigIts[d1] + hexDigIts[d2];
	}

}
