package com.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.db.model.Contact;

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
//			Log.e("debug", "" + displayName);
			contact.setName(displayName);
			int count = cursor.getInt(cursor.getColumnIndex(HAS_PHONE_NUMBER));
			String selection = CONTACT_ID + "=" + _id;
			if (count > 0) {
				Cursor phc = contentResolver.query(PHONES_URI, null, selection,
						null, null);
				if (count >= 1) {
					phc.moveToNext();
					String phoneNumber1 = phc.getString(phc
							.getColumnIndex(PHONE_NUMBER));
					contact.setCellphone1(phoneNumber1);
				}
				if (count >= 2) {
					phc.moveToNext();
					String phoneNumber2 = phc.getString(phc
							.getColumnIndex(PHONE_NUMBER));
					contact.setCellphone2(phoneNumber2);
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
//			Log.i("debug", contact + "");

		}
		cursor.close();
		return contacts;
	}
}
