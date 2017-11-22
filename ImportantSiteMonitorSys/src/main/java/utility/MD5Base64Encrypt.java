package utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class MD5Base64Encrypt {
	public static String encrypt(String message) {
		if (message != null) {
			byte[] b = getDigest(message.getBytes());
			Encoder b64 = Base64.getEncoder();
			String pass = b64.encodeToString(b);
			return pass;
		}
		return "";
	}

	// MD5加密
	public static byte[] getDigest(byte[] b) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(b);
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			// FIXME:DDD
			e.printStackTrace();
		}

		return null;
	}
}
