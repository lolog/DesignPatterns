package adj.felix.java.patterns.ch02.structure.bridge;

/**
 * <pre>
 * ~~~~ <b>桥接模式</b> ~~~~
 * 桥梁模式是对象的结构模式, 又称为柄体模式和接口模式。
 * 01. 用意：将抽象化与实现脱耦, 使得二者可以独立变化。
 *     (1) 抽象化：抽象出存在于多个实体中共性的联系。
 *     (2) 实现：抽象化的具体实现。
 *     (3) 脱藕：耦合的解耦。 实现方式,将角色之间的继承关系改为聚合关系, 即将继承的强关联改为聚合的弱关联。
 * 02. 结构 {@link BridgePattern.jpg}
 *     (1) 等级结构
 *         1) 抽象化等级结构：由抽象化、修正的抽象化角色构成。
 *         2) 实现化等级结构：由实现角色、具体实现角色构成。
 *     (2) 角色
 *         1) 抽象化(Abstraction)： 抽象化的定义, 保存一个实现化对象的引用。
 *         2) 修正抽象化(RefinedAbstraction)： 拓展抽象化, 修正父类对抽象化的定义。
 *         3) 实现化(Implementor)：只给出实现化的接口, 但不给出具体的实现。
 *         4) 具体实现化(ConcreteImplementor)
 * 03. 概念
 *     问题：究竟什么是桥接? 为何需要桥接? 如何实现桥接? 谁来桥接?
 *     (1) 桥接
 *         在不同的对象之间,将这些不同的对象链接起来, 从而实现相互通信和使用的一个桥梁。
 *         如,AbstractMessage作为桥梁,将CommonMessage/UrgencyMessage/EspeciallyUrgencyMessage和MessagSMS/MessagEmail/MessagMobile链接起来。
 *     (2) 为何需要链接
 *         同上
 *     (3) 如何实现桥接
 *         抽象化拥有实现化对象, 就算桥接上了。 如AbstractMessage拥有MessageImplementor的impl实例对象。
 *     (4) 谁来桥接
 *         即, 谁来负责创建Implementor对象, 并将其设置到抽象化的对象上? 方式如下：
 *         1) 抽象化拥有实现化对象, 客户端创建Implementor对象, 并且将其设置到该对象上。
 *         2) 构建抽象化对象的时候, 根据传入的参数值选择创建相应的Implementor对象。
 *         3) 构建抽象化对象的时候, 创建一个默认的Implementor对象, 然后子类决定是否改变其实现。
 *         4) 通过抽象工厂或者简单工厂选择创建具体的Implementor对象, 抽象化可以通过调用工厂的方法获取Implementor对象。
 *         5) 通过IoC/DI容器创建和获取Implementor对象, 将其注入到Abstraction。
 * 04. 对设计原则的体现
 *     (1) 开闭原则
 *     (2) 多用对象组合, 少用对象继承
 * 05. 适用场景
 *     (1) 抽象和实现不采用固定绑定关系。
 *     (2) 抽象和实现应该扩展的情况。
 *     (3) 实现的部分修改, 不会对客户产生影响。
 *     (4) 采用继承的实现方案, 会导致产生很多子类, 需要减少子类的情况。
 * 06. 优点
 *     (1) 分离抽象与实现部分
 *         抽象化和实现化独立定义了接口, 从而实现了系统的分层。
 *     (2) 更好的扩展性
 *          桥梁模式实现抽象和实现的分离, 而且分别定义接口, 使得抽象化和实现化可以独立地扩展, 而不会相互影响, 从而提高了系统的可扩展性。
 *     (3) 可动态切换实现
 *         桥梁模式实现抽象和实现的分离, 实现桥接的时候, 就可以实现动态的选择和使用具体的实现。
 *     (4) 可减少子类的个数
 * </pre>
 * @author adolf.felix
 */
public class BridgePattern {
	/**
	 * 1. 需求：发送消息 -> 不同的消息类型, 具有不同的业务功能。 
	 *        其中, 消息类型：普通消息、加急消息和特急消息, 消息发送方式包括：站内短信、邮件和手机短消息。
	 * 2. 分析：发送消息的业务包括：消息类型、消息发送方式2个纬度{@link Dimensions.png}, 抽象化为：抽象的消息、具体的消息发送方式。
	 *        实现的时候, 必须将抽象的消息和具体的消息发送方式相互独立, 否则, 一个纬度的变化会导致另一个纬度进行相应的变化, 从而使得程序扩展非常困难。
	 *        实现结构为{@link Message.png}
	 */
	
