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

     <!--Listview-->
     <view
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            class="com.poliveira.apps.parallaxlistview.ParallaxListView"
            app:enableZoom="true"
            android:id="@+id/parallaxListview"
            app:zoomFactor="0.7"
            app:scrollMultiplier="0.5"
     />
     <!--SCrollView-->
     <view
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            class="com.poliveira.apps.parallaxlistview.ParallaxScrollView"
            app:enableZoom="true"
            android:id="@+id/parallaxScrollview"
            app:zoomFactor="0.7"
            app:scrollMultiplier="0.5"
     />

**Code usage**

     --Listview--
     ParallaxListView parallaxListView = (ParallaxListView) findViewById(R.id.parallaxListView);
     parallaxListView.setParallaxView(LayoutInflater.from(this).inflate(R.layout.myParallaxView, parallaxListView, false));

     --ScrollView--
     ParallaxScrollView parallaxScrollView = (ParallaxScrollView) findViewById(R.id.parallaxScrollView);
     parallaxScrollView.setParallaxView(LayoutInflater.from(this).inflate(R.layout.myParallaxView, parallaxScrollView, false));
**Output:**

![alt tag](https://github.com/kanytu/android-parallax-listview/blob/master/screenshots/teste.gif)
![alt tag](https://github.com/kanytu/android-parallax-listview/blob/master/screenshots/teste2.gif)
