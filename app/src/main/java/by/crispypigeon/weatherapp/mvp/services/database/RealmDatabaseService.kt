package by.crispypigeon.weatherapp.mvp.services.database

import by.crispypigeon.weatherapp.mvp.datamodels.apimodels.WeatherResponse
import by.crispypigeon.weatherapp.mvp.datamodels.dbmodels.CityDbItem
import by.crispypigeon.weatherapp.mvp.datamodels.dbmodels.WeatherDbItem
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.createObject
import java.text.SimpleDateFormat
import java.util.*

class RealmDatabaseService {
    private val realm: Realm = Realm.getDefaultInstance()
    private val datePattern = "yyyy-MM-dd HH:mm:ss"

    fun GetFirstWeatherItem(): WeatherDbItem {
        return realm.where(WeatherDbItem::class.java).findFirst()!!
    }

    fun GetAllWeatherItems(): List<WeatherDbItem> {
        val result = realm.where(WeatherDbItem::class.java).findAll()
        return realm.copyFromRealm(result)
    }

    fun ClearAllItems() {
        realm.executeTransaction() {
            realm.deleteAll()
        }
    }

    fun SaveWeatherToDb(response: WeatherResponse) {

        val idNumber: Number? = realm.where(WeatherDbItem::class.java).max("id")
        var currentId: Int
        currentId = if (idNumber?.toInt() == null || idNumber.toInt() == 0) {
            1
        } else {
            idNumber.toInt()
        }

        realm.executeTransaction()
        {
            val cityDbItem = realm.createObject<CityDbItem>()
            cityDbItem.index = response.city.country
            cityDbItem.name = response.city.name
            cityDbItem.lontitude = response.city.coord.lon
            cityDbItem.latitude = response.city.coord.lat

            for (item in response.list) {
                val weatherDbItem = realm.createObject<WeatherDbItem>(++currentId)
                weatherDbItem.condition = item.weather.first().description
                weatherDbItem.temperature = item.main.temp
                weatherDbItem.time = SimpleDateFormat(datePattern).parse(item.dt_txt)
                weatherDbItem.city = cityDbItem
            }
        }
    }
}