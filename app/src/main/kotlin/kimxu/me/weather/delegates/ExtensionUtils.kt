package kimxu.me.weather.delegates

import java.text.DateFormat
import java.util.*

/**
 * Created by kimxu on 2016/12/30.
 */


fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}