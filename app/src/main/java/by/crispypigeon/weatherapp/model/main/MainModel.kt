package by.crispypigeon.weatherapp.model.main

import by.crispypigeon.weatherapp.datamodels.apimodels.WeatherResponse
import android.app.Activity
import by.crispypigeon.weatherapp.datamodels.dbmodels.WeatherDbItem
import by.crispypigeon.weatherapp.datamodels.resultmodels.WeatherItem
import by.crispypigeon.weatherapp.services.api.WeatherApi
import by.crispypigeon.weatherapp.services.database.WeatherDatabaseService
import by.crispypigeon.weatherapp.services.filter.WeatherFilterService
import by.crispypigeon.weatherapp.services.geolocation.GeoLocationService

class MainModel(private val activity: Activity) {

    private val appId = "0160dc60ff74dfca79aa310ee187d61e"
    private val unit = "metric"
    private val lang = "ru"

    private val weatherApi = WeatherApi()
    private val realmDbService = WeatherDatabaseService()
    private val weatherFilterService = WeatherFilterService()
    private val geoLocationService = GeoLocationService(activity)

    private fun clearDbItems() {
        realmDbService.deleteAll()
    }

    private fun saveWeatherItemsToDb(response: WeatherResponse) {
        realmDbService.saveItemsFromAnother(response)
    }

    private fun getAllWeatherDbItems(): List<WeatherDbItem> {
        return realmDbService.getAllItems()
    }

    suspend fun getWeather(): WeatherItem {
        var forecasts = getAllWeatherDbItems()

        if (!weatherFilterService.validateForecasts(forecasts)) {
            clearDbItems()

            val response = getUpdatedForecastsFromApi()

            saveWeatherItemsToDb(response)

            forecasts = getAllWeatherDbItems()
        }

        val weatherNow = weatherFilterService.getCurrentWeather(forecasts)
        val todayForecasts = weatherFilterService.get24HForecast(forecasts)

        return WeatherItem(weatherNow, todayForecasts)
    }

    private suspend fun getUpdatedForecastsFromApi(): WeatherResponse {
        val location = geoLocationService.requestLocationData()
        return weatherApi.getWeatherFromApi(
            activity,
            appId,
            location.latitude.toString(),
            location.longitude.toString(),
            unit,
            lang
        )
    }

    suspend fun updateForecastsFromApi(): WeatherItem {
        clearDbItems()

        val response = getUpdatedForecastsFromApi()

        saveWeatherItemsToDb(response)

        val forecasts = getAllWeatherDbItems()
        val weatherNow = weatherFilterService.getCurrentWeather(forecasts)
        val todayForecasts = weatherFilterService.get24HForecast(forecasts)

        return WeatherItem(weatherNow, todayForecasts)
    }
}