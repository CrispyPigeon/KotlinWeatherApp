package by.crispypigeon.weatherapp.mvp.datamodels.apimodels

import com.google.gson.annotations.SerializedName

data class Wind (

	@SerializedName("speed") val speed : Double,
	@SerializedName("deg") val deg : Int
)