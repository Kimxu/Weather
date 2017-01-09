package kimxu.me.weather.net

import android.util.Log
import java.net.URL

/**
 * Created by kimxu on 2016/12/29.
 */

public class Request(val url: String) {
    public fun run() {
        val forecastJsonStr =URL(url).readText()
        Log.d(javaClass.simpleName,forecastJsonStr)

    }
}
