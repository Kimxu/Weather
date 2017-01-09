package kimxu.me.weather.domain

/**
 * Created by kimxu on 2016/12/29.
 */
public interface Command<T>{
    fun execute():T

}
