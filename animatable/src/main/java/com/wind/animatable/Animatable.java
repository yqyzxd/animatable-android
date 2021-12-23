package com.wind.animatable;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * created by wind on 4/30/21:11:19 AM
 */
public class Animatable {

    private Animation mAnimation;
    private Animatable(Animation animation){
      this.mAnimation=animation;
    }

    public Animatable start(){
        mAnimation.start();
        return this;
    }

    public void cancel(){
        if (mAnimation!=null){
            mAnimation.cancel();
        }
    }

    public static Builder newBuilder(){
        return new Builder();
    }
    public static class Builder {

        private Animation animation;


        public Builder which(Animation animation) {
           this.animation=animation;
            return this;
        }

        public Animatable build() {
            return new Animatable(animation);
        }

    }


    public  interface Animation{
        void start();
        void cancel();
    }

    public static abstract  class AbsAnimation implements Animation{
        protected Animator mAnimator;
        public AbsAnimation(AnimationBuilder builder){
            mAnimator=  buildAnimator(builder);
            if (mAnimator instanceof ValueAnimator) {
                ValueAnimator valueAnimator= (ValueAnimator) mAnimator;
                valueAnimator.setRepeatMode(builder.getRepeatMode());
                valueAnimator.setRepeatCount(builder.getRepeatCount());
                if (builder.getInterpolator()!=null)
                    valueAnimator.setInterpolator(builder.getInterpolator());
            }
            mAnimator.setDuration(builder.getDuration());
        }
        public abstract Animator buildAnimator(AnimationBuilder builder);
        @Override
        public void start() {
            if (mAnimator!=null)
                mAnimator.start();
        }

        @Override
        public void cancel() {
            if (mAnimator!=null)
                mAnimator.cancel();
        }
    }


    public static abstract class AnimationBuilder{
        private long duration;
        private int repeatMode;
        private int repeatCount;
        private View target;
        private float[] values;
        private TimeInterpolator interpolator;
        public AnimationBuilder(View target){
            this.target=target;
        }

        public AnimationBuilder duration(long duration){
            this.duration=duration;
            return this;
        }
        public AnimationBuilder repeatMode(int repeatMode){
            this.repeatMode=repeatMode;
            return this;
        }

        public AnimationBuilder repeatCount(int repeatCount){
            this.repeatCount=repeatCount;
            return this;
        }
        public AnimationBuilder interpolator(TimeInterpolator interpolator){
            this.interpolator=interpolator;
            return this;
        }
        public AnimationBuilder values(float... values){
            this.values=values;
            return this;
        }


        public abstract Animation build();


        public float[] getValues() {
            return values;
        }

        public int getRepeatCount() {
            return repeatCount;
        }

        public View getTarget() {
            return target;
        }

        public int getRepeatMode() {
            return repeatMode;
        }

        public long getDuration() {
            return duration;
        }

        public TimeInterpolator getInterpolator() {
            return interpolator;
        }
    }



}
