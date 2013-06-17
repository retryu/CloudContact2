package com.view;

import com.util.AnimationUtil;
import com.util.UnitsUtil;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

/**
 * @author retryu listview µÄµ×²¿view
 * 
 */
public class DropBottomView extends RelativeLayout implements DropBottom {

	private Context context;
	private Animation showAnimation;
	private Animation hideAniamtion;
	private View view;
	private boolean isShow;
	private  View  content;
	
	private  boolean  animationisng;

	private int top;
	private int right;

	public DropBottomView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public DropBottomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
 
	public DropBottomView(Context context) {
		super(context);
		init(context);

	}

	public void init(Context c) {
		this.context = c;
		animationisng=false;
		isShow = true;
		showAnimation = getShowAnimation();
		hideAniamtion = getHideAnimation();
		content=this;
	}

	public void setView(View v) {
		this.view = v;
		LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		addView(v, layoutParams);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);

		right = r;
		top = t;
		Log.e("debug", "right:"+right+"  top:"+top);
	}

	@Override
	public void show(int  diss) {
		if (isShow == false&&animationisng==false) {
			Log.e("debug  ", "  show  diss:"+diss);  
			startAnimation(showAnimation);
		}
	}

	@Override
	public void hide(int  diss) {
		if (isShow == true&&animationisng==false) {
			Log.e("debug  ", "  hide  diss:"+diss);
			startAnimation(hideAniamtion);
		}
	}

	@Override
	public boolean isShow() {
		// TODO Auto-generated method stub
		return false;
	}

	public Animation getShowAnimation() {
		Animation showAniamtion = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -1);
		showAniamtion.setDuration(300);
		showAniamtion.setInterpolator(AnimationUtils.loadInterpolator(context, R.anim.accelerate_decelerate_interpolator));
		showAniamtion.setFillAfter(false);
//		Log.e("debug", "  show before  right:"+right+"  top:"+top);
		showAniamtion.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				isShow=true;
				animationisng=true;
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				animationisng=false;
				isShow = true;
//				Log.e("debug", "  show after  right:"+right+"  top:"+top);
				content.clearAnimation();
				int  diss=content.getBottom()-content.getTop();
				content.layout(content.getLeft(), content.getTop()-diss, content.getRight(), content.getBottom()-diss);
			}
		});
		return showAniamtion;
	}

	public Animation getHideAnimation() {
		final Animation hideAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);
		hideAnimation.setDuration(300);
		hideAnimation.setInterpolator(AnimationUtils.loadInterpolator(context, R.anim.accelerate_decelerate_interpolator));
		hideAnimation.setFillAfter(false);
//		Log.e("debug", "  hide before  right:"+right+"  top:"+top);

		hideAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				animationisng=true;

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				animationisng=false;
				isShow = false;
//				Log.e("debug", "  hide after  right:"+right+"  top:"+top);
				content.clearAnimation();
				int  diss=content.getBottom()-content.getTop();
				content.layout(content.getLeft(), content.getTop()+diss, content.getRight(), content.getBottom()+diss);

			}
		});
		return hideAnimation;
	}

}
