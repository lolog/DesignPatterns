package adj.felix.java.patterns.ch02.structure.composite;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 * ~~~~ <b>组合模式之透明方式</b> ~~~~
 * Component声明用来管理子类对象的所有方法, 包括add()、remove()以及getChild()方法。
 * 角色
 *   (1) Component(抽象构件)：规定参加组合对象的接口, 规范共有的接口及默认行为。提供管理和取得下层组件的方法接口, 包括add()、remove()和getChild()方法。
 *   (2) Left(树叶构件)：提供实现Component的原始行, 并给出管理和取得构件的add()、remove()和getChild()方法实现。
 *   (3) Composite(树枝构件)：提供管理和取得下层构件的行为方法, 以及组件的原始行为。
 * </pre>
 * @author adolf.felix
 */
public class TransparentCompositePattern {
	/**
	 * Component(抽象构件), 规定参加组合对象的接口, 规范共有的接口及默认行为。提供管理和取得下层组件的方法接口, 包括add()、remove()和getChild()方法。
	 */
	interface Root {
		/**添加节点**/
		public boolean add(Root root);
		/** 移除节点 **/
		public boolean remove(Root root);
		/** 获取下一级子节点 **/
		public Set<Root> getChilds();
		
		/** 描述 **/
		public String getDescribe();
	}
	
	/**
	 * Left(树叶构件), 提供实现Component的原始行, 并给出管理和取得构件的add()、remove()和getChild()方法实现。
	 */
	static class File implements Root {
		private String describe;
		
		public File(String describe) {
			this.describe = describe;
		}
		
		@Override
		public boolean add(Root root) {
			return false;
		}

		@Override
		public boolean remove(Root root) {
			return false;
		}

		@Override
		public Set<Root> getChilds() {
			return null;
		}
		
		@Override
		public String getDescribe() {
			return describe;
		}
	}
	/**
	 * Composite(树枝构件), 提供管理和取得下层构件的行为方法, 以及组件的原始行为。
	 */
	static class Folder implements Root {
		private String describe;
		private Set<Root> childrens = new HashSet<Root>(); 
		
		public Folder(String describe) {
			this.describe = describe;
		}
		
		@Override
		public boolean add(Root root) {
			childrens.add(root);
			return true;
		}

		@Override
		public boolean remove(Root root) {
			childrens.remove(root);
			return true;
		}

		@Override
		public Set<Root> getChilds() {
			return childrens;
		}
		
		@Override
		public String getDescribe() {
			return describe;
		}
	}
	
	public static void main(String[] args) {
		Root c = new Folder("C:/"); // C盘
		Root d = new Folder("d:/"); // D盘
		
		// C盘下级目录
		Root programFiles = new Folder("Program Files");
		Root programFilesX86 = new Folder("Program Files(x86)");
		
		// C盘下弟三级文件及目录
		Root adobeSoft = new Folder("Adobe Soft");
		Root templateFile = new File("Template.java");
		
		c.add(programFiles);
		programFiles.add(adobeSoft);
		adobeSoft.add(templateFile);
		c.add(programFilesX86);
		
		// C盘
		String tab  = "";
		String prefix = "|-- ";
		listDecribe(c, tab, prefix);
		listDecribe(d, tab, prefix);
	}
	
	private static void listDecribe (Root root, String tab, String prefix) {
		System.out.println(tab + root.getDescribe());
		for (Root dir: root.getChilds()) {
			tab = "  " + prefix;
			if (dir.getChilds() == null) {
				System.out.println(prefix + dir.getDescribe());
			}
			else {
				listDecribe(dir, tab, "      " + prefix);
			}
		}
	}
}