package adj.felix.java.patterns.ch03.behavioral.memento;


/**
 * <pre>
 * ~~~~ <b>观察者模式之自述历史备忘录模式</b> ~~~
 * 在"自述历 史"模式里面, 原发器同时兼任备忘录管理者角色。
 * </pre>
 * @author adolf felix
 */
public class SayHistoryMementoPattern {
	/** Originator(原发器), 功能：计算器算法 **/
	interface IOperate {
		/** 创建备忘录 **/
		public void createHistory();
		/** 恢复备忘录 **/
		public void restoreHistory();
		
		public int getResult();
		public void setResult(int state);
		
		/** + **/
		public void add(int number);
		/** - **/
		public void sub(int number);
	}
	static class Operate implements IOperate {
		private IHistory history;
		private int result;
		
		@Override
		public int getResult() {
			return this.result;
		}
		public void setResult(int result) {
			this.result = result;
		}
		
		@Override
		public void createHistory() {
			this.history = new History(result);
		}
		@Override
		public void restoreHistory() {
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
	
	/** 客户端 **/
	public static void main(String[] args) {
		// Originator(原发器), 功能：计算器算法
		IOperate originator = new Operate();
		
		// 计算
		originator.add(5);
		
		// Memento(备忘录), 功能：历史记录
		// 描述：窄接口, 不能访问其内部属性
		originator.createHistory();
		
		// 计算
		originator.add(3);
		System.out.println("Add Result = " + originator.getResult());
		
		// 返回到刚才的状态
		originator.restoreHistory();
		System.out.println("Restore Result = " + originator.getResult());
	}
}
