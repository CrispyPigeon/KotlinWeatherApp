package by.crispypigeon.weatherapp.mvp.datamodels.apimodels

import com.google.gson.annotations.SerializedName

data class Sys (

	@SerializedName("pod") val pod : String
)