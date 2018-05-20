package adj.felix.java.patterns.ch01.create;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <pre>
 * ~~~~ <b>简单工厂模式</b> ~~~~
 * 实现算法和界面的分离,也就是将业务逻辑和界面逻辑分开,降低耦合度。
 * 01. 组成: 产品抽象、产品实现、工厂类
 * 02. 功能： 创建接口、抽象类或者具体类的实例。
 * 03. 工厂
 *     1) 实例工厂： 创建工厂类的实例。
 *     2) 静态工厂： 使用简单工厂的时候,通常不用创建工厂类的实例,而是使用静态工厂实现。
 *     3) 万能工厂： 简单工厂模式,理论上可以构建任何对象的实例,即"大杂烩"的工厂类。
 * 04. 工厂创建对象的范围
 *     1) 理论上, 简单工厂可以创建任何对象的实例。
 *     2) 实际上, 创建范围不要太大, 建议将其控制在一个独立的组件或者模块级别。
 * 05. 命名规范
 *     1) 工厂类名：模块名称 + Factory。
 *     2) 方法名称：get+接口名称, create+接口名称, new+接口名称。
 * 06. 抽象类和接口的选择
 *     1) 优先选用接口类。
 *     2) 抽象类的选用条件：即要定义子类的行为, 又要为子类提供公共的功能。
 * 07.工厂类的实现
 *     1) switch
 *     2) if/else
 *     2) 通过配置文件+反射, 或者IoC/DI实现
 * 08.优缺点
 *     1) 实现了组件的封装。
 *     2) 实现客户端和具体实现类的解耦。
 *     3) 增加客户端的复杂度(通过客户端参数选择实现类,客户端还需要理解参数的功能和含义)。
 *     4) 不方便子类的拓展。
 * 09. 简单工厂的本质：选择实现类。
 * 10. 简单工厂模式的选用条件
 *     1) 完全封装隔离具体实现。
 *     2) 创建对象集中管理和控制, 简单工厂可以创建很多且不无关的对象。
 * </pre>
 * @author adolf.felix
 */
public class SimpleFactoryPattern {
	interface Api {
		public void describe();
	}

	public static class ApiImplA implements Api {
		@Override
		public void describe() {
			System.out.println("Impl[A]");
		}
	}
	static class ApiImplB implements Api {
		@Override
		public void describe() {
			System.out.println("Impl[B]");
		}
	}
	
	/**
	 * 注意：简单工厂模式只有一个工厂类,而工厂方法模式每个产品对应一个工厂
	 **/
	static class ApiFactory {
		public static Api getApi(String type) {
			if (type.equals("A")) {
				return new ApiImplA();
			} else if (type.equals("B")) {
				return new ApiImplB();
			}
			return null;
		}
	}
	
	static class ApiConfigFactory {
		static Properties properties = new Properties();
		static {
			try {
				// 01. 加载类同一包下的资源
				// ApiConfigFactory.class.getResourceAsStream("SimpleFactoryPattern.properties")
				
				// 02. 加载classpath路径下的资源
				InputStream in = ApiConfigFactory.class.getClassLoader().getResourceAsStream("ch01/SimpleFactoryPattern.properties");
				properties.load(in);
				in.close();
			} catch (IOException e) {
				throw new RuntimeException("not found config");
			}
		}
		
		public static Api getApi() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
			String implClass = properties.getProperty("apiImplClass");
			// 内部类配置： 包名.外部类$内部类
			Class<?> clazz = SimpleFactoryPattern.class.getClassLoader().loadClass(implClass);
			Api api = (Api) clazz.newInstance();
			return api;
		}
	}

	public static void main(String[] args) throws Exception{
		Api a = ApiFactory.getApi("A");
		a.describe();
		
		Api b = ApiFactory.getApi("B");
		b.describe();
		
		Api configApi = ApiConfigFactory.getApi();
		configApi.describe();
	}
}
