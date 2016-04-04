package com.example.groupimage.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.TypedValue;

/**
 * Created by hongwei on 3/21/16.
 */
public class GroupFaceUtil {
    private static final String TAG = "GroupFaceUtil";

    // space between images
    private static final int PADDING = 1;


    ///////
    // helper methods
    //////

    /**
     * Scale Image
     *
     * @param paramFloat
     * @param bitmap
     * @return scaled bitmap
     */
    private static Bitmap scaleBitmap(float paramFloat, Bitmap bitmap) {
        Matrix localMatrix = new Matrix();
        localMatrix.postScale(paramFloat, paramFloat);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), localMatrix, true);
    }

    /**
     * dip to px
     *
     * @param context
     * @param dipValue
     * @return pxValue
     */
    private static int dip2px(Context context, float dipValue) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dipValue, context.getResources().getDisplayMetrics()) + 0.5f);

    }
}
