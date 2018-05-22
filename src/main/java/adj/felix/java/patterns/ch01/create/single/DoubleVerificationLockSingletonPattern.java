package adj.felix.java.patterns.ch01.create.single;


/**
 * <pre> 
 * ~~~ <b>双重校验锁</b> ~~~ 
 * 安全懒汉单实例, 保证只有唯一一个实例被创建 。
 * volatile修饰的变量值, 不会被本地线程缓存, 所有对该变量的读写都是直接操作共享内存, 从而确保多个线程能正确地处理该变量。
 * </pre>
 * @author adolf.felix
 */
public class DoubleVerificationLockSingletonPattern {
	/** 使用双重检查加锁, 需要使用volatile关键字 **/
	private volatile static DoubleVerificationLockSingletonPattern INSTANCE;
	
	/** 谨记： 构造方法私有化 **/
	private DoubleVerificationLockSingletonPattern() {
		// TODO Auto-generated constructor stub
	}
	
	/** 获取实例 **/
	public static DoubleVerificationLockSingletonPattern getInstance () {
		if (INSTANCE == null) {
			synchronized (DoubleVerificationLockSingletonPattern.class){
				if (INSTANCE == null) {
					INSTANCE = new DoubleVerificationLockSingletonPattern();
				}
			}
		}
		return INSTANCE;
	}
}
