package com.activity.message;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.activity.message.MessageActivity.UIHandler;
import com.db.model.Message;
import com.hengtiansoft.cloudcontact.R;
import com.http.MessageApi;

public class MessageAdapter extends BaseAdapter {

	private List<Message> msgs;
	private Context context;

	private LayoutInflater layoutInflater;

	private UIHandler uiHandler;

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
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_message_item, null);
		}
		bindView(convertView, msg);
		return convertView;

	}

	public void bindView(View v, final Message msg) {

		TextView tvMsg = (TextView) v.findViewById(R.id.Tv_Msg);
		Button btnYes = (Button) v.findViewById(R.id.Btn_Msg_Yes);
		Button btnNo = (Button) v.findViewById(R.id.Btn_Msg_No);

		if (msg != null) {
			if (msg.isReaded() == true) {
				btnYes.setVisibility(View.INVISIBLE);
				btnNo.setVisibility(View.INVISIBLE);
			}
			if (msg.getMsg_type().equals("0")) {
				String requestName = msg.getRequest_user_name();
				tvMsg.setText(requestName + "希望与你成为朋友");
			}
			btnYes.setOnClickListener(new OnClickListener() {
				@Override    
				public void onClick(View v) {
					new Thread() {
						@Override
						public void run() {
							super.run();
							MessageApi.responseFriend(msg.getMsgId(), true);
							android.os.Message msgOs = new android.os.Message();
							msgOs.obj = msg;
							msgOs.what = MessageActivity.MSG_CHANGE_STATE;
							uiHandler.sendMessage(msgOs);
						}
					}.start();
				}
			});
			btnNo.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					new Thread() {
						@Override
						public void run() {
							super.run();
							MessageApi.responseFriend(msg.getMsgId(), false);
							android.os.Message msgOs = new android.os.Message();
							msgOs.what = MessageActivity.MSG_CHANGE_STATE;
							msgOs.obj = msg;
							uiHandler.sendMessage(msgOs);
						}
					}.start();
				}
			});
		}
	}

	public List<Message> getMsgs() {
		return msgs;
	}

	public void setMsgs(List<Message> msgs) {
		this.msgs = msgs;
	}

	public void addMsg(Message msg) {
		if (msgs == null) {
			msgs = new ArrayList<Message>();
		}
		msgs.add(msg);
		notifyDataSetChanged();
	}

	public void updateMessage(Message msg) {
		for (int i = 0; i < msgs.size(); i++) {
			Message m = msgs.get(i);
			if (m.getMsgId().equals(msg.getMsgId())) {
				m.setReaded(true);
			}
		}
		notifyDataSetChanged();
	}

	public UIHandler getUiHandler() {
		return uiHandler;
	}

	public void setUiHandler(UIHandler uiHandler) {
		this.uiHandler = uiHandler;
	}

}
