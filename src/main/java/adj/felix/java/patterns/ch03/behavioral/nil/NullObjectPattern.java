package adj.felix.java.patterns.ch03.behavioral.nil;

/**
 * <pre>
 * ~~~~ <b>空对象模式</b> ~~~
 * 01.定义
 *    用一个空对象取代 NULL, 减少对实例的检查。空对象可以提供默认的行为。
 * 02.组成结构
 *   (01)AbstractClass(抽象对象)
 *   (02)RealClass(具体对象)
 *   (03)NilClass(空对象)
 * 02.优点
 *   (01)加强系统的稳固性, 有效地防止空指针报错对整个系统的影响, 使系统更加稳定。
 *   (02)能实现对空对象情况的定制化的控制, 能够掌握处理空对象的主动权。
 *   (03)它并不依靠Client来保证整个系统的稳定运行。
 *   (04)通过isNil对==null的替换, 显得更加优雅, 更加易懂。
 * </pre>
 * @author adolf felix
 */
public class NullObjectPattern {
	/** AbstractClass(抽象对象) **/
	static abstract class AbstractParser {
		public abstract void parse();
		public abstract boolean isNil();
	}
	/** RealClass(具体对象) **/
	static class NokiaCmParser extends AbstractParser {
		@Override
		public void parse() {
			System.out.println("Nokia Cm");
		}
		@Override
		public boolean isNil() {
			return false;
		}
	}
	static class NilParser extends AbstractParser {
		@Override
		public void parse() {
		}
		@Override
		public boolean isNil() {
			return true;
		}
	}
	
	public static void main(String[] args) {
		AbstractParser parser = new NokiaCmParser();
		System.out.println("Step01. isNil = " + parser.isNil());
		
		parser = new NilParser();
		System.out.println("Step02. isNil = " + parser.isNil());
	}
}
