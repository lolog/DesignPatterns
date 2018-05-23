package adj.felix.java.patterns.ch01.create;

/**
 * <pre>
 * ~~~~ <b>建造者模式</b> ~~~~
 * 01. 概念
 *     又叫创建者模式、生成器模式。
 *     建造者模式是针对于产品的创建过程, 产品的组装算法是确定的, 而零部件是分配给多个厂商生产, 实现了分开实现产品零部件的生产和组装。
 *     建造者模式能够将一个复杂的构建与其表示相分离, 使得同样的构建过程可以创建不同的表示。
 *     建造者模式隐藏了复杂对象的创建过程, 它把复杂对象的创建过程加以抽象, 通过子类继承或者重载的方式, 动态的创建具有复合属性的对象。
 * 02. 角色
 *     1) 产品(Product)
 *        要创建的复杂对象, 一般来说包含多个部分。
 *     2) 抽象创建者(Builder)
 *        对复杂对象的创建过程加以抽象, 规范产品对象的各个组成部分的建造。
 *     3) 具体创建者(ConcreteBuilder)。
 *        I. 实现Builder接口, 针对不同的业务逻辑, 具体化产品各个部分的创建。
 *        II.在建造过程完成后, 提供产品的实例。
 *     4) 指导者(Director)
 *        调用具体创建者, 创建复杂对象的各个部分, 不涉及具体产品的信息, 只负责保证对象各部分完整创建或者按某种顺序创建。
 * 03. 实用范围
 *     1) 创建复杂对象的算法独立于对象的组成部分, 以及装配方式。
 *     2) 构造过程, 被构造的对象有不同表示。
 * 04. 优点
 *     1) 封装性
 *        客户端不必知道产品内部组成的细节, 将产品本身与产品的创建过程解耦, 使得相同的创建过程可以创建不同的产品对象。
 *     2) 建造者独立, 易于拓展
 *        每个具体建造者都是相互独立的, 那么使用不同的具体建造者可得到不同的产品对象。
 *     3) 精细地控制产品的创建过程
 *        将复杂产品的创建步骤分解在不同的方法中, 使得创建过程更加清晰, 也更方便使用程序来控制创建过程。
 *     4) 符合"开闭原则"
 *        新增具体建造者无须修改原有类库的代码, 指导者类针对抽象建造者类编程, 系统扩展方便, 符合"开闭原则"。
 * 05. 缺点
 *     1) 如果产品差异很大, 使用范围会受到一定的限制
 *        建造者模式所创建的产品一般具有较多的共同点, 其组成部分相似, 如果产品之间的差异性很大, 则不适合使用建造者模式, 因此其使用范围受到一定的限制。
 *     2) 产品内部变化复杂, 会导致系统变得很庞大
 *        如果产品的内部变化复杂, 可能会导致需要定义很多具体建造者类来实现这种变化, 导致系统变得很庞大。
 * 06. 与抽象工厂模式的区别
 *     1) 抽象工厂模式
 *        I. 针对于产品族, 使用"工厂"创建一类产品族(即, 包含各类产品)。
 *        II. 隐藏"工厂"内部的细节, 只关注生产什么类型的产品, 不关注具体生产的过程。
 *        III. 如果需要屏蔽对象创建过程, 只提供一个封装良好的对象, 则可以选择抽象工厂方法模式。
 *     2) 建造者模式
 *        I. 针对于产品的创建过程, "具体创建者"根据不同的创建过程构建一个完整的产品。
 *        II. 产品的创建需要"指导者"按照不同规则创建完整的对象, 不同的创建者完成不同的创建和装配任务。
 *        III. 建造者模式用在构件的装配方面, 如通过装配不同的组件或者相同组件的不同顺序, 可以产生出一个新的对象, 它可以产生一个非常灵活的架构, 方便地扩展和维护系统。
 * </pre>
 * @author adolf.felix
 */
public class BuilderPattern {
	/** 汽车类 **/
	static class Car {
		// 车头
		private String head;
		// 车身s
		private String body;
		// 车尾
		private String tail;
		public String getHead() {
			return head;
		}
		public void setHead(String head) {
			this.head = head;
		}
		public String getBody() {
			return body;
		}
		public void setBody(String body) {
			this.body = body;
		}
		public String getTail() {
			return tail;
		}
		public void setTail(String tail) {
			this.tail = tail;
		}
	}
	/** 汽车建造者抽象类 **/
	static abstract class CarBuilder {
		// 组装车头
		abstract void makeHead();
		// 组装车身
		abstract void makeBody();
		// 组装车尾
		abstract void makeTail();
		// 获取组装好的车
		abstract Car getCar();
	}
	/** 捷普车组装类 **/
	static class JeepBuilder extends CarBuilder {
		Car car = new Car();
		
		@Override
		void makeHead() {
			car.setHead("Jeep head");
		}
		@Override
		void makeBody() {
			car.setBody("Jeep body");
		}
		@Override
		void makeTail() {
			car.setTail("Jeep tail");
		}
		
		public Car getCar() {
			return car;
		}
	}
	/** 汽车组装操作的封装类 **/
	static class CarDirector {
		private CarBuilder builder;
		public CarDirector(CarBuilder builder) {
			this.builder = builder;
		}
		public void makeCar() {
			builder.makeHead();
			builder.makeBody();
			builder.makeTail();
		}
		public Car getResult () {
			return builder.getCar();
		}
	}
	
	public static void main(String[] args) {
		// 捷普车建造者
		CarBuilder builder = new JeepBuilder();
		// 指挥者
		CarDirector director = new CarDirector(builder);
		
		director.makeCar();
		
		Car car = director.getResult();
		
		System.out.println("[head] == " + car.getHead());
		System.out.println("[body] == " + car.getBody());
		System.out.println("[tail] == " + car.getTail());
	}
}
