package com.activity.contacts;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.db.model.Contact;
import com.db.model.Friend;
import com.hengtiansoft.cloudcontact.R;
import com.http.ContactApi;
import com.http.FriendApi;
import com.model.FriendItem;
import com.util.ContactUtil;
import com.view.DropBottomView;
import com.widget.DropBottomListView;
import com.widget.OprationDialog;
import com.widget.SideBar;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time��2013-6-10 ����02:24:05 file declare:
 */
public class ContactFragment extends Fragment {

	private DropBottomListView contactListView;
	private OldContactAdapter cAdapter;
	private ContactAdapter contactAdapter;
	private View view;
	private ContactUtil contactUtil;
	private final int MSG_SHOW_CONTACT = 1;
	private UiHandler uiHandler;
	private DropBottomView dropBottomView;
	private List<FriendItem> contacts = null;
	private LayoutInflater layoutInflater;
	private Button btnUoload;
	private Button btnRestore;
	private ContactUtil conUtil;
	private SideBar sideBar;
	private TextView mDialogText;
	private RelativeLayout layoutProgress;
	private Activity activity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		layoutInflater = inflater;
		view = inflater.inflate(R.layout.fragment_contacts, null);
		initWidget();
		return view;
	}

	public void initData() {
		contactUtil = new ContactUtil(getActivity());
	}

	public void initWidget() {
		activity = getActivity();
		sideBar = (SideBar) view.findViewById(R.id.sideBar);
		dropBottomView = (DropBottomView) view
				.findViewById(R.id.DropBottomView_Contact);
		contactListView = (DropBottomListView) view
				.findViewById(R.id.ListView_Contacts);
		contactAdapter = new ContactAdapter(getActivity());
		mDialogText = (TextView) view.findViewById(R.id.alphaText);
		sideBar.setListView(contactListView);
		sideBar.setTextView(mDialogText);
		contactListView.setAdapter(contactAdapter);
		layoutProgress = (RelativeLayout) view
				.findViewById(R.id.Layout_Progress);
		// addBottomVidw();
		uiHandler = new UiHandler();

		contactListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				FriendItem friendItem = contactAdapter.getFriends().get(
						position);

				Log.d("debug", "" + friendItem);
				OprationDialog oprationDialog = new OprationDialog(activity);
				oprationDialog.showDialog(friendItem);
			}

		});

		updeData();

	}

	public void updeData() {
		new Thread() {
			public void run() {

				List<FriendItem> friendItems = getContacts();
				List<FriendItem> frinds = FriendApi.getFriendItems();
				friendItems.addAll(frinds);

				Message msg = new Message();
				msg.what = MSG_SHOW_CONTACT;
				msg.obj = friendItems;
				uiHandler.sendMessage(msg);

			};
		}.start();
	}

	public void addTextView() {

	}

	public void addBottomVidw() {
		Context context = getActivity();
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater
				.inflate(R.layout.layout_bottom_contact, null);
		btnUoload = (Button) view.findViewById(R.id.Btn_Upload);
		btnRestore = (Button) view.findViewById(R.id.Btn_Restore);
		dropBottomView.setView(view);
		contactListView.setDropBottom(dropBottomView);

		btnUoload.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread() {
					@Override
					public void run() {
						super.run();

						// ContactApi.backUpCpntacts(contacts);

					}
				}.start();

			}
		});

		btnRestore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ContactApi.restoreContacts();

			}
		});
	}

	public List<FriendItem> getContacts() {
		List<FriendItem> friendItems;
		contactUtil = new ContactUtil(getActivity());
		List<Contact> contacts = contactUtil.query();
		friendItems = contactUtil.getFriendItem(contacts);
		return friendItems;
	}

	class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			switch (msg.what) {
			case MSG_SHOW_CONTACT:
				contacts = (List<FriendItem>) msg.obj;
				contactAdapter.setContacts(contacts);
				layoutProgress.setVisibility(View.GONE);
				sideBar.setVisibility(View.VISIBLE);
				// cAdapter.setContacts(contacts);
				// cAdapter.notifyDataSetChanged();
				break;

			default:
				break;
			}
		}
	}

}
