package com.poliveira.apps.parallaxlistview;

/**
 * Created by poliveira on 20/10/2014.
 */
public class Parameters {
    private boolean isZoomEnable = false;
    private float mScrollMultiplier = 0.5f;
    private float mZoomFactor = 1.0f;

    public boolean isZoomEnable() {
        return isZoomEnable;
    }

    public void setZoomEnable(boolean isZoomEnable) {
        this.isZoomEnable = isZoomEnable;
    }

    public float getScrollMultiplier() {
        return mScrollMultiplier;
    }

    public void setScrollMultiplier(float scrollMultipler) {
        mScrollMultiplier = scrollMultipler;
    }

    public float getZoomFactor() {
        return mZoomFactor;
    }

    public void setZoomFactor(float zoomFactor) {
        mZoomFactor = zoomFactor;
    }
}
