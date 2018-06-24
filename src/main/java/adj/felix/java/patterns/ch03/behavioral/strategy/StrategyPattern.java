package adj.felix.java.patterns.ch03.behavioral.strategy;

import java.text.MessageFormat;

/**
 * <pre>
 * ~~~~ <b>策略模式</b> ~~~
 * 01. 概述
 *   (01) 定义
 *        策略模式定义了一系列的算法, 并将每一个算法封装起来, 而且使他们可以相互替换, 让算法独立于使用它的客户而独立变化
 *   (02) 本质
 *        分离算法, 选择实现。
 * 02. 组成结构
 *     (01) Context(环境)
 *          持有一个Strategy的引用。
 *     (02) Strategy(抽象策略)
 *     (03) ConcreteStrategy(具体策略)
 *          实现相关的算法或行为。
 * 03. 使用场景
 *     (01) 针对同一类型问题的多种处理方式, 仅仅是具体行为有差别时; 
 *     (02) 需要安全封装多种同一类型的操作时; 
 *     (03) 出现同一抽象类有多个子类, 而又需要使用 if-else或者 switch-case来选择具体子类时。
 * 04. 优点
 *     (01) 算法分离
 *     (02) 避免多重实现
 *     (03) 更好的扩展性
 * 05. 缺点
 *     (01) 客户端需要了解各种算法
 *     (02) 只适合扁平的算法结构
 *          即一个策略接口下实现的平等算法。 而且, 在运行时只有一个算法被使用, 限制了算法的使用层级。
 * </pre>
 * @author adolf felix
 */
public class StrategyPattern {
	/** Context(环境) **/
	static class PriceContext {
		private Vip vip;
		public PriceContext(Vip vip) {
			this.vip = vip;
		}
		public double price(double price) {
			return vip.discount() * price;
		}
	}
	/** Strategy(抽象策略) **/
	interface Vip {
		public double discount();
	}
	/** ConcreteStrategy(具体策略) **/
	static class Normal implements Vip {
		@Override
		public double discount() {
			return 1.00;
		}
	}
	static class SilverVip implements Vip {
		@Override
		public double discount() {
			return 0.98;
		}
	}
	static class GoldVip implements Vip {
		@Override
		public double discount() {
			return 0.90;
		}
	}
	
	public static void main(String[] args) {
		Vip vip = new SilverVip();
		PriceContext context = new PriceContext(vip);
		
		double price = 100;
		System.out.println(MessageFormat.format("Original Price={0}, Discount Price={1}", price, context.price(price)));
	}
}
