package by.crispypigeon.weatherapp.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.crispypigeon.weatherapp.R
import by.crispypigeon.weatherapp.datamodels.dbmodels.WeatherDbItem
import kotlinx.android.synthetic.main.forecast_template_item.view.*
import java.text.SimpleDateFormat

class WeatherAdapter(private val items: List<WeatherDbItem>,
                     private val dateFormat: SimpleDateFormat) : RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_template_item, parent, false)
        return WeatherHolder(
            itemView
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        val currentItem = items[position]

        holder.temperatureTextView.text = currentItem.temperature.toString()
        holder.timeTextView.text = dateFormat.format(currentItem.time)
        holder.conditionTextView.text = currentItem.condition
    }

    class WeatherHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val temperatureTextView = itemView.temperatureItemTextView
        val timeTextView = itemView.timeItemTextView
        val conditionTextView = itemView.conditionItemTextView
    }
}