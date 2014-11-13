package net.canwhn.reflect.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Properties;


interface fruit {
    public abstract void eat();
}


class Apple implements fruit {
    public void eat() {
        System.out.println("Apple");
    }
}


class Orange implements fruit {
    public void eat() {
        System.out.println("Orange");
    }
}


// 构造工厂类
// 也就是说以后如果我们在添加其他的实例的时候只需要修改工厂类就行了
class Factory1 {
    public static fruit getInstance(String fruitName) {
        fruit f = null;

        if ("Apple".equals(fruitName)) {
            f = new Apple();
        }

        if ("Orange".equals(fruitName)) {
            f = new Orange();
        }

        return f;
    }
}


class Factory2 {
    public static fruit getInstance(String ClassName) {
        fruit f = null;

        try {
            f = (fruit) Class.forName(ClassName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }
}


class init {
    public static Properties getPro() throws FileNotFoundException, IOException {
        Properties pro = new Properties();
        File f = new File("fruit.properties");

        if (f.exists()) {
            pro.load(new FileInputStream(f));
        } else {
            pro.setProperty("apple", "net.canwhn.reflect.factory.Apple");
            pro.setProperty("orange", "net.canwhn.reflect.factory.Orange");
            pro.store(new FileOutputStream(f), "FRUIT CLASS");
        }

        return pro;
    }
}


class Factory3 {
    public static fruit getInstance(String ClassName) {
        fruit f = null;

        try {
            f = (fruit) Class.forName(ClassName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }
}


class initPro{
	
	public static Properties getinitPros() throws Exception{
		Properties pro = new Properties();
		File f = new File("fruit1.properties");
		if(f.exists()){
			pro.load(new FileInputStream(f));
		}else{
			pro.setProperty("apple", "net.canwhn.reflect.factory.Apple");
			pro.setProperty("Orange", "net.canwhn.reflect.factory.Orange");
			pro.store(new FileOutputStream(f), "Add by Ariel");
		}
		return pro;
	}
	
}


class TestFactory {
    public static void main(String[] a) throws Exception {
        fruit f1 = Factory1.getInstance("Orange");
        f1.eat();
        
        fruit f2 = Factory2.getInstance("net.canwhn.reflect.factory.Apple");

        if (f2 != null) {
            f2.eat();
        }

        Properties pro = init.getPro();
        fruit f3 = Factory3.getInstance(pro.getProperty("orange"));

        if (f3 != null) {
            f3.eat();
        }
        
       
    }
}
