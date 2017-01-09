package kimxu.me.weather.data.datasource

import kimxu.me.weather.data.db.ForecastDb
import kimxu.me.weather.delegates.firstResult
import kimxu.me.weather.domain.Forecast
import kimxu.me.weather.domain.ForecastList

/**
 * Created by kimxu on 2016/12/30.
 */
class ForecastProvider(val sources:List<ForecastDataSource> = ForecastProvider.SOURCES) {
    companion object{
        val DAY_IN_MILLIS=1000*60*60*24
        val SOURCES= listOf<ForecastDataSource>(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size() >= days)
            res
        else
            null
    }

    fun requestForecast(id: Long): Forecast = requestToSources {
        it.requestDayForecast(id)
    }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T = sources.firstResult { f(it) }

}