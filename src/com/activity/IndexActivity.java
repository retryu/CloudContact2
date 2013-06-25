package com.activity;

import java.util.List;

import org.apache.http.protocol.HTTP;

import com.activity.contacts.ContactFragment;
import com.activity.profile.ProfileActivity;
import com.db.model.Contact;
import com.hengtiansoft.cloudcontact.R;
import com.http.ContactApi;
import com.http.response.CommonResponse;
import com.util.ContactUtil;
import com.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time��2013-6-21 ����03:51:08 file declare:
 */
public class IndexActivity extends CommonActivity implements
		android.view.View.OnClickListener {
	private ImageView imgProfile;
	private RelativeLayout layoutContent;
	private FragmentManager fm;
	private FragmentTransaction ft;
	private Activity activity;
	private ImageView imgUpload;
	private ImageView imgFriend;
	private ImageView imgDownload;
	private TextView tvDownload;
	private TextView tvUpload;
	private TextView tvFriend;
	 
	private static final int MSG_UPDATE_DOWNLOAD = 3;
	private static final int MSG_FINISHDOWNLOAD = 4;
	private static final int MSG_UPLOAD = 1;
	private static final int MSG_UPLOAD_FINISH = 2;

	ContactFragment contactFragment;

	private UiHandler uiHandler;

	@Override
	public void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_index);
		initWidget();

	}

	public void initWidget() {
		activity = this;
		uiHandler = new UiHandler();
 
		imgProfile = (ImageView) findViewById(R.id.Img_Profile);
		imgUpload = (ImageView) findViewById(R.id.Img_Upload);
		imgFriend = (ImageView) findViewById(R.id.Img_Friend);
		imgDownload = (ImageView) findViewById(R.id.Img_Download);
		tvDownload = (TextView) findViewById(R.id.Tv_Download);
		tvFriend = (TextView) findViewById(R.id.Tv_Friend);
		tvUpload = (TextView) findViewById(R.id.Tv_Upload);

		// layoutContent = (RelativeLayout) findViewById(R.id.Layout_Menu);
		fm = getSupportFragmentManager();
		ft = fm.beginTransaction();
		contactFragment = new ContactFragment();
		ft.replace(R.id.Layout_Content, contactFragment);
		ft.commit();
		imgProfile.setOnClickListener(this);
		imgUpload.setOnClickListener(this);
		imgFriend.setOnClickListener(this);
		imgDownload.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Log.e("debug", "onclick");
		int id = v.getId();
		switch (id) {
		case R.id.Img_Profile:
			Intent intentToProfile = new Intent(getApplicationContext(),
					ProfileActivity.class);
			startActivity(intentToProfile);
			break;
		case R.id.Img_Upload:
			upload();
			break;

		case R.id.Img_Friend:

			break;
		case R.id.Img_Download:
			download();
			break;

		}

	}

	public void upload() {
		new Thread() {
			@Override
			public void run() {
				super.run();
				Message msg = new Message();
				msg.what = MSG_UPLOAD;
				String msgStr = getResources().getString(R.string.uploading);
				msg.obj = msgStr;
				uiHandler.sendMessage(msg);
				ContactUtil contactUtil = new ContactUtil(activity);
				List<Contact> contacts = contactUtil.query();
				ContactApi.backUpCpntacts(contacts);

				Message msgFinish = new Message();
				msgFinish.what = MSG_UPLOAD_FINISH;
				msgStr = getResources().getString(R.string.upload_finish);
				msgFinish.obj = msgStr;
				uiHandler.sendMessage(msgFinish);

			}
		}.start();

	}

	public void download() {
		new Thread() {
			@Override
			public void run() {
				super.run();
				CommonResponse response = ContactApi.restoreContacts();
				if (response.getStateCode() == com.http.HttpUtil.CODE_SUCESS)
					;

				Message redadyMsg = new Message();
				redadyMsg.what = MSG_UPDATE_DOWNLOAD;
				redadyMsg.obj = getResources()
						.getString(R.string.readyDownload);
				uiHandler.sendMessage(redadyMsg);

				List<Contact> contacts = ContactApi.toContacts(response
						.getResponse());
				ContactUtil contactUtil = new ContactUtil(activity);
				List<Contact> backContacts = contactUtil
						.getBackUpContact(contacts);
				int count = backContacts.size();
				String finishStr = getResources().getString(R.string.finish);
				String processStr = finishStr + "0/" + count;
				for (int i = 0; i < backContacts.size(); i++) {
					Contact contact = backContacts.get(i);
					processStr = finishStr + i + "/" + count;
					Message msg = new Message();
					msg.what = MSG_UPDATE_DOWNLOAD;
					msg.obj = processStr;
					uiHandler.sendMessage(msg);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					contactUtil.insert(contact);
				}
				Message msg = new Message();
				msg.what = MSG_FINISHDOWNLOAD;
				msg.obj = getResources().getString(R.string.finishDownload);
				uiHandler.sendMessage(msg);

			}
		}.start();
	}

	class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			int msgId = msg.what;
			switch (msgId) {
			case MSG_UPDATE_DOWNLOAD:
				String processStr = (String) msg.obj;
				tvDownload.setText(processStr);
				break;

			case MSG_FINISHDOWNLOAD:
				String content = (String) msg.obj;
				tvDownload.setText(getResources().getString(R.string.Download));
				contactFragment.updeData();
				Toast.makeText(activity,
						getResources().getString(R.string.finishDownload),
						Toast.LENGTH_LONG).show();
				break;
			case MSG_UPLOAD:
				String uploadMsg = (String) msg.obj;
				tvUpload.setText(uploadMsg);
				break; 
			case MSG_UPLOAD_FINISH:
				String contentStr = getResources().getString(R.string.upload);
				tvUpload.setText(contentStr);
				Toast.makeText(activity,
						getResources().getString(R.string.upload_finish),
						Toast.LENGTH_LONG).show();
				break;

			}

		}
	}
}
