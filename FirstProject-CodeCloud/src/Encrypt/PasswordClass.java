package Encrypt;

public class PasswordClass {
	// 암호화 복호화 해주는 클래스
	//공백과 특수문자는 암호화 복호화 되지않고 그대로 저장된다.
	
	private char[] abcCode = { // 각 순서대로 A~z에 해당하는 문자들
			// A는 65 따라서 첫번째 인덱스는 A-65														
			'`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '?', '_', '+', '=', '|', '[', ']', '{', '}',
			';', ':', ',', '.', '/',
			// 대문자와 소문자 사이에 6개의 다른문자가 있기때문에 그부분은 공백으로 처리
			' ', ' ', ' ', ' ', ' ', ' ',
			//////////////////////////////////////////////
			'¡', 'a', 'z', '¤', 'f', 'x', '§', '¨', 'v', 'ª', 'h', 's', 'l', '®', 'm', '°', '±', '²', '³', '´', 'b',
			'¶', '·', '¸', '¹', 'º' };

	// 0 1 2 ~9 각 숫자에 해당하는 문자
	private char[] numCode = { 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p' };

	//공백과 특수문자는 안됨
	String password;
	//알파벳과 숫자 이외의 자리를 알기 위한 boolean변수 
	boolean swi[];
			
	public PasswordClass() {
		
	}
	
	public String Encryption(String pwd) { // 암호화 메소드
		this.password =pwd;
		swi = new boolean[password.length()]; 
		
		int len = password.length();
		String eStr = "";

		for (int i = 0; i < len; i++) {
			char c = password.charAt(i);
			if (c >= 'A' && c <= 'z') {// 문자가 알파벳 소문자라면
				// 예를들어 c가 a일때 97을 빼주면 a에 해당하는 0이라는 인덱스값을 구할수 있다.
				int index = c - 65;
				eStr += abcCode[index];
			} else if (c >= '0' && c <= '9') {// 숫자일 때는 numCode 참고
				int index = Integer.parseInt(c+"");
				eStr+=numCode[index];
			}
			else {//이외의 문자면 그대로 붙인다.
				eStr+=c;
				swi[i]=true;
			}
		}
		return eStr;
	}
	public String Decode(String pwd) {	//복호화 메소드
		this.password =pwd;
		swi = new boolean[password.length()]; 
		
		int len=password.length();
		String eStr="";
		
		//indexof메소드를 사용하기 위하여 String형으로 만들어 준다.
		//java.util.Arrays.toString()으로 변환하면 괄호와 ,도 같이 문자열에 포함되서 new String으로 해야한다.
		String abcCodeStr = new String(abcCode);
		String numCodeStr = new String(numCode);
		
		for (int i = 0; i < len; i++) {
			char c=password.charAt(i);
	
			if(swi[i]==true) {
				//s[i](swi의 i번째)가 true면 해당 자리는 원래 알파벳이나 숫자가 아닌 다른문자이므로 그대로 붙여준다.
				eStr+=c;
				continue;
			}
			
			int abcC=abcCodeStr.indexOf(c);
			int numC=numCodeStr.indexOf(c);
			
			if(abcC!=-1) {//해당값이 -1이 안나오면 해당 문자열에 그 문자가 포함되있는 것이므로 아래 처리를 거쳐간다.
				/*
				 	알파벳중아 가장 작은 값을 가지는건 A로 아스키 코드값이 65이다.
				 	따라서 0번지에 해당하는 특수문자를 A로 바꾸려면
				 	인덱스에 +65를 해주면 된다.
				 	A부터해서 다른알파벳들도 순차적으로 아스키코드가 있기 때문에
				 	인덱스값+65해주면 해당 특수문자에 해당하는 알파벳을 얻을 수 있다.
				 */
				eStr+=(char)(abcC+65);
			}
			else{//사실상 특수문자도 거르고 abcC를 통해 알파벳도 없다는 결과가 나오면 숫자만 남기 때문에 조건을 따로 걸어줄 필요가 없다.
				
				eStr+=(numC+"");
			}
				
		
		}
		
		return eStr;
	}
}
