package adj.felix.java.patterns.ch01.create.single;

/**
 * <pre>
 * ~~~~ <b>单例与枚举</b> ~~~~
 * 单元素的枚举类型是实现Singleton的最佳方法。
 * Java枚举的基本思想是通过公有的静态final域为每个枚举常量导出实例的类。 即, 从某种角度来说, 枚举是单例的泛型化, 本质上是单元素的枚举。
 * 优点：使用枚举类实现单例模式, 代码会更加简洁, 并且提供了序列化的机制, 并由JVM从根本上提供保障, 绝对防止多次实例化, 是简洁, 高效, 安全的单例方式。
 * </pre>
 * @author adolf.felix
 */
public enum EnumSingleton {
	/** 枚举元素, 代表一个singleton实例 **/
	INSTANCE;
	
	/** 枚举类, 也有自己的属性和方法 **/
	public void describe () {
		// 
	}
}
