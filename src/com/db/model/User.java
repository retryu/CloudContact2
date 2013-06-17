package com.db.model;

import com.db.dao.impl.UserDaoImpl;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(daoClass = UserDaoImpl.class)
public class User {
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String email;
	@DatabaseField
	private String password;
	@DatabaseField
	private String access_token;
	@DatabaseField
	private String push_token;
	@DatabaseField
	private String cellPhon1;
	@DatabaseField
	private String cellPhone2;
	@DatabaseField
	private String name;
	@DatabaseField
	private String userId;
	@DatabaseField
	private String todo_one;
	@DatabaseField
	private String todo_two;
	@DatabaseField
	private String todo_three;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getPush_token() {
		return push_token;
	}

	public void setPush_token(String push_token) {
		this.push_token = push_token;
	}

	public String getCellPhon1() {
		return cellPhon1;
	}

	public void setCellPhon1(String cellPhon1) {
		this.cellPhon1 = cellPhon1;
	}

	public String getCellPhone2() {
		return cellPhone2;
	}

	public void setCellPhone2(String cellPhone2) {
		this.cellPhone2 = cellPhone2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTodo_one() {
		return todo_one;
	}

	public void setTodo_one(String todo_one) {
		this.todo_one = todo_one;
	}

	public String getTodo_two() {
		return todo_two;
	}

	public void setTodo_two(String todo_two) {
		this.todo_two = todo_two;
	}

	public String getTodo_three() {
		return todo_three;
	}

	public void setTodo_three(String todo_three) {
		this.todo_three = todo_three;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password
				+ ", access_token=" + access_token + ", push_token="
				+ push_token + ", cellPhon1=" + cellPhon1 + ", cellPhone2="
				+ cellPhone2 + ", name=" + name + ", userId=" + userId
				+ ", todo_one=" + todo_one + ", todo_two=" + todo_two
				+ ", todo_three=" + todo_three + "]";
	}

}
