package com.widget;

import com.view.DropBottom;

import android.content.Context;
import android.test.TouchUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ListView;

public class DropBottomListView extends ListView implements OnTouchListener {

	private Context context;

	private DropBottom dropBottom;
	float y;
	float yOld;

	public DropBottomListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public DropBottomListView(Context context) {
		super(context);
		init(context);

	}

	public void init(Context c) {
		context = c;
		setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
//		// TODO Auto-generated method stub
//
//		int  action=event.getAction();
//		switch (action) {
//		case MotionEvent.ACTION_DOWN:
//			y=event.getY();
//			break;
//
//		case  MotionEvent.ACTION_MOVE:
//			yOld = y;
//			y = event.getY();
//			float  x=event.getX();
//			float  diss=y-yOld;
//			
//			if(y-yOld>30){
//				dropBottom.show((int)diss);
//			}
//			if(y-yOld<-30){  
//				dropBottom.hide((int)diss);
//			}
//			break;
//		case  MotionEvent.ACTION_UP:
//			clearAction();
//			break;
//		}
//		 
		return false;
	}
	
	
	public  void  clearAction(){
		yOld=0;
		y=0;
	}

	public void setDropBottom(DropBottom dropBottom) {
		this.dropBottom = dropBottom;
	}

}
