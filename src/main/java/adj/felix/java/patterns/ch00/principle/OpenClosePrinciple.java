package adj.felix.java.patterns.ch00.principle;

import org.junit.Test;

/**
 * 开闭原则
 * @author adolf felix
 */
public class OpenClosePrinciple {
	interface IBook {
		/** 获取书名 **/
		public String getName();
		/** 书籍的作者 **/
		public String getAuthor();
		/** 价格 **/
		public double getPrice();
	}

	/***
	 * <pre>
	 *  问题：由于各种原因,要打折来销售书籍,那么再不违反"开闭原则"条件下应如何处理一个需求变化呢？
	 *  问题分析：
	 *  	01.修改接口 ,如果IBook接口新增getOffPrice()方法,那么NovelBook将实现此方法,违反"开闭原则"的修改关闭规则。
	 *  	02.修改实现类,修改NovelBook类,直接在getPrice()方法做打折处理,那么如果需要获取打折前的价格呢?
	 *      03.通过扩展实现变化,通过新增一个子类,覆写getPrice方法,如{@link OffNovelBook}。或者,通过实现扩展其他接口,并且继承实现,如{@link NovelDiscountBook}。
	 * </pre>
	 * @author adolf felix
	 */
	class NovelBook implements IBook {
		private String name;
		private double price;
		private String author;
		public NovelBook(String name, int price, String author) {
			this.name = name;
			this.price = price;
			this.author = author;
		}
		public String getName() {
			return this.name;
		}
		public String getAuthor() {
			return this.author;
		}
		public double getPrice() {
			return this.price;
		}
	}
	
	class OffNovelBook extends NovelBook {
		public OffNovelBook(String name, int price, String author) {
			super(name, price, author);
		}
		public double getPrice() {
			if (super.price > 40) {
				return super.price * 0.8;
			} else {
				return super.price * 0.9;
			}
		}
	}
	
	interface IDiscountBook extends IBook {
		/** 折扣价格 **/
		public double getOffPrice();
	}
	
	class NovelDiscountBook extends NovelBook implements IDiscountBook {
		public NovelDiscountBook(String name, int price, String author) {
			super(name, price, author);
		}
		public double getOffPrice() {
			if (super.price > 40) {
				return super.price * 0.8;
			} else {
				return super.price * 0.9;
			}
		}
	}

	@Test
	public void openClose() {
		IBook novel = new NovelBook("笑傲江湖", 100, "金庸");
		System.out.println("{书籍名字=" + novel.getName() + ", 书籍作者=" + novel.getAuthor() + ", 书籍价格=" + novel.getPrice() + "}");
	}
	
	@Test
	public void overrideMethodOpenClose() {
		IBook novel = new OffNovelBook("笑傲江湖", 100, "金庸");
		System.out.println("{书籍名字=" + novel.getName() + ", 书籍作者=" + novel.getAuthor() + ", 书籍价格=" + novel.getPrice() + "}");
	}
	
	@Test
	public void expandMethodOpenClose() {
		IDiscountBook novel = new NovelDiscountBook("笑傲江湖", 100, "金庸");
		System.out.println("{书籍名字=" + novel.getName() + ", 书籍作者=" + novel.getAuthor() + ", 书籍价格=" + novel.getOffPrice() + "}");
	}
}
