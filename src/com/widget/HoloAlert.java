package com.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hengtiansoft.cloudcontact.R;

public class HoloAlert extends RelativeLayout {

	private Context context;
	private int backgroundColor = R.color.info;
	private int textSize;
	TextView tvContent;
	public boolean isShow;
	private int Animationtime = 400;

	private AnimationSet showAnimation;
	private AnimationSet hideAnimation;
	private HoloAlert holoAlert;

	public HoloAlert(Context context, AttributeSet attrs) {
		super(context, attrs);
		isShow = false;
		holoAlert = this;
		initWidget(context);
	}

	public HoloAlert(Context context) {
		super(context);
		initWidget(context);
	}

	public void initWidget(Context c) {
		this.context = c;
		isShow = false;
		tvContent = new TextView(context);
		tvContent.setTextColor(Color.WHITE);
		tvContent.setText("正在登入中");
		showAnimation = getShowAnimation();
		hideAnimation = getHideAnimation();
		tvContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
		tvContent.setLayoutParams(getHorizontalParams());
		addView(tvContent);
		// setBackgroundResource(R.color.confirm);
		setBackgroundColor(getResources().getColor(R.color.info));
	}

	public LayoutParams getHorizontalParams() {
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		lp.addRule(CENTER_IN_PARENT);
		return lp;
	}

	public void setText(String text) {
		tvContent.setText(text);
	}

	public void show(String alertStr) {
		
		tvContent.setText(alertStr);
		startAnimation(showAnimation);
		setShow(true);
	}

	public void hide() {
		startAnimation(hideAnimation);
		setShow(false);
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public AnimationSet getShowAnimation() {
		AnimationSet animationSet = new AnimationSet(true);
		TranslateAnimation tAni = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF,
				0.0f);
		AlphaAnimation aAni = new AlphaAnimation(0, 1);
		animationSet.addAnimation(tAni);
		animationSet.addAnimation(aAni);
		animationSet.setDuration(Animationtime);
		animationSet.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				 
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				holoAlert.setVisibility(View.VISIBLE);
			}
		});
		return animationSet;

	}

	public AnimationSet getHideAnimation() {
		AnimationSet animationSet = new AnimationSet(true);
		TranslateAnimation tAni = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				-1.0f);
		AlphaAnimation aAni = new AlphaAnimation(1, 0);
		animationSet.addAnimation(tAni);
		animationSet.addAnimation(aAni);
		animationSet.setDuration(Animationtime);
		animationSet.setFillAfter(true);
		animationSet.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				holoAlert.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				holoAlert.setVisibility(View.INVISIBLE);
			}
		});
		return animationSet;
	}

}
