package com.poliveira.apps.parallaxlistview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.widget.RelativeLayout;

/**
 * Created by poliveira on 21/10/2014.
 */
public class CustomRelativeWrapper extends RelativeLayout {

    private int mOffset;

    public CustomRelativeWrapper(Context context) {
        super(context);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.clipRect(new Rect(getLeft(), getTop(), getRight(), getBottom() + mOffset));
        super.dispatchDraw(canvas);
    }

    public void setClipY(int offset) {
        mOffset = offset;
        invalidate();
    }
}

