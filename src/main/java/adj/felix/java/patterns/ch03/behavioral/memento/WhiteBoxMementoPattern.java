package adj.felix.java.patterns.ch03.behavioral.memento;

/**
 * <pre>
 * ~~~~ <b>观察者模式之白箱观察者模式</b> ~~~
 * 备忘录提供一个宽接口, 通过该接口, 备忘录内部存储的状态对外是可见的。
 * 缺点： 白箱实现将原发器的状态存储是外部可见的, 因此是破坏封装性的。 
 * </pre>
 * @author adolf felix
 */
public class WhiteBoxMementoPattern {
	/** Originator(原发器), 功能：计算器算法 **/
	interface IOperate {
		/** 创建备忘录 **/
		public History createHistory();
		/** 恢复备忘录 **/
		public void restoreHistory(History memento);
		
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
		public void restoreHistory(History history) {
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
	}
	/** Memento(备忘录), 功能：历史记录 **/
	static class History {
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
	/** Caretacker(备忘录管理者), 功能：计算器 **/
	/** Caretacker(备忘录管理者), 功能：计算器 **/
	static class Caculator {
		private IOperate operate;
		private History history;
		
		public Caculator(IOperate operate) {
			this.operate = operate;
		}
		
		public void saveHistory(History history) {
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
		// 描述：宽接口, 对外可见
		History memento = originator.createHistory();
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
