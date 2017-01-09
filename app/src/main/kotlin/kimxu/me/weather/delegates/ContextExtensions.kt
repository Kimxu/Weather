package kimxu.me.weather.delegates

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 * Created by kimxu on 2016/12/30.
 */


fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)