package kimxu.me.weather.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import kimxu.me.weather.R
import kimxu.me.weather.domain.Forecast
import kimxu.me.weather.domain.ForecastList
import kotlinx.android.synthetic.main.item.view.*
import org.jetbrains.anko.*

/**
 *
 * Created by kimxu on 2016/12/29.
 */

//class ForecastListAdapter(val weekForecast: ForecastList, val itemClick: OnItemClickListener) :
class ForecastListAdapter(val weekForecast: ForecastList, val itemClick: (Forecast)->Unit) :
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ForecastListAdapter.ViewHolder?, position: Int) {
        holder?.bind(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.dailyForecast.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        return ViewHolder(ForecastListUI().createView(AnkoContext.create(parent!!.context, parent)), itemClick)
    }


    class ForecastListUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent) {
                    orientation = LinearLayout.HORIZONTAL
                }
                imageView {
                    lparams(width = dip(48), height = dip(48))
                    id = R.id.icon
                }
                verticalLayout {
                    textView("date") {
                        id = R.id.date
                        setTextAppearance(ui.ctx,R.style.TextAppearance_AppCompat_Medium)
                    }.lparams {
                        width = matchParent
                        height = wrapContent
                    }
                    textView("description") {
                        id = R.id.description
                        setTextAppearance(ui.ctx,R.style.TextAppearance_AppCompat_Caption)
                    }.lparams {
                        width = matchParent
                        height = wrapContent
                    }
                }.lparams(width = dip(0), height = wrapContent) {
                    leftMargin = dip(10)
                    rightMargin = dip(10)
                    weight = 1f
                }

                verticalLayout {
                    gravity = Gravity.CENTER_HORIZONTAL
                    textView("30°") {
                        id = R.id.maxTemperature
                        setTextAppearance(ui.ctx,R.style.TextAppearance_AppCompat_Medium)
                    }.lparams {
                        width=dip(30)
                       gravity=Gravity.CENTER
                    }
                    textView("15°") {
                        id = R.id.minTemperature
                        setTextAppearance(ui.ctx,R.style.TextAppearance_AppCompat_Caption)
                    }.lparams {
                        width=dip(30)
                        gravity=Gravity.CENTER
                    }
                }
            }
        }
    }


    class ViewHolder(itemView: View, val itemClick: (Forecast)->Unit) : RecyclerView.ViewHolder(itemView) {
//        private val iconView: ImageView
//        private val dateView: TextView
//        private val descriptionView: TextView
//        private val maxTemperatureView: TextView
//        private val minTemperatureView: TextView
//
//        init {
//            iconView = itemView.find(R.id.icon)
//            dateView = itemView.find(R.id.date)
//            descriptionView = itemView.find(R.id.description)
//            maxTemperatureView = itemView.find(R.id.maxTemperature)
//            minTemperatureView = itemView.find(R.id.minTemperature)
//        }

        fun bind(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.context).load(iconUrl).into(itemView.icon)
                itemView.date.text = date.toString()
                itemView.description.text = description
                itemView.maxTemperature.text = "${high.toString()}"
                itemView.minTemperature.text = "${low.toString()}"
                itemView.setOnClickListener {
                    itemClick(forecast)
                }
            }
        }
    }



}




