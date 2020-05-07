package by.crispypigeon.weatherapp.mvp.datamodels.apimodels

import com.google.gson.annotations.SerializedName

data class Clouds (

	@SerializedName("all") val all : Int
)