package net.canwhn.reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.*; 
import java.util.Properties;


public class DumpMethods { 
   public static void main(String args[]) { 
      try { 
           Class c = Class.forName("java.util.Properties"); 
           Method m[] = c.getDeclaredMethods(); 
            
//           for (int i = 0; i < m.length; i++) 
//               System.out.println(m[i].toString()); 
//           
//           System.out.println(m[0].toString()); 
           
           File f = new File("fruit.properties");
           Method method = c.getMethod("load",InputStream.class);
           Properties pro = (Properties) method.invoke(c.newInstance(), new FileInputStream(f));
           System.out.println(pro.getProperty("apple"));
           //Method method = c.getMethod("split", String.class);
           //System.out.println(method.invoke(c.newInstance(), "@"));
           
           
      } 
      catch (Throwable e){ 
            System.err.println(e); 
      } 
   } 
}