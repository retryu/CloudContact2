package com.db.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.db.dao.interfaze.MsgDao;
import com.db.model.Message;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class MsgDaoImpl extends BaseDaoImpl<Message, String> implements MsgDao {

	public MsgDaoImpl(ConnectionSource connectionSource
			 ) throws SQLException {
		super(connectionSource, Message.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void readed(Message msg) {
			msg.setReaded(true);
			try {
				update(msg);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}	
	

	@Override
	public List<Message> getUnRead(Message msg) {
		List<Message> msgs=null;
		try {
			msgs = queryForEq("readed",false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msgs;
	}
	

}
