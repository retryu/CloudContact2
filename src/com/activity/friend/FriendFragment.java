package com.activity.friend;

import java.util.List;

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
import android.widget.ListView;

import com.db.model.Friend;
import com.hengtiansoft.cloudcontact.R;
import com.http.FriendApi;
import com.view.SearchView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time£º2013-6-10 ÏÂÎç02:17:24 file declare:
 */
public class FriendFragment extends Fragment {
	FriendAdapter friendAdapter;
	ListView friendListView;

	SearchView searchView;
	private View view;
	private UiHandler uiHandler;
	private static final int MSG_INIT = 1;
	private static final int mSG_UPDATE = 2;
	private boolean needRefesh;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_friends, null);
		initWidget();
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		Log.e("debug", "onViewCreated");
		if (needRefesh == true) {
			initFrineds();
		}
	}
  
	public void initWidget() {
		needRefesh = false;
		uiHandler = new UiHandler();
		friendListView = (ListView) view.findViewById(R.id.ListView_Friends);
		friendAdapter = new FriendAdapter(getActivity());
		friendAdapter.setFriends(null);
		friendListView.setAdapter(friendAdapter);
		searchView = (SearchView) view.findViewById(R.id.SearchView);
		searchView.setOnSearchCLick(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),
						SearchFriendActivity.class);
				startActivity(intent);

			}
		});
		initFrineds();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e("debug", "onResume");
	}

	public void initFrineds() {
		new Thread() {
			@Override
			public void run() {
				super.run();
				List<Friend> contacts = FriendApi.getFriends();
				Message msg = new Message();
				msg.what = MSG_INIT;
				msg.obj = contacts;
				if (uiHandler == null) {
					uiHandler = new UiHandler();
				}
				uiHandler.sendMessage(msg);
			}

		}.start();

	}

	class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {
			case MSG_INIT:
				List<Friend> friends = (List<Friend>) msg.obj;
				friendAdapter.setFriends(friends);
				friendAdapter.notifyDataSetChanged();
				break;

			case mSG_UPDATE:
				break;
			}
		}
	}

	public boolean isNeedRefesh() {
		return needRefesh;
	}

	public void setNeedRefesh(boolean needRefesh) {
		this.needRefesh = needRefesh;
	}
	
	
}
