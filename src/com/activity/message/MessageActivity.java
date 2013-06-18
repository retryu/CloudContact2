package com.activity.message;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.activity.MainActivity;
import com.db.model.Message;
import com.hengtiansoft.cloudcontact.R;
import com.http.MessageApi;
import com.http.response.CommonResponse;
import com.util.PushUtil;

public class MessageActivity extends SherlockFragmentActivity {

	public static final int MSG_DATA = 1;
	public static final int MSG_UPDATE = 2;
	public static final int MSG_CHANGE_STATE = 3;
	private ActionBar actionBar;
	private ListView listViewMessage;
	MessageAdapter msgAdapter;
	private UIHandler uiHandler;
	private Intent msgIntent;
	private static boolean needRefersh = false;

	@Override
	public void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_message_list);
		initWidget();
		initData();

		checkMsg();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		msgIntent = intent;
		Log.e("debug", "new Intent");
	}

	private void checkMsg() {
		msgIntent = getIntent();
		if (msgIntent != null) {
			Bundle b = msgIntent.getExtras();
			if (b != null) {
				String msgJson = b.getString(PushUtil.PUSH_DATA_EXTRA);
				System.err.println("msgId" + msgJson);
				final int id = MessageApi.getID(msgJson);
				new Thread() {
					public void run() {
						Message msg = MessageApi.getMessageById(id + "");
						android.os.Message msgOS = new android.os.Message();
						msgOS.what = MSG_UPDATE;
						msgOS.obj = msg;
						uiHandler.sendMessage(msgOS);
					};
				}.start();
			}
		}
	}

	public void initWidget() {
		actionBar = getSupportActionBar();
		actionBar.setTitle("消息列表");
		listViewMessage = (ListView) findViewById(R.id.ListView_Message);
		msgAdapter = new MessageAdapter(getApplicationContext());
		listViewMessage.setAdapter(msgAdapter);
		uiHandler = new UIHandler();
		msgAdapter.setUiHandler(uiHandler);
	}

	public void initData() {
		// new Thread() {
		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
		// super.run();
		// CommonResponse response = MessageApi.getMessages();
		// List<Message> msgList = MessageApi.toMessages(response
		// .getResponse());
		// android.os.Message msg = new android.os.Message();
		// msg.what = MSG_DATA;
		// msg.obj = msgList;
		// uiHandler.sendMessage(msg);
		// }
		// }.start();
	}

	class UIHandler extends Handler {
		@Override
		public void dispatchMessage(android.os.Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			int msgCode = msg.what;
			switch (msgCode) {
			case MSG_DATA:
				List<Message> msgs = (List<Message>) msg.obj;
				msgAdapter.setMsgs(msgs);
				break;

			case MSG_UPDATE:
				int msgId = msg.what;
				Message myMsg = (Message) msg.obj;
				msgAdapter.addMsg(myMsg);

				break;
			case MSG_CHANGE_STATE:
				Message myMessage = (Message) msg.obj;
				msgAdapter.updateMessage(myMessage);
				break;
			}
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("needRefersh", needRefersh);
	}

}
