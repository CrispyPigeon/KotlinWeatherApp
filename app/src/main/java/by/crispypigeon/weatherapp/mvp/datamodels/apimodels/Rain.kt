package by.crispypigeon.weatherapp.mvp.datamodels.apimodels

import com.google.gson.annotations.SerializedName

data class Rain (

	@SerializedName("3h") val hours : Double
)