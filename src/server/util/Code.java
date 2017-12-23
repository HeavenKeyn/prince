package server.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Base64;


public class Code {
	private static final String CHARSET = "utf-8";
	public static byte[] md5(String msg) {
		if (msg==null) {
			return null;
		}
		byte[] data = null;
		try {
			MessageDigest md5=MessageDigest.getInstance("MD5");
			data = md5.digest(msg.getBytes(CHARSET)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static String getMD5_32(byte[] md5) {
		if (md5==null) {
			return null;
		}
		return new BigInteger(1, md5).toString(16);
	}
	
	public static String getMD5_24(byte[] md5) {
		if (md5==null) {
			return null;
		}
		return new String(Base64.getEncoder().encode(md5));
	}
	
	public static String getMD5_32(String msg) {
		return getMD5_32(md5(msg));
	}
	
	public static String getMD5_24(String msg) {
		return getMD5_32(md5(msg)).substring(0, 24);
	}

}
