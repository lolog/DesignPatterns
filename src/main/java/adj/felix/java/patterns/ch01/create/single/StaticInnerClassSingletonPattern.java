package adj.felix.java.patterns.ch01.create.single;

/**
 * ~~~~ <b>静态内部类单例模式</b> ~~~~ <br>
 * 缺点：浪费一定的空间
 * @author adolf.felix
 */
public class StaticInnerClassSingletonPattern {
	/**
	 * 类级的内部类, 也就是静态的成员式内部类, 该内部类的实例与外部类的实例没有绑定关系,<br>
	 * 而且只有被调用到才会装载, 从而实现了延迟加载。
	 */
	private static class EagerAndLazyHolder {
		/** 静态初始化器, 由JVM来保证线程的安全**/
		private static StaticInnerClassSingletonPattern INSTANCE = new StaticInnerClassSingletonPattern();
	}
	
	private StaticInnerClassSingletonPattern() {
		// TODO Auto-generated constructor stub
	}
	
	public static StaticInnerClassSingletonPattern getInstance() {
		return EagerAndLazyHolder.INSTANCE;
	}
}
