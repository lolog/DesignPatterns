package adj.felix.java.patterns.ch01.create;

import org.junit.Test;

/**
 * <pre>
 * <b>在工厂模式</b>
 * 在工厂模式中,创建对象时不会对客户端暴露创建逻辑,并且是通过使用一个共同的接口来指向新创建的对象。
 * 01.优点： 
 *    01) 一个调用者想创建一个对象,只要知道其名称就可以了。 
 *    02) 扩展性高,如果想增加一个产品,只要扩展一个工厂类就可以。 
 *    03) 屏蔽产品的具体实现,调用者只关心产品的接口。
 * 02.缺点：
 *    每增加一个产品,都需要增加一个具体类和对象实现工厂,使得系统中类的个数成倍增加,在一定程度上增加了系统的复杂度,
 *    同时也增加了系统具体类的依赖。这并不是什么好事。
 * </pre>
 * @author adolf.felix
 */
public class FactoryPattern {
	interface Shape {
		void draw();
	}

	class Rectangle implements Shape {
		@Override
		public void draw() {
			System.out.println("Rectangle::draw()");
		}
	}

	class Square implements Shape {
		@Override
		public void draw() {
			System.out.println("Square::draw()");
		}
	}

	class Circle implements Shape {
		@Override
		public void draw() {
			System.out.println("Circle::draw()");
		}
	}

	class ShapeFactory {
		public Shape getShape(String shapeType) {
			if (shapeType == null) {
				return null;
			}
			if (shapeType.equalsIgnoreCase("CIRCLE")) {
				return new Circle();
			} else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
				return new Rectangle();
			} else if (shapeType.equalsIgnoreCase("SQUARE")) {
				return new Square();
			}
			return null;
		}
	}
	
	@Test
	public void facotryTest() {
		ShapeFactory shapeFactory = new ShapeFactory();

		Shape shape1 = shapeFactory.getShape("CIRCLE");
		shape1.draw();

		Shape shape2 = shapeFactory.getShape("RECTANGLE");
		shape2.draw();

		Shape shape3 = shapeFactory.getShape("SQUARE");
		shape3.draw();
	}
}
