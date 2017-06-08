package belka.us.androidtoggleswitch.widgets

import android.content.Context

/**
 * Created by lorenzorigato on 08/06/2017.
 */

fun dp2px(context: Context, dp: Float): Float {
    val metrics = context.resources.displayMetrics
    return dp * (metrics.densityDpi / 160f)
}