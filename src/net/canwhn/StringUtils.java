package net.canwhn;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Description:  </p>
 * @author �Ž�ΰ
 * @version 1.00
 * <pre>
 * �޸ļ�¼:
 * �޸ĺ�汾			�޸���		�޸�����			�޸�����	
 * 2010-7-24.1		�Ž�ΰ		2010-7-24		create
 * </pre>
 */
public class StringUtils {
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	/**
	 * ���˿ո��������Ĳ���Ϊ�հ׻���null������null
	 * @param value
	 * @return
	 */
	public static String trim(Object value){
		return null!=value?value.toString().trim():null;
	}
	
	/**
	 * ��Stringװ����int
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
	 * ��Objectװ����int
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
	 * ��Stringװ����boolean
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
	 * ����β����trimTempƥ����ַ�
	 * @param value
	 * @param trimTemp ���Ҫ���˶���ַ���֮�����������뷨��"��"������Ҳ�Ը÷��Ž�β
	 * @return
	 */
	public static String trimEnd(String value,String trimTemp){
		String result=value;
		for(int i=value.length()-1;i>=0;i--){
			char valueChar=value.charAt(i);
			if(-1!=trimTemp.indexOf(valueChar+"��")){
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
	 * ��Objectת����sql���String�������Date��ת��Ϊyyyy-MM-dd hh:mm:ss��ʽ�ַ���
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
	 * �滻ָ���ַ���
	 * @param rexp
	 * @param s
	 * @return
	 */
	public static String replaceString(String rexp,String s) {   
		return replaceString(rexp, s, "");     
    }
	/**
	 * �滻ָ���ַ���
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
	 * ��ȡָ���ַ���
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
	 * ��ȡָ���ַ���
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
	 * �Ƚ��ַ���
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
	// ���������λ��ת������
	static final int GB_SP_DIFF = 160;
	// ��Ź���һ�����ֲ�ͬ��������ʼ��λ��

	static final int[] secPosValueList = {
	1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787,

	3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027, 4086,

	4390, 4558, 4684, 4925, 5249, 5600 };
	// ��Ź���һ�����ֲ�ͬ��������ʼ��λ���Ӧ����
	static final char[] firstLetter = {

	'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j',

	'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',

	't', 'w', 'x', 'y', 'z' };
	/**
	 * ��ȡû�����ӵ�����ĸ
	 * @param oriStr
	 * @return
	 */
	public static String getFirstLetter(String oriStr) {

		String str = oriStr.toLowerCase();

		StringBuffer buffer = new StringBuffer();
		char ch;
		char[] temp;
		for (int i = 0; i < str.length(); i++) { // ���δ���str��ÿ���ַ�
			ch = str.charAt(i);
			temp = new char[] { ch };
			byte[] uniCode = new String(temp).getBytes();
			if (uniCode[0] < 128 && uniCode[0] > 0) { // �Ǻ���
				buffer.append(temp);
			} else {
				buffer.append(convert(uniCode));
			}
		}
		return buffer.toString();

	}

	/**
	 * ��ȡһ�����ֵ�ƴ������ĸ��
	 * 
	 * GB�������ֽڷֱ��ȥ160��ת����10��������ϾͿ��Եõ���λ��
	 * 
	 * ���纺�֡��㡱��GB����0xC4/0xE3���ֱ��ȥ0xA0��160������0x24/0x43
	 * 
	 * 0x24ת��10���ƾ���36��0x43��67����ô������λ�����3667���ڶ��ձ��ж���Ϊ��n��
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

		System.out.println(StringUtils.getFirstLetter("��ʱ�"));

		System.out.println(StringUtils.getFirstLetter("�Ұ������찲��"));

		System.out.println(StringUtils.getFirstLetter("I love �����찲��"));
		
		
		String name="2sdd[?]";
		System.out.println(matchString("\\[(\\d*\\??)\\]", name));
		System.out.println(replaceString("\\[([\\d\\?])+\\]", name));
		String temp=matchString("\\[(\\d*\\??)\\]", name);
		System.out.println(temp+"="+matchString("[\\d]+", temp));
		String value="ssl_url";
		System.out.println(replaceString("_url", value));

	}	

}
