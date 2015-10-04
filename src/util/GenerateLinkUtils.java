package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletRequest;


import model.vo.User;

public class GenerateLinkUtils {
	//重新設置密碼的鏈接
	//生成重設密碼的鏈接 
	 private static final String CHECK_CODE = "checkCode"; 

	public static String generateResetPwdLink(User user){
		return "http://localhost:8080/AccountActivate/resetPasswordUI?memberAccount="   
                + user.getMemberAccount() + "&" + CHECK_CODE +   "=" + generateCheckcode(user);  
	}

	/** 
     * 生成驗證帳戶的MD5校驗碼 
     * @param user  要激活的帳戶 
     * @return 將用戶名和密碼組合後，通過md5加密後的16進制格式的字符串 
     */  
	
	private static String generateCheckcode(User user) {
		String memberAccount = user.getMemberAccount();
		String randomCode = user.getRandomCode();
		return md5(memberAccount+":"+randomCode);
	}

	 /** 
     * 驗證校驗碼是否和注冊時發送的驗證碼一致 
     * @param user 要激活的帳戶 
     * @param checkcode 注冊時發送的校驗碼 
     * @return 如果一致返回true，否則返回false 
     */  
	 public static boolean verifyCheckcode(User user,ServletRequest request) {  
	        String checkCode = request.getParameter(CHECK_CODE);  
	        return generateCheckcode(user).equals(checkCode);  
	    }  
	
	
	private static String md5(String string) {
		MessageDigest md = null;
		try{
			md = MessageDigest.getInstance("md5");
			md.update(string.getBytes());
			byte[]md5Bytes = md.digest();
			return bytes2Hex(md5Bytes);
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return null;
	}

	 private static String bytes2Hex(byte[] byteArray)  
	    {  
	        StringBuffer strBuf = new StringBuffer();  
	        for (int i = 0; i < byteArray.length; i++)  
	        {  
	            if(byteArray[i] >= 0 && byteArray[i] < 16)  
	            {  
	                strBuf.append("0");  
	            }  
	            strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));  
	        }  
	        return strBuf.toString();  
	}
}
