package by.crispypigeon.weatherapp.mvp.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.crispypigeon.weatherapp.R
import by.crispypigeon.weatherapp.mvp.datamodels.resultmodels.WeatherItem
import kotlinx.android.synthetic.main.forecast_template_item.view.*

class WeatherAdapter(private val items: List<WeatherItem>) : RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_template_item, parent, false)
        return WeatherHolder(itemView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        val currentItem = items[position]

        holder.temperatureTextView.text = currentItem.temperature
        holder.timeTextView.text = currentItem.time
        holder.conditionTextView.text = currentItem.condition
    }

    class WeatherHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val temperatureTextView = itemView.temperatureItemTextView
        val timeTextView = itemView.timeItemTextView
        val conditionTextView = itemView.conditionItemTextView
    }
}