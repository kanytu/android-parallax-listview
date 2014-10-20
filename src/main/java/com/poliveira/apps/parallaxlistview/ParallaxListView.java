package com.poliveira.apps.parallaxlistview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Created by poliveira on 17/10/2014.
 */
public class ParallaxListView extends ListView implements ParallaxScrollEvent
{

    private ParallaxHelper mHelper;
    private CustomRelativeLayout mParallaxWrapper;
    private View mParallaxView;
    private ParallaxScrollEvent mScrollEvent;


    public ParallaxListView(Context context)
    {
        super(context);
        mHelper = new ParallaxHelper(context, null);
        init();
    }

    private void init()
    {
        mHelper.setParallaxScrollEvent(this);
    }

    public ParallaxListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mHelper = new ParallaxHelper(context, attrs);
        init();
    }

    public ParallaxListView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mHelper = new ParallaxHelper(context, attrs);
        init();
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt)
    {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mParallaxWrapper != null)
            mHelper.onScrollChanged(-mParallaxWrapper.getTop());
    }

    /**
     * sets the view as a parallax header
     *
     * @param v header view
     */
    public void setParallaxView(View v)
    {
        mParallaxView = v;
        mParallaxWrapper = new CustomRelativeLayout(getContext());
        mParallaxWrapper.addView(mParallaxView);
        addHeaderView(mParallaxWrapper);
        mHelper.registerParallax(mParallaxWrapper);
    }

    @Override
    public void onScroll(float percentage, float offset, View parallaxView)
    {
        mParallaxWrapper.setClipY(Math.round(offset));
        if (mScrollEvent != null)
            mScrollEvent.onScroll(percentage, offset, mParallaxView);
    }

    public void setScrollEvent(ParallaxScrollEvent scrollEvent)
    {
        mScrollEvent = scrollEvent;
    }

    static class CustomRelativeLayout extends RelativeLayout
    {

        private int mOffset;

        public CustomRelativeLayout(Context context)
        {
            super(context);
        }

        @Override
        protected void dispatchDraw(Canvas canvas)
        {
            canvas.clipRect(new Rect(getLeft(), getTop(), getRight(), getBottom() + mOffset));
            super.dispatchDraw(canvas);
        }

        public void setClipY(int offset)
        {
            mOffset = offset;
            invalidate();
        }
    }
}
