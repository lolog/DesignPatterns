package adj.felix.java.patterns.ch03.behavioral.iterator;

import java.util.Arrays;

/**
 * <pre>
 * ~~~~ <b>迭代器模式</b> ~~~
 * 亦游标(Cursor)模式
 * 01. 作用
 *     提供一种方法顺序访问聚合对象(如List、Set)内的每个元素, 而又不暴露聚合对象的内部实现。 
 * 02. 问题
 *     (01) 如果将遍历方法封装在聚合中, 那对于聚合类来说就承担的功能过多。 即, 聚合类不仅要维护自身内部数据元素, 还要对外提供遍历的接口方法。
 *     (02) 如果不提供遍历方法, 而让外部实现, 又会让聚合类的内部细节暴露无遗。
 *     (03) 因此迭代器模式应运而生, 将具体迭代器实现为聚合的内部类, 既不暴露聚合类的实现细节, 又不让聚合类承担过多功能。
 * 03. 组成结构
 *     (01) 抽象迭代器(Iterator)
 *          定义遍历元素的接口。
 *     (02) 具体迭代器(ConcreteIterator)
 *     (03) 抽象聚合(Aggregate)
 *          提供创建具体迭代器的接口。
 *     (04) 具体聚合(ConcreteAggregate)
 * 04. 优点
 *     支持以不同的方式去遍历一个容器对象, 也可以有多个遍历, 弱化了容器类与遍历算法之间的关系。
 * 05. 缺点
 *     对类文件的增加。
 * </pre>
 * @author adolf felix
 */
public class IteratorPattern {
	/** 抽象迭代器(Iterator) **/
	interface Iterator<T> {
		public boolean hasNext();
		public T next();
	}
	/** 抽象聚合(Aggregate) **/
	interface List<T> {
		public boolean add(T obj);
		public T remove(T obj);
		public Iterator<T> iterator(); 
	}
	
	static class ArrayList<T> implements List<T> {
		// 数据容量
		private int capacity = 0;
		// 最小容量
		private int minCapacity = 2;
		
		// 不需要序列化
		transient Object[] elements = new Object[minCapacity];
		
		@Override
		public boolean add(T obj) {
			if(capacity > minCapacity || capacity >= elements.length) {
				elements = Arrays.copyOf(elements, capacity + 1);
			}
			elements[capacity] = obj;
			capacity++;
			
			return true;
		}

		@Override
		public T remove(T obj) {
			int index = -1;
			for(Object element: elements) {
				if (element == obj) {
					index += 2;
					break;
				}
			}
			if(index > -1) {
				System.arraycopy(elements, index+1, elements, index, capacity - index - 1);
				elements[--capacity] = null; //gc
				return obj;
			}
			
			return null;
		}

		@Override
		public Iterator<T> iterator() {
			return new ArrayIterator();
		}
		
		class ArrayIterator implements Iterator<T> {
			// 位置
			private int cursor;
			
			@Override
			public boolean hasNext() {
				return (cursor < capacity);
			}

			@SuppressWarnings("unchecked")
			@Override
			public T next() {
				return (T) elements[cursor++];
			}
		}
	}
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer> ();
		list.add(0);
		list.add(1);
		list.add(2);
		Iterator<Integer> iterator = list.iterator();
		System.out.println("++++++++++++++++++ add elements ++++++++++++++++++");
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		// 移除
		System.out.println("\n++++++++++++++++++ remove element ++++++++++++++++++");
		list.remove(1);
		iterator = list.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
