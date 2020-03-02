package entity;
/**
 * �����ݿ�����Ӧ��Javaʵ��
 * 	��������˽��
 * 	�ṩget��set����
 * 	�ṩ��Ӧ�Ĺ��췽��������Ҫ��һ���޲εĹ���
 * @author lindy
 *
 */
public class StudentInfo {
	private int stuNo;
	private String stuName;
	private int gender;
	private int age;
	private String email;
	private String headImg;
	public int getStuNo() {
		return stuNo;
	}
	public void setStuNo(int stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public StudentInfo(int stuNo, String stuName, int gender, int age, String email, String headImg) {
		super();
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.headImg = headImg;
	}
	public StudentInfo(String stuName, int gender, int age, String email, String headImg) {
		super();
		this.stuName = stuName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.headImg = headImg;
	}
	public StudentInfo() {
		super();
	}
}
