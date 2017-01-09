package kimxu.me.weather.domain

import kimxu.me.weather.data.datasource.ForecastProvider
import kimxu.me.weather.data.datasource.ForecastServer
import kimxu.me.weather.net.ForecastRequest

/**
 *
 * Created by kimxu on 2016/12/29.
 */

class RequestForecastCommand(
        val zipCode: Long,
        val forecastProvider: ForecastProvider = ForecastProvider(listOf(ForecastServer()))) :
        Command<ForecastList> {

    companion object {
        val DAYS = 5
    }

    override fun execute():ForecastList {
        val result =forecastProvider.requestByZipCode(zipCode, DAYS)

         return result
    }
}