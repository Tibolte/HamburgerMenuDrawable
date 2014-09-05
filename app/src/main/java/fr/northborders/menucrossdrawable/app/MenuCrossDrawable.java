package fr.northborders.menucrossdrawable.app;

import android.graphics.*;
import android.graphics.drawable.Drawable;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.util.Property;

/**
 * Created by thibaultguegan on 05/09/2014.
 */
public class MenuCrossDrawable extends Drawable {
    private static final long ANIMATION_DURATION = 300;

    private Paint mLinePaint;
    private Paint mBackgroundPaint;

    private float[] mPoints = new float[12];
    private final RectF mBounds = new RectF();

    private int mStrokeWidth = 10;
    private int mLineColor = Color.YELLOW;
    private int mBackgroundColor = Color.RED;

    private boolean mMenuMode;

    public MenuCrossDrawable(){
        this(10, Color.YELLOW, Color.RED);
    }

    public MenuCrossDrawable(int strokeWidth, int lineColor, int backgroundColor){
        mStrokeWidth = strokeWidth;
        mLineColor = lineColor;
        mBackgroundColor = backgroundColor;

        setUp();
    }

    private void setUp(){
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(mLineColor);
        mLinePaint.setStrokeWidth(mStrokeWidth);
        mLinePaint.setStrokeCap(Paint.Cap.ROUND);

        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setColor(mBackgroundColor);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);

        float padding = bounds.width()/4;

        mBounds.left = bounds.left + padding;
        mBounds.right = bounds.right - padding;
        mBounds.top = bounds.top + padding;
        mBounds.bottom = bounds.bottom - padding;

