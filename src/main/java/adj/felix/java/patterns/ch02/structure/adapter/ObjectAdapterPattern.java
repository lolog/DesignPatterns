package adj.felix.java.patterns.ch02.structure.adapter;


/**
 * <pre>
 * ~~~~ <b>对象的适配器模式</b> ~~~~
 * TencentCompany(Client)的面试要求    英语、汉语
 * Chinese(源)的主要语言                                      汉语
 * 最终目标(Target)                TencentJob(工作)
 * 适配器(Adapter)                 Adapter
 * </pre>
 * @author adolf.felix
 */
public class ObjectAdapterPattern {
	static class Person {
		private String name;
		private String sex;
		private int age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
	}
	
	/** 中国主要语言是：汉语 **/
	static class Chinese extends Person {
		public void speakChinese () {
			System.out.println("Speak Chinese");
		}
	}
	/** 
	 * <b>目标接口</b>。<br>
	 * 目标Target, 工作要求：汉语、英语。<br>
	 * 但是, Chinese类只会汉语, 现在需要Chinese会英语, 那么就需要一个适配器Adapter。
	 */
	static interface TencentJob {
		public abstract void speakChinese();
		public abstract void speakEnglish();
	}
	
	/**
	 * <b>适配器</b>。<br>
	 * 通过Adapter将Chinese适配为TencentJob, 才能满足Tencent Company的要求。
	 */
	static class Adapter implements TencentJob {
		private Chinese chinese;
		
		public Adapter(Chinese chinese) {
			this.chinese = chinese;
		}
		
		@Override
		public void speakChinese() {
			chinese.speakChinese();
		}
		
		@Override
		public void speakEnglish() {
			System.out.println("Speak English");
		}
	}
	
	/**
	 * <b>客户端</b>。<br>
	 * Tencent Company面试要求：汉语、英语。
	 */
	static class TencentCompany {
		private TencentJob job;
		
		public TencentCompany(Chinese chinese) {
			this.job = new Adapter(chinese);
		}
		
		public void jobSkills() {
			job.speakChinese();
			job.speakEnglish();
		}
	}
	
	
	public static void main(String[] args) {
		// 应聘者
		Chinese chinese = new Chinese();
		
		TencentCompany client = new TencentCompany(chinese);
		client.jobSkills();
	}
}
