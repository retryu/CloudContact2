package com.activity.message;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.db.model.Message;
import com.hengtiansoft.cloudcontact.R;

public class MessageAdapter extends BaseAdapter {

	private List<Message> msgs;
	private Context context;

	private LayoutInflater layoutInflater;

	public MessageAdapter(Context c, List<Message> msgs) {
		context = c;
		this.msgs = msgs;
	}

	public MessageAdapter(Context c) {
		context = c;
		layoutInflater = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (msgs == null) {
			return 0;
		} else {
			return msgs.size();
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
		// TODO Auto-generated method stub
		Message msg = msgs.get(position);
		if (convertView == null) {
			convertView = bindView(convertView, msg);
		}
		return convertView;

	}

	public View bindView(View v, Message msg) {
		View view = layoutInflater.inflate(R.layout.layout_message_item, null);
		TextView tvMsg = (TextView) view.findViewById(R.id.Tv_Msg);
		Button btnYes = (Button) view.findViewById(R.id.Btn_Msg_Yes);
		Button btnNo = (Button) view.findViewById(R.id.Btn_Msg_No);
		if (msg.getMsg() != null) {
			tvMsg.setText(msg.getMsg());
		}
		return view;
	}

	public List<Message> getMsgs() {
		return msgs;
	}

	public void setMsgs(List<Message> msgs) {
		this.msgs = msgs;
	}

}
