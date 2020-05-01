package ch05_1;

public class Singleton {
	// static : class용 멤버함수 -> all 공유
	// private : 외부에서 접근하지 못함
	private static Singleton singleton = new Singleton();
	private Singleton() {
		System.out.println("create Instance");
	}
	public static Singleton getInstance() {
		return singleton;
	}
}
