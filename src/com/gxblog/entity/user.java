package com.gxblog.entity;

import java.io.Serializable;

public class user implements Serializable {
     private String username = "root";
     private String password = "root";
     private String picture;
     
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
        

}
