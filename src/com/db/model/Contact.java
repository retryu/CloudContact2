package com.db.model;

import com.db.dao.impl.ContactDaoImpl;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(daoClass = ContactDaoImpl.class)
public class Contact {
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String name;
	@DatabaseField
	private String cellphone1;
	@DatabaseField
	private String cellphone2;
	@DatabaseField
	private String telephone;
	@DatabaseField
	private String email;
	@DatabaseField
	private String todoOne;
	@DatabaseField
	private String todoTwo;
	@DatabaseField
	private String todoThree;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCellphone1() {
		return cellphone1;
	}

	public void setCellphone1(String cellphone1) {
		this.cellphone1 = cellphone1;
	}

	public String getCellphone2() {
		return cellphone2;
	}

	public void setCellphone2(String cellphone2) {
		this.cellphone2 = cellphone2;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTodoOne() {
		return todoOne;
	}

	public void setTodoOne(String todoOne) {
		this.todoOne = todoOne;
	}

	public String getTodoTwo() {
		return todoTwo;
	}

	public void setTodoTwo(String todoTwo) {
		this.todoTwo = todoTwo;
	}

	public String getTodoThree() {
		return todoThree;
	}

	public void setTodoThree(String todoThree) {
		this.todoThree = todoThree;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", cellphone1="
				+ cellphone1 + ", cellphone2=" + cellphone2 + ", telephone="
				+ telephone + ", email=" + email + ", todoOne=" + todoOne
				+ ", todoTwo=" + todoTwo + ", todoThree=" + todoThree + "]";
	}

}
