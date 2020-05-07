package by.crispypigeon.weatherapp.mvp.datamodels.apimodels

import com.google.gson.annotations.SerializedName

data class WeatherResponse (
	@SerializedName("cod") val cod : Int,
	@SerializedName("message") val message : Int,
	@SerializedName("cnt") val cnt : Int,
	@SerializedName("list") val list : List<WeatherState>,
	@SerializedName("city") val city : City
)