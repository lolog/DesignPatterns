package adj.felix.java.patterns.ch02.structure.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <pre>
 * ~~~~ <b>代理模式之Jdk动态代理 </b> ~~~~
 * 用反射机制动态的生成代理的对象。
 * 01. 结构
 *     (01) Subject(抽象角色)
 *          声明真实对象和代理对象的共同接口。一定是接口。
 *     (02) Proxy(代理角色)
 *          代理角色内部含有对真实角色的引用, 从而可以操作真实对象, 同时代理对象提供与真实对象的接口, 以便任何时候都能代替真实对象。
 *     (03) RealSubject(真实角色)
 *          代理角色所代理的对象, 亦最终要引用的对象。
 *          
 * 02. 实现步骤
 *     (01) 创建被代理的类以及接口。
 *     (02) 创建一个实现接口InvocationHandler的类, 实现invoke方法。
 *     (03) 调用Proxy的newProxyInstance(ClassLoader loader,Class[] interfaces,InvocationHandler h)静态方法, 创建一个代理类。
 * 
 * 03. 缺点
 *     (01) Subject必须是接口。
 *     (02) RealSubject必须实现Subject接口。
 *     
 * 04. 优点
 *     (01) Proxy不需要实现Subject的接口。
 *     (02) 利用jdk的api生成代理对象, 在内存中动态构建代理对象。
 *     (03) 代理角色不需要抽象角色接口, 但是真实角色必须实现接口, 否则不能用动态代理。
 * </pre>
 * @author adolf felix
 */
public class DynamicJdkProxy {
	/** Subject **/
	interface Moveable {
		public void move(String dist);
	}
	
	/** RealSubject **/
	static class Car implements Moveable {
		@Override
		public void move(String dist) {
			System.out.println("Car moving to " + dist);
		}
	}
	/** Proxy **/
	static class CarJdkProxy implements InvocationHandler {
		private Moveable moveable;
		
		public CarJdkProxy(Moveable moveable) {
			this.moveable = moveable;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("Starting Monitor...");
			method.invoke(moveable, args);
			System.out.println("Ending Monitor...");
			return null;
		}
	}
	
	public static void main(String[] args) {
		Car car = new Car();
		CarJdkProxy proxy = new CarJdkProxy(car);
		
		Moveable moveable = (Moveable) Proxy.newProxyInstance(DynamicJdkProxy.class.getClassLoader(), new Class[] {Moveable.class}, proxy);
		moveable.move("School");
	}
}