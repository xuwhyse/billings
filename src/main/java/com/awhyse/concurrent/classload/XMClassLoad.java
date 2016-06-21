package com.awhyse.concurrent.classload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/*
 * vm加载一个类大体分为三个步骤：
1）加载阶段：就是在硬盘上寻找java文件对应的class文件，并将class文件中的二进制数据加载到内存中，将其放在运行期数据区的方法区中去，
然后在堆区创建一个java.lang.Class对象，用来封装在方法区内的数据结构；
2）连接阶段：这个阶段分为三个步骤，步骤一：验证，当然是验证这个class文件里面的二进制数据是否符合java规范；步骤二：
准备，为该类的静态变量分配内存空间，并将变量赋一个默认值，比如int的默认值为0；步骤三：
解析，这个阶段就不好解释了，将符号引用转化为直接引用，涉及到指针；
3）初始化阶段：当我们主动调用该类的时候，将该类的变量赋于正确的值(这里不要和第二阶段的准备混淆了)，
举个例子说明下两个区别，比如一个类里有private static int i = 5; 这个静态变量在"准备"阶段会被分配一个内存空间并且被赋予一个默认值0，
当道到初始化阶段的时候会将这个变量赋予正确的值即5，了解了吧！
 */
public class XMClassLoad extends ClassLoader{

	//该方法要判断是否被加载，必须是保证同步块
	protected synchronized Class<?> loadClass(String name, boolean resolve)throws ClassNotFoundException{
		Class<?> klass = null;
		try {
			klass = super.findLoadedClass(name);//检查该类是否已经被父类装载。
			if(klass!=null)
				return klass;
		
			 byte[] bs = getClassBytes(name);//从一个特定的信息源寻找并读取该类的字节。
			if (bs != null && bs.length > 0) {
				//注册的时候就递归:  防止同一个类被不同的子加载器加载
	            klass = super.defineClass(name, bs, 0, bs.length);//注册类,注意这边有递归的找类注测loadClass  !!!!!!!!!!!
	        }
	        if (klass == null) { //如果读取字节失败，则试图从JDK的系统API中寻找该类。
	            klass = findSystemClass(name);
	        }
	        if (resolve && klass != null) {
	            resolveClass(klass);   
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return klass;
	 }
	/**
	 *自定义加载文件字节方法，根据文件名字 
	 * @param name
	 * @return
	 * @throws IOException 
	 */
	private byte[] getClassBytes(String className) throws IOException {
		String path = this.getClass().getResource("/").getPath() ;
        path += className.replace('.', File.separatorChar) + ".class";
        System.out.println(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return null;   //如果查找失败，则放弃查找。捕捉这个异常主要是为了过滤JDK的系统API。
        }
        byte[] bs = new byte[fis.available()];
        fis.read(bs);
        return bs;
	}
	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		XMClassLoad loader = new XMClassLoad();
		Class c = loader.loadClass("com.awhyse.concurrent.classload.Aaa", false);
		System.out.println(c);
        Object o = c.newInstance();
        System.out.println(o);
        Method m = c.getMethod("print", java.lang.String.class);
        m.invoke(o, "bitan");
	}

	public XMClassLoad() {
//		super.loadClass(name, resolve)
	}

}
