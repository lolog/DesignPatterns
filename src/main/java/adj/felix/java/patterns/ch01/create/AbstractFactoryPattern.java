package adj.felix.java.patterns.ch01.create;

/**
 * <pre>
 * ~~~~ <b>抽象工厂模式</b> ~~~~
 * 抽象工厂模式与工厂方法模式的最大区别: 工厂方法模式针对的是一个产品等级结构;而抽象工厂模式则需要面对多个产品等级结构。
 * 产品族: 位于不同产品等级结构中,功能相关联的产品组成的家族。
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
