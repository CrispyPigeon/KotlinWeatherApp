package by.crispypigeon.weatherapp.mvp.main.view

import by.crispypigeon.weatherapp.mvp.datamodels.resultmodels.WeatherItem

interface IMainView {
    fun ShowCurrentWeather(
        countryIndex: String,
        temperature: Double,
        lon: Double,
        lat: Double,
        condition: String,
        time: String
    )

    fun ShowForecasts(forecasts: List<WeatherItem>)
}