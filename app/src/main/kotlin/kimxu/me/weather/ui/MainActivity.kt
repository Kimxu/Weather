package kimxu.me.weather.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Button
import android.widget.EditText
import kimxu.me.weather.R
import kimxu.me.weather.delegates.DelegatesExt
import kimxu.me.weather.domain.RequestForecastCommand
import kimxu.me.weather.ui.adapters.ForecastListAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), ToolbarManager {
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    val zipCode: Long by DelegatesExt.preference(this, SettingsActivity.ZIP_CODE,
            SettingsActivity.DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = MainActivityUi().setContentView(this)
        val recyclerView = view.find<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        attachToScroll(recyclerView)
        initToolbar()
        loadForecast(recyclerView)
//        doAsync() {
//            val result = RequestForecastCommand("94043").execute()
//            uiThread {
//                toast("ok")
////                val recyclerView = view.find<RecyclerView>(R.id.recyclerView)
////                val recyclerView:RecyclerView = view.recyclerView
//
//                view.recyclerView.adapter=ForecastListAdapter(result){
//                    toast(it.date)
//                }
////                recyclerView.adapter = ForecastListAdapter(result){
////                    toast(it.date)
////                }
//            }
//        }
    }


    private fun loadForecast(recyclerView:RecyclerView) = doAsync() {
        var result = RequestForecastCommand(zipCode).execute()
        uiThread {
            toast("ok")
            val adapter = ForecastListAdapter(result) {
                startActivity<DetailActivity>(DetailActivity.ID to it.id,
                        DetailActivity.CITY_NAME to result.city)
            }
            recyclerView.adapter = adapter
            toolbarTitle = "${result.city} (${result.country})"
        }
    }


}

class MainActivityUi : AnkoComponent<MainActivity> {
    private val customStyle = { v: Any ->
        when (v) {
            is Button -> v.textSize = 26f
            is EditText -> v.textSize = 24f
        }
    }

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {

        relativeLayout {
            include<Toolbar>(R.layout.toolbar)
            recyclerView {
                id = R.id.recyclerView
                layoutManager = LinearLayoutManager(ctx)
            }.lparams(height = matchParent, width = matchParent){
                topMargin=dip(10)
                below(R.id.toolbar)
            }

        }.applyRecursively(customStyle)
    }


}
