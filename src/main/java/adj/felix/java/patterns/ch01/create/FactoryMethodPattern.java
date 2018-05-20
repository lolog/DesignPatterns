package adj.felix.java.patterns.ch01.create;

/**
 * <pre>
 * ~~~~ <b>工厂方法模式</b> ~~~~
 * 又名：虚拟构造器模式 / 多态性工厂模式。
 * 用意：工厂方法模式的用意,是定义一个创建产品对象的工厂接口,将具体创建的工作交给子类去做。
 * 组成：抽象工厂接口活抽象类、具体工厂类、产品抽象类、具体产品类。
 * 
 * 简单工厂模式 Vs 工厂方法模式
 * 1) 工厂方法模式是简单工厂模式的进一步抽象与推广。工厂模式是为了修复简单工厂模式不符合"OCP"规则而设计的。
 * 2) 结构复杂度
 *    工厂方法模式的工厂类比简单工厂模式多,因此增加了结构的复杂度。
 * 3) 代码复杂度
 *    简单工厂模式的工厂类随着产品类的增加需要增加很多方法, 而工厂方法模式每个具体工厂类只完成单一任务, 代码简洁。
 * </pre>
 * @author adolf.felix
 */
public class FactoryMethodPattern {
	interface Shape {
		public void describe();
	}
	static class Rectangle implements Shape {
		@Override
		public void describe() {
			System.out.println("Rectangle ");
		}
	}
	static class Square implements Shape {
		@Override
		public void describe() {
			System.out.println("Square");
		}
	}

	/**
	 * 注意：简单工厂模式只有一个工厂,而工厂模式每个产品对应一个工厂
	 **/
	static class ShapeFactory {
		public static Shape getShape(String shapeType) {
			if (shapeType.equalsIgnoreCase("Rectangle")) {
				return new Rectangle();
			} else if (shapeType.equalsIgnoreCase("Square")) {
				return new Square();
			}
			return null;
		}
	}

	interface Pen {
		public void describe();
	}
	static class Pencil implements Pen {
		@Override
		public void describe() {
			System.out.println("Pencil");
		}
	}
	static class Carbon implements Pen {
		@Override
		public void describe() {
			System.out.println("Carbon");
		}
	}
	/**
	 * 注意：简单工厂模式只有一个工厂,而工厂模式每个产品对应一个工厂
	 **/
	static class PenFactory {
		public static Pen getShape(String penType) {
			if (penType.equalsIgnoreCase("Pencil")) {
				return new Pencil();
			} else if (penType.equalsIgnoreCase("Carbon")) {
				return new Carbon();
			}
			return null;
		}
	}

	public static void main(String[] args) {
		Shape shape1 = ShapeFactory.getShape("Rectangle");
		shape1.describe();
		Shape shape2 = ShapeFactory.getShape("Square");
		shape2.describe();

		Pen pen1 = PenFactory.getShape("Pencil");
		pen1.describe();
		Pen pen2 = PenFactory.getShape("Carbon");
		pen2.describe();
	}
}
