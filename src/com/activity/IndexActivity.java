package com.activity;

import com.activity.profile.ProfileActivity;
import com.hengtiansoft.cloudcontact.R;

import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time£º2013-6-21 ÏÂÎç03:51:08 file declare:
 */
public class IndexActivity extends CommonActivity implements
		android.view.View.OnClickListener {

	private ImageView imgProfile;

	@Override
	public void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_index);
	}

	public void initWidget() {
		imgProfile = (ImageView) findViewById(R.id.Img_Profile);
	}

	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.Img_Profile:
			Intent intentToProfile = new Intent(getApplicationContext(),
					ProfileActivity.class);
			startActivity(intentToProfile);
			break;

		}

	}
}
