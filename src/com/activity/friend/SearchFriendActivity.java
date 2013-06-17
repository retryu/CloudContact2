package com.activity.friend;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.widget.SearchView;
import com.activity.CommonActivity;
import com.hengtiansoft.cloudcontact.R;
import com.widget.ListViewSearch;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time£º2013-6-17 ÏÂÎç04:13:48 file declare:
 */
public class SearchFriendActivity extends CommonActivity {

	private SearchView searchView;
	ListViewSearch lvFriend;

	@Override
	public void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.layout_search_friend);
		initWidget();
	}

	private void initWidget() {
		lvFriend = (ListViewSearch) findViewById(R.id.ListView_Friends);

	}

	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.search_friend_menu, menu);
		searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		lvFriend.setSearchView(searchView);
		lvFriend.init();
		return true;
	}

}
