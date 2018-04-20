package adj.felix.java.patterns.ch00.principle;

import org.junit.Test;

/**
 * <pre>
 * 依赖倒转原则
 * 高层模块不应该依赖低层模块,都应该依赖抽象;抽象不应该依赖细节;细节应该依赖抽象。
 * 通过接口的方式,{@link IReader}接口动态实现 {@link IRead}的调用。
 * </pre>
 * @author adolf felix
 */
public class DependenceInversionPrinciple {
	interface IRead {
		public void read();
	}
	
	abstract class IReader {
		public void read(IRead read) {
			read.read();
		}
	}

	/** 文学经典类 **/
	public class LiteraryClassic implements IRead {
		public void read() {
			System.out.println("文学经典阅读,滋润自己的内心心灵");
		}
	}

	/** 小说类 **/
	class Novel implements IRead {
		/** 阅读小说 **/
		public void read() {
			System.out.println("阅读小说，放松自己");
		}
	}

	/** 小明类 **/
	class XiaoMing extends IReader {
	}
	
	@Test
	public void dependenceInversion (String[] args) {
		XiaoMing xiaoming = new XiaoMing();
		IRead literaryClassic = new LiteraryClassic();
		xiaoming.read(literaryClassic);

		IRead novel = new Novel();
		xiaoming.read(novel);
	}
}