	/**
	 * 抽象化角色(Abstraction) <br>
	 * 抽象的消息对象, AbstractMessage类作用：桥梁的关系。
	 */
	static abstract class AbstractMessage {
		/** 实现部分的接口 **/
		private MessageImplementor impl;
		
		public AbstractMessage(MessageImplementor impl) {
			this.impl = impl;
		}
		/**
		 * 默认实现
		 * @param receiver 接收者
		 * @param message 发送的消息
		 */
		public void sendMessage(String receiver, String message) {
			this.impl.send(receiver, message);
		}
	}
	/**
	 * 修正抽象化(RefinedAbstraction) <br>
	 * 普通消息
	 */
	static class CommonMessage extends AbstractMessage {
		public CommonMessage(MessageImplementor impl) {
			super(impl);
		}
	}
	/**
	 * 修正抽象化(RefinedAbstraction) <br>
	 * 加急消息
	 */
	static class UrgencyMessage extends AbstractMessage {
		public UrgencyMessage(MessageImplementor impl) {
			super(impl);
		}
		@Override
		public void sendMessage(String receiver, String message) {
			message = "[-Urgency-] " + message;
			super.sendMessage(receiver, message);
		}
	}
	/**
	 * 修正抽象化(RefinedAbstraction) <br>
	 * 特急消息
	 */
	static class EspeciallyUrgencyMessage extends AbstractMessage {
		public EspeciallyUrgencyMessage(MessageImplementor impl) {
			super(impl);
		}
		@Override
		public void sendMessage(String receiver, String message) {
			message = "[-Especially Urgency-] " + message;
			super.sendMessage(receiver, message);
		}
	}
	
	/**
	 * 实现化(Implementor) <br>
	 * 抽象的消息发送方式
	 */
	interface MessageImplementor {
		public void send(String receiver, String message);
	}
	/**
	 * 具体实现化(ConcreteImplementor) <br>
	 * 发送信息的方式：站内短信
	 */
	static class MessagSMS implements MessageImplementor {
		@Override
		public void send(String receiver, String message) {
			System.out.println("{SendType=SMS, Receiver="+ receiver + ", Message=" + message + "}");
		}
	}
	/**
	 * 具体实现化(ConcreteImplementor) <br>
	 * 发送信息的方式：邮件
	 */
	static class MessagEmail implements MessageImplementor {
		@Override
		public void send(String receiver, String message) {
			System.out.println("{SendType=Email, Receiver="+ receiver + ", Message=" + message + "}");
		}
	}
	/**
	 * 具体实现化(ConcreteImplementor) <br>
	 * 发送信息的方式：手机短信
	 */
	static class MessagMobile implements MessageImplementor {
		@Override
		public void send(String receiver, String message) {
			System.out.println("{SendType=Mobile, Receiver="+ receiver + ", Message=" + message + "}");
		}
	}
	
	public static void main(String[] args) {
		System.out.println("*************** 站内发送-特急消息 ***************");
		MessageImplementor sendMessageBySMS = new MessagSMS(); // 站内短信
		AbstractMessage especiallyUrgencyMessage = new EspeciallyUrgencyMessage(sendMessageBySMS); // 特急消息
		especiallyUrgencyMessage.sendMessage("Adolf.Felix", "Help Me!");
		
		System.out.println("\n*************** 邮件发送-特急消息 ***************");
		MessageImplementor sendMessageByEamil = new MessagEmail(); // 邮件短信
		especiallyUrgencyMessage = new EspeciallyUrgencyMessage(sendMessageByEamil); // 特急消息
		especiallyUrgencyMessage.sendMessage("Adolf.Felix", "Help Me!");
		
		System.out.println("\n*************** 邮件发送-加急消息 ***************");
		MessageImplementor sendMessageByMobile = new MessagMobile(); // 站内短信
		AbstractMessage urgencyMessage = new UrgencyMessage(sendMessageByMobile);
		urgencyMessage.sendMessage("Adolf.Felix", "What is your contact information?");
		
		
	}
}