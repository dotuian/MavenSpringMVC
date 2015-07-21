package com.dotuian.springmvc.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "xmlusers")
public class XmlUserList {

	private List<XmlUser> dataList;

	public XmlUserList() {

	}

	public XmlUserList(List<XmlUser> dataList) {
		this.dataList = dataList;
	}

	@XmlElement(name = "xmluser")
	public List<XmlUser> getXmlUsers() {
		return this.dataList;
	}
}
