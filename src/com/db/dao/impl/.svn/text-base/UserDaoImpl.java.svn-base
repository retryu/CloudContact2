package com.db.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.db.dao.interfaze.UserDao;
import com.db.model.User;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class UserDaoImpl extends BaseDaoImpl<User, String> implements UserDao {

	public UserDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, User.class);
	}

	@Override
	public boolean isExits() {
		List<User> users=null;
		try {
			users = queryForAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(users.size()<=0){
			return false;
		}
		else{
			return  true;
		}
	}

 
 
 

 
}
