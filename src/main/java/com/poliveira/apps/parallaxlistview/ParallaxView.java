package com.poliveira.apps.parallaxlistview;

import android.view.View;

/**
 * Created by poliveira on 21/10/2014.
 */
public interface ParallaxView {
    /**
     * sets the view as a parallax header
     *
     * @param v header view
     */
    public void setParallaxView(View v);

    public void setScrollEvent(ParallaxScrollEvent scrollEvent);

    public Parameters getParameters();

    public void setParameters(Parameters params);
}
