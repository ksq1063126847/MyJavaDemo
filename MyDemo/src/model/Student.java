package model;

public class Student implements java.io.Serializable   {

	//ע�⣺���齫�ֶ�˽�У������������Է��ʡ�
	public String Name;
	public String ID;
	
	public String toString() {
		String result = "";
		result = "������"+ Name + " ѧ�ţ�" + ID;
		return result;		
	}
	
	
}
