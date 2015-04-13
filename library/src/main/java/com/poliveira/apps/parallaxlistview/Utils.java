package com.poliveira.apps.parallaxlistview;

import android.os.Build;
import android.view.View;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by poliveira on 20/10/2014.
 */
class Utils {
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public static int generateId() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1)
            for (; ; ) {
                final int result = sNextGeneratedId.get();
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF) newValue = 1;
                if (sNextGeneratedId.compareAndSet(result, newValue))
                    return result;
            }
        else {
            return View.generateViewId();
        }
    }
}
