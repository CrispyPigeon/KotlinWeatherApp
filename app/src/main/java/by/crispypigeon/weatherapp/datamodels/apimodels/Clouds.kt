package by.crispypigeon.weatherapp.datamodels.apimodels

import com.google.gson.annotations.SerializedName

data class Clouds (

	@SerializedName("all") val all : Int
)