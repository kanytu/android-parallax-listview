package com.myapps.parallaxlistview;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.poliveira.apps.parallaxlistview.ParallaxGridView;
import com.poliveira.apps.parallaxlistview.ParallaxListView;
import com.poliveira.apps.parallaxlistview.ParallaxScrollEvent;
import com.poliveira.apps.parallaxlistview.ParallaxScrollView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    ArrayAdapter<String> mAdapter;
    RelativeLayout mContainer;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> mStrings = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mStrings.add("Android String " + i);
        }
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStrings);
        mContainer = (RelativeLayout) findViewById(R.id.container);
        mContainer.post(new Runnable() {
            @Override
            public void run() {
                onClick(findViewById(R.id.btnList));
            }
        });
    }


    public void onClick(View view) {
        if (view.getId() == R.id.btnList) {
            mContainer.removeAllViews();
            final View v = getLayoutInflater().inflate(R.layout.include_listview, mContainer, true);
            ParallaxListView mListView = (ParallaxListView) v.findViewById(R.id.view);
            mListView.setAdapter(mAdapter);
            mListView.setParallaxView(getLayoutInflater().inflate(R.layout.view_header, mListView, false));
            mImageView = new ImageView(this);
            final int size = Math.round(48 * getResources().getDisplayMetrics().density);
            final int buttonsSize = findViewById(R.id.linearLayout).getHeight();
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(size, size);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            params.setMargins(0, 0, Math.round(16 * getResources().getDisplayMetrics().density), 0);
            mImageView.setBackgroundResource(R.drawable.floating_button);
            mImageView.setImageResource(R.drawable.ic_action_add);
            mListView.setScrollEvent(new ParallaxScrollEvent() {
                @Override
                public void onScroll(double percentage, double offset, View parallaxView) {
                    double translation = parallaxView.getHeight() - (parallaxView.getHeight() * percentage) + size / 2 - buttonsSize;
                    ViewCompat.setTranslationY(mImageView, (float) translation);
                }
            });
            mContainer.addView(mImageView, params);
        } else if (view.getId() == R.id.btnScroll) {
            mContainer.removeAllViews();
            View v = getLayoutInflater().inflate(R.layout.include_scrollview, mContainer, true);
            ParallaxScrollView mScrollView = (ParallaxScrollView) v.findViewById(R.id.view);
            mScrollView.setParallaxView(getLayoutInflater().inflate(R.layout.view_header, mScrollView, false));
        } else if (view.getId() == R.id.btnGrid) {
            mContainer.removeAllViews();
            final View v = getLayoutInflater().inflate(R.layout.include_gridview, mContainer, true);
            ParallaxGridView mGridView = (ParallaxGridView) v.findViewById(R.id.view);
            mGridView.setParallaxView(getLayoutInflater().inflate(R.layout.view_header, mGridView, false));
            mGridView.setAdapter(mAdapter);
            mGridView.setNumColumns(2);
        }
    }
}
