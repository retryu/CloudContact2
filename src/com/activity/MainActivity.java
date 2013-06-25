package com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.activity.contacts.ContactFragment;
import com.activity.friend.FriendFragment;
import com.activity.main.TabsAdapter;
import com.activity.message.MessageActivity;
import com.hengtiansoft.cloudcontact.R;
import com.util.PushUtil;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time£º2013-6-10 ÏÂÎç03:53:35 file declare:
 */
public class MainActivity extends CommonActivity {

	private ViewPager viewPager;
	private Fragment contaFragment;
	private FriendFragment firendFragment;  
	ActionBar actionBar;
	private static final String INTENT_REFERSH = "refersh";
	private boolean needRefersh;

	@Override
	public void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		init();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		Log.e("debug", "MainActivity onNewIntent");
		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			String type = bundle.getString("type");
			if (type.equals(INTENT_REFERSH)) {
				needRefersh=true;
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		com.actionbarsherlock.view.MenuInflater menuInflater = getSupportMenuInflater();
		menuInflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Log.e("debug", "groupId:" + item.getGroupId() + "  " + item.getItemId()
				+ "  id" + R.id.msg);
		Intent intent = new Intent(this, MessageActivity.class);
		intent.putExtra(PushUtil.PUSH_DATA_EXTRA, "{'messageID':'89'}");
		startActivity(intent);

		return super.onOptionsItemSelected(item);
	}

	public void init() {
		needRefersh=false;
		actionBar = getSupportActionBar();
		viewPager = (ViewPager) findViewById(R.id.ViewPaper);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		TabsAdapter tabsAdapter = new TabsAdapter(this, viewPager);
		tabsAdapter.addTab(actionBar.newTab().setText("Í¨Ñ¶Â¼"),
				ContactFragment.class, null);
		tabsAdapter.addTab(actionBar.newTab().setText("ÅóÓÑ"),
				FriendFragment.class, null);

		firendFragment = (FriendFragment) tabsAdapter.getItem(1);
		firendFragment.setNeedRefesh(needRefersh);
		
	}

}
