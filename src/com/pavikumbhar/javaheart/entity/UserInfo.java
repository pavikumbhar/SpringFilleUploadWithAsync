/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.entity;

import java.util.Date;

/**
 *
 * @author pravinkumbhar
 */
public class UserInfo {
	
	private int id;
	private String username;
	private Date inputDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	@Override
	public String toString() {
		return "id=" + id + ", username=" + username;
	}

}
