package adj.felix.java.patterns.ch01.create.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * ~~~~ <b>原型模式之浅拷贝</b> ~~~~
 * 指被拷贝对象的所有变量都含有与原对象相同的值, 而所有对其他对象的引用仍然指向原来的对象。即, 浅拷贝不拷贝它引用的对象.
 * </pre>
 * @author adolf.felix
 */
public class ShallowCopyPrototypePattern {
	/**
	 * <pre>
	 * 实现Cloneable接口, 并且重写Object的clone方法。
	 * 注意, 能够实现拷贝的Java类必须实现一个Cloneable的标志接口, 表示这个Java类支持被复制; 否则将抛出CloneNotSupportedException异常。
	 * 角色：
	 *    (1) Object Prototype
	 *    (2) ShallowCopyPrototype Prototype实现类
	 *    (3) ShallowCopyPrototypePattern Client
	 * 补充, Object类clone方法的原理： 从内存中(具体来说是堆内存中)以二进制流的方式进行拷贝, 重新分配一个内存块, 因此拷贝过程构造函数不会被调用。
	 * </pre>
	 */
	static class ShallowCopyPrototype implements Cloneable {
		/** Object类clone方法的原理： 从内存中(具体来说是堆内存中)以二进制流的方式进行拷贝, 重新分配一个内存块, 因此拷贝过程构造函数不会被调用。**/
		public ShallowCopyPrototype() {
			System.out.println("Shallow Copy");
		}
		/** 非引用对象, 值与原来的对象相同 **/
		private String name;
		/** 引用对象, 浅拷贝时仍然指向原来的对象 **/
		private ArrayList<String> items = new ArrayList<String>();
		
		@Override
		protected ShallowCopyPrototype clone() throws CloneNotSupportedException {
			return (ShallowCopyPrototype) super.clone();
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		public void addIntem(String item) {
			items.add(item);
		}
		public List<String> getItems() {
			return items;
		}
		
		@Override
		public String toString() {
			return "{name=" + name + ", items=" + items +"}";
		}
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		// 输出： Shallow Copy 
		ShallowCopyPrototype shallowCopy = new ShallowCopyPrototype();
		// 原始值
		shallowCopy.setName("Orign");
		shallowCopy.addIntem("Orign");
		
		// 拷贝的对象, 不会调用构造器, 只是从堆内存中进行拷贝
		ShallowCopyPrototype shallowCopyNew = shallowCopy.clone();
		shallowCopyNew.setName("New");
		shallowCopyNew.addIntem("New");
		
		// 输出：{name=Orign, items=[Orign, New]}
		System.out.println(shallowCopy);
	}
}