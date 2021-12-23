package com.wind.animatable;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * created by wind on 2021/6/15:9:53 上午
 */
public class ScaleXYAnimation implements Animatable.Animation {

    private AnimatorSet animatorSet;
    public ScaleXYAnimation(Builder builder){

        ObjectAnimator scaleXAnimator= ObjectAnimator.ofFloat( builder.getTarget(),"scaleX",builder.getValues());
        ObjectAnimator scaleYAnimator=  ObjectAnimator.ofFloat( builder.getTarget(),"scaleY",builder.getValues());

        scaleXAnimator.setRepeatMode(builder.getRepeatMode());
        scaleXAnimator.setRepeatCount(builder.getRepeatCount());

        scaleYAnimator.setRepeatMode(builder.getRepeatMode());
        scaleYAnimator.setRepeatCount(builder.getRepeatCount());
        animatorSet=new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator,scaleYAnimator);
        if (builder.getDuration()>0) {
            animatorSet.setDuration(builder.getDuration());
        }
    }

    public static Builder newBuilder(View target){
        return new Builder(target);
    }
    @Override
    public void start() {

        animatorSet.start();
    }

    @Override
    public void cancel() {
        if (animatorSet!=null){
            animatorSet.cancel();
        }
    }

    public static class Builder extends Animatable.AnimationBuilder{

        public Builder(View target) {
            super(target);
        }

        @Override
        public Animatable.Animation build() {
            return new ScaleXYAnimation(this);
        }
    }
}
