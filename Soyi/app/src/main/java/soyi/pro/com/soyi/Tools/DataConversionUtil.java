package soyi.pro.com.soyi.Tools;

public class DataConversionUtil {
	/*String 转int*/
	public static int StringToInt(String value){
	    return Integer.valueOf(value).intValue();
	}
	
	/*int 转String*/
	public static String intToString(int value){
		    return new Integer(value).toString();
	}
	
	/*String 转 float*/
	public static float StringTofloat(String value){
		    return Float.valueOf(value).floatValue();
	}
	
	/*float 转 String*/
	public static String floatToString(String value){
		    return new Float(value).toString();
	}
	
	/* 字符串转换成十六进制字符串 */
	public static String StringToHex(String value)    
	{      
	  
	    char[] chars = "0123456789ABCDEF".toCharArray();      
	    StringBuilder sb = new StringBuilder("");    
	    byte[] bs = value.getBytes();      
	    int bit;         
	    for (int i = 0; i < bs.length; i++)    
	    {      
	        bit = (bs[i] & 0x0f0) >> 4;      
	        sb.append(chars[bit]);      
	        bit = bs[i] & 0x0f;      
	        sb.append(chars[bit]);    
	        sb.append(' ');    
	    }      
	    return sb.toString().trim();      
	}    
	
	/*十六进制转换字符串*/
	public static String HexToString(String value)    
	{      
	    String str = "0123456789ABCDEF";      
	    char[] hexs = value.toCharArray();      
	    byte[] bytes = new byte[value.length() / 2];      
	    int n;      
	    for (int i = 0; i < bytes.length; i++)    
	    {      
	        n = str.indexOf(hexs[2 * i]) * 16;      
	        n += str.indexOf(hexs[2 * i + 1]);      
	        bytes[i] = (byte) (n & 0xff);      
	    }      
	    return new String(bytes);      
	}   
	
	
	/*bytes转换成十六进制字符串 */
	public static String byteToHex(byte[] value)    
	{    
	    String stmp="";    
	    StringBuilder sb = new StringBuilder("");    
	    for (int n=0;n<value.length;n++)    
	    {    
	        stmp = Integer.toHexString(value[n] & 0xFF);    
	        sb.append((stmp.length()==1)? "0"+stmp : stmp);    
	        sb.append(" ");    
	    }    
	    return sb.toString().toUpperCase().trim();    
	}  
	
	/*bytes字符串转换为Byte数组*/
	public static byte[] hexStr2Bytes(String value)    
	{    
	    int m=0,n=0;    
	    int l=value.length()/2;    
	    System.out.println(l);    
	    byte[] ret = new byte[l];    
	    for (int i = 0; i < l; i++)    
	    {    
	        m=i*2+1;    
	        n=m+1;    
	        ret[i] = Byte.decode("0x" + value.substring(i*2, m) + value.substring(m,n));    
	    }    
	    return ret;    
	}    
	
	
	
}
