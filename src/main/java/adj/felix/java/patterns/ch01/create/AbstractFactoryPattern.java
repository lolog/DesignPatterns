package adj.felix.java.patterns.ch01.create;

/**
 * <pre>
 * ~~~~ <b>抽象工厂模式</b> ~~~~
 * 抽象工厂模式与工厂方法模式的最大区别在于: 工厂方法模式针对的是一个产品等级结构; 而抽象工厂模式则需要面对多个产品等级结构, 即产品族。
 * 产品族和产品级别: 位于不同产品等级结构中,功能相关联的产品组成的家族。
 *    比如AMD的主板、芯片组、CPU组成一个家族, Intel的主板、芯片组、CPU组成一个家族。
 *    而这两个家族都来自于三个产品等级：主板、芯片组、CPU。
 * 目的: 给客户端提供一个接口, 可以创建多个产品族中的产品对象。使用抽象工厂模式的条件：
 *    1) 系统不依赖于产品类实例如何被创建、组合和表达的细节。
 *    2) 系统的产品有多于一个的产品族, 而系统只消费其中某一产品族的产品。
 *    3) 使用同属于同一个产品族的产品, 这一约束必须在系统的设计中体现出来。比如：Intel主板必须使用Intel CPU、Intel芯片组.
 *    4) 系统提供一个产品类的库, 所有的产品以同样的接口出现, 从而使客户端不依赖于实现。
 * 优点：
 *    1) 分离接口和实现
 *       客户端使用抽象工厂来创建需要的对象，而客户端根本就不知道具体的实现是谁，客户端只是面向产品的接口编程而已。也就是说，客户端从具体的产品实现中解耦。
 *    2) 使切换产品族变得容易
 *       因为一个具体的工厂实现代表的是一个产品族，比如上面例子的从Intel系列到AMD系列只需要切换一下具体工厂。
 * 缺点：
 *    不太容易扩展新的产品: 如果需要给整个产品族添加一个新的产品, 那么就需要修改抽象工厂, 这样就会导致修改所有的工厂实现类。
 * </pre>
 * @author adolf.felix
 */
public class AbstractFactoryPattern {
	
	interface Cpu {
		public void calculate();
	}

	static class IntelCpu implements Cpu {
		/* CPU的针脚数 */
		private int pins = 0;
		IntelCpu(int pins) {
			this.pins = pins;
		}
		@Override
		public void calculate() {
			System.out.println("Intel CPU Pins = " + pins);
		}
	}

	static class AmdCpu implements Cpu {
		private int pins = 0;
		public AmdCpu(int pins) {
			this.pins = pins;
		}
		@Override
		public void calculate() {
			System.out.println("AMD CPU Pins = " + pins);
		}
	}

	interface Mainboard {
		public void installCPU();
	}

	static class IntelMainboard implements Mainboard {
		/* CPU插槽的孔数 */
		private int cpuHoles = 0;
		public IntelMainboard(int cpuHoles) {
			this.cpuHoles = cpuHoles;
		}
		@Override
		public void installCPU() {
			System.out.println("Intel Mainboard Holes = " + cpuHoles);
		}
	}

	static class AmdMainboard implements Mainboard {
		private int cpuHoles = 0;
		public AmdMainboard(int cpuHoles) {
			this.cpuHoles = cpuHoles;
		}
		@Override
		public void installCPU() {
			System.out.println("AMD Mainboard Holes = " + cpuHoles);
		}
	}

	interface AbstractFactory {
		public Cpu createCpu();
		public Mainboard createMainboard();
	}

	static class IntelFactory implements AbstractFactory {
		@Override
		public Cpu createCpu() {
			return new IntelCpu(755);
		}
		@Override
		public Mainboard createMainboard() {
			return new IntelMainboard(755);
		}
	}

	static class AmdFactory implements AbstractFactory {
		@Override
		public Cpu createCpu() {
			return new IntelCpu(938);
		}

		@Override
		public Mainboard createMainboard() {
			return new IntelMainboard(938);
		}
	}

	static class ComputerEngineer {
		/* 定义组装机需要的CPU */
		private Cpu cpu;
		/* 定义组装机需要的主板 */
		private Mainboard mainboard;

		public void makeComputer(AbstractFactory af) {
			/* 组装机器的基本步骤 */
			// 1:首先准备好装机所需要的配件
			prepareHardwares(af);
			// 2:组装机器
			// 3:测试机器
			// 4：交付客户
		}

		private void prepareHardwares(AbstractFactory af) {
			// 直接找相应的工厂获取
			this.cpu = af.createCpu();
			this.mainboard = af.createMainboard();
			// 测试配件是否好用
			this.cpu.calculate();
			this.mainboard.installCPU();
		}
	}

	public static void main(String[] args) {
		// 创建装机工程师对象
		ComputerEngineer cf = new ComputerEngineer();
		// 客户选择并创建需要使用的产品对象
		AbstractFactory af = new IntelFactory();
		// 告诉装机工程师自己选择的产品，让装机工程师组装电脑
		cf.makeComputer(af);
	}
}
