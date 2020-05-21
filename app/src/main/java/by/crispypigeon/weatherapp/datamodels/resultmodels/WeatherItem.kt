package by.crispypigeon.weatherapp.datamodels.resultmodels

import by.crispypigeon.weatherapp.datamodels.dbmodels.WeatherDbItem

data class WeatherItem(
    val weatherNow: WeatherDbItem,
    val forecast24H: List<WeatherDbItem>
)