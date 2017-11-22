package encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class MD5Base64 {
	public static void main(String[] args) {  
        String  pass="fjtt";  
        byte[] b=getDigest(pass.getBytes());          
        Encoder b64= Base64.getEncoder();
        pass=b64.encodeToString(b);  
        System.out.println(pass);  
    }  
    //MD5加密  
    public static byte[] getDigest(byte[] b){  
        try {  
            MessageDigest md=MessageDigest.getInstance("MD5");            
            md.update(b);  
            return md.digest();  
        } catch (NoSuchAlgorithmException e) {  
            //FIXME:DDD  
            e.printStackTrace();  
        }  
          
        return null;  
    }  
}
