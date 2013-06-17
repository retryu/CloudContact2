package com.activity.contacts;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.db.model.Contact;
import com.hengtiansoft.cloudcontact.R;
import com.http.ContactApi;
import com.util.ContactUtil;
import com.view.DropBottomView;
import com.widget.DropBottomListView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time£º2013-6-10 ÏÂÎç02:24:05 file declare:
 */
public class ContactFragment extends Fragment {

	private DropBottomListView contactListView;
	private ContactAdapter contactAdapter;
	private View view;
	private ContactUtil contactUtil;
	private final int MSG_SHOW_CONTACT = 1;
	private UiHandler uiHandler;
	private DropBottomView dropBottomView;
	private List<Contact> contacts = null;

	private Button btnUoload;
	private Button btnRestore;
	private ContactUtil conUtil;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_contacts, null);
		initWidget();
		return view;
	}

	public void initData() {
		contactUtil = new ContactUtil(getActivity());

	}

	public void initWidget() {
		dropBottomView = (DropBottomView) view
				.findViewById(R.id.DropBottomView_Contact);
		contactListView = (DropBottomListView) view
				.findViewById(R.id.ListView_Contacts);
		contactAdapter = new ContactAdapter(getActivity());
		contactListView.setAdapter(contactAdapter);
		addBottomVidw();
		uiHandler = new UiHandler();
		new Thread() {
			public void run() {

				List<Contact> contacts = getContacts();
				Message msg = new Message();
				msg.what = MSG_SHOW_CONTACT;
				msg.obj = contacts;
				uiHandler.sendMessage(msg);

			};
		}.start();
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

						ContactApi.backUpCpntacts(contacts);

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

	public List<Contact> getContacts() {
		contactUtil = new ContactUtil(getActivity());
		List<Contact> contacts = contactUtil.query();

		return contacts;
	}

	class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			switch (msg.what) {
			case MSG_SHOW_CONTACT:
				contacts = (List<Contact>) msg.obj;
				contactAdapter.setContacts(contacts);
				contactAdapter.notifyDataSetChanged();
				break;

			default:
				break;
			}

		}
	}
}
