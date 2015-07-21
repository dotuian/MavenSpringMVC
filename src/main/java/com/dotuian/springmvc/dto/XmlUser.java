package com.dotuian.springmvc.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xmluser")
public class XmlUser {

	private long userid;
	private String username;
	private int age;
	private int sex;

	public long getUserid() {
		return userid;
	}

	@XmlElement
	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	@XmlElement
	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	@XmlElement
	public void setAge(int age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	@XmlElement
	public void setSex(int sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userid=");
		builder.append(userid);
		builder.append(", username=");
		builder.append(username);
		builder.append(", age=");
		builder.append(age);
		builder.append(", sex=");
		builder.append(sex);
		builder.append("]");
		return builder.toString();
	}

}
