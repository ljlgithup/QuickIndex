package com.example.quick.index.bean;

import com.example.quick.index.utils.PinyinUtil;
/**
 * 这bean对象要实现比较，所以是实现compareable
 * @author lenovo
 *
 */
public class Person implements Comparable<Person>{

	private String name;
	private String pingyin;
	
	public Person(String name){
		this.name = name;
		this.pingyin = PinyinUtil.getPinyin(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPingyin() {
		return pingyin;
	}

	public void setPingyin(String pingyin) {
		this.pingyin = pingyin;
	}

	@Override
	public int compareTo(Person another) {
		return this.pingyin.compareTo(another.pingyin);
	}
}
