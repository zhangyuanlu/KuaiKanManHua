package com.zyl.kuaikan.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

import com.zyl.kuaikan.R;

public class CountDwonButton extends AppCompatButton implements ValueAnimator.AnimatorUpdateListener,Animator.AnimatorListener{
    private int countDown=60;
    private ValueAnimator animator;
    private Context context;
    private boolean prepare;
    public CountDwonButton(Context context) {
        this(context,null);
    }

    public CountDwonButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CountDwonButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCountDown(int countDown) {
        this.countDown = countDown;
        prepareAnim();
    }
    private void prepareAnim(){
        animator=ValueAnimator.ofInt(countDown,0);
        animator.setDuration(1000*countDown);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(this);
        animator.addListener(this);
        prepare=true;
    }
    public int getCountDown() {
        return countDown;
    }

    public void startCountDown(Context context){
        this.context=context;
        if(!prepare){
            prepareAnim();
        }
        animator.start();
        setEnabled(false);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int count= (int) valueAnimator.getAnimatedValue();
        setText(String.format(context.getString(R.string.count_down_time), count));
    }

    @Override
    public void onAnimationStart(Animator animator) {

    }

    @Override
    public void onAnimationEnd(Animator animator) {
        setEnabled(true);
        setText(context.getString(R.string.get_verify_code));
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }
}
