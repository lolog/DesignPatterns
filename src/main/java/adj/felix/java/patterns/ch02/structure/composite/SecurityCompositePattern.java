package adj.felix.java.patterns.ch02.structure.composite;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 * ~~~~ <b>组合模式之透明方式</b> ~~~~
 * Composite声明所有的用来管理子类对象的方法。
 * 角色
 *   (1) Component(抽象构件)：规定参加组合对象的接口, 规范共有的接口及默认行为, 但不提供取得和管理下层组件的接口方法。
 *   (2) Left(树叶构件)：提供实现Component的原始行。
 *   (3) Composite(树枝构件)：提供管理和取得下层构件的行为方法, 以及提供实现Component的原始行为。
 * </pre>
 * @author adolf.felix
 */
public class SecurityCompositePattern {
	/**
	 * Component(抽象构件), 规定参加组合对象的接口, 规范共有的接口及默认行为, 但不提供取得和管理下层组件的接口方法。
	 */
	interface Root {
		/** 文件详情 **/
		public String getDescribe();
	}
	
	/**
	 * Left(树叶构件), 提供实现Component的原始行。
	 */
	static class File implements Root {
		private String describe;
		
		public File(String describe) {
			this.describe = describe;
		}

		@Override
		public String getDescribe() {
			return describe;
		}
		
		@Override
		public String toString() {
			return describe;
		}
	}
	/**
	 * Composite(树枝构件), 提供管理和取得下层构件的行为方法, 以及提供实现Component的原始行为。
	 */
	static class Folder implements Root {
		private String describe;
		private Set<Root> childrens = new HashSet<Root>(); 
		
		public Folder(String describe) {
			this.describe = describe;
		}
		
		public boolean add(Root root) {
			childrens.add(root);
			return true;
		}

		public boolean remove(Root root) {
			childrens.remove(root);
			return true;
		}

		public Set<Root> getChilds() {
			return childrens;
		}
		
		@Override
		public String getDescribe() {
			return describe;
		}
		
		@Override
		public String toString() {
			return describe;
		}
	}
	
	public static void main(String[] args) {
		Folder c = new Folder("C:/"); // C盘
		Folder d = new Folder("D:/"); // D盘
		
		// C盘下级目录
		Folder programFiles = new Folder("Program Files");
		Folder programFilesX86 = new Folder("Program Files(x86)");
		
		// C盘下弟三级文件及目录
		Folder adobeSoft = new Folder("Adobe Soft");
		File templateFile = new File("Template.java");
		
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
		if (root instanceof File) {
			return;
		}
		
		Folder folder = (Folder) root;
		for (Root dir: folder.getChilds()) {
			tab = "  " + prefix;
			if (dir instanceof Folder) {
				listDecribe(dir, tab, "      " + prefix);
			}
			else {
				System.out.println(tab + dir.getDescribe());
			}
		}
	}
}