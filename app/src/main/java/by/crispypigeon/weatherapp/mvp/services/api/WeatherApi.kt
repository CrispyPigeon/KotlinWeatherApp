package by.crispypigeon.weatherapp.mvp.services.api

import WeatherResponse
import android.content.Context
import android.net.Uri
import com.android.volley.Response
import com.android.volley.toolbox.Volley


class WeatherApi {
    //Administrative strings
    private val scheme = "https"
    private val baseUrl = "api.openweathermap.org"
    private val department = "data"
    private val version = "2.5"
    private val forecastKey = "forecast"

    //API Keys
    private val appIdKey = "appId"
    private val latKey = "lat"
    private val lonKey = "lon"
    private val unitsKey = "units"
    private val langKey = "lang"

    fun GetWeather(
        context: Context,
        listener: Response.Listener<WeatherResponse>,
        appId: String,
        lat: String,
        lon: String,
        units: String,
        lang: String
    ) {
        val queue = Volley.newRequestQueue(context)
        val keysMap = mapOf<String, String>(
            appIdKey to appId,
            latKey to lat,
            lonKey to lon,
            unitsKey to units,
            langKey to lang
        )
        val url = GetFullUrl(forecastKey, keysMap) //Implement adding a mutablemap
        val request = GsonRequest<WeatherResponse>(
            url, WeatherResponse::class.java,
            listener = listener,
            errorListener = Response.ErrorListener { response ->
                //Implement error action
            }, headers = null
        )

        queue.add(request)
    }

   private fun GetFullUrl(path: String, keys: Map<String, String>): String {
        val builder = Uri.Builder()
        builder.scheme(scheme)
            .authority(baseUrl)
            .appendPath(department)
            .appendPath(version)
            .appendPath(path)
        for (key in keys) {
            builder.appendQueryParameter(key.key, key.value)
        }
        return builder.build().toString()
    }
}