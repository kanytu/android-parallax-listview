package com.poliveira.apps.parallaxlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

/**
 * Created by poliveira on 20/10/2014.
 */
public class ParallaxScrollView extends ScrollView
{
    private final ParallaxHelper mHelper;
    private RelativeLayout mWrapper;
    private View mInternalView;

    public ParallaxScrollView(Context context)
    {
        super(context);
        mHelper = new ParallaxHelper(context, null);
        init();
    }

    public void setScrollEvent(ParallaxScrollEvent scrollEvent)
    {
        mHelper.setParallaxScrollEvent(scrollEvent);
    }

    public ParallaxScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mHelper = new ParallaxHelper(context, attrs);
        init();
    }

    public ParallaxScrollView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mHelper = new ParallaxHelper(context, attrs);
        init();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt)
    {
        super.onScrollChanged(l, t, oldl, oldt);
        mHelper.onScrollChanged(getScrollY());
    }

    private void init()
    {

    }

    private Parameters getParameters()
    {
        return mHelper.getParameters();
    }

    private void setParameters(Parameters params)
    {
        mHelper.setParameters(params);
    }

    public void setParallaxView(View parallaxView)
    {
        if (getChildCount() > 0 && getChildAt(0) != null)
        {
            if (parallaxView.getId() <= 0)
                parallaxView.setId(Utils.generateId());
            if (mHelper.getParallaxView() != null)
                mWrapper.removeView(mHelper.getParallaxView());
            mWrapper.addView(parallaxView, 0);
            ((RelativeLayout.LayoutParams) mInternalView.getLayoutParams()).addRule(RelativeLayout.BELOW, parallaxView.getId());
            mHelper.registerParallax(parallaxView);
        }
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params)
    {
        if (getChildCount() == 0)
        {
            mInternalView = child;
            mWrapper = new RelativeLayout(getContext());
            mWrapper.addView(mInternalView);
            super.addView(mWrapper, params);
        }
    }
}
