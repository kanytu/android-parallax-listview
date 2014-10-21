package com.poliveira.apps.parallaxlistview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by poliveira on 20/10/2014.
 */
public class ParallaxHelper {
    private Parameters mParameters;
    private View mParallaxView;
    private ParallaxScrollEvent mParallaxScrollEvent;

    public ParallaxHelper(Context context, AttributeSet attributeSet) {
        initializeParameters(context, attributeSet);
    }

    private void initializeParameters(Context context, AttributeSet attrs) {
        mParameters = new Parameters();
        if (attrs == null)
            return;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ParallaxListView);

        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = a.getIndex(i);
            switch (attr) {
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

    protected Parameters getParameters() {
        return mParameters;
    }

    protected void setParameters(Parameters parameters) {
        mParameters = parameters;
    }

    protected View getParallaxView() {
        return mParallaxView;
    }

    protected void setParallaxView(View parallaxView) {
        mParallaxView = parallaxView;
    }

    protected ParallaxScrollEvent getParallaxScrollEvent() {
        return mParallaxScrollEvent;
    }

    protected void setParallaxScrollEvent(ParallaxScrollEvent parallaxScrollEvent) {
        mParallaxScrollEvent = parallaxScrollEvent;
    }

    protected void onScrollChanged(float scrolled) {
        Log.v("scrolled", scrolled + "");
        if (mParallaxView != null) {
            float currentOffset = (scrolled * mParameters.getScrollMultiplier());
            mParallaxView.setTranslationY(currentOffset);
            float left = Math.min(1, (currentOffset / (mParallaxView.getHeight() * mParameters.getScrollMultiplier())));

            if (mParameters.isZoomEnable()) {
                float zoom = (left * mParameters.getZoomFactor()) + 1;
                mParallaxView.setScaleX(zoom);
                mParallaxView.setScaleY(zoom);
            }
            if (mParallaxScrollEvent != null)
                mParallaxScrollEvent.onScroll(left, currentOffset, mParallaxView);
        }
    }

    protected void registerParallax(View parallaxWrapper) {
        mParallaxView = parallaxWrapper;
    }
}
