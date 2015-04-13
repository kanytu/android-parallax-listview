package com.poliveira.apps.parallaxlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created by poliveira on 17/10/2014.
 */
public class ParallaxListView extends ListView implements ParallaxScrollEvent, ParallaxView {

    private ParallaxHelper mHelper;
    private CustomRelativeWrapper mParallaxWrapper;
    private View mParallaxView;
    private ParallaxScrollEvent mScrollEvent;


    public ParallaxListView(Context context) {
        super(context);
        mHelper = new ParallaxHelper(context, null);
        init();
    }

    public ParallaxListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHelper = new ParallaxHelper(context, attrs);
        init();
    }

    public ParallaxListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mHelper = new ParallaxHelper(context, attrs);
        init();
    }

    private void init() {
        mHelper.setParallaxScrollEvent(this);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mParallaxWrapper != null)
            mHelper.onScrollChanged(-mParallaxWrapper.getTop());
    }

    /**
     * sets the view as a parallax header
     *
     * @param v header view
     */
    public void setParallaxView(View v) {
        mParallaxView = v;
        mParallaxWrapper = new CustomRelativeWrapper(getContext());
        mParallaxWrapper.addView(mParallaxView);
        addHeaderView(mParallaxWrapper);
        mHelper.registerParallax(mParallaxWrapper);
    }

    @Override
    public void onScroll(double percentage, double offset, View parallaxView) {
        mParallaxWrapper.setClipY(Math.round((float)offset));
        if (mScrollEvent != null)
            mScrollEvent.onScroll(percentage, offset, mParallaxView);
    }

    public void setScrollEvent(ParallaxScrollEvent scrollEvent) {
        mScrollEvent = scrollEvent;
    }

    @Override
    public Parameters getParameters() {
        return mHelper.getParameters();
    }

    @Override
    public void setParameters(Parameters params) {
        mHelper.setParameters(params);
    }


}
