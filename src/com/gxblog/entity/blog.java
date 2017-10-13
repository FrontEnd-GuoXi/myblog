package com.gxblog.entity;

import com.sun.rowset.CachedRowSetImpl;

public class blog {
	private int id;
	private String title;
	private String content;
	private int pub_time;
	private String username;
	private String author;

	public blog(){}
	
	public blog(String title, String author, String content,String username){
		this.title = title;
		this.author = author;
		this.content = content;
		this.username = username;
	}
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPub_time() {
		return pub_time;
	}
	public void setPub_time(int pub_time) {
		this.pub_time = pub_time;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	
}
