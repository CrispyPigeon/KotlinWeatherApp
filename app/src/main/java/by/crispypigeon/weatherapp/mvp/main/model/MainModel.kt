package by.crispypigeon.weatherapp.mvp.main.model

import WeatherResponse
import android.app.Activity
import android.content.Context
import by.crispypigeon.weatherapp.mvp.services.api.WeatherApi
import by.crispypigeon.weatherapp.mvp.services.geolocation.GeoLocationService
import com.android.volley.Response
import com.google.android.gms.location.LocationListener

class MainModel(private val activity: Activity) {
    private val weatherApi = WeatherApi()
    private val geoLocationService = GeoLocationService(activity)


    fun GetWeather(
        context: Context,
        listener: Response.Listener<WeatherResponse>,
        appId: String,
        units: String,
        lang: String
    ) {
        geoLocationService.setListener(LocationListener { location ->
            weatherApi.GetWeather(
                activity,
                listener,
                appId,
                location.latitude.toString(),
                location.longitude.toString(),
                units,
                lang
            )
        })
        geoLocationService.getLastLocation()
    }
}