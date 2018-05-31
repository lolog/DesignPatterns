package adj.felix.java.patterns.ch02.structure.facade;

/**
 * <pre>
 * ~~~~ <b>外观模式</b> ~~~~
 * 又称为Facade模式、门面模式。
 * 01. 定义
 *     为子系统中的一组接口提供一个统一接口。为子系统提供一个更高层次、更简单的接口, 从而降低了子系统的复杂度和依赖, 是的子系统更易于使用和管理。
 * 02. 作用
 *     (1) 实现客户类与子系统类的松耦合
 *     (2) 降低原有系统的复杂度
 *     (3) 提高了客户端使用的便捷性, 使得客户端无须关心子系统的工作细节, 通过外观角色即可调用相关功能。
 * 03. 体现
 *     (1) 引入外观角色之后，用户只需要与外观角色交互。
 *     (2) 用户与子系统之间的复杂逻辑关系由外观角色来实现。
 * 04. 角色
 *     (1) Facade(门面): 外观模式的核心。被客户端调用。
 *     (2) SystemX(子系统): 实现子系统的功能。
 *     (3) Client(客户): 调用facade完成需要得到的功能。
 * 05. 优缺点
 *     (1) 优点
 *         1) 降低了客户类与子系统类的耦合度, 实现了子系统与客户之间的松耦合关系。
 *            I. 只是提供了一个访问子系统的统一入口, 并不影响用户直接使用子系统类。
 *            II. 减少了与子系统的关联对象, 实现了子系统与客户之间的松耦合关系, 松耦合使得子系统的组件变化不会影响到它的客户。
 *         2) 外观模式对客户屏蔽了子系统组件, 从而简化了接口, 减少了客户处理的对象数目并使子系统的使用更加简单。
 *            I. 引入外观角色之后, 用户只需要与外观角色交互。
 *            II. 用户与子系统之间的复杂逻辑关系由外观角色来实现。
 *         3) 降低原有系统的复杂度和系统中的编译依赖性, 并简化了系统在不同平台之间的移植过程。
 *            因为编译一个子系统一般不需要编译所有其他的子系统。一个子系统的修改对其他子系统没有任何影响, 而且子系统内部变化也不会影响到外观对象。
 *     (2) 缺点
 *         1) 在不引入抽象外观类的情况下, 增加新的子系统可能需要修改外观类或客户端的源代码, 违背了"开闭原则"。
 *         2) 不能很好地限制客户使用子系统类, 如果对客户访问子系统类做太多的限制则减少了可变性和灵活性。
 * 06. 实用场景
 *     (1) 要为一个复杂的子系统对外提供一个简单的接口。
 *     (2) 提供子系统的独立性。
 *     (3) 客户程序与多个子系统之间存在很大的依赖性。
 *         引入外观类将子系统与客户以及其他子系统解耦, 可以提高子系统的独立性和可移植性。
 *     (4) 在层次化结构中, 可以使用外观模式定义系统中每一层的入口。
 *         层与层之间不直接产生联系, 而通过外观类建立联系, 降低层之间的耦合度。
 * 07. demo
 *     {@link FacadePattern.jpg}
 * </pre>
 * @author adolf.felix
 */
public class FacadePattern {
	/** CPU子系统类 **/
	static class CPU 
	{
	    public void start()
	    {
	        System.out.println("CPU is starting");
	    }
	    public void shutDown()
	    {
	    	System.out.println("CPU is shutDowning");
	    }
	}
	/** Disk子系统类 **/
	static class Disk 
	{
		public void start()
		{
			System.out.println("Disk is starting");
		}
		public void shutDown()
		{
			System.out.println("Disk is shutDowning");
		}
	}
	/** Memory子系统类 **/
	static class Memory 
	{
		public void start()
		{
			System.out.println("Memory is starting");
		}
		public void shutDown()
		{
			System.out.println("Memory is shutDowning");
		}
	}
	/** 门面类(核心) **/
	static class Computer
	{
	    private CPU cpu;
	    private Memory memory;
	    private Disk disk;
	    public Computer()
	    {
	        cpu = new CPU();
	        memory = new Memory();
	        disk = new Disk();
	    }
	    public void start()
	    {
	    	System.out.println("************  Computer start begin ************");
	        cpu.start();
	        disk.start();
	        memory.start();
	        System.out.println("************ Computer start End ************");
	    }
	    
	    public void shutDown()
	    {
	    	System.out.println("************ Computer shutDown begin ************");
	        cpu.shutDown();
	        disk.shutDown();
	        memory.shutDown();
	        System.out.println("************ Computer shutDown end ************");
	    }
	}
	
	/** 客户端类 **/
	public static void main(String[] args) {
		Computer computer = new Computer();
        computer.start();
        
        System.out.println();
        
        computer.shutDown();
	}
}
