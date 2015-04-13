package com.poliveira.apps.parallaxlistview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by poliveira on 20/10/2014.
 */
class ParallaxHelper {
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
            if (attr == R.styleable.ParallaxListView_enableZoom) {
                mParameters.setZoomEnable(a.getBoolean(attr, false));
            } else if (attr == R.styleable.ParallaxListView_scrollMultiplier) {
                mParameters.setScrollMultiplier(a.getFloat(attr, 0.5f));
            } else if (attr == R.styleable.ParallaxListView_zoomFactor) {
                mParameters.setZoomFactor(a.getFloat(attr, 1.0f));
            }
        }
        a.recycle();
    }

    Parameters getParameters() {
        return mParameters;
    }

    void setParameters(Parameters parameters) {
        mParameters = parameters;
    }

    View getParallaxView() {
        return mParallaxView;
    }

    protected void setParallaxView(View parallaxView) {
        mParallaxView = parallaxView;
    }

    protected ParallaxScrollEvent getParallaxScrollEvent() {
        return mParallaxScrollEvent;
    }

    void setParallaxScrollEvent(ParallaxScrollEvent parallaxScrollEvent) {
        mParallaxScrollEvent = parallaxScrollEvent;
    }

    void onScrollChanged(float scrolled) {
        if (mParallaxView != null) {
            double currentOffset = (scrolled * mParameters.getScrollMultiplier());
            ViewCompat.setTranslationY(mParallaxView, (float) currentOffset);
            double left = Math.min(1, (currentOffset / (mParallaxView.getHeight() * mParameters.getScrollMultiplier())));

            if (mParameters.isZoomEnable()) {
                float zoom = (float) ((left * mParameters.getZoomFactor()) + 1);
                ViewCompat.setScaleX(mParallaxView, zoom);
                ViewCompat.setScaleY(mParallaxView, zoom);
            }
            if (mParallaxScrollEvent != null)
                mParallaxScrollEvent.onScroll(left, currentOffset, mParallaxView);
        }
    }

    void registerParallax(View parallaxWrapper) {
        mParallaxView = parallaxWrapper;
    }
}
