package adj.felix.java.patterns.ch01.create;


/**
 * <pre>
 * ~~~~ <b>工厂模式</b> ~~~~
 * 在工厂模式中,创建对象时不会对客户端暴露创建逻辑,并且是通过使用一个共同的接口来指向新创建的对象。
 * 01.优点： 
 *    01) 一个调用者想创建一个对象,只要知道其名称就可以了。 
 *    02) 扩展性高,如果想增加一个产品,只要扩展一个工厂类就可以。 
 *    03) 屏蔽产品的具体实现,调用者只关心产品的接口。
 * 02.缺点：
 *    每增加一个产品,都需要增加一个具体类和对象实现工厂,使得系统中类的个数成倍增加,在一定程度上增加了系统的复杂度,同时也增加了系统具体类的依赖。
 * 03.注意：简单工厂模式只有一个工厂,而工厂模式每类产品对应一个工厂
 * </pre>
 * @author adolf.felix
 */
public class FactoryPattern {
	/** 形状的抽象算法接口 **/
	interface Shape {
		/** 不同算法的公共接口方法 **/
		public void describe();
	}
	/** 矩形 **/
	static class Rectangle implements Shape {
		@Override
		public void describe() {
			System.out.println("Rectangle ");
		}
	}
	/** 正方形 **/
	static class Square  implements Shape {
		@Override
		public void describe() {
			System.out.println("Square");
		}
	}
	/** 
	 * 形状工厂 <br>
	 * 注意：简单工厂模式只有一个工厂,而工厂模式每个产品对应一个工厂
	 **/
	static class ShapeFactory {
		public Shape getShape(String shapeType) {
			if (shapeType.equalsIgnoreCase("Rectangle")) {
				return new Rectangle();
			} else if (shapeType.equalsIgnoreCase("Square")) {
				return new Square();
			}
			return null;
		}
	}
	
	/** 笔的抽象算法接口 **/
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
	 * 笔的工厂 <br>
	 * 注意：简单工厂模式只有一个工厂,而工厂模式每个产品对应一个工厂
	 **/
	static class PenFactory {
		public Pen getShape(String penType) {
			if (penType.equalsIgnoreCase("Pencil")) {
				return new Pencil();
			} else if (penType.equalsIgnoreCase("Carbon")) {
				return new Carbon();
			}
			return null;
		}
	}
	
	public static void main(String[] args) {
		ShapeFactory shapeFactory = new ShapeFactory();
		Shape shape1 = shapeFactory.getShape("Rectangle");
		shape1.describe();
		Shape shape2 = shapeFactory.getShape("Square");
		shape2.describe();
		
		PenFactory penFactory = new PenFactory();
		Pen pen1 = penFactory.getShape("Pencil");
		pen1.describe();
		Pen pen2 = penFactory.getShape("Carbon");
		pen2.describe();
	}
}
