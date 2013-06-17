package com.db.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.db.dao.interfaze.ContactDao;
import com.db.dao.interfaze.UserDao;
import com.db.model.Contact;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class ContactDaoImpl extends BaseDaoImpl<Contact, String> implements
		ContactDao {

	public ContactDaoImpl(ConnectionSource connectionSource)
			throws SQLException {
		super(connectionSource, Contact.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void batchInsert(List<Contact> contacts) {
		for (Contact c : contacts) {
			try {
				create(c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void batchDelete(List<Contact> contacts) {
		for (Contact c : contacts) {
			try {
				delete(c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
