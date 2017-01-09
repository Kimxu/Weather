package kimxu.me.weather.data.db

import kimxu.me.weather.domain.Forecast as ModelForecast
import kimxu.me.weather.domain.ForecastList

/**
 *
 * Created by kimxu on 2016/12/30.
 */



class DbDataMapper {

    fun convertFromDomain(forecast: ForecastList) = with<ForecastList,CityForecast>(forecast) {
        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
       return CityForecast(id, city, country, daily)
    }

    private fun convertDayFromDomain(cityId: Long, forecast:ModelForecast) = with(forecast) {
        DayForecast(date, description, high, low, iconUrl, cityId)
    }

    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, daily)
    }

    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        ModelForecast(_id, date, description, high, low, iconUrl)
    }
}