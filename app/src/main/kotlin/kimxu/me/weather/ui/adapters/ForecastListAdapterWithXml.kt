package kimxu.me.weather.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kimxu.me.weather.R
import kimxu.me.weather.domain.Forecast
import kimxu.me.weather.domain.ForecastList
import org.jetbrains.anko.find

/**
 *
 * Created by kimxu on 2016/12/29.
 */

class ForecastListAdapterWithXml(val weekForecast:ForecastList,val itemClick: OnItemClickListener):
        RecyclerView.Adapter<ForecastListAdapterWithXml.ViewHolder>(){
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int =weekForecast.dailyForecast.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): ViewHolder? {

        val view =LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ViewHolder(view,itemClick)
    }

    class ViewHolder(val view: View, val itemClick:OnItemClickListener):
            RecyclerView.ViewHolder(view){

        private val iconView: ImageView
        private val dateView: TextView
        private val descriptionView: TextView
        private val maxTemperatureView: TextView
        private val minTemperatureView: TextView

        init {
            iconView = view.find(R.id.icon)
            dateView = view.find(R.id.date)
            descriptionView = view.find(R.id.description)
            maxTemperatureView = view.find(R.id.maxTemperature)
            minTemperatureView = view.find(R.id.minTemperature)
        }

        fun bindForecast(forecast:Forecast){
            with(forecast){
                Picasso.with(itemView.context).load(iconUrl).into(iconView)
                dateView.text=date.toString()
                descriptionView.text=description
                maxTemperatureView.text="${high.toString()}"
                minTemperatureView.text="${low.toString()}"
                itemView.setOnClickListener {
                    itemClick(forecast)
                }
            }
        }

    }

    public interface OnItemClickListener{
        operator fun invoke(forecast: Forecast)
    }

}




