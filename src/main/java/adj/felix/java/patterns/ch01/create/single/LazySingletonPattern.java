package adj.felix.java.patterns.ch01.create.single;

/**
 * 懒汉式单例模式<br>
 * 加载类的时候不创建实例, 只有当第一次请求的时候创建, 并且只在第一次创建后, 以后不再创建该类的实例。
 * @author adolf.felix
 */
public class LazySingletonPattern {
	/**
	 * <pre> 
	 * ~~~ <b>双重校验锁</b> ~~~ 
	 * 安全懒汉单实例, 保证只有唯一一个实例被创建 。
	 * volatile修饰的变量值, 不会被本地线程缓存, 所有对该变量的读写都是直接操作共享内存, 从而确保多个线程能正确地处理该变量。
	 * 
	 * </pre>
	 * @author adolf.felix
	 */
	static class LazySecuritySingletonPattern {
		/** 使用双重检查加锁, 需要使用volatile关键字 **/
		private volatile static LazySecuritySingletonPattern INSTANCE;
		
		/** 谨记： 构造方法私有化 **/
		private LazySecuritySingletonPattern() {
			// TODO Auto-generated constructor stub
		}
		
		/** 获取实例 **/
		public static LazySecuritySingletonPattern getInstance () {
			if (INSTANCE == null) {
				synchronized (LazySecuritySingletonPattern.class){
					if (INSTANCE == null) {
						INSTANCE = new LazySecuritySingletonPattern();
					}
				}
			}
			return INSTANCE;
		}
	}
	
	/** 不安全懒汉单实例, 缺点：并发情况可能会创建多个实例 **/
	static class LazyNotSecuritySingletonPattern {
		private static LazySecuritySingletonPattern INSTANCE;
		
		/** 谨记： 构造方法私有化 **/
		private LazyNotSecuritySingletonPattern() {
			// TODO Auto-generated constructor stub
		}
		
		/** 获取实例 **/
		public static LazySecuritySingletonPattern getInstance () {
			if (INSTANCE == null) {
				INSTANCE = new LazySecuritySingletonPattern();
			}
			return INSTANCE;
		}
	}
}
