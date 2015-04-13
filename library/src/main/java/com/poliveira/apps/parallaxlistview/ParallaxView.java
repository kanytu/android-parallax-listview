package com.poliveira.apps.parallaxlistview;

import android.view.View;

/**
 * Created by poliveira on 21/10/2014.
 */
interface ParallaxView {
    /**
     * sets the view as a parallax header
     *
     * @param v header view
     */
    void setParallaxView(View v);

    void setScrollEvent(ParallaxScrollEvent scrollEvent);

    Parameters getParameters();

    void setParameters(Parameters params);
}
