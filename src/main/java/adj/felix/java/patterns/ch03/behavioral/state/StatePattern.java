package adj.felix.java.patterns.ch03.behavioral.state;

/**
 * <pre>
 * ~~~~ <b>状态模式</b> ~~~
 * 01.定义
 *    允许一个对象在其内部状态改变时, 改变它的行为, 看起来似乎修改了它的类。状态模式是一种对象行为型模式。
 * 02.组成结构
 *   (01)Context(环境)
 *       定义客户端感兴趣的接口, 并且内部维护一个具体状态类的实例, 从而维护了对象的现有状态。
 *   (02)State(抽象状态)
 *       抽象状态类或状态接口, 抽象封装行为。
 *   (03)ConcreteState(具体状态)
 * 03.优缺点
 *   (01)优点
 *       避免为了判断状态而使用大量的if/else逻辑代码, 使代码结构清晰的同时还保证了拓展性和维护性。
 *   (02)缺点
 *       导致系统过多的具体状态类。
 * 04.策略模式和状态模式比较
 *   (01)结构几乎完全一致, 目的和本质完全不一样。
 *   (02)策略模式围绕互换的算法来创建业务的, 状态模式通过改变内部的状态来控制对象的行为。前者行为是彼此独立、可以相互替换的, 后者行为是不可以相互替换的。
 * 05.使用场景
 *   (01)对象的行为依赖于它的状态, 并且可以在运行时根据状态改变行为。
 *   (02)代码中包含大量与对象状态有关的if/else语句, 这些条件对应于对象的各种状态, 这些冗余条件语句的出现导致代码的可维护性和灵活性变差, 这种情况适合使用状态模式进行优化。
 * </pre>
 * @author adolf felix
 */
public class StatePattern {
	/** State(抽象状态) **/
	interface IWaterState {
		public void handle();
	}
	/** ConcreteState(具体状态) **/
	static class NormalWaterState implements IWaterState {
		@Override
		public void handle() {
			System.out.println("Normal Temperature");
		}
	}
	static class WarmWaterState implements IWaterState {
		@Override
		public void handle() {
			System.out.println("Warm Temperature");
		}
	}
	static class BoilingWaterState implements IWaterState {
		@Override
		public void handle() {
			System.out.println("Boiling Temperature");
		}
	}
	/** Context(环境) **/
	static class WaterContext {
		private IWaterState state;
		
		public void setState(IWaterState state) {
			this.state = state;
		}
		public void printState() {
			state.handle();
		}
	}
	
	public static void main(String[] args) {
		WaterContext context = new WaterContext();
		
		context.setState(new NormalWaterState());
		context.printState();
		
		context.setState(new WarmWaterState());
		context.printState();
		
		context.setState(new BoilingWaterState());
		context.printState();
	}
}