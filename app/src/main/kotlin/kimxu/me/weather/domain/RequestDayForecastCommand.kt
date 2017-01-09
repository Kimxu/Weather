package kimxu.me.weather.domain

import kimxu.me.weather.data.datasource.ForecastProvider

/**
 * Created by kimxu on 2016/12/30.
 */


class RequestDayForecastCommand(
        val id: Long,
        val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<Forecast> {

    override fun execute() = forecastProvider.requestForecast(id)
}