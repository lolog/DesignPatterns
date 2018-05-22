package adj.felix.java.patterns.ch01.create.single;

/**
 * 饿汉式单例模式<br>
 * 类被加载的时候, 创建一个唯一实例。
 * 
 * @author adolf.felix
 */
public class EagerSingletonPattern {
	/** 单例对象仅为static修饰的实例变量 **/
	private static EagerSingletonPattern INSTANCE = new EagerSingletonPattern();

	/** 谨记： 构造方法私有化 **/
	private EagerSingletonPattern() {
		// TODO Auto-generated constructor stub
	}

	/** 获取实例 **/
	public static EagerSingletonPattern getInstance() {
		return INSTANCE;
	}
}
