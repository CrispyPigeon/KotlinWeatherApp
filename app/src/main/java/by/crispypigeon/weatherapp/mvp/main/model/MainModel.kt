package by.crispypigeon.weatherapp.mvp.main.model

import by.crispypigeon.weatherapp.mvp.datamodels.apimodels.WeatherResponse
import android.app.Activity
import by.crispypigeon.weatherapp.mvp.datamodels.dbmodels.WeatherDbItem
import by.crispypigeon.weatherapp.mvp.services.api.WeatherApi
import by.crispypigeon.weatherapp.mvp.services.database.RealmDatabaseService
import by.crispypigeon.weatherapp.mvp.services.geolocation.GeoLocationService
import com.android.volley.Response
import com.google.android.gms.location.LocationListener
import io.realm.RealmResults

class MainModel(private val activity: Activity) {

    private val weatherApi = WeatherApi()
    private val realmDbService = RealmDatabaseService()
    private val geoLocationService = GeoLocationService(activity)

    fun clearDbItems() {
        realmDbService.ClearAllItems()
    }

    fun saveWeatherItemsToDb(response: WeatherResponse){
        realmDbService.SaveWeatherToDb(response)
    }

    fun getFirstWeatherDbItem() : WeatherDbItem{
       return realmDbService.GetFirstWeatherItem()
    }

    fun getAllWeatherDbItems() : List<WeatherDbItem>{
        return realmDbService.GetAllWeatherItems()
    }

    fun getWeather(
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