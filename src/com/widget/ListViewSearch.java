package com.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;

import com.actionbarsherlock.widget.SearchView;
import com.actionbarsherlock.widget.SearchView.OnQueryTextListener;
import com.http.FriendApi;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time£º2013-6-17 ÏÂÎç04:49:19 file declare:
 */
public class ListViewSearch extends ListView {

	private SearchView searchView;

	public ListViewSearch(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ListViewSearch(Context context) {
		super(context);
	}

	public void init() {
		searchView.setOnQueryTextListener(new OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				Log.e("debuf", "" + query);

				queryUser(query);
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {

				return false;
			}
		});
	}

	public void queryUser(String user) {
		final String u = user;
		new Thread() {
			@Override
			public void run() {
				super.run();
				FriendApi.getFriend(u);
			}
		}.start();

	}

	public SearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}

}
