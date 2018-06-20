package adj.felix.java.patterns.ch03.behavioral.observer;

import java.util.Enumeration;
import java.util.Vector;

/**
 * <pre>
 * ~~~~ <b>观察者模式</b> ~~~
 * 亦发表-订阅模式、模型-视图模式、源-收听者模式、从属者模式。
 * 01. 概述
 *     观察者模式定义了一个一对多的依赖, 让一个或多个观察者对象监听一个主题的对象状态变化。
 * 02. 组成结构
 *     (01) Subject(抽象主题)
 *          亦抽象被观察者, 可以是抽象类或者接口实现。
 *     (02) Observer(抽象观察者)
 *     (03) ConcreteSubject(具体主题)
 *          亦具体被观察者, 当状态发生变化时, 给具体观察者发送通知。
 *     (04) ConcreteObserver(具体观察者)
 *          接收通知处理
 * 03. 优点
 *     (01) 具体主题和具体观察者是松耦合关系
 *          由于主题接口仅仅依赖于观察者接口, 因此具体主题只需知道观察者是实现抽象观察者接口的某个实例, 无须知道具体是哪个类。
 *          同样, 由于观察者仅仅依赖于主题接口, 因此具体观察者只需知道依赖的主题是实现抽象主题接口的某个实例, 无须知道具体是哪个类。
 *     (02) 观察者模式满足"开-闭原则"
 *          主题接口仅仅依赖于观察者接口, 创建具体主题的类也仅仅是依赖于观察者接口; 因此, 如果增加新的实现观察者接口的类, 不必修改创建具体主题的类的代码。
 *          同样, 创建具体观察者的类仅仅依赖于主题接口, 如果增加新的实现主题接口的类, 也不必修改创建具体观察者类的代码。
 *     (03) 支持广播通讯
 * 04. 缺点
 *     (01) 如果一个主题有很多的直接和间接的观察者, 每次通知所有观察者会花费很多时间。
 *     (02) 如果在主题之间存在循环依赖, 那么主题之间会进行循环调用, 导致系统崩溃。在使用观察者模式是要特别注意这一点。
 *     (03) 如果对观察者的通知是通过另外的线程进行异步投递的话, 系统必须保证投递是以自恰的方式进行的。
 *     (04) 虽然观察者模式可以随时使观察者知道所观察的对象发生了变化, 但是观察者模式没有相应的机制使观察者知道所观察的对象是怎么发生变化的。
 * 05. 使用场景
 *     (01) 对象的数据更新时需要通知其他对象, 但不希望和被通知的那些对象形成紧耦合。
 *     (02) 对象的数据更新时, 需要让其他对象也各自更新自己的数据, 但不知道具体有多少对象需要更新数据。
 * </pre>
 * @author adolf felix
 */
public class ObserverPattern {
	/** 抽象主题 **/
	interface Subject {
		/** 绑定观察者 **/
		public void attach(Observer observer);
		/** 移除绑定的观察者 **/
		public void detach(Observer observer);
		/** 广播所有的观察者  **/
		public void notifyObservers();
	}
	/**
	 * 订单主题 - 自行考虑并发线程问题
	 */
	static class OrderSubject implements Subject {
		// 主题内部状态, 可以自动声明维护
		// private State state;
		
		private Vector<Observer> observers = new Vector<Observer>();
		
		@Override
		public void attach(Observer observer) {
			if(!this.observers.contains(observer)) {
				this.observers.add(observer);
			}
		}
		@Override
		public void detach(Observer observer) {
			this.observers.remove(observer);
		}
		@Override
		public void notifyObservers() {
			Enumeration<Observer> elements = observers.elements();
			while(elements.hasMoreElements()) {
				Observer observer = elements.nextElement();
				observer.update("Order Price Changed From Order Subject");
			}
		}
	}
	
	/** 抽象观察者 **/
	interface Observer {
		/** 接收通知 **/
		public void update(String... args);
	}
	static class WarehouseObserver implements Observer {
		@Override
		public void update(String... args) {
			System.out.println("[Warehouse Observer], Message = " + args[0]);
		}
	}
	static class PayObserver implements Observer {
		@Override
		public void update(String... args) {
			System.out.println("[Pay Observer], Message = " + args[0]);
		}
	}
	
	public static void main(String[] args) {
		// 主题
		Subject subject = new OrderSubject();
		// 观察者
		Observer warehouseObserver = new WarehouseObserver();
		Observer payObserver = new PayObserver();
		
		// 订阅主题
		subject.attach(warehouseObserver);
		subject.attach(payObserver);
		
		System.out.println("++++++++++++++ 已订阅 +++++++++++++++");
		// 发送通知
		subject.notifyObservers();
		
		System.out.println("\n++++++++++++++ 取消部分订阅 +++++++++++++++");
		// 取消订阅主题
		subject.detach(payObserver);
		// 发送通知
		subject.notifyObservers();
	}
}