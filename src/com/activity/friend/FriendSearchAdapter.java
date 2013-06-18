package com.activity.friend;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.db.model.Friend;
import com.hengtiansoft.cloudcontact.R;
import com.http.FriendApi;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time：2013-6-18 下午12:34:06 file declare:
 */
public class FriendSearchAdapter extends BaseAdapter {
	List<SearchResult> results;
	private Context context;
	private LayoutInflater layoutInflater;
	private EditText editText;
	AlertDialog alertDialog;

	public FriendSearchAdapter(Context c) {
		context = c;
		layoutInflater = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		if (results != null) {
			return results.size();
		} else {
			Log.e("debug	FriendSearchAdapter", "null");
			return 0;
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
		SearchResult result = results.get(position);
		if (convertView == null) {
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_friend_search_item, null);
		}
		bindView(convertView, result);
		return convertView;
	}

	public void bindView(View view, SearchResult result) {
		TextView tvName = (TextView) view.findViewById(R.id.Tv_ContactName);
		Button btnAdd = (Button) view.findViewById(R.id.Btn_Add_Friend);

		if (result.getType() == 0) {
			tvName.setText("未找到该用户");
			btnAdd.setVisibility(View.INVISIBLE);
		} else {
			final Friend friend = result.getFriend();
			if (friend.getName() != null) {
				if (result.getType() == 1) {
					tvName.setText(friend.getName() + "(已是好友)");
					btnAdd.setVisibility(View.INVISIBLE);
				} else {
					tvName.setText(friend.getName());
					btnAdd.setVisibility(View.VISIBLE);
				}
			}
		 
			btnAdd.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// Toast.makeText(context, "请求已发送"+friend.getId(),
					// Toast.LENGTH_LONG).show();
					// FriendApi.addFriend(firendId, msg)
					editText = new EditText(context);
					alertDialog = new AlertDialog.Builder(context)
							.setTitle("请输入请求信息")
							.setIcon(android.R.drawable.ic_dialog_info)
							.setView(editText)
							.setPositiveButton("确定",
									getPositiveBtnListenner(friend))
							.setNegativeButton("取消",
									getNegaticeButtonListenner()).show();
				}
			});

		}
	}

	public android.content.DialogInterface.OnClickListener getPositiveBtnListenner(
			final Friend friend) {

		android.content.DialogInterface.OnClickListener clickListener = new android.content.DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(context, "请求已发送",
						 Toast.LENGTH_LONG).show();
				new Thread() {
					public void run() {
						FriendApi.addFriend(friend.getId() + "", editText
								.getText().toString());
					};
				}.start();
			}

		};
		return clickListener;
	}

	public android.content.DialogInterface.OnClickListener getNegaticeButtonListenner() {
		android.content.DialogInterface.OnClickListener clickListener = new android.content.DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}

		};
		return clickListener;
	}

	public List<SearchResult> getResults() {
		return results;
	}

	public void setResults(List<SearchResult> r) {
		this.results = r;
		notifyDataSetChanged();
	}

}
