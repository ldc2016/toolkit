package com.vip.own.dencrypt;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class SecretUtils {
	
	
	/*
	 * 根据字符串生成密钥字节数组
	 *
	 * @param keyStr 密钥字符串
	 *
	 * @return
	 *
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException {
		final byte[] key = new byte[24]; // 声明一个24位的字节数组，默认里面都是0
		final byte[] temp = keyStr.getBytes("UTF-8"); // 将字符串转成字节数组

		/*
		 * 执行数组拷贝 System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
		 */
		if (key.length > temp.length) {
			// 如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
			System.arraycopy(temp, 0, key, 0, temp.length);
		} else {
			// 如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
			System.arraycopy(temp, 0, key, 0, key.length);
		}
		return key;
	}
	/**
	 * 解密函数
	 *
	 * @param src
	 *            密文的字节数组
	 * @return
	 */
	public static byte[] decryptMode(byte[] src) {
		try {
			final SecretKey deskey = new SecretKeySpec(build3DesKey(PASSWORD_CRYPT_KEY), Algorithm);
			final Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey); // 初始化为解密模式
			return c1.doFinal(src);
		} catch (final Exception e) {
			////e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密函数
	 *
	 * @param str
	 *            密文的字节数组
	 * @return
	 */
	public static String decryptMode(String str) {
		if (null == str || "".equals(str.trim())) {
			return null;
		}
		try {
			return new String(decryptMode(Base64.decode(str)));
		} catch (final Exception e1) {
			////e1.printStackTrace();
		}
		return null;
	}

	public static String decryptMode(String str,String key) {
		if (null == str || "".equals(str.trim())) {
			return null;
		}
		try {
			final SecretKey deskey = new SecretKeySpec(build3DesKey(key), Algorithm);
			final Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey); // 初始化为解密模式
			return new String(c1.doFinal(Base64.decode(str)));
		} catch (final Exception e) {
			////e.printStackTrace();
		}
		return null;

	}

	/**
	 * 加密方法
	 *
	 * @param src
	 *            源数据的字节数组
	 * @return
	 */
	public static byte[] encryptMode(byte[] src) {
		try {
			final SecretKey deskey = new SecretKeySpec(build3DesKey(PASSWORD_CRYPT_KEY), Algorithm); // 生成密钥
			final Cipher c1 = Cipher.getInstance(Algorithm); // 实例化负责加密/解密的Cipher工具类
			c1.init(Cipher.ENCRYPT_MODE, deskey); // 初始化为加密模式
			return c1.doFinal(src);
		} catch (final Exception e) {
			// e
		}
		return null;
	}

	/**
	 * 加密方法
	 *
	 * @param str
	 *            源数据的字符串
	 * @return
	 */
	public static String encryptMode(String str) {
		if (null == str || "".equals(str.trim())) {
			return null;
		}
		// 加密
		final byte[] secretArr = SecretUtils.encryptMode(str.getBytes());
		return Base64.encode(secretArr);

	}


	public static String encryptMode(String str, String key) {
		if (null == str || "".equals(str.trim())) {
			return null;
		}
		try {
			final SecretKey deskey = new SecretKeySpec(build3DesKey(key), Algorithm); // 生成密钥
			final Cipher c1 = Cipher.getInstance(Algorithm); // 实例化负责加密/解密的Cipher工具类
			c1.init(Cipher.ENCRYPT_MODE, deskey); // 初始化为加密模式
			final byte[] secretArr = c1.doFinal(str.getBytes());
			return Base64.encode(secretArr);
		} catch (final Exception e) {
			// e
		}
		return null;
	}



	public static void main(String[] args) {
//		final String msg = "3DES加密解密案例";
//		//System.out.println("【加密前】：" + msg);

		// 加密
		final String secretArr = SecretUtils.decryptMode("MpvxNyLeQGoHM8uCvJZRwg==","2015MoankerVipshop");
		System.out.println("【加密后】：" + secretArr);
		
		String bankcard=SecretUtils.encryptMode("6216739876543211215", "2015MoankerVipshop");
		System.out.println(bankcard);

		// 解密
	//	final byte[] myMsgArr = SecretUtils.decryptMode("");
	//	//System.out.println("【解密后】：" + new String(myMsgArr));
//
//		// 加密
//		final String secretStr = SecretUtils.encryptMode(msg);
//		//System.out.println("【加密后】：" + secretStr);
//
//	
	//	//System.out.println("【解密后】：" + SecretUtils.decryptMode("VMKAyyNJr85TC/N9dz3mDO0NjaSabSObwMCLPEBNRn0EpHAphexMUg=="));

	}

	// 定义加密算法，有DES、DESede(即3DES)、Blowfish
	private static final String Algorithm = "DESede";

	// private static final String PASSWORD_CRYPT_KEY =
	// "2012PinganVitality075522628888ForShenZhenBelter075561869839";
	private static final String PASSWORD_CRYPT_KEY = "2015MoankerVipshop";
}
