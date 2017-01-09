package kimxu.me.weather.data.datasource

import kimxu.me.weather.domain.Forecast
import kimxu.me.weather.domain.ForecastList

/**
 *
 * Created by kimxu on 2016/12/30.
 */


interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?
}
