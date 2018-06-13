package adj.felix.java.patterns.ch02.structure.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * <pre>
 * ~~~~ <b>代理模式之Cglib动态代理 </b> ~~~~
 * 亦子类代理, 在内存中构建一个子类对象从而实现对目标对象功能的扩展。
 * 01. 结构
 *     (01) Proxy(代理角色)
 *          代理角色内部含有对真实角色的引用, 从而可以操作真实对象, 同时代理对象提供与真实对象的接口, 以便任何时候都能代替真实对象。
 *     (02) RealSubject(真实角色)
 *          代理角色所代理的对象, 亦最终要引用的对象。
 * 
 * 02. 缺点
 *     (01) 代理类不能为final类,否则报错。
 *     (02) 由于CGLib是采用动态创建子类的方法, 对于final/static修饰的方法无法进行代理。
 *     
 * 03. 优点
 *     (01) JDK的动态代理必须实现一个或多个接口, 而Cglib可以代理没有实现接口的类。
 *     (02) Cglib是一个强大的高性能的代码生成包, 可以在运行期扩展java类与实现java接口。
 *     (03) 从执行效率上看, Cglib动态代理效率比JDK高。
 * </pre>
 * @author adolf felix
 */
public class DynamicCglibProxy {
	/** RealSubject **/
	static class Car {
		public void move(String dist) {
			System.out.println("Car moving to " + dist);
		}
	}
	/** Proxy **/
	static class CarJdkProxy implements MethodInterceptor {
		private Object target;
		
		public CarJdkProxy(Object target) {
			this.target = target;
		}
		
		 /**
		  * 给目标对象创建一个代理对象
		  * @return 代理对象
		  */
	    public Object getProxyInstance(){
	        //1.工具类
	        Enhancer en = new Enhancer();
	        //2.设置父类
	        en.setSuperclass(target.getClass());
	        //3.设置回调函数
	        en.setCallback(this);
	        //4.创建子类(代理对象)
	        return en.create();

	    }
		
	    /**
	     * @param object 为cglib动态生成的代理实例
	     * @param method 实体类被代理的方法
	     * @param args 为method参数数值列表
	     * @param proxy 为生成代理类对应方法的代理引用
	     */
		@Override
		public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
			System.out.println("Starting Monitor...");
			Object returnValue = method.invoke(target, args);
			System.out.println("Ending Monitor...");
			
			return returnValue;
		}
	}
	
	public static void main(String[] args) {
		Car car = new Car();
		Car proxy = (Car) new CarJdkProxy(car).getProxyInstance();
		
		System.out.println("Proxy Class = " + proxy);
		proxy.move("School");
	}
}