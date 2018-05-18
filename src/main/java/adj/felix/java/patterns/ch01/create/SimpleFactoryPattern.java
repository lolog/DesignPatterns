package adj.felix.java.patterns.ch01.create;


/**
 * <pre>
 * ~~~~ <b>简单工厂模式</b> ~~~~
 * 实现算法和界面的分离,也就是将业务逻辑和界面逻辑分开,降低耦合度。
 * 01. 算法封装: 定义一个抽象的算法接口,提供不同算法的公共接口方法。其他具体算法继承这个抽象类,并实现具体的算法。
 * 02. 优点：
 *        (1) 调用者根据名称就可以创建对象。
 *        (2) 扩展性高,如果需要新增一个产品,只需扩展到工厂类就可以。
 *        (3) 屏蔽产品的具体实现,调用者只关心产品的接口。
 * 03. 缺点：新增一个产品,需要增加一个具体产品实现类,和修改工厂实现,从而使得系统中类的个数成倍增加,在一定程度上增加了系统的复杂度,同时也增加了系统具体类的依赖。
 * 04. 注意：简单工厂模式只有一个工厂,而工厂模式每类产品对应一个工厂。
 * </pre>
 * @author adolf felix
 */
public class SimpleFactoryPattern {
	/** 抽象的算法接口 **/
	interface Shape {
		/** 不同算法的公共接口方法 **/
		public void describe();
	}
	
	/** 抽象算法的公共接口 **/
	static abstract class AbstractShape implements Shape {
		private int edge;
		
		public AbstractShape(int edge) {
			this.edge = edge;
		}
		
		/** 公共接口的算法方法**/
		public int degree() {
			if (edge < 3) {
				throw new RuntimeException("not available edge");
			}
			if (edge == 3) {
				return 180;
			}
			return 360;
		}
	}
	/** 矩形 **/
	static class Rectangle extends AbstractShape {
		public Rectangle() {
			super(4);
		}
		
		@Override
		public void describe() {
			System.out.println("Rectangle degree = " + degree());
		}
	}
	
	/** 笔的抽象算法接口 **/
	interface Pen {
		public void material();
		public void describe();
	}
	static class Pencil implements Pen {
		@Override
		public void material() {
			System.out.println("Wood, Core");
		}
		@Override
		public void describe() {
			System.out.println("Pencil");
		}
	}
	
	/** 
	 * 注意：简单工厂模式只有一个工厂,而工厂模式每个产品对应一个工厂
	 **/
	static class SimpleFactory {
		public Object getShape (String shape) {
			if (shape.equals("Rect")) {
				return new Rectangle();
			}
			else if (shape.equals("Pencil")) {
				return new Pencil();
			}
			return null;
		}
	}
	
	public static void main(String[] args) {
		SimpleFactory factory = new SimpleFactory();
		Shape shape = (Shape) factory.getShape("Rect");
		shape.describe();
		
		Pen pen = (Pen) factory.getShape("Pencil");
		pen.material();
		pen.describe();
	}
}
