package adj.felix.java.patterns.ch03.behavioral.command;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * ~~~~ <b>命令模式</b> ~~~
 * 亦动作模式/事务模式。
 * 01. 定义
 *   将一个请求封装为一个对象, 可用不同的请求对用户进行参数化。对请求排队或记录请求日志, 以及支持撤销操作。
 * 02. 结构
 *   (01) Command(抽象命令)     
 *        声明执行操作的接口。
 *   (02) ConcreteCommand(具体命令)
 *        实现命令接口。 通常持有接收者, 起到转发消息的作用, 即调用接收者的功能完成命令需要执行的操作。 
 *   (03) Receiver(接收者)
 *        执行命令的对象。
 *   (04) Invoker(调用者)
 *        要求命令对象执行请求。通常会持有命令对象, 可以持有很多的命令对象。
 *   (05) Client(客户端)
 *        创建具体的命令对象, 并且设置命令对象的接收者, 使用Invoker触发执行命令。 
 * 03. 优点
 *   (01) 降低系统的耦合度
 *        使得发起命令的对象(客户端)和具体实现命令的对象(接收者)完全解耦, 即客户端完全不知道接收者是谁, 也不知道如何实现。
 *   (02) 控制更动态
 *        命令模式将请求封装起来, 可以动态对它就行参数化、序列化和日志化等操作, 因而可以动态控制系统。
 *   (03) 容易实现命令复合
 *        命令对象能动态组合成为复合命令, 使得系统操作更简单, 功能更强大。
 *   (04) 扩展性更好
 *        可以动态扩展新的命令和接收者。
 * 04. 缺点
 *   可能导致系统有过多的具体命令类。针对每一个命令都需要设计一个具体命令类, 因此系统可能需要大量具体命令类, 这将影响命令模式的使用。 
 * 05. 适用场景
 *   (01) 需要将请求调用者和请求接收者解耦, 使得调用者和接收者不直接交互。
 *   (02) 需要在不同的时间指定请求、将请求排队和执行请求。
 *   (03) 系统需要支持命令的撤销(Undo)操作和恢复(Redo)操作。
 *   (04) 系统需要将一组操作组合在一起, 即支持宏命令。
 * </pre>
 * @author adolf felix
 */
public class CommandPattern {
	/** Receiver(接收者) **/
	interface IOperation {
		/** 获取结果 **/
		public int getResult();
		/** 设置初始值 **/
		public void setStartResult(int number);
		/** + **/
		public void add(int number);
		/** - **/
		public void sub(int number);
	}
	static class Operation implements IOperation {
		private int result;
		
		@Override
		public int getResult() {
			return result;
		}

		@Override
		public void setStartResult(int number) {
			this.result = number;
		}
		@Override
		public void add(int number) {
			result += number;
		}
		@Override
		public void sub(int number) {
			result -= number;
		}
	}
	/** Command(抽象命令) **/
	interface Command {
		public void execute();
		/** 撤销 **/
		public void undo();
	}
	/** ConcreteCommand(具体命令) **/
	static class AddCommand implements Command {
		private IOperation operation;
		private int number;
		
		public AddCommand(IOperation operation, int number) {
			this.operation = operation;
			this.number = number;
		}
		
		@Override
		public void execute() {
			this.operation.add(number);
		}
		@Override
		public void undo() {
			this.operation.sub(number);
		}
	}
	static class SubCommand implements Command {
		private IOperation operation;
		private int number;
		
		public SubCommand(IOperation operation, int number) {
			this.operation = operation;
			this.number = number;
		}
		
		@Override
		public void execute() {
			this.operation.sub(number);
		}
		@Override
		public void undo() {
			this.operation.add(number);
		}
	}
	/** Invoker(调用者) **/
	static class Calculator {
		private List<Command> comands = new ArrayList<Command>();
		private List<Command> redoComands = new ArrayList<Command>();
		private List<Command> undoComands = new ArrayList<Command>();
		
		public Calculator addCommand(Command command) {
			comands.add(command);
			return this;
		}
		
		public Calculator doPressed () {
			for(Command command: comands) {
				command.execute();
				undoComands.add(command);
			}
			comands.clear();
			return this;
		}
		/**
		 * <pre>
		 * ~~~~ <b>撤销操作</b> ~~~
		 * 方式
		 *   (01) 补偿式
		 *        亦反操作式, 按照原来的操作反向操作。
		 *   (02) 存储恢复式
		 *        把操作状态记录下来, 然后撤销操作的时候, 直接恢复就可以了, 请查看"备忘录模式"。
		 * </pre>
		 * @return
		 */
		public Calculator undoPressed () {
			// 不执行操作
			if(undoComands.isEmpty()) {
				return this;
			}
			
			int index=undoComands.size() -1;
			Command command = undoComands.get(index);
			
			// 处理
			undoComands.remove(command);
			redoComands.add(command);
			
			// 执行操作
			command.undo();
			
			return this;
		}
		public Calculator redoPressed () {
			if (redoComands.isEmpty()) {
				return this;
			}
			
			int index = redoComands.size() -1;
			Command command = redoComands.get(index);
			
			// 执行之后, 不能后退了
			undoComands.clear();
			redoComands.remove(command);
			
			// 执行操作
			command.execute();
			
			return this;
		}
	}
	
	/** Client(客户端) **/
	public static void main(String[] args) {
		// Invoker(调用者)
		Calculator calculator = new Calculator();
		
		// Receiver(接收者)
		IOperation operation = new Operation();
		
		// ConcreteCommand(具体命令)
		Command add5 = new AddCommand(operation, 5);
		Command add2 = new AddCommand(operation, 4);
		Command sub3 = new SubCommand(operation, 3);
		
		calculator.addCommand(add5)
		          .addCommand(add2)
		          .addCommand(sub3);
		
		// 按下按键执行操作
		calculator.doPressed();
		System.out.println("Result = " + operation.getResult());
		
		// 撤销操作
		calculator.undoPressed()
		          .undoPressed();
		System.out.println("Undo Twice Result = " + operation.getResult());
		
		// 前进操作
		calculator.redoPressed();
		System.out.println("Redo Once Result = " + operation.getResult());
	}
}
