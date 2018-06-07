package adj.felix.java.patterns.ch02.structure.flyweight;

import java.util.HashMap;

/**
 * <pre>
 * ~~~~ <b>享元模式</b> ~~~~
 * 又Flyweight模式, 采用共享避免拥有大量相同内容对象的开销, 最常见、最直观的就是内存损耗。享元模式以共享的方式高效支持大量的细粒度对象。
 * 01. 实现的关键
 *     (1) 外蕴状态
 *         会因为环境的变化而环境影响, 因此由客户端保存, 并在享元对象创建之后, 在需要使用的时候再传入到享元对象内部。外蕴状态不会影响享元内部的内蕴状态, 彼此是相互独立的。 
 *     (2) 内蕴状态
 *         存储在享元对象内部的对象, 不受环境的影响。
 * 02. 结构
 *     (1) Flyweight(抽象享元角色)
 *         为具体享元角色规定了实现的方法, 外蕴状态以参数的形式通过此方法传入。在Java中可以由抽象类、接口来担当。
 *     (2) ConcreteFlyweight(具体享元角色)
 *         实现Flyweight规定的方法。如果存在内蕴状态, 就负责为内蕴状态提供存储空间。
 *     (3) FlyweightFactory(享元工厂角色)
 *         负责创建和管理享元角色。
 *     (4) Client(客户端)
 *         维护对所有享元对象的引用, 而且还需要存储对应的外蕴状态。
 * 03. 优缺点
 *    (1) 使系统变得更复杂
 *        为了使对象可以共享, 需要将一些状态外部化, 增加程序逻辑的复杂性。
 *    (2) 将享元对象的状态外部化, 而读取外部状态使得运行时间稍微变长。 
 * 04. 使用场景
 *    (1) 应用程序使用了大量内容重复的对象, 并且增加了很大存储/链接开销。
 *    (2) 对象的大多数状态都可以变为外部状态。
 *    (3) 删除对象以外的状态, 可以相对较少的共享对象取代很多组对象。
 *    (4) 应用程序不依赖于对象的标志。
 * </pre>
 * 
 * @author adolf.felix
 */
public class FlyweightPattern {
	/** 抽象享元角色 **/
	interface Shape {
		public void draw();
	}

	/** 具体享元角色 **/
	static class Circle implements Shape {
		private String color;
		private int x;
		private int y;
		private int radius;

		public Circle(String color) {
			this.color = color;
		}

		public void setX(int x) {
			this.x = x;
		}

		public void setY(int y) {
			this.y = y;
		}

		public void setRadius(int radius) {
			this.radius = radius;
		}

		@Override
		public void draw() {
			System.out.println("Circle {color=" + color + ", xy=[" + x + ", " + y + "], radius=" + radius + "}");
		}
	}
	/** 享元工厂角色 **/
	static class ShapeFactory {
		private static final HashMap<String, Shape> circleMap = new HashMap<>();

		public static Shape create(String color) {
			Circle circle = (Circle) circleMap.get(color);

			if (circle == null) {
				circle = new Circle(color);
				circleMap.put(color, circle);
				System.out.println("Creating circle of color : " + color);
			}
			return circle;
		}
	}
	
	private static final String colors[] = { "Red", "Green", "Blue", "White", "Black" };
	
	public static void main(String[] args) {
		for (int i=0; i<colors.length; i++) {
			String color = colors[i];
			Circle circle = (Circle) ShapeFactory.create(color);
		    circle.setX(i);
		    circle.setY(i);
		    circle.setRadius(100 + i);
		    circle.draw();
		}
	}
}
