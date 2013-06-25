package com.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts.Data;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;

import com.db.model.Contact;
import com.model.FriendItem;

/**
 * @author chenyuruan the util for contact crud;
 */
public class ContactUtil {
	private Context context;
	private Uri uri;
	private ContentResolver contentResolver;
	// uri
	private static final Uri CONTACTS_URI = ContactsContract.Contacts.CONTENT_URI;
	private static final Uri EMAIL_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;

	// property
	private static String URI_CONTACT = "content://com.android.contacts/contacts";
	private static final String _ID = ContactsContract.Contacts._ID;
	private static final String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
	private static final String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
	private static final String PHONE_NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
	private static final String EMAIL_DATA = ContactsContract.CommonDataKinds.Email.DATA;
	private static final String EMAIL_TYPE = ContactsContract.CommonDataKinds.Email.TYPE;
	private static final Uri PHONES_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
	private static final String CONTACT_ID = ContactsContract.Data.CONTACT_ID;

	public ContactUtil(Context c) {
		context = c;
		uri = Uri.parse("content://com.android.contacts/contacts");
		contentResolver = context.getContentResolver();
	}

	public List<Contact> query() {

		List<Contact> contacts = new ArrayList<Contact>();
		Cursor cursor = contentResolver.query(CONTACTS_URI, null, null, null,
				null);
		while (cursor.moveToNext()) {

			Contact contact = new Contact();
			int _id = cursor.getInt(cursor.getColumnIndex(_ID));
			String displayName = cursor.getString(cursor
					.getColumnIndex(DISPLAY_NAME));
			// Log.e("debug", "" + displayName);
			contact.setName(displayName);
			int count = cursor.getInt(cursor.getColumnIndex(HAS_PHONE_NUMBER));
			String selection = CONTACT_ID + "=" + _id;
			if (count > 0) {
				Cursor phc = contentResolver.query(PHONES_URI, null, selection,
						null, null);
//				if (count >= 1) {
//					phc.moveToNext();
//					String phoneNumber1 = phc.getString(phc
//							.getColumnIndex(PHONE_NUMBER));
//					contact.setCellphone1(phoneNumber1);
//				}
//				if (count >= 2) {
//					phc.moveToNext();
//					String phoneNumber2 = phc.getString(phc
//							.getColumnIndex(PHONE_NUMBER));
//					contact.setCellphone2(phoneNumber2);
//				}
				
				int i=0;
				while(phc.moveToNext()){
					if(i==0){
						String phoneNumber1 = phc.getString(phc
								.getColumnIndex(PHONE_NUMBER));
						contact.setCellphone1(phoneNumber1);
					}
					if(i==1){
						String phoneNumber2 = phc.getString(phc
								.getColumnIndex(PHONE_NUMBER));
						contact.setCellphone2(phoneNumber2);
					}
					i++;
				}
				
				
				phc.close();
			}

			Cursor emailCursor = contentResolver.query(EMAIL_URI, null,
					selection, null, null);
			if (emailCursor.moveToNext()) {
				String email = emailCursor.getString(emailCursor
						.getColumnIndex(EMAIL_DATA));
				contact.setEmail(email);
			}
			emailCursor.close();
			contacts.add(contact);
			// Log.i("debug", contact + "");

		}
		cursor.close();
		return contacts;
	}

	public void insert(Contact contact) {
		ContentValues values = new ContentValues();
		// 首先向RawContacts.CONTENT_URI执行一个空值插入，目的是获取系统返回的rawContactId
		Uri rawContactUri = context.getContentResolver().insert(
				RawContacts.CONTENT_URI, values);
		long rawContactId = ContentUris.parseId(rawContactUri);
		if (contact.getName() != null) {
			values.clear();
			values.put(Data.RAW_CONTACT_ID, rawContactId);
			values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
			values.put(StructuredName.GIVEN_NAME, contact.getName());
			context.getContentResolver().insert(
					android.provider.ContactsContract.Data.CONTENT_URI, values);

		}
		if (contact.getCellphone1() != null) {
			values.clear();
			values.put(
					android.provider.ContactsContract.Contacts.Data.RAW_CONTACT_ID,
					rawContactId);
			values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
			values.put(Phone.NUMBER, contact.getCellphone1());
			values.put(Phone.TYPE, Phone.TYPE_MOBILE);
			context.getContentResolver().insert(
					android.provider.ContactsContract.Data.CONTENT_URI, values);
		}
		if (contact.getCellphone2() != null) {
			values.clear();
			values.put(
					android.provider.ContactsContract.Contacts.Data.RAW_CONTACT_ID,
					rawContactId);
			values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
			values.put(Phone.NUMBER, contact.getCellphone1());
			values.put(Phone.TYPE, Phone.TYPE_HOME);
			context.getContentResolver().insert(
					android.provider.ContactsContract.Data.CONTENT_URI, values);
		}

		if (contact.getEmail() != null) {
			values.clear();
			values.put(
					android.provider.ContactsContract.Contacts.Data.RAW_CONTACT_ID,
					rawContactId);
			values.put(
					android.provider.ContactsContract.Contacts.Data.RAW_CONTACT_ID,
					rawContactId);
			values.put(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE);
			values.put(Email.DATA, contact.getEmail());
			values.put(Email.TYPE, Email.TYPE_WORK);
			context.getContentResolver().insert(
					android.provider.ContactsContract.Data.CONTENT_URI, values);
		}
		context.getContentResolver().insert(
				android.provider.ContactsContract.Data.CONTENT_URI, values);

	}

	public static ArrayList<FriendItem> getFriendItem(List<Contact> contacts) {
		ArrayList<FriendItem> friendItems = new ArrayList<FriendItem>();
		for (int i = 0; i < contacts.size(); i++) {
			Contact contact = contacts.get(i);

			Log.e("debug", "" + contact);
			FriendItem friendItem = new FriendItem();
			friendItem.setType(2);
			friendItem.setContact(contact);
			String name = contact.getName();
			if (name == null) {
				name = "无名";
			}
			friendItem.setNickName(PingYinUtil.getPingYin(name));
			friendItems.add(friendItem);
		}
		return friendItems;
	}

	public List<Contact> getBackUpContact(List<Contact> contacts) {
		boolean isExits = false;
		List<Contact> localContacts = query();
		List<Contact> backContacts = new ArrayList<Contact>();
		int j = 0;
		for (int i = 0; i < contacts.size(); i++) {
			Contact contact = contacts.get(i);
			isExits = isExits(localContacts, contact);
			String contactStr = contact.getCellphone1() + contact.getName();
			if (isExits == false) {
				Log.e("debug", "i:" + i + "   insert:" + contactStr);
				backContacts.add(contact);
			}
		}
		return backContacts;
	}

	public boolean isExits(List<Contact> localContacts, Contact contact) {

		String contactStr = contact.getCellphone1() + contact.getName();
		String localStr = "";
		if (contactStr.equals("") || contactStr == null) {
			return true;
		}
		for (int i = 0; i < localContacts.size(); i++) {
			Contact localContact = localContacts.get(i);
			if (localContact.getCellphone1() == null) {
				localStr = localContact.getName();
			} else {
				localStr = localContact.getCellphone1()
						+ localContact.getName();
			}
			if (localStr != null && contactStr != null) {
				if (localStr.equals(contactStr)) {
					return true;
				}
			}
		}
		return false;

	}

}
