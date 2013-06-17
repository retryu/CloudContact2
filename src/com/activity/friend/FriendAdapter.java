package com.activity.friend;

import java.util.List;

import com.db.model.Friend;
import com.hengtiansoft.cloudcontact.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time£º2013-6-10 ÏÂÎç02:49:19 file declare:
 */
public class FriendAdapter extends BaseAdapter {

	List<Friend> friends;
	private Context context;
	private LayoutInflater layoutInflater;

	public FriendAdapter(Context c) {
		this.context = c;
		layoutInflater = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (friends == null) {
			return 0;
		} else {
			return friends.size();
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Friend friend = friends.get(position);
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.layout_friend_item,
					null);
		}
		bindView(convertView, friend);
		return convertView;
	}

	public void bindView(View view, Friend friend) {
		TextView tvName = (TextView) view.findViewById(R.id.Tv_ContactName);
		TextView tvContactNumber = (TextView) view
				.findViewById(R.id.Tv_ContactNumber);
		if (friend.getName() != null) {
			tvName.setText(friend.getName());
		}
		if (friend.getCellPhone1() != null) {
			tvContactNumber.setText(friend.getCellPhone1());
		}
	}

	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}

}
