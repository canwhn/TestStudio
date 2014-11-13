package net.canwhn.reflect.proxy;

public class TestProxy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyInvocationHandler demo = new MyInvocationHandler();
		Subject sub = (Subject)demo.bind(new RealSubject());
		String info = sub.say("Ariel", 23);
		System.out.println(info);
	}

}
