package net.canwhn.reflect;

class Sino{
	
}

public class TestMain {	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Sino s = new Sino();
		System.out.println(s.getClass().getClassLoader().getClass().getName());

	}

}
