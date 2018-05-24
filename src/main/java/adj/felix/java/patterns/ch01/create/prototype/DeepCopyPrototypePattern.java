package adj.felix.java.patterns.ch01.create.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * ~~~~ <b>原型模式之深拷贝</b> ~~~~
 *  1. 概念
 *     指被拷贝对象的所有变量都含有与原对象相同的值, 除引用对象外。 引用对象变量指向被拷贝过的新对象, 而不再是原有的那些引用对象。
 *  2. 实现方式
 *     1) 序列化
 *     2) Cloneable接口实现
 * </pre>
 * @author adolf.felix
 */
public class DeepCopyPrototypePattern {
	/**
	 * <pre>
	 * ~~~~ <b>通过序列化方式实现深拷贝</b> ~~~~
	 * 能够实现序列化的类, 必须实现Serializable接口。
	 * </pre>
	 * @author adolf.felix
	 */
	static class DeepCopySerializablePrototype implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private String detail;
		/** 引用对象 **/
		private List<String> items = new ArrayList<String>();
		
		/** 序列化方式实现的深度拷贝, 反序列化时, 构造器不会被调用。 **/
		public DeepCopySerializablePrototype() {
			System.out.println("DeepCopyPrototype");
		}
		
		@Override
		protected DeepCopySerializablePrototype clone() throws CloneNotSupportedException {
			try {
				// 输出流写入对象
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(out);
				oos.writeObject(this);
				
				// 输入流读取对象
				ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
				ObjectInputStream ois = new ObjectInputStream(in);
				
				return (DeepCopySerializablePrototype) ois.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public void setDetail(String detail) {
			this.detail = detail;
		}
		public String getDetail() {
			return detail;
		}
		public void setItems(List<String> items) {
			this.items = items;
		}
		public List<String> getItems() {
			return items;
		}
		public void addItem(String item) {
			this.items.add(item);
		}
		@Override
		public String toString() {
			return "{detail=" + detail + ", items=" + items + "}";
		}
	}
	
	/**
	 * <pre>
	 * ~~~~ <b>通过Cloneable方式实现深拷贝</b> ~~~~
	 * 需要通过JVM方式拷贝, 那么必须实现Cloneable接口, 以及Object的clone方法。
	 * </pre>
	 * @author adolf.felix
	 */
	static class DeepCopyCloneablePrototype implements Cloneable {
		private String detail;
		/** 引用对象 **/
		private List<String> items = new ArrayList<String>();
		
		/** Object类clone方法的原理： 从内存中(具体来说是堆内存中)以二进制流的方式进行拷贝, 重新分配一个内存块, 因此拷贝过程构造函数不会被调用。 **/
		public DeepCopyCloneablePrototype() {
			System.out.println("DeepCopyPrototype");
		}
		
		@Override
		@SuppressWarnings("unchecked")
		protected DeepCopyCloneablePrototype clone() throws CloneNotSupportedException {
			DeepCopyCloneablePrototype deepCopy = (DeepCopyCloneablePrototype) super.clone();
			// 克隆items, 只能通过手动才能实现克隆
			deepCopy.items = (List<String>) ((ArrayList<String>) this.items).clone();
			return deepCopy;
		}
		
		public void setDetail(String detail) {
			this.detail = detail;
		}
		public String getDetail() {
			return detail;
		}
		public void setItems(List<String> items) {
			this.items = items;
		}
		public List<String> getItems() {
			return items;
		}
		public void addItem(String item) {
			this.items.add(item);
		}
		@Override
		public String toString() {
			return "{detail=" + detail + ", items=" + items + "}";
		}
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		System.out.println("******** Serializable实现克隆的方式  *************** ");
		// 原始对象, 输出：DeepCopyPrototype
		DeepCopySerializablePrototype deepCopySerializable = new DeepCopySerializablePrototype();
		deepCopySerializable.addItem("Orign");
		deepCopySerializable.setDetail("Orign");
		
		// 深拷贝对象
		DeepCopySerializablePrototype deepCopySerializableNew = deepCopySerializable.clone();
		deepCopySerializableNew.setDetail("New");
		deepCopySerializableNew.addItem("New");
		
		// 输出：{detail=Orign, items=[Orign]}
		System.out.println(deepCopySerializable);
		
		
		System.out.println("******** Cloneable实现克隆的方式  *************** ");
		DeepCopyCloneablePrototype deepCopyCloneable = new DeepCopyCloneablePrototype();
		deepCopyCloneable.addItem("Orign");
		deepCopyCloneable.setDetail("Orign");
		
		// 深拷贝对象
		DeepCopyCloneablePrototype deepCopyCloneableNew = deepCopyCloneable.clone();
		deepCopyCloneableNew.setDetail("New");
		deepCopyCloneableNew.addItem("New");
		
		// 输出：{detail=Orign, items=[Orign]}
		System.out.println(deepCopyCloneable);
	}
}
