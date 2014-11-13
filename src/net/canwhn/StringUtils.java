package net.canwhn;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Description:  </p>
 * @author 张建伟
 * @version 1.00
 * <pre>
 * 修改记录:
 * 修改后版本			修改人		修改日期			修改内容	
 * 2010-7-24.1		张建伟		2010-7-24		create
 * </pre>
 */
public class StringUtils {
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	/**
	 * 过滤空格，如果传入的参数为空白或是null，返回null
	 * @param value
	 * @return
	 */
	public static String trim(Object value){
		return null!=value?value.toString().trim():null;
	}
	
	/**
	 * 将String装换成int
	 * @param value
	 * @return
	 */
	public static Integer toInteger(String value){
		if(null!=value){
			value=value.trim();
			value="".equals(value)?null:value;
		}
		return null!=value?Integer.parseInt(value):null;
	}
	/**
	 * 将Object装换成int
	 * @param value
	 * @return
	 */
	public static Integer toInteger(Object value){
		if(value instanceof Integer){
			return (Integer)value;
		}else{
			return toInteger(value.toString());
		}
	}
	/**
	 * 将String装换成boolean
	 * @param value
	 * @return
	 */
	public static Boolean toBoolean(String value){
		if(null!=value){
			value=value.trim();
			value="".equals(value)?null:value;
		}
		return null!=value?Boolean.parseBoolean(value):null;
	}
	/**
	 * 过滤尾部和trimTemp匹配的字符
	 * @param value
	 * @param trimTemp 如果要过滤多个字符，之间以中文输入法的"："隔开，也以该符号结尾
	 * @return
	 */
	public static String trimEnd(String value,String trimTemp){
		String result=value;
		for(int i=value.length()-1;i>=0;i--){
			char valueChar=value.charAt(i);
			if(-1!=trimTemp.indexOf(valueChar+"：")){
				result=value.substring(0, i);
			}else{
				break;
			}
		}
		return result;
	}
	public static String trimBlank(String value){
		 Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	      Matcher m = p.matcher(value);
	      value = m.replaceAll("");
	      return value;
	}


	
	
	/**
	 * 将Object转化成sql语句String，如果是Date，转化为yyyy-MM-dd hh:mm:ss格式字符串
	 * @param value
	 * @return
	 */
	public static String toSqlValue(Object value){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(null!=value){
			if(value instanceof Date){
				result="to_date('"+sdf.format((Date)value)+"', 'yyyy-mm-dd hh24:mi:ss')";
			}else if(value instanceof String){
				result="'"+value.toString()+"'";
			}else{
				result=value.toString();
			}
		}else{
			result="null";
		}
		return result;
	}
	/**
	 * 替换指定字符串
	 * @param rexp
	 * @param s
	 * @return
	 */
	public static String replaceString(String rexp,String s) {   
		return replaceString(rexp, s, "");     
    }
	/**
	 * 替换指定字符串
	 * @param rexp
	 * @param s
	 * @return
	 */
	public static String replaceString(String rexp,String s,String dest) {   
		StringBuffer buff=new StringBuffer();
        Pattern p = Pattern.compile(rexp);  
        Matcher m = p.matcher(s); 
        boolean result = m.find();    
        while (result) {
        	for(int i=0;i<m.groupCount();i++){
        		m.appendReplacement(buff, dest);
        	}
            result = m.find();    
        }   
        return buff.toString();       
    }
	/**
	 * 获取指定字符串
	 * @param rexp
	 * @param s
	 * @return
	 */
	public static String matchString(String rexp,String s) { 
		StringBuffer buff=new StringBuffer();;
		Pattern p = Pattern.compile(rexp);  
		Matcher m = p.matcher(s); 
		while(m.find()){
			buff.append(m.group());
		}
		return buff.toString();
	}
	/**
	 * 获取指定字符串
	 * @param rexp
	 * @param s
	 * @return
	 */
	public static List<String> matchStringList(String rexp,String s) { 
		List<String> result=new ArrayList<String>();
		Pattern p = Pattern.compile(rexp);  
		Matcher m = p.matcher(s); 
		while(m.find()){
			result.add(m.group());
		}
		return result;
	}

	/**
	 * 比较字符串
	 * @param a
	 * @param b
	 * @return
	 */
	public static Boolean equals(String a,String b) { 
		if(a==null){
			return a==b;
		}else{
			return a.equals(b);
		}
	}
	// 国标码和区位码转换常量
	static final int GB_SP_DIFF = 160;
	// 存放国标一级汉字不同读音的起始区位码

	static final int[] secPosValueList = {
	1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787,

	3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027, 4086,

	4390, 4558, 4684, 4925, 5249, 5600 };
	// 存放国标一级汉字不同读音的起始区位码对应读音
	static final char[] firstLetter = {

	'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j',

	'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',

	't', 'w', 'x', 'y', 'z' };
	/**
	 * 获取没个汉子的首字母
	 * @param oriStr
	 * @return
	 */
	public static String getFirstLetter(String oriStr) {

		String str = oriStr.toLowerCase();

		StringBuffer buffer = new StringBuffer();
		char ch;
		char[] temp;
		for (int i = 0; i < str.length(); i++) { // 依次处理str中每个字符
			ch = str.charAt(i);
			temp = new char[] { ch };
			byte[] uniCode = new String(temp).getBytes();
			if (uniCode[0] < 128 && uniCode[0] > 0) { // 非汉字
				buffer.append(temp);
			} else {
				buffer.append(convert(uniCode));
			}
		}
		return buffer.toString();

	}

	/**
	 * 获取一个汉字的拼音首字母。
	 * 
	 * GB码两个字节分别减去160，转换成10进制码组合就可以得到区位码
	 * 
	 * 例如汉字“你”的GB码是0xC4/0xE3，分别减去0xA0（160）就是0x24/0x43
	 * 
	 * 0x24转成10进制就是36，0x43是67，那么它的区位码就是3667，在对照表中读音为‘n’
	 * 
	 */

	static char convert(byte[] bytes) {

		char result = '-';

		int secPosValue = 0;

		int i;

		for (i = 0; i < bytes.length; i++) {

			bytes[i] -= GB_SP_DIFF;

		}

		secPosValue = bytes[0] * 100 + bytes[1];

		for (i = 0; i < 23; i++) {

			if (secPosValue >= secPosValueList[i]
					&& secPosValue < secPosValueList[i + 1]) {

				result = firstLetter[i];

				break;

			}
		}
		return result;
	}

	public static void main(String[] args) {

		System.out.println(StringUtils.getFirstLetter("余呈标"));

		System.out.println(StringUtils.getFirstLetter("我爱北京天安门"));

		System.out.println(StringUtils.getFirstLetter("I love 北京天安门"));
		
		
		String name="2sdd[?]";
		System.out.println(matchString("\\[(\\d*\\??)\\]", name));
		System.out.println(replaceString("\\[([\\d\\?])+\\]", name));
		String temp=matchString("\\[(\\d*\\??)\\]", name);
		System.out.println(temp+"="+matchString("[\\d]+", temp));
		String value="ssl_url";
		System.out.println(replaceString("_url", value));

	}	

}
