package by.crispypigeon.weatherapp.services.api

import android.content.Context
import android.net.Uri
import android.widget.Toast
import by.crispypigeon.weatherapp.datamodels.apimodels.WeatherResponse
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


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

    suspend fun getWeatherFromApi(
        context: Context,
        appId: String,
        lat: String,
        lon: String,
        units: String,
        lang: String
    ): WeatherResponse {
        val queue = Volley.newRequestQueue(context)
        val keysMap = mapOf<String, String>(
            appIdKey to appId,
            latKey to lat,
            lonKey to lon,
            unitsKey to units,
            langKey to lang
        )
        var listener: Response.Listener<WeatherResponse>

        return suspendCoroutine { continuation ->

            listener = Response.Listener<WeatherResponse>() { response ->
                if (response != null)
                    continuation.resume(response)
                else
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
            }

            val url = GetFullUrl(forecastKey, keysMap)

            val request = GsonRequest<WeatherResponse>(
                url, WeatherResponse::class.java,
                listener = listener,
                errorListener = Response.ErrorListener {
                    //Implement error action
                }, headers = null
            )

            queue.add(request)
        }
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