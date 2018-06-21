package adj.felix.java.patterns.ch03.behavioral.template;

/**
 * <pre>
 * ~~~~ <b>模板模式</b> ~~~
 * 01. 定义
 *     定义一个操作中算法的框架, 而将一些步骤延迟到子类中, 使得子类可以不改变算法的结构即可重定义该算法中的某些特定步骤。
 * 02. 组成结构
 *     (01) AbstractClass(抽象模板)
 *          01) 基本操作(Primitive Operations)
 *              基本方法又可以分为三种: 抽象方法(Abstract Method)、具体方法(Concrete Method)和钩子方法(Hook Method)。
 *              抽象方法: 由抽象模板声明, 由具体子类实现。在Java语言里, 抽象方法以abstract关键字标示。
 *              具体方法: 由抽象模板声明并实现, 而子类并不实现或置换。
 *              钩子方法: 由抽象模板声明并实现, 而子类会加以扩展。通常抽象模板给出的实现是一个空实现, 作为方法的默认实现。
 *          02) 模板方法(Template Method)
 *              模板方法是定义在抽象类中的, 把基本操作方法组合在一起形成一个总算法或一个总行为的方法。
 *              抽象模板可以有任意多个模板方法, 而不限于一个。 每一个模板方法都可以调用任意多个具体方法。
 *     (02) ConcreteClass(具体模板)
 *          它是抽象类的子类，用于实现在父类中声明的抽象基本操作以完成子类特定算法的步骤，也可以覆盖在父类中已经实现的具体基本操作。
 * 03. 优点
 *     (01) 容易扩展
 *          一般来说, 抽象模板中的模版方法是不易发生改变的部分, 而抽象方法是容易反生变化的部分, 因此通过增加实现类一般可以很容易实现功能的扩展, 符合开闭原则。
 *     (02) 便于维护
 *          对于模版方法模式来说, 正是由于他们的主要逻辑相同, 才使用了模版方法, 假如不使用模版方法, 任由这些相同的代码散乱的分布在不同的类中, 维护起来是非常不方便的。
 *     (03) 比较灵活
 *          因为有钩子方法, 因此子类的实现也可以影响父类中主逻辑的运行。但是, 在灵活的同时, 由于子类影响到了父类, 违反了里氏替换原则, 也会给程序带来风险。这就对抽象类的设计有了更高的要求。
 * 04. 缺点
 *     按照设计习惯, 抽象模板负责声明最抽象、最一般的事物属性和方法, 实现类负责完成具体的事务属性和方法, 但是模板方式正好相反, 子类执行的结果影响了父类的结果, 会增加代码阅读的难度。
 * </pre>
 * @author adolf felix
 */
public class TemplatePattern {
	/** 抽象模板类 **/
	public abstract class AbstractTemplate {
		/** 模板方法 **/
		public void templateMethod() {
			abstractMethod();
			hookMethod();
			concreteMethod();
		}
		/** 抽象方法 **/
		public abstract void abstractMethod();
		
		/** 基本方法(已经实现) **/
		public void concreteMethod() {
			// 业务相关的代码
		}
		/** 钩子方法-可选实现 **/
		public void hookMethod() {
			
		}
	}
	
	/** 具体模板类 **/
	public class ConcreteTemplate extends AbstractTemplate{
	    @Override
	    public void abstractMethod() {
	        // 具体实现方法
	    }
	    @Override
	    public void hookMethod() {
	        // 钩子方法-可选实现
	    }
	}
}