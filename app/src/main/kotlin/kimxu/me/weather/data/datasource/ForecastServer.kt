package kimxu.me.weather.data.datasource

import kimxu.me.weather.data.db.DbDataMapper
import kimxu.me.weather.data.db.ForecastDb
import kimxu.me.weather.data.server.ServerDataMapper
import kimxu.me.weather.domain.ForecastList
import kimxu.me.weather.net.ForecastRequest

/**
 *
 * Created by kimxu on 2016/12/30.
 */


public class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(),
                     val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long) = throw UnsupportedOperationException()
}
