package singleton;

public class Singleton {
	public static Singleton s = null;
	public String hostDB_IP= null;	// 호스트 디비 IP 값 받는 변수
	
	
	private Singleton() {
		
	}
	public static Singleton getInstance() {
		if(s==null)
			s = new Singleton();
		
		return s;
	}
}
