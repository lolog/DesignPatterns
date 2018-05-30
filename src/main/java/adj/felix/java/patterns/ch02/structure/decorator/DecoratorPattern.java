package adj.felix.java.patterns.ch02.structure.decorator;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * ~~~~ <b>装饰模式</b> ~~~~
 * 装饰器模式, 能够在不改变原类文件和使用继承的情况下, 动态扩展一个对象的功能。 通过创建一个包装对象来实现, 也就是用装饰来包裹真实的对象。
 * 01. 特点
 *     (1) 装饰对象和真实对象具有相同的接口, 即, 装饰对象和真实对象以相同的方式实现交互。
 *     (2) 装饰对象包含一个真实对象的引用。
 *     (3) 装饰对象接受所有来自客户端的请求。
 *     (3) 装饰对象可以在转发请求前后附加一些功能。
 * 02. 装饰模式和类继承的区别
 *     (1) 装饰模式是动态行为, 对已经存在的类进行任意的组合。类的继承是静态行为, 一个类定义的功能无法动态改变。
 *     (2) 装饰模式扩展的是对象的功能, 无需增加类的数量。 而类的继承扩展的是类的功能, 如果需要增加对象的功能, 那么需要扩展子类对象。
 *     (3) 装饰模式是在不改变原类文件, 且使用继承的情况下, 动态扩展对象的功能。即, 通过创建包装对象来包裹真实对象。
 *     (4) 装饰模式将客户端的调用委派给装饰类
 *     (5) 装饰类的扩展是安全透明的。
 * 03. 结构 {@link DecoratorPattern.jpg}
 *     (1) Component(抽象构件)：抽象接口, 可以该类对象动态地添加附加职责。
 *     (2) ConcreteComponent(具体构件)：对象, 将为该对象添加一些附加职责。
 *     (3) Decorator(装饰者)：持有一个抽象构件的引用, 并定义一个与抽象构件一致的接口。
 *     (4) ConcreteDecorator(具体的装饰者)：负责给具体构件添加附加的职责。
 * 04. 优点
 *     (1) 比继承更灵活
 *         功能分离到装饰器, 通过组合的方式方式动态组合功能, 装饰器最终有哪些功能, 是由运行期间动态功能组合决定的。
 *     (2) 更容易复用功能
 *         装饰模式把一系列复杂的功能分散到各个装饰器中, 一般一个装饰器只实现一个功能, 因此实现装饰器变得简单, 并且功能可以复用。
 *     (3) 简化高层定义
 *         通过组合装饰器的方式, 给对象添加任意多的功能, 从而进行高层次定义的时候, 不用把所有的功能都定义出来。
 *     (4) 产生更细粒度对象
 *         装饰模式把一系列复杂的功能分散到各个装饰器中, 一般一个装饰器只实现一个功能, 因此装饰器对象更具有细粒度, 复合成为复杂的功能。
 * </pre>
 * @author adolf.felix
 */
public class DecoratorPattern {
	interface IDrinking {
		public double price();
		public Map<String, Object> describe();
	}
	
	static class Coffee implements IDrinking {
		private String name;
		private double price;
		
		public Coffee(String name, double price) {
			this.name = name;
			this.price = price;
		}
		
		@Override
		public double price() {
			return price;
		}
		
		@Override
		public Map<String, Object> describe () {
			Map<String, Object> describe = new HashMap<String, Object>();
			describe.put("name", name);
			describe.put("price", price);
			
			return describe;
		}
	}
	
	static abstract class DecoratorDrinking implements IDrinking {
		protected IDrinking drinking;
		
		public DecoratorDrinking(IDrinking drinking) {
			this.drinking = drinking;
		}
		
		@Override
		public double price() {
			return drinking.price();
		}
		
		@Override
		public Map<String, Object> describe() {
			return drinking.describe();
		}
	}
	
	static class SugarToDrinking extends DecoratorDrinking {
		public SugarToDrinking(IDrinking drinking) {
			super(drinking);
		}
		
		@Override
		public double price() {
			return 1 + super.price();
		}
		
		@Override
		public Map<String, Object> describe() {
			Map<String, Object> describe = super.describe();
			Object addition = describe.get("addition") == null ? "Sugger" : describe.get("addition") + " + Sugger";
			describe.put("addition", addition);
			
			return describe;
		}
	}
	
	static class MilkToDrinking extends DecoratorDrinking {
		public MilkToDrinking(IDrinking drinking) {
			super(drinking);
		}
		
		@Override
		public double price() {
			return 3 + super.price();
		}
		
		@Override
		public Map<String, Object> describe() {
			Map<String, Object> describe = super.describe();
			Object addition = describe.get("addition") == null ? "Milk" : describe.get("addition") + " + Milk";
			describe.put("addition", addition);
			
			return describe;
		}
	}
	
	public static void main(String[] args) {
		Coffee coffee = new Coffee("Latte", 20);
		
		DecoratorDrinking sugarCoffee = new SugarToDrinking(coffee);
		DecoratorDrinking milkCoffee = new MilkToDrinking(sugarCoffee);
		
		System.out.println(milkCoffee.describe());
		System.out.println("Total = " + milkCoffee.price());
		
	}
}
