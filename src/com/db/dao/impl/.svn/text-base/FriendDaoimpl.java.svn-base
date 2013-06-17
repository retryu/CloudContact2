package com.db.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.db.dao.interfaze.FriendDao;
import com.db.model.Friend;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class FriendDaoimpl extends BaseDaoImpl<Friend, String> implements
		FriendDao {

	public FriendDaoimpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Friend.class);
	}

	@Override
	public void batchInsert(List<Friend> firends) {
		for (Friend f : firends) {
			try {
				create(f);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void batchDelete(List<Friend> friends) {
		for (Friend f : friends) {
			try {
				delete(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
