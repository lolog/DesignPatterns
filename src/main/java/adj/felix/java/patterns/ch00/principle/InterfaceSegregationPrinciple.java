package adj.felix.java.patterns.ch00.principle;

import org.junit.Test;

public class InterfaceSegregationPrinciple {
	/** 美女接口 **/
	interface IPettyGirl {
		/** 漂亮 **/
		public void looking();
		/** 身材 **/
		public void figure();
		/** 气质 **/
		public void temperament();
	}

	/** 定义美女=漂亮+身材+气质 **/
	class PettyGirl implements IPettyGirl {
		private String name;
		public PettyGirl(String name) {
			this.name = name;
		}
		public void looking() {
			System.out.println(name + ", 特别漂亮");
		}
		public void figure() {
			System.out.println(name + ", 身材特别好");
		}
		public void temperament() {
			System.out.println(name + ", 非常有气质");
		}
	}

	/**
	 * <pre>
	 * 问题：随着时代的变化,审美观发生变化,美女的定义也发生变化,如分为：气质美女,极致美女,那么需要怎么处理呢？
	 * 分析：接口{@link IPettyGirl}设计过于庞大,容纳了很多可变的因素,那么根据接口分离原则,应该细化接口。 如：{@link IGoodBodyGirl}
	 * </pre>
	 */
	@Test
	public void notISP() {
		IPettyGirl netty = new PettyGirl("Netty");
		netty.looking();
		netty.figure();
		netty.temperament();
	}
	
	/** 标准美女 **/
	interface IBodyGirl {
		/** 面孔 **/
		public void looking();
		/** 身材 **/
		public void figure();
	}
	
	/** 气质美女 **/
	interface ITemperamentGirl {
		/** 气质 **/
		public void temperament();
	}
	
	interface _IPettyGirl extends IBodyGirl, ITemperamentGirl{
		
	}
	
	/** 气质美女 **/
	class GoodTemperamentGirl implements _IPettyGirl {
		private String name;
		public GoodTemperamentGirl(String name) {
			this.name = name;
		}
		public void looking() {
			System.out.println(name + ",颜值一般");
		}
		public void figure() {
			System.out.println(name + ",身材一般");
		}
		public void temperament() {
			System.out.println(name + ",非常有气质");
		}
	}
	
	@Test
	public void isISP() {
		_IPettyGirl netty = new GoodTemperamentGirl("Netty");
		
		ITemperamentGirl temperamentGirl = netty;
		temperamentGirl.temperament();
		
		IBodyGirl bodyGirl = netty;
		bodyGirl.looking();
		bodyGirl.figure();
	}
}
