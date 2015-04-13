package com.poliveira.apps.parallaxlistview;

import android.view.View;

/**
 * Created by poliveira on 20/10/2014.
 */
public interface ParallaxScrollEvent {
    /**
     * @param percentage   scroll progress on the parallax view, [0,1.0]
     * @param offset       total offset
     * @param parallaxView
     */
    void onScroll(double percentage, double offset, View parallaxView);
}
