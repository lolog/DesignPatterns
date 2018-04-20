package adj.felix.java.patterns.ch00.principle;

import org.junit.Test;

/**
 * 里氏代换原则
 * @author adolf felix
 */
public class LiskovSubstitutionPrinciple {
	abstract class Gun {
		protected void config(Wp wp) {
			System.out.println("父类,输入参数");
		}
		protected Basic getConfig() {
			System.out.println("父类,返回类型");
			return null;
		}
		protected abstract void shoot();
	}

	class Rifle extends Gun {
		public void shoot() {
			System.out.println("步枪射击");
		}
	}

	class Cachine extends Gun {
		public void shoot() {
			System.out.println("机枪射击");
		}
	}
	
	/** 子类可以添加自己的个性方法  **/
	public class AUG extends Gun {
		public void zoomOut() {
			System.out.println("通过放大镜观察");
		}
		public void shoot() {
			System.out.println("AUG射击");
		}

	}
	
	public class SubmachineGun extends Gun {
		/**覆盖或实现父类的方法时,输入参数要比父类更宽松 **/
		protected <T extends Basic> void config(T wp) {
			System.out.println("子类,输入参数");
		}
		/** 覆写或实现父类的方法时,返回类型要比父类更严格 **/
		@Override
		protected Wp getConfig() {
			System.out.println("子类,返回类型严格");
			return null;
		}
		@Override
		protected void shoot() {
		}
	}

	/** 子类必须完全实现父类的方法,但是不能覆盖父类的非抽象方法。 */
	@Test
	public void allImplementLSP() {
		Gun rifle = new Rifle();
		Gun cachine = new Cachine();

		rifle.shoot();
		cachine.shoot();
	}
	
	/** 子类必须完全实现父类的方法,但是不能覆盖父类的非抽象方法。 */
	@Test
	public void personaliseLSP() {
		AUG aug = new AUG();
		Gun gun = aug;
		
		aug.zoomOut();
		gun.shoot();
	}
	
	/** 输入参数要比父类更宽松,返回类型要比父类更严格。 */
	@Test
	public void overrideLSP() {
		SubmachineGun gun = new SubmachineGun();
		
		gun.config(new Wp());
		gun.getConfig();
		gun.shoot();
	}
}

class Basic {
	public String name;
	public Integer age;
}
class Wp extends Basic {
	public double zoom;
}