package by.crispypigeon.weatherapp.view.main

import by.crispypigeon.weatherapp.datamodels.dbmodels.WeatherDbItem
import java.util.*

interface IMainView {
    fun showCurrentWeather(
        countryIndex: String,
        temperature: Double,
        lon: Double,
        lat: Double,
        condition: String,
        time: Date
    )

    fun showForecasts(forecasts: List<WeatherDbItem>)
}