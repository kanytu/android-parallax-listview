android-parallax-listview
=========================


**Integration**
====

**Step 1.** Add the JitPack repository to your build file

   	repositories {
   	    maven {
   	        url "https://jitpack.io"
   	    }
   	}
   	
**Step 2.** Add the dependency

    dependencies {
    	compile 'com.github.kanytu:android-parallax-listview:v1.1'
    }



**Listeners:**

    //will be called on scroll. Returns the scroll progression percentage. from 0 to 1.0
    ParallaxScrollListener{
       void onScroll(float percentage,float offset, View parallaxView);
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

     parallaxView.setParallaxView(LayoutInflater.from(this).inflate(R.layout.myParallaxView, parallaxScrollView, false));


**Output:**

![ParallaxListView](https://github.com/kanytu/android-parallax-listview/blob/master/screenshots/teste.gif)
![ParallaxScrollView](https://github.com/kanytu/android-parallax-listview/blob/master/screenshots/teste2.gif)
![ParallaxGridView](https://github.com/kanytu/android-parallax-listview/blob/master/screenshots/teste3.gif)
