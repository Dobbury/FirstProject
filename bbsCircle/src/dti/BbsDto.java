package dti;

public class BbsDto {

	
	
	private String name="";
	private String name2="";
	private int age;
	public BbsDto(String name, String name2, int age) {
		super();
		this.name = name;
		this.name2 = name2;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "BbsDto [name=" + name + ", name2=" + name2 + ", age=" + age + "]";
	}
	
	
	
}
