package adj.felix.java.patterns.ch03.behavioral.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * ~~~~ <b>责任链模式</b> ~~~~
 * 01. 定义
 *     避免请求发送者与接收者耦合在一起, 让多个对象都有可能接收请求, 将这些对象连接成一条链, 并且沿着这条链传递请求, 直到有对象处理它为止。
 * 02. 目的
 *     主要解决职责链上的处理者负责处理请求, 客户只需要将请求发送到职责链上即可, 无须关心请求的处理细节和请求的传递, 所以职责链将请求的发送者和请求的处理者解耦了。
 * 03. 组成结构
 *     (01) Handler(抽象处理角色)
 *     (02) ConcreteHandler(具体处理角色)
 * 03. 使用场景
 *     (01) 有多个对象可以处理同一个请求, 具体哪个对象处理该请求由运行时刻自动确定。
 *     (02) 在不明确指定接收者的情况下, 向多个对象中的一个提交一个请求。
 *     (03) 可动态指定一组对象处理请求。
 * 04. 优点
 *     (01) 降低耦合度
 *          将请求的发送者和接收者解耦。
 *     (02) 简化了对象
 *          使得对象不需要知道链的结构。
 *     (03) 增强给对象指派职责的灵活性。
 *          通过改变链内的成员或者调动它们的次序, 允许动态地新增或者删除责任。
 *     (04) 增加新的请求处理类很方便。
 * 05. 缺点
 *     (01) 不能保证请求一定被接收。
 *     (02) 系统性能将受到一定影响, 而且在进行代码调试时不太方便, 可能会造成循环调用。
 *     (03) 可能不容易观察运行时的特征, 有碍于除错。
 * </pre>
 * @author adolf felix
 */
public class ChainofResponsibilityPattern {
	/** 请求消息 **/
	static class Request {
		public String content;
		public Request(String content) {
			this.content = content;
		}
	}
	/** 响应消息 **/
	static class Response {
		public String content;
		public Response(String content) {
			this.content = content;
		}
	}
	
	/** 抽象处理角色 **/
	interface Filter {
		public void doFilter(Request request, Response response, FilterChain chain);
	}
	/** 抽象处理角色 **/
	interface FilterChain {
		public void doFilter(Request request, Response response);
	}
	/** 具体处理角色 **/
	static class HtmlFilter implements Filter {
		@Override
		public void doFilter(Request request, Response response, FilterChain chain) {
			request.content = request.content.replaceAll("<", "[").replaceAll(">", "]");
			
			chain.doFilter(request, response);
		}
	}
	static class SqlFilter implements Filter {
		@Override
		public void doFilter(Request request, Response response, FilterChain chain) {
			// SQL注入攻击
			request.content = request.content.replaceAll("-", "");
			
			chain.doFilter(request, response);
		}
	}
	static class ApplicationFilterChain implements FilterChain {
		private List<Filter> filters = new ArrayList<Filter>();
		private int position = 0;
		
		/** Servlet **/
		private Servlet servlet = new Servlet();
		
		/**
		 * 可以通过配置文件配置, 即web.xml
		 * @param filter 过滤器
		 */
		public void addFilter (Filter filter) {
			filters.add(filter);
		}
		
		@Override
		public void doFilter(Request request, Response response) {
			if (position < filters.size()) {
				filters.get(position++).doFilter(request, response, this);
			}
			else {
				servlet.service(request, response);
			}
		}
	}
	
	static class Servlet {
		public void service(Request request, Response response) {
			System.out.println("---Request Content----");
			System.out.println(request.content);
		}
	}
	
	public static void main(String[] args) {
		Request request = new Request("<b>hello word, select * from user where user = 'user'-----</b>");
        Response response = new Response("response");
        
        Filter htmlFilter = new HtmlFilter();
        Filter sqlFilter = new SqlFilter();
        ApplicationFilterChain chain = new ApplicationFilterChain();
        
        chain.addFilter(sqlFilter);
		chain.addFilter(htmlFilter);
		
		// 执行过滤
		chain.doFilter(request, response);
	}
}