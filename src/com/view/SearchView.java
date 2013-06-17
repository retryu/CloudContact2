package com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hengtiansoft.cloudcontact.R;

public class SearchView extends RelativeLayout {

	private Context context;
	private LayoutInflater layoutInflater;
	private Button btnSearch;

	public SearchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public void init(Context c) {
		context = c;
		layoutInflater = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(R.layout.layout_searchview, null);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);
		addView(view, layoutParams);
		btnSearch = (Button) view.findViewById(R.id.Btn_Search);
	}

	public void setOnSearchCLick(OnClickListener clickListener) {
		btnSearch.setOnClickListener(clickListener);
	}

}
