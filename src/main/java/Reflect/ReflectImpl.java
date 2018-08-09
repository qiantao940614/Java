package Reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.internal.runners.TestMethod;

public class ReflectImpl {
	private String testAttr="test value";
	
	String testAttr2="test Value2";
	
	protected String testAttr3="test Value3";
	
	public String testAttr4="test value4";
	
	public void TestMethod() {
		System.out.println("Test Method Running");
	}
	
	public String TestMethod(int i) {
		System.out.println("Test Method Running 2:"+i);
		return "success";
	}
	
	public ReflectImpl() {
		//System.out.println("Test Constructor 1");
	}
	
	/*public ReflectImpl(String testAttr) {
		this.testAttr=testAttr;
		System.out.println("Test Constructor 2:"+testAttr);
	}*/
	
	/**
	 *测试类的名字  父类等 
	 *
	 */
	@Test
	public void testClass() throws ClassNotFoundException{
		//Class c=new ReflectImpl().getClass();
		Class c= Class.forName("Reflect.ReflectImpl");
		System.out.println("Class Name:\t"+c.getName());
		System.out.println("Super Class:\t"+c.getSuperclass());

		

	}
	
	/**
	  * 通过反射，调用类中的方法
	  * @throws ClassNotFoundException,NoSuchMethodException，
	  * 		SecurityException,IllegalAccessException,
	  * 		IllegalArgumentException,InvocationTargetException,
	  * 		InstantiationException
	  *
	  */
	@Test
	public void testClassMethod() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Class class1= Class.forName("Reflect.ReflectImpl");
		//for(Method m:class1.getDeclaredMethods()){
		//	System.out.println("----------------");
		//	System.out.println("input value Type"+m.getParameterCount());
		//}
		Object o=class1.newInstance();//得到对象
		System.out.println("------无参无返回值方法测试");
		Method  method=class1.getMethod("TestMethod");
		Object object=method.invoke(o);//调用无参对象
		System.out.println("\t返回值:"+object);
		
		
		System.out.println();				
		System.out.println("------有参有返回值方法测试  ");
		Method  method2=class1.getMethod("TestMethod",int.class);//此处不能用封装类型
		Object object2=method2.invoke(o,1);//调用有参对象
		System.out.println("\t返回值:"+object2);
	}
	
	
	/**
	  * 通过反射，调用类中的构造方法
	  * @throws  ClassNotFoundException, InstantiationException,
	  * 		 IllegalAccessException, NoSuchMethodException, 
	  * 		SecurityException, IllegalArgumentException, 
	  * 		InvocationTargetException 
	  */
	@Test
	public void testConstructor() throws Exception{
		Class class1= Class.forName("Reflect.ReflectImpl");
		//Constructor[] con=class1.getConstructors()//所有共有构造方法
		//Constructor[] con=class1.getDeclaredConstructors();//所有构造方法
		System.out.println();				
		System.out.println("------无参构造函数测试 ");
		Constructor c1 = class1.getDeclaredConstructor();
		class1.newInstance();
		System.out.println();				
		System.out.println("------有参构造函数测试 ");
		System.out.println("Junit4不允许测试类具有有参构造方法 ");
		//Constructor c2 = class1.getDeclaredConstructor(String.class);
		//c2.newInstance("Class");
	}
	
	/**
	 * 通过反射，调用类中的属性
	 * @throws ClassNotFoundException, NoSuchFieldException, 
	 * 			SecurityException, IllegalArgumentException, 
	 * 			IllegalAccessException, InstantiationException
	 */
	@Test
	public void testAttribute() throws Exception{
		Class class1= Class.forName("Reflect.ReflectImpl");
		Object o=class1.newInstance();//得到对象
		 Field[] fields = class1.getDeclaredFields();//所有属性
		 //Field[] fields = class1.getFields();//所有公有属性
		 System.out.println();
		 System.out.println("-----测试所有属性，并得到其值");
		 System.out.println("权  限 :    类型:  属  性  名   值");
		 for(Field field:fields){
			 System.out.println(Modifier.toString(field.getModifiers())+"  "+field.getType().getSimpleName()+"  "+field.getName()+"="+field.get(o));
		 }
		 System.out.println("\r---测试指定属性，并修改其值");
		 Field field=class1.getField("testAttr");
		 field.setAccessible(true);// 设置操作权限为true
		 field.set(o, "hahah");
		 System.out.println("----测试结果是否成功    没测试");
		
	}
	
}
