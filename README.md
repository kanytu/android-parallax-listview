android-parallax-listview
=========================

**Listeners:**

    //will be called on scroll. Returns the scroll progression percentage. from 0 to 1.0
    ParallaxScrollListener{
      onScroll(float offsetPercentage, View parallaxView)
    }

**Parameters:**

     enableZoom - zooms view on scroll (will use zoomFactor)
     zoomFactor - zoom factor
     scrollMultiplier - scroll multiplier (0.5 recommended)

**XML usage:**

     <view
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            class="com.poliveira.apps.parallaxlistview.ParallaxListView"
            app:enableZoom="true"
            android:id="@+id/parallaxListview"
            app:zoomFactor="0.7"
            app:scrollMultiplier="0.5"
     />


**Code usage**

     ParallaxListView parallaxListView = (ParallaxListView) findViewById(R.id.parallaxListView);
     parallaxListView.setParallaxView(LayoutInflater.from(this).inflate(R.layout.myParallaxView, parallaxListView, false));

**Output:**

![alt tag](https://github.com/kanytu/android-parallax-listview/blob/master/screenshots/teste.gif)
