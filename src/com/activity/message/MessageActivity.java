package com.activity.message;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.db.model.Message;
import com.hengtiansoft.cloudcontact.R;
import com.http.MessageApi;
import com.http.response.CommonResponse;
import com.util.PushUtil;

public class MessageActivity extends SherlockFragmentActivity {

	private static final int MSG_DATA = 1;

	private static final int MSG_UPDATE = 2;
	private ActionBar actionBar;
	private ListView listViewMessage;
	MessageAdapter msgAdapter;
	private UIHandler uiHandler;

	@Override
	public void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_message_list);
		initWidget();
		initData();

		checkMsg();
	}

	private void checkMsg() {
		Intent i = getIntent();
		if (i != null) {
			Bundle b = i.getExtras();
			String msgJson = b.getString(PushUtil.PUSH_DATA_EXTRA);
			System.err.println("msgId" + msgJson);
			final int id = MessageApi.getID(msgJson);
			new Thread() {
				public void run() {
					Message msg = MessageApi.getMessageById(id + "");
					Message msgOS = new Message();
				};
			}.start();

		}
	}

	public void initWidget() {
		actionBar = getSupportActionBar();
		actionBar.setTitle(getResources().getText(R.string.friend_list));
		listViewMessage = (ListView) findViewById(R.id.ListView_Message);
		msgAdapter = new MessageAdapter(getApplicationContext());
		listViewMessage.setAdapter(msgAdapter);
		uiHandler = new UIHandler();
	}

	public void initData() {
		new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				CommonResponse response = MessageApi.getMessages();
				List<Message> msgList = MessageApi.toMessages(response
						.getResponse());
				android.os.Message msg = new android.os.Message();
				msg.what = MSG_DATA;
				msg.obj = msgList;
				uiHandler.sendMessage(msg);
			}
		}.start();
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
				Message myMsg = MessageApi.getMessageById(msgId + "");

				break;
			}
		}

	}

}
