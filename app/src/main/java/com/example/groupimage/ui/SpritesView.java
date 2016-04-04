package com.example.groupimage.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by hongwei on 3/21/16.
 */
public class SpritesView extends ViewGroup {
    //private static final String TAG = "SpritesView";

    private static final int MAX_IMAGES = 5;

    private ImageView[] mImageViews;
    private String[] mSpriteUrls;

    public SpritesView(Context context) {
        this(context, null, 0);
    }

    public SpritesView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpritesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void loadSpriteUrls(Context context, String[] spriteUrls) {
        mSpriteUrls = spriteUrls;

        for (int i=0; i<mSpriteUrls.length; i++) {
            mImageViews[i].setVisibility(VISIBLE);
            Glide.with(context).load(mSpriteUrls[i]).asBitmap().into(mImageViews[i]);
        }
        for (int i=mSpriteUrls.length; i<MAX_IMAGES; i++) {
            mImageViews[i].setVisibility(GONE);
        }

        this.invalidate(); // refresh
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        switch (getNumImages()) {
            case 1:
                layoutOneSprite();
                break;

            case 2:
                layoutTwoSprites();
                break;

            case 3:
                layoutThreeSprites();
                break;

            case 4:
                layoutFourSprites();
                break;

            case 5:
                layoutFiveSprites();
                break;
        }
    }

    ///////////////
    // helper methods
    ///////////////

    private void init(Context context) {
        mImageViews = new ImageView[MAX_IMAGES];
        for (int i=0; i<MAX_IMAGES; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setVisibility(i == 0 ? VISIBLE : GONE); //show one at init
            imageView.setPadding(0, 0, 0, 0);
            this.addView(imageView);
            mImageViews[i] = imageView;
        }
    }

    private int getNumImages() {
        if (mSpriteUrls == null) {
            return 1;
        }
        if (mSpriteUrls.length < 1) {
            return 1;
        }
        if (mSpriteUrls.length > MAX_IMAGES) {
            return MAX_IMAGES;
        }

        return mSpriteUrls.length;
    }

    private void layoutOneSprite() {
        final int left = this.getPaddingLeft();
        final int top = this.getPaddingTop();
        final int right = this.getMeasuredWidth() - this.getPaddingRight();
        final int bottom = this.getMeasuredHeight() - this.getPaddingBottom();
        final int width = right - left;
        final int height = bottom - top;

        ImageView imageView = mImageViews[0];
        imageView.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));

        imageView.layout(left, top, right, bottom);
    }

    private void layoutTwoSprites() {
        final float scale = 0.75f;
        final int left = this.getPaddingLeft();
        final int top = this.getPaddingTop();
        final int right = this.getMeasuredWidth() - this.getPaddingRight();
        final int bottom = this.getMeasuredHeight() - this.getPaddingBottom();
        final int width = (int) ((right - left) * scale);
        final int height = (int) ((bottom - top) * scale);

        ImageView imageView1 = mImageViews[0];
        ImageView imageView2 = mImageViews[1];

        imageView1.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));
        imageView2.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));

        imageView1.layout(left, top, left + width, top + height);
        imageView2.layout(right - width, bottom - height, right, bottom);
    }

    private void layoutThreeSprites() {
        final float scale = 0.66f;
        final int left = this.getPaddingLeft();
        final int top = this.getPaddingTop();
        final int right = this.getMeasuredWidth() - this.getPaddingRight();
        final int bottom = this.getMeasuredHeight() - this.getPaddingBottom();
        final int width = (int) ((right - left) * scale);
        final int height = (int) ((bottom - top) * scale);

        ImageView imageView1 = mImageViews[0];
        ImageView imageView2 = mImageViews[1];
        ImageView imageView3 = mImageViews[2];

        imageView1.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));
        imageView2.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));
        imageView3.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));

        imageView1.layout(left, top, left + width, top + height);
        imageView2.layout(right - width, top, right, top + height);
        imageView3.layout(left, bottom - height, right, bottom);
    }

    private void layoutFourSprites() {
        final float scale = 0.66f;
        final int left = this.getPaddingLeft();
        final int top = this.getPaddingTop();
        final int right = this.getMeasuredWidth() - this.getPaddingRight();
        final int bottom = this.getMeasuredHeight() - this.getPaddingBottom();
        final int width = (int) ((right - left) * scale);
        final int height = (int) ((bottom - top) * scale);

        ImageView imageView1 = mImageViews[0];
        ImageView imageView2 = mImageViews[1];
        ImageView imageView3 = mImageViews[2];
        ImageView imageView4 = mImageViews[3];

        imageView1.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));
        imageView2.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));
        imageView3.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));
        imageView4.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));

        imageView1.layout(left, top, left + width, top + height);
        imageView2.layout(right - width, top, right, top + height);
        imageView3.layout(left, bottom - height, left + width, bottom);
        imageView4.layout(right - width, bottom - height, right, bottom);
    }

    private void layoutFiveSprites() {
        final float scale = 0.5f;
        final int left = this.getPaddingLeft();
        final int top = this.getPaddingTop();
        final int right = this.getMeasuredWidth() - this.getPaddingRight();
        final int bottom = this.getMeasuredHeight() - this.getPaddingBottom();
        final int width = (int) ((right - left) * scale);
        final int height = (int) ((bottom - top) * scale);

        ImageView imageView1 = mImageViews[0];
        ImageView imageView2 = mImageViews[1];
        ImageView imageView3 = mImageViews[2];
        ImageView imageView4 = mImageViews[3];
        ImageView imageView5 = mImageViews[4];

        imageView1.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));
        imageView2.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));
        imageView3.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));
        imageView4.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));
        imageView5.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));

        imageView1.layout(left, top, left + width, top + height);
        imageView2.layout(right - width, top, right, top + height);
        imageView3.layout(left, top + (bottom - height)/3, right, bottom - (bottom - height)/3);
        imageView4.layout(left, bottom - height, left + width, bottom);
        imageView5.layout(right - width, bottom - height, right, bottom);
    }
}
