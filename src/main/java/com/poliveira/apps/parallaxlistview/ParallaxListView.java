package com.poliveira.apps.parallaxlistview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Created by poliveira on 17/10/2014.
 */
public class ParallaxListView extends ListView implements AbsListView.OnScrollListener
{

    private View mHeader;
    private View mView;

    public ParallaxListView(Context context)
    {
        super(context);
        init();
    }

    private void init()
    {
        setOnScrollListener(this);
    }

    public ParallaxListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public ParallaxListView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState)
    {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        if (view.getChildCount() > 0 && view.getChildAt(0) != null)
        {
            float currentOffset = (float) Math.round(-view.getChildAt(0).getTop() * 0.5);
            mHeader.setTranslationY(currentOffset);
            ((CustomRelativeLayout) mHeader).setClipY(Math.round(currentOffset));
            float left = (currentOffset / mHeader.getHeight());
//todo: add to attrs
            mView.setScaleX(left + 1);
            mView.setScaleY(left + 1);
        }
    }

    public void addParallaxView(View v)
    {
        mView = v;
        CustomRelativeLayout relativeLayout = new CustomRelativeLayout(getContext());
        relativeLayout.addView(v);
        mHeader = relativeLayout;
        addHeaderView(relativeLayout);
    }

    static class CustomRelativeLayout extends RelativeLayout
    {

        private int mOffset;

        public CustomRelativeLayout(Context context)
        {
            super(context);
        }

        @Override
        protected void dispatchDraw(Canvas canvas)
        {
            canvas.clipRect(new Rect(getLeft(), getTop(), getRight(), getBottom() + mOffset));
            super.dispatchDraw(canvas);
        }

        public void setClipY(int offset)
        {
            this.mOffset = offset;
            invalidate();
        }
    }
}
