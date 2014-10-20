package com.poliveira.apps.parallaxlistview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Created by poliveira on 17/10/2014.
 */
public class ParallaxListView extends ListView implements AbsListView.OnScrollListener
{

    public interface ParallaxScrollListener{
        /**
         *
         * @param offsetPercentage scroll progress, [0,1.0]
         * @param parallaxView
         */
        void onScroll(float offsetPercentage, View parallaxView);
    }
    private CustomRelativeLayout mParallaxWrapper;
    private View mParallaxView;
    private Parameters mParameters;
    private ParallaxScrollListener mParallaxScrollListener;

    public ParallaxListView(Context context)
    {
        super(context);
        initializeParameters(null);
        init();
    }

    private void init()
    {
        setOnScrollListener(this);
    }

    public ParallaxListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initializeParameters(attrs);
        init();
    }

    public View getParallaxView()
    {
        return mParallaxView;
    }

    private void initializeParameters(AttributeSet attrs)
    {
        mParameters = new Parameters();
        if (attrs == null)
            return;
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.ParallaxListView);

        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.ParallaxListView_enableZoom:
                    mParameters.setZoomEnable(a.getBoolean(attr, false));
                    break;
                case R.styleable.ParallaxListView_scrollMultiplier:
                    mParameters.setScrollMultiplier(a.getFloat(attr, 0.5f));
                    break;
                case R.styleable.ParallaxListView_zoomFactor:
                    mParameters.setZoomFactor(a.getFloat(attr, 1.0f));
                    break;
            }
        }
        a.recycle();
    }

    public ParallaxListView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        initializeParameters(attrs);
        init();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState)
    {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        if (view.getChildCount() > 0 && mParallaxWrapper!=null)
        {
            float currentOffset =  (-mParallaxWrapper.getTop() * mParameters.getScrollMultiplier());
            mParallaxWrapper.setTranslationY(currentOffset);
            mParallaxWrapper.setClipY(Math.round(currentOffset));
            float left = Math.min(1,(currentOffset / (mParallaxWrapper.getHeight()*mParameters.getScrollMultiplier())));

            if(mParameters.isZoomEnable())
            {
                float zoom = (left*mParameters.getZoomFactor()) + 1;
                mParallaxView.setScaleX(zoom);
                mParallaxView.setScaleY(zoom);
            }
            if(mParallaxScrollListener!=null)
                mParallaxScrollListener.onScroll(left,mParallaxView);
        }
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
    }

    public ParallaxScrollListener getParallaxScrollListener()
    {
        return mParallaxScrollListener;
    }

    public void setParallaxScrollListener(ParallaxScrollListener parallaxScrollListener)
    {
        mParallaxScrollListener = parallaxScrollListener;
    }

    public Parameters getParameters()
    {
        return mParameters;
    }

    public void setParameters(Parameters parameters)
    {
        mParameters = parameters;
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
