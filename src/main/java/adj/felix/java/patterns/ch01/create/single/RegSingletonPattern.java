package adj.felix.java.patterns.ch01.create.single;

import java.util.HashMap;
import java.util.Map;

/**
 * 登机式单例模式<br>
 * 维护着一组单例类实例, 将这些实例存放在一个Map中, 对于已经登记过的实例, 直接从工厂返回; 没有登记过的, 先登记后返回。
 * @author adolf.felix
 */
public class RegSingletonPattern {
	/** 单例对象仅为static修饰的实例变量, 线程安全 **/
	static class RegStaticSingletonPattern {
		private static Map<String, RegStaticSingletonPattern> INSTANCES = new HashMap<String, RegStaticSingletonPattern>();
		
		static {
			// 加载类的时候, 默认登记一个实例
			RegStaticSingletonPattern instance = new RegStaticSingletonPattern();
			INSTANCES.put(instance.getClass().getName(), instance);
		}
		
		/** 谨记： 构造方法私有化 **/
		private RegStaticSingletonPattern() {
			// TODO Auto-generated constructor stub
		}
		
		public static RegStaticSingletonPattern getInstance(String name) {
			// 为null, 获取默认登记的实例
			if (name == null) {
				name = RegStaticSingletonPattern.class.getName();
			}
			if (INSTANCES.get(name) == null) {
				RegStaticSingletonPattern instance = new RegStaticSingletonPattern();
				INSTANCES.put(name, instance);
			}
			return INSTANCES.get(name);
		}
	}
	
	/** 单例对象仅为static修饰的实例变量, 线程安全 **/
	static class RegStaticSecuritySingletonPattern {
		private static Map<String, RegStaticSecuritySingletonPattern> INSTANCES = new HashMap<String, RegStaticSecuritySingletonPattern>();
		
		static {
			// 加载类的时候, 默认登记一个实例
			RegStaticSecuritySingletonPattern instance = new RegStaticSecuritySingletonPattern();
			INSTANCES.put(instance.getClass().getName(), instance);
		}
		
		/** 谨记： 构造方法私有化 **/
		private RegStaticSecuritySingletonPattern() {
			// TODO Auto-generated constructor stub
		}
		
		public static RegStaticSecuritySingletonPattern getInstance(String name) {
			// 为null, 获取默认登记的实例
			if (name == null) {
				name = RegStaticSecuritySingletonPattern.class.getName();
			}
			// 第一次使用的时候登记
			if (INSTANCES.get(name) == null) {
				synchronized (name) {
					// 保证安全性
					if (INSTANCES.get(name) == null) {
						RegStaticSecuritySingletonPattern instance = new RegStaticSecuritySingletonPattern();
						INSTANCES.put(name, instance);
					}
				}
			}
			return INSTANCES.get(name);
		}
	}
	
	/** 单例对象仅为fianl和static修饰的实例变量, 线程不安全 **/
	static class RegFinalSingletonPattern {
		private static final Map<String, RegFinalSingletonPattern> INSTANCES = new HashMap<String, RegFinalSingletonPattern>();
		
		static {
			// 加载类的时候, 默认登记一个实例
			RegFinalSingletonPattern instance = new RegFinalSingletonPattern();
			INSTANCES.put(instance.getClass().getName(), instance);
		}
		
		/** 谨记： 构造方法私有化 **/
		private RegFinalSingletonPattern() {
			// TODO Auto-generated constructor stub
		}
		
		public static RegFinalSingletonPattern getInstance(String name) {
			// 为null, 获取默认登记的实例
			if (name == null) {
				name = RegFinalSingletonPattern.class.getName();
			}
			// 第一次使用的时候登记
			if (INSTANCES.get(name) == null) {
				RegFinalSingletonPattern instance = new RegFinalSingletonPattern();
				INSTANCES.put(name, instance);
			}
			return INSTANCES.get(name);
		}
	}
	
	/** 单例对象仅为fianl和static修饰的实例变量, 线程安全 **/
	static class RegFinalSecuritySingletonPattern {
		private static final Map<String, RegFinalSecuritySingletonPattern> INSTANCES = new HashMap<String, RegFinalSecuritySingletonPattern>();
		
		static {
			// 加载类的时候, 默认登记一个实例
			RegFinalSecuritySingletonPattern instance = new RegFinalSecuritySingletonPattern();
			INSTANCES.put(instance.getClass().getName(), instance);
		}
		
		/** 谨记： 构造方法私有化 **/
		private RegFinalSecuritySingletonPattern() {
			// TODO Auto-generated constructor stub
		}
		
		public static RegFinalSecuritySingletonPattern getInstance(String name) {
			// 为null, 获取默认登记的实例
			if (name == null) {
				name = RegStaticSecuritySingletonPattern.class.getName();
			}
			// 第一次使用的时候登记
			if (INSTANCES.get(name) == null) {
				synchronized (name) {
					// 保证安全性
					if (INSTANCES.get(name) == null) {
						RegFinalSecuritySingletonPattern instance = new RegFinalSecuritySingletonPattern();
						INSTANCES.put(name, instance);
					}
				}
			}
			return INSTANCES.get(name);
		}
	}
}
