package com.util;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time£º2013-6-6 ÏÂÎç05:58:06 file declare:
 */
public class AnimationUtil {
	private AnimationSet fadeOut;
	private AnimationSet fadeIn;

	public AnimationSet getFadeOutAnimation(int  time) {
		fadeOut = new AnimationSet(true);
		TranslateAnimation tAni = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				-1.0f);
		AlphaAnimation aAni = new AlphaAnimation(1, 0);
		fadeOut.addAnimation(tAni);
		fadeOut.addAnimation(aAni);
		fadeOut.setDuration(time);
		fadeOut.setFillAfter(true);
		return fadeOut;
	}

	public AnimationSet getFadeInAniamtion() {
			return  fadeIn;
	}

}
