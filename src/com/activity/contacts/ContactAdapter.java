package com.activity.contacts;

import java.util.List;

import com.db.model.Contact;
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
public class ContactAdapter extends BaseAdapter {

	List<Contact> contacts;
	private Context context; 
	private LayoutInflater layoutInflater;

	public ContactAdapter(Context c) {
		this.context = c;
		layoutInflater = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub

		if (contacts == null) {
			return 0;
		} else {
			return contacts.size();
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
		if (convertView == null) {
			View view = layoutInflater.inflate(R.layout.layout_friend_item,
					null);
			Contact contact = contacts.get(position);
			bindView(view, contact);
			return view;
		}
		return convertView;
	}

	public void bindView(View view, Contact contact) {
		TextView tvName = (TextView) view.findViewById(R.id.Tv_ContactName);
		TextView tvNumber = (TextView) view.findViewById(R.id.Tv_ContactNumber);
		if (contact.getName() != null) {
			tvName.setText(contact.getName());
		}
		if (contact.getCellphone1() != null) {
			tvNumber.setText(contact.getCellphone1());
		}
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

}
