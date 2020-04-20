package by.crispypigeon.weatherapp.mvp.main.model

import WeatherResponse
import android.content.Context
import by.crispypigeon.weatherapp.mvp.services.api.WeatherApi
import com.android.volley.Response

class MainModel {
    private val weatherApi = WeatherApi()

    fun GetWeather(
        context: Context,
        listener: Response.Listener<WeatherResponse>,
        appId: String,
        lat: Double,
        lon: Double,
        units: String,
        lang: String
    ) {
        weatherApi.GetWeather(context, listener, appId, lat.toString(), lon.toString(), units, lang)
    }
}