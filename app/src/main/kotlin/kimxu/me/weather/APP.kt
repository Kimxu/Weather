package kimxu.me.weather

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kimxu.me.weather.delegates.DelegatesExt
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 *
 * Created by kimxu on 2016/12/30.
 */
class APP : Application() {


    companion object {
        var instance: APP by DelegatesExt.notNullSingleValue<APP>()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


}

