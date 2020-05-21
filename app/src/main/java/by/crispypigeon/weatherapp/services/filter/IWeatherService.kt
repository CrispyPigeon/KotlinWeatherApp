package by.crispypigeon.weatherapp.services.filter

import by.crispypigeon.weatherapp.datamodels.dbmodels.WeatherDbItem

interface IWeatherService {
    fun getCurrentWeather(items: List<WeatherDbItem>): WeatherDbItem
    fun get24HForecast(items: List<WeatherDbItem>): List<WeatherDbItem>
    fun validateForecasts(items: List<WeatherDbItem>?): Boolean
}