        setUpMenuLines();
    }

    private void setUpMenuLines(){

        mPoints[0] = mBounds.left;
        mPoints[1] = mBounds.top;
        mPoints[2] = mBounds.right;
        mPoints[3] = mBounds.top;

        mPoints[4] = mBounds.left;
        mPoints[5] = mBounds.centerY();
        mPoints[6] = mBounds.right;
        mPoints[7] = mBounds.centerY();

        mPoints[8] = mBounds.left;
        mPoints[9] = mBounds.bottom;
        mPoints[10] = mBounds.right;
        mPoints[11] = mBounds.bottom;
    }

    private float x(int pointIndex) {
        return mPoints[xPosition(pointIndex)];
    }

    private float y(int pointIndex) {
        return mPoints[yPosition(pointIndex)];
    }

    private int xPosition(int pointIndex) {
        return pointIndex*2;
    }

    private int yPosition(int pointIndex) {
        return xPosition(pointIndex) + 1;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(mBounds.centerX(), mBounds.centerY(), mBounds.width(), mBackgroundPaint);

        canvas.save();
        canvas.drawLine(x(0), y(0), x(1), y(1), mLinePaint);
        canvas.restore();

        canvas.save();
        canvas.drawLine(x(2), y(2), x(3), y(3), mLinePaint);
        canvas.restore();

        canvas.save();
        canvas.drawLine(x(4), y(4), x(5), y(5), mLinePaint);
        canvas.restore();
    }

    public void toggle(){
        if(mMenuMode){
            animateToMenu();
        } else {
            animateToCross();
        }
        mMenuMode = !mMenuMode;
    }

    private void animateToCross(){
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(this, mPropertyPointAX, mBounds.left),
                ObjectAnimator.ofFloat(this, mPropertyPointAY, mBounds.centerY()),
                ObjectAnimator.ofFloat(this, mPropertyPointBX, mBounds.right),
                ObjectAnimator.ofFloat(this, mPropertyPointBY, mBounds.centerY()),
                ObjectAnimator.ofFloat(this, mPropertyPointEX, mBounds.left),
                ObjectAnimator.ofFloat(this, mPropertyPointEY, mBounds.centerY()),
                ObjectAnimator.ofFloat(this, mPropertyPointFX, mBounds.right),
                ObjectAnimator.ofFloat(this, mPropertyPointFY, mBounds.centerY()),

                //hide middle line
                ObjectAnimator.ofFloat(this, mPropertyPointCX, mBounds.centerX()),
                ObjectAnimator.ofFloat(this, mPropertyPointCY, mBounds.centerY()),
                ObjectAnimator.ofFloat(this, mPropertyPointDX, mBounds.centerX()),
                ObjectAnimator.ofFloat(this, mPropertyPointDY, mBounds.centerY())
        );
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                AnimatorSet set = new AnimatorSet();
                int delta = mStrokeWidth;
                set.playTogether(
                        ObjectAnimator.ofFloat(MenuCrossDrawable.this, mPropertyPointAX, mBounds.left),
                        ObjectAnimator.ofFloat(MenuCrossDrawable.this, mPropertyPointAY, mBounds.top),
                        ObjectAnimator.ofFloat(MenuCrossDrawable.this, mPropertyPointBX, mBounds.right),
                        ObjectAnimator.ofFloat(MenuCrossDrawable.this, mPropertyPointBY, mBounds.bottom),
                        ObjectAnimator.ofFloat(MenuCrossDrawable.this, mPropertyPointEX, mBounds.left),
                        ObjectAnimator.ofFloat(MenuCrossDrawable.this, mPropertyPointEY, mBounds.bottom),
                        ObjectAnimator.ofFloat(MenuCrossDrawable.this, mPropertyPointFX, mBounds.right),
                        ObjectAnimator.ofFloat(MenuCrossDrawable.this, mPropertyPointFY, mBounds.top)
                );
                set.setDuration(ANIMATION_DURATION/2).start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.setDuration(ANIMATION_DURATION/2).start();
    }

    private void animateToMenu()
    {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(this, mPropertyPointAX, mBounds.left),
                ObjectAnimator.ofFloat(this, mPropertyPointAY, mBounds.centerY()),
                ObjectAnimator.ofFloat(this, mPropertyPointBX, mBounds.right),
                ObjectAnimator.ofFloat(this, mPropertyPointBY, mBounds.centerY()),
                ObjectAnimator.ofFloat(this, mPropertyPointEX, mBounds.left),
                ObjectAnimator.ofFloat(this, mPropertyPointEY, mBounds.centerY()),
                ObjectAnimator.ofFloat(this, mPropertyPointFX, mBounds.right),
                ObjectAnimator.ofFloat(this, mPropertyPointFY, mBounds.centerY()),

                //show middle line
                ObjectAnimator.ofFloat(this, mPropertyPointCX, mBounds.left),
                ObjectAnimator.ofFloat(this, mPropertyPointCY, mBounds.centerY()),
                ObjectAnimator.ofFloat(this, mPropertyPointDX, mBounds.right),
                ObjectAnimator.ofFloat(this, mPropertyPointDY, mBounds.centerY())
        );
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                AnimatorSet set = new AnimatorSet();
                set.playTogether(
                        ObjectAnimator.ofFloat(MenuCrossDrawable.this, mPropertyPointAX, mBounds.left),
                        ObjectAnimator.ofFloat(MenuCrossDrawable.this, mPropertyPointAY, mBounds.top),
                        ObjectAnimator.ofFloat(MenuCrossDrawable.this, mPropertyPointBX, mBounds.right),
                        ObjectAnimator.ofFloat(MenuCrossDrawable.this, mPropertyPointBY, mBounds.top),
                        ObjectAnimator.ofFloat(MenuCrossDrawable.this, mPropertyPointEX, mBounds.left),
                        ObjectAnimator.ofFloat(MenuCrossDrawable.this, mPropertyPointEY, mBounds.bottom),
                        ObjectAnimator.ofFloat(MenuCrossDrawable.this, mPropertyPointFX, mBounds.right),
                        ObjectAnimator.ofFloat(MenuCrossDrawable.this, mPropertyPointFY, mBounds.bottom)

                );
                set.setDuration(ANIMATION_DURATION/2).start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.setDuration(ANIMATION_DURATION/2).start();
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    private PointProperty mPropertyPointAX = new XPointProperty(0);
    private PointProperty mPropertyPointAY = new YPointProperty(0);
    private PointProperty mPropertyPointBX = new XPointProperty(1);
    private PointProperty mPropertyPointBY = new YPointProperty(1);
    private PointProperty mPropertyPointCX = new XPointProperty(2);
    private PointProperty mPropertyPointCY = new YPointProperty(2);
    private PointProperty mPropertyPointDX = new XPointProperty(3);
    private PointProperty mPropertyPointDY = new YPointProperty(3);
    private PointProperty mPropertyPointEX = new XPointProperty(4);
    private PointProperty mPropertyPointEY = new YPointProperty(4);
    private PointProperty mPropertyPointFX = new XPointProperty(5);
    private PointProperty mPropertyPointFY = new YPointProperty(5);

    private abstract class PointProperty extends Property<MenuCrossDrawable, Float> {

        protected int mPointIndex;

        private PointProperty(int pointIndex) {
            super(Float.class, "point_" + pointIndex);
            mPointIndex = pointIndex;
        }
    }

    private class XPointProperty extends PointProperty {

        private XPointProperty(int pointIndex) {
            super(pointIndex);
        }

        @Override
        public Float get(MenuCrossDrawable object) {
            return object.x(mPointIndex);
        }

        @Override
        public void set(MenuCrossDrawable object, Float value) {
            object.mPoints[object.xPosition(mPointIndex)] = value;
            invalidateSelf();
        }
    }

    private class YPointProperty extends PointProperty {

        private YPointProperty(int pointIndex) {
            super(pointIndex);
        }

        @Override
        public Float get(MenuCrossDrawable object) {
            return object.y(mPointIndex);
        }

        @Override
        public void set(MenuCrossDrawable object, Float value) {
            object.mPoints[object.yPosition(mPointIndex)] = value;
            invalidateSelf();
        }
    }
}
