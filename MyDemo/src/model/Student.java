package model;

public class Student implements java.io.Serializable   {

	//注意：建议将字段私有，而公开其属性访问。
	public String Name;
	public String ID;
	
	public String toString() {
		String result = "";
		result = "姓名："+ Name + " 学号：" + ID;
		return result;		
	}
	
	
}
