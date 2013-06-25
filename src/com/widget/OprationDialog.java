package com.widget;

import javax.security.auth.PrivateCredentialPermission;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.db.model.Contact;
import com.db.model.Friend;
import com.hengtiansoft.cloudcontact.R;
import com.model.FriendItem;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time：2013-6-25 下午02:29:26 file declare:
 */
public class OprationDialog extends Dialog {

	private LayoutInflater layoutInflater;
	private View view;
	private RelativeLayout layoutCell1;
	private RelativeLayout layoutCell2;
	private Activity activity;

	public OprationDialog(Activity a) {
		super(a);
		init(a);
		activity = a;
	}

	public void init(Context context) {
		setContentView(R.layout.layout_contact_oprate);
		layoutCell1 = (RelativeLayout) findViewById(R.id.Layout_Cell1);
		layoutCell2 = (RelativeLayout) findViewById(R.id.Layout_Cell2);
	}

	public void showDialog(FriendItem friendItem) {
		String number1 = null;
		String number2 = null;
		String name = null;
		boolean needShow = false;
		if (friendItem.getType() == 1) {
			Friend friend = friendItem.getFriend();
			number1 = friend.getCellPhone1();
			number2 = friend.getCellPhone2();
			name = friend.getName();
		}
		if (friendItem.getType() == 2) {
			Contact contact = friendItem.getContact();
			number1 = contact.getCellphone1();
			number2 = contact.getCellphone2();
			name = contact.getName();
		}
		setTitle(name);
		if (number1 == null || number1.equals("")) {
			layoutCell1.setVisibility(View.GONE);
			layoutCell1.setVisibility(View.VISIBLE);
		} else {
			needShow = true;
			bindCell1(number1);

		}

		if (number2 == null || number1.equals("")) {
			layoutCell2.setVisibility(View.GONE);
		} else {
			needShow = true;
			layoutCell2.setVisibility(View.VISIBLE);
			bindCell2(number2);
		}
		if (needShow == true) {
			show();
		}
	}

	public void bindCell1(final String number) {
		TextView tvNumber = (TextView) layoutCell1
				.findViewById(R.id.Tv_Number1);
		ImageView imgMessage = (ImageView) layoutCell1
				.findViewById(R.id.Img_Phone1);
		tvNumber.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("tel:" + number);
				Intent intent = new Intent(Intent.ACTION_CALL, uri);
				activity.startActivity(intent);
			}
		});
		imgMessage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri smsToUri = Uri.parse("smsto://" + number);
				Intent mIntent = new Intent(
						android.content.Intent.ACTION_SENDTO, smsToUri);
				activity.startActivity(mIntent);
			}
		});
	}

	public void bindCell2(final String number) {
		TextView tvNumber = (TextView) layoutCell2
				.findViewById(R.id.Tv_Number1);
		ImageView imgMessage = (ImageView) layoutCell2
				.findViewById(R.id.Img_Phone1);
		tvNumber.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("tel:" + number);
				Intent intent = new Intent(Intent.ACTION_CALL, uri);
				activity.startActivity(intent);
			}
		});
		imgMessage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri smsToUri = Uri.parse("smsto://" + number);
				Intent mIntent = new Intent(
						android.content.Intent.ACTION_SENDTO, smsToUri);
				activity.startActivity(mIntent);
			}
		});
	}

}
