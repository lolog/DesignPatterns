package adj.felix.java.patterns.ch02.structure.proxy;

/**
 * <pre>
 * ~~~~ <b>代理模式之静态代理 </b> ~~~~
 * 01. 结构
 *     (01) Subject(抽象角色)
 *          声明真实对象和代理对象的共同接口。抽象类或者接口。
 *     (02) Proxy(代理角色)
 *          代理角色内部含有对真实角色的引用, 从而可以操作真实对象, 同时代理对象提供与真实对象的接口, 以便任何时候都能代替真实对象。
 *     (03) RealSubject(真实角色)
 *          代理角色所代理的对象, 亦最终要引用的对象。
 * 02. 实现
 *     代理角色和真实角色都要实现同一个操作行为的接口(或继承同一个抽象类), 并且代理角色依赖于真实角色, 因为代理角色内部有对真实角色的引用, 代理在操作真实角色去执行动作时，必须要知道是哪个真实角色。
 * 
 * 03. 优缺点
 *     (01) 业务类值关注业务逻辑, 保证了业务类的重用性。
 *     (02) 一个代理类只服务于一个真实角色, 如果真实角色过多, 那么相应的代理类也过多, 静态代理就无法胜任了。
 *     (03) 如果增加抽象角色方法, 那么所有真实角色和代理角色都要做相应的修改, 不满足"开不原则", 增加了代码维护的复杂度。
 * </pre>
 * @author adolf felix
 */
public class StaticProxy {
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
	static class CarProxy implements Moveable {
		private Moveable moveable;
		
		public CarProxy(Moveable moveable) {
			this.moveable = moveable;
		}
		
		@Override
		public void move(String dist) {
			System.out.println("Starting Monitor...");
			this.moveable.move(dist);
			System.out.println("Ending Monitor...");
		}
	}
	
	public static void main(String[] args) {
		Moveable car = new Car();
		CarProxy proxy = new CarProxy(car);
		proxy.move("School");
	}
}