package com.wind.animatable;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * created by wind on 6/11/21:8:18 PM
 */
public class TranslationXAnimation extends Animatable.AbsAnimation {

    public TranslationXAnimation(Animatable.AnimationBuilder builder) {
        super(builder);
    }

    public static Builder newBuilder(View target){
        return new Builder(target);
    }

    @Override
    public Animator buildAnimator(Animatable.AnimationBuilder builder) {
        return ObjectAnimator.ofFloat(builder.getTarget(),"translationX",builder.getValues());
    }


    public static class Builder extends Animatable.AnimationBuilder{


        public Builder(View target) {
            super(target);
        }

        public Animatable.Animation build(){
           return new TranslationXAnimation(this);
        }
    }
}
