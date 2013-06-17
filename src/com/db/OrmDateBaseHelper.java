package com.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.db.dao.interfaze.ContactDao;
import com.db.dao.interfaze.FriendDao;
import com.db.dao.interfaze.MsgDao;
import com.db.dao.interfaze.UserDao;
import com.db.model.Contact;
import com.db.model.Friend;
import com.db.model.Message;
import com.db.model.User;
import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class OrmDateBaseHelper extends SQLiteOpenHelper {

	private static FriendDao frindDao;
	private static ContactDao contactDao;
	private static UserDao userDao;
	private static MsgDao msgDao;

	public OrmDateBaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		initScheme();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.e("debug", "onCreate");

	}

	public void initScheme() {
		ConnectionSource connectionSource = new AndroidConnectionSource(this);
		try {
			TableUtils.createTableIfNotExists(connectionSource, Friend.class);
			TableUtils.createTableIfNotExists(connectionSource, Contact.class);
			TableUtils.createTableIfNotExists(connectionSource, User.class);
			TableUtils.createTableIfNotExists(connectionSource, Message.class);
			frindDao = DaoManager.createDao(connectionSource, Friend.class);
			contactDao = DaoManager.createDao(connectionSource, Contact.class);
			userDao = DaoManager.createDao(connectionSource, User.class);
			msgDao = DaoManager.createDao(connectionSource, Message.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.e("debug", "onCreate");
	}

	public static FriendDao getFrindDao() {
		return frindDao;
	}

	public static void setFrindDao(FriendDao frindDao) {
		OrmDateBaseHelper.frindDao = frindDao;
	}

	public static ContactDao getContactDao() {
		return contactDao;
	}

	public static void setContactDao(ContactDao contactDao) {
		OrmDateBaseHelper.contactDao = contactDao;
	}

	public  UserDao getUserDao() {
		return userDao;
	}

	public   void setUserDao(UserDao userDao) {
		OrmDateBaseHelper.userDao = userDao;
	}

	public   MsgDao getMsgDao() {
		return msgDao;
	}

	public   void setMsgDao(MsgDao msgDao) {
		OrmDateBaseHelper.msgDao = msgDao;
	}

}
