package kimxu.me.weather.net

import android.util.Log
import com.google.gson.Gson
import kimxu.me.weather.data.server.ForecastResult
import java.net.URL

/**
 * Created by kimxu on 2016/12/29.
 */

public class ForecastRequest(val zipCode: Long, val gson: Gson = Gson()) {
    companion object {
        private val APP_ID = "b9ec6960222f1e474e48bad64eae5690"
        private val URL = "http://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="

    }

    fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        Log.d(javaClass.simpleName,forecastJsonStr)
        return gson.fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}