package com.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hengtiansoft.cloudcontact.R;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time£º2013-6-21 ÏÂÎç02:11:59 file declare:
 */
public class HeadEditText extends RelativeLayout {

	private Context context;
	private TextView tvHead;
	private EditText etCOntent;
	LayoutInflater layoutInflater;
	String name;

	public HeadEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		name = "";
		TypedArray a = context
				.obtainStyledAttributes(attrs, R.styleable.MyEdit);
		name = a.getString(R.styleable.MyEdit_name);
		init(context);
	}

	public HeadEditText(Context context) {
		super(context);
		name = "";
		init(context);
	}

	public void init(Context c) {
		context = c;
		layoutInflater = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(R.layout.layout_head_edittext, null);
		tvHead = (TextView) view.findViewById(R.id.Tv_Head);
		etCOntent = (EditText) view.findViewById(R.id.ET_Content);
		tvHead.setText(name);
		addView(view);
	}

	public void setContent(String content) {
		etCOntent.setText(content);
	}

	public String getContent() {
		String content = etCOntent.getText().toString();
		return content;
	}
}
