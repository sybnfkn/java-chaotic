package com.zhangyan.proxy;

public class ProxyPatternDemo1 {
	
	public static void main(String[] args) {
		Subject subject = new ConcreteSubject();
		Subject proxy = new Proxy(subject);
		proxy.request();
	}
	
	public interface Subject {
		
		void request();
		
	}

	public static class ConcreteSubject implements Subject{
		
		public void request() {
			System.out.println("执行请求");
		}
		
	}
	
	public static class Proxy implements Subject {
		
		private Subject subject;
		
		public Proxy(Subject subject) {
			this.subject = subject;
		}

		public void request() {
			System.out.println("执行额外的条件判断，拷贝是否要调用subject的request()方法"); 
			boolean invoke = true;
			// 可以决定是否执行，装饰器模式就不行
			if(invoke) {
				subject.request();
			}
		}
		
	}
	
}
