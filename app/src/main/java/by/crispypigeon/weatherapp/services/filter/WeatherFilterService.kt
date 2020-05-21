package by.crispypigeon.weatherapp.services.filter

import by.crispypigeon.weatherapp.datamodels.dbmodels.WeatherDbItem
import java.util.*
import kotlin.math.abs

class WeatherFilterService : IWeatherService {

    override fun getCurrentWeather(items: List<WeatherDbItem>): WeatherDbItem {

        val specifiedDate = Calendar.getInstance().time;

        var nearestDateItem = items.minBy{ abs(it.time.time-specifiedDate.time) }

        return nearestDateItem!!
    }

    override fun get24HForecast(items: List<WeatherDbItem>): List<WeatherDbItem> {

        val currentDate = Calendar.getInstance().time;

        var increasedDate = currentDate;
        val calendar = Calendar.getInstance()
        calendar.time = increasedDate
        calendar.add(Calendar.DATE, 1)
        increasedDate = calendar.time

        return items.filter { item -> item.time < increasedDate && item.time > currentDate }
    }

    override fun validateForecasts(items: List<WeatherDbItem>?): Boolean {

        if (items == null)
            return false

        val currentDate = Calendar.getInstance().time;

        if (items.any { item -> item.time.date == currentDate.date })
            return true
        return false
    }
}