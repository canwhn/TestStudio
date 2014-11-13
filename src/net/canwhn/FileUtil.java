package net.canwhn;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;



public class FileUtil {
	public static boolean cleanDir(String filepath){
		File f = new File(filepath);//定义文件路径          
		if (f.exists() && f.isDirectory()) {//判断是文件还是目录   
			try {
				FileUtils.cleanDirectory(f);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	/**
	 * 读取文件内容
	 * @return
	 */
	public static String readFile(String path){
		return readFile(path,null);
	}
	/**
	 * 根据文件路径获取文件名
	 * @param path
	 * @return
	 */
	public static String getFileName(String path){
		if(EmptyUtils.isEmpty(path)) return path;
		int index=path.lastIndexOf("\\");
		if(-1!=index){
			return path.substring(index+1); 
		}
		return path;
	}
	/**
	 * 读取数据返回行信息
	 * @param path
	 * @param encoding
	 * @return
	 */
	public static List<String>  readLines(String path,String encoding){
		File file=new File(path);
		List<String> result=new ArrayList<String>();
		result=readLines(file, encoding);
		return result;
	}
	public static List<String> readLines(File file, String encoding){
		List<String> result=new ArrayList<String>();
		try {
			if(EmptyUtils.isNotEmpty(encoding)){
				result= FileUtils.readLines(file, encoding);
			}else{
				result= FileUtils.readLines(file,"UTF-8");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public static List<String> readLines(File file){
		return readLines(file, null);
	}
	/**
	 * 读取数据返回行信息
	 * @param path
	 * @param encoding
	 * @return
	 */
	public static List<String>  readLines(String path){
		return readLines(path, null);
	}
	/**
	 * 读取文件内容
	 * @return
	 */
	public static String readFile(String path,String encoding){
		File file=new File(path);
		return readFile(file, encoding);
	}
	public static String readFile(File file,String encoding){
		String result="";
		try {
			if(EmptyUtils.isNotEmpty(encoding)){
				result= FileUtils.readFileToString(file, encoding);
			}else{
				result= FileUtils.readFileToString(file,"UTF-8");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	//判断文件夹下是否存在文件
	public static boolean existFile(String filePath){
		File file=new File(filePath);
		if(file.isDirectory()){
			if(file.listFiles().length>0){
				return true;
			}
		}
		return false;
	}
	/**
	 * 获取工程路径
	 * @return
	 */
	public static String getProjectPath(){
		return System.getProperty("user.dir");
	}
	/**
	 * 获取指定文件夹下的符合条件的所有文件
	 * @param file 指定文件夹
	 * @param filter 过滤查询
	 * @param fileNames  查找文件名
	 * @return
	 */
	public static Set<File> getAllFile(File file,java.io.FileFilter filter,String [] startNames,String [] endNames){
		Set<File> allFile=new HashSet<File>();
		if(!file.exists()){
			System.out.println("文件不存在");
			return allFile;
		}
		File[] files=file.listFiles(filter);
		for (File f : files) {
			if(f.isDirectory()){
				allFile.addAll(getAllFile(f,filter,startNames,endNames));
			}else{
				if(!filter.accept(f)){//查询的文件
					continue;
				}
				if(EmptyUtils.isEmpty(startNames)&&EmptyUtils.isEmpty(endNames)){
					allFile.add(f);
					continue;
				}
				if(EmptyUtils.isNotEmpty(startNames)){
					for (String startName : startNames) {
						if(f.getAbsolutePath().startsWith(startName)){
							allFile.add(f);
							break;
						}
					}
				}
				if(EmptyUtils.isNotEmpty(endNames)){
					for (String endName : endNames) {
						if(f.getAbsolutePath().endsWith(endName)){
							allFile.add(f);
							break;
						}
					}
				}
			}
		}
		return allFile;
	}
	public static Set<File> getAllFile(String path,String... suffixs){
		File file=new File(path);
		Set<String> _suffixs=new HashSet<String>();
		if(null!=suffixs){
			for (String suffix : suffixs) {
				_suffixs.add(suffix);
			}
		}
		return getAllFile(file, _suffixs);
	}
	public static Set<File> getAllFile(File file,String... suffixs){
		Set<String> _suffixs=new HashSet<String>();
		for (String suffix : suffixs) {
			_suffixs.add(suffix);
		}
		return getAllFile(file, _suffixs);
	}
	/**
	 * 获取指定文件夹下后缀为suffix的文件
	 * @param file
	 * @param suffix
	 * @return
	 */
	public static Set<File> getAllFile(File file,Set<String> suffixs){
		Set<File> allFile=new HashSet<File>();
		if(!file.exists()){
			System.out.println("文件不存在");
			return allFile;
		}
		//不是文件夹时
		if(!file.isDirectory()){
			allFile.add(file);
			return allFile;
		}
		File[] files=file.listFiles();
		for (File f : files) {
			if(f.isDirectory()){
				allFile.addAll(getAllFile(f,suffixs));
			}else if(suffixs.size()==0){
				allFile.add(f);
			}else if(suffixs.contains(getSuffix(f))){
				allFile.add(f);
			}
		}
		return allFile;
	}
	/**
	 * 获取文件后缀
	 * @return
	 */
	public static String getSuffix(File f) {
		String fileName = f.getName();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		return suffix;

	}
	/**
	 * 获取以startNames开头的文件
	 * 
	 * @param file
	 *            根文件
	 * @param filterType
	 *            过滤类型
	 * @param startNames
	 *            开头字符串匹配
	 * @return
	 */
	public static Set<File> getAllFileEqStart(File file,String [] filterType,String [] startNames){
		return getAllFile(file, null,filterType, startNames,null);
	}
	/**
	 * 获取以startNames结尾的文件
	 * @param file 根文件
	 * @param filterType 过滤类型
	 * @param endNames 结尾字符串匹配
	 * @return
	 */
	public static Set<File> getAllFileEqEnd(File file,String [] excludeNames,String [] filterType,String [] endNames){
		return getAllFile(file,excludeNames, filterType, null,endNames);
	}
	/**
	 * 获取startNames开头或endNames结尾的文件
	 * @param file 根文件
	 * @param excludeNames 排除的文件夹名
	 * @param filterType 过滤类型
	 * @param startNames 口头字符串匹配
	 * @param endNames 结尾字符串匹配
	 * @return
	 */
	public static Set<File> getAllFile(File file,final String [] excludeNames,final String [] filterType,String [] startNames,String [] endNames){
		java.io.FileFilter filter=new java.io.FileFilter(){
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				String fileName=f.getAbsolutePath();
				if(f.isDirectory()){//如果是文件夹
					if(EmptyUtils.isEmpty(excludeNames)){
						return true;
					}
					for (String excludeName : excludeNames) {
						if(fileName.endsWith(excludeName)){
							return false;
						}
					}
					return true;
				}
				if(null==filterType) return true;//如果为空表示都返回
				for (String type : filterType) {
					if (f.getAbsolutePath().endsWith(type)) {//后缀符合时返回true
						return true;
					}
				}
				return false;
			}
			
		};
		return getAllFile(file, filter,startNames,endNames);
	}
	/**
	 * 返回拷贝文件
	 * @param srcFile
	 * @return
	 */
	public static File copy(String srcPath,String destPath){
		File srcFile=new File(srcPath);
		File destFile=new File(destPath);
		try {
			FileUtils.copyFile(srcFile,destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destFile;
	}
	/**
	 * 备份文件到bak文件夹
	 * @param path
	 * @return
	 */
	public static boolean backFile(String path){
		Date now=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd-HHmmss");
		String fileName=getFileName(path);
		int index=fileName.lastIndexOf(".");
		String suffix="";
		if(-1!=index){
			suffix=fileName.substring(index+1);
			fileName=fileName.substring(0,index);
		}
		String destPath="C://bak"/*CommonUtil.getProjectPath()+"\\bak\\"+fileName+"_bak_"+sdf.format(now)+"."+suffix*/;
		File file=copy(path, destPath);
		try {
			return file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 将行生成到文件中
	 * @param path
	 * @return
	 */
	public static void writeLines(String path,Collection<?> lines){
		writeLines(path, "UTF-8", lines);
	}
	/**
	 * 将行生成到文件中
	 * @param path
	 * @return
	 */
	public static void writeStringToFile(String path,String data,String encoding){
		File file=new File(path);
		try {
			FileUtils.writeStringToFile(file, data, encoding);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 将行生成到文件中
	 * @param path
	 * @return
	 */
	public static void writeStringToFile(String path,String data){
		writeStringToFile(path, data,"UTF-8");
	}
	/**
	 * 将行生成到文件中
	 * @param path
	 * @return
	 */
	public static void writeLines(String path,String encoding,Collection<?> lines){
		File file=new File(path);
		try {
			FileUtils.writeLines(file, encoding,lines);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		/*File file=new File("E:\\WorkSpace\\SNS\\SSL_DEV\\flex_src\\com\\sinotrans\\ssl\\system\\view\\CmmUserList.mxml");
		Set<File> allFiles=getAllFile(file,"mxml");
		for (File f : allFiles) {
			System.out.println(f.getAbsolutePath());
		}*/
		//backFile("E:\\WorkSpace\\SNS\\piaohai_3.4\\src\\com\\piaohai\\common\\util\\ConfigUtil.java");
		String s = readFile("fruit.properties");
		System.out.println(s);
	}
}
