package adj.felix.java.patterns.ch02.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * ~~~~ <b>过滤器模式</b>
 * 亦标准模式(Criteria Pattern), 允许开发人员使用不同的标准来过滤一组对象, 通过逻辑运算以解耦的方式把它们连接起来。
 * </pre>
 * 
 * @author adolf felix
 */
public class FilterPattern {
	/** 对象 **/
	static class Person {
		public String name; // 姓名
		public String gender; // 性别
		public String marital; // 婚姻情况

		public Person(String name, String gender, String marital) {
			this.name = name;
			this.gender = gender;
			this.marital = marital;
		}

		@Override
		public String toString() {
			return "{" + name + ", " + gender + ", " + marital + "}";
		}
	}

	/** 过滤器接口 **/
	public interface Filter {
		List<Person> filter(List<Person> persons);
	}

	/** Male Filter **/
	static class MaleFilter implements Filter {
		@Override
		public List<Person> filter(List<Person> persons) {
			List<Person> result = new ArrayList<>();

			for (Person person : persons) {
				if ("MALE".equalsIgnoreCase(person.gender)) {
					result.add(person);
				}
			}

			return result;
		}
	}

	/** Female Filter **/
	static class FemaleFilter implements Filter {
		@Override
		public List<Person> filter(List<Person> persons) {
			List<Person> result = new ArrayList<>();

			for (Person person : persons) {
				if ("MALE".equalsIgnoreCase(person.gender)) {
					result.add(person);
				}
			}

			return result;
		}
	}

	/** Singl Filter **/
	static class SingleFilter implements Filter {
		@Override
		public List<Person> filter(List<Person> persons) {
			List<Person> result = new ArrayList<>();

			for (Person person : persons) {
				if ("SINGLE".equalsIgnoreCase(person.marital)) {
					result.add(person);
				}
			}

			return result;
		}
	}

	/** Married Filter **/
	static class MarriedFilter implements Filter {
		@Override
		public List<Person> filter(List<Person> persons) {
			List<Person> result = new ArrayList<>();

			for (Person person : persons) {
				if ("MARRIED".equalsIgnoreCase(person.marital)) {
					result.add(person);
				}
			}

			return result;
		}
	}

	/** And Filter **/
	static class FilterAnd implements Filter {
		private Filter filter;
		private Filter otherFilter;

		public FilterAnd(Filter filter, Filter otherFilter) {
			this.filter = filter;
			this.otherFilter = otherFilter;
		}

		@Override
		public List<Person> filter(List<Person> persons) {
			List<Person> tmpList = filter.filter(persons);
			return otherFilter.filter(tmpList);
		}
	}

	/** Or Filter **/
	static class FilterOr implements Filter {
		private Filter filter;
		private Filter otherFilter;

		public FilterOr(Filter filter, Filter otherFilter) {
			this.filter = filter;
			this.otherFilter = otherFilter;
		}

		@Override
		public List<Person> filter(List<Person> persons) {
			List<Person> filterList = filter.filter(persons);
			List<Person> otherFilterList = otherFilter.filter(persons);

			for (Person person : otherFilterList) {
				if (!filterList.contains(person)) {
					filterList.add(person);
				}
			}

			return filterList;
		}
	}

	public static void main(String[] args) {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Felix", "Female", "Married"));
		persons.add(new Person("Susan", "Male", "Married"));
		persons.add(new Person("Alpha", "Male", "Single"));
		persons.add(new Person("Belta", "Female", "Married"));
		persons.add(new Person("Marry", "Male", "Single"));
		persons.add(new Person("Alray", "Female", "Single"));
		persons.add(new Person("Simmy", "Male", "Single"));
		persons.add(new Person("Altye", "Male", "Married"));
		persons.add(new Person("Berry", "Female", "Single"));
		persons.add(new Person("Joson", "Female", "Single"));

		System.out.println("---------------------所有男性---------------------");
		List<Person> maleList = new MaleFilter().filter(persons);
		printList(maleList);

		System.out.println("\n---------------------所有单身---------------------");
		List<Person> singleList = new SingleFilter().filter(persons);
		printList(singleList);
		// 打印出所有已婚女性的信息
		System.out.println("\n--------------------所有已婚女性-------------------");
		List<Person> marriedFemaleList = new FilterAnd(new MarriedFilter(), new FemaleFilter()).filter(persons);
		printList(marriedFemaleList);

		System.out.println("\n-------------------所有单身或女性------------------");
		List<Person> singleOrFemaleList = new FilterOr(new SingleFilter(), new FemaleFilter()).filter(persons);
		printList(singleOrFemaleList);
	}

	/** 打印列表中的数据信息 **/
	private static void printList(List<Person> list) {
		for (Person person : list) {
			System.out.println(person.toString());
		}
	}
}
