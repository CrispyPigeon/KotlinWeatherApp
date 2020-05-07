package by.crispypigeon.weatherapp.mvp.datamodels.apimodels

import com.google.gson.annotations.SerializedName

data class WeatherState (

	@SerializedName("dt") val dt : Int,
	@SerializedName("main") val main : Main,
	@SerializedName("weather") val weather : List<Weather>,
	@SerializedName("clouds") val clouds : Clouds,
	@SerializedName("wind") val wind : Wind,
	@SerializedName("rain") val rain : Rain,
	@SerializedName("sys") val sys : Sys,
	@SerializedName("dt_txt") val dt_txt : String
)