package com.activity.login;

import java.sql.SQLException;

import android.R.menu;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import cn.jpush.android.api.JPushInterface;

import com.activity.IndexActivity;
import com.activity.MainActivity;
import com.activity.profile.ProfileActivity;
import com.db.dao.interfaze.UserDao;
import com.db.model.Contact;
import com.db.model.User;
import com.hengtiansoft.cloudcontact.LoginActivity;
import com.hengtiansoft.cloudcontact.R;
import com.http.CommonApi;
import com.http.ContactApi;
import com.http.HttpUtil;
import com.http.LoginApi;
import com.http.MessageApi;
import com.http.RegisterApi;
import com.http.response.CommonResponse;
import com.http.response.ErrorResponse;

public class LoginFragment extends Fragment implements OnClickListener {

	private View view;
	private Button btnLogging;
	private Button btnRegister;
	private EditText etUserMail;
	private EditText etUserPass;
	private LoginActivity loginActivity;
	private LinearLayout linearLayout;
	private UiHandler uiHandler;

	private Button btnLogOut;

	private static final int MSG_TO_MAIN = 1;
	private static final int MSG_SHOW_ERROR = 2;
	private static final int MSG_SHOW = 3;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// return super.onCreateView(inflater, container, savedInstanceState);
		view = (View) inflater.inflate(R.layout.fragment_loging, null);
		initWidget();
		return view;
	}

	public void initWidget() {
		uiHandler = new UiHandler();
		loginActivity = (LoginActivity) getActivity();
		btnLogging = (Button) view.findViewById(R.id.Btn_Logging);
		btnRegister = (Button) view.findViewById(R.id.Btn_Register);
		etUserMail = (EditText) view.findViewById(R.id.Et_User_Mail);
		etUserPass = (EditText) view.findViewById(R.id.Et_User_Password);
		linearLayout = (LinearLayout) view
				.findViewById(R.id.Layout_Login_Opration);
		btnLogging.setOnClickListener(this);
		btnRegister.setOnClickListener(this);
		btnLogOut = (Button) view.findViewById(R.id.logout);
		btnLogOut.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				JPushInterface.resumePush(loginActivity);

			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.Btn_Logging:
			login();
			break;

		case R.id.Btn_Register:
			// LoginActivity mainActivity = (LoginActivity) getActivity();
			// Intent intent=new Intent(mainActivity, MainActivity.class);
			// startActivity(intent);

			register();
			break;

		}
	}

	public void login() {
		String userName = etUserMail.getText().toString();
		String password = etUserPass.getText().toString();
		final User u = new User();
		u.setEmail(userName);
		u.setPassword(password);
		new Thread() {
			public void run() {
				CommonResponse commonResponse = LoginApi.login(u);

				if (commonResponse.getStateCode() == HttpUtil.CODE_SUCESS) {
					try {
						Message msg = new Message();
						msg.what = MSG_SHOW;
						msg.obj = "µ«»Î≥…π¶";
						uiHandler.sendMessage(msg);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					User user = LoginApi.toUser(commonResponse.getResponse());
					JPushInterface.setAliasAndTags(
							loginActivity.getApplicationContext(),
							user.getUserId(), null);
					HttpUtil.setUser(user);

					// ContactApi.restoreContacts();

					Message msg = new Message();
					msg.what = MSG_TO_MAIN;
					msg.obj = user;
					uiHandler.sendMessage(msg);

				} else {
					Message msg = new Message();
					msg.what = MSG_SHOW_ERROR;
					ErrorResponse errResponse = CommonApi
							.toErrorResponse(commonResponse.getResponse());
					Log.e("debug", "msg:" + errResponse.toString());
					msg.obj = errResponse.getMessage();
					uiHandler.sendMessage(msg);
				}
			};
		}.start();

	}

	public void register() {
		final User user = getUserInfo();

		new Thread() {
			public void run() {
				CommonResponse response = RegisterApi.register(user);
				User user = LoginApi.toUser(response.getResponse());

				JPushInterface.setAliasAndTags(
						loginActivity.getApplicationContext(),
						user.getUserId(), null);

			};
		}.start();

	}

	public void loging() {
		User user = getUserInfo();

	}

	public User getUserInfo() {
		String userMail = etUserMail.getText().toString();
		String userPassWord = etUserPass.getText().toString();
		User user = new User();
		user.setEmail(userMail);
		user.setPassword(userPassWord);
		return user;
	}

	class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			switch (msg.what) {
			case MSG_TO_MAIN:

				UserDao userDao = loginActivity.getOrmDateBaseHelper()
						.getUserDao();
				User u = (User) msg.obj;
				try {
					userDao.create(u);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				HttpUtil.setUser(u);
				try {
					userDao.create(u);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Intent intent = new Intent(getActivity(), IndexActivity.class);
				startActivity(intent);
				loginActivity.finish();
				break;

			case MSG_SHOW_ERROR:
				String msgStr = (String) msg.obj;
				loginActivity.showAlert(msgStr);
				break;
			case MSG_SHOW:
				String msgContent = (String) msg.obj;
				loginActivity.showAlert(msgContent);

				break;
			}

		}
	}

}
