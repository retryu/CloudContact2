package com.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;

import com.actionbarsherlock.widget.SearchView;
import com.actionbarsherlock.widget.SearchView.OnQueryTextListener;
import com.activity.friend.FriendSearchAdapter;
import com.activity.friend.SearchResult;
import com.db.model.Friend;
import com.http.FriendApi;
import com.http.HttpUtil;
import com.http.response.CommonResponse;
import com.model.FriendState;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time£º2013-6-17 ÏÂÎç04:49:19 file declare:
 */
public class ListViewSearch extends ListView {

	private FriendSearchAdapter searchAdapter;
	private SearchView searchView;
	private final static int MSG_NOT_FOUND = 1;
	private final static int MSG_SHOW_RESULT = 2;
	private final static int MSG_ERROR = 3;
	private UiHandler uiHandler;

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
		uiHandler = new UiHandler();
	}

	public void queryUser(String user) {
		final String u = user;
		new Thread() {
			@Override
			public void run() {
				super.run();
				CommonResponse response = FriendApi.getFriend(u);
				if (response.getStateCode() == HttpUtil.CODE_NULL) {
					Message msg = new Message();
					msg.what = MSG_NOT_FOUND;
					uiHandler.sendMessage(msg);
				} else if (response.getStateCode() == HttpUtil.CODE_SUCESS) {
					Message msg = new Message();
					msg.what = MSG_SHOW_RESULT;
					FriendState friendState = FriendApi.checkFriend(response
							.getResponse());
					msg.obj = friendState;
					uiHandler.sendMessage(msg);
				}
			}
		}.start();

	}

	public SearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}

	class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {
			case MSG_NOT_FOUND:
				List<SearchResult> r = new ArrayList<SearchResult>();
				SearchResult searchResult = new SearchResult();
				searchResult.setType(0);
				r.add(searchResult);
  				searchAdapter.setResults(r);
				break;

			case MSG_SHOW_RESULT:

				List<SearchResult> results = new ArrayList<SearchResult>();
				FriendState friendState = (FriendState) msg.obj;
				Friend friend = new Friend();
				friend.setName(friendState.getName());
				friend.setId(Integer.parseInt(friendState.getUserId()));
				if (friendState.getIsAlreadyFriend() == 0) {
					SearchResult result = new SearchResult();
					result.setType(2);
					result.setFriend(friend);
					results.add(result);
					searchAdapter.setResults(results);
				} else {
					SearchResult result = new SearchResult();
					result.setType(1);
					result.setFriend(friend);
					results.add(result);
					searchAdapter.setResults(results);
				}
				break;
			}

		}
	}

	public FriendSearchAdapter getSearchAdapter() {
		return searchAdapter;
	}

	public void setSearchAdapter(FriendSearchAdapter searchAdapter) {
		this.searchAdapter = searchAdapter;
		this.setAdapter(searchAdapter);
	}

}
