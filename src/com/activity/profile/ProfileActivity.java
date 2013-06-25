package com.activity.profile;

import java.sql.SQLException;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.activity.CommonActivity;
import com.activity.IndexActivity;
import com.activity.MainActivity;
import com.db.OrmDateBaseHelper;
import com.db.dao.interfaze.UserDao;
import com.db.model.User;
import com.hengtiansoft.cloudcontact.R;
import com.http.UserInfoApi;
import com.widget.HeadEditText;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time��2013-6-21 ����01:29:33 file declare:
 */
public class ProfileActivity extends CommonActivity implements
		android.view.View.OnClickListener {
	private UserDao userDao;
	private OrmDateBaseHelper ormDateBaseHelper;
	private HeadEditText HeMobile;
	private HeadEditText HeHome;
	private HeadEditText HeQQ;
	private Button btnUpdate;
	private Button btnBack;
	private static final int MSG_ALERT = 1;
	private UiHandler uiHandler;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_profile);
		ormDateBaseHelper = getOrmDateBaseHelper();
		userDao = ormDateBaseHelper.getUserDao();
		initWidget();
		try {
			User user = userDao.queryForId("1");
			bindData(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initWidget() {
		HeHome = (HeadEditText) findViewById(R.id.He_Home);
		HeMobile = (HeadEditText) findViewById(R.id.He_Mobile);
		HeQQ = (HeadEditText) findViewById(R.id.He_QQ);
		btnUpdate = (Button) findViewById(R.id.Btn_Update);
		btnBack = (Button) findViewById(R.id.Btn_Back);
		btnBack.setOnClickListener(this);
		btnUpdate.setOnClickListener(this);
		uiHandler = new UiHandler();
	}

	public void bindData(User user) {
		if (user.getCellPhon1() != null) {
			HeHome.setContent(user.getCellPhon1());
		}
		if (user.getCellPhone2() != null) {
			HeHome.setContent(user.getCellPhone2());
		}
		if (user.getTodo_one() != null) {
			HeQQ.setContent(user.getTodo_one());
		}
	}

	public User getUser() {
		User user = null;
		try {
			user = userDao.queryForId("1");
		} catch (Exception e) {
			e.printStackTrace();
		}

		String mobile = HeMobile.getContent();
		String home = HeHome.getContent();
		String qq = HeQQ.getContent();
		user.setCellPhon1(mobile);
		user.setCellPhone2(home);
		user.setTodo_one(qq);

		return user;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Update:
			final User user = getUser();
			new Thread() {
				public void run() {
					UserInfoApi.updateInfo(user);
					try {
						userDao.update(user);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Message msg = new Message();
					msg.what = MSG_ALERT;
					uiHandler.sendMessage(msg);
				};
			}.start();
			break;
		case R.id.Btn_Back:
			Intent intent = new Intent(this, IndexActivity.class);
			startActivity(intent);
			break;
		}
	}

	class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {
			case MSG_ALERT:
				Toast.makeText(getApplicationContext(), "�������",
						Toast.LENGTH_LONG).show();
				break;

			default:
				break;
			}

		}

	}

}
