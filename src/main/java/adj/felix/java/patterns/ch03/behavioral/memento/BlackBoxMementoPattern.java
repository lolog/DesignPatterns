package adj.felix.java.patterns.ch03.behavioral.memento;


/**
 * <pre>
 * ~~~~ <b>观察者模式之黑箱观察者模式</b> ~~~
 * 备忘录对原发器提供一个宽接口, 对其他对象提供一个窄接口, 备忘录内部存储的状态对外是可见的。
 * 实现：
 *    在JAVA语言中, 实现双重接口的办法就是将备忘录设计成原发器的内部成员类。 
 * </pre>
 * @author adolf felix
 */
public class BlackBoxMementoPattern {
	/** Originator(原发器), 功能：计算器算法 **/
	interface IOperate {
		/** 创建备忘录 **/
		public IHistory createHistory();
		/** 恢复备忘录 **/
		public void restoreHistory(IHistory memento);
		
		public int getResult();
		public void setResult(int state);
		
		/** + **/
		public void add(int number);
		/** - **/
		public void sub(int number);
	}
	static class Operate implements IOperate {
		private int result;
		
		@Override
		public int getResult() {
			return this.result;
		}
		public void setResult(int result) {
			this.result = result;
		}
		
		@Override
		public History createHistory() {
			return new History(result);
		}
		@Override
		public void restoreHistory(IHistory history) {
			this.result = history.getResult();
		}
		
		@Override
		public void add(int number) {
			this.result += number;
		}
		@Override
		public void sub(int number) {
			this.result -= number;
		}
		
		static class History implements IHistory {
			private int result;
			
			public History() {
			}
			public History(int result) {
				this.result = result;
			}
			public void setResult(int result) {
				this.result = result;
			}
			public int getResult() {
				return result;
			}
		}
	}
	/** Memento(备忘录), 功能：历史记录 **/
	interface IHistory {
		public void setResult(int result);
		public int getResult();
	}
	/** Caretacker(备忘录管理者), 功能：计算器 **/
	static class Caculator {
		private IOperate operate;
		private IHistory history;
		
		public Caculator(IOperate operate) {
			this.operate = operate;
		}
		
		public void saveHistory(IHistory history) {
			this.history = history;
		}
		public void restoreHistory() {
			operate.restoreHistory(history);
		}
	}
	
	/** 客户端 **/
	public static void main(String[] args) {
		// Originator(原发器), 功能：计算器算法
		IOperate originator = new Operate();
		
		// Caretacker(备忘录管理者), 功能：计算器
		Caculator caretacker = new Caculator(originator);
		
		// 计算
		originator.add(5);
		
		// Memento(备忘录), 功能：历史记录
		// 描述：窄接口, 不能访问其内部属性
		IHistory memento = originator.createHistory();
		// 根据原发器, 决定保存状态到备忘录
		caretacker.saveHistory(memento);
		
		// 计算
		originator.add(3);
		System.out.println("Add Result = " + originator.getResult());
		
		// 返回到刚才的状态
		caretacker.restoreHistory();
		System.out.println("Restore Result = " + originator.getResult());
	}
}
