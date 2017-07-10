package dallasapps.nsunstracker.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by katy on 7/9/17.
 */

public class Converter {

    public static int convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int dp =  Math.round(px / (metrics.densityDpi / 160f));
        return dp;
    }

    public static int convertDpToPixels(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px =  Math.round(dp * (metrics.densityDpi / 160f));
        return px;
    }

}
