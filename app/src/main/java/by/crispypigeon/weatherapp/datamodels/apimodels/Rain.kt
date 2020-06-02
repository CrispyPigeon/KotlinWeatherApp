package by.crispypigeon.weatherapp.datamodels.apimodels

import com.google.gson.annotations.SerializedName

data class Rain (

	@SerializedName("3h") val hours : Double
)