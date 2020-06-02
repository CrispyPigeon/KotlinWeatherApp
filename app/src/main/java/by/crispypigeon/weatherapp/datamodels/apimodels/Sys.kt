package by.crispypigeon.weatherapp.datamodels.apimodels

import com.google.gson.annotations.SerializedName

data class Sys (

	@SerializedName("pod") val pod : String
)